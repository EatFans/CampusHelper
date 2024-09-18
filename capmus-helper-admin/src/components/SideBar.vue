<template>
  <div class="sidebar-container" :style="{ width: isSidebarOpen ? '200px' : '50px' }" >

  </div>
</template>

<script setup>
import { defineProps } from 'vue';

// 定义接收的样式类
// eslint-disable-next-line no-unused-vars
const props = defineProps({
  isSidebarOpen: {
    type: Boolean,
    default: true
  }
});
</script>

<style scoped>
.sidebar-container{
  position: fixed;
  width: 200px;
  height: 100vh;
  overflow: auto;
  border: black solid 1px;
  transition: width 0.3s ease-in-out;
  display: block;
  background: #304054;
}

.test {
  width: 100%;
  height: 300px;
  background: yellow;
  margin-bottom: 20px;
}
</style>