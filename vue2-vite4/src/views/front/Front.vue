<template>
  <div class="Front">
    <div class="Content">
      <div class="Mask" @click="openDrawer()" v-if="drawerSwitch"></div>
      <!--头部-->
      <div class="Header" :style="windowWidth<=768?{'padding':'0'}:{'padding':'0 20px'}">
        <div class="Header-PC" v-if="windowWidth>768">
          <!--LOGO-->
          <div class="Header-Left">
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
          <div class="Header-Middle PCNavBar" v-if="windowWidth>768" >
            <router-link to="/home" class="NavBarItem">主页</router-link>
            <router-link to="/article" class="NavBarItem">文章</router-link>
            <router-link to="/guestbook" class="NavBarItem">留言板</router-link>
            <router-link to="/about" class="NavBarItem">关于</router-link>
          </div>

          <div class="Header-Right">
            <!--头像-->
            <el-dropdown v-if="JSON.stringify(user)!=='{}'" trigger="click">
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
                <router-link to="/manage/backhome">
                  <el-dropdown-item style="font-size: 14px; padding: 5px 0" v-if="user.role==='ADMIN'">
                    前往后台
                  </el-dropdown-item>
                </router-link>
                <!--<el-dropdown-item style="font-size: 14px; padding: 5px 0">-->
                <!--  <router-link to="/person">个人中心</router-link>-->
                <!--</el-dropdown-item>-->
                <span @click="logout()" style="text-decoration: none">
                <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                  退出
                </el-dropdown-item>
              </span>
              </el-dropdown-menu>
            </el-dropdown>
            <router-link to="/login" v-else>
              <div style="width: 100px;height: 100% ;cursor: pointer; text-align: center;float: right">
                登陆
              </div>
            </router-link>
          </div>
        </div>
        <div class="Header-Mobile" v-else>
          <!--手机导航栏-->
          <div class="DrawerButton" @click="openDrawer" >
            <i class="iconfont icon-daohanglan" style="line-height: 60px;"></i>
          </div>
          <!--<div v-if="windowWidth<=768" style="flex: 1" >-->

            <!--<el-dropdown trigger="click">-->
            <!--  <span class="el-dropdown-link">-->
            <!--    &lt;!&ndash;{{$route.name }}<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>&ndash;&gt;-->
            <!--  </span>-->
            <!--  <el-dropdown-menu slot="dropdown" class="PhoneNavBar">-->
            <!--    <el-dropdown-item><i class="iconfont icon-zhuye"></i><router-link to="/home">主页</router-link></el-dropdown-item>-->
            <!--    <el-dropdown-item><i class="iconfont icon-wenzhang"></i><router-link to="/article">文章</router-link></el-dropdown-item>-->
            <!--    <el-dropdown-item><i class="iconfont icon-liuyanban"></i><router-link to="/guestbook">留言板</router-link></el-dropdown-item>-->
            <!--    <el-dropdown-item><i class="iconfont icon-guanyu"></i><router-link to="/about">关于</router-link></el-dropdown-item>-->
            <!--  </el-dropdown-menu>-->
            <!--</el-dropdown>-->
          <!--</div>-->
          <!--<div class="HeaderLeft" v-if="windowWidth<=768" style="padding: 0">-->

          <!--</div>-->
          <!--LOGO-->
          <div class="LOGO" @click="$router.push('/home')">
            Wanwan
          </div>
          <!--空-->
          <div>

          </div>
        </div>
      </div>
      <!--主体内容-->
      <div class="Main" style="min-height: calc(100vh - 100px);overflow: hidden">
        <div style="height: 60px"></div>
        <keep-alive>
          <router-view v-if="$route.meta.keepAlive"></router-view>
        </keep-alive>
        <!-- 不需要缓存的视图组件 -->
        <router-view v-if="!$route.meta.keepAlive"></router-view>
      </div>
      <!--站脚-->
      <div class="Footer">
        备案号：<a href="https://beian.miit.gov.cn/" target="_blank">湘ICP备2024049847号-1</a>
      </div>
    </div>
    <!--抽屉导航栏-->
    <div class="Drawer" v-if="windowWidth<=768">
      <!--用户信息-->
      <div class="Drawer-User">
        <!--头像-->
        <img class="User-Avatar"></img>
        <div class="User-Info">
          <!--用户名-->
          <h3 class="User-Name"></h3>
          <!--口号-->
          <span class="User-Slogan"></span>
        </div>
      </div>
      <!--工具栏-->
      <div class="Drawer-Tools">
        <div class="item">
          <i></i>
        </div>
        <div class="item">
          <i></i>
        </div>
      </div>
      <!--导航-->
      <div class="Drawer-Nav">
          <nav class="nav-list">
            <a></a>
          </nav>
      </div>
    </div>

  </div>

  <!--<el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>-->
</template>

<script>
import user from "@/views/manage/User.vue";
import debounce from 'lodash/debounce';
import AMapLoader from "@amap/amap-jsapi-loader";
import _ from "lodash";
window._AMapSecurityConfig = {
  securityJsCode: "439ebac8fb9b8bdc5fb2f1c2c21bf4f9",
};
export default {
  name: "Front",
  components:{
  },
  data(){
    return{
      user: localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
      currentPathName: localStorage.getItem("currentPathName")?localStorage.getItem("currentPathName"):'',
      showComponent: true,
      cityData: {
        adcode: "",
        city: "",
        province: "",
        rectangle: "",
        humidity: "",
        temperature: 25,
        weather: "",
        windDirection: "",
        windPower: "",
        reportTime: "",
        forecasts: [],
      },
      visitorIP: "",
      weatherData: {},
      drawerSwitch: false,
    }
  },
  computed:{
    windowWidth(){
      return this.$store.state.windowWidth
    }
  },
  created() {

  },
  mounted() {
    this.$store.dispatch('createWindowSizeListener')
    this.initAMap()
    this.getVisitorIP()
  },
  unmounted() {
    this.map?.destroy();
  },
  beforeDestroy() {
    this.$store.dispatch('destroyWindowSizeListener')
  },
  // 删除滚动监听器，建议使用beforeRouteLeave，因为destroyed()钩子在路由跳转时不会触发
  beforeRouteLeave(to,from,next) {
    this.$store.dispatch('destroyWindowSizeListener')
    next()
  },
  methods:{
    async initAMap() {
      let that = this
      const AMap = await AMapLoader.load({
        key: "93367f193dab1c8e2d19d7b2fade2c5c",
        version: "2.0",
        plugins: [],
      });

      await new Promise((resolve, reject) => {
        AMap.plugin('AMap.CitySearch', function () {
          let citySearch = new AMap.CitySearch()
          citySearch.getLocalCity(function (status, result) {
            if (status === 'complete' && result.info === 'OK') {
              // 查询成功，result即为当前所在城市信息
              // console.log(result)
              that.cityData = _.pick(result, ['adcode', 'city', 'province', 'rectangle'])
              // console.log(that.cityData)
              resolve(AMap)
            } else {
              reject('Failed to get city data'); // 获取城市信息失败
            }
          })
        })
      })
      await new Promise((resolve, reject) => {
        AMap.plugin("AMap.Weather", function () {
          //创建天气查询实例
          let weather = new AMap.Weather();
          //执行实时天气信息查询
          weather.getLive(that.cityData.city, function (err, data) {
            // that.cityData = _.pick(data, ['humidity','temperature','weather','windDirection','windPower','reportTime'])
            that.cityData.humidity = data.humidity
            that.cityData.temperature = data.temperature
            that.cityData.weather = data.weather
            that.cityData.windDirection = data.windDirection
            that.cityData.windPower = data.windPower
            that.cityData.reportTime = data.reportTime
            sessionStorage.setItem('cityData', JSON.stringify(that.cityData))
            // console.log(that.cityData)
            // console.log(err, data);
            //err 正确时返回 null
            //data 返回实时天气数据，返回数据见下表
          });
          //执行天气预报信息查询
          weather.getForecast(that.cityData.city, function (err, data) {
            that.cityData.forecasts = data.forecasts
            // console.log(err, data);
            //err 正确时返回 null
            //data 返回天气预报数据，返回数据见下表
          });
        });
      })
    },
    getVisitorIP(){

    },
    openDrawer(){
      this.drawerSwitch = !this.drawerSwitch
      const bodyStyle = document.body.style
      if(this.drawerSwitch){
        // 关闭滚动
        bodyStyle.overflow = "hidden"
        // 打开抽屉
        $('.Drawer').css("left", "0")
        $('.Content').css("left", "250px")

      }else {
        bodyStyle.overflow = ""
        // 关闭抽屉
        $('.Drawer').css("left", "-250px")
        $('.Content').css("left", "0")
      }

    },
    logout(){
      this.$store.commit("logout")
      this.$message.success("退出成功")
    },
  }
}
</script>

<style lang="scss" scoped>
.Content{
  position: relative;
  transition: all 0.36s ease-in-out;
  .Mask{
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    right: 0;
    z-index: 99999;
    background-color: rgba(255, 255, 255, .5);
    backdrop-filter: blur(3px);
  }
  .Header{
    padding: 0 20px;
    position: fixed;
    width: 100%;
    z-index: 1000;
    height: 60px;
    background-color: rgba(245, 245, 245, 0.8); /* 设置透明度为 0.8 */
    border-bottom: 1px solid #dedede;
    backdrop-filter: blur(5px); /* 添加模糊效果，模糊半径为 10px */
    .Header-PC{
      height: 60px;
      line-height: 60px;
      display: flex;
      .Header-Left{
        flex: 1;
        display: flex;
        padding-left: 30px;

      }
      .Header-Middle{
        flex: 3;
        display: flex;
        justify-content: center;
        width: 412px;
      }
      .Header-Right{
        text-align: right;
        flex: 1;
        el-dropdown{
          width: 100px;
          height: 100% ;
          cursor: pointer;
        }
      }
      .PCNavBar{
        .NavBarItem{
          cursor: pointer;
          font-size: 14px;
          color: #686868;
          padding: 0 20px;
        }
        .NavBarItem:hover{
          color: #343434;
          border-bottom: 2px solid #0083ff;
        }
      }
      .PCNavBar .router-link-active{
        color: #0083ff;
        border-bottom: 2px solid #0083ff;
      }
    }
    .Header-Mobile{
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 60px;

      .DrawerButton{
        text-align: center;
        height: 100%;
        width: 80px;
      }
      .DrawerButton:active{
        background-color: #add8e6;
      }
      .DrawerButton>input:checked .Drawer{
        left: 0;
      }
      div:nth-child(3){
        width: 80px;
        height: 100%
      }
    }
  }
  //.Main{
  //  div{
  //  }
  //}
  .Footer{
    padding: 32px 16px;
    text-align: center;
    background-color: #fdfdfd;
    /*margin-top: 10px;*/
  }

}
.Drawer{
  position: fixed;
  width: 250px;
  height: 100vh;
  /*left: 0px;*/
  top: 0;
  z-index: 1000;
  background: #34393d;
  left: -250px;
  transition: all 0.36s ease-in-out;

  .Drawer-User{
    height: 120px;
  }
  .Drawer-Tools{
    height: 50px;
  }
  .Drawer-Nav{
    height: 500px;
  }
}



//.PhoneNavBar{
//
//}
//
//.PhoneNavBar .router-link-active{
//  color: #0083ff;
//}





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
.LOGO:hover{
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

</style>