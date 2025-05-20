import request from "@/utils/request.js";

const homeApi = {
    /**
     * 获取所有轮播图
     * @returns {*}
     */
    queryAll(source) {
        return request({
            method: "GET",
            url: "/api/home/slider",
            headers: {
                "Wan-Source": source
            }
        });
    },
    /**
     * 获取首页展示的文章
     * @returns {*}
     */
    queryHomeArticles(){
        return request({
            method: "GET",
            url: "/api/home/articles"
        })
    },

    /**
     * 保存或更新轮播图
     * @param data
     * @returns {*}
     */
    create(data) {
        return request({
            method: "POST",
            url: "/api/home/slider",
            data
        });
    },
    modify(data){
        return request({
            method: "PUT",
            url: "/api/home/slider",
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
            url: "/api/home/slider/" + id
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
            url: "/api/home/slider",
            data: ids
        });
    },

    /**
     * 显示或隐藏轮播图
     * @param data
     * @returns {*}
     */
    updateShow(data) {
        return request({
            method: "PATCH",
            url: "/api/home/slider",
            data
        });
    },

};

export default homeApi;
