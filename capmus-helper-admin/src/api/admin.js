import axios from 'axios';

const baseURL = 'http://localhost:8080/admin'; // 根据实际后端地址修改

const axiosInstance = axios.create({
    baseURL,
    timeout: 10000 // 超时时间
});

const adminAPI = {
    // 登录接口
    login(credentials) {
        return axiosInstance.post('/login', credentials);
    },
    // 验证token
    checkToken(credentials) {
        return axiosInstance.post('/checkToken', credentials);
    }

};

export default adminAPI;
