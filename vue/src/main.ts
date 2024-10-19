import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'

const app = createApp(App)
axios.defaults.baseURL="http://localhost:9090"
app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.mount('#app')
