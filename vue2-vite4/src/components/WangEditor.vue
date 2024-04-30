<template>
  <div style="border: 1px solid #ccc" ref="wangEditor">
    <Toolbar
        style="border-bottom: 1px solid #ccc;position:sticky; top: 0;z-index: 999"
        :editor="editor"
        :mode="mode"
        :defaultConfig="toolbarConfig"
        v-if="editable"
    />
    <!--v-if="editable"-->
    <Editor
        style="min-height: 300px; overflow-y: visible"
        id="editor-text-area"
        v-model="html"
        :mode="mode"
        :defaultConfig="editorConfig"
        @onChange="onChange"
        @onCreated="onCreated"
    />
  </div>
</template>
<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import {serverIp} from "../../public/config.js";
import{Boot} from '@wangeditor/editor'
import {myButtonMenu} from "@/plugins/myButtonMenu.js";
import fileApi from "@/api/fileApi.js";
Boot.registerMenu(myButtonMenu)

export default {
  name: "WangEditor",
  components: {Editor, Toolbar },
  props: {
    content: {
      type: String,
      default: "",
    },
    editable: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      mode: 'default', // 'default' or 'simple'
      editor: null,
      html: "",
      toolbarConfig: {
        // toolbarKeys: [ /* 显示哪些菜单，如何排序、分组 */ ],
        excludeKeys: ['fullScreen'],
        insertKeys: {
          index: 35, // 插入的位置，基于当前的 toolbarKeys
          keys: ['createMenu']
        }
      },
      editorConfig: {
        placeholder: "请输入内容...",
        scroll: true,
        MENU_CONF: {
          //   配置上传图片
          uploadImage: {
            server:fileApi.upload(),
            fieldName: "file",

            // 自定义上传参数，例如传递验证的 token 等。参数会被添加到 formData 中，一起上传到服务端。
            meta: {},
            // 请求头
            // headers: { token: localStorage.getItem("token") },
            timeout: 5 * 1000, // 5s 超时时间
            //选择文件时的类型限制，默认为['image/*'] 如不想限制，则设置为 []
            allowedFileTypes: ["image/*"],
            maxFileSize: 10 * 1024 * 1024, //10MB //设置大点 不然图片过大会报错
            // 最多可上传几个文件，默认为 100
            maxNumberOfFiles: 10,
            // base64LimitSize: 1000000 * 1024, // 1g 以下插入 base64
            // 自定义插入图片
            customInsert(res, insertFn) {
              // res 即服务端的返回结果
              // console.log(res);
              // 从 res 中找到 url alt href ，然后插图图片
              insertFn(res.data, null, res.data)
            },
          },
          //   配置上传视频
          uploadVideo: {
            server:fileApi.upload(),
            // form-data fieldName ，默认值 'wangeditor-uploaded-video'
            fieldName: "file",
            // 单个文件的最大体积限制，默认为 10M
            maxFileSize: 100 * 1024 * 1024, // 5M
            // 最多可上传几个文件，默认为 5
            maxNumberOfFiles: 3,
            meta: {},

            // 请求头
            // headers: { token: localStorage.getItem("token")},
            timeout: 5 * 1000, // 5s 超时时间
            //选择文件时的类型限制，默认为['video/*'] 如不想限制，则设置为 []
            allowedFileTypes: ["video/*"],
            // 自定义插入视频
            customInsert(res, insertFn) {
              // res 即服务端的返回结果
              // console.log(res);
              // 从 res 中找到 url alt href ，然后插图图片
              insertFn(res.data, null, res.data)
            },
          },
        },
      },
    };
  },
  methods: {

    onCreated(editor) {
      this.editor = Object.seal(editor); // 一定要用 Object.seal() ，否则会报错
      // console.log(this.html)
      if(!this.editable){
        this.editor.disable()
        this.$refs.wangEditor.style.border = "0px"
        this.$emit("sendWords", this.editor.getText().length);
      }
      // console.log(this.editor.getText().length)
      this.handleMenu(editor)

    },
    //执行this.editor.disable()，这个就不会被触发
    onChange(editor) {
      // console.log(editor.children)
      // 当数据改变时，手动更新编辑器内容
      this.$emit("changeHtml", this.html);

    },
    handleMenu(editor){
      const headers = editor.getElemsByTypePrefix('header')//获取标题
      const headerContainer = document.getElementById('header-container');
      if(!headerContainer) return
      // console.log(headerContainer)
      headerContainer.innerHTML = headers.map(header => {
        const text = header.children[0].text
        // console.log(text)
        const { id, type } = header
        return `<li id="${id}" type="${type}">${text}</li>` //w组件demo，没反应
        // return `<li type="${type}"><a href="#${id}">${text}</a></li>`  //html加锚方式跳转，会被覆盖
        // return `<li id="${id}" type="${type}" @click="scrollToAnchor(${id})">${text}</li>` //直接上click

      }).join('')

    }
  },
  created() {
    // console.log(this.content)
    this.html = this.content

  },
  mounted() {
    // this.handleMenu(this.editor); // 在组件渲染完成后调用 onCreated 方法
  },
  beforeDestroy() {
    const editor = this.editor;
    if (editor == null) return;
    editor.destroy(); // 组件销毁时，及时销毁编辑器
  },
};
</script>
<style src="@wangeditor/editor/dist/css/style.css"></style>
<style>
.w-e-text-container {
  min-height: 300px !important; /*!important是重点，因为原div是行内样式设置的高度300px*/
}
.w-e-text-container [data-slate-editor]{
  min-height: 300px !important; /*!important是重点，因为原div是行内样式设置的高度300px*/
}
.w-e-scroll{
  min-height: 300px !important;
}
.w-e-textarea-video-container{
    padding: 0;
}
.w-e-textarea-video-container video{
  width: 100%;
}
.w-e-text-container pre{

}
.w-e-text-container pre>code{
}

/*目录样式 start*/

#header-container {
  list-style-type: none;
  /*padding-left: 20px;*/
}

#header-container li {
  color: #333;
  margin: 10px 0;
  cursor: pointer;
}

#header-container li:hover {
  text-decoration: underline;
}

#header-container li[type="header1"] {
  font-size: 20px;
  font-weight: bold;
}

#header-container li[type="header2"] {
  font-size: 16px;
  padding-left: 15px;
  font-weight: bold;
}

#header-container li[type="header3"] {
  font-size: 14px;
  padding-left: 30px;
}

#header-container li[type="header4"] {
  font-size: 12px;
  padding-left: 45px;
}

#header-container li[type="header5"] {
  font-size: 12px;
  padding-left: 60px;
}
/*目录样式 end*/
</style>