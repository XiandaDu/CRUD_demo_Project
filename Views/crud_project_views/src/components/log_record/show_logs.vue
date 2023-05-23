<template>
    <div class="container">
        <h1>Log Information</h1>
        <div v-if="refresh">
            <table class="logTable">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Action</th>
                    <th>Object Type</th>
                    <th>Modified Student Id (if appliable)</th>
                    <th>Modified Teacher Id (if appliable)</th>
                    <th>Create Time</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="log in paginatedList" :key="log.id">
                    <td>{{ log.id }}</td>
                    <td>{{ log.action }}</td>
                    <td>{{ log.objectType}}</td>
                    <td>{{ log.modiStudId }}</td>
                    <td>{{ log.modiTeacId }}</td>
                    <td>{{ log.createTime.substring(0,10) }}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pageControl">
            <button @click="prevPage">Last Page</button>
            {{ currentPage }}/{{ Math.ceil(totalPages/perPage) }}
            <button @click="nextPage">Next Page</button>
        </div>


        <button @click="addMode" type="addButton">Add Log</button>
        <div class="addLog" v-if="showAddLog">
            <h3>Add Log</h3>
            <form>
                <label for="addAction">Action： </label>
                <input type="text" id="addAction" v-model="newLog.action" required>
                <label for="addObjectType">Impacted Object Type： </label>
                <input type="text" id="addObjectType" v-model="newLog.objectType" required>
                <label for="addModiStudId">Impacted Student id： </label>
                <input type="number" id="addModiStudId" v-model="newLog.modiStudId" required>
                <label for="addModiTeacId">Impacted Teacher id： </label>
                <input type="number" id="addModiTeacId" v-model="newLog.modiTeacId" required>

                <button type="submit" @click.prevent="addLog">Save</button>
                <button type="submit" @click="addMode">Cancel</button>
            </form>
        </div>

    </div>

</template>

<script>
import axios from 'axios';
export default {
    data(){
        return {
            logList: [],
            currentLog: {
                id:"",
                action:"",
                objectType:"",
                modiStudId:"",
                modiTeacId:"",
                createTime:"",
            },
            perPage: 7,
            currentPage: 1,
            showAddLog: false,
            refresh: true,
            newLog: {
                id:"",
                action:"",
                objectType:"",
                modiStudId:"",
                modiTeacId:"",
                createTime:"",
            },
        };
    },
    methods: {
        getAllLogs() {
            axios.get("http://localhost:8081/log/getAll").then((response) => {
                this.logList = response.data;
            });
        },
        getById(id){
            axios.get(`http://localhost:8081/log/get/${id}`).then((response)=>{
                this.currentLog = response.data;
            })
        },
        addLog(){
            axios.post(`http://localhost:8081/log/post`, this.newLog)
                .then((response) => {
                    alert('Successfully added log!');
                    this.logList.push(response.data);
                    this.newLog = {
                        id:"",
                        action:"",
                        objectType:"",
                        modiStudId:"",
                        modiTeacId:"",
                        createTime:"",
                    };
                })
                .catch(() => {
                    alert("Fail to add new log");
                });
        },

        addMode(){
            this.showAddLog = !this.showAddLog;
        },
        prevPage() {
            if (this.currentPage > 1) {
                this.currentPage--
            }
        },
        nextPage() {
            if ((this.currentPage)*this.perPage < this.totalPages) {
                this.currentPage++
            }
        },
    },

    mounted() {
        this.getAllLogs();
    },
    computed: {
        totalPages() {
            return (this.logList.length)
        },
        // Calculate the page division
        paginatedList() {
            const start = (this.currentPage - 1) * this.perPage
            const end = start + this.perPage
            return this.logList.slice(start, end)
        }
    },
}
</script>

