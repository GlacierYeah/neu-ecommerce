import { createRouter, createWebHashHistory } from 'vue-router'

import Register from '../views/Register'
import Login from '../views/Login.vue'
import BrandProductList from '../views/BrandProductList.vue'
import ManagerUser from '../views/ManagerUser.vue'
import ResellerIndex from '../views/ResellerIndex.vue'
import ManagerMenu from '../views/ManagerMenu.vue'
import ManagerInfo from '../views/ManagerInfo.vue'
import ResellerInfo from '../views/ResellerInfo.vue'
import BrandProductManagement from '../views/BrandProductManagement.vue'
import BrandManagement from '../views/BrandManagement.vue'
import BrandOrder from '../views/BrandOrder.vue'
import BrandWalletManagement from '../views/BrandWalletManagement.vue'
import BrandTransactionRecords from '../views/BrandTransactionRecords.vue'
import BrandZebraAccount from '../views/BrandZebraAccount.vue'
import BrandInfo from '../views/BrandInfo.vue'
import ResellerOrder from '../views/ResellerOrder.vue'
import ResellerStore from '../views/ResellerStore.vue'
import ResellerStoreInfo from '../views/ResellerStoreInfo.vue'
import ResellerWalletManagement from '../views/ResellerWalletManagement.vue'
import ResellerTransactionRecords from '../views/ResellerTransactionRecords.vue'
import ResellerWish from '../views/ResellerWish.vue'
import ResellerProductList from '../views/ResellerProductList.vue'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/brandProductList',
    name: 'BrandProductList',
    component: BrandProductList
  },
  {
    path: '/managerUser',
    name: 'ManagerUser',
    component: ManagerUser
  },
  {
    path: '/resellerIndex',
    name: 'ResellerIndex',
    component: ResellerIndex
  },
  {
    path: '/managerMenu',
    name: 'ManagerMenu',
    component: ManagerMenu
  },
  {
    path: '/managerInfo',
    name: 'ManagerInfo',
    component: ManagerInfo
  },
  {
    path: '/resellerInfo',
    name: 'ResellerInfo',
    component: ResellerInfo
  },
  {
    path: '/brandProductManagement',
    name: 'BrandProductManagement',
    component: BrandProductManagement
  },
  {
    path: '/brandManagement',
    name: 'BrandManagement',
    component: BrandManagement
  },
  {
    path: '/brandOrder',
    name: 'BrandOrder',
    component: BrandOrder
  },
  {
    path: '/brandWalletManagement',
    name: 'BrandWalletManagement',
    component: BrandWalletManagement
  },
  {
    path: '/brandTransactionRecords',
    name: 'BrandTransactionRecords',
    component: BrandTransactionRecords
  },
  {
    path: '/brandZebraAccount',
    name: 'BrandZebraAccount',
    component: BrandZebraAccount
  },
  {
    path: '/brandInfo',
    name: 'BrandInfo',
    component: BrandInfo
  },
  {
    path: '/resellerOrder',
    name: 'ResellerOrder',
    component: ResellerOrder
  },
  {
    path: '/resellerStore',
    name: 'ResellerStore',
    component: ResellerStore
  },
  {
    path: '/resellerStoreInfo',
    name: 'ResellerStoreInfo',
    component: ResellerStoreInfo
  },
  {
    path: '/resellerWalletManagement',
    name: 'ResellerWalletManagement',
    component: ResellerWalletManagement
  },
  {
    path: '/resellerTransactionRecords',
    name: 'ResellerTransactionRecords',
    component: ResellerTransactionRecords
  },
  {
    path: '/resellerWish',
    name: 'ResellerWish',
    component: ResellerWish
  },
  {
    path: '/resellerProductList',
    name: 'ResellerProductList',
    component: ResellerProductList
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
