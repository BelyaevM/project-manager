package com.bmp.projectmanager.application.domain.mail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bmp.projectmanager.application.domain.entity.User;

@Service
public class MailClient {

    private static String MAIL_FOLDER = "mails";
    private static String MAIL_FROM_DEFAULT = "no-reply@projm.com";

	private JavaMailSender mailSender;

	@Autowired
	private MessageSource messageSource;

	@Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String getTemplate(String name, Locale locale, Map<String, String> replaces) throws IOException {

        String lang = locale.getLanguage();
        String fileName = MAIL_FOLDER + File.separator + name + "_" + lang + ".tmpl";
        String defaultFileName = MAIL_FOLDER + File.separator + name + "_en.tmpl";

        File file = null;

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        URL resourceName = classLoader.getResource(fileName);

        if (resourceName == null) {
            resourceName = classLoader.getResource(defaultFileName);
        }

        file = new File(resourceName.getFile());

        String content = null;
        if (file.exists()) {
            content = new String(Files.readAllBytes(file.toPath()));

            if (content != null && content.length() > 0) {
                for (Map.Entry<String, String> entry : replaces.entrySet()) {
                    content = content.replace(entry.getKey(), entry.getValue());
                }

            }
        }

        return content;
    }

    public void sendMail(String from, String to, String subject, String msg) {
        try {

            MimeMessage message = mailSender.createMimeMessage();

            message.setSubject(subject);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(msg, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    public void sendRegistrationEmail(User user, String hostUrl) {
        Map<String, String> replaces = new HashMap<>();
        replaces.put("{fullName}", user.getFullName());
        replaces.put("{url}", "http://" + hostUrl + "/profile/activate?email=" + user.getEmail() + "&token=" + user.getToken());

        try {
            String content = getTemplate("registration", Locale.ENGLISH, replaces);
            String subject = messageSource.getMessage("mail.registration.subject", null, Locale.ENGLISH);

            sendMail(MAIL_FROM_DEFAULT, user.getEmail(), subject, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
