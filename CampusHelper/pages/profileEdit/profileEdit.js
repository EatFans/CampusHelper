// pages/profileEdit/profileEdit.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    beforeNickname: '',   // 更新前的昵称
    beforeAvatarUrl: '',  // 更新前的头像url
    updatedNickname: '',  //更新后的昵称
    updatedAvatarUrl: '',         // 更新后的头像url
    defaultAvatarUrl: 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0',
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
    console.log("正在从缓存中加载用户数据!");
    const user = wx.getStorageSync('user');
    if (user) {
    
      this.setData({
        beforeNickname: user.nickname,
        beforeAvatarUrl: user.avatarUrl
      });
      this.checkUserAvatarUrl(user.avatarUrl);

      console.log("用户数据加载成功:");

    } else {
      console.log("数据加载失败")
    }
  },

  /**
   * 检查用户头像url是否有效
   * @param {用户头像url}} avatarUrl 
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
            beforeAvatarUrl: this.data.defaultAvatarUrl,
          });
          console.log("重置用户头像为默认头像!");
        }
      },
      fail: (err) => {
        console.error('请求失败', err);
        // 请求失败，也认为头像无效，使用默认头像
        console.log("用户头像url时效！");
        this.setData({
          beforeAvatarUrl: this.data.defaultAvatarUrl,
        });
        console.log("重置用户头像为默认头像!");
      }
    });
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

  onInput(event){
    this.setData({
      updatedNickname: event.detail.value
    })
  },

  /**
   * 当用户输入改变的时候
   * @param {事件} e 
   */
  onInputChange(e){
    const nickName = e.detail.value;
    this.setData({
      updatedNickname: nickName
    })
  },

  /**
   * 当用户选择头像
   * @param {事件}} e 
   */
  onChooseAvatar(e){
    const avatarUrl = e.detail.avatarUrl;
    console.log(avatarUrl)
    this.setData({
      updatedAvatarUrl: avatarUrl,
      beforeAvatarUrl: avatarUrl
    });
    
  },

  /**
   * 用户点击更新按钮
   */
  clickUpdateButton(){
    console.log("正在更新用户头像和昵称");
    console.log("用户头像："+this.data.updatedAvatarUrl);
    console.log("用户昵称："+this.data.updatedNickname);
    const user = wx.getStorageSync('user');
    const token = wx.getStorageSync('userToken');
    if (token){
      // 头像不更新，只更新名字
      if (this.data.updatedAvatarUrl == '' && this.data.updatedNickname != ''){
        console.log("头像不更新，只更新名字");
        this.updateUserNickname(token,user);
      }
      // 头像更新，名字不更新
      if (this.data.updatedAvatarUrl != '' && this.data.updatedNickname == ''){
        console.log("头像更新，名字不更新");
        this.updateUserAvatar(token,user);
      }
      // 头像和名字同时更新
      if (this.data.updatedAvatarUrl != '' && this.data.updatedNickname != ''){
        console.log("头像和名字一块更新");
        this.updateUserAvatar(token,user);
        this.updateUserNickname(token,user);
      }

      if (this.data.updatedAvatarUrl == '' && this.data.updatedNickname == ''){
        this.showUpdateErrorToast();
      }
    } else {
      this.showUpdateErrorToast();
    }

    // 先检查updatedAvatarUrl和updatedNickname的值是不是为空，不能为空才能更新

    // 发送更新用户资料请求，将token、头像url和昵称一块发送给服务端，服务端去做处理，完成处理响应返回数据

    // 最后检查服务端数据是否更新成功，更新成功后，修改本地缓存数据同步更新

  },

  updateUserNickname(token,user){
    wx.request({
      url: 'http://127.0.0.1:8080/user/updateUserNickname',
      method:'POST',
      data: {
        token: token,
        nickname: this.data.updatedNickname
      },
      success: (res) => {
        if (res.data.status == 'success'){
          user.nickname = this.data.updatedNickname;
          wx.setStorageSync('user', user);
          console.log(user);
          console.log("用户昵称更新成功！");
          this.showUpdateSuccessToast();
        } else {
          console.log("用户昵称更新失败！");
          this.showUpdateErrorToast();
        }
        
      }
    })
  },

  updateUserAvatar(token,user){
    wx.request({
      url: 'http://127.0.0.1:8080/user/updateUserAvatarUrl',
      method: 'POST',
      data: {
        token: token,
        avatarUrl: this.data.updatedAvatarUrl
      },
      success: (res) => {
        if (res.data.status == 'success'){
          user.avatarUrl = this.data.updatedAvatarUrl;
          wx.setStorageSync('user',user);
          console.log(user);
          console.log("用户头像更新成功！");
          this.showUpdateSuccessToast();
        } else {
          console.log("用户头像更新失败！");
          this.showUpdateErrorToast();
        }
      }
    })
  },

  showUpdateSuccessToast() {
    wx.showToast({
      title: '更新成功',
      icon: 'success',
      duration: 2000
    })
  },

  showUpdateErrorToast() {
    wx.showToast({
      title: '更新失败',
      icon: 'error',
      duration: 2000
    })
  }




  

})