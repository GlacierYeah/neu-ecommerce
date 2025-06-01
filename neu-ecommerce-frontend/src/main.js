import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import layui from '../node_modules/layui';
import 'jquery';


createApp(App).use(router).mount('#app')
