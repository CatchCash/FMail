var $$ = mdui.JQ;

$$('.newmail-btn').on('click', function(e) {
    document.location.assign('newmail.html');
});

var drawer = document.getElementById('drawer');
drawer.addEventListener('opened.mdui.drawer', function() {
    $$('.mail-container').removeClass('mdui-col-md-8 mdui-col-offset-md-2')
    $$('.mail-container').addClass('mdui-col-md-10 mdui-col-offset-md-1')
});
drawer.addEventListener('closed.mdui.drawer', function() {
    $$('.mail-container').removeClass('mdui-col-md-10 mdui-col-offset-md-1')
    $$('.mail-container').addClass('mdui-col-md-8 mdui-col-offset-md-2')
});

$(document).ready(function() {
    $(".mail-header").click(function() {
        $.ajax({
            url: "../html/mail-details.html",
            cache: false,
            success: function(result) {
                $(".mail-details").html(result);
            }
        });
    });
});
