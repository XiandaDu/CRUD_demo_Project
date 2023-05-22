import './assets/main.css'

import { createApp } from 'vue'
import top from './topFile.vue'
import router from "@/components/router/index";

createApp(top).use(router).mount('#app')
