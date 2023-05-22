import {createRouter, createWebHistory} from 'vue-router'
import search from '../search.vue'
import welcome from'../../App.vue'
import table from '../../sample.vue'

const routes = [
    {
        path: '/search',
        component: search
    },
    {
        path: '/welcome',
        component: welcome
    },
    {
        path:'/table',
        component: table
    }
]

const router = createRouter ({
    history: createWebHistory(),
    routes
})



export default router