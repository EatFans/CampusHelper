<template>
  <div class="sidebar-container" :style="{ width: isSidebarOpen ? '200px' : '50px' }" >
    <!--导航栏物品-->
    <div v-for="(item,index) in sidebarList" :key="index"  class="sidebar-item" @click="geToPage(item.link)">
      <div class="sidebar-item-icon">
        <i :class="item.icon" :style="{color: showIndex === index ? '#4098f5' : '#b7c3d1'}" ></i>
      </div>
      <div v-if="isSidebarOpen" class="sidebar-item-text">
        <p :style="{color: showIndex === index ? '#4098f5' : '#b7c3d1'}">{{item.text}}</p>
      </div>

    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import router from "@/router";


// 定义接收的样式类
// eslint-disable-next-line no-unused-vars
const props = defineProps({
  isSidebarOpen: {
    type: Boolean,
    default: true
  },
  showIndex: {
    type: Number,
    default: 0
  }
});

const sidebarList = [
  {
    text: '仪表盘',
    link: '/dashboard',
    icon: 'bx bx-tachometer'
  },
  {
    text: '文档',
    link: '/doc',
    icon: 'bx bx-file'
  }
]

/**
 * 跳转页面
 * @param url 页面的url路径
 */
const geToPage = (url) => {
  router.push(url);
}
</script>

<style scoped>
.sidebar-container{
  position: fixed;
  width: 200px;
  height: 100vh;
  overflow: auto;
  transition: width 0.3s ease-in-out;
  display: block;
  background: #304054;
}

.sidebar-item {
  width: 100%;
  height: 50px;
  display: flex;
  flex-direction: row;
}

.sidebar-item:hover {
  background: #232c3c;
}

.sidebar-item-icon {
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.sidebar-item-icon i {
  font-size: 22px;
  color: #b7c3d1;
}

.sidebar-item-text {
  position: absolute;
  margin-left: 50px;
  width: 150px;
  height: 50px;
  display: flex;
  align-items: center;
}

.sidebar-item-text p{
  font-size: 14px;
  color: #b7c3d1;
}
</style>