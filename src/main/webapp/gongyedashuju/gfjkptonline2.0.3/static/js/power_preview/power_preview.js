var doMainName = 'http://localhost:8080';
var username = $.cookie('username');

var $powerstation_container= $('.powerstation-info-container');

/*在地图和左边加载电站信息--开始*/
$.ajax({
    type : 'post',
    dataType : 'json',
    url : doMainName + '/preview/list',
    success : function (data) {
        var result = data.data;
        constructPowerLists(result);
    },
    error : function (err) {

    }
});

function constructPowerLists(result) {//在地图左侧动态拼装图片和表格
    for(var i = 0;i < result.length; i++){
        constructPowerStationInfo(result[i]);
    }
}

function constructPowerStationInfo(data) {
    var imgSrc = '../static/img/comment/' + data.name  + '.jpg';
    if(data.name === "5KW photovoltaic power project.Jpg of Changsha University of Science and Technology") {
        data.name = "长沙理工大学5kW光伏发电项目";
    } else if(data.name === "60KW photovoltaic power project in the car Creek Village") {
        data.name = "车溪村60kW光伏发电项目";
    } else if(data.name === "Zhuzhou 100KW photovoltaic power project") {
        data.name = "株洲市100kW光伏发电项目";
    }
    //var imgSrc = '../static/img/comment/cxc.png';
    $powerstation_container.append('<div class="powerstation-info">\n' +
        '                                <div class="pic-container" onclick="toInfo()">\n' +
        '                                    <img src="' + imgSrc + '" alt="长沙理工大学5kw项目">\n' +
        '                                </div>\n' +
        '                                <table class="powerstation-table">\n' +
        '                                    <tr>\n' +
        '                                        <td colspan="4">' + data.name + '</td>\n' +
        '                                    </tr>\n' +
        '                                    <tr>\n' +
        '                                        <td colspan="2">电站规模：</td>\n' +
        '                                        <td colspan="2">'+ data.scale + '</td>\n' +
        '                                    </tr>\n' +
        '                                    <tr>\n' +
        '                                        <td colspan="2">当前功率：</td>\n' +
        '                                        <td colspan="2">'+ (data.currentPower/1000).toFixed(2) + 'kWh' + '</td>\n' +
        '                                    </tr>\n' +
        '                                    <tr>\n' +
        '                                        <td colspan="2">今日发电：</td>\n' +
        '                                        <td colspan="2">'+ (data.dailyOutput/1000).toFixed(2) +  'kWh' + '</td>\n' +
        '                                    </tr>\n' +
        '                                    <tr>\n' +
        '                                        <td colspan="2">累计发电量：</td>\n' +
        '                                        <td colspan="2">' + (data.totalOutput/1000).toFixed(2) + 'kWh' + '</td>\n' +
        '                                    </tr>\n' +
        '                                    <tr>\n' +
        '                                        <td>更新时间</td>\n' +
        '                                        <td colspan="3">' + formatDateTime(data.updateTime) + '</td>\n' +
        '                                    </tr>\n' +
        '                                </table>\n' +
        '                            </div>');
}

/*在地图和左边加载电站信息--结束*/

function formatDateTime(inputTime) {//时间戳转yyyy-mm-dd hh-mm-ss
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
}
