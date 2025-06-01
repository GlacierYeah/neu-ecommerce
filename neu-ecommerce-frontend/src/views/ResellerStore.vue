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
            <li><a @click="resellerIndex">首页</a></li>
            <li></li>
            <li>
              <a class="style_red">我的网店</a>
              <div class="dropdown">
                <a @click="resellerStore">网店商品</a>
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
            <li><a @click="resellerInfo">我的信息</a></li>
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
          <a @click="resellerIndex" title="我的网店">我的网店</a>
        </h1>
      </div>
      <!-- search搜索模块 -->
      <div class="search">
        <input type="search" name="" id="" placeholder="尼康" />
        <button>搜索</button>
      </div>

      <!-- 心愿单模块 -->
      <div class="store_info">
        <a @click="brandManagement">网店信息管理</a>
      </div>
    </header>
    <!-- header头部模块制作 end -->
    <!-- nav模块制作 start -->
    <nav class="nav">
      <div class="w">
        <div class="sk_list">
          <ul>
            <li><a href="#">相机</a></li>
            <li><a href="#" style="color: red">手机</a></li>
            <li><a href="#">无人机</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- nav模块制作 end -->

    <!-- 列表页主体部分 -->
    <div class="w sk_container">
      <div class="sk_bd">
        <ul class="clearfix" v-for="product in products" :key="product.id">
          <li>
            <div><img :src="product.productPic" alt="头像" /></div>
            <div style="text-align: center">
              <span>{{ product.name }}</span>
            </div>
            <div>
              <input
                type="button"
                value="加入购物车"
                class="btn"
                @click="openDialog(product)"
              />
            </div>
          </li>
        </ul>
      </div>
    </div>
    <!-- 支付的弹出框 -->
    <div class="overlay" v-if="dialogVisible">
      <div class="dialog">
        <form @submit.prevent="addCar(editForm)">
          <table class="layui-table table-center">
            <tbody>
              <tr>
                <td><label for="">借买类型：</label></td>
                <td>
                  <select
                    class="inp"
                    v-model="editForm.type"
                  >
                    <option value="借">借</option>
                    <option value="买">买</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td><label for="">数量：</label></td>
                <td>
                    <input type="text" class="inp" v-model="editForm.quantity" />
                </td>
              </tr>
            </tbody>
          </table>
          <!-- 弹出框修改部分 -->
          <div style="text-align: center; margin-top: 20px">
            <input type="submit" value="确认" class="btn" />
            <input
              type="button"
              value="取消"
              class="btn"
              @click="closeDialog"
            />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
  
  <script setup>
import axios from "axios";
import { useRouter, useRoute } from "vue-router";
import { ref } from "vue";
const router = useRouter();
const route = useRoute();
let data = route.query.data;
console.log(data);
let editForm = ref([]);
let products = ref([]);
let dialogVisible = ref(false);

axios
  .get("/api/store/findAllProducts", {
    headers: {
      Authorization: data,
    },
  })
  .then((response) => {
    console.log(response.data);
    if (response.data.code === 0) {
      console.log(response);
      console.log(response.data.data);
      products.value = response.data.data;
    } else {
      alert(response.data.message);
    }
  })
  .catch((error) => {
    console.error(error);
  });

const openDialog = (product) => {
  dialogVisible.value = true;
  editForm.value = {
    ...product,
  };
};

const closeDialog = () => {
  dialogVisible.value = false;
};

const addCar = (help) => {
    console.log(help);
  axios
    .post(`/api/store/addCar`, help, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 0) {
        alert("加入购物车成功");
        dialogVisible.value = false;
        console.log(response);
        window.location.reload();
      } else {
        alert(response.data.message);
      }
    })
    .catch((error) => {
      console.error(error);
      alert("加入购物车失败");
    });
}


const resellerIndex = () => {
  router.push({ path: "/resellerIndex", query: { data: data } });
};
const resellerInfo = () => {
  router.push({ path: "/resellerInfo", query: { data: data } });
};
const resellerStore = () => {
  router.push({ path: "/resellerStore", query: { data: data } });
};
const resellerStoreInfo = () => {
  router.push({ path: "/resellerStoreInfo", query: { data: data } });
};
const resellerWish = () => {
  router.push({ path: "/resellerWish", query: { data: data } });
};
const resellerOrder = () => {
  router.push({ path: "/resellerOrder", query: { data: data } });
};
const resellerWalletManagement = () => {
  router.push({ path: "/resellerWalletManagement", query: { data: data } });
};
const resellerTransactionRecords = () => {
  router.push({ path: "/resellerTransactionRecords", query: { data: data } });
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
  font-style: normal;
}
/* 去掉li 的小圆点 */
li {
  list-style: none;
}

img {
  /* border 0 照顾低版本浏览器 如果 图片外面包含了链接会有边框的问题 */
  border: 0;
  /* 取消图片底侧有空白缝隙的问题 */
  vertical-align: middle;
}

button {
  /* 当我们鼠标经过button 按钮的时候，鼠标变成小手 */
  cursor: pointer;
}

a {
  color: #666;
  text-decoration: none;
}

a:hover {
  color: #c81623;
}

button,
input {
  /* "\5B8B\4F53" 就是宋体的意思 这样浏览器兼容性比较好 */
  font-family: Microsoft YaHei, Heiti SC, tahoma, arial, Hiragino Sans GB,
    "\5B8B\4F53", sans-serif;
  /* 默认有灰色边框我们需要手动去掉 */
  border: 0;
  outline: none;
}

body {
  /* CSS3 抗锯齿形 让文字显示的更加清晰 */
  -webkit-font-smoothing: antialiased;
  background-color: #fff;
  font: 12px/1.5 Microsoft YaHei, Heiti SC, tahoma, arial, Hiragino Sans GB,
    "\5B8B\4F53", sans-serif;
  color: #666;
}

.hide,
.none {
  display: none;
}
/* 清除浮动 */
.clearfix:after {
  visibility: hidden;
  clear: both;
  display: block;
  content: ".";
  height: 0;
}

.clearfix {
  zoom: 1;
}

/* 声明字体图标 这里一定要注意路径的变化 */
@font-face {
  font-family: "icomoon";
  src: url("../assets/fonts/icomoon.eot?tomleg");
  src: url("../assets/fonts/icomoon.eot?tomleg#iefix")
      format("embedded-opentype"),
    url("../assets/fonts/icomoon.ttf?tomleg") format("truetype"),
    url("../assets/fonts/icomoon.woff?tomleg") format("woff"),
    url("../assets/fonts/icomoon.svg?tomleg#icomoon") format("svg");
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
  content: "\e91e";
  font-family: "icomoon";
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
  content: "\e93a";
  font-family: "icomoon";
  margin-right: 5px;
  color: #b1191a;
}
.shopcar::after {
  content: "\e920";
  font-family: "icomoon";
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
  font-family: "icomoon";
  content: "\e920";
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
.service_txt p {
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
/* 列表页专有的css */

.nav {
  overflow: hidden;
}

.logo a {
  background: url(../assets/images/brand_logo.png) no-repeat;
}
.sk {
  position: absolute;
  left: 190px;
  top: 40px;
  border-left: 1px solid #c81523;
  padding: 3px 0 0 14px;
}
.sk_list {
  float: left;
}
.sk_list ul li {
  float: left;
}
.sk_list ul li a {
  display: block;
  line-height: 47px;
  padding: 0 30px;
  font-size: 16px;
  font-weight: 700;
  color: #000;
}
.sk_con {
  float: left;
}
.sk_con ul li {
  float: left;
}
.sk_con ul li a {
  display: block;
  line-height: 49px;
  padding: 0 20px;
  font-size: 14px;
}
.sk_con ul li:last-child a::after {
  content: "\e91e";
  font-family: "icomoon";
}
.sk_bd ul li {
  overflow: hidden;
  float: left;
  margin-right: 13px;
  width: 290px;
  height: 460px;
  border: 1px solid transparent;
}
.sk_bd ul li:nth-child(4n) {
  margin-right: 0;
}
.sk_bd ul li:hover {
  border: 1px solid #c81523;
}
.store_info {
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
.store_info::before {
  font-family: "icomoon";
  margin-right: 5px;
  color: #b1191a;
}
.store_info::after {
  content: "\e920";
  font-family: "icomoon";
  margin-left: 10px;
}
.btn {
  font-size: 18px;
  padding: 8px 16px;
  margin-top: 10px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}
/* 添加弹出框样式 */
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.dialog {
  background: white;
  padding: 20px;
  border-radius: 5px;
  width: 600px;
}

.dialog table {
  width: 100%;
}
/* 将两个按钮放在同一行 */
.dialog .btn {
  display: inline-block;
  margin: 0 10px;
}

.input-file {
  /* 样式使文件输入视觉上更吸引人 */
  border: 1px solid #ccc;
  padding: 6px 12px;
  width: 100%;
  box-sizing: border-box;
}
.btn {
  width: 200px;
  height: 34px;
  background-color: #c81623;
  font-size: 14px;
  color: #fff;
  margin: 30px 0 0 70px;
}
</style>