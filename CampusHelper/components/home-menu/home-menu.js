// components/home-menu/home-menu.js
Component({

  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    currentPage: 0,  // 当前页数
    totalPages: 2,   // 总页数，根据数据实际情况设置
    indicators: [0, 1],  // 根据页数生成指示器数组
    homeMenuItems:[
      {
        id: 1,
        icon: '../../images/homeMenuIcon/pick_up_the_courier.png',
        text: '待定',
        link: '/pages/campusWalls/campusWalls'
      },
      {
        id: 2,
        icon: '../../images/homeMenuIcon/takeaway.png',
        text: '待定',
        link: '',
      },
      {
        id: 3,
        icon: '../../images/homeMenuIcon/lost_and_found.png',
        text: '待定',
        link: ''
      },
      {
        id: 4,
        icon: '../../images/homeMenuIcon/versatile_quest.png',
        text: '待定',
        link: ''
      },
      {
        id: 5,
        icon: '../../images/homeMenuIcon/lost_and_found.png',
        text: '邀请好友',
        link: ''
      }
    ]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onScroll(e) {
      // 计算当前页面
      const scrollLeft = e.detail.scrollLeft;
      const currentPage = Math.round(scrollLeft / wx.getSystemInfoSync().windowWidth);
      this.setData({
        currentPage
      });
    },

    goToPage(event){
      const link = event.currentTarget.dataset.link;
      wx.navigateTo({
        url: link,
        success: function(res) {
          console.log("成功跳转");
        },
        fail: function(err) {
          console.error('跳转失败',err);
        }
      })
    }
  }
})