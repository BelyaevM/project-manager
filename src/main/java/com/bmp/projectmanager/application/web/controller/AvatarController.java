package com.bmp.projectmanager.application.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bmp.projectmanager.spring.UserDetailsImpl;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller
@RequestMapping("/avatar")
public class AvatarController {

    public static String BIG_AVATAR_POSTFIX = "_big_thumb.png";
    public static String SMALL_AVATAR_POSTFIX = "_small_thumb.png";

    @Value("${project.manager.avatar.dir.path}")
    private String avatarDirPath;

    @GetMapping(value="/big/{userId}", produces=MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public FileSystemResource bigAvatar(ModelAndView modelAndView, @PathVariable Long userId) {
        return this.getAvatar(userId, BIG_AVATAR_POSTFIX);
    }


    @GetMapping(value="/small/{userId}", produces=MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public FileSystemResource smallAvatar(ModelAndView modelAndView, @PathVariable Long userId, HttpServletResponse response) {
        return this.getAvatar(userId, SMALL_AVATAR_POSTFIX);
    }

    private FileSystemResource getAvatar(Long id, String postfix) {
        String avatarFileName = avatarDirPath + File.separator + id + File.separator + id + postfix;

        File f = new File(avatarFileName);
        if(f.exists() && !f.isDirectory()) {
            return new FileSystemResource(f);
        }

        return null;
    }

    @GetMapping("/upload")
    public ModelAndView avatarUpload(ModelAndView modelAndView) {
        modelAndView.setViewName("avatar/avatar_upload");
        return modelAndView;
    }

    @PostMapping("/upload")
    public ModelAndView avatarUploadProcessing(@RequestParam("files") MultipartFile[] files, ModelAndView modelAndView,
            Authentication auth, HttpServletRequest request) {

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        modelAndView.setViewName("redirect:/profile/view");

        for (MultipartFile multipartFile : files) {
            String filePath = avatarDirPath + File.separator + userDetails.getUser().getId() + File.separator;

            if(! new File(filePath).exists()) {
                new File(filePath).mkdirs();
            }

            try {
                FileUtils.cleanDirectory(new File(filePath));

                String orgName = multipartFile.getOriginalFilename();
                String fullFilePath = filePath + orgName;

                File dest = new File(fullFilePath);
                multipartFile.transferTo(dest);

                Thumbnails.of(dest).size(80, 80).crop(Positions.CENTER).toFile(new File(filePath + userDetails.getUser().getId() + BIG_AVATAR_POSTFIX));
                Thumbnails.of(dest).size(35, 35).crop(Positions.CENTER).toFile(new File(filePath + userDetails.getUser().getId() + SMALL_AVATAR_POSTFIX));

            } catch (IllegalStateException e) {
                System.out.println(e);
                e.printStackTrace();
                modelAndView.setViewName("redirect:/avatar/error");
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
                modelAndView.setViewName("redirect:/avatar/error");
            }
        }

        return modelAndView;
    }

}
