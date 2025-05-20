<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid"></i>用户总数</div>
          <div style="padding:10px 0; text-align: center; font-weight: bold">
            {{statistics.userCount}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-money"></i>文章总数</div>
          <div style="padding:10px 0; text-align: center; font-weight: bold">
            {{statistics.articleCount}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-bank-card"></i>评论总数</div>
          <div style="padding:10px 0; text-align: center; font-weight: bold">
            {{statistics.commentCount}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #909399">
          <div><i class="el-icon-s-shop"></i>留言总数</div>
          <div style="padding:10px 0; text-align: center; font-weight: bold">
            {{statistics.leaveWordCount}}
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px"></div>
      </el-col>
      <el-col :span="12">
        <div id="pie" style="width: 500px; height: 450px"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts';
import echartsApi from "@/api/echartsApi.js";
export default {
  name: "DashBord",
  data(){
    return{
      statistics: [],
    }
  },
  mounted() {
    // let chartDom = document.getElementById('main');
    // let myChart = echarts.init(chartDom);
    let option;

    option = {
      title: {
        text: '各季度成员数量统计',
        subtext: '趋势图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ["第一季度","第二季度","第三季度","第四季度"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: '星巴克',
          data: [],
          type: 'bar'
        },
        {
          name: '星巴克',
          data: [],
          type: 'line'
        },
        {
          name: '瑞幸咖啡',
          data: [],
          type: 'bar'
        },
        {
          name: '瑞幸咖啡',
          data: [],
          type: 'line'
        }
      ]
    };

    //饼图
    // let pieChartDom = document.getElementById('pie');
    // let pieChart = echarts.init(pieChartDom);
    let pieOption;

    pieOption = {
      title: {
        text: '各季度成员数量统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '星巴克',
          type: 'pie',
          radius: '50%',
          center: ['25%', '60%'],
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          },
          label:{
            show: true,
            position: 'inner',
            formatter: '{d}%'
          }
        },
        {
          name: '瑞幸咖啡',
          type: 'pie',
          radius: '40%',
          center: ['75%', '50%'],
          data: [
            { value: 5, name: '第一季度' },
            { value: 6, name: '第二季度' },
            { value: 7, name: '第三季度' },
            { value: 8, name: '第四季度' },
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          },
          label:{
            show: true,
            position: 'inner',
            formatter: '{d}%'
          }
        }
      ]
    };
    // this.request.get("/echarts/members").then(res=>{
    //   if (res.code === '200'){
    //     option.series[0].data = res.data
    //     option.series[1].data = res.data
    //     option.series[2].data = [5,6,7,8]
    //     option.series[3].data = [5,6,7,8]
    //     pieOption.series[0].data = [
    //       { value: res.data[0], name: '第一季度' },
    //       { value: res.data[1], name: '第二季度' },
    //       { value: res.data[2], name: '第三季度' },
    //       { value: res.data[3], name: '第四季度' },
    //     ]
    //   }
    //   option && myChart.setOption(option);
    //   pieOption && pieChart.setOption(pieOption);
    // });
    echartsApi.queryStatistics().then(res=>{
      if (res.code === '200'){
        this.statistics = res.data
      }else {
        this.$message.error(res.msg)
      }
    })
  },
}

</script>

<style>

</style>