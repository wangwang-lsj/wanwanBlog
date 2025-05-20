import request from "@/utils/request.js";
import { serverIp } from "../../public/config.js";

const userApi = {

    /**
     * 用户注册
     * @param data
     * @returns {*}
     */
    register(data) {
        return request({
            method: "POST",
            url: "/api/users/register",
            data
        });
    },

    /**
     * 用户登陆
     * @param data
     * @returns {*}
     */
    login(data) {
        return request({
            method: "POST",
            url: "/api/users/login",
            data
        });
    },

    /**
     * 用户分页
     * @param params
     * @returns {*}
     */
    queryPage(params) {
        return request({
            method: "GET",
            url: "/api/users/page",
            params
        });
    },

    /**
     * 新建用户
     * @param data
     * @returns {*}
     */
    create(data) {
        return request({
            method: "POST",
            url: "/api/users",
            data
        });
    },
    /**
     * 修改用户
     * @param data
     * @returns {*}
     */
    modify(data) {
        return request({
            method: "PUT",
            url: "/api/users",
            data
        });
    },

    /**
     * 通过id删除用户
     * @param id
     * @returns {*}
     */
    deleteById(id) {
        return request({
            method: "DELETE",
            url: "/api/users/" + id
        });
    },

    /**
     * 用过ids批量删除用户
     * @param data
     * @returns {*}
     */
    deleteBatch(data) {
        return request({
            method: "DELETE",
            url: "/api/users",
            data
        });
    },

    /**
     * 修改密码
     * @param data
     * @returns {*}
     */
    modifyPassword(data) {
        return request({
            method: "PATCH",
            url: "/api/users",
            data
        });
    },

    /**
     * 通过用户名查询用户
     * @param username
     * @returns {*}
     */
    queryByName(username) {
        return request({
            method: "GET",
            url: "/api/users/" + username
        });
    },

    /**
     * 导出excel
     */
    exportExcel() {
        window.open("http://" + serverIp + ":9090/api/users/export");
    },

    /**
     * 导入excel
     * @returns {string}
     */
    importExcel() {
        return "http://" + serverIp + ":9090/api/users/import";
    }
};

export default userApi;