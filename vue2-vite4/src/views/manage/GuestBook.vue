<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入留言昵称" prefix-icon="el-icon-search" v-model="nickname" @keydown.enter.native="load" @keydown.esc.native="reset"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load" >搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd"style="margin-right: 5px">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <template>
        <el-popconfirm
            confirm-button-text='确定'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="确定批量删除吗？"
            @confirm="handleDeleteBatch"
        >
          <el-button type="danger" slot="reference" style="margin-right: 5px">批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>
      </template>
    </div>
    <el-table :data="tableData" :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange" border stripe>
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="nickName" label="昵称"></el-table-column>
      <el-table-column prop="content" label="内容" >
        <template v-slot="scope">
          <el-popover
              placement="right"
              width="400"
              trigger="click"
              :content="scope.row.content">
              <div style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;" slot="reference">
                {{scope.row.content}}
              </div>
          </el-popover>

        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="留言时间" width="150"></el-table-column>
      <el-table-column prop="ip" label="IP地址"></el-table-column>
      
      <el-table-column prop="replyContent" label="回复内容">
        <template v-slot="scope">
          <el-popover
            placement="right"
            width="400"
            trigger="click"
            >
              <el-input v-model="scope.row.replyContent"
                type="textarea"
                placeholder="回复内容"
                rows="10" 
                maxlength="1000"
                show-word-limit
                @change="reply(scope.row)"
                >
              </el-input>
            <div v-if="scope.row.replyContent" slot="reference" class="text-oneLine-omit" style="user-select: none;cursor: pointer">{{ scope.row.replyContent }}</div>
            <el-button v-else slot="reference" style="float: right;">回复</el-button>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="replyUserName" label="回复人昵称"></el-table-column>
      <el-table-column prop="replyTime" label="回复时间"></el-table-column>
      <el-table-column prop="replied" label="是否回复">
        <template slot-scope="scope">
          <el-switch
            disabled
            v-model="scope.row.replied"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="enable" label="是否展示">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.enable"
            active-color="#13ce66"
            inactive-color="#ff4949"
            @change="handleEnableChange(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template v-slot="scope">
          <el-popconfirm
              style="margin-left: 5px"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="handleDelete(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除<i class="el-icon-delete"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
          :current-page="pageNum"
          :page-sizes="[2 ,5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <!-- 新增留言弹窗 -->
    <el-dialog title="新增留言" :visible.sync="addDialog" width="400px">
      <el-form label-width="80px" size="small">
        <el-input 
            type="text" 
            placeholder="留言人" 
            v-model="form.nickName" 
            maxlength="10" 
            show-word-limit>
          </el-input>
          <div style="margin: 20px 0;"></div>
          <el-input 
          type="textarea"
          placeholder="留言内容"
          v-model="form.content"
          rows="10" 
          maxlength="1000"
          show-word-limit>
          </el-input>  
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialog = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import leaveWordApi from "@/api/leaveWordApi.js";
import manage from "@/views/manage/Manage.vue";

export default {
  name: "GuestBook",
  data() {
    return {
      currentUser: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      pageNum: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      addDialog: false,
      multipleSelection: [],
      form: {},
      nickname: '',
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      leaveWordApi.page(
          {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            nickName: this.nickname
          },
          "manage"
      ).then(res=>{
        if(res.code=== '200'){
          this.tableData = res.data.records
          this.total = res.data.total
        }
      })
    },
    reset(){
      this.nickname = ''
      this.load()
    },
    handleAdd(){
      this.addDialog = true
      this.form = {}
    },
    handleSelectionChange(val){
      this.multipleSelection = val
    },
    handleDelete(id){
      leaveWordApi.deleteById(id).then(res=>{
        if (res.code === '200'){
          this.$message.success("删除成功");
        }else{
          this.$message.error("删除失败")
        }
        this.load()
      })
    },
    handleDeleteBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      leaveWordApi.deleteBatch(ids).then(res=>{
        if (res.code === '200'){
          this.$message.success("批量删除成功");
        }else{
          this.$message.error("批量删除失败")
        }
        this.load()
      })
    },
    reply(row){
      if(row.replyContent === null){
        return 
      }
      row.replyUserId = this.currentUser.id
      row.replied = true
      leaveWordApi.reply(row).then(res=>{
        if (res.code === '200'){
          this.$message.success("回复成功");
        }else{
          this.$message.error("回复失败")
        }
        this.load()
      })
    },
    save(){
      leaveWordApi.leaveMessage(this.form).then(res=>{
        if (res.code === '200'){
          this.$message.success("添加成功");
          this.addDialog=false
        }else{
          this.$message.error("添加失败")
        }
        this.load()
      })
    },
    handleEnableChange(row){
      leaveWordApi.show({
        id: row.id,
        enable: row.enable
      }).then(res=>{
        if (res.code === '200'){
          this.$message.success("操作成功");
        }else{
          this.$message.error("操作失败")
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

</style>