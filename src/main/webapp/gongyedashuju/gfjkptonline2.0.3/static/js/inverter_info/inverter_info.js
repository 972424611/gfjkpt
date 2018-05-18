var doMainName = 'http://localhost:8080';


/*树形菜单开始*/
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event,menuTree,treeNode) {
            loadChart(thisDay,optionDay);
        }
    }
};

var zNodes =[
    { id:1, pId:0, name:"综合教学楼", isParent : true},
    { id:11, pId:1, name:"综合教学楼"},
    { id:2, pId:0, name:"工科楼"},
    { id:21, pId:2, name:"工科一号楼"},
    { id:22, pId:2, name:"工科二号楼"},
    { id:22, pId:2, name:"工科三号楼"},
    { id:3, pId:0, name:"行健轩", isParent:true},
    { id:31, pId:3, name:"行健轩一栋"},
    { id:32, pId:3, name:"行健轩二栋"},
    { id:33, pId:3, name:"行健轩三栋"},
    { id:34, pId:3, name:"行健轩四栋"},
    { id:35, pId:3, name:"行健轩五栋"},
    { id:36, pId:3, name:"行健轩六栋"}
];
$.fn.zTree.init($("#myTree"), setting, zNodes);
/*树形菜单结束*/

//获取图表容器
var thisDay = document.getElementById('thisDay-container');
var thisMonth = document.getElementById('thisMonth-container');
var thisQuarter = document.getElementById('thisQuarter-container');
var thisYear = document.getElementById('thisYear-container');
var thisCustom = document.getElementById('custom-container');

$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {//shown.bs.tab为tab选项卡高亮事件,
    //关键操作！！！！
    // 获取已激活的标签页的名称
    var activeTab = $(e.target)[0].hash;//hash 属性是一个可读可写的字符串，该字符串是 URL 的锚部分（从 # 号开始的部分）
    if(activeTab === '#thisDay'){
        nowDay = getNowFormatDate(new Date());
        $day_input.val(getNowFormatDate(nowDay));
        toChart(nowDay,'day');
    }else if(activeTab === "#thisMonth"){
        nowDay = getNowFormatDate(new Date());
        var month = nowDay.substring(0,7);
        toChart(month,'month');
    }else if(activeTab === "#thisQuarter"){
        nowDay = getNowFormatDate(new Date());
        loadChart(thisQuarter,optionQuarter);
    }else if(activeTab === "#thisYear"){
        nowDay = getNowFormatDate(new Date());
        var year = nowDay.substring(0,4);
        toChart(year,'year');
    }else if(activeTab === "#thisCustom"){
        nowDay = getNowFormatDate(new Date());
        loadChart(thisCustom,optionCustom);
    }
});


//根据日期加载表格
function loadChart(dateElem,dateChartOption) {//第一个参数为日期的类型（年月周日季度）,第二个为不同类型日期对应的表格的设置
    echarts.dispose(dateElem);
    var thisDayChart = echarts.init(dateElem);
    thisDayChart.setOption(dateChartOption);
    window.onresize = function () {
        thisDayChart.resize();
    };
}

/**layui日期插件**/
layui.use('laydate', function(){
    var laydate = layui.laydate;
    laydate.render({
        elem: '#day-input',
        type : 'date',
        done: function(value){
            console.log(value); //得到日期生成的值，如：2017-08-18
            nowDay = value;
            toDayChart(nowDay);
        }
    });
    laydate.render({
        elem: '#custom-input-left',
        type : 'date'
    });
    laydate.render({
        elem: '#custom-input-right',
        type : 'date'
    })
});


/*日期选择模块开始*/
var domain = '';
var inverter_name = '';
var $day_input = $('#day-input');
var $previous_day = $('.previous-day');
var $next_day = $('.next-day');
var $previous_month = $('#previous-month');
var $now_month = $('#now-month');
var $next_month = $('#next-month');
var $previous_year = $('#previous-year');
var $now_year = $('#now-year');
var $next_year = $('#next-year');
var date = new Date();
var nowDay = getNowFormatDate(date);
var year = parseInt(nowDay.substring(0,4));

toChart(nowDay,'day');//进入页面默认加载当前天的数据
previewPic();


$day_input.val(nowDay);

$previous_day.on('click',function () {
    previousDay();
});
$next_day.on('click',function () {
    nextDay();
});
$previous_month.on('click',function () {
    previousMonth();
});
$now_month.on('click',function () {
    nowMonth();
});
$next_month.on('click',function () {
    nextMonth();
});
$previous_year.on('click',function () {
    previousYear();
});
$now_year.on('click',function () {
    nowYear();
});
$next_year.on('click',function () {
    nextYear();
});

function previousDay() {//上一天
    nowDay = addDate(nowDay,-1);
    $day_input.val(nowDay);
    toChart(nowDay,'day');
}

function nextDay() {//下一天
    nowDay = addDate(nowDay,1);
    $day_input.val(nowDay);
    toChart(nowDay,'day');
}

function previousMonth() {//上一月
    nowDay = getPreMonth(getNowFormatDate(nowDay));
    var month = nowDay.substring(0,7);
    toChart(month,'month');
}

function nowMonth() {//本月
    nowDay = getNowFormatDate(new Date());
    var month = nowDay.substring(0,7);
    toChart(month,'month');
}

function nextMonth() {//下一月
    nowDay = getNextMonth((getNowFormatDate(nowDay)));
    var month = nowDay.substring(0,7);
    toChart(month,'month');
}


function previousYear() {
    year = parseInt(year);
    year --;
    console.log(year);
    toChart(year,'year');
}

function nowYear() {
    var nowDay = getNowFormatDate(new Date());
    year = nowDay.substring(0,4);
    toChart(year,'year');
}

function nextYear() {
    year = parseInt(year);
    year ++;
    toChart(year,'year');
}

function toChart(date,dateType) {
    $.ajax({
        type : 'get',
        url : doMainName + '/inverter/chart?name=inverter1&type=' + dateType + '&date=' + date,
        dataType : 'json',
        success : function (jsonResult) {
            var totalActivePower = pushData(jsonResult,'dailyOutput');
            var tansTemp1 = pushData(jsonResult,'tansTemp1');
            var tansTemp2 = pushData(jsonResult,'tansTemp2');
            var times = pushData(jsonResult,'times');
            var titleDateLeft;
            var titleDateRight;
            var chartTitle = '';
            if(dateType === 'day'){
                titleDateLeft = getNowChineseDate(date);
                titleDateRight = getNowChineseDate(addDate(date,+1));
                chartTitle = titleDateLeft + '至' + titleDateRight + '发电量监测';
            } else if(dateType === 'month'){
                titleDateLeft = getNowChineseDate(date);
                titleDateRight = getNowChineseDate(getNextMonth(date));
                chartTitle = titleDateLeft + '发电量监测';
            } else if(dateType === 'quarter'){

            } else if(dateType === 'year'){
                chartTitle = getNowChineseDate(date)  + '发电量监测';
            }
            temperListen(tansTemp1);
            temperListen(tansTemp2);
            outputListen(totalActivePower);
            console.log(totalActivePower);
            var option = {
                title : {
                    text : chartTitle,
                    left : 'center'
                },
                tooltip : {
                    show : true,
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle: {
                            color: '#999'
                        }
                    }
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend : {
                    data : ['发电量（wh）','模块一温度（℃）','模块二温度（℃）'],
                    left : 20,
                    bottom : 0
                },
                xAxis: {
                    type: 'category',
                    data: times
                },
                yAxis: [
                    {
                        name : '发电量（wh）',
                        type: 'value'
                        // max : 16000,
                        // min : 0
                        // interval : 40
                    },
                    {
                        name : '温度（℃）',
                        type: 'value'
                        // max : 100,
                        // min : -10,
                        // interval : 10
                    }
                ],
                series: [
                    {
                        name : '发电量（wh）',
                        data: totalActivePower,
                        type: 'bar',
                        smooth: true,
                        yAxisIndex : 0,
                        markPoint : {
                            data : [ {
                                type : 'max',
                                name : 'max'
                            }, {
                                type : 'min',
                                name : 'min'
                            } ]
                        },
                        itemStyle : {
                            color : '#2F4554'
                        }
                    },
                    {
                        name : '模块一温度（℃）',
                        data: tansTemp1,
                        type: 'line',
                        smooth: true,
                        yAxisIndex : 1,//规定使用的坐标
                        itemStyle : {
                            color : '#C23531'
                        }
                    },
                    {
                        name : '模块二温度（℃）',
                        data: tansTemp2,
                        type: 'line',
                        smooth: true,
                        yAxisIndex : 1,
                        itemStyle : {
                            color : '#008000'
                        }
                    }
                ]
            };
            if(dateType === 'day'){
                loadChart(thisDay,option);
            } else if(dateType === 'month'){
                loadChart(thisMonth,option);
            } else if(dateType === 'year'){
                loadChart(thisYear,option);
            }

        },
        error : function (err) {
            console.log(err);
        }

    })
}

function getNowFormatDate(date) {//格式化日期的函数，格式为yyyy-mm-dd
    date = new Date(date);
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

function getNowChineseDate(date) {//格式化日期的函数，格式为yyyy-mm-dd
    var dateArr = date.split('-');
    var tmp;
    if(date.length === 10){
        tmp = dateArr[0] + '年' + dateArr[1] + '月' + dateArr[2] + '日';
    } else if(date.length === 7){
        tmp = dateArr[0] + '年' + dateArr[1] + '月';
    } else if(date.length === 4){
        tmp = dateArr[0] + '年';
    }
    return tmp;
}

function addDate(date,days){//date : 当前日期，days : 当前要增加的天数
    var d=new Date(date);
    d.setDate(d.getDate()+days);
    var month=d.getMonth()+1;
    var day = d.getDate();
    if(month<10){
        month = "0"+month;
    }
    if(day<10){
        day = "0"+day;
    }
    var val = d.getFullYear() + "-" + month + "-" + day;
    return val;
}

function getPreMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 === 0) {
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }
    var t2 = year2 + '-' + month2 + '-' + day2;
    return t2;
}

function getNextMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中的月的天数
    var year2 = year;
    var month2 = parseInt(month) + 1;
    if (month2 === 13) {
        year2 = parseInt(year2) + 1;
        month2 = 1;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }

    var t2 = year2 + '-' + month2 + '-' + day2;
    return t2;
}

function pushData(data,dataName) {//把json数据中的每一项解析并拼装成数组
    var result = [];
    for(var i = 0; i < data.data.length; i++){
        result.push(data.data[i][dataName]);
    }
    return  result;
}
/*日期选择模块结束*/

/*报警监听开始*/
var $temp_warning = $('#temp-warning');
var $temp_success = $('#temp-success');
var $power_warning = $('#power-warning');
var $power_success  = $('#power-success');
function temperListen(t) {
    var flag = 1; //0表示异常，1表示正常
    var maxTemperature = 80;
    var minTemperature = -10;
    for(var i = 0;i < t.length; i++){
        if(maxTemperature < t[i] || minTemperature > t[i]){
            console.log('温度异常');
            flag = 0;
            break;
        }
    }
    if(flag === 0){
        $temp_warning.css('display','block');
        $temp_success.css('display','none');
    } else {
        $temp_warning.css('display','none');
        $temp_success.css('display','block');
    }
}

function outputListen(o) {
    var output = 0;
    var flag = 1;
    for(var i = 0;i < o.length; i++){
        output += o[i];
    }
    if(output === 0){
        flag = 0;
        console.log('功率异常');
    } else {
        flag = 1;
    }
    if(flag === 0){
        $power_warning.css('display','block');
        $power_success.css('display','none');
    } else {
        $power_warning.css('display','none');
        $power_success.css('display','block');
    }
}

/*报警监听结束*/


/*动态加载六张图片的数据--开始*/
function previewPic() {
    $.ajax({
        type : 'post',
        dataType : 'json',
        url : doMainName + '/inverter/inverterIcon',
        success : function (data) {
            var result = data.data;
            $('#currentOutput').html(result.currentOutput + 'kW');
            $('#irradiance').html(result.irradiance + 'W/㎡');
            $('#temperature').html(result.temperature + '℃');
            $('#co2Reduction').html(result.co2Reduction + 't');
            $('#equivalentTree').html(result.equivalentTree.toFixed(4) + '棵');
            $('#totalIncome').html(result.totalIncome + '元');
        },
        error : function (err) {
            console.log(err);
        }
    })
}
/*动态加载六张图片的数据--结束*/