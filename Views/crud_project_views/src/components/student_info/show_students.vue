<template>
    <div class="container">
    <h2>Student Information</h2>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Gender</th>
            <th>Class</th>
            <th>Age</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Operations</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="student in studentList" :key="student.id">
            <td>{{ student.name }}</td>
            <td>{{ student.gender }}</td>
            <td>{{ student.inClass}}</td>
            <td>{{ student.age }}</td>
            <td>{{ student.phone }}</td>
            <td>{{ student.addr}}</td>
            <td>
                <button @click="editMode(student.id)">Edit</button>
                <button @click="deleteUser(student.id)">Delete</button>
            </td>
        </tr>
        </tbody>
    </table>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data(){
        return {
            studentList: [],
            currentStudent: {},
        };
    },
    methods: {
        getAllStudents() {
            axios.get("http://localhost:8081/student/getAll").then((response) => {
                this.studentList = response.data;
            });
        },
        getById(id){
            axios.get("http://localhost:8081/student/get/${id}").then((response)=>{
                this.currentStudent = response.data;
            })
        },
        deleteUser(id){
            if (this.getById(id).id===null){
                alert("The user you are going to delete does not exist")
            } else if (confirm("Are you sure you want to delete the information of this student?")) {
                axios.delete(`http://localhost:8081/student/delete/${id}`).then(() => {
                    this.studentList = this.studentList.filter((student) => student.id !== id);
                    alert("Delete Successfully!");
                });
            }
        },
        editMode(id){

        }
    },
    mounted() {
        this.getAllStudents();
    }
}
</script>

<style>
.container{
    position:absolute;
    left: 25vw;
    top: 10vw;
    width: 65vw;
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
</style>
