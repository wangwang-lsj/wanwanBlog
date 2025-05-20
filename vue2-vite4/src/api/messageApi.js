import request from '@/utils/request'

const messageApi = {
    /**
     * 分页查询留言
     * @param params
     * @returns {*}
     */
    queryPage(params, source) {
        return request({
            method: "GET",
            url: "/api/messages/page",
            params,
            headers: { "Wan-Source": source }
        });
    },

    /**
     * 发表留言
     * @param data
     * @returns {*}
     */
    createMessage(data) {
        return request({
            method: "POST",
            url: "/api/messages",
            data
        });
    },

    /**
     * 显示或隐藏留言
     * @param data
     * @returns {*}
     */
    updateShow(data) {
        return request({
            method: "PATCH",
            url: "/api/messages",
            data
        });
    },

    /**
     * 回复留言
     * @param data
     * @returns {*}
     */
    updateReply(data) {
        return request({
            method: "PUT",
            url: "/api/messages",
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
            url: "/api/messages/" + id
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
            url: "/api/messages",
            data: ids
        });
    }
};

export default messageApi;
