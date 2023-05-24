import {createRouter, createWebHistory} from 'vue-router'
import search from '../../search.vue'
import welcome from '../../welcome.vue'
import table from '../../tables.vue'
import notFound from '../notFound.vue'

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
        path: '/:pathMatch(.*)*',
        component: notFound
    }
]

const router = createRouter ({
    history: createWebHistory(),
    routes
})



export default router