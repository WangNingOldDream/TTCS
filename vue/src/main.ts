import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const app = createApp(App)
axios.defaults.baseURL="http://localhost:9090"
app.use(createPinia().use(piniaPluginPersistedstate))
app.use(router)
app.use(ElementPlus)
app.mount('#app')
