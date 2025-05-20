<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入角色名称" prefix-icon="el-icon-search" v-model="name"></el-input>
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
    </div>
    <el-table :data="tableData" :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange" border stripe>
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="flag" label="唯一标识"></el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column label="操作" width="280" align="center">
        <template v-slot="scope">
          <el-button type="danger" @click="handleMenu(scope.row)">分配菜单 <i class="el-icon-menu"></i></el-button>
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
          :page-sizes="[2 ,5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="新增角色" :visible.sync="createFormVisible" width="20%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="唯一标识" >
          <el-input v-model="form.flag" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" >
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="createFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="create">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改角色" :visible.sync="modifyFormVisible" width="20%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="唯一标识" >
          <el-input v-model="form.flag" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" >
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="modify">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="菜单分配" :visible.sync="menuDialogVisible" width="20%">
      <el-tree
          :props="props"
          :data="menuData"
          show-checkbox
          node-key="id"
          ref="tree"
          check-strictly
          :default-expanded-keys="expands"
          :default-checked-keys="checks">
        <span class="custom-tree-node" slot-scope="{ node, data }">
          <span><i :class="data.icon"></i> {{data.name}}</span>
        </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="menuDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import menuApi from "@/api/menuApi.js";
import roleApi from "@/api/roleApi.js";

export default {
  name: "User",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      createFormVisible: false,
      modifyFormVisible: false,
      menuDialogVisible: false,
      form:{},
      multipleSelection:[],
      menuData: [],
      props: {
        label: 'name',
      },
      checks:[],
      expands:[],
      roleId: 0,
      roleFlag: '',
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      roleApi.queryPage({
        pageNum:this.pageNum,
        pageSize:this.pageSize,
        name: this.name
      }).then(res=>{
        if(res.code==='200'){
          this.tableData = res.data.records
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
    create(){
      roleApi.create(this.form).then(res=>{
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
      roleApi.modify(this.form).then(res=>{
        if (res.code === '200'){
          this.$message.success("添加成功");
          this.modifyFormVisible=false
        }else{
          this.$message.error("添加失败")
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
    handleAdd(){
      this.createFormVisible=true
      this.form={}
    },

    handleEdit(row){
      this.form = row
      this.modifyFormVisible = true
      this.load()
    },
    handleDelete(id){
      roleApi.deleteById(id).then(res=>{
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
      roleApi.deleteBatch(ids).then(res=>{
        if (res.code === '200'){
          this.$message.success("批量删除成功");
        }else{
          this.$message.error("批量删除失败")
        }
        this.load()
      })
    },
    handleSelectionChange(val){
      this.multipleSelection = val
    },
    handleMenu(row){
      this.menuDialogVisible = true
      this.roleId = row.id
      this.roleFlag = row.flag

      menuApi.queryByName("").then(res=>{
        if(res.code==='200'){
          this.menuData = res.data
          this.expands = this.menuData.map(v=>v.id)
        }
      })
      roleApi.queryMenuById(row.id).then(res=>{
        if(res.code==='200'){
          this.$refs.tree.setCheckedKeys(res.data);
        }
      })
    },
    saveRoleMenu(){
      roleApi.createRoleMenus(this.roleId,this.$refs.tree.getCheckedKeys()).then(res=>{
        if (res.code === '200'){
          this.menuDialogVisible = false

          // 操作管理员角色的时候需要重新登录
          if(this.roleFlag === 'ADMIN'){
            this.$store.commit("logout")
          }
          this.$message.success("分配成功")
        }else {
          this.$message.error("分配失败")
        }
      })
    }
  }
}
</script>

<style>
</style>