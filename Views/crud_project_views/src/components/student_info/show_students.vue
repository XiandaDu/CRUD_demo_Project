<template>
    <div class="container">
    <h1>Student Information</h1>
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
            <th>Create Time</th>
            <th>Operations</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="student in paginatedList" :key="student.id">
            <td>{{ student.name }}</td>
            <td>{{ student.gender }}</td>
            <td>{{ student.inClass}}</td>
            <td>{{ student.age }}</td>
            <td>{{ student.phone }}</td>
            <td>{{ student.addr}}</td>
            <td>{{ student.createTime.substring(0,10) }}</td>
            <td>
                <button @click="editMode(student.id)">Edit</button>
                <button @click="deleteStudent(student.id)">Delete</button>
            </td>
        </tr>
        </tbody>
    </table>
        </div>
        <div class="pageControl">
            <button @click="prevPage">Last Page</button>
            {{ currentPage }}/{{ Math.ceil(totalPages/perPage) }}
            <button @click="nextPage">Next Page</button>
        </div>

        <div class="editStudent" v-if="showEditStudent">
            <h3>Edit Student</h3>
            <form>
                <label for="editName">Name： </label>
                <input type="text" id="editName" v-model="currentStudent.name" required>
                <label for="editGender">Gender： </label>
                <input type="text" id="editEmail" v-model="currentStudent.gender" required>
                <label for="editClass">Class： </label>
                <input type="number" id="editClass" v-model="currentStudent.inClass" required>
                <label for="editAge">Age： </label>
                <input type="number" id="editAge" v-model="currentStudent.age" required>
                <label for="editPhone">Phone： </label>
                <input type="text" id="editPhone" v-model="currentStudent.phone" required>
                <label for="editAddr">Address： </label>
                <input type="text" id="editAddr" v-model="currentStudent.addr" required>

                <button type="submit" @click.prevent="updateStudent">Save</button>
                <button type="submit" @click="editMode(currentStudent.id)">Cancel</button>
            </form>
        </div>
        <button @click="addMode" type="addButton">Add Student</button>
        <div class="addStudent" v-if="showAddStudent">
            <h3>Add Student</h3>
            <form>
                <label for="addName">Name： </label>
                <input type="text" id="addName" v-model="newStudent.name" required>
                <label for="addGender">Gender： </label>
                <input type="text" id="addEmail" v-model="newStudent.gender" required>
                <label for="addClass">Class： </label>
                <input type="number" id="addClass" v-model="newStudent.inClass" required>
                <label for="addAge">Age： </label>
                <input type="number" id="addAge" v-model="newStudent.age" required>
                <label for="addPhone">Phone： </label>
                <input type="text" id="addPhone" v-model="newStudent.phone" required>
                <label for="addAddr">Address： </label>
                <input type="text" id="addAddr" v-model="newStudent.addr" required>

                <button type="submit" @click.prevent="addStudent">Save</button>
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
            studentList: [],
            currentStudent: {
                id:0,
                name:"",
                gender:"",
                inClass:0,
                age:0,
                phone:"",
                addr:"",
                createTime:"",
                updateTime:"",
            },
            perPage: 10,
            currentPage: 1,
            showEditStudent: false,
            showAddStudent: false,
            refresh: true,
            newStudent: {
                id:0,
                name:"",
                gender:"",
                inClass:0,
                age:0,
                phone:"",
                addr:"",
                createTime:"",
                updateTime:"",
            },
        };
    },
    methods: {
        getAllStudents() {
            axios.get("http://localhost:8081/student/getAll").then((response) => {
                this.studentList = response.data;
            });
        },
        getById(id){
            axios.get(`http://localhost:8081/student/get/${id}`).then((response)=>{
                this.currentStudent = response.data;
            })
        },
        deleteStudent(id){
             if (confirm("Are you sure you want to delete this student?")) {
                axios.delete(`http://localhost:8081/student/delete/${id}`).then(() => {
                    this.studentList = this.studentList.filter((student) => student.id !== id);
                    alert("Delete Successfully!");
                });
            }
        },
        updateStudent(){
            axios.put(`http://localhost:8081/student/put`, this.currentStudent)
                .then(() => {
                    this.showEditStudent = false;
                    alert(`Edit Successful`);
                })
                .catch(() => {
                    alert("Failed to update");
                });
        },
        addStudent(){
            axios.post(`http://localhost:8081/student/post`, this.newStudent)
                .then((response) => {
                    alert('Successfully added student!');
                    this.studentList.push(response.data);
                    this.newStudent = {
                        id:"",
                        name:"",
                        gender:"",
                        inClass:"",
                        age:"",
                        phone:"",
                        addr:"",
                        createTime:"",
                        updateTime:"",
                    };
                })
                .catch(() => {
                    alert("Fail to add new student");
                });
        },
        editMode(id){
                this.getById(id);
                this.showEditStudent = !this.showEditStudent;
        },
        addMode(){
          this.showAddStudent = !this.showAddStudent;
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
        this.getAllStudents();
    },
    computed: {
        totalPages() {
            return (this.studentList.length)
        },
        // Calculate the page division
        paginatedList() {
            const start = (this.currentPage - 1) * this.perPage
            const end = start + this.perPage
            return this.studentList.slice(start, end)
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
.addStudent,
.editStudent {
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
