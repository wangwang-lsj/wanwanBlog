<template>
  <el-card style="width: 500px;">
    <el-form label-width="80px" size="small">
      <el-upload
          class="avatar-uploader"
          :action="uploadURl"
          :show-file-list="false"
          :on-success="handleAvatarSuccess">
        <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
      <el-form-item label="用户名" >
        <el-input v-model="form.username" autocomplete="off" disabled></el-input>
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
        <el-input type="textarea" v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">确认</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import userApi from "@/api/userApi.js";
import fileApi from "@/api/fileApi.js";

export default {
  name: "Person",

  data(){
    return {
      uploadURl: fileApi.upload(),
      form: {},
      user: localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
    }
  },
  created() {
    this.getUser()
  },
  methods:{
    getUser(){
      userApi.queryByName(this.user.username).then((res)=>{
        if(res.code === '200'){
          this.form = res.data
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    save(){
      userApi.saveOrUpdate(this.form).then(res=>{
        if (res.code === '200'){
          this.$emit("refreshUser")
          this.user.avatarUrl = this.form.avatarUrl
          this.user.nickname = this.form.nickname
          localStorage.setItem("user",JSON.stringify(this.user))
          this.$message.success("保存成功");
        }else{
          this.$message.error("保存失败")
        }
        this.getUser()
      })
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res.data
    },
  }
}
</script>

<style scoped>
  .avatar-uploader{
    text-align: center;
    padding-bottom: 10px;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100%;
    height: 138px;
    line-height: 138px;
    text-align: center;
  }
  .avatar {
    width: 100%;
    height: 138px;
    display: block;
    border-radius: 10px;
  }
</style>