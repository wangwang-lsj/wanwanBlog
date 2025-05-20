import request from "@/utils/request.js";

const menuApi = {
    /**
     * 根据名称获取菜单
     * @param name
     * @returns {*}
     */
    queryByName(name) {
        return request({
            method: "GET",
            url: "/api/menus",
            params: { name }
        });
    },

    /**
     * 获取所有图标
     * @returns {*}
     */
    queryIcons() {
        return request({
            method: "GET",
            url: "/api/menus/icons"
        });
    },




    /**
     * 保存或更新菜单
     * @param data
     * @returns {*}
     */
    modify(data) {
        return request({
            method: "PUT",
            url: "/api/menus",
            data
        });
    },
    /**
     * 保存或更新菜单
     * @param data
     * @returns {*}
     */
    create(data) {
        return request({
            method: "POST",
            url: "/api/menus",
            data
        });
    },
    /**
     * 通过id删除菜单
     * @param id
     * @returns {*}
     */
    deleteById(id) {
        return request({
            method: "DELETE",
            url: "/api/menus/" + id
        });
    },

    /**
     * 删除菜单
     * @param ids
     * @returns {*}
     */
    deleteBatch(ids) {
        return request({
            method: "DELETE",
            url: "/api/menus",
            data: ids
        });
    }
};

export default menuApi;
