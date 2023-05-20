$(function () {
    // 滚动箭头
    $(window).scroll(function () {
        $(this).scrollTop() > 100 ? $('#back-to-top').fadeIn() : $('#back-to-top').fadeOut()
    });
    $('#back-to-top').on('click', function (e) {
        return e.preventDefault(), $('html, body').animate({
            scrollTop: 0
        }, 500);
    });
    // 搜索框
    $(".search").on("input propertychange", function () {
        if (this.value && this.value.replace(/\s*/g, '')) {
            $("#common-packages").hide();
            var search = this.value.replace(/\s*/g, '');
            var list = [];
            $('#common-packages a').each(function (i, obj) {
                if ($(obj).attr('alt').indexOf(search) > -1) {
                    list.push($(obj).prop("outerHTML"));
                }
                $("#search-results").html(list).show();
            });
        } else $("#common-packages").show(), $("#search-results").hide();
    }), $(document).on("input", ".clearable", function () {
        this.value ? $(this).next("i").removeClass("fa-search").addClass("fa-close x") : $(this).next("i").removeClass("fa-close").addClass("fa-search")
    }).on("mousemove", ".x", function (e) {
        $(this).addClass("onX")
    }).on("click", ".onX", function () {
        $(this).removeClass("x onX").prev("input").val("").change(), $(this).removeClass("fa-close").addClass("fa-search"), $("#common-packages").show(), $("#search-results").hide()
    });
    // 接口数量
    $('.package-amount strong').html(toThousands($('#common-packages a').length));
});