$(window).bind('scroll',function () {
    var top = $(this).scrollTop();
    if(top >= 50){
        $('.section-left').css('top',0);
    } else {
        $('.section-left').css('top',50);
    }
});