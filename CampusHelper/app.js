// app.js

App({
  onLaunch() {
    this.checkToken();
  },

  /**
   * 检查本地Token
   */
  checkToken() {
    const token = wx.getStorageSync('userToken');

    if (token) {
      // 如果Token存在
      console.log("Token存在，开始验证...");
      this.verifyToken(token);
    } else {
      // 如果Token不存在, 发送请求检查用户是否存在于数据库中，如果不存在就创建一个新用户，
      // 并开始登录，获取Token保存到本地
      console.log("用户没有登录，开始登录流程...");
      wx.login({
        success: (res) => {
          if (res.code){
            // TODO:
            // 将code请求发送到服务端，服务端来获取openid，服务端将openid与数据库进行比对，如果有就返回开始登录获取Token，
            // 如果数据库中没有，就创建新用户信息，并返回创建信息，创建完毕进行登录获取Token

          } else {
            console.log("获取code失败！");
          }
        },
      })
      this.loginAndGetToken();
    }
  },

/**
 * 验证Token
 * @param {Token} token 
 */
  verifyToken(token) {
    wx.request({
      url: `http://127.0.0.1:8080/auth/checkToken`,
      method: 'POST',
      data: { token },
      success: (res) => {
        if (res.data.status === 'success') {
          console.log("Token验证通过");
          // 验证通过后，请求获取用户基础数据，并保存到本地中
          this.handleTokenSuccess();
        } else {
          console.log(res.data.message);
          this.handleTokenError();
        }
      },
      fail: (err) => {
        console.error("Token验证请求失败:", err);
      }
    });
  },

  /**
   * 监听Token验证成功
   */
  handleTokenSuccess(){
    wx.setStorageSync('isLogin', true);

  },

  /**
   * 监听Token验证失败
   */
  handleTokenError() {
    wx.removeStorageSync('userToken');
    wx.setStorageSync('isLogin', false);
    console.log("已清除本地失效Token");
    this.loginAndGetToken();
  },

  /**
   * 登录和获取Token流程
   */
  loginAndGetToken() {
    wx.login({
      success: (loginRes) => {
        // 检查是否已经获取code
        if (loginRes.code) {
          // 如果已经获取
          wx.request({
            url: `http://127.0.0.1:8080/auth/getToken`,
            method: 'POST',
            data: { code: loginRes.code },
            success: (res) => {
              if (res.data.data) {
                console.log("成功重新登录，已获取Token:", res.data.data);
                wx.setStorageSync('userToken', res.data.data);
              } else {
                console.error("获取Token失败:", res.data.message);
              }
            },
            fail: (err) => {
              console.error("获取Token请求失败:", err);
            }
          });
        } else {
          console.error("登录失败，获取code失败:", loginRes.errMsg);
        }
      },
      fail: (err) => {
        console.error("wx.login失败:", err);
      }
    });
  },

  globalData: {
    userInfo: null
  }
});