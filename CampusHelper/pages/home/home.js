// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    newsText: "欢迎同学使用校园助手小程序。",
    universityList: [], // 学校列表
    selectedUniversity: '请选择' // 默认选择的学校
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.loadSupportedUniversitiesData();
  },

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
  }
})