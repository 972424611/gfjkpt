<html>
<head>
    <script type="text/javascript" src="history/static/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>

<script type="text/javascript">

    window.onload = function () {
        $.ajax({
            url: "/inverter/get/逆变器1?startPage=1&pageSize=10",
            type: "GET",
            success: function (result) {
                alert(result)
            }
        })
    }
    //     var request = new XMLHttpRequest();
    //     //request.open("GET","/inverter/getAll/逆变器1");
    //     request.open("GET","http://39.108.5.210/gfjkpt/inverter/getAll/gff");
    //     request.send();
    //     request.onreadystatechange = function () {
    //         if(request.readyState === 4){
    //             if(request.status === 200){
    //                 var str=JSON.parse(request.responseText);
    //                 if(str.status === 200){
    //                     alert(str.data[0].inverter_name);
    //                 }
    //             }
    //             else alert("error:"+request.status);
    //         }
    //     }
    // }
    // window.onload = function () {
    // $.ajax({
    //     type : "GET",
    //     async:false,
    //     jsonp:'callback',
    //     url : "http://522108b5.ngrok.io/inverter/getAll/逆变器1",
    //     dataType : "jsonp",
    //     jsonpCallback: "showData",  //指定回调函数名称
    //     success : function(){
    //     alert(545645);
    // },
    // error:function(){
    //     alert('fail');
    // }
    // });}
</script>

</body>
</html>
