<template>
    <div class="container">
        <h1>Teacher Information</h1>
        <div v-if="refresh">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Gender</th>
                <th>Class</th>
                <th>Age</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Email</th>
                <th>Title</th>
                <th>Create Time</th>
                <th>Operations</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="teacher in paginatedList" :key="teacher.id">
                <td>{{ teacher.name }}</td>
                <td>{{ teacher.gender }}</td>
                <td>{{ teacher.inClass}}</td>
                <td>{{ teacher.age }}</td>
                <td>{{ teacher.phone }}</td>
                <td>{{ teacher.addr}}</td>
                <td>{{teacher.email}}</td>
                <td>{{teacher.title}}</td>
                <td>{{ teacher.createTime}}</td>
                <td>
                    <button @click="editMode(teacher.id)">Edit</button>
                    <button @click="deleteTeacher(teacher.id)">Delete</button>
                </td>
            </tr>
            </tbody>
        </table>
            </div>
        <div class="pageControl">
            <button @click="prevPage">Last Page</button>
            {{ currentPage }}/{{Math.ceil(totalPages/perPage)}}
            <button @click="nextPage">Next Page</button>
        </div>

        <div class="editTeacher" v-if="showEditTeacher">
            <h3>Edit Teacher</h3>
            <form>
                <label for="editName">Name： </label>
                <input type="text" id="editName" v-model="currentTeacher.name" required>
                <label for="editGender">Gender： </label>
                <input type="text" id="editEmail" v-model="currentTeacher.gender" required>
                <label for="editClass">Class： </label>
                <input type="number" id="editClass" v-model="currentTeacher.inClass" required>
                <label for="editAge">Age： </label>
                <input type="number" id="editAge" v-model="currentTeacher.age" required>
                <label for="editPhone">Phone： </label>
                <input type="text" id="editPhone" v-model="currentTeacher.phone" required>
                <label for="editAddr">Address： </label>
                <input type="text" id="editAddr" v-model="currentTeacher.addr" required>
                <label for="editEmail">Email： </label>
                <input type="text" id="editEmail" v-model="currentTeacher.email" required>
                <label for="editTitle">Title： </label>
                <input type="text" id="editTitle" v-model="currentTeacher.title" required>

                <button type="submit" @click.prevent="updateTeacher">Save</button>
                <button type="submit" @click="editMode(currentTeacher.id)">Cancel</button>
            </form>
        </div>
        <button @click="addMode" type="addButton">Add Teacher</button>
        <div class="addTeacher" v-if="showAddTeacher">
            <h3>Add Teacher</h3>
            <form>
                <label for="addName">Name： </label>
                <input type="text" id="addName" v-model="newTeacher.name" required>
                <label for="addGender">Gender： </label>
                <input type="text" id="addEmail" v-model="newTeacher.gender" required>
                <label for="addClass">Class： </label>
                <input type="number" id="addClass" v-model="newTeacher.inClass" required>
                <label for="addAge">Age： </label>
                <input type="number" id="addAge" v-model="newTeacher.age" required>
                <label for="addPhone">Phone： </label>
                <input type="text" id="addPhone" v-model="newTeacher.phone" required>
                <label for="addAddr">Address： </label>
                <input type="text" id="addAddr" v-model="newTeacher.addr" required>
                <label for="editEmail">Email： </label>
                <input type="text" id="editEmail" v-model="newTeacher.email" required>
                <label for="editTitle">Title： </label>
                <input type="text" id="editTitle" v-model="newTeacher.title" required>

                <button type="submit" @click.prevent="addTeacher">Add</button>
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
            teacherList: [],
            currentTeacher: {
                id:0,
                name:"",
                gender:"",
                inClass:0,
                age:0,
                phone:"",
                addr:"",
                email:"",
                title:"",
                createTime:"",
                updateTime:"",
            },
            perPage: 10,
            currentPage: 1,
            showEditTeacher: false,
            showAddTeacher: false,
            refresh: true,
            newTeacher: {
                id:0,
                name:"",
                gender:"",
                inClass:0,
                age:0,
                phone:"",
                addr:"",
                email:"",
                title:"",
                createTime:"",
                updateTime:"",
            },
        };
    },
    methods: {
        getAllTeachers() {
            axios.get("http://localhost:8081/teacher/getAll").then((response) => {
                this.teacherList = response.data;
            });
        },
        getById(id){
            axios.get(`http://localhost:8081/teacher/get/${id}`).then((response)=>{
                this.currentTeacher = response.data;
            })
        },
        deleteTeacher(id){
            if (confirm("Are you sure you want to delete this teacher?")) {
                axios.delete(`http://localhost:8081/teacher/delete/${id}`).then(() => {
                    this.teacherList = this.teacherList.filter((teacher) => teacher.id !== id);
                    alert("Delete Successfully!");
                });
            }
        },
        updateTeacher(){
            axios.put(`http://localhost:8081/teacher/put`, this.currentTeacher)
                .then(() => {
                    this.showEditTeacher = false;
                    alert(`Edit Successful`);
                })
                .catch(() => {
                    alert("Failed to update");
                });
            this.refresh = false;
            this.getAllTeachers();
            this.refresh = true;
        },
        addTeacher(){
            alert(this.newTeacher.title + this.newTeacher.addr + this.newTeacher.email);
            axios.post(`http://localhost:8081/teacher/post`, this.newTeacher)
                .then((response) => {
                    alert('Successfully added teacher!');
                    this.teacherList.push(response.data);
                    this.newTeacher = {
                        id:"",
                        name:"",
                        gender:"",
                        inClass:"",
                        age:"",
                        phone:"",
                        addr:"",
                        email:"",
                        title:"",
                        createTime:"",
                        updateTime:"",
                    };
                })
                .catch(() => {
                    alert("Fail to add new teacher");
                });
            this.refresh = false;
            this.getAllTeachers();
            this.refresh = true;
        },
        editMode(id){
            this.getById(id);
            this.showEditTeacher = !this.showEditTeacher;
        },
        addMode(){
            this.showAddTeacher = !this.showAddTeacher;
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
        this.getAllTeachers();
    },
    computed: {
        totalPages() {
            return (this.teacherList.length)
        },
        // Calculate the page division
        paginatedList() {
            const start = (this.currentPage - 1) * this.perPage
            const end = start + this.perPage
            return this.teacherList.slice(start, end)
        }
    },
}
</script>

<style>
.container{
    position:absolute;
    left: 22vw;
    top: 6vw;
    width: 75vw;
    height: 80vw;
}
table {
    border-collapse: collapse;
    width: 100%;
    margin-bottom: 20px;
}

th,
td {
    text-align: left;
    padding: 10px;
    border: 1px solid #ddd;
}

th {
    background-color: #f2f2f2;
}

button {
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 5px 10px;
    border-radius: 3px;
    cursor: pointer;
    margin-right: 5px;
}

button:hover {
    background-color: #24244f;
}

.pageControl{
    text-align: center;
}
.addTeacher,
.editTeacher {
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
    z-index: 99; /*edit mode will show on the toppest layer*/
}

form label {
    display: block;
    margin-bottom: 10px;
}

form input {
    padding: 10px;
    width: 100%;
    margin-bottom: 20px;
    border: none;
    border-bottom: 2px solid #ccc;
    font-size: 16px;
}

form input:focus {
    outline: none;
    border-color: #32326c;
}

form button[type="submit"] {
    background-color: #32326c;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
}

form button[type="submit"]:hover {
    background-color: #24244f;
}

button[type="addButton"]{
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    right: 2vw;
    top: 0vw;
}
button[type="addButton"]:hover {
    background-color: #24244f;
}

</style>
