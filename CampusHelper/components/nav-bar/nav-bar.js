// components/nav-bar/nav-bar.js
Component({

  /**
   * 组件的属性列表
   */
  properties: {
    title: {
      type: String,
      value: ''
    },
    hasBack: {
      type: Boolean,
      value: false
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    goBack(){
      wx.navigateBack({
        delta: 1, // 返回到上一页面
        success: function(res) {
          console.log('成功返回到上一个页面');
        },
        fail: function(err) {
          console.error('返回失败', err);
        }
      });
    }
  }
})