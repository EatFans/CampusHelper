// app.js
App({
  onLaunch() {
    // 初始化数据
    const token = wx.getStorageSync('userToken'); // 从本地获取服务端给用户颁发的userToken验证令牌
    // 检查token令牌是否有效存在
    if (token){
      console.log("token令牌存在于本地，但是还未验证令牌是否有效")
    } else {
      // 用户没有登录的话，跳转到自动前往登录
      console.log("用户没有登录");
      wx.login({
        success: (res) => {
          console.log("已经获取到code："+res.code);
          // 获取到code后，将code请求发送到服务端中进行验证，服务端验证处理完毕后，返回一个时效7天到key令牌，保存在本地
          wx.request({
            url: 'http://127.0.0.1:8080/user/getToken',
            method: 'POST',
            data: {
              code: res.code
            },
            success (res) {
              console.log("已经将code请求到后端服务器中...，返回到的key结果为"+res.data);
            }
            
          })
        },
      })
    }
    
  },
  globalData: {
    userInfo: null
  }
})
