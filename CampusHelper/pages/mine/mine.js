// index.js

Page({
  data: {
    user: {},
    userAvatarUrl: '',
    defaultAvatarUrl: 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0',
    isLogin: false,
    hasUniversity: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    
  },


  onShow(){
    const user = wx.getStorageSync('user');
    const avatarUrl = user.avatarUrl || this.data.defaultAvatarUrl; // 如果avatarUrl为空，使用默认头像
    this.checkUserAvatarUrl(avatarUrl);
    this.setData({
      isLogin: wx.getStorageSync('isLogin'),
      user: user
    })
    if (this.data.user.university){
      this.setData({
        hasUniversity: true
      })
    }
    if (this.data.user.avatarUrl){
      this.setData({
        userAvatarUrl: this.data.user.avatarUrl
      })
    }
  },

  /**
   * 检查用户头像url是否有效
   */
  checkUserAvatarUrl(avatarUrl){
    wx.request({
      url: avatarUrl,
      method: 'GET',
      success: (res) => {
        if (res.statusCode === 200) {
          console.log('头像URL有效');
          // 头像URL有效，继续使用该头像
        } else {
          console.log('头像URL失效');
          // 头像URL无效，切换为默认头像
          this.setData({
            'user.avatarUrl': this.data.defaultAvatarUrl,
          });
          console.log("重置用户头像为默认头像!");
        }
      },
      fail: (err) => {
        console.error('请求失败', err);
        // 请求失败，也认为头像无效，使用默认头像
        console.log("用户头像url时效！");
        this.setData({
          'user.avatarUrl': this.data.defaultAvatarUrl,
        });
        console.log("重置用户头像为默认头像!");
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

  /**
   * 跳转到设置页面
   */
  goToSettings(){
    wx.navigateTo({
      url: '/pages/settings/settings',
      success: function(res) {
        console.log("成功跳转到设置页面");
      },
      fail: function(err) {
        console.error('跳转失败',err);
      }
    })
  },

  /**
   * 跳转到联系客服页面
   */
  goToCustomerServices(){
    wx.navigateTo({
      url: '/pages/customerServices/customerServices',
      success: function(res) {
        console.log("成功跳转到联系客服页面");
      },
      fail: function(err) {
        console.error('跳转失败',err);
      }
    })
  },

  

  test(){
    this.setData({
      isLogin: !this.data.isLogin
    });
   
  },
  
  
})
