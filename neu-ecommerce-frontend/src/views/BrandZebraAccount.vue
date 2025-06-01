<template>
  <!-- 快捷导航模块 start -->
  <section class="shortcut">
    <div class="w">
      <div class="fl">
        <ul>
          <li>东软跨境电商借买交易平台欢迎您！&nbsp;</li>
        </ul>
      </div>
      <div class="fr">
        <ul>
          <li>
            <a>我的品牌</a>
            <div class="dropdown">
              <a @click="brandProductList">品牌商品</a>
              <a @click="brandProductManagement">品牌商品管理</a>
              <a @click="brandManagement">品牌信息管理</a>
            </div>
          </li>
          <li></li>
          <li><a @click="brandOrder">我的订单</a></li>
          <li></li>
          <li>
            <a>电子钱包</a>
            <div class="dropdown">
              <a @click="brandWalletManagement">钱包管理</a>
              <a @click="brandTransactionRecords">交易记录</a>
            </div>
          </li>
          <li></li>
          <li><a class="style_red" @click="brandZebraAccount">斑马账号</a></li>
          <li></li>
          <li><a @click="brandInfo">我的信息</a></li>
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
          <img src="../assets/images/logo.png" alt=""
        /></a>
      </div>
    </header>
    <div class="registerarea">
      <h3>斑马账号管理</h3>
      <div class="store_table">
        <table
          class="layui-table table-center"
          style="margin-top: 15px; margin-left: 200px"
        >
          <colgroup>
            <col width="150" />
            <col width="500" />
            <col width="500" />
          </colgroup>
          <thead>
            <template v-if="!zebraAccount">
              <a
                class="newbtn layui-btn layui-btn-xs add-product-btn"
                lay-event="edit"
                @click="openAddDialog"
                >注册</a
              >
            </template>
          </thead>
          <tbody v-if="zebraAccount">
            <tr>
              <td><label for="">用户名：</label></td>
              <td>
                <span>{{ zebraAccount.accountName }}</span>
              </td>
            </tr>
            <tr>
              <td><label for="">邮箱：</label></td>
              <td>
                <span>{{ zebraAccount.email }}</span>
              </td>
            </tr>
            <tr>
              <td><label for="">手机号：</label></td>
              <td>
                <span>{{ zebraAccount.phoneNumber }}</span>
              </td>
            </tr>
            <tr>
              <th colspan="2" class="register-cell">
                <div class="btn-container">
                  <input
                    type="submit"
                    value="修改基本信息"
                    class="btn"
                    style="background-color: darkgoldenrod"
                    @click="openDialog"
                  />
                  <input
                    type="submit"
                    value="修改密码"
                    class="btn"
                    style="background-color: darkblue"
                    @click="openPwdDialog"
                  />
                </div>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 注册斑马账号的弹出框 -->
    <div class="overlay" v-if="addDialogVisible">
      <div class="dialog">
        <form @submit.prevent="registerZebra">
          <table class="layui-table table-center">
            <tbody>
              <tr>
                <td><label for="">用户名：</label></td>
                <td>
                  <input
                    type="text"
                    class="inp"
                    v-model="addForm.accountName"
                  />
                </td>
              </tr>
              <tr>
                <td><label for="">密码：</label></td>
                <td>
                  <input
                    type="password"
                    class="inp"
                    v-model="addForm.password"
                  />
                </td>
              </tr>
              <tr>
                <td><label for="">邮箱：</label></td>
                <td>
                  <input type="text" class="inp" v-model="addForm.email" />
                </td>
              </tr>
              <tr>
                <td><label for="">手机号：</label></td>
                <td>
                  <input
                    type="text"
                    class="inp"
                    v-model="addForm.phoneNumber"
                  />
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

    <!-- 修改基本信息的弹出框 -->
    <div class="overlay" v-if="dialogVisible">
      <div class="dialog">
        <form>
          <table class="layui-table table-center">
            <tbody>
              <tr>
                <td><label for="">用户名：</label></td>
                <td>
                  <input
                    type="text"
                    class="inp"
                    v-model="editForm.accountName"
                  />
                </td>
              </tr>
              <tr>
                <td><label for="">邮箱：</label></td>
                <td>
                  <input type="text" class="inp" v-model="editForm.email" />
                </td>
              </tr>
              <tr>
                <td><label for="">手机号：</label></td>
                <td>
                  <input
                    type="text"
                    class="inp"
                    v-model="editForm.phoneNumber"
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <!-- 弹出框修改部分 -->
          <div style="text-align: center; margin-top: 20px">
            <input type="submit" value="确认" class="btn" @click="update" />
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

    <!-- 修改密码的弹出框 -->
    <div class="overlay" v-if="pwdDialogVisible">
      <div class="dialog">
        <form @submit.prevent="updatePwd">
          <table class="layui-table table-center">
            <tbody>
              <tr>
                <td><label for="">原密码：</label></td>
                <td>
                  <input
                    type="password"
                    class="inp"
                    v-model="pwdForm.oldPassword"
                  />
                </td>
              </tr>
              <tr>
                <td><label for="">新密码：</label></td>
                <td>
                  <input
                    type="password"
                    class="inp"
                    v-model="pwdForm.newPassword"
                  />
                </td>
              </tr>
              <tr>
                <td><label for="">确认密码：</label></td>
                <td>
                  <input
                    type="password"
                    class="inp"
                    v-model="pwdForm.rePassword"
                  />
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
import { useRouter, useRoute } from "vue-router";
import { ref } from "vue";
import axios from "axios";

const router = useRouter();
const route = useRoute();

const data = route.query.data;

let dialogVisible = ref(false);
let upDialogVisible = ref(false);
let addDialogVisible = ref(false);
let pwdDialogVisible = ref(false);
let zebraAccount = ref([]);
let editForm = ref({});
const addForm = ref({
  accountName: "",
  password: "",
  email: "",
  phoneNumber: "",
});
let pwdForm = ref({
  oldPassword: "",
  newPassword: "",
  rePassword: "",
});

const openAddDialog = () => {
  addDialogVisible.value = true;
};

const openDialog = () => {
  dialogVisible.value = true;
};

const openPwdDialog = () => {
  pwdDialogVisible.value = true;
};

const closeDialog = () => {
  upDialogVisible.value = false;
  addDialogVisible.value = false;
  pwdDialogVisible.value = false;
  dialogVisible.value = false;
};

axios
  .get("/api/brand/findZebraByBrandId", {
    headers: {
      Authorization: data,
    },
  })
  .then((response) => {
    console.log(response.data);
    if (response.data.code === 0) {
      console.log(response.data);
      console.log(response);
      zebraAccount.value = response.data.data;
      editForm.value = {
        id: zebraAccount.value.id,
        accountName: zebraAccount.value.accountName,
        email: zebraAccount.value.email,
        phoneNumber: zebraAccount.value.phoneNumber,
      };
    } else {
      alert(response.data.message);
    }
  })
  .catch((error) => {
    console.error(error);
  });

const registerZebra = () => {
  console.log(addForm.value);
  axios
    .post("/api/brand/registerZebra", addForm.value, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 0) {
        alert("添加成功");
        addDialogVisible.value = false;
        window.location.reload();
      } else {
        alert(response.data.message);
      }
    })
    .catch((error) => {
      console.error(error);
      alert("添加失败");
    });
};

const update = () => {
  console.log(editForm.value);
  axios
    .put("/api/brand/updateZebraAccount", editForm.value, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 0) {
        
        console.log(response.data);
      if (response.data.code === 0) {
        alert("修改成功");
        addDialogVisible.value = false;
        console.log(editForm.value);
        window.location.reload();
      } else {
        alert(response.data.message);
      }
      } else {
        alert(response.data.message);
      }
    })
    .catch((error) => {
      console.error(error);
      alert("修改失败");
    });
};

const updatePwd = () => {
  const pwdFormValue = {
    old_pwd: pwdForm.value.oldPassword,
    new_pwd: pwdForm.value.newPassword,
    re_pwd: pwdForm.value.rePassword,
  };
  console.log("Sending data:", pwdFormValue); // 打印发送的数据以供调试

  axios
    .put("/api/wallet/updateZebraPwd", pwdFormValue, {
      headers: {
        "Content-Type": "application/json",
        Authorization: data,
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 0) {
        alert("更新成功");
        // wallet.value.password = pwdForm.value.newPassword; 
        pwdDialogVisible.value = false;
      } else {
        alert(response.data.message);
      }
    })
    .catch((error) => {
      console.error("更新失败:", error);
      alert("更新失败");
    });
};

const brandProductList = () => {
  router.push({ path: "/brandProductList", query: { data: data } });
};
const brandProductManagement = () => {
  router.push({ path: "/brandProductManagement", query: { data: data } });
};
const brandManagement = () => {
  router.push({ path: "/brandManagement", query: { data: data } });
};
const brandOrder = () => {
  router.push({ path: "/brandOrder", query: { data: data } });
};
const brandWalletManagement = () => {
  router.push({ path: "/brandWalletManagement", query: { data: data } });
};
const brandTransactionRecords = () => {
  router.push({ path: "/brandTransactionRecords", query: { data: data } });
};
const brandZebraAccount = () => {
  router.push({ path: "/brandZebraAccount", query: { data: data } });
};
const brandInfo = () => {
  router.push({ path: "/brandInfo", query: { data: data } });
};
</script>

<style scoped>
@import url("https://www.layuicdn.com/layui-v2.5.7/css/layui.css");
@import url("//unpkg.com/layui@2.9.13/dist/css/layui.css");
@import url("https://cdn.jsdelivr.net/npm/layui-v2.5.6/dist/layui.js");
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
  background: url(../assets/images/brand_logo.png) no-repeat;
}
.login {
  float: right;
  font-size: 14px;
}
.login a {
  color: #c81523;
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

/* 添加商品按钮样式 */
.add-product-btn {
  background-color: rgb(195, 37, 53);
  color: white; /* 设置字体颜色为白色以增加对比度 */
  padding: 10px 20px; /* 增加内边距以放大按钮 */
  margin-bottom: 20px; /* 增加与表格的间距 */
  border: none; /* 移除边框 */
  border-radius: 5px; /* 添加圆角 */
  cursor: pointer; /* 鼠标悬停时显示指针 */
}

/* 表格按钮样式 */
.table-btn {
  padding: 5px 15px; /* 增加内边距以放大按钮 */
  margin-right: 5px; /* 增加按钮之间的间距 */
  border: none; /* 移除边框 */
  border-radius: 5px; /* 添加圆角 */
  cursor: pointer; /* 鼠标悬停时显示指针 */
}

/* 编辑按钮样式 */
.edit-btn {
  background-color: #4caf50; /* 设置背景颜色 */
  color: white; /* 设置字体颜色为白色 */
}

/* 删除按钮样式 */
.delete-btn {
  background-color: #f44336; /* 设置背景颜色 */
  color: white; /* 设置字体颜色为白色 */
}

/* 下架按钮样式 */
.status-btn {
  background-color: #ff9800; /* 设置背景颜色 */
  color: white; /* 设置字体颜色为白色 */
}

.newbtn {
  display: inline-block; /* 使元素以块级元素显示，但仍然保持行内元素的特性 */
  text-align: center; /* 水平居中文本 */
  line-height: normal; /* 设置合适的行高以垂直居中文本 */
  vertical-align: middle; /* 垂直居中 */
  padding: 10px 20px; /* 根据需要调整内边距 */
  font-size: 14px;
  height: 34px;
}
.layui-table {
  margin: 0 auto; /* 居中显示表格 */
  width: 80%; /* 设置表格宽度 */
}

.wallet_operations {
  text-align: center; /* 按钮居中 */
  margin-bottom: 20px; /* 按钮下方留出一些间距 */
}

.operations button {
  margin: 0 5px; /* 按钮之间的间距 */
  font-size: 18px; /* 按钮字体大小 */
  padding: 10px 16px; /* 按钮内边距 */
}

.register_button {
  margin-bottom: 10px; /* 注册按钮下方留出一些间距 */
}

.layui-table th {
  text-align: center; /* 表头文字居中 */
  font-size: 20px; /* 表头字体大小 */
}

.layui-table td {
  text-align: center; /* 表格内容文字居中 */
  font-size: 18px; /* 表格内容字体大小 */
}

.register-cell {
  text-align: center; /* 注册单元格文字居中 */
}

.btn-container {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.btn {
  font-size: 18px;
  padding: 8px 16px;
}
</style>