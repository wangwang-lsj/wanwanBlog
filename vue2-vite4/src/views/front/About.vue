<template>
  <div class="AboutBg">
    <!--<vue-particles-->
    <!--    color="#39AFFD"-->
    <!--    :particleOpacity="0.7"-->
    <!--    :particlesNumber="100"-->
    <!--    shapeType="circle"-->
    <!--    :particleSize="4"-->
    <!--    linesColor="#8DD1FE"-->
    <!--    :linesWidth="1"-->
    <!--    :lineLinked="true"-->
    <!--    :lineOpacity="0.4"-->
    <!--    :linesDistance="150"-->
    <!--    :moveSpeed="3"-->
    <!--    :hoverEffect="true"-->
    <!--    hoverMode="grab"-->
    <!--    :clickEffect="true"-->
    <!--    clickMode="push"-->
    <!--&gt;</vue-particles>-->
    <div class="FlexContent">
      <div class="LeftPart">
        <div class="BigBlock">
          <div class="TitleFontLine">博客特色</div>
          <p><i class="iconfont icon-aixin" style="color: red"></i>游客经过极简风的注册/登录后，可查看并不精美的主页，和乱糟糟的文章列表布局，和简陋至极的留言板，和</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>实现了二级评论，游客登录后可对文章进行评论</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>简单的实现了响应式布局，可以在电脑和手机上游览博客</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>游客可对网站进行留言，欢迎留言并提供建议，博主也可对留言进行回复</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>目前初具雏形吧，博客潜力巨大。。。。。。</p>
        </div>
        <div class="BigBlock" style="margin-top: 16px;">
          <div class="TitleFontLine">技术实现</div>
          <p><i class="iconfont icon-aixin" style="color: red"></i>vue2构建工具为vite，用户端手写的UI组件 + Vue.js，管理端使用ElementUI + Vue.js</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>服务端语言使用Java、框架使用Springboot，博客部署在Nginx</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>数据库使用MySql</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>由阿里云服务器部署</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>使用宝塔面板进行管理</p>
        </div>
        <div class="BigBlock" style="margin-top: 16px;">
          <div class="TitleFontLine">自己的饼</div>
          <p><i class="iconfont icon-aixin" style="color: red"></i>完美的实现响应式布局，在手机，平板，电脑上完全展示内容及功能</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>添加动画样式</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>实现游客不登录也能对博客内容进行完整访问</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>将Redis缓存数据库应用到每个接口，使用定时任务在夜间将Redis数据库一次存入mysql数据库</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>完善的网站统计，如文章阅读不再是简单的+1，而是根据同一天内或者其他，一个ip点击才增加一次，然后使用echart将数据展示在后台管理</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>将vue2升级成vue3，及其它相关的也升级</p>
        </div>
        <div class="BigBlock" style="margin-top: 16px;">
          <div class="TitleFontLine">关于博主</div>
          <p><i class="iconfont icon-aixin" style="color: red"></i>博主02，湖南衡阳人，软件工程本科即将毕业</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>兴趣：游戏、动漫、小说、电影</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>了解的技术有：ElementUI、Vue.js、JavaScript、java、C</p>
          <p><i class="iconfont icon-aixin" style="color: red"></i>前端布局和样式</p>
        </div>
      </div>
      <div class="RightPart">
        <div class="GitPart">
          <div style="background-color: #555555;height: 110px;"></div>
          <div style="position: absolute;top: 70px;text-align: center;width: 100%">
            <img src="/头像.jpg" style="width: 80px; border-radius: 100%">
          </div>
          <div style="background-color: #ffffff;height: 100px;">
            <div style="font-size: 18px;text-align: center;margin-top: 50px;">玩玩的Github</div>
            <a class="BlueButton" href="https://github.com/wangwang-lsj/wanwanBlog" target="_blank">博客源码</a>
          </div>
          <div class="GitStatistic">
            <div style="" class="GitStatisticItem">
              <div class="GitStatisticItemNum">0</div>
              <div class="GitStatisticItemText">Followers</div>
            </div>
            <div style="flex: 1;text-align: center" class="GitStatisticItem">
              <div class="GitStatisticItemNum">0</div>
              <div class="GitStatisticItemText">Star</div>
            </div>
            <div style="flex: 1;text-align: center" class="GitStatisticItem">
              <div class="GitStatisticItemNum">0</div>
              <div class="GitStatisticItemText">Repositories</div>
            </div>
          </div>
        </div>
        <div class="ContactMe">
          <div class="TitleFontLine">联系我</div>
          <div class="ContactMe-Link">
            <div>
              <a href="https://github.com/wangwang-lsj" target="_blank"><i class="iconfont icon-github" style="color: rgb(148, 138, 236);"></i></a>
            </div>
            <div>
              <a href="https://blog.csdn.net/weixin_52285403?spm=1010.2135.3001.5343" target="_blank"><i class="iconfont icon-csdn" style="color: rgb(247, 142, 61);"></i></a>
            </div>
            <div>
              <i class="iconfont icon-youxiang" style="color: rgb(73, 169, 238);" @click="copyToClipboard('wanwan20231113@163.com')"></i>
            </div>
            <div>
              <i class="iconfont icon-QQ" style="color:rgb(18, 150, 219);" @click="copyToClipboard('不给')"></i>
            </div>
            <div>
              <i class="iconfont icon-weixin" style="color: rgb(244, 110, 101);" @click="copyToClipboard('没得')"></i>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "About",
  data() {
    return {

    };
  },
  computed:{

  },
  methods: {
    copyToClipboard(content) {
      if (navigator.clipboard && window.isSecureContext) {
        navigator.clipboard.writeText(content)
            .then(() => {
              this.$message.success('已复制到剪切板')
              // 可以添加一些反馈，如提示用户已复制到剪切板
            })
            .catch(err => {
              console.error('Failed to copy to clipboard:', err);
              // 复制失败时可以提供一些错误提示
            });
      }else {
        // 创建text area
        const textArea = document.createElement('textarea')
        textArea.value = content
        // 使text area不在viewport，同时设置不可见
        document.body.appendChild(textArea)
        textArea.focus()
        textArea.select()
        this.$message.success('已复制到剪切板')
        return new Promise((res, rej) => {
          // 执行复制命令并移除文本框
          document.execCommand('copy') ? res() : rej()
          textArea.remove()
        })
      }

    }
  },
}
</script>

<style scoped>
.AboutBg{
  user-select: none;
  min-height: 100vh;
  width: 100%;
  position: relative; /* 为防止页面有其他的内容时会挤下去 */
}
#particles-js{
  position: absolute;
  width: 100%;
  height: 100%;
}
/*电脑样式*/
@media screen and (min-device-width: 768px){
  .FlexContent{
    display: flex;
    margin: 0 auto;
    max-width: 1200px;
    align-items: start;
    justify-content: center;
    position: relative;
  }
  .LeftPart{
    position: relative;
    margin-right: .5rem;
    width: 75%;
    z-index: 2;
    margin-top: 10px;
    margin-bottom: 10px;

  }
  .RightPart{
    display: flex;
    flex-direction: column;
    width: 25%;
    border-radius: 2px;
    margin-left: .5rem;
    position: sticky;
    top:70px;
  }
  .BigBlock{
    background-color: #ffffff;
    padding: 1rem 4rem
  }
}
/*手机样式*/
@media screen and (max-device-width: 768px){
  .FlexContent{
    display: flex;
    flex-direction: column;
    z-index: 2;
    position: relative;

  }
  .LeftPart{
    position: relative;
    margin: 1rem;
    z-index: 2;
  }
  .RightPart{
    position: relative;
    border-radius: 2px;
    margin: 1rem;
  }
  .BigBlock{
    background-color: #ffffff;
    padding: 1rem 1rem
  }
}


.TitleFontLine{
  margin-top: 16px;
  font-size: 20px;
  text-align: center;
}
.BigBlock p{
  font-size: 14px;
  margin: 2rem 0;
}


.GitPart{
  background-color: #ffffff;
  position: relative;

}
.BigBlock i{
  margin: 0 0.5rem;
}
.ContactMe{
  margin-top: 1rem;
  background-color: #ffffff;
  padding: 1rem;
}
.ContactMe-Link{
  padding-top: 2rem;
  display: flex;
  justify-content: space-around;
}
.ContactMe-Link i{
  font-size: 20px;
}
.BlueButton{
  color: #ffffff;
  cursor: pointer;
  display: block;
  margin: 20px auto;
  text-align: center;
  width: 8rem;
  padding: .5rem;
  border-radius: 5px;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 400%;
}
.BlueButton:hover {
  animation: changeColor 5s linear infinite; /* 鼠标悬浮时应用动画 */
}
@keyframes changeColor {
  0% {
    background-position: 0%;
  }
  100% {
    background-position: 100%;
  }
}
.GitStatistic{
  background-color: #ffffff;
  border-top: 1px solid #e9e9e9;
  height: 100px;
  display: flex;
  padding: 20px 0;

}
.GitStatisticItem{
  flex: 1;
  text-align: center
}
.GitStatisticItemNum{
  font-size: 20px;
  color: rgb(94, 94, 94);
  margin-bottom: .5rem;
}
.GitStatisticItemText{
  font-size: 14px;
  color: rgba(0, 0, 0, .43);
}
</style>