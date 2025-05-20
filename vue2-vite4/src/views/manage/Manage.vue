<template>
  <el-container style="min-height: 100vh;">
    <el-aside :width="sideWidth+'px'" style="box-shadow: 2px 0 6px rgb(0 21 41);position: relative;z-index: 1000">
      <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container style="min-height: 100vh">
      <el-header style="border-bottom:1px solid gray; height: 60px;position: sticky;top: 0;z-index: 999; background-color: #e6e6e6">
        <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" :user="user"></Header>
      </el-header>

      <el-main>
        <router-view @refreshUser="getUser"/>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
import userApi from "@/api/userApi.js";
export default {
  components:{
    Aside,
    Header,
  },
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse:false,
      sideWidth: 200,
      user: localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},

    }
  },
  created() {
    this.getUser()
  },
  methods:{
    collapse(){
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWidth=64
        this.collapseBtnClass='el-icon-s-unfold'
      }else {
        this.sideWidth=200
        this.collapseBtnClass='el-icon-s-fold'
      }
    },
    getUser(){
      userApi.queryByName(this.user.username).then((res)=>{
        if(res.code === '200'){
          this.user = res.data
        }else {
          this.$message.error(res.msg)
        }
      })
    },

  }
};
</script>
<style>

</style>