<template>
  <div class="login-page-container">
    <div class="login-box">
      <!--登录盒标题-->
      <div class="login-box-title">
        <h1>后台管理系统登录</h1>
      </div>
      <div class="login-box-content">

      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.login-page-container {
  width: 100%;
  height: 100vh;
  background: linear-gradient(-50deg, #F7A37B, #F7A37B, #FFDEA8, #FFDEA8, #D0E4B0, #D0E4B0, #7CC5D0, #7CC5D0, #00A2E1, #00A2E1, #0085C8, #0085C8);  display: flex;
  justify-content: center;
}

.login-box {
  margin-top: 15vh;
  width: 700px;
  height: 600px;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  /* 毛玻璃 */
  background: rgba(255, 255, 255, 0.2); /* 增加透明度，将 alpha 值设置为 0.4 */
  -webkit-backdrop-filter: blur(15px); /* 增加模糊强度 */
  backdrop-filter: blur(15px); /* 增加模糊强度 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 添加阴影 */

}

.login-box-title {
  width: 100%;
  height: 13%;
  /* border: black solid 1px; */
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-box-title h1 {
  font-weight: 500;

}

.login-box-content {
  width: 100%;
  height: 100%;
  border: black solid 1px;
}
</style>