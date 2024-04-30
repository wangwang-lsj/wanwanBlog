<template>
  <div>
    <!--头部-->
    <div class="Header">
      <div style="height: 60px;line-height: 60px;display: flex;">
        <!--LOGO-->
        <div class="HeaderLeft" v-if="!isPhone">
          <div class="LOGO" @click="$router.push('/home')">
            Wanwan
          </div>
        </div>
        <!--电脑导航栏(Element-ui版)-->
        <!--<div style="flex:3" v-if="!isPhone">-->
        <!--  <el-menu class="el-menu-demo" mode="horizontal" router style="display: flex;justify-content: center">-->
        <!--    <el-menu-item index="/home">主页</el-menu-item>-->
        <!--    <el-menu-item index="/article">文章</el-menu-item>-->
        <!--    <el-menu-item index="/guestbook">留言板</el-menu-item>-->
        <!--    <el-menu-item index="/About">关于</el-menu-item>-->
        <!--  </el-menu>-->
        <!--</div>-->
        <!--电脑导航栏-->
        <!--<div style="flex: 3;display: flex;justify-content: center" v-if="!isPhone" class="HeaderMiddle NavBar">-->
        <!--  <div class="NavBarItem" @click="$router.push('/home')">主页</div>-->
        <!--  <div class="NavBarItem" @click="$router.push('/article')">文章</div>-->
        <!--  <div class="NavBarItem" @click="$router.push('/guestbook')">留言板</div>-->
        <!--  <div class="NavBarItem" @click="$router.push('/about')">关于</div>-->
        <!--</div>-->
        <!--电脑导航栏-->
        <div style="flex: 3;display: flex;justify-content: center" v-if="!isPhone" class="HeaderMiddle PCNavBar">
          <router-link to="/home" class="NavBarItem">主页</router-link>
          <router-link to="/article" class="NavBarItem">文章</router-link>
          <router-link to="/guestbook" class="NavBarItem">留言板</router-link>
          <router-link to="/about" class="NavBarItem">关于</router-link>
        </div>
        <!--手机导航栏-->
        <div v-if="isPhone" style="flex: 1">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              {{$route.name }}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown" class="PhoneNavBar">
              <el-dropdown-item><i class="iconfont icon-zhuye"></i><router-link to="/home">主页</router-link></el-dropdown-item>
              <el-dropdown-item><i class="iconfont icon-wenzhang"></i><router-link to="/article">文章</router-link></el-dropdown-item>
              <el-dropdown-item><i class="iconfont icon-liuyanban"></i><router-link to="/guestbook">留言板</router-link></el-dropdown-item>
              <el-dropdown-item><i class="iconfont icon-guanyu"></i><router-link to="/about">关于</router-link></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div class="HeaderLeft" v-if="isPhone" style="padding: 0">
          <div class="LOGO" @click="$router.push('/home')">
            Wanwan
          </div>
        </div>
        <div style="text-align: right;flex: 1" class="HeaderRight">
          <!--头像-->
          <el-dropdown v-if="JSON.stringify(user)!=='{}'" style="width: 100px;height: 100% ;cursor: pointer;" trigger="click">
            <div style="display: inline-block; width: 100%; height: 60px">
              <div style="height: 40px;width: 40px;margin: 0 auto">
                <img :src="user.avatarUrl" alt=""
                     style="width: 40px; border-radius: 50%;position: relative;">
              </div>
              <div style="width: 100%;height: 20px; line-height: 20px;text-align: center">
                <span>{{ user.nickname }}</span><i class="el-icon-arrow-down"></i>
              </div>
            </div>
            <el-dropdown-menu v-slot="dropdown" style="width: 100px; text-align: center;">
              <el-dropdown-item style="font-size: 14px; padding: 5px 0" v-if="user.role==='ADMIN'">
                <router-link to="/manage/backhome">前往后台</router-link>
              </el-dropdown-item>
              <!--<el-dropdown-item style="font-size: 14px; padding: 5px 0">-->
              <!--  <router-link to="/person">个人中心</router-link>-->
              <!--</el-dropdown-item>-->
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <span @click="logout()" style="text-decoration: none">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <div v-else style="width: 100px;height: 100% ;cursor: pointer; text-align: center;float: right">
            <router-link to="/login">登陆</router-link>
          </div>
        </div>
      </div>
    </div>
    <!--主体内容-->
    <div style="min-height: calc(100vh - 100px);" class="Main">
      <div style="height: 60px;"></div>
      <keep-alive>
        <router-view v-if="$route.meta.keepAlive"></router-view>
      </keep-alive>
      <!-- 不需要缓存的视图组件 -->
      <router-view v-if="!$route.meta.keepAlive"></router-view>
    </div>
    <!--站脚-->
    <div style="" class="Footer">
      备案号：<a href="https://beian.miit.gov.cn/" target="_blank" style="">湘ICP备2024049847号-1</a>
    </div>
  </div>
  <!--<el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>-->
</template>

<script>
import user from "@/views/manage/User.vue";
import debounce from 'lodash/debounce';

export default {
  name: "Front",
  components:{
  },
  data(){
    return{
      user: localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
      currentPathName: localStorage.getItem("currentPathName")?localStorage.getItem("currentPathName"):'',
      showComponent: true,
      innerWidth: 0,
      innerHeight: 0,
    }
  },
  computed:{
    isPhone(){
      return this.innerWidth<=600
    },
    // 使用防抖函数包装 getBrowSize 方法
    debouncedGetBrowSize() {
      return debounce(this.getBrowSize, 500);
    },
  },
  created() {
  },
  mounted() {
    window.addEventListener('resize', this.getBrowSize);
    this.getBrowSize();
  },
  destroyed() {
    window.removeEventListener('resize', this.getBrowSize);
  },
  // 删除滚动监听器，建议使用beforeRouteLeave，因为destroyed()钩子在路由跳转时不会触发
  beforeRouteLeave(to,from,next) {
    window.removeEventListener("resize", this.getBrowSize);//销毁滚动事件
    next()
  },
  methods:{
    logout(){
      this.$store.commit("logout")
      this.$message.success("退出成功")
    },
    getBrowSize() {
      this.innerHeight = window.innerHeight
      this.innerWidth = window.innerWidth
      // console.log("浏览器宽度:"+window.innerWidth);
      // console.log("浏览器高度:"+window.innerHeight);
      // console.log("isPhone:"+this.isPhone)
    },
  }
}
</script>

<style scoped>
/* */
.Header{
  padding: 0 20px;
  position: fixed;
  width: 100%;
  z-index: 1000;
  height: 60px;
  background-color: rgba(245, 245, 245, 0.8); /* 设置透明度为 0.8 */
  border-bottom: 1px solid #dedede;
  backdrop-filter: blur(5px); /* 添加模糊效果，模糊半径为 10px */
}
.HeaderLeft{
  flex: 1;
  display: flex;
  padding-left: 30px
}
.LOGO{
  cursor: pointer;
  height: 60px;
  width: auto;
  line-height: 60px;
  font-size: 30px;
  color:#fefefe;
  text-shadow:0px 1px 0px #c0c0c0,
  0px 2px 0px #b0b0b0,
  0px 3px 0px #a0a0a0,
  0px 4px 0px #909090,
  0px 5px 10px rgba(0, 0, 0, .9);
  transition: all 0.3s ease; /* 添加过渡效果 */
}
/* 鼠标悬停时的样式 */
.LOGO:hover {
  text-shadow: 0px 1px 0px #c0c0c0,
  0px 3px 0px #a0a0a0,
  0px 5px 0px #909090,
  0px 7px 10px rgba(0, 0, 0, .9);
}
/* 鼠标点击时的样式 */
.LOGO:active {
  text-shadow: 0px 1px 0px #c0c0c0,
  0px 3px 0px #a0a0a0,
  0px 5px 0px #909090,
  0px 7px 10px rgba(0, 0, 0, .9),
  0px 0px 20px #fefefe; /* 添加额外的阴影效果 */
}
.HeaderMiddle{

}
.PCNavBar{

}
.PhoneNavBar{

}
.PhoneNavBar .router-link-active{
  color: #0083ff;
}
.NavBarItem{
  cursor: pointer;
  font-size: 14px;
  color: #686868;
  padding: 0 20px;
}
.PCNavBar .router-link-active{
  color: #0083ff;
  border-bottom: 2px solid #0083ff;
}
.NavBarItem:hover{
  color: #343434;
  border-bottom: 2px solid #0083ff;
}
.HeaderRight{

}
.Footer{
  padding: 32px 16px;
  text-align: center;
  background-color: #fdfdfd;
  /*margin-top: 10px;*/
}
</style>