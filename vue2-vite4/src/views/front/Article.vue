<template>
  <div class="ArticleBg">
    <div style="margin: 0;display: flex;justify-content: center;">
      <!--左边导航栏-->
      <div style="flex: 1;max-width: 300px;display: flex;justify-content: right" v-if="windowWidth>=1200">
        <div class="card" style="width: 150px;position: fixed">
          <div class="article-category-item" @click="handSelectBy(item.name)" :class="{'article-category-item-active': item.name === currentCategory}" v-for="item in categoryList" :key="item.id">{{ item.name }}</div>
        </div>
      </div>
      <!--中间内容-->
      <div style="flex: 3;max-width: 800px;" :style="windowWidth<768?'padding:0':'padding:0 10px'">
        <div class="card" style="padding: 0">
          <div style="height: 40px;border-bottom: 1px solid #f2f3f5;display: flex;padding: 10px;">
            <!--文章类别(桌面)-->
            <div style="flex: 1;width: 200px;">
              <ul class="article-category1" v-if="windowWidth<1200">
                <li v-for="item in categoryList" :key="item.id" @click="handSelectBy(item.name)" class="article-category1-item" :class="{'article-category1-item-active': item.name === currentCategory}">{{ item.name }}</li>
              </ul>
            </div>
            <!--文章类别(移动)-->
            <div style="width: 70px;text-align: center">
              <el-dropdown placement="bottom" @command="handleOrderTarget">
              <span class="el-dropdown-link">
                {{ orderTarget }}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
                <el-dropdown-menu v-slot="dropdown">
                  <el-dropdown-item command="默认">默认</el-dropdown-item>
                  <el-dropdown-item command="时间">时间</el-dropdown-item>
                  <el-dropdown-item command="点赞">点赞</el-dropdown-item>
                  <el-dropdown-item command="浏览量">浏览量</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
            <div style="width: 50px;text-align: center">
              <el-dropdown placement="bottom" @command="handleOrder">
              <span class="el-dropdown-link">
                {{ order }}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
                <el-dropdown-menu v-slot="dropdown">
                  <el-dropdown-item command="逆序"><span style="cursor: pointer">逆序</span></el-dropdown-item>
                  <el-dropdown-item command="顺序"><span style="cursor: pointer">顺序</span></el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </div>
          <div v-for="article in articleList" :key="article.id" class="article-box" style="padding: 0 10px;height: 100px;cursor: pointer">
            <div @click="goDetail(article.id)" style="display: flex;width: 100%; height:100%;border-bottom: 1px solid #f2f3f5">
              <div style="flex: 1; width: 0" class="article_title_content">
                <div style="font-size: 16px; font-weight: bold;margin-bottom: 10px" class="article_title">
                  {{ article.title }}
                </div>
                <div class="article_content" style="color: #666666;margin-bottom: 20px;font-size: 13px">
                  {{ article.description }}
                </div>
                <div style="display: flex">
                  <div style="flex: 1;font-size: 13px">
                    <span style="color: #666666;margin-right: 10px"><i class="iconfont icon-liulanliang"></i>{{ article.readCount }}</span>
                    <span style="color: #666666;margin-right: 10px"><i class="iconfont icon-dianzan"></i>{{ article.likes }}</span>
                    <span style="color: #666666;margin-right: 10px"><i class="iconfont icon-pinglun"></i>{{ article.commentCount }}</span>
                  </div>
                  <div style="width: fit-content">
                    <el-tag v-for="tag in JSON.parse(article.tags||'[]')" :key="tag" type="primary" style="margin-right: 10px">{{ tag }}</el-tag>
                  </div>
                </div>
              </div>
              <div style="width: 100px;height: 100%; margin-left: 5px;display: flex;align-items: center;">
                <el-image v-if="article.cover" :src="article.cover" style="border-radius: 5px; width: 100%;" alt="封面"></el-image>
                <!--<el-image v-else src="/img/noimg.jpg" style="border-radius: 5px;"></el-image>-->
                <img v-else src="/img/noimg.jpg" style="border-radius: 5px;max-width: 100px;"></img>
              </div>
            </div>
          </div>
          <p v-if="noMore && articleList.length>10" style="text-align: center">没有更多了</p>
        </div>
      </div>
      <!--右边相关-->
      <div style="flex: 1; max-width: 300px;height: auto;" v-if="windowWidth>=1200">
        <!--搜索-->
        <div class="Search" style="margin-bottom: 10px;display: flex;align-items: center">
          <!--<el-input v-model="searchContent" :placeholder="searchPlaceholder" @keyup.enter.native="handleSearch" ref="search"></el-input>-->
          <input :placeholder="searchPlaceholder" style="" class="SearchInput" maxlength="15" v-model="searchContent" @keyup.enter="handleSearch" ref="search"></input>
          <button style="width: 32px;height: 32px;border: none" class="SearchButton" @click="handleSearch">
            <i class="iconfont icon-sousuo"></i>
          </button>
        </div>
        <!--文章统计-->
        <div class="ArticleStatistic">
          <div class="ArticleStatisticItem">
            <div>{{ statistic.articleCount }}</div>
            <p>全站文章</p>
          </div>
          <div>|</div>
          <div class="ArticleStatisticItem">
            <div>{{ statistic.commentCount }}</div>
            <p>全站评论</p>
          </div>
          <div style="">|</div>
          <div class="ArticleStatisticItem">
            <div>{{ statistic.todayRead }}</div>
            <p>今日阅读</p>
          </div>
        </div>
        <!--热门推荐-->
        <div class="card HotArticle">
          <div style="border-bottom: 3px solid #f2f3f5;margin-bottom: 5px;">
            <span><i class="iconfont icon-rementuijian" style="color: red;"></i>热门推荐</span><span style="float: right;user-select:none;" class="huanyihuan" @click="changeHot"><i class="iconfont icon-huanyihuan"></i>换一换</span>
          </div>
          <div>
            <ul>
              <li style="height: 40px;padding: 8px;display: flex;cursor: pointer" class="hotArticle" v-for="(article) in displayedArticles" :key="article.id" @click="goDetail(article.id)" :title="article.title">
                <div style="width: 24px;color: #c93756" class="hotArticleItem" v-if="article.serialNumber===1">{{ article.serialNumber }}</div>
                <div style="width: 24px;color: #f47983" class="hotArticleItem" v-else-if="article.serialNumber===2">{{ article.serialNumber }}</div>
                <div style="width: 24px;color: #f9906f" class="hotArticleItem" v-else-if="article.serialNumber===3">{{ article.serialNumber }}</div>
                <div style="width: 24px;color: #9fa4b0;" class="hotArticleItem" v-else>{{ article.serialNumber }}</div>
                <div style="flex: 1" class="text-oneLine-omit">
                  {{article.title}}
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getClientHeight, getScrollHeight, getScrollTop} from "@/utils/MyUtils.js";
import ArticleCategory from "@/views/manage/ArticleCategory.vue";
import categoryApi from "@/api/categoryApi.js";
import articleApi from "@/api/articleApi.js";
import {throttle} from "lodash";

let map = new Map()
map.set('默认','')
map.set('时间','public_date')
map.set('点赞','likes')
map.set('浏览量','read_count')

export default {
  name: "Article",
  components: {ArticleCategory},
  data(){
    return{
      pageNum: 1,
      pageSize: 10,
      total: 0,
      currentCategory: '全部博客',
      categoryList:[],
      count: 10,
      articleList: [],
      orderTarget: "默认",
      order: "逆序",
      searchContent: '',
      searchPlaceholder: '上下求索',
      hotArticleList: [],
      hotPageNum: 1,
      hotPageSize: 5,
      statistic: {},
      throttledWindowScroll: null, // 存储throttle处理后的函数引用
    }
  },
  computed:{
    noMore(){
      return this.articleList.length===this.total
    },
    displayedArticles() {
      const startIndex = (this.hotPageNum - 1) * this.hotPageSize;
      const endIndex = startIndex + this.hotPageSize;
      return this.hotArticleList.slice(startIndex, endIndex);
    },
    windowWidth(){
      return this.$store.state.windowWidth
    }
  },
  created() {
    categoryApi.queryAll().then(res => {
      if (res.code === '200') {
        this.categoryList = res.data
        this.categoryList.unshift({name: "全部博客"})
      }
    })
    this.load()
    this.loadHotArticle()
  },
  mounted() {
    this.throttledWindowScroll = throttle(this.windowScroll.bind(this), 500); // 将throttle后的函数存入data
    window.addEventListener('scroll', this.throttledWindowScroll) //监听页面滚动
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.throttledWindowScroll);//销毁滚动事件
  },
  // 因为keep-alive的原因，组件被激活时会执行activated函数，离开时会执行deactivated函数
  activated() {
    window.addEventListener('scroll', this.throttledWindowScroll) //监听页面滚动
  },
  beforeRouteLeave(to,from,next) {
    // 记录离开时的滚动位置到 session storage
    sessionStorage.setItem('articleScrollTop', window.scrollY.toString());
    window.removeEventListener("scroll", this.throttledWindowScroll);//销毁滚动事件
    next()
  },
  // beforeRouteEnter(to,from,next){
  //
  //   next(vm => {
  //
  //         //因为当钩子执行前，组件实例还没被创建
  //         // vm 就是当前组件的实例相当于上面的 this，所以在 next 方法里你就可以把 vm 当 this 来用了。
  //     window.addEventListener('resize', vm.handleResize);
  //
  //     window.addEventListener('scroll',vm.throttledWindowScroll) //监听页面滚动
  //   });
  // },



  methods: {
    load() {
      articleApi.queryPage({
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        title: this.searchContent,
        description: this.searchContent,
        categoryName: this.currentCategory==='全部博客'?'':this.currentCategory,
        orderTarget: map.get(this.orderTarget),
        order: this.order==='逆序'?'desc':'asc',
      }).then(res => {
        if (res.code === '200') {
          this.articleList = res.data.records
          this.total = res.data.total
        }
      })
      articleApi.queryStatistics().then(res=>{
        if(res.code === '200'){
          this.statistic = res.data
        }
      })
      // this.count += 2
    },
    //回调函数
    windowScroll() {
      // console.log(this.noMore)
      if (this.noMore){
        return
      }
      //获取三个值
      let scrollTop = getScrollTop()
      let clientHeight = getClientHeight()
      let scrollHeight = getScrollHeight()
      // console.log(scrollTop, clientHeight, scrollHeight)
      //如果满足公式则，确实到底了
      if (scrollHeight - (scrollTop + clientHeight) < 100) {
        this.loadMore()
        // console.log("到底了,加载更多")
      }
    },
    loadMore(){
      // 在请求前递增pageNum，确保请求的是下一页数据
      const nextPage = this.pageNum + 1;
      articleApi.queryPage({
        pageNum: nextPage,
        pageSize: this.pageSize,
        title: this.searchContent,
        description: this.searchContent,
        categoryName: this.currentCategory==='全部博客'?'':this.currentCategory,
        orderTarget: map.get(this.orderTarget),
        order: this.order==='逆序'?'desc':'asc',
      }).then(res => {
        if (res.code === '200') {
          // console.log(res.data.records)
          this.pageNum = nextPage
          this.articleList = this.articleList.concat(res.data.records)
        }else {

        }
      })
    },
    goDetail(id){
      if(window.getSelection().toString()===''){
        this.$router.push({
          // path:'/article/detail',
          name: '文章详细',
          query:{id: id}
        })
      }
    },
    handSelectBy(name){
      this.pageNum = 1
      this.pageSize =10
      this.currentCategory = name
      this.load()
    },
    // 配合element-ui的无限滚动
    // nextPageLoad() {
    //   // console.log("111")
    //   this.pageNum += 1
    //   this.request.post('/article/page', {
    //     pageNum: this.pageNum,
    //     pageSize: this.pageSize,
    //   }).then(res => {
    //     if (res.code === '200') {
    //       this.articleList = this.articleList.concat(res.data.records)
    //     }
    //   })
    // }
    handleOrderTarget(command){
      this.pageNum = 1
      this.pageSize = 10
      this.orderTarget = command
      this.load()
    },
    handleOrder(command){
      this.pageNum = 1
      this.pageSize = 10
      this.order = command
      this.load()
    },
    handleSearch(){
      if(this.searchContent===''){
        this.searchPlaceholder = "请输入搜索内容"
      }else {
        this.load()
      }
    },
    loadHotArticle(){
      articleApi.queryHots({
        pageNum: 1,
        pageSize: 10,
      }).then(res=>{
        if(res.code === '200'){
          // 为每篇文章添加序号属性
          res.data.records.forEach((article, index) => {
            article.serialNumber = index + 1; // 序号从1开始
          });
          // 将处理后的文章列表赋值给 hotArticleList
          this.hotArticleList = res.data.records;
        }
      })
    },
    changeHot() {
      const totalPages = Math.ceil(this.hotArticleList.length / this.hotPageSize);
      this.hotPageNum = (this.hotPageNum % totalPages) + 1;
    },
    getAssetsFile(fileUrl){
      return new URL(`../../assets/img/${fileUrl}`, import.meta.url).href
    }
  }
}
</script>

<style scoped>
.ArticleBg{
  min-height: 100vh;
  padding: 10px 0;
}
.article-category-item{
  padding: 10px 0;
  text-align: center;
  font-size: 18px;
  cursor: pointer;
}
.article-category-item:hover{

  background-color: #6ab7e7;
  color: #ffffff;
  border-radius: 5px;
}
.article-category-item-active{
  background-color: #3F5EFB;
  color: #ffffff;
  border-radius: 5px;
}


.article-category1 {
  display: flex;
  white-space: nowrap;
  overflow-x: scroll;
  overflow-y: hidden;
  width: auto;
  flex-flow: row nowrap;
  justify-content: space-between;
}

.article-category1::-webkit-scrollbar {
  display: none; /* 隐藏滚动条 */
}
.article-category1-item-active{
  color: blue;
}
.article-category1 li {
  padding: 0 5px;
  display: inline-block;
  cursor: pointer;
}
.article-category1 li:hover {
  color: blue;
}
/*.box-card.article .el-card__body{*/
/*  display: flex;*/
/*  width: 100%;*/
/*}*/
.article-box:hover{
  background-color: #f7f8fa;
}
.article_content{
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.huanyihuan{
  cursor: pointer;
}
.huanyihuan:hover{
  color: #1e80ff;
}

@font-face {
  font-family: "Rousseau-Deco";
  src: url("@/assets/font/Rousseau-Deco-2.ttf");
}

/*热门文章 start*/
.HotArticle{
  position: sticky;
  top: 70px;
}
.hotArticle:hover{
  background-color: #f7f8fa;
}
.hotArticleItem{
  font-family: "Reouseau-Deco",serif;
  font-size: 20px;
  font-weight: 950;
}
/*热门文章 end*/
/*搜索start*/
.Search{
  background-color: #F5F5F5;
  padding: 10px;
  border-radius: 10px;

}
.Search:hover{
  background-color: #ffffff;

}
.SearchInput{
  height: 32px;
  border: none;
  flex: 1;
  padding: 0 5px;
}
.SearchInput:focus{
  outline: none;
  background-color: #f0f0f0;
}
.SearchInput:hover{

}
.SearchButton{

}
.SearchButton:hover{
  background-color: #bdbdbd;
}
/*搜索end*/
/*文章统计 start*/
.ArticleStatistic{
  cursor: pointer;
  display: flex;
  align-items: center;
  padding: 10px;
  margin-bottom: 10px;
  background-color: #F5F5F5;
  border-radius: 5px;
}
.ArticleStatistic:hover{
  background-color: #ffffff;
}
.ArticleStatisticItem{
  flex: 1;
  display: flex;
  align-items: center;
  flex-direction: column
}
.ArticleStatisticItem>div{
  font-size: 20px;
}
.ArticleStatisticItem>p{
  font-size: 10px;
}
/*文章统计 end*/

</style>