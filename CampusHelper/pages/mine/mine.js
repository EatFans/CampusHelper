// index.js
const defaultAvatarUrl = 

Page({
  data: {
    userInfo: {},
    user: {
      avatarUrl: 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0',
      name: "微信用户",
      hasRealNameAuthentication: false,
    },
    isLogin: false,
  },

  onReady(){
    this.isLogined();
  },
  
  isLogined(){
    const token = wx.getStorageSync('userToken');
    if (token){
      // 如果token存在
      // TODO: 去检查验证token

    } else {
      // token不存在
      console.log("还没有登录！将isLogin设置为false");
      this.setData({
        isLogin: false
      });
    }
  },

  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        });
        user.avatarUrl = userInfo.avatarUrl;
      }
    })
  },


  

  test(){
    this.setData({
      isLogin: !this.data.isLogin
    });
   
  },
  
  
})
