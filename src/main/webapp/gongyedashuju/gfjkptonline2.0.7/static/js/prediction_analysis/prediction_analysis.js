var domainName = 'http://47.106.101.133:8080/gfjkpt';
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
        onClick: function (event, treeId, treeNode) {
            console.log(treeId, treeNode);
            if(treeNode.level === 0){
                return 0;
            }else {//选取子节点
                showChart();
            }
        }
    }
};

var zNodes =[
    { id: 1, pId: 0, name: "综合教学楼", isParent: true},
    { id: 11, pId: 1, name: "综合教学楼"},
    { id: 2, pId: 0, name: "工科楼", open: true},
    { id: 21, pId: 2, name: "工科一号楼"},
    { id: 22, pId: 2, name:" 工科二号楼"}
];
$.fn.zTree.init($("#myTree"), setting, zNodes);
/*树形菜单结束*/

/*图表部分--开始*/
function Option() {//图表设置构造函数
    return {
        title: {
            text: '',
            textStyle: {
                fontWeight: 800,
                fontFamily: 'Courier New'
            },
            left: 'center',//标题居中
            padding: 10
        },
        legend: {
            data: [{name: '实际发电量'},{name: '预测发电量'},{name: '实际收入'},{name: '预测收入'}],
            bottom: 0
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: {
            name: '时间',
            data: []
        },
        yAxis: [{
            name: '功率（kWh）'
        },{
            name: '收入（元）'
        }],
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
        series: [{
            name: '实际发电量',
            type: 'bar',
            data: [],
            markPoint : {
                data : [ {
                    type : 'max',
                    name : 'max'
                }, {
                    type : 'min',
                    name : 'min'
                } ]
            }
        },{
            name: '预测发电量',
            type: 'bar',
            data: [],
            markPoint : {
                data : [ {
                    type : 'max',
                    name : 'max'
                }, {
                    type : 'min',
                    name : 'min'
                } ]
            }
        },{
            name: '实际收入',
            type: 'line',
            data: [],
            yAxisIndex: 1,
            markPoint : {
                data : [ {
                    type : 'max',
                    name : 'max'
                }, {
                    type : 'min',
                    name : 'min'
                } ]
            }
        },{
            name: '预测收入',
            type: 'line',
            data: [],
            yAxisIndex: 1,
            markPoint : {
                data : [ {
                    type : 'max',
                    name : 'max'
                }, {
                    type : 'min',
                    name : 'min'
                } ]
            }
        }]
    }
}
var dayChartOption = new Option();
var monthChartOption = new Option();
var $day_chart = $('#day-chart')[0];
var $month_chart = $('#month-chart')[0];
showChart();
function showChart() {//加载图表并显示
    var nowDate = getNowFormatDate(new Date());
    var dateObj = {
        dayDate: nowDate,
        monthDate: nowDate.substring(0,7)
    };
    $.ajax({
        type: 'post',
        url: domainName + '/inverter/predict',
        dataType: 'json',
        data: dateObj,
        success: function (result) {
            writeOption(result.data.voDayList,'day',dateObj);
            writeOption(result.data.voMonthList,'month',dateObj);
            echarts.dispose($day_chart);
            echarts.dispose($month_chart);
            var dayChart = echarts.init($day_chart);
            var monthChart = echarts.init($month_chart);
            dayChart.setOption(dayChartOption,true);
            monthChart.setOption(monthChartOption,true);
            window.onresize = function () {
                dayChart.resize();
                monthChart.resize();
            }
        },
        error: function (error) {
            throw Error(error);
        }
    });
}
function writeOption(data,dateType,dateObj) {//设置echarts图表的option
    var currentOutputArr = pushData(data,'currentOutput');
    var predictOutputArr = pushData(data,'predictOutput');
    var currentIncome = pushData(data,'currentIncome');
    var predictIncome = pushData(data,'predictIncome');
    var timeArr = pushData(data,'times');
    switch (dateType){
        case 'day': {
            dayChartOption.xAxis.data = timeArr;
            dayChartOption.series[0].data = currentOutputArr;
            dayChartOption.series[1].data = predictOutputArr;
            dayChartOption.series[2].data = currentIncome;
            dayChartOption.series[3].data = predictIncome;
            dayChartOption.title.text = getNowChineseDate(dateObj.dayDate) + ' 日发电及收入预测'
            break;
        }
        case 'month': {
            monthChartOption.xAxis.data = timeArr;
            monthChartOption.series[0].data = currentOutputArr;
            monthChartOption.series[1].data = predictOutputArr;
            monthChartOption.series[2].data = currentIncome;
            monthChartOption.series[3].data = predictIncome;
            monthChartOption.title.text = getNowChineseDate(dateObj.monthDate) + ' 月发电及收入预测';
            break;
        }
    }
}
function pushData(data,detailDataName) {//遍历数据进行拼装
    var arr = [];
    for(var i = 0,len = data.length; i < len; i++){
        arr.push(data[i]['' + detailDataName]);
    }
    return arr;
}
/*图表部分--结束*/