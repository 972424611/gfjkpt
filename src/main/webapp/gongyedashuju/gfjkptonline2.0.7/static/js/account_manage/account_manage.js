var domainName = 'http://47.106.101.133:8080/gfjkpt';
/*组件的拼装--开始*/
gulpHtml('header-wrap','../model/gulp/header.html');
gulpHtml('section-left','../model/gulp/section-left.html',function () {
    $.getScript('../plugin/accordion/js/accordion.js');
});
// /*组件的拼装--结束*/
// function Tree(elem,zNodes) {
//     var setting = {
//         data: {
//             simpleData: {
//                 enable: true
//             }
//         },
//         callback: {
//             onClick: function (event, treeId, treeNode) {
//                 zTree.checkNode(treeNode, !treeNode.checked, true);
//             }
//         },
//         check: {
//             enable : true,
//             checkStyle : "checkbox",    //复选框
//             checkboxType : {
//                 "Y" : "s",
//                 "N" : "ps"
//             }
//         }
//     };
//     var zTree = $.fn.zTree.init($('#' + elem), setting, zNodes);
// }
//
// var roleTreeNodes = [
//     {id: 1, pId: 0, name: 'xlxq'},
//     {id: 2, pId: 0, name: 'glxq'}
// ];
// var buildingTreeNodes = [
//     {id: 1, pId: 0, name: 'xxx大学', isParent: true, open: true},
//     {id: 11, pId: 1, name: '示例建筑1'},
//     {id: 12, pId: 1, name: '示例建筑2'},
//     {id: 13, pId: 1, name: '示例建筑3'},
//     {id: 14, pId: 1, name: '示例建筑4'},
//     {id: 15, pId: 1, name: '示例建筑5'},
//     {id: 16, pId: 1, name: '示例建筑6'}
// ];
// var departmentTreeNodes = [
//     {id: 1, pId: 0, name: 'xxx大学', isParent: true, open: true},
//     {id: 11, pId: 1, name: '示例部门1'},
//     {id: 12, pId: 1, name: '示例部门2'},
//     {id: 13, pId: 1, name: '示例部门3'},
//     {id: 14, pId: 1, name: '示例部门4'},
//     {id: 15, pId: 1, name: '示例部门5'},
//     {id: 16, pId: 1, name: '示例部门6'}
// ];
// var functionTreeNodes = [
//     {id: 1, pId: 0, name: '首页'},
//     {id: 2, pId: 0, name: '地图'},
//     {id: 3, pId: 0, name: '用能监测', isParent: true, open: true},
//     {id: 31, pId: 3, name: '按建筑查看', isParent: true, open: true},
//     {id: 311, pId: 31, name: '负荷监测'},
//     {id: 312, pId: 31, name: '电量检测'},
//     {id: 411, pId: 3, name: '按单位查看', isParent: true, open: true},
//     {id: 411, pId: 41, name: '负荷监测'},
//     {id: 412, pId: 41, name: '电量检测'}
// ];
// Tree('roleTree',roleTreeNodes);
// Tree('buildingTree',buildingTreeNodes);
// Tree('departmentTree',departmentTreeNodes);
// Tree('functionTree',functionTreeNodes);

var $myModalLabel = $('#myModalLabel');
var $table_body = $('#table-body');
/*展示用户信息部分--开始*/
showUser();
function showUser() {
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: domainName + '/admin/list',
        success: function (result) {
            constructTable(result.data);
        },
        error: function (error) {
            throw Error(error);
        }
    })
}
function constructTable(data) {
    for(var i = 0,len = data.length; i < len; i++){
        constructTr(data[i]);
    }
}
function constructTr(data) {
    console.log(data);
    $table_body.append('<tr>\n' +
        '                            <td>' + data.id + '</td>\n' +
        '                            <td>' + data.username + '</td>\n' +
        '                            <td>' + data.status + '</td>\n' +
        '                            <td>' + data.role + '</td>\n' +
        '                            <td>\n' +
        '                                <button class="btn btn-danger" onclick="deleteUser(' + data.id + ')">删除</button>\n' +
        '                            </td>\n' +
        '                        </tr>');
}
/*展示用户信息部分--开始*/

/*添加用户部分--开始*/
var $username = $('#username');
var $password = $('#password');
var $role = $('input[name = "role"]');
var $status = $('#status');
function clearForm() {
    $username.val('');
    $password.val('');
    $role.each(function () {
        $(this).prop('checked',false);
    });
    $status.prop('checked',false);
}
function selectChecked(arr) {
    var tmp;
    console.log(arr);
    for(var i = 0; i < arr.length; i++){
        if(arr[i]['checked']){
            tmp = arr[i].labels[0].innerHTML;
            break;
        }
    }
    return tmp;
}
function addUser() {
    $myModalLabel.html('添加用户');
    clearForm();
}
function saveAddUser() {
    $.ajax({
        type: 'post',
        dataType: 'json',
        url: domainName + '/admin/add',
        data:{
            username: $username.val(),
            password: $password.val(),
            role: selectChecked($role),
            status: judgeStatus()
        },
        success: function (result) {
            if (result.ret === true){
                alert('保存成功');
                location.reload();
            }else {
                alert('保存失败');
            }
        },
        error: function (error) {
            throw Error(error);
        }
    });
}
function judgeStatus() {
    if ($status.is(':checked')){
        return 1;
    }else {
        return 0;
    }
}
/*添加用户部分--结束*/

/*修改用户部分--开始*/
function changeUserInfo() {
    $myModalLabel.html('修改信息');
    clearForm();
}
/*修改用户部分--结束*/

/*删除用户部分--开始*/
function deleteUser(id) {
    var conf = confirm('确认删除？');
    if(conf){
        $.ajax({
            type: 'post',
            url: domainName + '/admin/delete',
            dataType: 'json',
            data: {
                id: id
            },
            success: function (result) {
                if (result.ret === true){
                    alert('删除成功！');
                    location.reload();
                }else {
                    alert('删除失败！');
                }
            },
            error: function (error) {
                throw Error(error);
            }
        })
    }
}
/*删除用户部分--结束*/