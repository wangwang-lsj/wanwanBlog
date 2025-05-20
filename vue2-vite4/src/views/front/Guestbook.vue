<template>
  <div class="GuestBookBg">
    <div style="display: flex;justify-content: center;" :style="windowWidth<1200?'flex-direction: column':''">
      <!--留言弹幕-->
      <div style="flex: 1;max-width: 300px;" v-if="windowWidth>=1200"></div>
      <!--留言板-->
      <div style="flex: 2;max-width: 1000px;min-height: 100vh" :style="windowWidth<768?'padding: 0':'padding: 0 10px'">
        <div class="card" style="max-width: 1000px;">
          <div class="block" style="text-align: left;">
            <el-timeline>
              <el-timeline-item :timestamp="item.createTime" placement="top" v-for="item in messageList" :key="item.id">
                <div style="display: flex">
                  <el-card style="border-radius: 10px;flex: 1;" body-style="padding: 10px;">
                    <h4>留言人：{{ item.nickName }}</h4>
                    <div style="word-wrap: break-word;word-break: break-all">{{item.content}}</div>
                  </el-card>
                  <el-card style="border-radius: 10px;flex: 1;" v-if="item.replyUserName" body-style="padding: 10px;">
                    <h4>回复人：{{ item.replyUserName }}</h4>
                    <div style="word-wrap: break-word;">{{item.replyContent}}</div>
                    <p style="font-size: 13px; text-align: right; color: #909399">{{item.replyTime}}</p>
                  </el-card>
                </div>
              </el-timeline-item>
            </el-timeline>
            <div class="block">
              <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="pageNum"
                  :page-sizes="[10, 20,1000]"
                  :page-size="pageSize"
                  background
                  small
                  layout="total, prev, pager, next,jumper"
                  :total="total">
              </el-pagination>
            </div>
          </div>
        </div>
      </div>
      <!--留言-->
      <div style="flex: 1;padding: 0 10px;" :style="windowWidth<1200?'max-width:100%;margin-top: 10px':'max-width: 300px'">
        <div style="top: 70px;" :style="windowWidth<1200?'position: static':'position: fixed;'">
          <div class="card" style="text-align: right;">
            <el-form>
              <el-form-item label="">
                <el-input type="text"
                          placeholder="留言人"
                          v-model="caller"
                          maxlength="10"
                          show-word-limit>
                </el-input>
              </el-form-item>
              <el-form-item label="">
                <el-input type="textarea"
                          placeholder="留言内容"
                          v-model="leaveMessage"
                          rows="10" maxlength="1000"
                          show-word-limit>
                </el-input>
              </el-form-item>
            </el-form>
            <el-button type="primary" round  style="margin-top: 10px;" @click="sendLeaveMessage">留言</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import messageApi from "@/api/messageApi.js";

export default {
  name: 'GuestBook',
  data() {
    return {
      caller: '',
      leaveMessage: '',
      pageNum: 1,
      pageSize: 10,
      total: 10,
      messageList:[],
    }
  },
  computed: {
    windowWidth(){
      return this.$store.state.windowWidth
    }
  },
  created() {
    this.load()
  },
  mounted() {
    // window.addEventListener('resize', this.handleResize);
  },
  destroyed() {
    // window.removeEventListener('resize', this.handleResize);
  },

  // beforeRouteEnter(to,from,next){
  //   next(vm => {
  //     //因为当钩子执行前，组件实例还没被创建
  //     // vm 就是当前组件的实例相当于上面的 this，所以在 next 方法里你就可以把 vm 当 this 来用了。
  //     window.addEventListener('resize', vm.handleResize);
  //   });
  //
  // },
  // // 删除滚动监听器，建议使用beforeRouteLeave，因为destroyed()钩子在路由跳转时不会触发(加了这个就跳不了路由)
  // beforeRouteLeave(to,from,next) {
  //   window.removeEventListener('resize', this.handleResize);
  //   next()
  // },
  methods: {
    load(){
      messageApi.queryPage({
            pageNum: this.pageNum,
            pageSize: this.pageSize,
      }).then(res => {
        this.messageList = res.data.records
        this.total = res.data.total
      })
    },
    sendLeaveMessage(){
      // console.log(this.caller);
      messageApi.createMessage({
        nickName:this.caller,
        content:this.leaveMessage
      }).then(res=>{
        if(res.code === '200'){
          this.$message.success("留言成功")
          this.caller = ''
          this.leaveMessage = ''
        }else{
          this.$message.error("留言失败")
        }
        this.load()
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum=pageNum
      this.load()
    },
  }

}
</script>

<style scoped>
.GuestBookBg{
  min-height: 100vh;
  padding: 10px 0;
}

</style>
