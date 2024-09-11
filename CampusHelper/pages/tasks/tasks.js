// pages/tasks/tasks.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isTaskFilterAllButtonClicked: true,  // 全部筛选按钮出初始状态
    isTaskFilterWaiMaiButtonClicked: false,  // 外卖筛选按钮初始状态
    isTaskFilterQuKuaiDiButtonClicked: false,  // 取快递筛选按钮初始状态
    isTaskFilterJiKuaiDiButtonClicked: false,  // 寄快递筛选按钮初始化状态
    isTaskFilterQuanNengButtonClicked: false,  // 全能筛选按钮初始化状态

    tasks: {
      all: [],
      waimai: [],
      qukuaidi: [],
      jikuaidi: [],
      quanneng: []
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },

  /**
   *  跳转到任务搜索界面
   */
  navToTaskSearchPage(){
    wx.navigateTo({
      url: '/pages/search/taskSearch/taskSearch',
    });
  },

  /**
   * 切换TaskFilterAllButton按钮的状态
   */
  toggleTaskFilterAllButtonStatus() {
    // 检查All按钮是否为开,All按钮不能手动设置为false
    // 只有用户设置别的按钮为true时候，自动设置为false，以保证总有一个按钮的值为true
    if (!this.dataisTaskFilterAllButtonClicked){
      // 如果不是为true，设置All开关为true，其他按钮设置为false
      this.setData({
        isTaskFilterAllButtonClicked: true,
        // 设置全部按钮为开就要设置其他按钮为关，反之也一样
        isTaskFilterWaiMaiButtonClicked: false,
        isTaskFilterQuKuaiDiButtonClicked: false,
        isTaskFilterJiKuaiDiButtonClicked: false,
        isTaskFilterQuanNengButtonClicked: false,
      });  
    }
  },

  /**
   * 切换TaskFileterWaiMaiButton按钮的状态
   */
  toggleTaskFilterWaiMainButtonStatus(){
    // 检查WaiMai按钮是否为true
    if (this.data.isTaskFilterWaiMaiButtonClicked){
      // 如果为true，点击后，设置所有的按钮为false，除了all按钮设置为true
      this.setData({
        isTaskFilterWaiMaiButtonClicked: false,
        isTaskFilterQuKuaiDiButtonClicked: false,
        isTaskFilterJiKuaiDiButtonClicked: false,
        isTaskFilterQuanNengButtonClicked: false,
        isTaskFilterAllButtonClicked: true
      })
    } else {
      // 如果是false，点击后，设置为true，其他按钮设置为false
      this.setData({
        isTaskFilterWaiMaiButtonClicked: true,
        isTaskFilterAllButtonClicked: false,
        isTaskFilterQuKuaiDiButtonClicked: false,
        isTaskFilterJiKuaiDiButtonClicked: false,
        isTaskFilterQuanNengButtonClicked: false
      });
    }
    
  },

  /**
   * 切花TaskFileterQuKuaiDiButton按钮的状态
   */
  toggleTaskFilterQuWaiMaiButtonStatus(){
    // 检查QuKuaiDi按钮是否为true
    if (this.data.isTaskFilterQuKuaiDiButtonClicked){
      // 如果是true,点击后，设置所以的按钮为false,除了all按钮设置为true
      this.setData({
        isTaskFilterWaiMaiButtonClicked: false,
        isTaskFilterQuKuaiDiButtonClicked: false,
        isTaskFilterJiKuaiDiButtonClicked: false,
        isTaskFilterQuanNengButtonClicked: false,
        isTaskFilterAllButtonClicked: true
      });
    } else {
      // 如果是false，点击后，设置为true，然后将其他按钮设置为false
      this.setData({
        isTaskFilterQuKuaiDiButtonClicked: true,
        isTaskFilterWaiMaiButtonClicked: false,
        isTaskFilterAllButtonClicked: false,
        isTaskFilterJiKuaiDiButtonClicked: false,
        isTaskFilterQuanNengButtonClicked: false
      });
    }
  },

  /**
   * 切换TaskFilterJiKuaiDiButton按钮的状态
   */
  toggleTaskFilterJiKuaiDiButtonStatus(){
    // jikuaidi按钮是否为true
    if (this.data.isTaskFilterJiKuaiDiButtonClicked){
      // 如果为true，点击后，将所有按钮都是设置为false,除了all设置为true
      this.setData({
        isTaskFilterJiKuaiDiButtonClicked: false,
        isTaskFilterWaiMaiButtonClicked: false,
        isTaskFilterQuKuaiDiButtonClicked: false,
        isTaskFilterQuanNengButtonClicked: false,
        isTaskFilterAllButtonClicked: true
      });
    } else {
      // 如果为false，点击后，设置为true，然后将其他按钮设置为false
      this.setData({
        isTaskFilterJiKuaiDiButtonClicked: true,
        isTaskFilterQuKuaiDiButtonClicked: false,
        isTaskFilterWaiMaiButtonClicked: false,
        isTaskFilterAllButtonClicked: false,
        isTaskFilterQuanNengButtonClicked: false,
      })
    }
  },

  /**
   * 切换TaskFileterQuanNengButton按钮的状态
   */
  toggleTaskFileterQuanNengButtonStatus(){
    // 检查quanneng按钮是否为true
    if (this.data.isTaskFilterQuanNengButtonClicked){
      // 如果为true，点击后，设置所有按钮都为false,除了all按钮设置为true
      this.setData({
        isTaskFilterQuanNengButtonClicked: false,
        isTaskFilterJiKuaiDiButtonClicked: false,
        isTaskFilterQuKuaiDiButtonClicked: false,
        isTaskFilterWaiMaiButtonClicked: false,
        isTaskFilterAllButtonClicked: true
      });
    } else {
      // 如果是false, 点击后，设置为true，并设置其他按钮都为false
      this.setData({
        isTaskFilterQuanNengButtonClicked: true,
        isTaskFilterAllButtonClicked: false,
        isTaskFilterWaiMaiButtonClicked: false,
        isTaskFilterQuKuaiDiButtonClicked: false,
        isTaskFilterJiKuaiDiButtonClicked: false
      });
    }
  }
})