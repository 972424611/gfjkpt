/*组件的拼装--开始*/
gulpHtml('header-wrap','../model/gulp/header.html');
gulpHtml('section-left','../model/gulp/section-left.html',function () {
    $.getScript('../plugin/accordion/js/accordion.js');
});
/*组件的拼装--结束*/

/*树形菜单开始*/
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event,menuTree,treeNode) {
            nowDay = getNowFormatDate(new Date());
            toChart(nowDay,option);
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
var thisDay = document.getElementById('thisDay-container');
var thisDayRight = document.getElementById('thisDay-container-right');
var thisDayLeft = document.getElementById('thisDay-container-left');
var thisMonth = document.getElementById('thisMonth-container');
var thisYear = document.getElementById('thisYear-container');

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
    }else if(activeTab === "#thisYear"){
        nowDay = getNowFormatDate(new Date());
        var year = nowDay.substring(0,4);
        toChart(year,'year');
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
var doMainName = 'http://47.106.101.133:8080';
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

// toChart(nowDay,'day');//进入页面默认加载当前天的数据

$day_input.val(nowDay);

// $previous_day.on('click',function () {
//     previousDay();
// });
// $next_day.on('click',function () {
//     nextDay();
// });
// $previous_month.on('click',function () {
//     previousMonth();
// });
// $now_month.on('click',function () {
//     nowMonth();
// });
// $next_month.on('click',function () {
//     nextMonth();
// });
// $previous_year.on('click',function () {
//     previousYear();
// });
// $now_year.on('click',function () {
//     nowYear();
// });
// $next_year.on('click',function () {
//     nextYear();
// });

// function previousDay() {//上一天
//     nowDay = addDate(nowDay,-1);
//     $day_input.val(nowDay);
//     toChart(nowDay,'day');
// }
//
// function nextDay() {//下一天
//     nowDay = addDate(nowDay,1);
//     $day_input.val(nowDay);
//     toChart(nowDay,'day');
// }
//
// function previousMonth() {//上一月
//     nowDay = getPreMonth(getNowFormatDate(nowDay));
//     var month = nowDay.substring(0,7);
//     toChart(month,'month');
// }
//
// function nowMonth() {//本月
//     nowDay = getNowFormatDate(new Date());
//     var month = nowDay.substring(0,7);
//     toChart(month,'month');
// }
//
// function nextMonth() {//下一月
//     nowDay = getNextMonth((getNowFormatDate(nowDay)));
//     var month = nowDay.substring(0,7);
//     toChart(month,'month');
// }
//
//
// function previousYear() {
//     year = parseInt(year);
//     year --;
//     console.log(year);
//     toChart(year,'year');
// }
//
// function nowYear() {
//     var nowDay = getNowFormatDate(new Date());
//     year = nowDay.substring(0,4);
//     toChart(year,'year');
// }
//
// function nextYear() {
//     year = parseInt(year);
//     year ++;
//     toChart(year,'year');
// }
var obj = {
    data : [
        {time : "0 : 00",lux : 0},
        {time : "2 : 00",lux : 0},
        {time : "4 : 00",lux : 0},
        {time : "6 : 00",lux : 45},
        {time : "8 : 00",lux : 110},
        {time : "10 : 00",lux : 150},
        {time : "12 : 00",lux : 260},
        {time : "14 : 00",lux : 265},
        {time : "16 : 00",lux : 255},
        {time : "18 : 00",lux : 108},
        {time : "20 : 00",lux : 0},
        {time : "22 : 00",lux : 0}

    ]
};
console.log(pushData(obj,'lux'));
console.log(pushData(obj,'time'));
var a = pushData(obj,'lux');
var b = pushData(obj,'time');

toChart(nowDay,'day');
toRightChart('day');
function toRightChart(dateType) {
        var option = {
            title : {
                text : '长沙理工大学2018年5月17日光照强度监测',
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
                data : ['光照强度'],
                left : 20,
                bottom : 0
            },
            xAxis: {
                type: 'category',
                data: b
            },
            yAxis: [
                {
                    name : '光照强度（lx）',
                    type: 'value'
                }
            ],
            series: [
                {
                    name : '光照强度',
                    data: a,
                    type: 'line',
                    smooth: true,
                    yAxisIndex : 0,//规定使用的坐标
                    itemStyle : {
                        color : '#C23531'
                    }
                }
            ]
        };
        if(dateType === 'day'){
            loadChart(thisDayRight,option);
        } else if(dateType === 'month'){
            loadChart(thisMonth,option);
        } else if(dateType === 'year'){
            loadChart(thisYear,option);
        }
}


function toChart(date,dateType) {
    $.ajax({
        type : 'get',
        url : doMainName + '/load/chart?name=inverter1&type=' + dateType + '&date=' + date,
        dataType : 'json',
        success : function (jsonResult) {
            var totalActivePower = pushData(jsonResult,'totalActivePower');
            var tansTemp1 = pushData(jsonResult,'tansTemp1');
            var tansTemp2 = pushData(jsonResult,'tansTemp2');
            var times = pushData(jsonResult,'times');
            var titleDateLeft;
            var titleDateRight;
            var chartTitle = '';
            if(dateType === 'day'){
                titleDateLeft = getNowChineseDate(date);
                titleDateRight = getNowChineseDate(addDate(date,+1));
                chartTitle = '长沙理工大学' + titleDateLeft + '至' + titleDateRight + '功率监测';
            } else if(dateType === 'month'){
                titleDateLeft = getNowChineseDate(date);
                titleDateRight = getNowChineseDate(getNextMonth(date));
                chartTitle = '长沙理工大学' + titleDateLeft + '功率监测';
            } else if(dateType === 'quarter'){

            } else if(dateType === 'year'){
                chartTitle = '长沙理工大学' + getNowChineseDate(date)  + '功率监测';
            }
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
                    data : ['模块一温度','模块二温度'],
                    left : 20,
                    bottom : 0
                },
                xAxis: {
                    type: 'category',
                    data: times
                },
                yAxis: [
                    {
                        name : '温度（℃）',
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name : '模块一温度',
                        data: tansTemp1,
                        type: 'line',
                        smooth: true,
                        yAxisIndex : 0,//规定使用的坐标
                        itemStyle : {
                            color : '#C23531'
                        }
                    },
                    {
                        name : '模块二温度',
                        data: tansTemp2,
                        type: 'line',
                        smooth: true,
                        yAxisIndex : 0,
                        itemStyle : {
                            color : '#008000'
                        }
                    }
                ]
            };
            if(dateType === 'day'){
                loadChart(thisDayLeft,option);
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