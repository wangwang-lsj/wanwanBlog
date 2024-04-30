<style lang="scss" scoped>
.avatar-img {
    object-fit: cover;
    border-radius: 50%;
    width: 100%;
    height: 100%;
    overflow: hidden;
}

textarea {
    outline: none;
    border: none;
    background: #f1f2f3;
    resize: none;
    border-radius: 8px;
    padding: 10px 10px;
    font-size: 16px;
    color: #333333;
    border: 1px solid transparent;
}

img {
    -webkit-user-drag: none;
}

.avatar {
    width: 40px;
    height: 40px;
    object-fit: cover;
}

.height80 {
    // height: 80px !important;
}

.height80 textarea {
    border: 1px solid #49b1f5;
}

@keyframes scaleUp {
    0% {
        opacity: 0;
        transform: scale(0)
    }

    100% {
        opacity: 1;
        transform: scale(1)
    }
}

.scaleUp {
    animation: scaleUp 0.3s;
    transform-origin: 0 0;
}

.comment-area {
    display: flex;
    align-items: flex-start;
    margin-bottom: 38px;

    color: #90949e;

    .comment-avatar {
        width: 48px;
        height: 48px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-right: 8px;

        i {
            font-size: 40px;
            border: 1px solid #c4c4c4;
            border-radius: 50%;

        }
    }

    .comment-right {
        flex: 1;
        display: flex;
        height: 60px;
        // transition: height 0.5s;

        position: relative;

        .edit-area {
            flex: 1;
        }

        .comment-btn {
            background-color: #49b1f5;
            cursor: pointer;
            width: 64px;
            border-radius: 8px;
            margin-left: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #fff;
        }

        .comment-tips {
            position: absolute;
            bottom: -28px;
            height: 24px;
            width: calc(100% - 72px);
            margin-right: 72px;
            display: flex;
            align-items: center;

            &>span:first-child {
                width: 20px;
                height: 20px;
                cursor: pointer;
                display: flex;
                align-items: center;
                justify-content: center;

                &.active {
                    color: #49b1f5;
                }

            }


            .emoji-wrapper {
                z-index: 9;
                user-select: none;
                position: absolute;
                bottom: 0;
                top: 28px;
                left: 0;
                display: flex;
                flex-wrap: wrap;
                width: 294px;
                height: 146px;
                overflow-y: auto;
                background-color: #fff;
                padding: 5px;
                border-radius: 6px;
                border-radius: 6px;
                box-shadow: 0 3px 6px 0 rgb(0 0 0 / 12%);
                border: 1px solid rgba(0, 0, 0, .06);

                &::before {
                    content: '';
                    position: absolute;
                }

                span.emoji {
                    width: 30px;
                    height: 30px;
                    display: block;
                    margin: 2px;
                    cursor: pointer;
                    padding: 3px;
                    border-radius: 6px;

                    img {
                        width: 100%;
                        height: 100%;
                    }

                    transition: all 0.28s;

                    &:hover {
                        background-color: #dddddd;
                    }
                }
            }

            .triangle {
                content: '';
                position: absolute;
                width: 8px;
                height: 8px;
                top: 25px;
                left: 8px;
                background-color: white;
                border: 1px solid #f0f0f0;
                transform: rotate(45deg);
                border-right-color: transparent;
                border-bottom-color: transparent;
            }
        }
    }

}
</style>

<template>
    <div class="comment-area">

        <!-- 左侧的头像 -->
        <div class="comment-avatar">
            <img v-if="avatarUrl" :src="avatarUrl" class="avatar-img">
            <i v-else class="iconfont icon-touxiang"></i>
        </div>

        <!-- 文本框 和 评论按钮 -->
        <div :class="['comment-right', { height80: height80 }]">

            <!-- 文本框 -->
            <textarea id="textarea" ref="textarea" v-model="textareaContent" @focus="height80 = true" @blur="doBlur"
                :placeholder="placeholder" class="edit-area">
            </textarea>

            <!-- 评论按钮 -->
            <div class="comment-btn" @click="postComment">评论</div>

            <!-- 表情面板 -->
            <div class="comment-tips">

                <!-- 触发表情icon -->
                <span @click="activeEmojiPanel($event, true)"
                    :class="['iconfont icon-biaoqing', { active: emojiPanelActive }]">
                </span>

                <!-- 待选择的表情列表 -->
                <div v-show="emojiPanelActive">
                    <div class="emoji-wrapper scaleUp" @click="activeEmojiPanel">
                        <span @click="addEmoji(emoji)" class="emoji" v-for="(emoji, idx) in emojiList" :key="idx">
                            <img :src="'/public'+emoji.link" alt="">
                        </span>
                    </div>

                </div>

                <!-- 三角形 -->
                <div v-show="emojiPanelActive" class="triangle"></div>
            </div>
        </div>
    </div>
</template>

<script>

/* 表情配置数据 转为 数组 */
import emojiConfig from './emoji.json'
let emojiList = []
for (let key in emojiConfig) {
    emojiList.push({
        title: key,
        link: emojiConfig[key]
    })
}

export default {
    name: 'EmojiText',
    props: {
        imgPrefix: { /* 图片路径前缀 */
            type: String,
            default: '/public'
        },
        placeholder: { /* 默认占位符 */
            type: String,
            default: '来说几句?'
        },
        avatarUrl: { /* 头像 */
            type: String
        },
        emojiSize: {
            type: Number,
            default: null
        },
        afterComment: {  /* 发表评论之后，需要执行的函数 */
            type: Function
        }
    },
    data() {
        return {

            /* 文本框中有文字 或 无文字但是处于焦点状态时 为true */
            height80: false,

            /* 表情配置数据 */
            emojiList,

            /* 是否打开表情面板 */
            emojiPanelActive: false,

            /* 文本框的内容 */
            textareaContent: '',
        }
    },
    mounted() {
        document.addEventListener('click', this.emojiListener)
    },
    destroyed() {
        document.removeEventListener('click', this.emojiListener)
    },
    methods: {
        emojiListener(){
          let _this = this
          _this.emojiPanelActive = false
        },
        /**/ 
        focus() {
            this.$refs['textarea'].focus()
        },
        /* 添加表情 */
        addEmoji(emoji) {
            let textarea = this.$refs['textarea'];

            // console.log(textarea.selectionStart, textarea.selectionEnd, 'start,end');

            // 最开始的位置要记录下，后面要根据它来设置插入文本后，设置光标的位置
            let selectionStart1 = textarea.selectionStart

            let txtArr = this.textareaContent.split('')
            txtArr.splice(textarea.selectionStart, textarea.selectionEnd - textarea.selectionStart, emoji.title)
            this.textareaContent = txtArr.join('')

            /* 一定要放在$nextTick去执行, 上面修改完值后, 还要等vue把修改的数据渲染出来之后, 再去定位光标 */
            this.$nextTick(() => {
                // 替换文本后, 需要把光标，再次定位到替换后的那个位置，否则，它会回到最前面
                textarea.focus()
                textarea.setSelectionRange(selectionStart1 + emoji.title.length, selectionStart1 + emoji.title.length)
            })
        },

        /* 激活表情面板, 第二个参数: 是否切换 */
        activeEmojiPanel(e, isToggle) {
            if (isToggle) {
                this.emojiPanelActive = !this.emojiPanelActive
            } else {
                this.emojiPanelActive = true
            }
            e.stopPropagation() /* 阻止事件冒泡 */
        },

        /* 文本域失去焦点时 */
        doBlur() {
            if (this.textareaContent.length > 0) {
                this.height80 = true
            } else {
                this.height80 = false
            }
        },

        /* 发表评论 */
        postComment() {

            // if (!this.textareaContent) {
            //   this.$message("评论不能为空")
            //   return
            // }

            let _this = this

            /* 处理换行, 虽然解决了, 但是不知道为什么在文本域里面按enter和手动输入\n有啥区别?
               哦懂了, \n在正则里面就是表示的换行这一个字符, 手动输入的\n其实是2个字符, 按enter输入的其实是一个字符（虽然它看上去是2个字符）,
               我们程序员习惯了\n表示换行这个字符(但这只是在开发工具里面支持的写法),
               如果把下面改成 /\\n/ 去替换那就可以匹配到手动输入的\n这2个字符
            */
            // console.log(this.textareaContent,'textareaContent');
            let result = this.textareaContent.replace(/\n/g, function (str) {
                console.log('检测到str:' + str);
                return "<br/>"
            })
            // console.log(result,'result');

            /* 处理表情 */
            /* 这个replace函数, 第一个参数是正则表达式, 他回去匹配文本；第二个参数是将匹配的文本传入进行处理的函数，函数的返回值将会替换匹配的文本 */
            result = result.replace(/\[.*?]/g, function (str) {
                if (_this.emojiSize) {
                    return `<img class="emoji-pic" src="${_this.imgPrefix}${emojiConfig[str]}"  style="width:${_this.emojiSize}px;height:${_this.emojiSize}"/>`;
                } else {
                    return `<img class="emoji-pic" src="${_this.imgPrefix}${emojiConfig[str]}" />`;
                }
            })

            this.$emit('comment', result)

            
            this.doBlur()
            // this.afterComment && this.afterComment()
        },
        clearTextareaContent() {
            this.textareaContent = ''
        }
    },
}
</script>

