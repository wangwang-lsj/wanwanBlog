import request from "@/utils/request.js";
import { serverIp } from "../../public/config.js";

const fileApi = {
    /**
     * 获取文件上传地址
     * @returns {string}
     */
    upload() {
        return 'http://' + serverIp + ':9090/api/files/upload';
    },

    /**
     * 下载文件
     * @param fileUUID
     */
    download(fileUUID) {
        window.open(fileUUID);
    },

    /**
     * 分页查询文件
     * @param params
     * @returns {*}
     */
    queryPage(params) {
        return request({
            method: "GET",
            url: "/api/files/page",
            params
        });
    },

    /**
     * 更新文件信息
     * @param data
     * @returns {*}
     */
    modify(data) {
        return request({
            method: "PUT",
            url: "/api/files",
            data
        });
    },

    /**
     * 根据ID删除文件
     * @param id
     * @returns {*}
     */
    deleteById(id) {
        return request({
            method: "DELETE",
            url: "/api/files/" + id
        });
    },

    /**
     * 批量删除文件
     * @param ids
     * @returns {*}
     */
    deleteBatch(ids) {
        return request({
            method: "DELETE",
            url: "/api/files",
            data: ids
        });
    },

};

export default fileApi;
