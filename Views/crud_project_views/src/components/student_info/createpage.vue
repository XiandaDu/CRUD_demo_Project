<script setup>
import incon from './components/icons/IconDocumentation.vue'
</script>


<template>
    <div>
        <ul>
            <li v-for="item in paginatedList" :key="item.id">{{ item }}</li>
        </ul>
        <incon></incon>
        <div>
            <button @click="prevPage">上一页</button>
            {{ currentPage }}
            <button @click="nextPage">下一页</button>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            list: ['apple','orange','3','4','5','grape','kiwi','sth','3','4','5','6'], // 所有数据
            perPage: 6, // 每页显示的数据数量
            currentPage: 1 // 当前页码
        }
    },
    computed: {
        totalPages() {
            return this.list.length
        },
        // 计算出分页数据
        paginatedList() {
            const start = (this.currentPage - 1) * this.perPage
            const end = start + this.perPage
            return this.list.slice(start, end)
        }
    },
    methods: {
        prevPage() {
            if (this.currentPage > 1) {
                this.currentPage--
            }
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.currentPage++
            }
        }
    }
}
</script>
