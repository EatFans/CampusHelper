<template>
  <div class="add-university-pop-ups" v-show="isAddUniversityPopUps">
    <div class="add-university-pop-ups-container">
      <div class="add-university-pop-ups-title">
        <p>添加新大学信息数据</p>
      </div>
      <div class="add-university-pop-ups-content">

      </div>
      <!-- 操作按钮 -->
      <div class="add-university-pop-ups-operate-button-box">
        <div class="add-university-pop-ups-cancel-button" @click="sendIsAddUniversityPopUp">
          <p>取消</p>
        </div>
        <div class="add-university-pop-ups-determine-button">
          <p>确定</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps} from 'vue';

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

const sendIsAddUniversityPopUp = () => {
  emit("isAddUniversityPopUp", !props.isAddUniversityPopUps);
  console.log("isAddUniversityPopUp", props.isAddUniversityPopUps);
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
</style>