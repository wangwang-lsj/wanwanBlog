//获取当前可视范围的高度
export const getClientHeight = () => {
    let clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight)
    } else {
        clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight)
    }
    return clientHeight
}

//获取文档完整的高度
export const getScrollHeight= () => {
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight)
}

//获取当前滚动条的位置
export const getScrollTop = () =>{
    let scrollTop = 0;
    //window.pageYOffset = document.documentElement.scrollTop
    if (document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop
    } else if (document.body) {
        scrollTop = document.body.scrollTop
    }
    return scrollTop
}

/**
 * 防抖
 * @param {Function} fn
 * @param {Number} delay
 */

export const debounce = function debounce(fn, delay) {
    let timer = null;
    return function () {
        clearTimeout(timer);
        let args = arguments;
        let that = this;
        timer = setTimeout(function () {
            fn.apply(that, args);
        }, delay);
    };
};
export function formatTimeAgo(dateString) {
    const timestamp = new Date(dateString).getTime();
    const now = new Date();
    const past = new Date(timestamp);
    const diff = now - past;
    const seconds = Math.floor(diff / 1000);
    const minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);
    const weeks = Math.floor(days / 7);
    const months = Math.floor(days / 30);

    if (months > 0) {
        return `${months}个月前`;
    } else if (weeks > 0) {
        return `${weeks}周前`;
    } else if (days > 0) {
        return `${days}天前`;
    } else if (hours > 0) {
        return `${hours}小时前`;
    } else if (minutes > 0) {
        return `${minutes}分钟前`;
    } else {
        return '刚刚';
    }
}