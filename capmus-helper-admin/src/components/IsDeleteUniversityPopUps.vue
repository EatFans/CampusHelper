<template>
  <div class="is-delete-university-pop-ups" v-show="isDeleteUniversityPopUps">
    <div class="is-delete-university-pop-ups-container">
      <div class="is-delete-university-pop-ups-title">
        <p>确定要删除该大学信息吗？</p>
      </div>

      <div class="is-delete-university-pop-ups-operate-button-box">
        <div class="is-delete-university-pop-ups-cancel-button" @click="sendIsDeleteUniversityPopUp">
        <p>取消</p>
      </div>
        <div class="is-delete-university-pop-ups-determine-button" @click="deleteUniversity">
          <p>确定</p>
        </div>
      </div>
    </div>

  </div>
</template>


<script setup>
import {defineProps} from 'vue';
import universityAPI from "@/api/university";

// 定义 props
// eslint-disable-next-line no-unused-vars
const props = defineProps({
  isDeleteUniversityPopUps: {
    type: Boolean,
    default: false
  },
  university: {
    type: Object,
    default: null
  }
});

// eslint-disable-next-line no-undef
const emit = defineEmits(['isDeleteUniversityPopUp']);


const sendIsDeleteUniversityPopUp = () => {
  emit("isDeleteUniversityPopUp", !props.isDeleteUniversityPopUps);
}

const deleteUniversity = async () => {
  const response = await universityAPI.deleteUniversity(props.university);
  const status = response.data.status;
  console.log(response.data);
  console.log(status);
  if (status === 'success'){
    console.log("成功删除！");
    window.location.reload();
  }

}
</script>

<style scoped>
.is-delete-university-pop-ups {
  z-index: 1000; /* 设置一个较高的 z-index 确保在其他元素之上 */
  position: fixed; /* 使用 fixed 使其在窗口中固定 */
  top: 0; /* 覆盖整个屏幕，从顶部开始 */
  left: 0; /* 从左侧开始 */
  width: 100%; /* 宽度 100% */
  height: 100%; /* 高度 100% */
  /*background: rgba(58, 55, 55, 0.8); /* 设置背景色并增加透明度 */
  display: flex; /* 使用 Flexbox 进行居中 */
  justify-content: center; /* 水平居中 */
}

.is-delete-university-pop-ups-container {
  margin-top: 15%;
  width: 400px;
  height: 200px;
  border-radius: 10px;
  background: #fff;
}

.is-delete-university-pop-ups-operate-button-box {
  width: 100%;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.is-delete-university-pop-ups-cancel-button {
  width: 80px;
  height: 25px;
  border-radius: 6px;
  background: #f44847;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-size: 14px;
}
.is-delete-university-pop-ups-cancel-button:hover {
  background: #ec6363;
}

.is-delete-university-pop-ups-determine-button {
  width: 80px;
  height: 30px;
  border-radius: 6px;
  background: #1d8bf5;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-size: 14px;
  margin-left: 50px;
}
.is-delete-university-pop-ups-determine-button:hover {
  background: #4c9ce8;
}

.is-delete-university-pop-ups-title {
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>