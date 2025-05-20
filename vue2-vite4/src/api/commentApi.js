import request from '@/utils/request'

const commentApi = {
    /**
     * 根据文章ID获取评论列表
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    queryPageByCondition(params) {
        return request({
            method: "GET",
            url: "/api/comments/page",
            params
        });
    },

    /**
     * 获取回复列表(因为初次加载只有三条)
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    queryReplies(params) {
        return request({
            method: "GET",
            url: "/api/comments/replies",
            params
        });
    },

    /**
     * 根据文章ID获取评论数
     * @param {string} id - 文章ID
     * @returns {Promise}
     */
    queryCountByArticleId(id) {
        return request({
            method: "GET",
            url: "/api/comments/" + id
        });
    },

    /**
     * 添加评论
     * @param {Object} data - 请求体数据
     * @returns {Promise}
     */
    createComment(data) {
        return request({
            method: "POST",
            url: "/api/comments",
            data
        });
    },

    /**
     * 点赞评论
     * @param {string} commentId - 评论ID
     * @param {string} userId - 用户ID
     * @returns {Promise}
     */
    updateLike(commentId, userId) {
        return request({
            method: "POST",
            url: `/api/comments/like/${commentId}/${userId}`
        });
    },

    /**
     * 取消点赞评论
     * @param {string} commentId - 评论ID
     * @param {string} userId - 用户ID
     * @returns {Promise}
     */
    updateDisLike(commentId, userId) {
        return request({
            method: "POST",
            url: `/api/comments/dislike/${commentId}/${userId}`
        });
    }
};

export default commentApi;
