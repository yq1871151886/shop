<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/20
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Title</title>
    <link type="text/css" href="/commons/mainjc/css/style.css" rel="stylesheet" />
    <jsp:include page="commons/commons.jsp"></jsp:include>
    <script type="text/javascript" src="/commons/mainjc/js/jquery.min.js"></script>



    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="/commons/mainjs/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="/commons/mainjs/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Google fonts - Roboto-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,300,700,400italic">
    <!-- owl carousel-->
    <link rel="stylesheet" href="/commons/mainjs/vendor/owl.carousel/assets/owl.carousel.css">
    <link rel="stylesheet" href="/commons/mainjs/vendor/owl.carousel/assets/owl.theme.default.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="/commons/mainjs/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="/commons/mainjs/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="/commons/mainjs/img/favicon.png">
    <script src="/commons/mainjs/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="/commons/mainjs/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="/commons/mainjs/vendor/owl.carousel/owl.carousel.min.js"></script>
    <script src="/commons/mainjs/vendor/masonry-layout/masonry.pkgd.min.js"></script>
    <script src="/commons/mainjs/js/front.js"></script>



</head>
<body>
<!--左侧菜单-->
<div class="sec-mainL left" id="menu">

</div>

<div id="mainvi"></div>
<%--原始的div--%>
<%--<div class="col-md-6 col-lg-3 grid-item">
    <div class="box-masonry"> <a href="detail.html" title="" class="box-masonry-image with-hover-overlay with-hover-icon"><img src="/commons/mainjs/img/portfolio/gravity-paper.jpg" alt="" class="img-fluid"></a>
        <div class="box-masonry-text">
            <h4> <a href="detail.html">Name of the work 3</a></h4>
            <div class="box-masonry-desription">
                <p>Fifth abundantly made Give sixth hath. Cattle creature i be don't them.</p>
            </div>
        </div>
    </div>
</div>--%>
<br/>
<div class="layui-card">
    <div class="layui-card-header">商品列表</div>
    <center>
    <div class="layui-card-body">
        <table class="layui-hide" id="shop"  lay-filter="shop"></table>
    </div>
    </center>
</div>
<script type="text/javascript">
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use(['element','util','form','table'], function () {
        /*var form = layui.form;
        var element = layui.element;
        element.on('nav(test1)', function (elem) {
            console.log(elem); //得到当前点击的DOM对象
        });

        //监听折叠
        element.on('collapse(test1)', function(data){
            //layer.msg('展开状态：'+ data.show);
        });*/

        var utile = layui.util;
    });



        $(function () {
          queryMenu();
            var $top = $('.sec-mainNav').offset().top + $('.sec-mainNav').height()
            //左侧导航动画
            $('.sec-mainNav li').on('mouseenter', function() {
                var $height = $(this).offset().top + $(this).find('.menu-panel').outerHeight();
                $(this).find('.menu-panel').show();
                if($height - $top >= 0) {
                    $(this).find('.menu-panel').css({
                        top: -($height - $top) + 'px'
                    })
                }
            });
            $('.sec-mainNav li').on('mouseleave', function() {
                $(this).find('.menu-panel').hide();
            });
            /*这是两个展示DIV,类似于淘宝京东那种有大图得*/
            /*queryShopsAllByElectricid();
            queryShopsAllByBrand();*/
        })

    function queryMenu() {

        $.ajax({
            url:"http://localhost:8081/menu/queryMenu",
            type:"get",
            dataType:"json",
            async:false,
            success:function (result) {
                var menuHtml = "<div class='mainL-hd-box'>" +
                    "        <h2 class='mainL-hd'><a href='#'>电商</a></h2>" +
                    "    </div>" +
                    "    <ul class='sec-mainNav'>";
                for (var i = 0; i < result.length; i++) {
                    menuHtml+="<li><h3  onclick='searchShopss(null,\""+result[i].id+"\",this)'>"+result[i].name+"</h3><div class='menu-tab' name='electric' onclick='searchShopss(null,\""+result[i].id+"\",this)'>";
                    var child =  result[i].children;
                    if (child.length>1) {
                        for (var j = 0; j <2;j++){
                            menuHtml+="<a href=''>"+child[j].name+"</a>";
                        }
                    }

                        menuHtml+="<div class='fix'></div>" +
                            "            </div>" +
                            "            <span class='menu-more'>更多</span>" +
                            "            <div class='menu-panel'>" +
                            "                <div class='menu-panel-hd'>" +
                            "                    <h4>热门分类</h4>" +
                            "                    <div class='sub-group'>";
                    for (var j = 0; j <child.length;j++){
                        menuHtml+=" <a href='javascript:;' name='brand' onclick='searchShops(\""+child[j].ele+"\",\""+result[i].id+"\",this)'>"+child[j].name+"</a>";
                    }

                    menuHtml+=" </div>" +
                        "                </div>" +
                        "                <div class='menu-panel-bd'>" +
                        "                    <ul>" ;

                    for (var j = 0; j <child.length;j++){
                        menuHtml+="<li><a href=''><img src='"+child[j].image+"'/></a></li>";
                    }
                        menuHtml+="                    </ul>" +
                        "                </div>" +
                        "                <a href='' class='menu-panel-btn'>" +
                        "                    <span>查看全部商家</span>" +
                        "                    <em></em>" +
                        "                </a>" +
                        "            </div>" +
                        "        </li>";
                }
                menuHtml+="</ul>";
                $("#menu").html(menuHtml);
            },
        })
    }


      function queryShopsAllByElectricid(electricid) {
                $.ajax({
                    data:{
                        electricid:electricid
                    },
                    url:"http://localhost:8083/shop/queryShopsAllByElectricid",
                    dataType:"json",
                    async:false,
                    success:function (shop) {
                        /* private Integer id; electricid; name; subtitle; mainImg; subImgs; detail; price;stock;
         status; createTime; updateTime;Integer limit; page;*/
                    var vi = '<div class="box-masonry"> <a href="detail.html" title="" class="box-masonry-image with-hover-overlay with-hover-icon">';
                        var vi ='';
                        for (var i = 0; i < shop.length; i++) {

                            vi+='<div class="col-md-2 col-lg-3 grid-item">' +
                                '                    <div class="box-masonry"> <a href="detail.html" title="" class="box-masonry-image with-hover-overlay with-hover-icon"><img src="'+shop[i].mainImg+'" alt="" class="img-fluid"></a>' +
                                '                        <div class="box-masonry-text">' +
                                '                            <h4> <a href="detail.html">'+shop[i].subtitle+'</a></h4>' +
                                '                            <div class="box-masonry-desription">' +
                                '                                <p>'+shop[i].name+'</p>' +
                                '                                <p>'+shop[i].detail+'</p>' +
                                '                                <p>'+shop[i].price+'</p>' +
                                '                                <p>'+shop[i].stock+'</p>' +
                                '                                <p>'+shop[i].status+'</p>' +
                                '                            </div>' +
                                '                        </div>' +
                                '                    </div>' +
                                '                </div>';

                        }

                        $("#mainvi").html(vi);
                    /*<div class="col-md-6 col-lg-3 grid-item">
                    <div class="box-masonry"> <a href="detail.html" title="" class="box-masonry-image with-hover-overlay with-hover-icon"><img src="/commons/mainjs/img/portfolio/gravity-paper.jpg" alt="" class="img-fluid"></a>
                        <div class="box-masonry-text">
                            <h4> <a href="detail.html">Name of the work 3</a></h4>
                            <div class="box-masonry-desription">
                                <p>Fifth abundantly made Give sixth hath. Cattle creature i be don't them.</p>
                            </div>
                        </div>
                    </div>
                </div>*/

                    }
                })
      }





function queryShopsAllByBrand(brandid,electricid) {
    $.ajax({
        data:{
            electric:electricid,
            brandid:brandid
        },
        url:"http://localhost:8083/shop/queryShopsAllByBrandid",
        dataType:"json",
        async:false,
        success:function (shop) {
            var vi = '<div class="box-masonry"> <a href="detail.html" title="" class="box-masonry-image with-hover-overlay with-hover-icon">';
            var vi ='';
            for (var i = 0; i < shop.length; i++) {
                vi+='<div class="col-md-2 col-lg-3 grid-item">' +
                    '                    <div class="box-masonry"> <a href="detail.html" title="" class="box-masonry-image with-hover-overlay with-hover-icon"><img src="'+shop[i].mainImg+'" alt="" class="img-fluid"></a>' +
                    '                        <div class="box-masonry-text">' +
                    '                            <h4> <a href="detail.html">'+shop[i].subtitle+'</a></h4>' +
                    '                            <div class="box-masonry-desription">' +
                    '                                <p>'+shop[i].name+'</p>' +
                    '                                <p>'+shop[i].detail+'</p>' +
                    '                                <p>'+shop[i].price+'</p>' +
                    '                                <p>'+shop[i].stock+'</p>' +
                    '                                <p>'+shop[i].status+'</p>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>' +
                    '                </div>';
            }

            $("#mainvi").html(vi);
        }
    })



}




    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#shop'
            ,url:'http://localhost:8083/shop/queryShopsByparameter1'
            ,method:'get'
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 1 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: false //不显示尾页
            }
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'name', width:100, title: '商品名称'}
                ,{field:'subtitle', width:100, title: '标题'}
                ,{field:'detail', width:100, title: '描述'}
                ,{field:'price', width:100, title: '价格'}
                ,{field:'stock', width:100, title: '库存'}
                ,{field:'createTime', width:160, title: '登记时间',
                    templet:function (d) {
                        if (d.createTime != null) {
                            return new Date(d.createTime).Format("yyyy-MM-dd hh:mm:ss");
                        }
                        return "";
                    }}
                , {fixed: 'right', align: 'center', width: 200, toolbar: '#barDemo'}
            ]]

        });
    });




    function searchShops(brandid,electricid,obj){
        // stuName  province1  city1 county1 education d1 d2          adult stuSex1  choreStatus1  insurance1
            var jsonData={};
            jsonData.brandid = brandid;
            jsonData.electricid = electricid;

        var brands =  document.getElementsByName("brand");
            for (var i = 0; i < brands.length; i++) {
                if (brands[i] == obj) {
                    $(brands[i]).css("background-color","gray");
                   // $(brands[i]).parent().parent().parent().parent().css("background-color","gray");
                }else {
                    $(brands[i]).css("background-color","");
                }
            }
        //重新加载列表并传参
        layui.table.reload('shop',{
                where:jsonData
                ,page:
                    {
                        curr: 1 //重新从第 1 页开始
                    }
            }
        );
    }
    function searchShopss(brandid,electricid,obj){
        // stuName  province1  city1 county1 education d1 d2          adult stuSex1  choreStatus1  insurance1
            var jsonData={};
            jsonData.brandid = brandid;
            jsonData.electricid = electricid;

        var electrics =  document.getElementsByName("electric");
            for (var i = 0; i < electrics.length; i++) {
                if (electrics[i] == obj) {
                    $(electrics[i]).parent().parent().css("background-color","gray");
                }else {
                    $(electrics[i]).parent().parent().css("background-color","");
                }
            }
        var brands =  document.getElementsByName("brand");
        for (var i = 0; i < brands.length; i++) {
                $(brands[i]).css("background-color","");
        }
        //重新加载列表并传参
        layui.table.reload('shop',{
                where:jsonData
                ,page:
                    {
                        curr: 1 //重新从第 1 页开始
                    }
            }
        );
    }


















</script>




</body>
</html>
