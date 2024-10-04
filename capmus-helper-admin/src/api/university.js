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
    // 添加新的大学信息数据
    addNewUniversityData(credentials) {
        return axiosInstance.post('/university/add', credentials);
    },
    // 获取所有已经支持的大学的数量
    getUniversityAmount(){
        return axiosInstance.get('/university/getUniversityAmount');
    },
    // 删除大学信息数据
    deleteUniversity(data) {
        return axiosInstance.post('/university/delete', data);
    }

};

export default universityAPI;