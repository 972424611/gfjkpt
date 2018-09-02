/*组件的拼装--开始*/
gulpHtml('header-wrap','../model/gulp/header.html');
gulpHtml('section-left','../model/gulp/section-left.html',function () {
    $.getScript('../plugin/accordion/js/accordion.js');
});
/*组件的拼装--结束*/

var doMainName = 'http://47.106.101.133:8080/gfjkpt';
var thisDayContainer = document.getElementById('thisDay-container');
var chartTitle = '';
var nowDate = getNowFormatDate(new Date());
var dateType = 'day';
var local = '工科一号楼';
var $day_input = $('#day-input');
var $previous_day = $('.previous-day');
var $next_day = $('.next-day');
var $previous_month = $('#previous-month');
var $now_month = $('#now-month');
var $next_month = $('#next-month');
var $previous_year = $('#previous-year');
var $now_year = $('#now-year');
var $next_year = $('#next-year');
/*树形菜单开始*/
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            if(treeNode.level === 0){
                return;
            }else {
                local = treeNode.name;
                showChart(nowDate,dateType,local);
            }
        }
    }
};

var zNodes =[
    { id:1, pId:0, name:"综合教学楼", isParent: true},
    { id:11, pId:1, name:"综合教学楼"},
    { id:2, pId:0, name:"工科楼", open: true,checked: true},
    { id:21, pId:2, name:"工科一号楼", checked: true},
    { id:22, pId:2, name:"工科二号楼"}
];
var zTree = $.fn.zTree.init($("#myTree"), setting, zNodes);
/*树形菜单结束*/

//获取图表容器


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
    showChart(nowDate,'day',local);
}
function nextDay() {//下一天
    nowDate = addDate(nowDate,1);
    $day_input.val(nowDate);
    showChart(nowDate,'day',local);
}
function previousMonth() {//上一月
    nowDate = getPreMonth(getNowFormatDate(nowDate)).substring(0,7);
    showChart(nowDate,'month',local);
}
function nowMonth() {//本月
    nowDate = getNowFormatDate(new Date()).substring(0,7);
    showChart(nowDate,'month',local);
}
function nextMonth() {//下一月
    nowDate = getNextMonth((getNowFormatDate(nowDate))).substring(0,7);
    showChart(nowDate,'month',local);
}
function previousYear() {
    nowDate = parseInt(nowDate);
    nowDate --;
    showChart(nowDate,'year',local);
}
function nowYear() {
    nowDate = getNowFormatDate(new Date()).substring(0,4);
    showChart(nowDate,'year',local);
}
function nextYear() {
    nowDate = parseInt(nowDate);
    nowDate ++;
    showChart(nowDate,'year',local);
}
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {//shown.bs.tab为tab选项卡高亮事件,
    //关键操作！！！！
    // 获取已激活的标签页的名称
    var activeTab = $(e.target)[0].hash;//hash 属性是一个可读可写的字符串，该字符串是 URL 的锚部分（从 # 号开始的部分）
    if(activeTab === '#thisDay'){
        nowDate = getNowFormatDate(new Date());
        dateType = 'day';
        showChart(nowDate,dateType,local);
    }else if(activeTab === "#thisMonth"){
        nowDate = getNowFormatDate(new Date()).substring(0,7);
        dateType = 'month';
        showChart(nowDate,dateType,local);
    }else if(activeTab === "#thisYear"){
        nowDate = getNowFormatDate(new Date()).substring(0,4);
        dateType = 'year';
        showChart(nowDate,dateType,local);
    }
});
var option = {
    title: {
        text: chartTitle,
        left: 'center'
    },
    tooltip: {
        show: true,
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
    legend: {
        data: ['有功功率（wh）','视在功率（wh）'],
        left: 20,
        bottom: 0
    },
    xAxis: {
        type: 'category',
        data: []
    },
    yAxis: [
        {
            name: '有功功率（wh）',
            type: 'value'
        }
    ],
    series: [{
        name: '有功功率（wh）',
        data: [],
        type: 'bar',
        smooth: true,
        yAxisIndex: 0,
        markPoint: {
            data: [{
                type: 'max',
                name: 'max'
            }, {
                type: 'min',
                name: 'min'
            }]
        },
        itemStyle: {
            color: '#2F4554'
        }
    }, {
        name: '视在功率（wh）',
        data: [],
        type: 'line',
        smooth: true,
        yAxisIndex: 0,
        markPoint: {
            data: [{
                type: 'max',
                name: 'max'
            }, {
                type: 'min',
                name: 'min'
            }]
        },
        itemStyle: {
            color: 'green'
        }
    }]
};
/*树形菜单结束*/
showChart(nowDate,dateType,local);
function showChart(nowDate,dateType,local) {
    echarts.dispose(thisDayContainer);
    $.ajax({
        type: 'post',
        url: doMainName + '/load/chart',
        dataType: 'json',
        data: {
            date: nowDate,
            type: dateType,
            local: local
        },
        success: function (result) {
            chartTitle = local + getNowChineseDate(nowDate) + "用电量监测";
            var data = result.data;
            var activePower = [];
            var times = [];
            var apparentPower = [];
            for(var i = 0; i < data.length; i++){
                activePower.push(data[i].activePower);
                apparentPower.push(data[i].apparentPower);
                times.push(data[i].times);
            }
            option.xAxis.data = times;
            option.series[0].data = activePower;
            option.series[1].data = apparentPower;
            option.title.text = chartTitle;
            var myChart = echarts.init(thisDayContainer);
            myChart.setOption(option);
            window.onresize = function () {
                myChart.resize();
            };
        },
        error: function (error) {
            throw Error(error);
        }
    });
    iconPreview();
}

// iconPreview();

function iconPreview() {//五个图片的数据加载
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: doMainName + '/load/icon',
        data: {
            local: local
        },
        success : function (data) {
            var result = data.data;
            $('#currentLoad').html(result.currentLoad + 'Wh');
            $('#ratio').html(result.ratio + '%');
            $('#percentage').html(result.percentage + '%');
            $('#maxMouthLoad').html(result.maxMouthLoad + 'Wh');
            $('#maxYearLoad').html(result.maxYearLoad + 'Wh');
        },
        error : function (err) {
            console.log('err : ',err);
        }
    })
}