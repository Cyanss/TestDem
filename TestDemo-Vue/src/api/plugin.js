import {plugin} from './config';
import axios from 'axios';
const pluginApi = {
  getPluginInfo(page, size) {
    const url = `${plugin}/page`;
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
  getUserInfo(pluginid) {
    const url = `${plugin}/user`;
    const data = Object.assign({}, {
      pluginid: pluginid
    });
    return axios.get(url, {
      params: data,
      headers: {
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      return Promise.resolve(res.data);
    });
  },
  getPluginInfoLike(pluginName, page, size) {
    const url = `${plugin}/like`;
    const data = Object.assign({}, {
      pluginname: pluginName,
      page,
      size
    });
    return axios.get(url, {
      params: data
    }).then((res) => {
      return Promise.resolve(res.data);
    });
  },
  addPluginInfo(pluginInfo) {
    const url = `${plugin}/add`;
    const data = Object.assign({}, {
      userId: pluginInfo.userId,
      pluginName: pluginInfo.pluginName,
      pluginType: pluginInfo.pluginType,
      pluginIcon: pluginInfo.pluginIcon,
      pluginEnv: pluginInfo.pluginEnv,
      pluginKeyword: pluginInfo.pluginKeyword,
      pluginUrl: pluginInfo.pluginUrl,
      pluginContent: pluginInfo.pluginContent
    });
    return axios.post(url, data, {
      headers: {
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      return Promise.resolve(res.data);
    });
  },
  deletePluginInfo(pluginId) {
    const url = `${plugin}/delete`;
    const data = Object.assign({}, {
      pluginid: pluginId
    });
    return axios.delete(url, {
      params: data,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then((res) => {
      return Promise.resolve(res.data);
    });
  }
};
export default pluginApi;
