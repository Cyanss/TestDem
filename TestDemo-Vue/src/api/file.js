import {file} from './config';
import axios from 'axios';
const fileApi = {
  uploadFile(formData) {
    const url = `${file}/upload`;
    return axios.post(url, formData).then((res) => {
      return Promise.resolve(res.data);
    });
  },
  downloadFile(fileName) {
    const url = `${file}/download`;
    const data = Object.assign({}, {
      filename: fileName
    });
    return axios.get(url, {
      params: data,
      headers: {
        'content-disposition': `attachment;filename=${fileName}`,
        'content-type': 'application/x-download;charset=utf-8'
      }
    }).then((res) => {
      return Promise.resolve(res.data);
    });
  },
  getFileNameList() {
    const url = `${file}/list`;
    return axios.get(url).then((res) => {
      return Promise.resolve(res.data);
    });
  }
};
export default fileApi;
