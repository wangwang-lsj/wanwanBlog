import request from "@/utils/request.js";

const echartsApi = {
    /**
     * 获取统计数据
     * @returns {*}
     */
    queryStatistics() {
        return request({
            method: "GET",
            url: "/api/echarts/statistics"
        });
    },

    /**
     * 获取示例数据
     * @returns {*}
     */
    example() {
        return request({
            method: "GET",
            url: "/api/echarts/example"
        });
    },

    /**
     * 获取成员数据
     * @returns {*}
     */
    members() {
        return request({
            method: "GET",
            url: "/api/echarts/members"
        });
    }
};

export default echartsApi;
