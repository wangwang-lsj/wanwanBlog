<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入一级菜单名称" prefix-icon="el-icon-search" v-model="name"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd(null)"style="margin-right: 5px">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
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
    <el-table :data="tableData"
              :header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange"
              border
              stripe
              row-key="id"
              default-expand-all
    >
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="id" label="ID" width="80px"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="path" label="路径"></el-table-column>
      <el-table-column prop="pagePath" label="页面路径"></el-table-column>
      <el-table-column label="图标" width="80px" class-name="fontSize30" align="center" label-class-name="fontSize12">
        <template slot-scope="scope">
          <i :class="scope.row.icon"></i>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column prop="sortNum" label="顺序"></el-table-column>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleAdd(scope.row.id)" v-if="!scope.row.pid&&!scope.row.path">新增子菜单 <i class="el-icon-plus"></i></el-button>
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
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
    <!--<div style="padding: 10px 0">-->
    <!--  <el-pagination-->
    <!--      @current-change="handleCurrentChange"-->
    <!--      @size-change="handleSizeChange"-->
    <!--      :current-page="pageNum"-->
    <!--      :page-sizes="[2 ,5, 10, 15, 20]"-->
    <!--      :page-size="pageSize"-->
    <!--      layout="total, sizes, prev, pager, next, jumper"-->
    <!--      :total="total">-->
    <!--  </el-pagination>-->
    <!--</div>-->
    <el-dialog title="菜单信息" :visible.sync="dialogFormVisible" width="20%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="路径" >
          <el-input v-model="form.path" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="页面路径" >
          <el-input v-model="form.pagePath" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标" >
          <el-select clearable v-model="form.icon" placeholder="请选择" style="width: 100%">
            <el-option v-for="item in iconOptions" :key="item.name" :label="item.name" :value="item.value"><i :class="item.value"></i>{{item.name}}</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" >
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="顺序" >
          <el-input v-model="form.sortNum" autocomplete="off"></el-input>
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

import menuApi from "@/api/menuApi.js";

export default {
  name: "User",
  data(){
    return{
      tableData: [],
      total: 0,
      // pageNum: 1,
      // pageSize: 10,
      name: "",
      dialogFormVisible: false,
      form:{},
      multipleSelection:[],
      iconOptions:[],
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      menuApi.getByName(this.name).then(res=>{
        if(res.code==='200'){
          this.tableData = res.data
          this.total = res.data.total
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    reset(){
      this.name=""
      this.load()
    },
    // handleSizeChange(pageSize) {
    //   this.pageSize=pageSize
    //   this.load()
    // },
    // handleCurrentChange(pageNum) {
    //   this.pageNum=pageNum
    //   this.load()
    // },
    handleAdd(pid){
      this.dialogFormVisible=true
      this.form={}
      if(pid){
        this.form.pid = pid
      }
      menuApi.getIcons().then(res=>{
        if(res.code==='200'){
          this.iconOptions = res.data
        }
      })
    },
    handleEdit(row){
      this.form = row
      this.dialogFormVisible = true
      menuApi.getIcons().then(res=>{
        if(res.code==='200'){
          this.iconOptions = res.data
        }
      })
    },
    handleDelete(id){
      menuApi.deleteById(id).then(res=>{
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
      if(ids.length === 0){
        this.$message.error("请选择要删除的数据")
        return
      }
      menuApi.deleteBatch(ids).then(res=>{
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
    save(){
      menuApi.saveOrUpdate(this.form).then(res=>{
        if (res.code === '200'){
          this.$message.success("添加成功");
          this.$emit("refreshMenus")
          this.dialogFormVisible=false
        }else{
          this.$message.error("添加失败")
        }
        this.load()
      })
    },
  }
}
</script>

<style>
.fontSize12{
  font-size: 12px;
}
.fontSize30{
  font-size: 30px;
}
</style>