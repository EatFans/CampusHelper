import axios from 'axios';

const baseURL = 'http://localhost:8080'; // 根据实际后端地址修改

const axiosInstance = axios.create({
    baseURL,
    timeout: 10000 // 超时时间
});


const universityAPI = {
    // 分页获取大学信息数据
    getUniversityPages(credentials) {
        return axiosInstance.post('/university/getUniversities', credentials);
    },


};

export default universityAPI;