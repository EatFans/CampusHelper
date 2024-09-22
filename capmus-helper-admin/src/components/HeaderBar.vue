<template>
  <div class="header-bar-container">
    <div class="sidebar-button" @click="sendIsSideBarOpen">
      <i class='bx bx-list-ul' ></i>
    </div>

    <!--页面路径显示-->
    <div class="page-path-display-box">
      <p v-for="(item, index) in pagePathList " :key="index" @click="goToPage(item.url)" class="page-path-display-text">{{item.title}} /</p>
    </div>
  </div>
</template>

<script setup>
import {defineProps, ref} from "vue";
import router from "@/router";

const isSidebarOpen = ref(true);

// eslint-disable-next-line no-unused-vars
const props = defineProps({
  pagePathList: {
    type: Array,
    default: null
  }
});

// eslint-disable-next-line no-undef
const emit = defineEmits(['sidebarValue']);

const sendIsSideBarOpen = () => {
  isSidebarOpen.value = !isSidebarOpen.value;
  emit("sidebarValue", isSidebarOpen.value);
  console.log("sidebarValue", isSidebarOpen.value);
}

const goToPage = (url) => {
  router.push(url);
}



</script>

<style scoped>
.header-bar-container {
  width: 100%;
  height: 50px;
  display: flex;
  flex-direction: row;
  background: #fff;
}

.sidebar-button {
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.sidebar-button:hover {
  background: #e8eaea;
}

.sidebar-button i {
  font-size: 25px;
}

.page-path-display-box {
  height: 50px;
  width: auto;
  display: flex;
  align-items: center;
  margin-left: 10px;
}

.page-path-display-text {
  font-size: 14px;
  color: #b7bbbf;
  margin-left: 5px;
}

.page-path-display-text:hover {
  color: #4098f5;
}


</style>