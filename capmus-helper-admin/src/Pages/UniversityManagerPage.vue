<template>
  <div :class="['app-wrapper', sidebarClass]">
    <!-- 左侧侧边栏 -->
    <SideBar :isSidebarOpen="isSidebarOpen" :showIndex="pageIndex"/>

    <!--主要容器-->
    <div class="main-container">
      <div class="main-content">
        <HeaderBar @sidebarValue="toggleSidebar" :pagePathList="pagePathList" />

        <UniversityTable class="university-table" />

      </div>
    </div>
  </div>
</template>

<script setup>
import {computed, ref} from "vue";

import SideBar from "@/components/SideBar.vue";
import HeaderBar from "@/components/HeaderBar.vue";
import UniversityTable from "@/components/UniversityTable.vue";

// 定义一个状态来控制侧边栏的展开与折叠
const isSidebarOpen = ref(true);

const pageIndex = ref(4);



// 定义页面路径显示的列表
const pagePathList = ref([
  {
    title: '主页',
    url: '/dashboard'
  },
  {
    title: '学校管理',
    url: 'university-manager'
  }
]);



// 根据侧边栏的状态来动态生成 class
const sidebarClass = computed(() => {
  return isSidebarOpen.value ? 'openSidebar' : 'hiddenSidebar';
});

// 切换侧边栏状态的方法
const toggleSidebar = (value) => {
  isSidebarOpen.value = value;
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
  background: #f0f0f0;
}


/**
  隐藏sidebar后的main-container容器的样式
 */
.hiddenSidebar .main-container {
  overflow-y: auto;
  background: #f0f0f0;
}


.main-content {
  margin-left: 200px;
}


.hiddenSidebar .main-content {
  width: 100%;
  margin-left: 50px;
}


</style>