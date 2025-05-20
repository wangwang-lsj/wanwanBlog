<template>
  <div>
    <el-input style="width: 200px;margin-right: 10px" placeholder="请输入文件名" prefix-icon="el-icon-search" v-model="name"></el-input>
    <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
    <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handUpload" style="display: inline-block; margin-right: 5px">上传文件 <i class="el-icon-top"></i></el-button>
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
    </div>
    <el-table :data="tableData" :header-cell-class-name="headerBg" @selection-change="handleSelectionChange" border stripe>
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="name" label="文件名称" width="400">
      </el-table-column>
      <el-table-column prop="type" label="文件类型" width="80">
      </el-table-column>
      <el-table-column prop="size" label="文件大小(kb)">
        <template v-slot="scope">
          {{scope.row.size}}kb
        </template>
      </el-table-column>

      <el-table-column label="预览">
        <template v-slot="scope">
          <!--<el-image v-if="scope.row.type==='png'||'jpg'||'gif'" :src="scope.row.url" :preview-src-list="[scope.row.url]"></el-image>-->
          <el-button type="primary" @click="preview(scope.row.url)">预览</el-button>
        </template>
      </el-table-column>
      <el-table-column label="下载">
        <template v-slot="scope">
          <el-button type="primary" @click="handleDownload(scope.row.url)">下载</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="url" label="地址" width="250px">

      </el-table-column>
      <el-table-column prop="enable" label="启用">
        <template v-slot="scope">
          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc" @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="上传日期" width="150">
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
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
    <el-dialog title="文件信息" :visible.sync="dialogFormVisible" width="20%">
      <el-form label-width="80px" size="small">
        <el-form-item label="文件名" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog class="fileUpload"title="文件上传" :visible.sync="uploadDialogFormVisible" style="min-width: 500px">
      <el-upload :action="uploadURl"
                 :on-success="handleFileUploadSuccess"
                 :on-error="handleFileUploadError"
                 :show-file-list="true"
                 drag
                 :limit="10"
                 multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">上传所需文件，一次上传最多10个文件，单个文件且不超过100MB，总上传不超过1000MB，</div>
      >
      </el-upload>

    </el-dialog>
  </div>
</template>

<script>
import fileApi from "@/api/fileApi.js";
import {serverIp} from "../../../public/config.js";

export default {
  name: "File",

  data(){
    return{
      uploadURl: fileApi.upload(),
      headerBg: 'headerBg',
      tableData:[],
      name: '',
      multipleSelection: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      dialogFormVisible: false,
      uploadDialogFormVisible: false,
      form: {},
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      fileApi.queryPage(
          {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            name: this.name
          }
      ).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.records
          this.total = res.data.total
        }else {
          this.$message.error(res.msg)
        }

      })
    },
    reset() {
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    handleEdit(row){
      this.form = row
      this.form.name = this.form.name.replace(/\.[^.]+$/,"")
      this.dialogFormVisible = true
    },
    save(){
      let filename = this.form.name
      filename = filename.endsWith('.'+this.form.type)?filename:filename.concat(".",this.form.type)
      this.form.name = filename
      fileApi.modify(this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("编辑成功");
        } else {
          this.$message.error("编辑失败")
        }
        this.load()
      })
    },
    handleDelete(id) {
      fileApi.deleteById(id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功");
        } else {
          this.$message.error("删除失败")
        }
        this.load()
      })
    },
    handleDeleteBatch() {
      let ids = this.multipleSelection.map(v => v.id)
      if(ids.length === 0){
        this.$message.error("请选择要删除的数据")
        return
      }
      fileApi.deleteBatch(ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功");
        } else {
          this.$message.error("批量删除失败")
        }
        this.load()
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    handleFileUploadSuccess(res){
      this.$message.success("上传成功")
      console.log(res)
      this.load()
    },
    handleFileUploadError(res){
      this.$message.error("上传失败")
      console.log(res)
      this.load()
    },
    handleDownload(url){
      fileApi.download(url)
    },
    changeEnable(row){
      fileApi.modify(row).then(res=>{
        if(res.code === '200'){
          this.$message.success("操作成功")
        }
      })
    },
    handUpload(){
      this.uploadDialogFormVisible = true
    },
    preview(url) {
      window.open('http://'+serverIp+':8012/onlinePreview?url=' + encodeURIComponent(window.btoa((url))))
    },

  }
}
</script>

<style>

</style>