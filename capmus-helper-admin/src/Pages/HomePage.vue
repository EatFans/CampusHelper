<template>
  <div :class="['app-wrapper', sidebarClass]">
    <SideBar :isSidebarOpen="isSidebarOpen" :showIndex="0"/>
    <div class="main-container">
      <div class="main-content">
        <button @click="toggleSidebar">点我</button>

      </div>
    </div>
  </div>
</template>

<script setup>
import {computed, ref} from "vue";

import SideBar from "@/components/SideBar.vue";

// 定义一个状态来控制侧边栏的展开与折叠
const isSidebarOpen = ref(true);

// 根据侧边栏的状态来动态生成 class
const sidebarClass = computed(() => {
  return isSidebarOpen.value ? 'openSidebar' : 'hiddenSidebar';
});

// 切换侧边栏状态的方法
const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value;
};

</script>


<style scoped>
.app-wrapper {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: row;
}


/**
  展开sidebar后的main-container容器的样式
 */
.main-container {
  width: 100%;
  height: auto;
  overflow-y: auto;
}


/**
  隐藏sidebar后的main-container容器的样式
 */
.hiddenSidebar .main-container {
  width: 100%;
  height: 2500px;
  overflow-y: auto;
}


.main-content {
  margin-left: 200px;
  width: 100%;
  height: 2500px;
}


.hiddenSidebar .main-content {
  width: 100%;
  height: 2500px;
  margin-left: 50px;
}

</style>