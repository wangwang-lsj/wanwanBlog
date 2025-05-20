<style scoped>
.toast-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100vh;
    display: flex;
    align-items: flex-start;
    justify-content: center;
    pointer-events: none;
}

.toast {
    min-width: 80px;
    max-width: 200px;
    line-height: 1.4em;
    padding: 10px 10px 10px 30px;
    word-wrap: break-word;
    border-radius: 6px;
    color: #e9f7e1;
    top: 40px;
    position: relative;
}

.toast .icon {
    position: absolute;
    left: 8px;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
}

.v-enter {
    transform: translateY(-20px);
    opacity: 0;
}

.v-leave-to {
    transform: translateY(20px);
    opacity: 0;

}

.v-enter-active,
.v-leave-active {
    transition: all 0.5s;
}

</style>

<template>
    <div class="toast-container" style="z-index: 999999">
        <transition>
            <div v-show="toastShow" class="toast" :style="{ 'background-color': bgColor}">
                <i :class="['icon','iconfont',typeIcon]" :style="{ 'background-color': bgColor }"></i>
                <span>{{ messageApi }}</span>
            </div>
        </transition>
    </div>
</template>

<script>
// console.log('toast script...');

export default {
    name: 'Tip',
    data() {
        return {
            toastShow: false,
            type: '', // success、error、warn
            message: '',
            bgColor: '#52c41a',
            typeIcon:'',
            timer: null,
        }
    },
    created() {
        // console.log('我被创建了');
    },
    mounted() {
        // console.log('我被挂在了');
    },
    watch: {
        type(newVal) {
            switch (newVal) {
                case 'success': 
                    this.bgColor = '#52c41a'; this.typeIcon = 'icon-chenggong'; 
                    break;
                case 'error': 
                    this.bgColor = '#f56c6c'; this.typeIcon = 'icon-71shibai'; 
                    break;
                case 'warn': 
                    this.bgColor = '#e6a23c'; this.typeIcon = 'icon-icon--jinggao'; 
                    break;
            }
        }
    },
    methods: {
        showToast(type,msg) {
            this.type = type
            this.message = msg
            this.toastShow = true
            this.timer = setTimeout(() => {
                this.toastShow = false
                this.timer = null
            }, 1500)
        },
        showMsg(type, msg) {
            if (this.timer) {
                console.log('已存在timer', this.timer);
                clearTimeout(this.timer)
                this.timer = null
                this.toastShow = false
                this.$nextTick(()=>{
                    this.showToast(type,msg)
                })
                return
            }

            this.showToast(type,msg)

           
        }
    },
    components: {
    }
}
</script>

