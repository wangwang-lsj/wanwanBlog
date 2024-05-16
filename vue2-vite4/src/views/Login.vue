<template>
  <div class="LoginBg">
    <!--<vue-particles class="vue-particles" color="#8EB5C9" :particleOpacity="0.7" :particlesNumber="150" shapeType="star"-->
    <!--               :particleSize="4" linesColor="#8EB5C9" :linesWidth="1" :lineLinked="true" :lineOpacity="0.4" :linesDistance="150"-->
    <!--               :moveSpeed="3" :hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="push" ref="particles">-->
    <!--</vue-particles>-->
    <div style="" class="Login">
      <div style="text-align: center; font-size: 30px;margin: 20px auto;font-family: 'Microsoft YaHei UI'">登陆</div>
      <el-form :model="loginForm" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input placeholder="请输入账号" @keydown.enter.native="login" auto-complete="on" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" @keydown.enter.native="login" auto-complete="on" size="medium" style="margin: 10px 0" :show-password="true" prefix-icon="el-icon-lock" v-model="loginForm.password" ></el-input>
        </el-form-item>
        <el-form-item prop="code">
          <div style="display: flex">
            <el-input placeholder="请输入验证码" @keydown.enter.native="login" auto-complete="on" size="medium" style="margin: 10px 0;width: 150px" prefix-icon="el-icon-wind-power" v-model="loginForm.code" ></el-input>
            <div style="padding: 10px 0" @click="refreshCode"><Identify :identifyCode="identifyCode"></Identify></div>
          </div>
        </el-form-item>
        <div style="margin: 6px 0; text-align: right">
          <el-form-item>
            <el-button type="warning" size="small" @click="$router.push('/register')">前去注册</el-button>
            <el-button type="primary" size="small" @click="login">登陆</el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import user from "@/views/manage/User.vue";
import {setRoutes} from "@/router";
import Identify from "@/components/Identify.vue";
import userApi from "@/api/userApi.js";


export default {
  name: "Login",
  components: {
    Identify
  },
  data(){
      var validateName = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (value.length < 3 ||value.length>12){
            callback(new Error('密码长度在3到12之间'))
          }
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (value.length < 5 ||value.length>12){
            callback(new Error('密码长度在5到12之间'))
          }
          callback();
        }
      };
      var validateCode = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入验证码'));
        } else {
          if (value ===this.identifyCode||value === this.identifyCode.toLowerCase()){
            callback();
          }
          callback(new Error('验证码错误'))
        }
      };
    return{
      loginForm:{
        username: '',
        password: '',
        code: '',
      },
      rules: {
        username: [
          {validator: validateName,trigger: 'blur'},
        ],
        password: [
          {validator: validatePass,trigger: 'blur'},
        ],
        code: [
          {validator: validateCode,trigger: 'blur'},
        ],
      },
      identifyCode: '',
      identifyCodes: '23456789ABCDEFJHIJKLNPQRSDUVWXYZ'
    }
  },
  mounted () {
    // 初始化验证码
    this.refreshCode()
  },
  methods:{
    refreshCode () {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode (o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    },
    randomNum (min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    login(){
      this.$refs['userForm'].validate((valid) => {
        if (valid) {
          userApi.login(this.loginForm).then(res=>{
            if(res.code === "200"){
              if(res.data.avatarUrl === null){
                res.data.avatarUrl = "/史蒂夫.png"
              }
              //res是对象
              localStorage.setItem("user",JSON.stringify(res.data))
              localStorage.setItem("menus",JSON.stringify(res.data.menus))
              setRoutes()
              this.$message.success("登陆成功")
              if(res.data.role=== "ADMIN"){
                this.$router.push("/manage/home")
              }else{
                this.$router.push("/")
              }
            }else{
              this.$message.error(res.msg);
            }
          }).catch((error)=>{
            console.log(error)
          })
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
.LoginBg{
  user-select: none;
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background-image: linear-gradient(to bottom right ,#FC4668,#3F5EFB);
  position: relative;
  display: flex;
  justify-content: center; /* 在水平方向上居中 */
  align-items: center; /* 在垂直方向上居中 */
}
#particles-js{
  position: absolute; /* 为防止页面有其他的内容时会挤下去 */
  height: 100vh;
  width: 100%;
  overflow: hidden;
}
.Login{
  width: 350px;
  height: 400px;
  background-color: #fff;
  border-radius: 10px;
  padding: 20px;
  z-index: 999;
}
</style>