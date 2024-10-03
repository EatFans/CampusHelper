<template>
  <div :class="['app-wrapper', sidebarClass]">
    <!-- 左侧侧边栏 -->
    <SideBar :isSidebarOpen="isSidebarOpen" :showIndex="pageIndex"/>

    <!--主要容器-->
    <div class="main-container">
      <div class="main-content">
        <HeaderBar @sidebarValue="toggleSidebar" :pagePathList="pagePathList" />

        <div class="operate-container">
          <div class="add-university-button">
            <p>添加大学</p>
          </div>
        </div>

        <UniversityTable class="university-table" :universityList="universityList" />

      </div>
    </div>
  </div>
</template>

<script setup>
import {computed, onMounted, ref} from "vue";

import SideBar from "@/components/SideBar.vue";
import HeaderBar from "@/components/HeaderBar.vue";
import UniversityTable from "@/components/UniversityTable.vue";
import universityAPI from "@/api/university";

// 定义一个状态来控制侧边栏的展开与折叠
const isSidebarOpen = ref(true);

const pageIndex = ref(4);

const universityList = ref([]);
const universityAmount = ref(0);

const page = ref(1);
const size = ref(20);

onMounted(() => {
  fetchUniversities();
});

/**
 * 从后端获取大学信息数据到前端中加载渲染
 */
const fetchUniversities = async () => {
  try {
    const params = {
      page: page.value, // 当前页码
      size: size.value // 每页显示的条数
    };
    console.log(params)
    const response = await universityAPI.getUniversityPages(params);
    console.log(response);
    universityList.value = response.data.universities;
    universityAmount.value = response.data.total;
  } catch (error) {
    console.error('获取大学信息失败:', error);
  }

};

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

.operate-container {
  width: 100%;
  height: 30px;
  margin-top: 10px;
}

.add-university-button {
  width: 80px;
  height: 30px;
  background: #40b27f;
  margin-left: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.add-university-button:hover{
  background: #53e3a2;
}

.add-university-button p{
  color: #fff;
  font-size: 13px;
}

</style>