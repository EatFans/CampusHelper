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

          <div class="paginator-container">
            <div class="last-page-button" @click="goToLastPage">
              <p>上一页</p>
            </div>
            <div class="next-page-button" @click="goToNextPage">
              <p>下一页</p>
            </div>
            <div class="total-page-amount">
              <p>共{{pageAmount}}页</p>
            </div>
            <div class="current-page-index">
              <p>当前在{{currentPage}}页</p>
            </div>
          </div>
        </div>

        <UniversityTable class="university-table" :universityList="universityList" @isDeleteUniversityPopUp="openIsDeleteUniversityPopUp"/>

      </div>
    </div>


  </div>
  <!-- 添加大学弹窗 -->
  <AddUniversityPopUps :isAddUniversityPopUps="isAddUniversityPopUp" @isAddUniversityPopUp="handlerAddUniversityPopUp" />
  <!--确定删除大学信息弹窗-->
  <IsDeleteUniversityPopUps :university="currentDeleteUniversity" :isDeleteUniversityPopUps="isIsDeleteUniversityPopUp" @isDeleteUniversityPopUp="handlerIsDeleteUniversityPopUps"/>
</template>

<script setup>
import {computed, onMounted, ref} from "vue";

import SideBar from "@/components/SideBar.vue";
import HeaderBar from "@/components/HeaderBar.vue";
import UniversityTable from "@/components/tables/UniversityTable.vue";
import universityAPI from "@/api/university";
import AddUniversityPopUps from "@/components/AddUniversityPopUps.vue";
import IsDeleteUniversityPopUps from "@/components/IsDeleteUniversityPopUps.vue";

// 定义一个状态来控制侧边栏的展开与折叠
const isSidebarOpen = ref(true);
// 定义一个状态来控制添加大学弹窗开启与关闭
const isAddUniversityPopUp = ref(false);
// 定义一个状态来控制确定删除大学信息弹出开启与关闭
const isIsDeleteUniversityPopUp = ref(false);

const pageIndex = ref(4);

const universityList = ref([]);
const universityAmount = ref(0);

const currentPage = ref(1);  //当前选择的页面
const size = ref(20);    // 一页多少条数据
const pageAmount = ref(0);   // 总共多少页

// 被选定准备删除的大学
const currentDeleteUniversity = ref();

/**
 * 打开是否删除大学弹窗
 * @type {{}}
 */
const openIsDeleteUniversityPopUp = (data) => {
  console.log(data);
  isIsDeleteUniversityPopUp.value = data.isIsDeleteUniversityPopUpsOpen;
  currentDeleteUniversity.value = data.university;
  console.log("currentDeleteUniversity", currentDeleteUniversity);
}

/**
 * 监听是否删除大学信息弹窗开启与关闭
 */
const handlerIsDeleteUniversityPopUps = (value) => {
  isIsDeleteUniversityPopUp.value = value;
}

/**
 * 切换添加大学弹窗开启与关闭
 */
const toggleAddUniversityPopUp = () => {
  isAddUniversityPopUp.value = !isAddUniversityPopUp.value;
};

/**
 * 监听添加大学信息数据弹窗是否关闭
 * @param value boolean值
 */
const handlerAddUniversityPopUp = (value) => {
  isAddUniversityPopUp.value = value;
}

onMounted(() => {
  fetchUniversities();
});

/**
 * 从后端获取大学信息数据到前端中加载渲染
 */
const fetchUniversities = async () => {
  try {
    const params = {
      page: currentPage.value, // 当前页码
      size: size.value // 每页显示的条数
    };
    console.log(params)
    const response = await universityAPI.getUniversityPages(params);
    console.log(response);
    universityList.value = response.data.universities;
    universityAmount.value = response.data.total;
    pageAmount.value = response.data.pages;
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

/**
 * 前往下一页
 */
const goToNextPage = () => {
  if (currentPage.value < pageAmount.value) {
    currentPage.value++;
    fetchUniversities();
  }
}

/**
 * 返回上一页
 */
const goToLastPage = () => {
  if (currentPage.value > 1) {
    currentPage.value -= 1;
    fetchUniversities();
  }
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

.operate-container {
  width: 100%;
  height: 30px;
  margin-top: 10px;
  display: flex;
  flex-direction: row;
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

.paginator-container {
  width: 400px;
  height: 100%;
  margin-left: 10px;
  display: flex;
  flex-direction: row;
  /*border: black solid 1px;*/
}

.last-page-button, .next-page-button {
  width: 40px;
  height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: #b7c3d1 solid 1px;
  margin-left: 10px;
}

.last-page-button:hover, .next-page-button:hover {
  background: #9da3aa;
}

.last-page-button p,.next-page-button p {
  font-size: 12px;
}

.total-page-amount,.current-page-index {
  margin-left: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}


</style>