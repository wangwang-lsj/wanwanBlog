import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/display.css';

import App from './App.vue'
import router from './router'
import './assets/css/gloable.css'
import request from "@/utils/request"
import store from "@/store";
import validator from 'validator';
import $ from  'jquery'

import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'

import '@/./assets/iconfont/iconfont.css'
import VueLazyLoad from 'vue-lazyload'

import VueParticles from '@/lib/vue-particles'

Vue.config.devtools
Vue.config.productionTip = false
Vue.use(ElementUI, {size:"small"});
Vue.prototype.request=request
// Vue.use(Viewer)
// Vue.use(VueParticles)
Viewer.setDefaults({
  Options: { 'inline': true, 'button': true, 'navbar': true, 'title': true, 'toolbar': true, 'tooltip': true, 'movable': true, 'zoomable': true, 'rotatable': true, 'scalable': true, 'transition': true, 'fullscreen': true, 'keyboard': true, 'url': 'data-source' }
})
Vue.prototype.$validator = validator
// Vue.use(VueLazyLoad, {
//   // 可选配置项，可省略
//   // error: require('./assets/img/loaderror.jpg'), // 加载失败时显示的图片
//   // loading: require('./assets/img/loading.jpg'), // 加载中时显示的图片
//   preLoad: 1.3, // 预加载高度的比例
//   attempt: 3 // 尝试加载次数
// })

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')


