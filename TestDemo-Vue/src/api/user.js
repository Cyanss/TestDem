import {user} from './config';
import axios from 'axios';
 const userApi = {
  getUserInfo(page, size) {
    const url = `${user}/page`;
    const data = Object.assign({}, {
      page,
      size
    });
    return axios.get(url, {
      params: data
    }).then((res) => {
      return Promise.resolve(res.data);
    });
  },
  addUserInfo(userInfo) {
    const url = `${user}/add`;
    const data = Object.assign({}, {
      userName: userInfo.userName,
      userPhone: userInfo.userPhone,
      userEmail: userInfo.userEmail,
      userPassword: userInfo.userPassword,
      userAuth: userInfo.userAuth
    });
    return axios.post(url, data, {
      headers: {
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      return Promise.resolve(res.data);
    });
  },
  deleteUserInfo(userId) {
   const url = `${user}/delete`;
   const data = Object.assign({}, {
     userid: userId
   });
   return axios.delete(url, {
     params: data
   }).then((res) => {
     return Promise.resolve(res.data);
   });
  }
 };
 export default userApi;
