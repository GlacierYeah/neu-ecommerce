<template>
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
  <div class="w">
    <header>
      <div class="logo">
        <a @click="brandProductList">
          <img src="../assets/images/brand_logo.png" alt=""
        /></a>
      </div>
    </header>
    <div class="registerarea">
      <h3>交易记录</h3>
      <div class="search_box">
        <input
          type="text"
          class="layui-input"
          placeholder="搜索交易时间等"
          id="search-input"
          style="width: 300px; display: inline-block"
          v-model = "searchTime"
        />
        <button
          class="layui-btn layui-btn-normal"
          id="search-btn"
          style="background-color: rgb(172, 31, 31)"
          @click="search(searchTime)"
        >
          搜索
        </button>
      </div>
      <div class="store_table">
        <table class="layui-table">
          <colgroup>
            <col width="150" />
            <col width="150" />
            <col width="150" />
            <col width="150" />
            <col width="200" />
            <col width="150" />
          </colgroup>
          <thead>
            <tr>
              <th>交易ID</th>
              <th>钱包ID</th>
              <th>交易类型</th>
              <th>金额</th>
              <th>时间戳</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody id="transaction-table-body">
            <tr v-for=" transactionRecord in transactionRecords " :key="transactionRecord.id">
              <td>{{ transactionRecord.id }}</td>
              <td>{{ transactionRecord.walletId }}</td>
              <td>{{ transactionRecord.tradeType }}</td>
              <td>{{ transactionRecord.amount }}</td>
              <td>{{ transactionRecord.transactionTime }}</td>
              <td>
                <button class="layui-btn layui-btn-xs">详情</button>
              </td>
            </tr>
            
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref } from "vue";
import axios from "axios";

const router = useRouter();
const route = useRoute();

const data = route.query.data;

let transactionRecords = ref([]);
let searchTime = ref();

axios
  .get("/api/transaction/findTransaction", {
    headers: {
      Authorization: data,
    },
  })
  .then((response) => {
    console.log(response.data);
    if (response.data.code === 0) {
      console.log(response.data.data);
      transactionRecords.value = response.data.data;
      console.log(products);
    } else {
      alert(response.data.message);
    }
  })
  .catch((error) => {
    console.error(error);
  });

const search = (searchTime) => {
  console.log("Search time:", searchTime);
  axios
    .get(
      `/api/transaction/findTransactionsByTime?time=${encodeURIComponent(
        searchTime
      )}`,
      {
        headers: {
          Authorization: data, // 确保将 yourToken 替换为实际的令牌
        },
      }
    )
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 0) {
        console.log("Response data:", response.data);
        transactionRecords.value = response.data.data;
      } else {
        alert(response.data.message);
      }
    })
    .catch((error) => {
      console.error("Error:", error);
    });
};

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
@import url("https://www.layuicdn.com/layui-v2.5.7/css/layui.css");
@import url("//unpkg.com/layui@2.9.13/dist/css/layui.css");
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
.w {
  width: 1200px;
  margin: 0 auto;
}
header {
  height: 84px;
  border-bottom: 2px solid #c81523;
}
.logo {
  padding-top: 25px;
}
.registerarea {
  height: 522px;
  border: 1px solid #ccc;
  margin-top: 20px;
}
.registerarea h3 {
  height: 42px;
  border-bottom: 1px solid #ccc;
  background-color: #ececec;
  line-height: 42px;
  padding: 0 10px;
  font-size: 18px;
  font-weight: 400;
}
.logo a {
  background: url(../assets/images/store_logo.png) no-repeat;
}
.login {
  float: right;
  font-size: 14px;
}
.login a {
  color: #c81523;
}
.layui-table thead th,
.layui-table tbody td {
  text-align: center;
}
.store_form {
  width: 600px;

  margin: 50px auto 0;
}
.store_form ul li {
  margin-bottom: 20px;
}
.store_form ul li label {
  display: inline-block;
  width: 88px;
  text-align: right;
}
.store_form ul li .inp {
  width: 242px;
  height: 37px;
  border: 1px solid #ccc;
}
.error {
  color: #c81523;
}
.error_icon,
.success_icon {
  display: inline-block;
  vertical-align: middle;
  width: 20px;
  height: 20px;
  background: url(../assets/images/error.png) no-repeat;
  margin-top: -2px;
}
.success {
  color: green;
}
.success_icon {
  background: url(../assets/images/success.png) no-repeat;
}
.safe {
  padding-left: 170px;
}
.safe em {
  padding: 0 12px;
  color: #fff;
}
.ruo {
  background-color: #de1111;
}
.zhong {
  background-color: #40b83f;
}

.qiang {
  background-color: #f79100;
}
.agree {
  padding-left: 95px;
}
.agree input {
  vertical-align: middle;
}
.agree a {
  color: #1ba1e6;
}
.btn {
  width: 200px;
  height: 34px;
  background-color: #c81623;
  font-size: 14px;
  color: #fff;
  margin: 30px 0 0 70px;
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
</style>