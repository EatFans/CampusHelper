<template>
  <div class="add-university-pop-ups" v-show="isAddUniversityPopUps">
    <div class="add-university-pop-ups-container">
      <div class="add-university-pop-ups-title">
        <p>添加新大学信息数据</p>
      </div>
      <div class="add-university-pop-ups-content">
        <div class="add-university-pop-ups-item">
          <p>名称</p>
          <input type="text" placeholder="请输入大学名称" v-model="university">
        </div>
        <div class="add-university-pop-ups-item">
          <p>省份</p>
          <input type="text" placeholder="请输入大学所在的省份" v-model="province" >
        </div>
        <div class="add-university-pop-ups-item">
          <p>城市</p>
          <input type="text" placeholder="请输入大学所在的城市" v-model="city" >
        </div>

        <div v-show="isError" class="put-university-info-error-message">
          <p>{{ errorMessage }}</p>
        </div>
        <div v-show="isSuccess" class="put-university-info-success-message">
          <p>{{successMessage}}</p>
        </div>
      </div>
      <!-- 操作按钮 -->
      <div class="add-university-pop-ups-operate-button-box">
        <div class="add-university-pop-ups-cancel-button" @click="sendIsAddUniversityPopUp">
          <p>取消</p>
        </div>
        <div class="add-university-pop-ups-determine-button" @click="putNewUniversityInfo">
          <p>确定</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {defineProps, onMounted, ref} from 'vue';
import adminAPI from "@/api/admin";
import universityAPI from "@/api/university";

// 定义 props
// eslint-disable-next-line no-unused-vars
const props = defineProps({
  isAddUniversityPopUps: {
    type: Boolean,
    default: false
  }
});

// eslint-disable-next-line no-undef
const emit = defineEmits(['isAddUniversityPopUp']);

onMounted(() =>{
  // 检查token是否有效
  const token = sessionStorage.getItem("token");
  console.log(token);
});

const sendIsAddUniversityPopUp = () => {
  emit("isAddUniversityPopUp", !props.isAddUniversityPopUps);
  window.location.reload();
}

const university = ref("");
const province = ref("");
const city = ref("");
const updatedByName = ref("");
const updatedByUuid = ref("");
const updatedAt = ref();

const isError = ref(false);
const errorMessage = ref("");

const isSuccess = ref(false);
const successMessage = ref("");

/**
 * 添加推送一个新大学信息
 * @returns {Promise<void>}
 */
const putNewUniversityInfo = async () => {
  if (university.value === "") {
    isError.value = true;
    errorMessage.value = "学校名称不能为空！";
    return;
  }
  if (province.value === "") {
    isError.value = true;
    errorMessage.value = "学校所在省份不能为空！";
    return;
  }
  if (city.value === "") {
    isError.value = true;
    errorMessage.value = "学校所在城市不能为空！";
    return;
  }
  const localDate = new Date();
  updatedAt.value = localDate.toISOString();
  if (updatedByName.value === "" && updatedByUuid.value === "") {
    const token = localStorage.getItem("token");
    // 通过token获取管理员用户数据
    const admin = await getAdminData(token);
    updatedByName.value = admin.nickName;
    updatedByUuid.value = admin.uuid;
    await addNewUniversity();

  }
}

/**
 * 添加新的大学信息数据
 * @returns {Promise<void>}
 */
const addNewUniversity = async () => {
  const dataObject = {
    university: university.value,
    province: province.value,
    city: city.value,
    updatedByName: updatedByName.value,
    updatedByUuid: updatedByUuid.value,
    updatedAt: updatedAt.value
  };
  const response = await universityAPI.addNewUniversityData(dataObject);
  const status = response.data.status;
  const message = response.data.message;
  if (status === "success") {
    isSuccess.value = true;
    successMessage.value = message;
    setTimeout(() => {
      isSuccess.value = false;
      emit("isAddUniversityPopUp", !props.isAddUniversityPopUps);
      window.location.reload();
    },500);
  } else {
    isError.value = true;
    errorMessage.value = message;
  }
}

/**
 * 获取管理员用户数据
 * @param token token登录令牌
 */
const getAdminData = async (token) => {
  const response = await adminAPI.getAdminByToken({token});
  const status = response.data.status;
  const message = response.data.message;
  const data = response.data.data;
  if (status === "success") {
    return data;
  } else {
    isError.value = true;
    errorMessage.value = message;
  }

}
</script>


<style scoped>
/*
  添加大学弹窗样式
 */
.add-university-pop-ups {
  z-index: 1000; /* 设置一个较高的 z-index 确保在其他元素之上 */
  position: fixed; /* 使用 fixed 使其在窗口中固定 */
  top: 0; /* 覆盖整个屏幕，从顶部开始 */
  left: 0; /* 从左侧开始 */
  width: 100%; /* 宽度 100% */
  height: 100%; /* 高度 100% */
  background: rgba(58, 55, 55, 0.8); /* 设置背景色并增加透明度 */
  display: flex; /* 使用 Flexbox 进行居中 */
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
}

.add-university-pop-ups-container {
  width:900px;
  height: 900px;
  background: #fff;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
}

.add-university-pop-ups-title {
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
  flex-direction: row;
}

.add-university-pop-ups-title p{
  color: black;
  font-size: 20px;
  font-weight: 300;
  margin-left: 15px;
}

.add-university-pop-ups-content {
  width: 100%;
  height: 750px;

}

.add-university-pop-ups-item {
  width: 100%;
  height: 50px;
  margin-top: 10px;
  display: flex;
  align-items: center;
}

.add-university-pop-ups-item p {
  margin-left: 15px;
}

.add-university-pop-ups-item input {
  margin-left: 30px;
  width: 80%;
  height: 40px;
  border-radius: 5px;
  border: #b7bbbf solid 1px;
  padding-left: 10px;
}


.add-university-pop-ups-operate-button-box {
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin-top: 10px;
}
.add-university-pop-ups-cancel-button {
  width: 60px;
  height: 40px;
  background: #f44847;
  border-radius: 6px;
  margin-right: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.add-university-pop-ups-cancel-button:hover {
  background: #ec6363;
}

.add-university-pop-ups-determine-button {
  width: 60px;
  height: 40px;
  background: #1d8bf5;
  border-radius: 6px;
  margin-right: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.add-university-pop-ups-determine-button:hover{
  background: #4c9ce8;
}

.add-university-pop-ups-determine-button p, .add-university-pop-ups-cancel-button p{
  color: #fff;
  font-size: 14px;
}

.put-university-info-error-message, .put-university-info-success-message {
  height: 50px;
  width: 96%;
  margin: 10px auto;
  display: flex;
}

.put-university-info-error-message p {
  color: red;
  font-size: 14px;
  margin-left: 60px;
}
.put-university-info-success-message p {
  color: yellowgreen;
  font-size: 14px;
  margin-left: 60px;
}
</style>