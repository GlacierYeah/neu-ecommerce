<template>
  <div class="w">
    <header>
      <div class="logo">
        <a> <img src="../assets/images/logo.png" alt="" /></a>
      </div>
    </header>
    <div class="registerarea">
      <h3>
        登录
        <div class="login">没有账号，去<a @click="register">注册</a></div>
      </h3>
      <div class="reg_form">
        <form @submit.prevent="login">
          <ul>
            <li>
              <label for="identity">身份：</label>
              <select id="identity" class="inp" v-model="userLoginForm.role">
                <option value="1">管理员</option>
                <option value="3">品牌商</option>
                <option value="2">借卖方</option>
              </select>
            </li>
            <li>
              <label for="">账户名：</label>
              <input type="text" class="inp" v-model="userLoginForm.userName" />
              <span class="error" v-if="userNameError()">
                <i class="error_icon"></i> 账户名不能为空
              </span>
            </li>
            <li>
              <label for="">登录密码：</label>
              <input type="password" class="inp" v-model="userLoginForm.password" />
              <span class="error" v-if="userPasswordError()">
                <i class="error_icon"></i> 密码不能为空
              </span>
            </li>
            <li>
              <label for="captcha">验证码：</label>
              <img :src="captchaUrl" alt="验证码" id="captcha_image" @click="refreshCaptcha" style="cursor: pointer; width: 140px; height: 40px" />
            </li>
            <li>
              <label for="captcha_input">输入验证码：</label>
              <input type="text" id="captcha_input" class="inp" v-model="userLoginForm.captcha" />
              <span class="error" v-if="userCaptchaError()">
                <i class="error_icon"></i> 验证码不正确，请重新输入
              </span>
            </li>
            <li>
              <input type="submit" value="登录" class="btn" />
            </li>
          </ul>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";
import qs from "qs";

const router = useRouter();
const userLoginForm = ref({
  userName: "",
  password: "",
  role: "",
  captcha: "",
});
const captchaUrl = ref("");
const captchaKey = ref("");

const userNameError = () => !userLoginForm.value.userName;
const userPasswordError = () => !userLoginForm.value.password;
const userCaptchaError = () => !userLoginForm.value.captcha;

const generateFixedCaptcha = () => {
  const chars = "0123456789";
  let fixedCaptchaKey = "";
  for (let i = 0; i < 5; i++) {
    fixedCaptchaKey += chars.charAt(Math.floor(Math.random() * chars.length));
  }
  return fixedCaptchaKey;
};

const refreshCaptcha = async () => {
  const fixedCaptchaKey = generateFixedCaptcha();
  captchaKey.value = fixedCaptchaKey; // 存储前端生成的验证码 key
  captchaUrl.value = `/api/captcha?key=${fixedCaptchaKey}`;
};

const login = async () => {
  if (userNameError() || userPasswordError() || userCaptchaError()) {
    alert("请填写完整信息");
    return;
  }

  const requestData = {
    username: userLoginForm.value.userName,
    password: userLoginForm.value.password,
    verificationCode: userLoginForm.value.captcha,
    key: captchaKey.value,
    role: userLoginForm.value.role,
  };

  console.log('Request data:', requestData);

  if(userLoginForm.value.role == 1) {
    axios
      .post("/api/admins/login", qs.stringify(requestData), {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
      .then((response) => {
        console.log(response.data);
        if (response.data.code === 0) {
          alert('登录成功');
          router.push({ path: '/managerUser',query:{data:response.data.data} });
        } else {
          alert(response.data.message);
        }
      })
      .catch((error) => {
        console.error(error);
      });
  }else if (userLoginForm.value.role == 2) {
    axios
      .post("/api/merchants/login", qs.stringify(requestData), {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
      .then((response) => {
        console.log(response.data);
        if (response.data.code === 0) {
          alert('登录成功');
          router.push({path: '/resellerIndex',query:{data:response.data.data}});
        } else {
          alert(response.data.message);
        }
      })
      .catch((error) => {
        console.error(error);
      });
  } else {
    axios
      .post("/api/brands/login", qs.stringify(requestData), {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      })
      .then((response) => {
        console.log(response.data);
        if (response.data.code === 0) {
          alert('登录成功');
          router.push({path: '/brandProductList',query:{data:response.data.data}});
        } else {
          alert(response.data.message);
        }
      })
      .catch((error) => {
        console.error(error);
      });
  }


};

const register = () => {
  router.push({ path: '/register' });
};

onMounted(() => {
  refreshCaptcha();
});
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

.w {
  width: 1200px;
  margin: 0 auto;
}
header {
  height: 84px;
  border-bottom: 2px solid #c81523;
}
.logo {
  padding-top: 18px;
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
.login {
  float: right;
  font-size: 14px;
}
.login a {
  color: #c81523;
}
.reg_form {
  width: 600px;

  margin: 50px auto 0;
}
.reg_form ul li {
  margin-bottom: 20px;
}
.reg_form ul li label {
  display: inline-block;
  width: 88px;
  text-align: right;
}
.reg_form ul li .inp {
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