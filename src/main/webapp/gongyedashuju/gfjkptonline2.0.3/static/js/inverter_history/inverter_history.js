/*树形菜单开始*/
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback : {
        onClick: ''
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

/*历史数据表格部分开始*/
var $table_body = $('#table-body');
$.ajax({
    type : 'post',
    dataType : 'json',
    url : 'http://localhost:8080/inverter/list?pageNo=1&pageSize=10&name=inverter1',
    success : function (data) {
        $table_body.html('');
        var inverterList = data.data.inverterList;
        for(var i = 0;i < inverterList.length; i++){
            constructTr(inverterList[i]);
        }
        layui.use('laypage', function(){
            var laypage = layui.laypage;
            //执行一个laypage实例
            laypage.render({
                elem: "page-nation" //注意，这里的 test1 是 ID，不用加 # 号
                ,count: 500 //数据总数，从服务端得到
                ,limit : 10
                ,curr : 1
                ,jump: function (obj) {
                    $.ajax({
                        type : 'post',
                        url : 'http://localhost:8080/inverter/list?name=inverter1',
                        dataType : 'json',
                        data : { pageNo : obj.curr, pageSize : obj.limit},
                        success : function (data2) {
                            var inverterList2 = data2.data.inverterList;
                            $table_body.html('');
                            for(var i = 0; i < inverterList2.length; i++){
                                constructTr(inverterList2[i]);
                            }
                        },
                        error : function (error) {
                            console.log(error);
                        }
                    });
                }
            });
        });
    },
    error : function (error) {
        console.log(error);
    }
});

function constructTr(list) {
    $('#table-body').append('<tr>\n' +
        '                                            <td>' + list.id + '</td>\n' +
        '                                            <td>' + list.times + '</td>\n' +
        '                                            <td>' + list.dailyOutput + '</td>\n' +
        '                                            <td>' + list.totalOutput + ' </td>\n' +
        '                                            <td>' + list.aPhaseCurrent + '</td>\n' +
        '                                            <td>' + list.aPhaseVoltage + '</td>\n' +
        '                                            <td>' + list.bPhaseCurrent + '</td>\n' +
        '                                            <td>' + list.bPhaseVoltage + '</td>\n' +
        '                                            <td>' + list.cPhaseCurrent + '</td>\n' +
        '                                            <td>' + list.cPhaseVoltage + '</td>\n' +
        '                                            <td>' + list.totalActivePower + '</td>\n' +
        '                                            <td>' + list.tansTemp1 + '</td>\n' +
        '                                            <td>' + list.tansTemp2 + '</td>\n' +
        '                                        </tr>');
}

/*历史数据表格结束*/