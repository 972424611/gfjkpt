/*组件的拼装--开始*/
gulpHtml('header-wrap','../model/gulp/header.html');
gulpHtml('section-left','../model/gulp/section-left.html',function () {
    $.getScript('../plugin/accordion/js/accordion.js');
});
/*组件的拼装--结束*/


var doMainName = 'http://47.106.101.133:8080/gfjkpt';
var thisDay = document.getElementById('this-container');//获取图表容器
var inverter_name = 'inverter1';
var field = '日发电量';
var dateType = 'day';
var nowDate = "2018-07-09";
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
            console.log(treeId, treeNode);
            if(treeNode.level === 0){
                return;
            }else {
                showChart(nowDate,dateType,inverter_name,field);
                console.log('2');
            }
        }
    }
};

var zNodes =[
    { id:1, pId:0, name:"综合教学楼", isParent : true},
    { id:11, pId:1, name:"综合教学楼"},
    { id:2, pId:0, name:"工科楼"},
    { id:21, pId:2, name:"工科一号楼"},
    { id:22, pId:2, name:"工科二号楼"}
];
$.fn.zTree.init($("#myTree"), setting, zNodes);
/*树形菜单结束*/


$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {//shown.bs.tab为tab选项卡高亮事件,
    //关键操作！！！！
    // 获取已激活的标签页的名称
    var activeTab = $(e.target)[0].hash;//hash 属性是一个可读可写的字符串，该字符串是 URL 的锚部分（从 # 号开始的部分）
    if(activeTab === '#thisDay'){
        nowDate = "2018-07-09";
        $day_input.val(getNowFormatDate(nowDate));
        dateType = 'day';
        showFieldSelect(dateType);
        showChart(nowDate,dateType,inverter_name,field);
    }else if(activeTab === "#thisMonth"){
        nowDate = getNowFormatDate(new Date()).substring(0,7);
        dateType = 'month';
        showFieldSelect(dateType);
        showChart(nowDate,dateType,inverter_name,field);
    }else if(activeTab === "#thisYear"){
        nowDate = getNowFormatDate(new Date()).substring(0,4);
        dateType = 'year';
        showFieldSelect(dateType);
        showChart(nowDate,dateType,inverter_name,field);
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
            nowDate = value;
            showChart(nowDate,dateType,inverter_name,field);
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
showFieldSelect(dateType);
showChart(nowDate,'day',inverter_name,field);//进入页面默认加载当前天的数据
previewPic();
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
    showChart(nowDate,'day',inverter_name,field);
}

function nextDay() {//下一天
    nowDate = addDate(nowDate,1);
    $day_input.val(nowDate);
    showChart(nowDate,'day',inverter_name,field);
}

function previousMonth() {//上一月
    nowDate = getPreMonth(getNowFormatDate(nowDate)).substring(0,7);
    showChart(nowDate,'month',inverter_name,field);
}

function nowMonth() {//本月
    nowDate = getNowFormatDate(new Date()).substring(0,7);
    showChart(nowDate,'month',inverter_name,field);
}

function nextMonth() {//下一月
    nowDate = getNextMonth((getNowFormatDate(nowDate))).substring(0,7);
    showChart(nowDate,'month',inverter_name,field);
}


function previousYear() {
    nowDate = parseInt(nowDate);
    nowDate --;
    showChart(nowDate,'year',inverter_name,field);
}

function nowYear() {
    nowDate = getNowFormatDate(new Date()).substring(0,4);
    showChart(nowDate,'year',inverter_name,field);
}

function nextYear() {
    nowDate = parseInt(nowDate);
    nowDate ++;
    showChart(nowDate,'year',inverter_name,field);
}

function showChart(date,dateType,name,field) {
    $.ajax({
        type: 'post',
        url: doMainName + '/inverter/chart',
        dataType: 'json',
        data: {
            date: date,
            type: dateType,
            name: name,
            field: field
        },
        success: function (jsonResult) {
            var totalActivePower = pushData(jsonResult,'field');
            var tansTemp1 = pushData(jsonResult,'tansTemp1');
            var tansTemp2 = pushData(jsonResult,'tansTemp2');
            var times = pushData(jsonResult,'times');
            var titleDateLeft;
            var titleDateRight;
            var chartTitle = '';
            if(dateType === 'day'){
                titleDateLeft = getNowChineseDate(date);
                titleDateRight = getNowChineseDate(addDate(date,-1));
                chartTitle = titleDateRight + "至" + titleDateLeft + "-" + field + '监测';
            }else if(dateType === 'month'){
                titleDateLeft = getNowChineseDate(date);
                var a = date.split("-");
                a[1] = parseInt(a[1]) - 1;
                if(parseInt(a[1]) < 10) {
                    a[1] = "0" + a[1];
                }
                date = a[0] + "-" + a[1];
                titleDateRight = getNowChineseDate(date);
                chartTitle = titleDateRight + "至" + titleDateLeft + "-" + field + '监测';
            }else if(dateType === 'year'){
                chartTitle = getNowChineseDate(date) +  "-" + field + '监测';
            }
            temperListen(tansTemp1);
            temperListen(tansTemp2);
            outputListen(totalActivePower);
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
                    },
                    {
                        name : '温度（℃）',
                        type: 'value'
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
            loadChart(thisDay,option);
        },
        error : function (err) {
            throw Error(err);
        }

    })
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
        url : doMainName + '/inverter/icon',
        success : function (data) {
            var result = data.data;
            $('#currentOutput').html(result.currentOutput + 'W');
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

/*显示下拉菜单--开始*/
function showFieldSelect(dateType) {
    var optionStr = [];
    var optionValueArr = [];
    if(dateType === 'day'){
        optionValueArr = ['日发电量','总发电量','总有功功率'];
    }else if(dateType === 'month' || dateType === 'year') {
        optionValueArr = ['总发电量', '总有功功率'];
    }else {
        throw Error('dateType is error!');//日期类型不正确时抛出异常
    }
    for(var i = 0; i < optionValueArr.length; i++){
        optionStr = optionStr + '<option value="'+ optionValueArr[i] + '">' + optionValueArr[i] + '</option>'
    }
    $('.field-select').html('<label for="field-select">数据选择：</label>\n' +
        '                                    <select name="" id="field-select" onchange="listenSelectChang($(this))">\n' + optionStr +
        '                                    </select>');
}
/*显示下拉菜单--结束*/
/*监听下拉菜单的值的变化并进行相应操作--开始*/
function listenSelectChang(obj) {
    field = obj.children('option:selected').val();
    showChart(nowDate,dateType,inverter_name,field);
}
/*监听下拉菜单的值的变化并进行相应操作--结束*/