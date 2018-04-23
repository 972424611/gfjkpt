var clickTimes = 0;
$('#price').on('click',function () {
    clickTimes++;
    console.log(clickTimes);
    if(clickTimes % 2 === 0){
        $('#price span:eq(0)').css('display','inline');
        $('#price span:eq(1)').css('display','none');
    } else {
        $('#price span:eq(0)').css('display','none');
        $('#price span:eq(1)').css('display','inline');
    }
});

layui.use('laypage', function(){
    var laypage = layui.laypage;

    //执行一个laypage实例
    laypage.render({
        elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
        ,count: 50 //数据总数，从服务端得到
    });
});