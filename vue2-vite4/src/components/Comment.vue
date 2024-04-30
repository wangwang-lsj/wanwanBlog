<template>
  <div class="card">
    <!--对文章评论，一级评论-->
    <!--<div style="display: flex;">-->
    <!--  <div style="">-->
    <!--    <el-avatar :src="currentUser.avatarUrl"></el-avatar>-->
    <!--  </div>-->
    <!--  <div style="flex: 1;padding: 0 10px">-->
    <!--    <el-input type="textarea" placeholder="请输入内容" v-model="commentContent" maxlength="150" show-word-limit>-->
    <!--    </el-input>-->
    <!--  </div>-->
    <!--  <div style="display: flex;flex-direction: column">-->
    <!--    <div style="flex: 1"></div>-->
    <!--    <el-button type="primary" round @click="addFirstComment">发送</el-button>-->
    <!--  </div>-->
    <!--</div>-->
    <!-- 主评论表情输入框 -->
    <emoji-text ref="firstCommentRef" @comment="addFirstComment" :avatarUrl="currentUser.avatarUrl"></emoji-text>
    <!--一级评论和对一级评论的回复，二级评论-->
    <div style="min-height: 500px;margin: 20px auto">
      <div v-for="firstComment in commentList" :key="firstComment.id"
        style="border-bottom: 1px solid #e3e5e7;min-height: 100px;display: flex;">
        <div style="min-width: 40px;">
          <el-avatar :src="firstComment.avatarUrl"></el-avatar>
        </div>

        <div style="flex: 1; padding: 5px;">
          <div style="display: flex; margin-top: 5px">
            <div style="flex: 1">{{ firstComment.nickname }}</div>
            <div style="flex: 1"></div>
          </div>
          <div style="min-height: 30px; line-height: 30px; table-layout:fixed; word-break:break-all; overflow:hidden;">
            <span v-html="firstComment.commentContent"></span>
          </div>
          <div style="display: flex; margin-top: 10px">
            <div style="flex: 1;font-size: 15px;line-height: 27px;">
              <span style="margin-right: 20px"><i class="iconfont icon-shijian1">{{ firstComment.createTime }}</i></span>

            </div>
            <div style="flex: 1;text-align: right;line-height: 27px;">
              <span style="margin-right: 20px;font-size: 18px;user-select: none;">
                <i class="iconfont icon-dianzan" @click="likeOrDislike(firstComment)"
                  :style="{ color: firstComment.isLike ? '#fc5531' : '#999aaa' }">
                  {{ firstComment.likeNum }}
                </i>
              </span>
              <span style="margin-right: 20px;font-size: 18px;" class="pinglun">
                <!--TODO 二级评论-->
                <i class="iconfont icon-pinglun" @click="openInputBox(null,firstComment)"></i>
              </span>
            </div>
          </div>

          <div v-for="secondComment in firstComment.children" :key="secondComment.id"
            style="min-height: 100px;padding: 10px 0;display: flex">
            <div style="min-width: 40px">
              <el-avatar :src="secondComment.avatarUrl">头像</el-avatar>
            </div>
            <div style="flex: 1; padding: 5px;">
              <div style="display: flex; margin-top: 5px">
                <div style="flex: 1;min-height: 30px;">{{ secondComment.nickname }} 回复
                  {{ secondComment.replyUserNickname }}</div>
                <div style="flex: 1"></div>
              </div>
              <div style="line-height: 30px;min-height: 30px;table-layout:fixed; word-break:break-all; overflow:hidden;">
                <span v-html="secondComment.commentContent"></span>
              </div>
              <div style="display: flex; margin-top: 10px">
                <div style="flex: 1;font-size: 14px">
                  <span style="margin-right: 20px;white-space: nowrap">
                    <i class="iconfont icon-shijian1">{{ secondComment.createTime }}</i>
                  </span>
                </div>
                <div style="flex: 1;text-align: right">
                  <span style="margin-right: 20px;user-select: none">
                    <i class="iconfont icon-dianzan" :style="{ color: secondComment.isLike ? '#fc5531' : '#999aaa' }"
                      @click="likeOrDislike(secondComment)">
                      {{ secondComment.likeNum }}
                    </i>
                  </span>
                  <span style="margin-right: 20px" class="pinglun">
                    <!--TODO 二级评论-->
                    <i class="iconfont icon-pinglun" @click="openInputBox(secondComment,firstComment)"></i>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div v-if="firstComment.replyTotalCount > 3 && firstComment.children.length !== firstComment.replyTotalCount">
            <span @click="showMore(firstComment)" style="margin-left: 50px; cursor: pointer" class="viewMoreComment">
              还剩{{firstComment.replyTotalCount - firstComment.children.length }} 条回复,点击查看
            </span>
          </div>
          <!-- 二级评论输入框 -->
          <!-- 废物属性利用 -->
          <div v-if="firstComment.replyUserId">
            <!--<div style="display: flex;">-->
            <!--  <div style="">-->
            <!--    <el-avatar :src="currentUser.avatarUrl"></el-avatar>-->
            <!--  </div>-->
            <!--  <div style="flex: 1;padding: 0 10px">-->
            <!--    <el-input type="textarea" placeholder="请输入评论" v-model="secondCommentContent" maxlength="150" show-word-limit>-->
            <!--    </el-input>-->
            <!--  </div>-->
            <!--  <div style="display: flex;flex-direction: column">-->
            <!--    <div style="flex: 1"></div>-->
            <!--    <el-button type="primary" round @click="send">发送</el-button>-->
            <!--  </div>-->
            <!--</div>-->
            <emoji-text ref="secondCommentRef" @comment="addSecondCommentContent" :avatarUrl="currentUser.avatarUrl"></emoji-text>
          </div>
        </div>

      </div>
      <div v-if="isBottom && total > 10" style="text-align: center">到底了</div>
      <div v-if="!total">
        <p style="font-size: 50px;text-align: center">暂无评论</p>
      </div>
    </div>
  </div>
</template>

<script>
import { getClientHeight, getScrollHeight, getScrollTop } from "@/utils/MyUtils.js";
import EmojiText from "@/components/EmojiText/EmojiText.vue";
import commentApi from "@/api/commentApi.js";

export default {
  name: "Comment",
  components: {EmojiText},

  data() {
    return {
      articleId: this.$route.query.id,
      isInputReady: false, // 标记输入是否就绪
      currentUser: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      comment: {},
      secondCommentContent: '',
      commentContent: '',
      commentList: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
    }
  },
  computed: {
    isBottom() {
      return this.commentList.length === this.total
    }
  },
  created() {
    this.load()
  },
  mounted() {
    window.addEventListener('scroll', this.windowScroll) //监听页面滚动
  },
  destroyed() {
    window.removeEventListener("scroll", this.windowScroll);//销毁滚动事件
  },

  beforeRouteEnter(to, from, next) {
    next(vm => {
      //因为当钩子执行前，组件实例还没被创建
      // vm 就是当前组件的实例相当于上面的 this，所以在 next 方法里你就可以把 vm 当 this 来用了。
      window.addEventListener('scroll', vm.windowScroll) //监听页面滚动
    });
  },
  // 删除滚动监听器，建议使用beforeRouteLeave，因为destroyed()钩子在路由跳转时不会触发(加了这个就跳不了路由)
  beforeRouteLeave(to, from, next) {
    window.removeEventListener("scroll", this.windowScroll);//销毁滚动事件
    next()
  },
  methods: {
    load() {
      if(Object.keys(this.currentUser).length === 0) {
        this.currentUser.id = 0
      }
      commentApi.getByArticleId({
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        articleId: this.articleId,
        currentUserId: this.currentUser.id
      }).then(res => {
        if (res.code === '200') {
          // console.log(res)
          this.commentList = res.data.records
          this.total = res.data.total
        } else {
          // this.$message.error(res.msg)
        }
      })
    },
    showMore(comment) {
      if (comment.children.length === comment.replyTotalCount) {
        return
      }
      commentApi.getReplies({
        commentId: comment.id,
        startIndex: comment.children.length,
        count: comment.replyTotalCount - comment.children.length,
        currentUserId: this.currentUser.id

      }).then(res => {
        if (res.code === '200') {
          // console.log("成功")
          comment.children = comment.children.concat(res.data)
        }
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    addFirstComment(content) {
      if(this.currentUser.id === 0) {
        this.$message("请先登录")
        return
      }

      if (!content) {
        this.$message("评论不能为空")
        return
      }
      this.comment.parentId = null
      this.comment.replyCommentId = null
      this.comment.replyUserId = null
      this.comment.userId = this.currentUser.id
      this.comment.commentContent = content
      this.comment.likeNum = 0
      this.comment.articleId = this.articleId

      commentApi.addComment(this.comment).then(res => {
        if (res.code === '200') {
          // this.$message.success("评论成功")
          this.$refs['firstCommentRef'].clearTextareaContent()
          // 加一再减一,不然滚动加载出错哦
          this.commentList.unshift(res.data)
          if (this.total >= 10) {
            this.commentList.pop()
          }
          //这里可能有问题
          this.total += 1
        }
      })
    },
    addSecondCommentContent(content){
      if(this.currentUser.id === 0) {
        this.$message("请先登录")
        return
      }
      if (!content) {
        this.$message("评论不能为空")
        return
      }
      this.secondCommentContent = content
    },
    // 感谢gpt大哥送来的灵感
    async openInputBox(secondComment,firstComment) {
      // console.log(secondComment,firstComment)
      for(let i = 0;i<this.commentList.length;i++){
        this.commentList[i].replyUserId = false
      }
      firstComment.replyUserId = !firstComment.replyUserId
      await new Promise((resolve) => {
        // 通过监听数据绑定来等待数据输入
        const unwatch = this.$watch('secondCommentContent', (newValue) => {
          if (newValue !== '') {
            unwatch(); // 停止监听
            resolve(); // 解析 Promise
          }
        });
      });
      if(secondComment!=null){
        this.addSecondComment(secondComment,firstComment);
      }else{
        this.addSecondComment(firstComment,firstComment);
      }
      this.isInputReady = false
      firstComment.replyUserId = !firstComment.replyUserId
    },
    send(){
      this.isInputReady = true
    },
    // fistComment参数在comment是二级评论的情况下使用
    // comment参数是要回复的评论，可能是一级也可能是二级，firstComment是用来方便插入数据的，因为它有children属性
    // shi代码，但能用
    addSecondComment(comment,firstComment) {
      // console.log(this.secondCommentContent)
      let secondComment = {}
      secondComment.articleId = this.articleId
      secondComment.userId = this.currentUser.id
      secondComment.commentContent = this.secondCommentContent
      if (comment.parentId === null) {
        // 说明是comment是一级评论
        secondComment.parentId = comment.id
      } else {
        // 说明是comment的二级评论，也就是对二级评论进行评论
        secondComment.parentId = comment.parentId
      }
      secondComment.replyUserId = comment.userId
      secondComment.replyCommentId = comment.id
      commentApi.addComment(secondComment).then(res => {
        if (res.code === '200') {
          // this.$message.success("评论成功")
          // 加一再减一,不然滚动加载出错哦
          if (comment.parentId === null) {
            // 说明是comment是一级评论
            if (comment.children&&comment.replyTotalCount >= 3) {
              comment.children.pop()
            }
            if(comment.children===null){
              // console.log(comment)
              comment.children = []
              comment.children.unshift(res.data)
            }else{
              comment.children.unshift(res.data)
            }
            comment.replyTotalCount += 1
          } else {
            // 说明comment是二级评论，也就是对二级评论进行评论，也是二级评论
            if (firstComment.replyTotalCount >= 3) {
              firstComment.children.pop()
            }
            firstComment.children.unshift(res.data)

            firstComment.replyTotalCount += 1
          }
          // this.$refs['mainCommentRef'].clearTextareaContent()
          this.secondCommentContent = ''
        }
      })
    },
    likeOrDislike(comment) {
      if(this.currentUser.id === 0) {
        this.$message("请先登录")
        return
      }
      if (comment.isLike) {
        commentApi.disLike(comment.id, this.currentUser.id).then(res => {
          if (res.code === '200') {
            comment.likeNum -= 1
            comment.isLike = !comment.isLike
            this.$message.success("取消点赞成功")
          }
        })
      } else {
        commentApi.like(comment.id,this.currentUser.id).then(res => {
          if (res.code === '200') {
            comment.likeNum += 1
            comment.isLike = !comment.isLike
            this.$message.success("点赞成功")
          }
        })
      }
    },
    save() {

    },
    //回调函数
    windowScroll() {
      if (this.isBottom) {
        return
      }
      //获取三个值
      let scrollTop = getScrollTop()
      let clientHeight = getClientHeight()
      let scrollHeight = getScrollHeight()
      //如果满足公式则，确实到底了
      if (scrollTop + clientHeight === scrollHeight) {
        //发送异步请求请求数据，同时携带offset并自增offset
        //noMore是自定义变量，如果是最后一批数据则以后都不加载
        if (!this.noMore) {
          this.pageNum += 1
          commentApi.getByArticleId({
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            articleId: this.articleId,
            currentUserId: this.currentUser.id
          }).then(res => {
            if (res.code === '200') {
              // console.log(res)
              this.commentList = this.commentList.concat(res.data.records)
            } else {
              // this.$message.error(res.msg)
            }
          })
        }
        console.log("到底了")
      }
    },


  }
}
</script>

<style scoped>
.viewMoreComment:hover{
  color: #409EFF;
}
.pinglun:hover{
  color: #1e80ff;
}

</style>