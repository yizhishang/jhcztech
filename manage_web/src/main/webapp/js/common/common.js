/**
 * Created by dong on 2016/3/18.
 */
$(document).ready(function () {
    $("html").niceScroll({styler: "fb", cursorcolor: "#000"});
    $(".backToTop").goToTop();
    $(window).bind('scroll resize', function () {
        $(".backToTop").goToTop({
            duration: 200
        });
    });
});
/*
var qrcode = new QRCode($("#erweima").get(0), {
    width: 139,
    height: 136
});
qrcode.makeCode("http://www.jhcztech.com");*/
