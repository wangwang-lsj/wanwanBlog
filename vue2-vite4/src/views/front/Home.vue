<template>
  <div class="HomeBg">
    <div style="margin: 0;display: flex;justify-content: center">
      <!--左-->
      <div style="flex: 1; max-width: 400px;" v-if="windowWidth>768">
        <!--<el-card class="box-card">-->
        <!--  <div slot="header" class="clearfix" style="text-align: center">-->
        <!--    <span style="font-size: xx-large"><i class="iconfont icon-gonggao" style="font-size: 32px;color: green"></i>公告</span>-->
        <!--  </div>-->
        <!--  <div>-->
        <!--    主页不知道放什么了-->
        <!--  </div>-->
        <!--</el-card>-->
      </div>
      <!--中-->
      <div style="flex: 3;max-width: 600px;margin: 0 10px;">
        <!--轮播图-->
        <div style="border-bottom: 1px solid #ccc;">
          <!--height=10vh -->
          <el-carousel :interval="4000" height="25vh" style="border-radius: 5px;">
            <el-carousel-item v-for="item in images" :key="item.id">
              <img :src="item.url" style="width: 100%;height: 100%"></img>
            </el-carousel-item>
          </el-carousel>
        </div>
        <!--内容-->
        <div style="margin: 10px 0">
            <!--<viewer :images="urls">-->
            <!--  <img v-for="(url,index) in urls" :key="index" :src="url" v-lazy="url" alt="test" width="100%">-->
            <!--</viewer>-->
          <div class="HomeArticle" v-for="article in homeArticles" :key="article.id" :style="windowWidth<=768?'flex-direction: column;':''" @click="goDetail(article.id)">
            <div class="HomeArticleCover"><img :src="article.cover"></div>
            <div style="flex: 1;display: flex;justify-content: space-between;flex-direction: column">
              <div class="HomeArticleTD">
                <h3>{{article.title}}</h3>
                <p>{{article.description}}</p>
              </div>

              <div style="display: flex;justify-content: space-between;" class="HomeArticleInfo">
                <span><i class="iconfont icon-shijian"></i>{{formatTimeAgo(article.publicDate)}}</span>
                <span><i class="iconfont icon-liulanliang"></i>{{article.readCount}}</span>
                <span><i class="iconfont icon-dianzan"></i>{{article.likes}}</span>
                <span><i class="iconfont icon-pinglun"></i>{{article.commentCount}}</span>
                <span>{{article.categoryName}}<i class="iconfont icon-category"></i></span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--右-->
      <div style="flex: 1;max-width: 400px" v-if="windowWidth>768">

      </div>
    </div>
  </div>
</template>

<script>

import homeApi from "@/api/homeApi.js";
import articleApi from "@/api/articleApi.js";
import {formatTimeAgo} from "@/utils/MyUtils.js";
export default {
  name: "Home",
  data(){
    return{
      testImages:[
        'http://localhost:9090/api/file/dbf21f06f21b4e67b5f2c8c172353a25.png'
      ],
      images:[],
      // browserWidth: window.innerWidth,
      homeArticles: [],
    }
  },
  computed:{
    windowWidth(){
      return this.$store.state.windowWidth
    }
  },
  created() {
    this.load()
  },
  mounted() {
    // this.$store.dispatch('createWindowSizeListener')
    // window.addEventListener('resize', this.handleResize);
  },
  destroyed() {
    // this.$store.dispatch('destroyWindowSizeListener')
    // window.removeEventListener('resize', this.handleResize);
  },

  beforeRouteEnter(to,from,next){
    next(vm => {
      //因为当钩子执行前，组件实例还没被创建
      // vm 就是当前组件的实例相当于上面的 this，所以在 next 方法里你就可以把 vm 当 this 来用了。
      // window.addEventListener('resize', vm.handleResize);
      // vm.$store.dispatch('destroyWindowSizeListener')

    });
  },
  // 删除滚动监听器，建议使用beforeRouteLeave，因为destroyed()钩子在路由跳转时不会触发(加了这个就跳不了路由)
  beforeRouteLeave(to,from,next) {
    // this.$store.dispatch('destroyWindowSizeListener')
    // window.removeEventListener('resize', this.handleResize);
    next()
  },
  methods:{
    formatTimeAgo,
    load(){
      homeApi.queryAll().then(res=>{
        if(res.code === '200'){
          this.images = res.data
        }
      })
      homeApi.queryHomeArticles().then(res=>{
        if(res.code === '200'){
          this.homeArticles = res.data
        }
      })
    },
    // handleResize() {
    //   this.browserWidth = window.innerWidth;
    // },
    goDetail(id){
      if(window.getSelection().toString()===''){
        this.$router.push({
          // path:'/article/detail',
          name: '文章详细',
          query:{id: id}
        })
      }
    },
  }
}
</script>

<style scoped>
.HomeBg{
  min-height: 100vh;
  padding: 10px 0;
  /*background-image: url("@/assets/img/mcbg.jpg");*/
  background-repeat: no-repeat;
  background-attachment: fixed;
}
@media screen and (max-width: 768px) {
  .HomeArticleCover{
    width: 100%;
    height: 11.5rem;
    margin-bottom: .5rem;
    margin-right: 0;
  }
  .HomeArticleCover img{
    width: 100%;
    height: 100%;
  }
}
@media screen and (min-width: 768px) {
  .HomeArticleCover{
    width:12rem;
    height:7.5rem;
    margin-right: 1rem;
  }
  .HomeArticleCover img{
    width:192px;
    height:120px;
    border-radius: 5px;
  }
}
.HomeArticle{
  display: flex;
  padding: 0.618rem;
  background: rgb(255, 255, 255,0.6);
  border-radius: 5px;
  margin-bottom: 10px;
  cursor: pointer;
}
.HomeArticle:hover{
  background: rgb(255, 255, 255,0.8);

}

.HomeArticleTD{

}
.HomeArticleTD h3{
  margin-bottom: 0.5rem;
}
.HomeArticleTD p{
  min-height: 3rem;
  color: rgba(0, 0, 0, .43);
  font-size: 0.9rem;
}
.HomeArticleInfo{
  font-size: 12px;
  height: 16px;
  line-height: 16px;
}
.HomeArticleInfo span{
  display: flex;
  align-items: center;
  height: 16px;
  line-height: 16px;
}
</style>