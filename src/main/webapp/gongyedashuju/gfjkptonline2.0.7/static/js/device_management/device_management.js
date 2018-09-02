var domainName = 'http://47.106.101.133:8080/gfjkpt';
var $table_body = $('#table-body');
/*模态框中表格内容获取--开始*/
var $type = $('#type');
var $number = $('#number');
var $name = $('#name');
var $model = $('#model');
var $status = $('#status');
var $factoryNumber = $('#factoryNumber');
var $current = $('#current');
var $voltage = $('#voltage');
var $frequency = $('#frequency');
var $ratedPower = $('#ratedPower');
var $price = $('#price');
var $ipAddress = $('#ipAddress');
var $installationPosition = $('#installationPosition');
var $managementUnit = $('#managementUnit');
var $manufacturer = $('#manufacturer');
var $manufactureDate = $('#manufactureDate');
var $serviceLife = $('#serviceLife');
var $purchaseDate = $('#purchaseDate');
function DeviceObj() {
    return {//保存设备信息的对象
        type: $type.val(),
        number: $number.val(),
        name: $name.val(),
        model: $model.val(),
        status: $status.val(),
        factoryNumber: $factoryNumber.val(),
        current: $current.val(),
        voltage: $voltage.val(),
        frequency: $frequency.val(),
        ratedPower: $ratedPower.val(),
        price: $price.val(),
        ipAddress: $ipAddress.val(),
        installationPosition: $installationPosition.val(),
        managementUnit: $managementUnit.val(),
        manufacturer: $manufacturer.val(),
        manufactureDate: $manufactureDate.val(),
        serviceLife: $serviceLife.val(),
        purchaseDate: $purchaseDate.val()
    }
}
/*模态框中表格内容获取--结束*/

/*组件的拼装--开始*/
gulpHtml('header-wrap','../model/gulp/header.html');
gulpHtml('section-left','../model/gulp/section-left.html',function () {
    $.getScript('../plugin/accordion/js/accordion.js');
});
/*组件的拼装--结束*/

/*获取设备信息--开始*/
getDeviceInfo();
function getDeviceInfo() {
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: domainName + '/equipment/list',
        success: function (result) {
            constructTable(result.data);
        },
        error: function (error) {
            console.log(error)
        }
    });
}
function constructTr(data) {
    $table_body.append('<tr>\n' +
        '                                <td>' + data.id + '</td>\n' +
        '                                <td>' + data.type + '</td>\n' +
        '                                <td>' + data.ipAddress+ '</td>\n' +
        '                                <td>' + data.status + '</td>\n' +
        '                                <td>\n' +
        '                                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="changeDeviceInfo()">修改</button>\n' +
        '                                    <button class="btn btn-danger" onclick="deleteDevice(' + data.id + ')">删除</button>\n' +
        '                                </td>\n' +
        '                            </tr>');
}
function constructTable(data) {
    for(var i = 0, len = data.length; i < len; i++){
        constructTr(data[i]);
    }
}
/*获取设备信息--结束*/

/*添加设备--开始*/
function saveAddDevice() {
    $.ajax({
        type: 'post',
        dataType: 'json',
        contentType: "application/json",
        url: domainName + '/equipment/add',
        data: JSON.stringify(DeviceObj()),
        success: function () {
            alert('添加成功');
            location.reload();
        },
        error: function (error) {
            throw Error(error);
        }
    })
}
/*添加设备--结束*/

/*删除设备--开始*/
function deleteDevice(obj) {
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: domainName + '/equipment/delete',
        data: {id : obj},
        success: function () {
            var conf = confirm('确认删除吗？');
            if(conf){
                alert('删除成功！');
                location.reload();
            }
        },
        error: function (error) {
            throw Error(error);
        }
    });
}
/*删除设备--结束*/

/*修改设备--开始*/
function changeDeviceInfo() {
    $('#myModalLabel').html('修改设备');
}
/*修改设备--结束*/

