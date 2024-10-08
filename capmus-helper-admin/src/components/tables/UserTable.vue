<template>
  <div class="user-table-container">
    <!--用户列表表格-->
    <table class="user-table" >
      <!--表格头-->
      <thead>
      <tr>
        <td class="user-table-head" style="width: 16%">uuid</td>
        <td class="user-table-head" style="width: 15%">openId</td>
        <td class="user-table-head" style="width: 5%">头像</td>
        <td class="user-table-head" style="width: 10%">昵称</td>
        <td class="user-table-head" style="width: 10%">性别</td>
        <td class="user-table-head" style="width: 5%">生日</td>
        <td class="user-table-head" style="width: 14%">手机号码</td>
        <td class="user-table-head" style="width: 12%">姓名</td>
        <td class="user-table-head" style="width: 5%">大学</td>
        <td class="user-table-head" style="width: 5%">状态</td>
      </tr>
      </thead>
      <!--表格主体-->
      <tbody>
      <tr v-for="(item, index) in universityList" :key="index">
        <td class="user-table-body">{{item.id}}</td>
        <td class="user-table-body">{{item.university}}</td>
        <td class="user-table-body">{{item.province}}</td>
        <td class="user-table-body">{{item.city}}</td>
        <td class="user-table-body">{{item.updatedByName}}</td>
        <td class="user-table-body">{{item.updatedByUuid}}</td>
        <td class="user-table-body">{{item.updatedAt}}</td>
        <td class="user-table-body">
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
  userList: {
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
.user-table-body-button {
  height: 44px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border-right: #b7c3d1 solid 1px;
  border-bottom: #b7c3d1 solid 1px;
}

.user-table-body {
  height: 44px;
  text-align: center;
  border: #b7c3d1 solid 1px;
}

.user-table caption {
  font-size: 2em;
  font-weight: bold;
  margin: 1em 0;
}



.user-table-head {
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

.user-table {
  width: 100%;
  border-collapse: collapse;
}


.user-table-container {
  width: 95%;
  height: auto;
  margin-top: 10px;
  margin-left: 20px;
  background: #fff;
}

</style>