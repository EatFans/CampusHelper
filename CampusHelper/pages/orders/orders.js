// pages/orders/orders.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isOrderFilterBarWeiWanChenButtonClicked: true,
    isOrderFilterBarYiWanChenButtonClicked: false
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
   * 切换OrderFilterBarWeiWanChenButton的状态
   */
  toggleOrderFilterBarWeiWanChenButtonStatus(){
    // 检查是否为true
    if (this.data.isOrderFilterBarWeiWanChenButtonClicked){
      // 如果为true，就设置为false,并将另外一个按钮设置为true,保证至少有一个按钮的值为true
      this.setData({
        isOrderFilterBarWeiWanChenButtonClicked: false,
        isOrderFilterBarYiWanChenButtonClicked: true
      });
    } else {
      // 如果为false，就设置为true，并将另外一个按钮设置为false
      this.setData({
        isOrderFilterBarYiWanChenButtonClicked: false,
        isOrderFilterBarWeiWanChenButtonClicked: true
      })
    }
  },
  /**
   * 切换OrderFilterBarYiWanChenButton的状态
   */
  toggleOrderFilterBarYiWanChenButtonStatus(){
    // 检查yiwanchen按钮的状态是否为true
    if (this.data.isOrderFilterBarYiWanChenButtonClicked){
      // 如果是true，就设置为false,并将另外一个按钮设置为true
      this.setData({
        isOrderFilterBarYiWanChenButtonClicked: false,
        isOrderFilterBarWeiWanChenButtonClicked: true
      });
    } else {
      // 如果是false，就设置为true，并将另外一个按钮设置为false
      this.setData({
        isOrderFilterBarYiWanChenButtonClicked: true,
        isOrderFilterBarWeiWanChenButtonClicked: false
      })
    }
  }
})