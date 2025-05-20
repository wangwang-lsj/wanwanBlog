<template>
  <div>
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
      <el-table-column prop="name" label="轮播图名"></el-table-column>

      <el-table-column prop="url" label="预览" width="150">
        <template v-slot="scope">
          <el-image :src="scope.row.url" :preview-src-list="[scope.row.url]"></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="url" label="地址"></el-table-column>
      <el-table-column prop="sortNum" label="顺序"></el-table-column>
      <el-table-column prop="enable" label="启用">
        <template v-slot="scope">
          <el-switch
              v-model="scope.row.enable"
              @change="changeEnable(scope.row)"
              active-color="#13ce66"
              inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>

      <el-table-column prop="updateTime" label="更新时间"></el-table-column>
      <el-table-column prop="createTime" label="新增时间"></el-table-column>
      <el-table-column label="操作" width="280" align="center">
        <template v-slot="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
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

    <el-dialog title="创建轮播图" :visible.sync="createFormVisible" width="20%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" >
          <el-input v-model="form.url" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="顺序" >
          <el-input v-model="form.sortNum" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="createFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="create">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改轮播图" :visible.sync="modifyFormVisible" width="20%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" >
          <el-input v-model="form.url" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="顺序" >
          <el-input v-model="form.sortNum" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="modify">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import homeApi from "@/api/homeApi.js";

export default {
  name: "HomeSliderShow",
  data() {
    return {
      tableData: [],
      createFormVisible: false,
      modifyFormVisible: false,
      multipleSelection: [],
      form: {},
      name: '',
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      homeApi.queryAll("manage").then(res=>{
        if(res.code=== '200'){
          this.tableData = res.data
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    reset(){
      this.name = ''
      this.load()
    },
    handleAdd(){
      this.createFormVisible = true
      this.form = {}
    },
    handleSelectionChange(val){
      this.multipleSelection = val
    },
    handleEdit(row){
      this.modifyFormVisible = true
      this.form = row
    },
    handleDelete(id){
      homeApi.deleteById(id).then(res=>{
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
      homeApi.deleteBatch(ids).then(res=>{
        if (res.code === '200'){
          this.$message.success("批量删除成功");
        }else{
          this.$message.error("批量删除失败")
        }
        this.load()
      })
    },
    create(){
      homeApi.create(this.form).then(res=>{
        if (res.code === '200'){
          this.$message.success("添加成功");
          this.createFormVisible=false
        }else{
          this.$message.error("添加失败")
        }
        this.load()
      })
    },
    modify(){
      homeApi.modify(this.form).then(res=>{
        if (res.code === '200'){
          this.$message.success("添加成功");
          this.modifyFormVisible=false
        }else{
          this.$message.error("添加失败")
        }
        this.load()
      })
    },
    changeEnable(row){
      homeApi.updateShow({
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
    }
  }
}
</script>

<style scoped>

</style>