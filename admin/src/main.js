import {createApp} from 'vue'
import App from './App.vue'
import router from "./router"
import axios from "axios"
import {optionFilter,optionObjectFilter} from "./filter/filter"

axios.interceptors.request.use(function (config) {
  console.log("请求，", config);
  return config
});

axios.interceptors.response.use(function (response) {
  console.log("响应，", response);
  return response
});

const app = createApp(App);
app.config.globalProperties.$filters = {
  optionObjectFilter
}
app.config.globalProperties.$ajax = axios;
app.use(router)
app.mount("#app");


console.log(process.env.VUE_APP_SERVER)


