<template>
  <div>
    <!-- 快捷导航模块 start -->
<section class="shortcut">
        <div class="w">
            <div class="fl">
                <ul>
                    <li>东软跨境电商借买交易平台欢迎您！&nbsp;</li>
                    <!-- <li>
                        <a href="#">请登录</a> &nbsp; <a href="register.html" class="style_red">免费注册</a>
                    </li> -->
                </ul>
            </div>
            <div class="fr">
                <ul>
                    <li><a @click="resellerIndex" class="style_red">首页</a></li>
                    <li></li>
                    <li>
                        <a>我的网店</a>
                        <div class="dropdown">
                            <a @click="resellerProductList">网店商品</a>
                            <a @click="resellerStoreInfo">信息管理</a>
                        </div>
                    </li>
                    <li></li>
                    <li><a @click="resellerWish">我的购物车</a></li>
                    <li></li>
                    <li><a @click="resellerOrder">我的订单</a></li>
                    <li></li>
                    <li>
                        <a href="#">电子钱包</a>
                        <div class="dropdown">
                            <a @click="resellerWalletManagement">钱包管理</a>
                            <a @click="resellerTransactionRecords">交易记录</a>
                        </div>
                    </li>
                    <li></li>
                    <li><a  @click="resellerInfo">我的信息</a></li>
                </ul>
            </div>
        </div>
    </section>
    <!-- 快捷导航模块 end -->
    <!-- header头部模块制作 start -->
    <header class="header w">
        <!-- logo模块 -->
        <div class="logo">
            <h1>
                <a @click="resellerIndex" title="东软跨境电商借买交易平台">东软跨境电商借买交易平台</a>
            </h1>
        </div>
        <!-- search搜索模块 -->
        <div class="search">
            <input type="search" name="" id="">
            <button>搜索</button>
        </div>
        <!-- hotwords模块制作 -->
        <div class="hotwords">
            <a href="#" class="style_red">优惠借卖价首发</a>
            <a href="#">亿元优惠</a>
            <a href="#">团购价</a>
            <a href="#">数码</a>
            <a href="#">礼服</a>
        </div>
        <!-- 心愿单模块 -->
        <div class="shopcar">
            <a @click="resellerWish">我的购物车</a>
            <!-- <i class="count">8</i> -->
        </div>
    </header>
    <!-- header头部模块制作 end -->
    <!-- nav模块制作 start -->
    <nav class="nav">
        <div class="w">
            <div class="dropdown">
                <div class="dt">全部商品分类</div>
                <div class="dd">
                    <ul>
                        <li><a @click="resellerProductList">数码产品</a> </li>
                        <li><a href="reseller_product_list.html">相机</a>、 <a href="#">摄像机</a>、<a href="#">无人机</a> </li>
                        <li><a href="#">电脑及配件</a> </li>
                        <li><a href="#">奢侈品</a> </li>
                        <li><a href="#">乐器</a> </li>
                        <li><a href="#">家用电器</a> </li>
                        <li><a href="#">户外用品</a> </li>
                        <li><a href="#">工具和设备</a> </li>
                        <li><a href="#">影音设备</a> </li>
                        <li><a href="#">摄影灯光</a> </li>
                        <li><a href="#">办公设备</a> </li>
                        <li><a href="#">运动器材</a> </li>
                        <li><a href="#">舞台设备</a> </li>
                        <li><a href="#">测量仪器</a> </li>
                        <li><a href="#">其他</a> </li>
                    </ul>
                </div>
            </div>
            <div class="navitems">
                <ul>
                    <li><a href="#">数码城</a></li>
                    <li><a href="#">相机城</a></li>
                    <li><a href="#">电脑城</a></li>
                    <li><a href="#">乐器城</a></li>
                    <li><a href="#">奢侈品</a></li>
                    <li><a href="#">工具城</a></li>
                    <li><a href="#">影音城</a></li>
                    <li><a href="#">办公城</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- nav模块制作 end -->
    <!-- 首页专有的模块 main  start -->
    <div class="w">
        <div class="main">
            <div class="focus">
                <ul>
                    <li>
                        <img src="../assets/upload/focus1.png" alt="">
                    </li>
                </ul>
            </div>
            <div class="newsflash">
                <div class="news">
                    <div class="news-hd">
                        <h5>平台快报</h5>
                        <a href="#" class="more">更多</a>
                    </div>
                    <div class="news-bd">
                        <ul>
                            <li><a href="#"><strong>[重磅]</strong> 超低借买价！</a></li>
                            <li><a href="#"><strong>[重磅]</strong> 五大品牌入驻！</a></li>
                            <li><a href="#"><strong>[重磅]</strong> 超低借买价！</a></li>
                            <li><a href="#"><strong>[重磅]</strong> 五大品牌入驻！</a></li>
                            <li><a href="#"><strong>[重磅]</strong> 超低借买价！</a></li>
                        </ul>
                    </div>
                </div>
                <div class="lifeservice">
                    <ul>
                        <li>
                            <i></i>
                            <p>数码</p>
                        </li>
                        <li>
                            <i></i>
                            <p>相机</p>
                        </li>
                        <li>
                            <i></i>
                            <p>乐器</p>
                        </li>
                        <li>
                            <i></i>
                            <p>奢侈品</p>
                        </li>
                        <li>
                            <i></i>
                            <p>户外用品</p>
                        </li>
                        <li>
                            <i></i>
                            <p>工具</p>
                        </li>
                        <li>
                            <i></i>
                            <p>运动器材</p>
                        </li>
                        <li>
                            <i></i>
                            <p>影音设备</p>
                        </li>
                        <li>
                            <i></i>
                            <p>摄影灯光</p>
                        </li>
                        <li>
                            <i></i>
                            <p>办公设备</p>
                        </li>
                        <li>
                            <i></i>
                            <p>测量仪器</p>
                        </li>
                        <li>
                            <i></i>
                            <p>舞台设备</p>
                        </li>
                    </ul>
                </div>
                <div class="bargain">
                    <img src="../assets/upload/bargain.png" alt="">
                </div>
            </div>
        </div>
    </div>
    <!-- 首页专有的模块 main  end -->
  </div>
</template>

<script setup>
import { useRouter, useRoute } from "vue-router";
import ResellerWish from "./ResellerWish.vue";
import ResellerProductList from "./ResellerProductList.vue";
const router = useRouter();
const route = useRoute();
let data = route.query.data;
console.log(data);

const resellerIndex = () => {
  router.push({ path: "/resellerIndex",query:{data:data} });
};
const resellerInfo = () => {
  router.push({ path: "/resellerInfo",query:{data:data} });
};
const resellerProductList = () => {
  router.push({ path: "/resellerProductList",query:{data:data} });
};
const resellerStoreInfo = () => {
  router.push({ path: "/resellerStoreInfo",query:{data:data} });
};
const resellerWish = () => {
  router.push({ path: "/resellerWish",query:{data:data} });
};
const resellerOrder = () => {
  router.push({ path: "/resellerOrder",query:{data:data} });
};
const resellerWalletManagement = () => {
  router.push({ path: "/resellerWalletManagement",query:{data:data} });
};
const resellerTransactionRecords = () => {
  router.push({ path: "/resellerTransactionRecords",query:{data:data} });
};

</script>

<style scoped>
/* 把我们所有标签的内外边距清零 */
* {
    margin: 0;
    padding: 0;
    /* css3盒子模型 */
    box-sizing: border-box;
}
/* em 和 i 斜体的文字不倾斜 */
em,
i {
    font-style: normal
}
/* 去掉li 的小圆点 */
li {
    list-style: none
}

img {
    /* border 0 照顾低版本浏览器 如果 图片外面包含了链接会有边框的问题 */
    border: 0;
    /* 取消图片底侧有空白缝隙的问题 */
    vertical-align: middle
}

button {
    /* 当我们鼠标经过button 按钮的时候，鼠标变成小手 */
    cursor: pointer
}

a {
    color: #666;
    text-decoration: none
}

a:hover {
    color: #c81623
}

button,
input {
    /* "\5B8B\4F53" 就是宋体的意思 这样浏览器兼容性比较好 */
    font-family: Microsoft YaHei, Heiti SC, tahoma, arial, Hiragino Sans GB, "\5B8B\4F53", sans-serif;
    /* 默认有灰色边框我们需要手动去掉 */
    border: 0; 
    outline: none;
}

body {
    /* CSS3 抗锯齿形 让文字显示的更加清晰 */
    -webkit-font-smoothing: antialiased;
    background-color: #fff;
    font: 12px/1.5 Microsoft YaHei, Heiti SC, tahoma, arial, Hiragino Sans GB, "\5B8B\4F53", sans-serif;
    color: #666
}

.hide,
.none {
    display: none
}
/* 清除浮动 */
.clearfix:after {
    visibility: hidden;
    clear: both;
    display: block;
    content: ".";
    height: 0
}

.clearfix {
    zoom: 1
}

/* 声明字体图标 这里一定要注意路径的变化 */
@font-face {
    font-family: 'icomoon';
    src:  url('../assets/fonts/icomoon.eot?tomleg');
    src:  url('../assets/fonts/icomoon.eot?tomleg#iefix') format('embedded-opentype'),
      url('../assets/fonts/icomoon.ttf?tomleg') format('truetype'),
      url('../assets/fonts/icomoon.woff?tomleg') format('woff'),
      url('../assets/fonts/icomoon.svg?tomleg#icomoon') format('svg');
    font-weight: normal;
    font-style: normal;
    font-display: block;
  }
/* 版心 */
.w {
    width: 1200px;
    margin: 0 auto;
}
.fl {
    float: left;
}
.fr {
    float: right;
}
.style_red {
    color: #c81623;
}
/* 快捷导航模块 */
.shortcut {
    height: 31px;
    line-height: 31px;
    background-color: #f1f1f1;
}
.shortcut ul li {
    float: left;
}
.store_table {
    margin: 20px auto;
    width: 90%;
}

.search_box {
    margin: 20px auto;
    width: 90%;
    text-align: center;
}

.layui-table th,
.layui-table td {
    text-align: center;
}

.layui-table td button {
    margin: 2px 5px;
}

.shortcut .w {
    display: flex;
    justify-content: space-between;
}

.shortcut .fr ul {
    display: flex;
    list-style: none;
}

.shortcut .fr ul li {
    position: relative;
    margin-right: 10px;
}

.shortcut .fr ul li a {
    text-decoration: none;
}

.shortcut .fr ul li:hover .dropdown {
    display: block;
}

.shortcut .dropdown {
    display: none;
    position: absolute;
    top: 20px;
    left: 0;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    z-index: 1000;
}

.shortcut .dropdown a {
    display: block;
    padding: 5px 10px;
    white-space: nowrap;
}

.shortcut .dropdown a:hover {
    background-color: #f5f5f5;
}
/* 选择所有的偶数的小li */
.shortcut .fr ul li:nth-child(even) {
    width: 1px;
    height: 12px;
    background-color: #666;
    margin: 9px 15px 0;
}
.arrow-icon::after {
    content: '\e91e';
    font-family: 'icomoon';
    margin-left: 6px;
}
/* header 头部制作 */
.header {
    position: relative;
    height: 105px;
 
}
.logo {
    position: absolute;
    top: 25px;
    width: 171px;
    height: 61px;
    
}
.logo a {
    display: block;
    width: 184px;
    height: 56px;
    background: url(../assets/images/logo.png) no-repeat;
    /* font-size: 0;京东的做法*/
    /* 淘宝的做法让文字隐藏 */
    text-indent: -9999px;
    overflow: hidden;
}
.search {
    position: absolute;
    left: 346px;
    top: 25px;
    width: 538px;
    height: 36px;
    border: 2px solid #b1191a;
}
.search input {
    float: left;
    width: 454px;
    height: 32px;
    padding-left: 10px;
}
.search button {
    float: left;
    width: 80px;
    height: 32px;
    background-color: #b1191a;
    font-size: 16px;
    color: #fff;
}
.hotwords {
    position: absolute;
    top: 66px;
    left: 346px;
}
.hotwords a {
    margin: 0 10px;
}
.shopcar {
    position: absolute;
    right: 60px;
    top: 25px;
    width: 140px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    border: 1px solid #dfdfdf;
    background-color: #f7f7f7;
}
.shopcar::before {
    content: '\e93a';
    font-family: 'icomoon';
    margin-right: 5px;
    color: #b1191a;
}
.shopcar::after {
    content: '\e920';
    font-family: 'icomoon';
    margin-left: 10px;
}
.count {
    position: absolute;
    top: -5px;
    left: 105px;
    height: 14px;
    line-height: 14px;
    color: #fff;
    background-color: #e60012;
    padding: 0 5px;
    border-radius: 7px 7px 7px 0;
}
/* nav模块制作 */
.nav {
    height: 47px;
    border-bottom: 2px solid #b1191a;
}
.nav .dropdown {
    float: left;
    width: 210px;
    height: 45px;
    background-color: #b1191a;
}
.nav .navitems {
    float: left;
}
.dropdown .dt {
    width: 100%;
    height: 100%;
    color: #fff;
    text-align: center;
    line-height: 45px;
    font-size: 16px;
}
.dropdown .dd {
    /* display: none; */
    width: 210px;
    height: 465px;
    background-color: #c81623;
    margin-top: 2px;
}
.dropdown .dd ul li {
    position: relative;
    height: 31px;
    line-height: 31px;
    margin-left: 2px;
    padding-left: 10px;
}
.dropdown .dd ul li:hover {
   background-color: #fff;
}
.dropdown .dd ul li::after {
    position: absolute;
    top: 1px;
    right: 10px;
    color: #fff;
    font-family: 'icomoon';
    content: '\e920';
    font-size: 14px;
}
.dropdown .dd ul li a {
    font-size: 14px;
    color: #fff;
}
.dropdown .dd ul li:hover a {
    color: #c81623;
}
.navitems ul li {
    float: left;
}
.navitems ul li a {
    display: block;
    height: 45px;
    line-height: 45px;
    font-size: 16px;
    padding: 0 25px;
}

/* 底部模块制作 */
.footer {
    height: 415px;
    background-color: #f5f5f5;
    padding-top: 30px;
}
.mod_service {
    height: 80px;
    border-bottom: 1px solid #ccc;
}
.mod_service ul li {
    float: left;
    width: 300px;
    height: 50px;
    padding-left: 35px;
}
.mod_service ul li h5 {
    float: left;
    width: 50px;
    height: 50px;
    background: url(../assets/images/icons.png) no-repeat -252px -2px;
    margin-right: 8px;
}
.service_txt h4 {
    font-size: 14px;
}
.service_txt  p {
    font-size: 12px;
}
.mod_help {
    height: 185px;
    border-bottom: 1px solid #ccc;
    padding-top: 20px;
    padding-left: 50px;
}
.mod_help dl {
    float: left;
    width: 200px;
}
.mod_help dl:last-child {
    width: 90px;
    text-align: center;
}
.mod_help dl dt {
    font-size: 16px; 
    margin-bottom: 10px;
}
.mod_copyright {
    text-align: center;
    padding-top: 20px;
}
.links {
    margin-bottom: 15px;
}
.links a {
    margin: 0 3px;
}
.copyright {
    line-height: 20px;
}
.main {
    width: 980px;
    height: 455px;
    margin-left: 220px;
    margin-top: 10px;
}
.focus {
    float: left;
    width: 721px;
    height: 455px;
    background-color: purple;
}
.newsflash {
    float: right;
    width: 250px;
    height: 455px;
}
.news {
    height: 165px;
   border: 1px solid #e4e4e4;
}
.news-hd {
    height: 33px;
    line-height: 33px;
    border-bottom: 1px dotted #e4e4e4;
    padding: 0 15px;
}
.news-hd h5 {
    float: left;
    font-size: 14px;
}
.news-hd .more {
    float: right;
}
.news-hd .more::after {
    font-family: 'icomoon';
    content: '\e920';
}
.news-bd {
    padding: 5px 15px 0;
}
.news-bd ul li {
    height: 24px;
    line-height: 24px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}
.lifeservice {
    overflow: hidden;
    height: 209px;
    /* background-color: purple; */
    border: 1px solid #e4e4e4;
    border-top: 0;
}
.lifeservice ul {
    width: 252px;
}
.lifeservice ul li {
    float: left;
    width: 63px;
    height: 71px;
    border-right: 1px solid #e4e4e4;
    border-bottom: 1px solid #e4e4e4;
    text-align: center;
}
.lifeservice ul li i {
    display: inline-block;
    width: 24px;
    height: 28px;
    background-color: pink;
    margin-top: 12px;
    background: url(../assets/images/icons.png) no-repeat -19px -15px;
}
.bargain {
    margin-top: 5px;
}
/* 推荐模块 */
.recom {
    height: 163px;
    background-color: #ebebeb;
    margin-top: 12px;
}
.recom_hd {
    float: left;
    height: 163px;
    width: 205px;
    background-color: #5c5251;
    text-align: center;
    padding-top: 30px;
}
.recom_bd {
    float: left;
}
.recom_bd ul li {
    position: relative;
    float: left;
}
.recom_bd ul li img {
    width: 248px;
    height: 163px;
}
.recom_bd ul li:nth-child(-n+3)::after {
    content: '';
    position: absolute;
    right: 0;
    top: 10px;
    width: 1px;
    height: 145px;
    background-color: #ddd;
}
/* 家用电梯模块 */
.box_hd {
    height: 30px;
    border-bottom: 2px solid #c81623;
}
.box_hd  h3 {
    float: left;
    font-size: 18px;
    color: #c81623;
    font-weight: 400;
}
.tab_list {
    float: right;
    line-height: 30px;
}
.tab_list ul li {
    float: left;
}
.tab_list ul li a {
    margin: 0 15px;
}
.floor .w {
    margin-top: 30px;
}
.box_bd {
    height: 361px;
  
}
.tab_list_item>div {
    float: left;
    height: 361px;
}

.col_210 {
    width: 210px;
    background-color: #f9f9f9;
    text-align: center;
}
.col_210  ul li {
    float: left;
    width: 85px;
    height: 34px;
    border-bottom: 1px solid #ccc;
    text-align: center;
    line-height: 33px;
    margin-right: 10px;
}
.col_210  ul {
    padding-left: 12px;
}
.col_329 {
    width: 329px;
}
.col_221 {
    width: 221px;
    border-right: 1px solid #ccc;
}
.col_219 {
    width: 219px;
}
.bb {
    /* 一般情况下，a如果包含有宽度的盒子，a需要转为块级元素 */
    display: block;
    border-bottom: 1px solid #ccc;
}


</style>