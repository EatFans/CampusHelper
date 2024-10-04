<template>
  <div :class="['app-wrapper', sidebarClass]">
    <!-- 左侧侧边栏 -->
    <SideBar :isSidebarOpen="isSidebarOpen" :showIndex="pageIndex"/>

    <!--主要容器-->
    <div class="main-container">
      <div class="main-content">
        <!--顶部头部栏-->
        <HeaderBar @sidebarValue="toggleSidebar" :pagePathList="pagePathList" />
        <!--页面右侧主要区域-->
        <div class="app-main">
          <!--显示数据的容器-->
          <div class="data-display-container">
            <!--用户数量-->
            <div class="data-display-item">
              <h1>大学数量：{{universityAmount}}</h1>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {computed, onMounted, ref} from "vue";

import SideBar from "@/components/SideBar.vue";
import HeaderBar from "@/components/HeaderBar.vue";
import universityAPI from "@/api/university";

// 定义一个状态来控制侧边栏的展开与折叠
const isSidebarOpen = ref(true);
const pageIndex = ref(0);


const universityAmount = ref();


// 定义页面路径显示的列表
const pagePathList = ref([
  {
    title: '主页',
    url: '/dashboard'
  },

]);



// 根据侧边栏的状态来动态生成 class
const sidebarClass = computed(() => {
  return isSidebarOpen.value ? 'openSidebar' : 'hiddenSidebar';
});

// 切换侧边栏状态的方法
const toggleSidebar = (value) => {
  isSidebarOpen.value = value;
};

onMounted(() =>{
  fetchUniversityAmount();
});

const fetchUniversityAmount = async () => {
  const response = await universityAPI.getUniversityAmount();
  universityAmount.value = response.data;
}

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

.app-main {
  width: 100%;
  height: 100%;
}

.data-display-container {
  width: 100%;
  height: 140px;
  margin-top: 60px;
  display: flex;
  flex-direction: row;

  border: black solid 1px;
}

.data-display-item {
  width: 250px;
  height: 120px;
  background: white;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: center;

}
</style>