import request from "@/utils/request.js";

const articleApi = {
    /**
     * 分页查询文章列表
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    page(params) {
        return request({
            method: "GET",
            url: "/articles/page",
            params,
        });
    },

    /**
     * 获取热门文章列表
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    getHots(params) {
        return request({
            method: "GET",
            url: "/articles/hots",
            params
        });
    },

    /**
     * 获取相关文章列表
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    getRelations(params) {
        return request({
            method: "GET",
            url: "/articles/relations",
            params
        });
    },

    /**
     * 获取所有文章
     * @returns {Promise}
     */
    getAll() {
        return request({
            method: "GET",
            url: "/articles",
        });
    },

    /**
     * 根据ID获取文章详情
     * @param {string} id - 文章ID
     * @returns {Promise}
     */
    getById(id) {
        return request({
            method: "GET",
            url: "/articles/" + id,
        });
    },

    /**
     * 获取文章全量信息
     * @param {string} id - 文章ID
     * @returns {Promise}
     */
    getOneAll(id) {
        return request({
            method: "GET",
            url: "/articles/" + id + "/all",
        });
    },

    /**
     * 获取用户对文章的点赞状态
     * @param {string} articleId - 文章ID
     * @param {string} userId - 用户ID
     * @returns {Promise}
     */
    getLike(articleId, userId) {
        return request({
            method: "GET",
            url: "/articles/" + articleId + "/" + userId,
        });
    },

    /**
     * 获取文章统计信息
     * @returns {Promise}
     */
    statistics() {
        return request({
            method: "GET",
            url: "/articles/statistics",
        });
    },

    /**
     * 更新文章
     * @returns {Promise}
     */
    save(data) {
        return request({
            method: "PUT",
            url: "/articles",
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
            url: "/articles",
            data
        });
    },

    /**
     * 文章点赞或取消点赞
     * @param {Object} params - 请求参数
     * @returns {Promise}
     */
    likeOrDisLike(params) {
        return request({
            method: "PUT",
            url: "/articles/likes",
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
            url: "/articles/" + id,
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
            url: "/articles",
            data: ids
        });
    },

    /**
     * 记录文章阅读次数
     * @param {string} id - 文章ID
     * @returns {Promise}
     */
    recordReadCount(id) {
        return request({
            method: "PATCH",
            url: "/articles/" + id,
        });
    },
    changeHomeShow(id,homeShow){
        return request({
            method: "PATCH",
            url: "/articles/"+id+"/"+homeShow,
        })
    }
};

export default articleApi;
