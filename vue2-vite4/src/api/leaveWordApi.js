import request from '@/utils/request'

const leaveWordApi = {
    /**
     * 分页查询留言
     * @param params
     * @returns {*}
     */
    page(params,source) {
        return request({
            method: "GET",
            url: "/leavewords/page",
            params,
            headers: { "Wan-Source": source }
        });
    },

    /**
     * 获取所有留言
     * @returns {*}
     */
    getAll() {
        return request({
            method: "GET",
            url: "/leavewords"
        });
    },

    /**
     * 根据id获取留言
     * @param id
     * @returns {*}
     */
    getById(id) {
        return request({
            method: "GET",
            url: "/leavewords/" + id
        });
    },

    /**
     * 发表留言
     * @param data
     * @returns {*}
     */
    leaveMessage(data) {
        return request({
            method: "POST",
            url: "/leavewords",
            data
        });
    },

    /**
     * 显示或隐藏留言
     * @param data
     * @returns {*}
     */
    show(data) {
        return request({
            method: "PATCH",
            url: "/leavewords",
            data
        });
    },

    /**
     * 回复留言
     * @param data
     * @returns {*}
     */
    reply(data) {
        return request({
            method: "PUT",
            url: "/leavewords",
            data
        });
    },

    /**
     * 根据id删除留言
     * @param id
     * @returns {*}
     */
    deleteById(id) {
        return request({
            method: "DELETE",
            url: "/leavewords/" + id
        });
    },

    /**
     * 批量删除留言
     * @param ids
     * @returns {*}
     */
    deleteBatch(ids) {
        return request({
            method: "DELETE",
            url: "/leavewords",
            data: ids
        });
    }
};

export default leaveWordApi;
