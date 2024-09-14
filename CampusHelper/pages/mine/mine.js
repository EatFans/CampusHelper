// index.js

Page({
  data: {
    user: {},
    isLogin: false,
    hasUniversity: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
    })
    
  },

  onShow(){
    const user = wx.getStorageSync('user');
    this.setData({
      isLogin: wx.getStorageSync('isLogin'),
      user: user
    })
    if (this.data.user.university){
      this.setData({
        hasUniversity: true
      })
    }
  },

  

  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '用于完善用户资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log(res.userInfo);

      }
    })
  },

  /**
   * 跳转到资料编辑页面
   */
  goToProfileEdit() {
    wx.navigateTo({
      url: '/pages/profileEdit/profileEdit', // 跳转到 profile-edit 页面
      success: function(res) {
        // 跳转成功回调
        console.log('成功跳转到用户资料编辑页面');
      },
      fail: function(err) {
        // 跳转失败回调
        console.error('跳转失败', err);
      }
    });
  },

  

  test(){
    this.setData({
      isLogin: !this.data.isLogin
    });
   
  },
  
  
})
