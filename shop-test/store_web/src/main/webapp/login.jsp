<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/20
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta charset="UTF-8">
    <meta name="description" content="Admin Dashboard Template" />
    <meta name="keywords" content="admin,dashboard" />
    <meta name="author" content="Lalassu" />
    <title>Title</title>
    <link href="/commons/register/assets/plugins/pace-master/themes/blue/pace-theme-flash.css" rel="stylesheet"/>
    <link href="/commons/register/assets/plugins/uniform/css/default.css" rel="stylesheet"/>
    <link href="/commons/register/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/commons/register/assets/plugins/fontawesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
    <link href="/commons/register/assets/plugins/line-icons/simple-line-icons.css" rel="stylesheet" type="text/css"/>
    <link href="/commons/register/assets/plugins/offcanvasmenueffects/css/menu_cornerbox.css" rel="stylesheet" type="text/css"/>
    <link href="/commons/register/assets/plugins/waves/waves.min.css" rel="stylesheet" type="text/css"/>
    <link href="/commons/register/assets/plugins/switchery/switchery.min.css" rel="stylesheet" type="text/css"/>
    <link href="/commons/register/assets/plugins/3d-bold-navigation/css/style.css" rel="stylesheet" type="text/css"/>

    <!-- Theme Styles -->
    <link href="/commons/register/assets/css/layers.min.css" rel="stylesheet" type="text/css"/>
    <link href="/commons/register/assets/css/layers/dark-layer.css" class="theme-color" rel="stylesheet" type="text/css"/>
    <link href="/commons/register/assets/css/custom.css" rel="stylesheet" type="text/css"/>

    <script src="/commons/register/assets/plugins/3d-bold-navigation/js/modernizr.js"></script>
    <script src="/commons/register/assets/plugins/offcanvasmenueffects/js/snap.svg-min.js"></script>
    <%--<jsp:include page="commons/commons.jsp"></jsp:include>--%>
    <script src="commons/jquery-3.2.1.js"></script>
</head>
<body class="page-register-alt">
<main class="page-content">
    <div class="page-inner">
        <div id="main-wrapper">
            <div class="row">
                <div class="col-md-3 col-register-box-alt">
                    <div class="panel panel-white register-box">
                        <div class="panel-body">
                            <label><font style="font-size: 37px">登陆页面</font></label>
                            <p class="m-t-md">在此使用手机验证进行登陆</p>
                            <form id="register">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="phone1" placeholder="手机号" required>
                                </div>
                                <div class="form-group">
                                    <input type="button" class="btn-info" onclick="sendCode()" value="发送验证码">
                                    <input type="text" id="phoneCode" class="password form-control" placeholder="验证码" required>
                                </div>
                                <!--<label>
                                    <input type="checkbox"> Agree the terms and policy
                                </label>-->
                                <!--<a href="login-alt.html" class="btn btn-default btn-block m-t-xs">Login</a>-->
                            </form>
                            <button class="btn btn-success btn-block m-t-xs" onclick="login()">登陆</button>
                            <p class="text-center m-t-xs text-sm register-footer">2019 &copy; lizards.</p>
                        </div>
                    </div>
                </div>
            </div><!-- Row -->
        </div><!-- Main Wrapper -->
    </div><!-- Page Inner -->
</main><!-- Page Content -->

<script type="text/javascript">


    function sendCode(){


        var phone = $("#phone1").val();

        $.ajax({
            data:{phoneNum:phone},
            url:"http://localhost:8081/users",
            dataType:"json",
            success:function (result) {
                if (result.code==512){
                    alert(result.message);
                } else {
                    alert(result.message);
                }
            },

        })

    }



function login() {



    var phone = $("#phone1").val();
        var phoneCode = $("#phoneCode").val();

        if (phoneCode=="" || phoneCode==null){
            alert("请输入验证码");
            return false;
        }

        $.post({
            data:{
                phoneNum:phone,
                code:phoneCode
            },
                url:"http://localhost:8081/users",
                dataType:"json",
                success:function (result) {

                    if (result.code==200){
                        alert(result.message);
                        window.location.href="/index.jsp";
                    } else {
                        alert(result.message);
                    }
            }


        })




}







</script>





</body>
</html>
