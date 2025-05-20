import axios from "axios";

const commonApi = {
    getVisitorIp() {
        return axios.get('https://api.ipify.org?format=json');
    }
}
axios.request({
    url: 'https://api.ipify.org?format=json',
}).then(r => console.log('Visitor IP:', r)).catch(error => console.error('Error fetching IP:', error));