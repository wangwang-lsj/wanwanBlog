import request from "@/utils/request.js";

const roleApi = {
    /**
     * 分页查询角色列表
     * @param params
     * @returns {*}
     */
    queryPage(params) {
        return request({
            method: "GET",
            url: "/api/roles/page",
            params
        });
    },

    /**
     * 查询所有角色
     * @returns {*}
     */
    queryRoles() {
        return request({
            method: "GET",
            url: "/api/roles"
        });
    },


    /**
     * 保存角色
     * @param data
     * @returns {*}
     */
    create(data) {
        return request({
            method: "POST",
            url: "/api/roles",
            data
        });
    },
    /**
     * 更新角色
     * @param data
     * @returns {*}
     */
    modify(data) {
        return request({
            method: "PUT",
            url: "/api/roles",
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
            url: "/api/roles/" + id
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
            url: "/api/roles",
            data: ids
        });
    },

    /**
     * 根据id查询角色
     * @param roleId
     * @returns {*}
     */
    queryMenuById(roleId) {
        return request({
            method: "GET",
            url: "/api/roles/" + roleId + "/menus"
        });
    },

    /**
     * 分配角色菜单
     * @param roleId
     * @param menusKeys
     * @returns {*}
     */
    createRoleMenus(roleId, menusKeys) {
        return request({
            method: "POST",
            url: "/api/roles/" + roleId + "/menus",
            data: menusKeys
        });
    },
};

export default roleApi;
