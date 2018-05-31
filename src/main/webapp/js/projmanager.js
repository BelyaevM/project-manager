function ajaxEmployerListUpdate(elem) {
    var pattern = elem.val();
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    
    $.ajax({
        method: "POST",
        url: "/ajax/user/filter",
        data: { q: pattern },
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },        
        success: function(data) {

            var tr = $('#usersList tr');
            $.each(tr, function(i, elem){
                if(!$(elem).hasClass('column-header')) {
                    $(elem).remove();
                }
            });

            $.each(data, function(i, elem) {
                var newTr = $('#templateRow').clone();
                newTr.attr('id','');

                var td = $('td', newTr);
                $($(td).get(0)).text(elem.id);
                $($(td).get(1)).text(elem.email);
                $($(td).get(2)).text(elem.firstName + ' ' + elem.lastName);
                $($(td).get(3)).text(elem.roles);
                $($(td).get(4)).text(elem.enabled);

                var settingsLink = $('.templateEdit', newTr);
                settingsLink.prop('href', "/user/settings/" + elem.id);

                $('#usersList').append(newTr);
            });

        },
        error: function() {
            elem.val('');
            alert('Employers filter error!');
        }
    });
}
