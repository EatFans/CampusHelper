
// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    newsText: "欢迎同学使用校园助手小程序平台。本平台严格保证用户信息安全，请同学放心使用，若有问题请及时联系客服！",
    universityList: [], // 学校列表
    selectedUniversity: '请选择' // 默认选择的学校
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.loadSupportedUniversitiesData();
    const user = wx.getStorageSync('user');
    // 检查用户大学是不是为null
    if (user){
      // 如果不是为null，将selectedUniversity设置为用户的大学
      this.setData({selectedUniversity: user.university})
    }
  },


  /**
   * 加载用户的大学数据信息
   */
  loadUserUniversityData(){
    wx.request({
      url: 'http://127.0.0.1:8080/user/getUserUniversity',
      method: 'POST',
      data: {
        token: wx.getStorageSync('userToken')
      },
      success: (res) => {
        console.log("已经获取用户保存的校园信息");
        this.setData({
          selectedUniversity: res.data.data
        })
      }
    })
  },
  

  /**
   * 加载已经支持的校区数据
   */
  loadSupportedUniversitiesData(){
    wx.request({
      url: 'http://127.0.0.1:8080/university/getAllSupportedUniversities',
      method: 'GET',
      success: (res) => {
      console.log("已经获取到所有支持的大学名称！");
      this.setData({
        universityList: res.data
      });
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    
  },



  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    // 获取用户的大学信息
    

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    console.log("用户正在上拉")
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    console.log("用户把页面下拉到底")
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {
    console.log("用户正在分享")
  },

  // 监听picker选择变化
  onUniversityChange: function(e) {
    const index = e.detail.value;
    this.setData({
      selectedUniversity: this.data.universityList[index]
    });
    console.log("选择的学校是: ", this.data.selectedUniversity);
    // 发送请求修改用户的学校
    wx.request({
      url: 'http://127.0.0.1:8080/user/updateUserUniversity',
      method: 'POST',
      data: {
        token: wx.getStorageSync('userToken'),
        university: this.data.universityList[index]
      },
      success: (res) => {
        console.log("正在请求修改用户学校");
        console.log(res.data);
        if (res.data.status == 'success'){
          console.log("修改校区成功！");
          // 这里修改校区成功后，将存在本地缓存中的user对象中的university值也改变成this.data.universityList[index]
          // 更新本地缓存的用户信息中的school字段
          let userInfo = wx.getStorageSync('user'); // 假设user是用户信息
          if (userInfo) {
            userInfo.university = this.data.selectedUniversity;
            wx.setStorageSync('user', userInfo); // 更新本地缓存
            console.log("本地user缓存数据已经更新");
          }
        } else {
          console.log("修改失败！服务器返回："+res.data);
        }
      }
    })
  }
})