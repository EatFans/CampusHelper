<template>
  <div class="home-page-container">
    <h1>{{mount}}</h1>
    <button @click="addMount">点击增加</button>
    <button @click="reduceMount">点击减少</button>
  </div>
</template>

<script setup>
import {ref} from "vue";

const mount = ref(0); // 创建一个mount变量，初始值0

const addMount = () =>{
  mount.value ++;
}

/**
 *
 */
const reduceMount = () => {
  mount.value --;
}
</script>


<style scoped>

</style>