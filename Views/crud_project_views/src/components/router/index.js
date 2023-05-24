import {createRouter, createWebHistory} from 'vue-router'
import search from '../search.vue'
import welcome from'../../App.vue'
import table from '../../sample.vue'

const routes = [
    {
        path: '/',
        component: welcome
    },
    {
        path: '/search',
        component: search
    },
    {
        path:'/table',
        component: table
    },
    {
        path: '/unknown',
        redirect: '/404'
    }
]

const router = createRouter ({
    history: createWebHistory(),
    routes
})



export default router