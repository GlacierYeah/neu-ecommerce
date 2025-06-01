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
          <li><a @click="resellerIndex">首页</a></li>
          <li></li>
          <li>
            <a>我的网店</a>
            <div class="dropdown">
              <a @click="resellerStore">网店商品</a>
              <a @click="resellerStoreInfo">信息管理</a>
            </div>
          </li>
          <li></li>
          <li><a class="style_red" @click="resellerWish">我的购物车</a></li>
          <li></li>
          <li><a @click="resellerOrder">我的订单</a></li>
          <li></li>
          <li>
            <a>电子钱包</a>
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
  <!-- header部分 start -->
  <div class="headerwai">
    <div class="w">
      <div class="header">
        <!-- logo模块 -->
        <div class="logo">
          <!-- 对logo进行优化 -->
          <h1>
            <a @click="resellerIndex" title="东软跨境电商借买交易平台"
              >东软跨境电商借买交易平台</a
            >
          </h1>
        </div>
        <div class="shopcar-gwc">我的购物车</div>
      </div>
    </div>
  </div>
  <!-- header部分 end -->

  <div class="w">
    <div class="big-concent">
      <div class="cart-filter-bar"><span>全部商品</span></div>
      <div class="cart-thead">
        <div class="thead-row">
          <div class="t-checkbox fl">
            <input
              type="checkbox"
              class="checkall"
              @change="toggleCheckAll"
            />全选
          </div>
          <div class="t-goods fl"><span>商品</span></div>
          <div class="t-goods fl"><span>借买类型</span></div>
          <div class="t-sum fl"><span>租借单价（/天）</span></div>
          <div class="t-num fl"><span>租借时间</span></div>
          <div class="t-sum fl"><span>购买单价</span></div>
          <div class="t-num fl"><span>数量</span></div>
          <div class="t-sum fl"><span>总价</span></div>
          <div class="t-action fl"><span>操作</span></div>
          <!-- <div class="t-action fl"><span></span></div> -->
        </div>
      </div>
      <div
        class="cart-item-list"
        v-for="cartItem in cartItems"
        :key="cartItem.id"
      >
        <div class="cart-item check-cart-item">
          <div class="p-checkbox fl">
            <input
              type="checkbox"
              class="j-checkbox"
              :checked="cartItem.checked"
              @change="updateProductStatus(cartItem)"
            />
          </div>
          <div class="p-goods fl">
            <div class="p-goods-hd fl">
              <img
                :src="cartItem.picturePic"
                alt=""
                style="height: 80px; width: 80px"
              />
            </div>
            <div class="p-goods-bd fl">{{ cartItem.productName }}</div>
          </div>
          <div class="p-goods-bd fl">{{ cartItem.type }}</div>
          <div class="p-sum fl">￥{{ cartItem.rentPrice }}</div>
          <div class="p-num fl">
            <a
              href="javascript:;"
              class="decrement"
              @click="decrementRentTime(cartItem)"
              >-</a
            >
            <input type="text" :value="cartItem.rentTime" class="itxt" />天
            <a
              href="javascript:;"
              class="increment"
              @click="incrementRentTime(cartItem)"
              >+</a
            >
          </div>
          <div class="p-sum fl">￥{{ cartItem.purchasePrice }}</div>
          <div class="p-num fl">
            <a
              href="javascript:;"
              class="decrement"
              @click="decrementQuantity(cartItem)"
              >-</a
            >
            <input type="text" :value="cartItem.quantity" class="itxt" />件
            <a
              href="javascript:;"
              class="increment"
              @click="incrementQuantity(cartItem)"
              >+</a
            >
          </div>
          <div class="p-sum fl">￥{{ calPrice(cartItem) }}</div>
          <div class="p-action fl">
            <a href="javascript:;" title="删除" @click="removeItem(cartItem)"
              >删除</a
            >
            |
            <a
              href="javascript:;"
              title="保存"
              @click="updateCar(cartItem)"
              >保存</a
            >
          </div>
        </div>
      </div>
      <div class="cart-item-last">
        <div class="p-checkbox fl">
          <input type="checkbox" class="checkall" @click="checkAll" />
          <span> 全选</span>
        </div>
        <div class="remove-checked fl">
          <a href="javascript:;" @click="removeCheckedItems">删除选中的商品</a>
        </div>
        <div class="remove-all fl">
          <a href="javascript:;" @click="clearCart">清理购物车</a>
        </div>
        <div class="toolbar-right fr">
          <div class="amount-sum fl">
            已经选 <span>{{ totalItems }}</span
            >件商品
          </div>
          <div class="price-sum fl">
            总价: <span>{{ formatCurrency(totalPrice) }}</span>
          </div>
          <div class="btn-area fl">
            <a href="javascript:;" @click="openDialog">去结算</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 填写物流信息的弹出框 -->
  <div class="overlay" v-if="dialogVisible">
    <div class="dialog">
      <form @submit.prevent="generateOrder">
        <table class="layui-table table-center">
          <tbody>
            <tr>
              <td><label for="">收货地址：</label></td>
              <td>
                <input type="text" class="inp" v-model="editForm.address" />
              </td>
            </tr>
            <tr>
              <td><label for="">物流类型：</label></td>
              <td>
                <select class="inp" v-model="editForm.logisticsType">
                  <option value="1">特快</option>
                  <option value="2">普快</option>
                  <option value="3">普快</option>
                </select>
              </td>
            </tr>
          </tbody>
        </table>
        <!-- 弹出框修改部分 -->
        <div style="text-align: center; margin-top: 20px">
          <input type="submit" value="创建订单" class="btn" />
          <input type="button" value="取消" class="btn" @click="closeDialog" />
        </div>
      </form>
    </div>
  </div>
  <!-- 创建订单的弹出框 -->
  <div class="overlay" v-if="orderDialogVisible">
    <div class="dialog">
      <form @submit="generateOrder">
        <table class="layui-table table-center">
          <tbody>
            <tr>
              <td><label for="">商品：</label></td>
              <td>{{ deliverForm.status }}</td>
            </tr>
            <tr>
              <td><label for="">租借类型：</label></td>
              <td>{{ deliverForm.logisticsNumber }}</td>
            </tr>
            <tr>
              <td><label for="">商品总价：</label></td>
              <td>{{ deliverForm.logisticsType }}</td>
            </tr>
            <tr>
              <td><label for="">邮费：</label></td>
              <td>{{ deliverForm.postage }}</td>
            </tr>
            <tr>
              <td><label for="">订单总价：</label></td>
              <td>{{ deliverForm.address }}</td>
            </tr>
          </tbody>
        </table>
        <!-- 弹出框修改部分 -->
        <div style="text-align: center; margin-top: 20px">
          <input type="submit" value="去支付" class="btn" />
          <input type="button" value="取消" class="btn" @click="closeDialog" />
        </div>
      </form>
    </div>
  </div>
  <!-- 支付的弹出框 -->
  <div class="overlay" v-if="payDialogVisible">
      <div class="dialog">
        <form @submit.prevent="payOrder">
          <table class="layui-table table-center">
            <tbody>
              <tr>
                <td><label for="">请输入支付密码：</label></td>
                <td>
                  <input
                    type="password"
                    class="inp"
                    v-model="editForm.payCode"
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
</template>

<script setup>
import { ref, computed } from "vue";
import axios from "axios";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();
const cartItems = ref([]);
const dialogVisible = ref(false);
const orderDialogVisible = ref(false);
const payDialogVisible = ref(false);
let data = route.query.data;
const editForm = ref({ address: "", logisticsType: "1" });

const fetchCartItems = async () => {
  try {
    const response = await axios.get("/api/store/findCarProduct", {
      headers: {
        Authorization: data,
      },
    });
    cartItems.value = response.data.data;
  } catch (error) {
    console.error("Error fetching cart items:", error);
  }
};

fetchCartItems();

const calPrice = (cartItem) => {
  if (cartItem.type === "借") {
    return (
      parseInt(cartItem.rentPrice) *
      parseInt(cartItem.rentTime) *
      parseInt(cartItem.quantity)
    );
  } else if (cartItem.type === "买") {
    return parseInt(cartItem.purchasePrice) * parseInt(cartItem.quantity);
  }
};

const updateProductStatus = (car) => {
  axios
    .put("/api/store/updateCarStatus", car, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 1) {
        fetchCartItems();
      }
    })
    .catch((error) => {
      console.error("Error updating product status:", error);
    });
};

const removeItem = (product) => {
  axios
    .delete(`/api/store/deleteCar`, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
      data: product
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 0) {
        alert("删除成功");
        window.location.reload();
      } else {
        alert(response.data.message);
      }
    })
    .catch((error) => {
      console.error(error);
      alert("删除失败");
    });
};

const removeCheckedItems = () => {
  const checkedItems = cartItems.value.filter((item) => item.checked);
  const ids = checkedItems.map((item) => item.id);
  axios
    .delete(`/api/store/deleteCar/${ids.join(",")}`, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 1) {
        fetchCartItems();
      }
    })
    .catch((error) => {
      console.error("Error removing checked items:", error);
    });
};

const clearCart = () => {
  axios
    .delete("/api/store/deleteCar", {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 1) {
        fetchCartItems();
      }
    })
    .catch((error) => {
      console.error("Error clearing cart:", error);
    });
};

const updateCar = (car) => {
  console.log(editForm);
  axios
    .put(`/api/store/updateCar`, car, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 0) {
        alert("保存成功");
        console.log(response);
        window.location.reload();
      } else {
        alert(response.data.message);
      }
    })
    .catch((error) => {
      console.error(error);
      alert("保存失败");
    });
}

const checkAll = (event) => {
  const isChecked = event.target.checked;
  cartItems.value.forEach((item) => {
    item.checked = isChecked;
  });
};

const toggleCheckAll = () => {
  const allChecked = cartItems.value.every((item) => item.checked);
  cartItems.value.forEach((item) => {
    item.checked = !allChecked;
  });
};

const decrementQuantity = (cartItem) => {
  if (cartItem.quantity > 1) {
    cartItem.quantity--;
  }
};

const incrementQuantity = (cartItem) => {
  cartItem.quantity++;
};

const decrementRentTime = (cartItem) => {
  if (cartItem.rentTime > 1) {
    cartItem.rentTime--;
  }
};

const incrementRentTime = (cartItem) => {
  cartItem.rentTime++;
};

const formatCurrency = (value) => {
  return value.toFixed(2);
};

const totalItems = computed(() => {
  return cartItems.value.reduce(
    (total, item) => (item.status ? total + item.quantity : total),
    0
  );
});

const totalPrice = computed(() => {
  return cartItems.value.reduce(
    (total, item) => (item.checked ? total + calPrice(item) : total),
    0
  );
});

const openDialog = () => {
  dialogVisible.value = true;
};

const openOrderDialog = () => {
  orderDialogVisible.value = true;
};



const closeDialog = () => {
  dialogVisible.value = false;
  orderDialogVisible.value = false;
  payDialogVisible.value = false;
};

const generateOrder = () => {
  
  axios
    .post(`/api/store/generateOrder?address=${editForm.value.address}&logisticsType=${editForm.value.logisticsType}`, editForm, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 0) {
        // 成功生成订单后的操作，比如跳转到订单页面
        router.push({ path: "/resellerOrder" , query: { data: data } });
      } else {
        alert(response.data.message);
      }
    })
    .catch((error) => {
      console.error("Error generating order:", error);
    });

};

const updateCartItem = (cartItem) => {
  axios
    .put(`/api/store/updateCart/${cartItem.id}`, cartItem, {
      headers: {
        Authorization: data,
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log(response.data);
      if (response.data.code === 1) {
        fetchCartItems();
      }
    })
    .catch((error) => {
      console.error("Error updating cart item:", error);
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
.w {
  width: 1200px;
  margin: 0 auto;
  clear: both;
}

.fl {
  float: left;
}

.fr {
  float: right;
}

.style_red {
  color: red;
}

.cart-item-list {
  border-bottom: 1px solid #ddd;
  padding: 15px 0;
}

.overlay {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.dialog {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: #fff;
  padding: 20px;
  border-radius: 5px;
}

.layui-table {
  width: 500px;
  margin: 20px auto;
}

.table-center {
  width: 40%;
}

.layui-table label {
  color: #000;
  font-weight: normal;
}
</style>


  
  <style scoped>
.thead-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cart-thead .fl {
  flex: 1;
  text-align: center;
}

.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cart-item .fl {
  flex: 1;
  text-align: center;
}

.checkall {
  margin-right: 10px;
}

.decrement,
.increment {
  cursor: pointer;
  user-select: none;
}
</style>
  
  
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

.turnred {
  font-size: 16px;
  color: #e2231a;
  font-weight: 700;
}

.shopcar-gwc {
  position: absolute;
  top: 34px;
  left: 189px;
  font-size: 20px;
  font-weight: 600;
}

.cart-filter-bar span {
  width: 74px;
  height: 32px;
  font-size: 16px;
  font-weight: 600;
  border-bottom: 1px solid #e2231a;
  padding: 5px; /*向内挤出5px，留出上下左右的缝隙，其实可以只给padding-bottom，在此效果相同*/
  color: #e2231a;
}

.cart-thead {
  margin-top: 4px;
  width: 1200px;
  height: 32px;
  padding: 5px 0; /*从内部撑开上下的5px*/
  line-height: 32px;
  background-color: #f3f3f3;
}

.cart-thead .t-checkbox {
  width: 118px;
  padding-left: 15px;
}

.cart-thead .t-goods {
  width: 400px;
}

.cart-thead .t-price {
  width: 130px;
  text-align: right;
  padding-right: 30px;
}
.cart-thead .t-num {
  width: 150px;
  text-align: center;
}
.cart-thead .t-sum {
  width: 100px;
  text-align: right;
}
.cart-thead .t-action {
  width: 130px;
  text-align: right;
}

.cart-item {
  margin-top: 20px;
}

.cart-item .p-checkbox {
  width: 46px;
  height: 25px;
}

.cart-item .p-goods {
  width: 565px;
  height: 92px;
}

.cart-item .p-price {
  width: 110px;
  height: 18px;
}

.cart-item .p-num {
  width: 170px;
  height: 22px;
}

.cart-item .p-sum {
  width: 145px;
  height: 18px;
}

.cart-item {
  height: 160px;
}

.p-goods-hd {
  height: 87px;
  width: 87px;
  padding: 5px;
  border: 1px solid #ccc;
}

.p-goods-bd {
  padding-left: 10px;
}

.cart-item {
  padding-top: 20px;
  /*发现设置完边框以后，挤在一起了，内部用padding*/
  border-style: solid;
  border-width: 2px 1px 1px;
  border-color: #aaa #f1f1f1 #f1f1f1;
}

.p-checkbox {
  padding-left: 15px;
}

.p-sum {
  font-weight: 600;
}

.p-num .itxt {
  float: left;
  width: 46px;
  border: 1px solid #cacbcb;
  height: 18px;
  text-align: center;
  font-size: 12px;
  font-family: verdana;
  color: #333;
  margin-left: -1px; /*由于左右a与中间的input均有边框，因此中间input左右边框为2px，只想要1px，因此通过margin -1，左移，右移，盖住多余的1px*/
  margin-right: -1px;
  outline: none;
}

.p-num .increment,
.p-num .decrement {
  /*行内转为块，给宽高*/
  /*  display: inline-block; */
  float: left;
  width: 16px;
  height: 18px;
  border: 1px solid #cacbcb;
  text-align: center;
}

.cart-item-last {
  margin-top: 20px;
  margin-bottom: 80px;
  width: 1200px;
  height: 52px;
  line-height: 52px;
  border: 1px solid #f0f0f0;
}

.remove-all {
  font-weight: 600;
}

.remove-checked {
  width: 120px;
  text-align: right;
}

.remove-all {
  width: 150px;
  text-align: center;
}

.amount-sum span {
  color: #e2231a;
  padding: 0 5px;
  font-weight: 800;
}

.price-sum span {
  font-size: 16px;
  color: #e2231a;
  font-weight: 600;
  padding-left: 8px; /*没有宽度随便给padding*/
}

.price-sum {
  margin: 0 15px 0;
}

.btn-area {
  width: 94px;
  height: 52px;
  background-color: #e54346;

  font-size: 18px;
  font-weight: 800;
  text-align: center;
  line-height: 52px;
  font-family: "Microsoft YaHei";
}

.btn-area a {
  color: #fff;
}

.check-cart-item {
  background-color: #fff4e8;
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

.btn {
  width: 200px;
  height: 34px;
  background-color: #c81623;
  font-size: 14px;
  color: #fff;
  margin: 30px 0 0 70px;
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
</style>