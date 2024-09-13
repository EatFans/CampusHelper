// app.js

App({
  onLaunch() {
    this.userLogin();
  },

  userLogin(){
    wx.login({
      success: (res) => {
        // 先获取code，检查code是否正确获取
        if (res.code){
          console.log("成功获取code:"+res.code);
          const token = wx.getStorageSync('userToken');
          // 本地检查是否存在token
          if (token){
            console.log("Token存在");
            // 如果token存在，说明用户已经用过小程序了，接下来要验证token有效性，再进一步做判断
            this.verifyToken(token);
          } else {
            console.log("Token不存在");
            // 如果token不存在，可能说明用户并没有用过小程序，也可能有用过只是清理了缓存，接下来要检查用户是否存在，然后再进行token登录
            this.checkUserOrCreateUser(res.code);
          }

        } else {
          console.log("获取code失败！");
        }
      },
    });
  },

  checkUserOrCreateUser(code){
    wx.request({
      url: 'http://127.0.0.1:8080/user/checkUser',
      method: 'POST',
      data: {
        code: code
      },
      success: (res) => {
        console.log("服务器返回用户信息");
        console.log(res.data);
        // 检查res.data中status状态，
        if (res.data.status == 'success'){
          // 如果检查用户存在，就直接进行登录并保存token
          this.loginAndSaveToken(code);

        } else {
          // 如果检查用户不存在，先创建用户到数据库后，再进行登录
          wx.request({
            url: 'http://127.0.0.1:8080/user/createaUser',
            method: 'POST',
            data: {
              code: code
            },
            success: (res) => {
              // TODO:  检查是否创建成功
            }
          });

        }
      },
      fail: (err) => {
        console.log("检查用户或创建用户失败");
      }
    })
  },

  /**
   * 登录并保存token
   * @param {code码} code 
   */
  loginAndSaveToken(code){
    wx.request({
      url: 'http://127.0.0.1:8080/auth/getToken',
      method: 'POST',
      data: {
        code: code
      },
      success: (res) => {
        if (res.data.data) {
          console.log("成功重新登录，已获取Token:", res.data.data);
          wx.setStorageSync('userToken', res.data.data);
          wx.setStorageSync('isLogin', true);
        } else {
          console.error("获取Token失败:", res.data.message);
        }
      },
    })
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
    console.log("用户成功登录！")
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
                wx.setStorageSync('isLogin', true);
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

});