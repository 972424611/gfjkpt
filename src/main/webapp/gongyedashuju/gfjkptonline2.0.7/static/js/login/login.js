/**监听输入框鼠标的点击和移除点击
 * **/

var $rewrite = $('.rewrite');
var $submit = $('.submit');
var $username = $('#username');
var $password = $('#password');
var $warning_bar = $('.warning-bar');

window.onload = function () {
    $rewrite.on('click',function () {
        $username.val('');
        $password.val('');
    });
    $submit.on('click',function () {
        if(judgeForm()) {
            $.ajax({
                type : 'post',
                url : 'http://47.106.101.133:8080/gfjkpt/user/login',
                dataType : 'json',
                data : {username : $username.val(), password : $password.val()},
                success : function (result) {
                    if(result.ret === true) {
                        $.cookie('role',$username.val());
                        window.location = './power_preview.html';
                    } else {
                        alert('账号或密码不正确！');
                    }
                },
                error : function () {
                    alert('请检查网络！');
                }
            })
        } else {
            return false;
        }
    })
};


function judgeForm() {//用来判断用户名和密码是否为空
    if($username.val() === '' && $password.val() === ''){
        $warning_bar.css('display','block');
        $warning_bar.html('请填写用户名和密码');
        return false;
    } else if($username.val() === '' && $password.val() !== ''){
        $warning_bar.css('display','block');
        $warning_bar.html('请填写用户名');
        return false;
    } else if($username.val() !== '' && $password.val() === ''){
        $warning_bar.css('display','block');
        $warning_bar.html('请填写密码');
        return false;
    } else {
        $warning_bar.css('display','none');
        return true;
    }
}