
if($.cookie('role') == null || $.cookie('role') === undefined) {
    window.location.href = "./login.html";
}

$(window).bind('scroll',function () {
    var top = $(this).scrollTop();
    if(top >= 50){
        $('.section-left').css('top',0);
    } else {
        $('.section-left').css('top',50);
    }
});


function toPowerPreview() {
    location.href = './power_preview.html';
}
function toInverterInfo() {
    location.href = './inverter_info.html';
}
function toInverterHistory() {
    location.href = './inverter_history.html';
}
function toEnergyUseMonitoring() {
    location.href = './energy_use_monitoring.html';
}
function toStatisticalAnalysis() {
    location.href = './statistical_analysis.html';
}
function toBuyer() {
    location.href = './buyer.html';
}
function toSeller() {
    location.href = './seller.html';
}
function toAccountManage(){
    if($.cookie('role') == "admin") {
        location.href = './account_manage.html';
    }
}
function toDeviceManagement(){
    //location.href = './device_management.html';
}
function toPredictionAnalysis(){
    location.href = './prediction_analysis.html';
}

function logout() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if(keys) {
        for(var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
    }
    //返回登录页面
    window.location.href = "./login.html";
}
