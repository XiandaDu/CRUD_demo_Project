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

        <button @click="searchModeLog" type="searchLogButton">Search Logs</button>
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


        <div class="searchLog" v-if="showSearchForm">
            <h3>Search Log</h3>
            <form>
                <label for="addAction">Action： </label>
                <input type="text" id="addAction" v-model="currentLog.action" required>
                <label for="addObjectType">Impacted Object Type： </label>
                <input type="text" id="addObjectType" v-model="currentLog.objectType" required>
                <label for="addModiStudId">Impacted Student id： </label>
                <input type="number" id="addModiStudId" v-model="currentLog.modiStudId" required>
                <label for="addModiTeacId">Impacted Teacher id： </label>
                <input type="number" id="addModiTeacId" v-model="currentLog.modiTeacId" required>

                <button type="submit" @click.prevent="SearchLog">Search</button>
                <button type="submit" @click="searchModeLog">Cancel</button>
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
                action:"",
                objectType:"",
                modiStudId:"",
                modiTeacId:"",
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
            showSearchForm: false,
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
        SearchLog(){
            axios.get(`http://localhost:8081/log/getByCondition`,{params: {...this.currentLog}}).then((response) => {
                this.logList = response.data;
            });
            this.currentPage = 1;
            this.showSearchForm = false;
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
                this.currentPage--;
            }
        },
        nextPage() {
            if ((this.currentPage)*this.perPage < this.totalPages) {
                this.currentPage++;
            }
        },
        searchModeLog(){
            this.showSearchForm = !this.showSearchForm;
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

<style>
button[type="searchLogButton"]{
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    right: 10vw;
    top: 0vw;
    font-size: 20px;
}
.searchLog{
    background-color: white;
    padding: 20px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.15);
    position: absolute;
    left: 5vw;
    top: 1vw;
    width: 30vw;
    opacity:1;
    z-index: 99;
}
</style>

