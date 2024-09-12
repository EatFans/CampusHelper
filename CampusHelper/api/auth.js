const url = "http://127.0.0.1:8080"

export function getToken(code = {}){
  return new Promise((resolve, reject) => {
    wx.request({
      url: '${url}/getToken',
      data: {
        code: code
      },
      header: {
        'Content-Type': 'application/json'
      },
      success: (res) => {
        if (res.statusCode == 200){
          resolve(res.data);
        } else {
          console.log("请求失败");
        }
      }
    });
  });
}