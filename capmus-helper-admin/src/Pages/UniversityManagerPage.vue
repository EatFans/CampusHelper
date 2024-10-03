<template>
  <div :class="['app-wrapper', sidebarClass]">
    <!-- 左侧侧边栏 -->
    <SideBar :isSidebarOpen="isSidebarOpen" :showIndex="pageIndex"/>

    <!--主要容器-->
    <div class="main-container">
      <div class="main-content">
        <HeaderBar @sidebarValue="toggleSidebar" :pagePathList="pagePathList" />

        <div class="operate-container">
          <div class="add-university-button" @click="toggleAddUniversityPopUp">
            <p>添加大学</p>
          </div>
        </div>

        <UniversityTable class="university-table" :universityList="universityList" />

      </div>
    </div>


  </div>
  <!-- 添加大学弹窗 -->
  <div class="add-university-pop-ups" v-show="isAddUniversityPopUp">
    <div class="add-university-pop-ups-container">
      <div class="add-university-pop-ups-title">
        <p>添加新大学信息数据</p>
      </div>
      <div class="add-university-pop-ups-content">

      </div>
      <!-- 操作按钮 -->
      <div class="add-university-pop-ups-operate-button-box">
        <div class="add-university-pop-ups-cancel-button" @click="toggleAddUniversityPopUp">
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
import {computed, onMounted, ref} from "vue";

import SideBar from "@/components/SideBar.vue";
import HeaderBar from "@/components/HeaderBar.vue";
import UniversityTable from "@/components/UniversityTable.vue";
import universityAPI from "@/api/university";

// 定义一个状态来控制侧边栏的展开与折叠
const isSidebarOpen = ref(true);
// 定义一个状态来控制添加大学弹窗开启与关闭
const isAddUniversityPopUp = ref(false);

const pageIndex = ref(4);

const universityList = ref([]);
const universityAmount = ref(0);

const page = ref(1);
const size = ref(20);

/**
 * 切换添加大学弹窗开启与关闭
 */
const toggleAddUniversityPopUp = () => {
  isAddUniversityPopUp.value = !isAddUniversityPopUp.value;
};

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