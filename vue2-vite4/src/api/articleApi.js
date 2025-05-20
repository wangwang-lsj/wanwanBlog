import request from "@/utils/request.js";

const articleApi = {
    /**
     * 分页查询文章列表
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    queryPage(params) {
        return request({
            method: "GET",
            url: "/api/articles/page",
            params,
        });
    },

    /**
     * 获取热门文章列表
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    queryHots(params) {
        return request({
            method: "GET",
            url: "/api/articles/hots",
            params
        });
    },

    /**
     * 获取相关文章列表
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    queryRelations(params) {
        return request({
            method: "GET",
            url: "/api/articles/relations",
            params
        });
    },


    // /**
    //  * 根据ID获取文章详情
    //  * @param {string} id - 文章ID
    //  * @returns {Promise}
    //  */
    // getById(id) {
    //     return request({
    //         method: "GET",
    //         url: "/api/articles/" + id,
    //     });
    // },

    /**
     * 获取文章全量信息
     * @param {string} id - 文章ID
     * @returns {Promise}
     */
    queryById(id) {
        return request({
            method: "GET",
            url: "/api/articles/" + id + "/all",
        });
    },

    /**
     * 获取用户对文章的点赞状态
     * @param {string} articleId - 文章ID
     * @param {string} userId - 用户ID
     * @returns {Promise}
     */
    queryLike(articleId, userId) {
        return request({
            method: "GET",
            url: "/api/articles/" + articleId + "/" + userId,
        });
    },

    /**
     * 获取文章统计信息
     * @returns {Promise}
     */
    queryStatistics() {
        return request({
            method: "GET",
            url: "/api/articles/statistics",
        });
    },

    /**
     * 更新文章
     * @returns {Promise}
     */
    modify(data) {
        return request({
            method: "PUT",
            url: "/api/articles",
            data
        });
    },

    /**
     * 创建文章
     * @param {Object} data - 文章数据
     * @returns {Promise}
     */
    create(data) {
        return request({
            method: "POST",
            url: "/api/articles",
            data
        });
    },

    /**
     * 文章点赞或取消点赞
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    updateLike(params) {
        return request({
            method: "PUT",
            url: "/api/articles/likes",
            params
        });
    },

    /**
     * 根据ID删除文章
     * @param {string} id - 文章ID
     * @returns {Promise}
     */
    deleteById(id) {
        return request({
            method: "DELETE",
            url: "/api/articles/" + id,
        });
    },

    /**
     * 批量删除文章
     * @param {Array} ids - 文章ID数组
     * @returns {Promise}
     */
    deleteBatch(ids) {
        return request({
            method: "DELETE",
            url: "/api/articles",
            data: ids
        });
    },

    /**
     * 记录文章阅读次数
     * @param {string} id - 文章ID
     * @returns {Promise}
     */
    updateReadCount(id) {
        return request({
            method: "PATCH",
            url: "/api/articles/" + id,
        });
    },
    updateHomeShow(id, homeShow){
        return request({
            method: "PATCH",
            url: "/api/articles/"+id+"/"+homeShow,
        })
    }
};

export default articleApi;
