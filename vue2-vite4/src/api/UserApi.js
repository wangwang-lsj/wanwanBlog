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
            url: "/users/register",
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
            url: "/users/login",
            data
        });
    },

    /**
     * 用户分页
     * @param params
     * @returns {*}
     */
    page(params) {
        return request({
            method: "GET",
            url: "/users/page",
            params
        });
    },

    /**
     * 新建用户或修改用户
     * @param data
     * @returns {*}
     */
    saveOrUpdate(data) {
        return request({
            method: "POST",
            url: "/users",
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
            url: "/users/" + id
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
            url: "/users",
            data
        });
    },

    /**
     * 修改密码
     * @param data
     * @returns {*}
     */
    password(data) {
        return request({
            method: "PUT",
            url: "/users",
            data
        });
    },

    /**
     * 通过用户名查询用户
     * @param username
     * @returns {*}
     */
    getByName(username) {
        return request({
            method: "GET",
            url: "/users/" + username
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