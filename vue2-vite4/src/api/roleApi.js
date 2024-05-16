import request from "@/utils/request.js";

const roleApi = {
    /**
     * 分页查询角色列表
     * @param params
     * @returns {*}
     */
    page(params) {
        return request({
            method: "GET",
            url: "/roles/page",
            params
        });
    },

    /**
     * 查询所有角色
     * @returns {*}
     */
    getRoles() {
        return request({
            method: "GET",
            url: "/roles"
        });
    },

    /**
     * 根据id查询角色
     * @param id
     * @returns {*}
     */
    getRoleById(id) {
        return request({
            method: "GET",
            url: "/roles/" + id
        });
    },

    /**
     * 保存或更新角色
     * @param data
     * @returns {*}
     */
    saveOrUpdate(data) {
        return request({
            method: "POST",
            url: "/roles",
            data
        });
    },

    /**
     * 根据id删除角色
     * @param id
     * @returns {*}
     */
    deleteById(id) {
        return request({
            method: "DELETE",
            url: "/roles/" + id
        });
    },

    /**
     * 批量删除角色
     * @param ids
     * @returns {*}
     */
    deleteBatch(ids) {
        return request({
            method: "DELETE",
            url: "/roles",
            data: ids
        });
    },

    /**
     * 根据id查询角色
     * @param roleId
     * @returns {*}
     */
    getMenus(roleId) {
        return request({
            method: "GET",
            url: "/roles/" + roleId + "/menus"
        });
    },

    /**
     * 分配角色菜单
     * @param roleId
     * @param menusKeys
     * @returns {*}
     */
    saveRoleMenus(roleId, menusKeys) {
        return request({
            method: "POST",
            url: "/roles/" + roleId + "/menus",
            data: menusKeys
        });
    },
};

export default roleApi;
