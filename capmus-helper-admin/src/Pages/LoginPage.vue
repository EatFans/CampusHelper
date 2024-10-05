<template>
  <div class="login-page-container">
    <div class="login-box">
      <!--登录盒标题-->
      <div class="login-box-title">
        <h1>后台管理系统登录</h1>
      </div>
      <div class="login-box-content">
        <!--输入账号-->
        <div class="login-box-input-username-item">
          <div class="login-box-input-username-title">
            <p>账号</p>
          </div>
          <div class="login-box-input-username-input">
            <div class="login-box-input-username-input-icon">
              <i class='bx bxs-user'></i>
            </div>
            <input type="text" id="username" v-model="loginData.username" value="" />
          </div>
        </div>
        <!--输入密码-->
        <div class="login-box-input-password-item">
          <div class="login-box-input-password-title">
            <p>密码</p>
          </div>
          <div class="login-box-input-password-input">
            <div class="login-box-input-password-input-icon">
              <i class='bx bxs-lock' ></i>
            </div>
            <input type="password" id="password" v-model="loginData.password" value="" />
          </div>
        </div>

        <!-- 登录按钮 -->
        <div class="login-box-button-item" @click="login">
          <button class="login-box-button">登录</button>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import router from "@/router";
import adminAPI from "@/api/admin";

const loginData = ref({
  username: '',
  password: '',
});


// 检查 token 是否有效的异步函数
const checkToken = async (token) => {
  try {
    const response = await adminAPI.checkToken(token); // 假设您有一个 checkToken 的 API
    const status = response.data.status;
    return status === 'success';
  } catch (error) {
    return false;
  }
};

onMounted(() => {
  const token = localStorage.getItem("token");

  if (token){
    checkToken({token}).then(isValid => {
      if (isValid) {
        console.log('token有效，直接跳转到/dashboard');
        router.push("/dashboard");
      } else {
        console.log("token无效，请重新登录")
      }

    });
  } else {
    console.log("并不存在token");
  }
});

const login = async () => {
  try {
    const response = await adminAPI.login(loginData.value);
    const status = response.data.status;
    const message = response.data.message;
    const data = response.data.data;

    console.log(status);
    console.log(message);
    console.log(data);

    // 如果登录成功，存储 token
    if (status === 'success') {
      localStorage.setItem('token', data);
      router.push('/dashboard');

    } else {
      // 处理错误消息
      alert(message || '登录失败，请重试');
    }

  } catch (error) {
    console.error('登录失败:', error);
    alert('网络错误，请稍后重试');
  }
}

</script>

<style scoped>

.login-page-container {
  width: 100%;
  height: 100vh;
  background: linear-gradient(-50deg, #F7A37B, #F7A37B, #FFDEA8, #FFDEA8, #D0E4B0, #D0E4B0, #7CC5D0, #7CC5D0, #00A2E1, #00A2E1, #0085C8, #0085C8);  display: flex;
  justify-content: center;
}

.login-box {
  margin-top: 190px;
  width: 500px;
  height: 350px;
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
  font-weight: 400;
  font-size: 1.8em;
  margin-top: 40px;
  color: #8a8686;
}

.login-box-content {
  width: 100%;
  height: 100%;
  /*border: black solid 1px； */
  display: flex;
  flex-direction: column;
}

.login-box-input-username-item {
  width: 80%;
  height: auto;
  /*border: black solid 1px;*/
  margin: 60px auto 0;
}

.login-box-input-password-item{
  width: 80%;
  height: auto;
  margin: 10px auto;
  /*border: black solid 1px;*/
}


.login-box-input-username-title, .login-box-input-password-title {
  height: 20px;
  width: 100%;
  /*background: red;*/
  display: flex;
  align-items: center;
}

.login-box-input-username-title p, .login-box-input-password-title p {
  font-size: 14px;
  font-weight: 300;
  color: #ffffff;

}

.login-box-input-username-input, .login-box-input-password-input{
  height: 40px;
  width: 100%;
  background: #fffdfd;
  border-radius: 8px;
  display: flex;
  flex-direction: row;
}

.login-box-input-username-input-icon, .login-box-input-password-input-icon {
  width: 30px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-box-input-username-input-icon i, .login-box-input-password-input-icon i {
  font-size: 20px;
  color: #a5a0a0;
}

.login-box-input-username-input input, .login-box-input-password-input input{
  width: 100%;
  height: 100%;
  border: none;
  border-radius: 8px;
  outline:none;
}

.login-box-button-item {
  width: 80%;
  height: 40px;
  margin: 20px auto;
}

.login-box-button {
  width: 100%;
  height: 100%;
  background: #00A2E1;
  border: none;
  border-radius: 8px;
  color: white;
  font-size: 14px;
}

.login-box-button:hover {
  background: #60bbdd;
}

</style>