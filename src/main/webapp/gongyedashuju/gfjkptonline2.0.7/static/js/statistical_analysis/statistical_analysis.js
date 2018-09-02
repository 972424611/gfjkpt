/*组件的拼装--开始*/
gulpHtml('header-wrap','../model/gulp/header.html');
gulpHtml('section-left','../model/gulp/section-left.html',function () {
    $.getScript('../plugin/accordion/js/accordion.js');
});
/*组件的拼装--结束*/

/*树形菜单开始*/
var doMainName = 'http://47.106.101.133:8080/gfjkpt';
var chartTitle = '有功功率';
var chartTitle2 = '视在功率';
var nowDate = getNowFormatDate(new Date());
var dateType = 'day';
var $day_input = $('#day-input');
var $previous_day = $('.previous-day');
var $next_day = $('.next-day');
var $previous_month = $('#previous-month');
var $now_month = $('#now-month');
var $next_month = $('#next-month');
var $previous_year = $('#previous-year');
var $now_year = $('#now-year');
var $next_year = $('#next-year');

$day_input.val(nowDate);

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
    nowDate = addDate(nowDate,-1);
    $day_input.val(nowDate);
    showChart(nowDate,'day');
}

function nextDay() {//下一天
    nowDate = addDate(nowDate,1);
    $day_input.val(nowDate);
    showChart(nowDate,'day');
}

function previousMonth() {//上一月
    nowDate = getPreMonth(getNowFormatDate(nowDate)).substring(0,7);
    showChart(nowDate,'month');
}

function nowMonth() {//本月
    nowDate = getNowFormatDate(new Date()).substring(0,7);
    showChart(nowDate,'month');
}

function nextMonth() {//下一月
    nowDate = getNextMonth((getNowFormatDate(nowDate))).substring(0,7);
    showChart(nowDate,'month');
}


function previousYear() {
    nowDate = parseInt(nowDate);
    nowDate --;
    showChart(nowDate,'year');
}

function nowYear() {
    nowDate = getNowFormatDate(new Date()).substring(0,4);
    showChart(nowDate,'year');
}

function nextYear() {
    nowDate = parseInt(nowDate);
    nowDate ++;
    showChart(nowDate,'year');
}

var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            zTree.checkNode(treeNode, !treeNode.checked, true);
            showChart(nowDate,dateType);
        },
        onCheck: function () {
            showChart(nowDate,dateType);
        }
    },
    check: {
        enable : true,
        checkStyle : "checkbox",    //复选框
        checkboxType : {
            "Y" : "s",
            "N" : "ps"
        }
    }
};

var zNodes =[
    { id:1, pId:0, name:"综合教学楼", open: true,isParent: true,checked: true},
    { id:11, pId:1, name:"综合教学楼", checked: true},
    { id:2, pId:0, name:"工科楼", open: true,checked: true},
    { id:21, pId:2, name:"工科一号楼", checked: true},
    { id:22, pId:2, name:"工科二号楼", checked: true}
];
var zTree = $.fn.zTree.init($("#myTree"), setting, zNodes);

$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {//shown.bs.tab为tab选项卡高亮事件,
    //关键操作！！！！
    // 获取已激活的标签页的名称
    var activeTab = $(e.target)[0].hash;//hash 属性是一个可读可写的字符串，该字符串是 URL 的锚部分（从 # 号开始的部分）
    if(activeTab === '#thisDay'){
        nowDate = getNowFormatDate(new Date());
        dateType = 'day';
        showChart(nowDate,dateType);
    }else if(activeTab === "#thisMonth"){
        nowDate = getNowFormatDate(new Date()).substring(0,7);
        dateType = 'month';
        showChart(nowDate,dateType);
    }else if(activeTab === "#thisYear"){
        nowDate = getNowFormatDate(new Date()).substring(0,4);
        dateType = 'year';
        showChart(nowDate,dateType);
    }
});
var thisDayContainer = document.getElementById('thisDay-container');
var thisDownContainer = document.getElementById('down-container');
var option = {
    title : {
        text : chartTitle,
        left : 'center',
        textStyle:{
            fontSize:15
        }
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
        data : [],
        left : 20,
        bottom : 0
    },
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: [
        {
            name : '有功功率（wh）',
            type: 'value'
        }
    ],
    series: [],
    // color:['#88bbb6','#c2cc83', '#756454']
    // color: ['#944040',' #756454',' #88bbb6']
    // color: ['#a1c5bb', '#6f564f', '#dac262']
    // color: ['#7ec7b3', '#5b443d','#e7cb56']
    color: ['#a0221f', '#e7cb65', '#4e3e2e']
    // color: ['#49735b', '#e9ebc6', '#cbce97']
};

var option2 = {
    title : {
        text : chartTitle2,
        left : 'center',
        textStyle:{
            fontSize:15
        }
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
        data : [],
        left : 20,
        bottom : 0
    },
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: [
        {
            name : '视在功率（wh）',
            type: 'value'
        }
    ],
    series: []
};
/*树形菜单结束*/
showChart(nowDate,dateType);
function showChart(nowDate,dateType) {
    chartTitle = "有功功率-" + getNowChineseDate(nowDate);
    chartTitle2 = "视在功率-" + getNowChineseDate(nowDate);
    echarts.dispose(thisDayContainer);
    echarts.dispose(thisDownContainer);
    option.series = [];
    option2.series = [];
    var treeObj = $.fn.zTree.getZTreeObj('myTree');
    var nodes = treeObj.getCheckedNodes(true);
    var nodeStr = '';
    if(nodes.length === 1){
        nodeStr = nodes[0].name;
    } else{
        for(var i = 0; i < nodes.length; i++){
            if(!nodes[i].isParent){
                nodeStr += nodes[i].name + '-';
            }
        }
        if(nodeStr[nodeStr.length - 1] === '-'){
            nodeStr = nodeStr.slice(0,nodeStr.length-1);//去掉字符串最后一个汉字之后的“-”
        }
    }
    $.ajax({
        type: 'post',
        url: doMainName + '/load/contrastChart',
        dataType: 'json',
        data: {
            date: nowDate,
            type: dateType,
            locals: nodeStr
        },
        success: function (result) {
            var data = result.data;
            var timesArr = [];//二维数组存放每次拼接的一维数组
            for(var i = 0; i < data.length; i++){
                var local = data[i].local;
                var totalPowerArr = [];
                var apparentPower = [];
                var timeArr = [];
                var tmpObj = {};
                var tmpObj2 = {};
                for(var j = 0; j < data[i].list.length; j++){
                    timeArr.push(data[i].list[j].times);
                    totalPowerArr.push(data[i].list[j].activePower);
                    apparentPower.push(data[i].list[j].apparentPower);
                }
                timesArr.push(timeArr);
                tmpObj = {
                    name : local,
                    data: totalPowerArr,
                    type: 'bar',
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
                };
                tmpObj2 = {
                    name : local,
                    data: apparentPower,
                    type: 'bar',
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
                };
                option.series.push(tmpObj);
                option2.series.push(tmpObj2);
                option.legend.data.push(local);
                option2.legend.data.push(local);
            }
            var myChart = echarts.init(thisDayContainer);
            var myChart2 = echarts.init(thisDownContainer);
            option.xAxis.data = getMaxLengthArrFromArrs(timesArr);
            option2.xAxis.data = getMaxLengthArrFromArrs(timesArr);
            option.title.text = chartTitle;
            option2.title.text = chartTitle2;
            myChart.setOption(option);
            myChart2.setOption(option2);
            window.onresize = function () {
                myChart.resize();
                myChart2.resize();
            };
        },
        error: function (error) {
            throw Error(error);
        }
    })
}

function getMaxLengthArrFromArrs(a) {//挑选出二维数组中最长的数组
    var max = a[0].length;
    var tag = 0;
    for (var i = 1; i < a.length; i++) {
        if (max < a[i].length) {
            max = a[i].length;
            tag = i;
        }
    }
    return a[tag];
}

/*右侧负荷饼图部分--开始*/
var thisDayPieContainer = document.getElementById('thisDay-pie-container');
var thisMonthPieContainer = document.getElementById('thisMonth-pie-container');
var dayPieOption = new Pie();
var monthPieOption = new Pie();
function Pie() {
    return {
        title : {
            text: '',
            left: 'center',
            textStyle:{
                fontSize:15
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        // legend: {
        //     orient: 'vertical',
        //     x: 'left',
        //     data: []
        // },
        series: [
            {
                data: [],
                type: 'pie',
                // radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                // label: {
                //     normal: {
                //         show: false,
                //         position: 'center'
                //     }
                //     // emphasis: {
                //     //     show: true,
                //     //     textStyle: {
                //     //         fontSize: '15',
                //     //         fontWeight: 'bold'
                //     //     }
                //     // }
                // }
                label: {
                    normal: {
                        show: true,
                        position: 'outside',
                        formatter:"{b}"
                    }
                }
            }
        ]
    }
}
showPie();
function showPie() {
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: doMainName + '/load/pieChart',
        success: function (result) {
            var dayPieChart = echarts.init(thisDayPieContainer);
            var monthPieChart = echarts.init(thisMonthPieContainer);
            if(result.data[0].voList !== null && result.data[0].voList.length !== 0) {
                setPieOption('day',result.data[0].voList);
            }
            if(result.data[1].voList !== null && result.data[1].voList.length !== 0) {
                setPieOption('month',result.data[1].voList);
            }
            dayPieChart.setOption(dayPieOption);
            monthPieChart.setOption(monthPieOption);
            window.onresize = function () {
                dayPieChart.resize();
                monthPieChart.resize();
            };
        },
        error: function (error) {
            throw Error(error);
        }
    })
}
function setPieOption(dateType,data) {
    var arr = [];
    var localArr = [];
    for (var i = 0,len = data.length; i < len; i++){
        var tmpObj = {
            value: data[i].consume,
            name: data[i].local.substring(6)
        };
        localArr.push(data[i].local.substring(6));
        arr.push(tmpObj);
    }
    switch (dateType){
        case 'day': {
            dayPieOption.title.text = getNowChineseDate(nowDate) + '设备能耗';
            dayPieOption.series[0].data = arr;
            dayPieOption.color = ['#a1c5bb', '#6f564f', '#FF3030'];
            break;
        }
        case 'month':{
            var tmpDate = nowDate.substring(0,7);
            monthPieOption.title.text = getNowChineseDate(tmpDate) + '设备能耗';
            monthPieOption.series[0].data = arr;
            break;
        }
    }
}
/*右侧负荷饼图部分--结束*/