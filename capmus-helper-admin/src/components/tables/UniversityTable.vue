<template>
  <div class="university-table-container">
    <!--校园列表表格-->
    <table class="university-table" >
      <!--表格头-->
      <thead>
        <tr>
          <td class="university-table-head" style="width: 5%">id</td>
          <td class="university-table-head" style="width: 23%">学校名称</td>
          <td class="university-table-head" style="width: 10%">省份</td>
          <td class="university-table-head" style="width: 10%">城市</td>
          <td class="university-table-head" style="width: 10%">更新者</td>
          <td class="university-table-head" style="width: 16%">更新者uuid</td>
          <td class="university-table-head" style="width: 14%">更新时间</td>
          <td class="university-table-head" style="width: 12%">操作</td>
        </tr>
      </thead>
      <!--表格主体-->
      <tbody>
        <tr v-for="(item, index) in universityList" :key="index">
          <td class="university-table-body">{{item.id}}</td>
          <td class="university-table-body">{{item.university}}</td>
          <td class="university-table-body">{{item.province}}</td>
          <td class="university-table-body">{{item.city}}</td>
          <td class="university-table-body">{{item.updatedByName}}</td>
          <td class="university-table-body">{{item.updatedByUuid}}</td>
          <td class="university-table-body">{{item.updatedAt}}</td>
          <td class="university-table-body-button">
            <div class="reset-university-button">
              <p>修改</p>
            </div>
            <div class="delete-university-button" @click="openIsDeleteUniversityPopUp(item)">
              <p>删除</p>
            </div>
          </td>
        </tr>
      </tbody>
    </table>

  </div>
</template>

<script setup>
import {defineProps} from 'vue';
// import universityAPI from "@/api/university";


// eslint-disable-next-line no-unused-vars
const props = defineProps({
  universityList: {
    type: Array,
    default: null
  }
});

// eslint-disable-next-line no-undef
const emit = defineEmits(["isDeleteUniversityPopUp"]);


const openIsDeleteUniversityPopUp = (university) => {
  const data = {
    isIsDeleteUniversityPopUpsOpen: true,
    university: university
  }
  emit("isDeleteUniversityPopUp", data);
}


</script>


<style scoped>
.reset-university-button {
  width: 45px;
  height: 30px;
  background: #1d8bf5;
  margin-right: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 12px;
  border-radius: 6px;
}

.reset-university-button:hover {
  background: #4f9fec;
}

.delete-university-button {
  width: 45px;
  height: 30px;
  background: #f44847;
  margin-left: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 12px;
  border-radius: 6px;
}

.delete-university-button:hover{
  background: #e36969;
}

.university-table-body-button {
  height: 44px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border-right: #b7c3d1 solid 1px;
  border-bottom: #b7c3d1 solid 1px;
}

.university-table-body {
  height: 44px;
  text-align: center;
  border: #b7c3d1 solid 1px;
}

.university-table caption {
  font-size: 2em;
  font-weight: bold;
  margin: 1em 0;
}



.university-table-head {
  height: 44px;
  text-align: center;
  padding-left: 10px;
  padding-right: 10px;
  background: #b7bbbf;
  border: #b7bbbf solid 1px;
}

thead, tr {
  width: 100%;
}

.university-table {
  width: 100%;
  border-collapse: collapse;
}


.university-table-container {
  width: 95%;
  height: auto;
  margin-top: 10px;
  margin-left: 20px;
  background: #fff;
}

</style>