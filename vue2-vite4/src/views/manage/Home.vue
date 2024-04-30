<template>
  <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="轮播图管理" name="first"><HomeSlider></HomeSlider></el-tab-pane>
    <el-tab-pane label="主页文章管理" name="second">主页文章管理</el-tab-pane>
  </el-tabs>
</template>

<script>
import HomeSlider from "@/views/manage/HomeSlider.vue";

export default {
  name: "Home",
  data() {
    return {
      activeName: 'first'
    };
  },
  components:{
    HomeSlider
  },
  methods: {
    handleClick(tab, event) {
      // console.log(tab, event);
    }
  }
}
</script>

<style scoped>

</style>