import request from "@/utils/request.js";

const homeApi = {
    /**
     * 获取所有轮播图
     * @returns {*}
     */
    getAll(source) {
        return request({
            method: "GET",
            url: "/home/slider",
            headers: {
                "Wan-Source": source
            }
        });
    },
    /**
     * 获取首页展示的文章
     * @returns {*}
     */
    getHomeArticles(){
        return request({
            method: "GET",
            url: "/home/articles"
        })
    },
    /**
     * 保存或更新轮播图
     * @param data
     * @returns {*}
     */
    saveOrUpdate(data) {
        return request({
            method: "POST",
            url: "/home/slider",
            data
        });
    },

    /**
     * 根据id删除轮播图
     * @param id
     * @returns {*}
     */
    deleteById(id) {
        return request({
            method: "DELETE",
            url: "/home/slider/" + id
        });
    },

    /**
     * 批量删除轮播图
     * @param ids
     * @returns {*}
     */
    deleteBatch(ids) {
        return request({
            method: "DELETE",
            url: "/home/slider",
            data: ids
        });
    },

    /**
     * 显示或隐藏轮播图
     * @param data
     * @returns {*}
     */
    show(data) {
        return request({
            method: "PATCH",
            url: "/home/slider",
            data
        });
    },

};

export default homeApi;
