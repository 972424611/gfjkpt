var map = new AMap.Map('amap-container',{
    resizeEnable : true,
    zoom : 10,
    center : [112.7550,27.9999]
});
AMap.plugin(['AMap.ToolBar','AMap.Scale'],function () {//工具插件
    map.addControl(new AMap.ToolBar());
    map.addControl(new AMap.Scale());
});
AMap.service('AMap.Geocoder',function () {
    geocoder = new AMap.Geocoder({
        city : "全国"
    });
});
var changsha = new AMap.Marker({
    icon : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
    position : [112.9388,28.2277]
});
var zhuzhou = new AMap.Marker({
    icon : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
    position : [113.1339,27.8276]
});
var xiangtan = new AMap.Marker({
    icon : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
    position : [113.157584,27.780445]
});
changsha.setMap(map);
zhuzhou.setMap(map);
xiangtan.setMap(map);
changsha.on('mouseover',function () {
    var info = [];
    info.push("<div><div><img style=\"float:left;\" src=\" http://webapi.amap.com/images/autonavi.png \"/></div> ");
    info.push("<div style=\"padding:0px 0px 0px 4px;\"><b onclick='toInfo()' style='cursor: pointer'>长沙理工大学云塘校区5kW项目</b>");
    info.push("电站规模：   5kW");
    info.push("当前功率：   null kW");
    info.push("今日发电：   0.53 kW");
    info.push("累计发电量： 0.18万kWh</div></div>" );
    var infoWindow = new AMap.InfoWindow({
        content:info.join("<br/>")
    });
    infoWindow.open(map,[112.9388,28.2277]);
});
zhuzhou.on('mouseover',function () {
    var info = [];
    info.push("<div><div><img style=\"float:left;\" src=\" http://webapi.amap.com/images/autonavi.png \"/></div> ");
    info.push("<div style=\"padding:0px 0px 0px 4px;\"><b onclick='toInfo()' style='cursor: pointer'>株洲市100kW项目</b>");
    info.push("电站规模：   100kW");
    info.push("当前功率：   null kW");
    info.push("今日发电：   0.00 kW");
    info.push("累计发电量： 0.75万kWh</div></div>" );
    var infoWindow = new AMap.InfoWindow({
        content:info.join("<br/>")
    });
    infoWindow.open(map,[113.1339,27.8276]);
});
xiangtan.on('mouseover',function () {
    var info = [];
    info.push("<div><div><img style=\"float:left;\" src=\" http://webapi.amap.com/images/autonavi.png \"/></div> ");
    info.push("<div style=\"padding:0px 0px 0px 4px;\"><b>车溪村60kW项目</b>");
    info.push("电站规模：   60kW");
    info.push("当前功率：   null kW");
    info.push("今日发电：   0.00 kW");
    info.push("累计发电量： null kWh</div></div>" );
    var infoWindow = new AMap.InfoWindow({
        content:info.join("<br/>")
    });
    infoWindow.open(map,[113.157584,27.780445]);
});

function toInfo() {
    location.href = './inverter_info.html';
}