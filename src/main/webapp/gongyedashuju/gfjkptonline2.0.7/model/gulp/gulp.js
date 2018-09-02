function gulpHtml(elem,fileName,callback) {
    var  str1= '<dl class="list_dl">\n' +
        '    <dt class="list_dt">\n' +
        '        <span class="_after"></span>\n' +
        '        <p><span class="glyphicon glyphicon-flash" aria-hidden="true">光伏发电</span></p>\n' +
        '        <i class="list_dt_icon"></i>\n' +
        '    </dt>\n' +
        '    <dd class="list_dd">\n' +
        '        <ul>\n' +
        '            <li class="list_li" onclick="toPowerPreview()">电站预览</li>\n' +
        '            <li class="list_li" onclick="toInverterInfo()">逆变器信息</li>\n' +
        '            <li class="list_li" onclick="toInverterHistory()">历史数据</li>\n' +
        '            <li class="list_li" onclick="toPredictionAnalysis()">预测分析</li>\n' +
        '        </ul>\n' +
        '    </dd>\n' +
        '    <dt class="list_dt">\n' +
        '        <span class="_after"></span>\n' +
        '        <p><span class="glyphicon glyphicon-stats" aria-hidden="true">负荷监控</span></p>\n' +
        '        <i class="list_dt_icon"></i>\n' +
        '    </dt>\n' +
        '    <dd class="list_dd">\n' +
        '        <ul>\n' +
        '            <li class="list_li" onclick="toEnergyUseMonitoring()">用能监测</li>\n' +
        '            <li class="list_li">能效管理</li>\n' +
        '            <li class="list_li" onclick="toStatisticalAnalysis()">统计分析</li>\n' +
        '        </ul>\n' +
        '    </dd>\n' +
        '    <dt class="list_dt">\n' +
        '        <span class="_after"></span>\n' +
        '        <p><span class="glyphicon glyphicon-cog" aria-hidden="true">设备信息</span></p>\n' +
        '        <i class="list_dt_icon"></i>\n' +
        '    </dt>\n' +
        '    <dd class="list_dd">\n' +
        '        <ul>\n' +
        '            <li class="list_li" onclick="toDeviceManagement()">设备管理</li>\n' +
        '            <li class="list_li">逆变器数据</li>\n' +
        '            <li class="list_li">负荷数据</li>\n' +
        '            <li class="list_li">储能数据</li>\n' +
        '        </ul>\n' +
        '    </dd>\n' +
        '    <dt class="list_dt">\n' +
        '        <span class="_after"></span>\n' +
        '        <p><span class="glyphicon glyphicon-cog" aria-hidden="true">用户管理</span></p>\n' +
        '        <i class="list_dt_icon"></i>\n' +
        '    </dt>\n' +
        '    <dd class="list_dd">\n' +
        '        <ul>\n' +
        '            <li class="list_li" onclick="toAccountManage()">角色管理</li>\n' +
        '        </ul>\n' +
        '    </dd>\n' +
        '</dl>\n';
    var  str2= '<nav class="navbar navbar-default header-nav">\n' +
        '                    <div class="container-fluid">\n' +
        '                        <div class="navbar-header">\n' +
        '                            <a class="navbar-brand" href="#">长沙理工大学光伏监控平台</a>\n' +
        '                        </div>\n' +
        '                        <ul class="nav navbar-nav navbar-right navbar-right-list">\n' +
        '                            <li><a href="../../index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a></li>\n' +
        '                            <li><a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>电站列表</a></li>\n' +
        '                            <li><a href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>Welcome</a></li>\n' +
        '                            <li class="dropdown">\n' +
        '                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>设置<span class="caret"></span></a>\n' +
        '                                <ul class="dropdown-menu">\n' +
        '                                    <li><a href="#">修改密码</a></li>\n' +
        '                                    <li><a href="#" onclick="logout()">注销</a></li>\n' +
        '                                </ul>\n' +
        '                            </li>\n' +
        '                        </ul>\n' +
        '                    </div>\n' +
        '                </nav>';
        var flag = fileName.split('/')[3].slice(0,1);
        document.getElementById(elem).innerHTML=flag==='h'?str2:str1;
        callback && callback();
}