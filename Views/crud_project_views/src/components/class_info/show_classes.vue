<template>
    <div class="Tables">
        <div class="SearchClass">
            <label>Search people in class: </label>
            <input type="number" v-model="searchForClass" required>
            <button type="SearchClass" @click="FindByClass(searchForClass)">Submit</button>
        </div>
    <div class="container">
        <h1>Student Information</h1>
        <div v-if="refresh">
            <table>
                <thead>
                <tr>
                    <th>Class</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Age</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Create Time</th>
                    <th>Operation</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="student in paginatedList" :key="student.id">
                    <td>{{ student.inClass}}</td>
                    <td>{{ student.name }}</td>
                    <td>{{ student.gender }}</td>
                    <td>{{ student.age }}</td>
                    <td>{{ student.phone }}</td>
                    <td>{{ student.addr}}</td>
                    <td>{{ student.createTime.substring(0,10) }}</td>
                    <td>
                        <button @click="editMode(student.id)">Edit</button>
                        <button @click="deleteStudent(student)">Delete</button>
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
<!--________________________________________________________________________________-->
    <div class="containerT">
        <h1>Teacher Information</h1>
        <div v-if="refresh">
            <table>
                <thead>
                <tr>
                    <th>Class</th>
                    <th>Name</th>
                    <th>Gender</th>
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
                <tr v-for="teacher in paginatedListTeacher" :key="teacher.id">
                    <td>{{ teacher.inClass}}</td>
                    <td>{{ teacher.name }}</td>
                    <td>{{ teacher.gender }}</td>
                    <td>{{ teacher.age }}</td>
                    <td>{{ teacher.phone }}</td>
                    <td>{{ teacher.addr}}</td>
                    <td>{{teacher.email}}</td>
                    <td>{{teacher.title}}</td>
                    <td>{{ teacher.createTime}}</td>
                    <td>
                        <button @click="editModeTeacher(teacher.id)">Edit</button>
                        <button @click="deleteTeacher(teacher)">Delete</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pageControl">
            <button @click="prevPageTeacher">Last Page</button>
            {{ currentPageTeacher }}/{{Math.ceil(totalPagesTeacher/perPageTeacher)}}
            <button @click="nextPageTeacher">Next Page</button>
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
                <button type="submit" @click="editModeTeacher(currentTeacher.id)">Cancel</button>
            </form>
        </div>
        <button @click="addModeTeacher" type="addButton">Add Teacher</button>
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
                <button type="submit" @click="addModeTeacher">Cancel</button>
            </form>
        </div>
    </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data(){
        return {
            searchForClass:null,
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
            perPageTeacher: 10,
            currentPageTeacher: 1,
            showEditTeacher: false,
            showAddTeacher: false,
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
            newLog: {
                action:"",
                objectType:"",
                modiStudId:"",
                modiTeacId:"",
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
        deleteStudent(student){
            //log part start
            this.newLog.action +="Delete studennt which has information " + this.getString(student)
            this.newLog.objectType = "student";
            this.newLog.modiStudId = student.id;
            this.newLog.modiTeacId = 0;
            axios.post(`http://localhost:8081/log/post`, this.newLog);
            //log part end
            if (confirm("Are you sure you want to delete this student?")) {
                axios.delete(`http://localhost:8081/student/delete/${student.id}`).then(() => {
                    this.studentList = this.studentList.filter((stu) => stu.id !== student.id);
                    alert("Delete Successfully!");
                });
            }

        },
        updateStudent(){
            axios.put(`http://localhost:8081/student/put`, this.currentStudent)
                .then(() => {
                    this.showEditStudent = false;
                    alert(`Edit Successful`);
                    //log part start
                    this.newLog.action +="Updated into "+ this.getString(this.currentStudent)
                    this.newLog.objectType = "student";
                    this.newLog.modiStudId = this.currentStudent.id;
                    this.newLog.modiTeacId = 0;
                    axios.post(`http://localhost:8081/log/post`, this.newLog);
                    //log part end
                })
                .catch(() => {
                    alert("Failed to update");
                });
        },
        addStudent(){
            axios.post(`http://localhost:8081/student/post`, this.newStudent)
                .then((response) => {
                    alert('Successfully added student!');

                    //log part start
                    this.newLog.action +="New student created "+ this.getString(this.newStudent)
                    this.newLog.objectType = "student";
                    this.newLog.modiStudId = response.data.id;
                    this.newLog.modiTeacId = 0;
                    axios.post(`http://localhost:8081/log/post`, this.newLog);
                    //log part end

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
        // -----------------------------------------------------
        getAllTeachers() {
            axios.get("http://localhost:8081/teacher/getAll").then((response) => {
                this.teacherList = response.data;
            });
        },
        getByIdTeacher(id){
            axios.get(`http://localhost:8081/teacher/get/${id}`).then((response)=>{
                this.currentTeacher = response.data;
            })
        },
        deleteTeacher(teacher){
            //log part start
            this.newLog.action +="Delete teacher with information " + this.getString(teacher)
            this.newLog.objectType = "teacher";
            this.newLog.modiStudId = 0;
            this.newLog.modiTeacId = teacher.id;
            axios.post(`http://localhost:8081/log/post`, this.newLog);
            //log part end

            if (confirm("Are you sure you want to delete this teacher?")) {
                axios.delete(`http://localhost:8081/teacher/delete/${teacher.id}`).then(() => {
                    this.teacherList = this.teacherList.filter((teac) => teac.id !== teacher.id);
                    alert("Delete Successfully!");
                });
            }
        },
        updateTeacher(){
            axios.put(`http://localhost:8081/teacher/put`, this.currentTeacher)
                .then(() => {
                    this.showEditTeacher = false;
                    alert(`Edit Successful`);

                    //log part start
                    this.newLog.action +="Updated into "+ this.getString(this.currentTeacher)
                    this.newLog.objectType = "teacher";
                    this.newLog.modiStudId = 0;
                    this.newLog.modiTeacId = this.currentStudent.id;
                    axios.post(`http://localhost:8081/log/post`, this.newLog);
                    //log part end
                })
                .catch(() => {
                    alert("Failed to update");
                });
        },
        addTeacher(){
            axios.post(`http://localhost:8081/teacher/post`, this.newTeacher)
                .then((response) => {
                    alert('Successfully added teacher!');

                    //log part start
                    this.newLog.action +="New teacher created "+ this.getString(this.newTeacher)
                    this.newLog.objectType = "teacher";
                    this.newLog.modiStudId = 0;
                    this.newLog.modiTeacId = response.data.id;
                    axios.post(`http://localhost:8081/log/post`, this.newLog);
                    //log part end

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
        },
        editModeTeacher(id){
            this.getByIdTeacher(id);
            this.showEditTeacher = !this.showEditTeacher;
        },
        addModeTeacher(){
            this.showAddTeacher = !this.showAddTeacher;
        },
        prevPageTeacher() {
            if (this.currentPageTeacher > 1) {
                this.currentPageTeacher--
            }
        },
        nextPageTeacher() {
            if ((this.currentPageTeacher)*this.perPageTeacher < this.totalPagesTeacher) {
                this.currentPageTeacher++
            }
        },

        FindByClass(searchClass){
            axios.get(`http://localhost:8081/teacher/getByCondition?inClass=${searchClass}`).then((response) => {
                this.teacherList = response.data;
            });
            axios.get(`http://localhost:8081/student/getByCondition?inClass=${searchClass}`).then((res) => {
                this.studentList = res.data;
            });
            this.refresh = false;
            this.refresh = true;
            this.currentPage = 1;
            this.currentPageTeacher = 1;
        },
    },

    mounted() {
        this.getAllStudents();
        this.getAllTeachers();
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
        },

        totalPagesTeacher() {
            return (this.teacherList.length)
        },
        // Calculate the page division
        paginatedListTeacher() {
            const start = (this.currentPageTeacher - 1) * this.perPageTeacher
            const end = start + this.perPageTeacher
            return this.teacherList.slice(start, end)
        },
        getString: function (){
            return function(data) {
                return JSON.stringify(data)
            }
        }
    },
}
</script>

<style>
.Tables{
    position: absolute;
    top: 5vw;
    left: 0vw;
}

.SearchClass{
    position: absolute;
    top: 2vw;
    left: 22vw;
    width: 35vw;
    font-size: 30px;
    border: 1px solid black;
    padding: 4px;
}
input {
     padding: 4px;
     width: 10vw;
     margin-bottom: 5px;
     border: none;
     border-bottom: 2px solid #ccc;
     font-size: 16px;
 }

input:focus {
    outline: none;
    border-color: #32326c;
}

button[type="SearchClass"]{
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 5px 7px;
    border-radius: 3px;
    cursor: pointer;
    margin-right: 2px;
    font-size: 20px;
    position: absolute;
    right: 1vw;
    top:25%;
}
.containerT{
    position:absolute;
    left: 22vw;
    top: 43vw;
    width: 75vw;
    height: 80vw;
}

.container{
    position:absolute;
    left: 22vw;
    top: 6vw;
    width: 75vw;
    height: 80vw;
}

button[type="addButton"]{
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 5px 5px;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    right: 2vw;
    top: 0vw;
    font-size: 20px;
}
button[type="addButton"]:hover {
    background-color: #24244f;
}

</style>
