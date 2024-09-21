<template>
  <div class="university-table-container">
    <!--学校数据表格头部信息栏-->
    <div class="university-table-header">
      <div class="id-info-item">
        <p>id</p>
      </div>

      <div class="university-name-info-item">
        <p>大学校区名称</p>
      </div>
    </div>
  </div>
</template>

<script setup>

</script>


<style scoped>
.university-table-container {
  width: 200px;
  height: 300px;
}

.university-table-header {
  height: 40px;
  border: #b7bbbf solid 1px;
  display: flex;
  flex-direction: row;
}

.id-info-item {
  width: 80px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-right: #b7bbbf solid 1px;
}

.id-info-item p {
  color: black;
  font-size: 18px;
}
</style>