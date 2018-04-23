


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
        toDayChart(nowDay);
    }else if(activeTab === "#thisMonth"){
        nowDay = getNowFormatDate(new Date());
        var month = nowDay.substring(0,7);
        toMonthChart(month);
    }else if(activeTab === "#thisQuarter"){
        nowDay = getNowFormatDate(new Date());
        loadChart(thisQuarter,optionQuarter);
    }else if(activeTab === "#thisYear"){
        nowDay = getNowFormatDate(new Date());
        var year = nowDay.substring(0,4);
        console.log('year',year)
        toYearChart(year);
    }else if(activeTab === "#thisCustom"){
        nowDay = getNowFormatDate(new Date());
        loadChart(thisCustom,optionCustom);
    }
});

optionQuarter = {
    title : {
        text : '长沙理工大学2018年4月14日-2018年4月15日用电监测',
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
        data : ['长沙理工大学','温度'],
        left : 20
    },
    xAxis: {
        type: 'category',
        data: ['二月','三月','四月']
    },
    yAxis: [
        {
            name : '电（kWh）',
            type: 'value',
            max : 200,
            min : 0,
            interval : 50
        },
        {
            name : '温度（℃）',
            type: 'value',
            max : 45,
            min : -10,
            interval : 10
        }
    ],
    series: [
        {
            name : '长沙理工大学',
            data: [150, 161.2, 183.3],
            type: 'line',
            smooth: true,
            markPoint : {
                data : [ {
                    type : 'max',
                    name : 'max'
                }, {
                    type : 'min',
                    name : 'min'
                } ]
            }
        },
        {
            name : '温度',
            data: [20.3, 22.5, 21.3],
            type: 'bar',
            smooth: true
        }
    ]
};
optionCustom = {
    title : {
        text : '长沙理工大学2018年4月14日-2018年4月15日用电监测',
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
        data : ['长沙理工大学','温度'],
        left : 20
    },
    xAxis: {
        type: 'category',
        data: ['1:00', '2:00', '3:00', '4:00', '5:00', '6:00', '7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00']
    },
    yAxis: [
        {
            name : '电（kWh）',
            type: 'value',
            max : 110,
            min : 0,
            interval : 20
        },
        {
            name : '温度（℃）',
            type: 'value',
            max : 45,
            min : -10,
            interval : 10
        }
    ],
    series: [
        {
            name : '长沙理工大学',
            data: [20, 35, 41, 24, 40, 30, 28, 26, 34, 33, 21, 40, 27, 31],
            type: 'line',
            smooth: true,
            markPoint : {
                data : [ {
                    type : 'max',
                    name : 'max'
                }, {
                    type : 'min',
                    name : 'min'
                } ]
            }
        },
        {
            name : '温度',
            data: [27.1, 27.5, 27.3, 24, 25.6, 25.3, 26.1, 26.6, 25.8, 24.8, 25.6, 26.2, 27.0, 28.0],
            type: 'bar',
            smooth: true
        }
    ]
};


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
        type : 'date'
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

toDayChart(nowDay);//进入页面默认加载当前天的数据

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
    toDayChart(nowDay);
}

function nextDay() {//下一天
    nowDay = addDate(nowDay,1);
    $day_input.val(nowDay);
    toDayChart(nowDay);
}

function previousMonth() {//上一月
    nowDay = getPreMonth(getNowFormatDate(nowDay));
    var month = nowDay.substring(0,7);
    toMonthChart(month);

}

function nowMonth() {//本月
    nowDay = getNowFormatDate(new Date());
    var month = nowDay.substring(0,7);
    toMonthChart(month);
}

function nextMonth() {//下一月
    nowDay = getNextMonth((getNowFormatDate(nowDay)));
    var month = nowDay.substring(0,7);
    toMonthChart(month);
}

function previousQaurter() {
    
}

function nowQuarter() {
    
}

function nextQuarter() {
    
}

function previousYear() {
    year = parseInt(year);
    year --;
    console.log(year);
    toYearChart(year);
}

function nowYear() {
    var nowDay = getNowFormatDate(new Date());
    year = nowDay.substring(0,4);
    toYearChart(year);
}

function nextYear() {
    year = parseInt(year);
    year ++;
    toYearChart(year);
}

function toDayChart(nowDay) {//加载对应日期的表格
    $.ajax({
        type : 'get',
        url : 'http://localhost:8080/inverter/chart?name=inverter1&type=day&date=' + nowDay,
        dataType : 'json',
        success : function (jsonResult) {

            /*ajax请求成功时的操作--开始*/
            var totalActivePower = pushData(jsonResult,'totalActivePower');
            var tansTemp1 = pushData(jsonResult,'tansTemp1');
            var tansTemp2 = pushData(jsonResult,'tansTemp2');
            var times = pushData(jsonResult,'times');
            var optionDay = {
                title : {
                    text : '长沙理工大学2018年4月14日-2018年4月15日用电监测',
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
                    data : ['长沙理工大学','模块一温度','模块二温度'],
                    left : 20,
                    bottom : 0
                },
                xAxis: {
                    type: 'category',
                    data: times,
                    axisLabel : {
                        interval : 0
                    }
                },
                yAxis: [
                    {
                        name : '电（kWh）',
                        type: 'value',
                        max : 360,
                        min : 0,
                        interval : 40
                    },
                    {
                        name : '温度（℃）',
                        type: 'value',
                        max : 100,
                        min : -10,
                        interval : 10
                    }
                ],
                series: [
                    {
                        name : '长沙理工大学',
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
                        name : '模块一温度',
                        data: tansTemp1,
                        type: 'line',
                        smooth: true,
                        yAxisIndex : 1,//规定使用的坐标
                        itemStyle : {
                            color : '#C23531'
                        }
                    },
                    {
                        name : '模块二温度',
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
            loadChart(thisDay,optionDay);
            /*ajax请求成功时的操作--结束*/
        },
        error : function (err) {
            console.log(err);
        }

    })

}

function toMonthChart(now) {
    $.ajax({
        type : 'get',
        url : 'http://localhost:8080/inverter/chart?name=inverter1&type=month&date=' + now,
        dataType : 'json',
        success : function (jsonResult) {
            var totalActivePower = pushData(jsonResult,'totalActivePower');
            var tansTemp1 = pushData(jsonResult,'tansTemp1');
            var tansTemp2 = pushData(jsonResult,'tansTemp2');
            var times = pushData(jsonResult,'times');
            var optionMonth = {
                title : {
                    text : '长沙理工大学2018年4月14日-2018年4月15日用电监测',
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
                    data : ['长沙理工大学','模块一温度','模块二温度'],
                    left : 20,
                    bottom : 0
                },
                xAxis: {
                    type: 'category',
                    data: times
                },
                yAxis: [
                    {
                        name : '电（kWh）',
                        type: 'value',
                        max : 360,
                        min : 0,
                        interval : 40
                    },
                    {
                        name : '温度（℃）',
                        type: 'value',
                        max : 100,
                        min : -10,
                        interval : 10
                    }
                ],
                series: [
                    {
                        name : '长沙理工大学',
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
                        name : '模块一温度',
                        data: tansTemp1,
                        type: 'line',
                        smooth: true,
                        yAxisIndex : 1,//规定使用的坐标
                        itemStyle : {
                            color : '#C23531'
                        }
                    },
                    {
                        name : '模块二温度',
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
            loadChart(thisMonth,optionMonth);
        },
        error : function (err) {
            console.log(err);
        }

    })

}

function toQuarterChart(now) {
    $.ajax({
        type : 'get',
        url : 'http://localhost:8080/inverter/chart?name=inverter1&type=quarter&date=' + now,
        dataType : 'json',
        success : function (jsonResult) {
            var totalActivePower = pushData(jsonResult,'totalActivePower');
            var tansTemp1 = pushData(jsonResult,'tansTemp1');
            var tansTemp2 = pushData(jsonResult,'tansTemp2');
            var times = pushData(jsonResult,'times');
            var optionYear = {
                title : {
                    text : '长沙理工大学2018年4月14日-2018年4月15日用电监测',
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
                    data : ['长沙理工大学','模块一温度','模块二温度'],
                    left : 20,
                    bottom : 0
                },
                xAxis: {
                    type: 'category',
                    data: times
                },
                yAxis: [
                    {
                        name : '电（kWh）',
                        type: 'value',
                        max : 360,
                        min : 0,
                        interval : 40
                    },
                    {
                        name : '温度（℃）',
                        type: 'value',
                        max : 100,
                        min : -10,
                        interval : 10
                    }
                ],
                series: [
                    {
                        name : '长沙理工大学',
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
                        name : '模块一温度',
                        data: tansTemp1,
                        type: 'line',
                        smooth: true,
                        yAxisIndex : 1,//规定使用的坐标
                        itemStyle : {
                            color : '#C23531'
                        }
                    },
                    {
                        name : '模块二温度',
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
            loadChart(thisYear,optionYear);
        },
        error : function (err) {
            console.log(err);
        }

    })
}

function toYearChart(now) {
    $.ajax({
        type : 'get',
        url : 'http://localhost:8080/inverter/chart?name=inverter1&type=year&date=' + now,
        dataType : 'json',
        success : function (jsonResult) {
            var totalActivePower = pushData(jsonResult,'totalActivePower');
            var tansTemp1 = pushData(jsonResult,'tansTemp1');
            var tansTemp2 = pushData(jsonResult,'tansTemp2');
            var times = pushData(jsonResult,'times');
            var optionYear = {
                title : {
                    text : '长沙理工大学2018年4月14日-2018年4月15日用电监测',
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
                    data : ['长沙理工大学','模块一温度','模块二温度'],
                    left : 20,
                    bottom : 0
                },
                xAxis: {
                    type: 'category',
                    data: times
                },
                yAxis: [
                    {
                        name : '电（kWh）',
                        type: 'value',
                        max : 360,
                        min : 0,
                        interval : 40
                    },
                    {
                        name : '温度（℃）',
                        type: 'value',
                        max : 100,
                        min : -10,
                        interval : 10
                    }
                ],
                series: [
                    {
                        name : '长沙理工大学',
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
                        name : '模块一温度',
                        data: tansTemp1,
                        type: 'line',
                        smooth: true,
                        yAxisIndex : 1,//规定使用的坐标
                        itemStyle : {
                            color : '#C23531'
                        }
                    },
                    {
                        name : '模块二温度',
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
            loadChart(thisYear,optionYear);
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

