import Vue from 'vue'
import Vuex from 'vuex'
import router, {resetRouter} from "@/router";
import debounce from "lodash/debounce";
Vue.use(Vuex)

let handleResize;
const store = new Vuex.Store({
    state:{
        currentPathName: '',
        windowWidth: 0,
    },
    mutations: {
        setPath(state){
            state.currentPathName = localStorage.getItem("currentPathName")
        },
        SET_WINDOW_WIDTH(state, width) {
            state.windowWidth = width;
        },
        logout(){
            localStorage.removeItem("user")
            localStorage.removeItem("menus")
            router.push("/login")

            // 重置路由
            resetRouter()
        },

    },
    actions:{
        createWindowSizeListener({ commit }) {
            // 使用防抖处理resize事件
            // handleResize = debounce(() => {
            //     commit('SET_WINDOW_WIDTH', window.innerWidth);
            // }, 300);
            // 不使用防抖
            handleResize = ()=>{commit('SET_WINDOW_WIDTH', window.innerWidth)};
            window.addEventListener('resize', handleResize);
            // 初始化时触发一次，避免页面初次加载时宽度为0
            handleResize();
        },
        destroyWindowSizeListener({ commit }) {
            window.removeEventListener('resize', handleResize);
        },
    }
})
export default store