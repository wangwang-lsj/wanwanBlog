<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入用户名" prefix-icon="el-icon-search" v-model="username"></el-input>
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入昵称" prefix-icon="el-icon-search" v-model="nickname"></el-input>
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入邮箱" prefix-icon="el-icon-messageApi" v-model="email"></el-input>
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入手机号" prefix-icon="el-icon-messageApi" v-model="phone"></el-input>
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入地址" prefix-icon="el-icon-position" v-model="address"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
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
      <el-upload :action="userApi.importExcel()"
                 accept="xlsx"
                 :on-success="handleUploadSuccess"
                 :on-error="handleUploadError"
                 :show-file-list="false" style="display: inline-block; margin-right: 5px">
        <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="handleExport">导出 <i class="el-icon-top"></i></el-button>
    </div>
    <el-table :data="tableData" :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange" border stripe>
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="60"></el-table-column>
      <el-table-column prop="role" label="角色" width="140">
        <template slot-scope="scope">
          <el-tag type="primary" v-for="item in roles" :key="item.id" v-if="scope.row.roleId === item.id">{{item.name}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="140"></el-table-column>
      <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="warning" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              style="margin-left: 5px"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="handleDelete(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-delete"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
          :current-page="pageNum"
          :page-size="pageSize"

          :page-sizes="[2 ,5, 10, 15, 20]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="20%">
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名" >
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" >
          <el-select clearable v-model="form.roleId" placeholder="请选择角色" style="width: 100%">
            <el-option v-for="item in roles" :key="item.name" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="昵称" >
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" >
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" >
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" >
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import userApi from "@/api/userApi.js";
import roleApi from "@/api/roleApi.js";

export default {
  name: "User",
  computed: {
    userApi() {
      return userApi
    }
  },
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      username: "",
      nickname: "",
      email: "",
      phone: "",
      address: "",
      dialogFormVisible: false,
      form:{},
      multipleSelection:[],
      roles:[],
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      userApi.queryPage({
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          username:this.username,
          nickname:this.nickname,
          email:this.email,
          phone:this.phone,
          address:this.address
      }).then(res=>{
        if(res.code==='200'){
          this.tableData = res.data.records
          this.total = res.data.total
        }else {
          this.$message.error(res.msg)
        }
      }).catch((error)=>{
        console.log(error)
      })
      roleApi.queryRoles().then(res=>{
        if(res.code==='200'){
          this.roles = res.data
        }
      }).catch((error)=>{
        console.log(error)
      })
    },
    reset(){
      this.username=""
      this.nickname=""
      this.email=""
      this.phone=""
      this.address=""
      this.load()
    },
    handleSizeChange(pageSize) {
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.tableData = []
      this.pageNum=pageNum
      this.load()
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    handleEdit(row){
      this.form = row
      this.dialogFormVisible = true
      this.load()
    },
    save(){
      userApi.saveOrUpdate(this.form).then(res=>{
        if (res.code === '200'){
          this.$message.success("添加成功");
          this.dialogFormVisible=false
        }else{
          this.$message.error("添加失败")
        }
        this.load()
      }).catch((error)=>{
        console.log(error)
      })
    },
    handleDelete(id){
      userApi.deleteById(id).then(res=>{
        if (res.code === '200'){
          this.$message.success("删除成功");
        }else{
          this.$message.error("删除失败")
        }
        this.load()
      }).catch((error)=>{
        console.log(error)
      })
    },
    handleDeleteBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      if(ids.length === 0){
        this.$message.error("请选择要删除的数据")
        return
      }
      userApi.deleteBatch(ids).then(res=>{
        if (res.code === '200'){
          this.$message.success("批量删除成功");
        }else{
          this.$message.error("批量删除失败")
        }
        this.load()
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    handleExport(){
      userApi.exportExcel()
    },
    handleUploadSuccess(){
      console.log()
      this.$message.success("上传成功")
      this.load()
    },
    handleUploadError(err, file, fileList){
      console.log(err)
      console.log(file)
      console.log(fileList)
      this.$message.success("上传失败")
    },
    // handleMultiDelete(){
    //   for (let i = 0; i < this.multipleSelection.length;i++){
    //     this.handleDelete(this.multipleSelection[i].id)
    //   }
    //   this.load()
    // },


  }
}
</script>

<style>
.headerBg{
  /* !important增加权重*/
  background: #eee !important;
}
</style>