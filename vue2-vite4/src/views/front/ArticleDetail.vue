<template>
  <div class="ArticleDetailBg">
    <div style="display: flex;justify-content: center;margin: 0 auto;max-width: 1600px;">
      <!--左-->
      <div v-if="windowWidth>=1200" style="padding: 0 10px;min-width: 20%;">
      </div>
      <!--中-->
      <div :style="{padding: windowWidth < 600 ? '0px' : '0 10px',minWidth: windowWidth < 1200 ? '100%' : '60%'}">
        <div :style="windowWidth<600?'padding:5px':'padding: 10px'" class="ArticleItem">
          <!--文章标题-->
          <h1 class="ArticleItemTitle">
            {{article.title}}
          </h1>
          <!--文章顶部-->
          <div class="ArticleItemInfo">
            <div><i class="iconfont icon-T"></i>共{{ words }}字,约{{readTime}}分钟</div>
            <span>|</span>
            <div><i class="iconfont icon-shijian"></i>{{ publicDate }} {{AmOrPm}}</div>
            <span>|</span>
            <div><i class="iconfont icon-liulanliang"></i>{{ article.readCount }}次阅读</div>
            <span>|</span>
            <div><i class="iconfont icon-dianzan"></i>{{ article.likes }}个点赞</div>
          </div>
          <!--文章详细-->
          <div style="width: 100%" class="ArticleItemContent">
            <!--不加v-if控制组件是否渲染不显示数据，猜测可能是，在数据没到的时候就渲染了组件，但是没数据就是空的了-->
            <WangEditor :content="this.article.content" :editable="false" v-if="articleCreated" @sendWords="handleWords" :key="componentKey"></WangEditor>
          </div>
          <!--文章底部-->
          <div style="display: flex;align-items: center">
            <div style="flex: 1; display: flex;">
              <div style="flex: 2;white-space: nowrap"><i class="iconfont icon-category">{{article.categoryName}}</i></div>
              <div style="flex: 3;white-space: nowrap">
                <i class="iconfont icon-biaoqian" v-if="article.tags!=='[]'"></i>
                <el-tag v-for="tag in JSON.parse(article.tags||'[]')" :key="tag" type="primary" style="margin-right: 10px">{{ tag }}</el-tag>
              </div>
            </div>
            <div style="flex: 1;display: flex;justify-content: right">
              <div @click="handLike" style="user-select: none">
                <i class="iconfont icon-dianzan" :style="{color: isLike?'#fc5531':'#999aaa',fontSize: fontSize+'px'}" ref="articleLikes" >{{article.likes}}</i>
              </div>
              <div>
                <i class="iconfont icon-pinglun" style="margin-left: 10px; font-size: 24px;">{{this.commentCount}}</i>
              </div>
            </div>
          </div>
        </div>
        <!--评论-->
        <div>
          <Comment :key="componentKey"></Comment>
        </div>
      </div>
      <!--右-->
      <div v-if="windowWidth>=1200" style="padding: 0 10px;min-width: 20%;">
        <div style="position: sticky; top: 70px;">
          <div style="background-color: #ffffff; padding: 20px;border-radius: 5px;">
            <h2>目录</h2>
            <ul id="header-container"></ul>
          </div>
          <!--相关推荐-->
          <div style="background-color: #ffffff; padding: 20px;margin-top: 20px; border-radius: 5px;">
            <div style="border-bottom: 3px solid #f2f3f5;margin-bottom: 5px;">
              <span><i class="iconfont icon-xiangguantuijian" style="color: #1afa29;"></i>相关推荐</span>
            </div>
            <div>
              <ul>
                <li style="height: 40px;padding: 8px;display: flex;cursor: pointer" class="RelatedArticle" v-for="(article,index) in relatedArticleList" :key="article.id" @click="goRelatedDetail(article.id)" :title="article.title">
                  <div style="width: 24px;color:#9fa4b0;" class="RelatedArticleItem">{{ index+1 }}</div>
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
  </div>
</template>

<script>
import WangEditor from "@/components/WangEditor.vue";
import Comment from "@/components/Comment.vue";
import articleApi from "@/api/articleApi.js";
import commentApi from "@/api/commentApi.js";


export default {
  name: "ArticleDetail",
  components: {Comment, WangEditor},
  data(){
    return{
      articleId: this.$route.query.id,
      currentUser: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},

      article:{},
      menuList:[],
      content:'',
      articleCreated: false,
      isLike: false,
      words: 0,
      readTime: 0,
      publicDate: "",
      AmOrPm: "",
      fontSize: 24,
      commentCount: 0,
      currentMenu: 0,
      relatedArticleList:[],
      componentKey: 0,
    }
  },
  computed:{
    windowWidth(){
      return this.$store.state.windowWidth
    }
  },
  // watch:{
  //   $route(to,from){
  //
  //   },
  // },
  created() {
    this.load()

  },
  mounted() {

    // 标题 DOM 容器
    const headerContainer = document.getElementById('header-container')
    // console.log(headerContainer)
    if(headerContainer === null) return
    headerContainer.addEventListener('mousedown', event => {
      if (event.target.tagName !== 'LI') return
      event.preventDefault()
      const id = event.target.id
      const elem = document.getElementById(id);
      // console.log(elem.offsetTop) //该元素的顶部距离父元素的顶部的距离
      const offset = elem.offsetTop + 110; // 距离这个视图顶部偏移量
      // console.log(offset)
      window.scrollTo({ top: offset, behavior: 'smooth' });
    });
    // this.Test()
  },
  // beforeRouteEnter(to,from,next){
  //   next(vm => {
  //     //因为当钩子执行前，组件实例还没被创建
  //     // vm 就是当前组件的实例相当于上面的 this，所以在 next 方法里你就可以把 vm 当 this 来用了。
  //     window.addEventListener('resize', vm.handleResize);
  //   });
  // },

  beforeRouteUpdate(to, from, next) {
    next()//执行这个之后，id就更新了
    this.goRelatedDetail(this.$route.query.id)
  },
  beforeDestroy() {
    this.article.readCount+=1
    articleApi.updateReadCount(this.articleId)
  },

  methods:{
    load(){
      if(Object.keys(this.currentUser).length === 0) {
        this.currentUser.id = 0
      }
      articleApi.queryById(this.articleId).then(res=>{
        if(res.code ==='200'){
          // console.log(res)
          this.article = res.data
          // console.log(this.article)
          this.AmOrPm = this.getAMPM(this.article.publicDate)
          this.publicDate = this.article.publicDate.replace(/(\d{2}):(\d{2}):(\d{2})/,'')
          this.loadRelatedArticle(this.article.categoryId)
          this.articleCreated = true
          this.componentKey++
        }
        // console.log(this.article)
      })

      articleApi.queryLike(this.articleId,this.currentUser.id).then(res=>{
        if(res.code === '200'){
          // console.log(res)
          if(res.data){
            this.isLike = true
          }else {
            this.isLike = false
          }
        }
      })
      this.getCommentCount()
    },
    getCommentCount(){
      commentApi.queryCountByArticleId(this.articleId).then(res=>{
        if(res.code === '200'){
          this.commentCount = res.data
        }
      })
    },

    //获取到富文本编辑器的内容
    hChangeData(editDataHtml) {
      // console.log(editDataHtml);
      // 获取最新的html数据
      this.article.content = editDataHtml;
      console.log(this.content);
    },

    handLike(){
      if(this.currentUser.id === 0) {
        this.$message("请先登录")
        return
      }
      if (this.isLike===true){
        articleApi.updateLike({
          articleId: this.articleId,
          userId: this.currentUser.id,
          isLike: this.isLike,
        }).then(res=>{
          if (res.code ==='200'){
            this.isLike = false
            this.article.likes-=1
          }else{
            this.$message.error(res.msg)
          }
        })
      }else {
        articleApi.updateLike({
          articleId: this.articleId,
          userId: this.currentUser.id,
          isLike: this.isLike,
        }).then(res=>{
          if(res.code ==='200'){
            this.isLike = true
            this.article.likes+=1
          }else{
            this.$message.error(res.msg)
          }
        })
      }
    },

    getAMPM(timeString) {
      // 将时间字符串转换为日期对象
      const date = new Date(timeString);

      // 获取小时
      const hours = date.getHours();

      // 判断是上午还是下午
      return  hours >= 12 ? '下午' : '上午';
    },


    handleWords(words){
      this.words = words
      const wordsPerMinute = 500;
      this.readTime = Math.ceil(words / wordsPerMinute);
    },

    loadRelatedArticle(categoryId){
      articleApi.queryRelations({
        pageNum: 1,
        pageSize: 5,
        articleId: this.articleId,
        categoryId: categoryId,
      }).then(res=>{
        if(res.code === '200'){
          // 将处理后的文章列表赋值给 hotArticleList
          this.relatedArticleList = res.data.records;
          // console.log(this.relatedArticleList)
        }
      })
    },
    goRelatedDetail(id){
      if(window.getSelection().toString()===''){
        this.$router.push({
          // path:'./detail',
          name: '文章详细',
          query:{id: id}
        })
        this.articleId=id
        this.load()
      }
    },
  }
}
</script>

<style scoped>
.ArticleDetailBg{
  padding: 10px 0;
}
.ArticleItem{
  background-color: #ffffff;
  margin-bottom: 20px
}
.ArticleItemTitle{
  font-size: 32px;
  margin-bottom: 10px;
  text-align: center
}
.ArticleItemInfo{
  display: flex;
  justify-content: center;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  flex-wrap: wrap;
  font-size: 14px;
  margin-bottom: 20px;
  color: rgba(0, 0, 0, 0.5); /* 降低透明度到 50% */;
}
.ArticleItemInfo span{
  padding: 0 10px;
}
.ArticleItemInfo i{
  margin-right: 5px;
}

/*相关文章 start*/
.RelatedArticle{
  position: sticky;
  top: 70px;
}
.RelatedArticle:hover{
  background-color: #f7f8fa;
}
.RelatedArticleItem{
  font-family: "Reouseau-Deco",serif;
  font-size: 20px;
  font-weight: 950;
}
/*相关文章 end*/

</style>