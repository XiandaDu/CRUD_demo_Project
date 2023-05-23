<template>
    <button @click="searchMode(1)" type="searchButton">Search Students</button>
    <button @click="deleteMode(1)" type="deleteButton">Delete Students</button>
    <button @click="searchMode(2)" type="searchButtonT">Search Teachers</button>
    <button @click="deleteMode(2)" type="deleteButtonT">Delete Teachers</button>
    <div class="result">
        <div v-if="showStudentResult">
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
                    <td>{{student.createTime.substring(0,19)}}</td>
                </tr>
                </tbody>
            </table>
            <div class="pageControl">
                <button @click="prevPage">Last Page</button>
                {{ currentPage }}
                <button @click="nextPage">Next Page</button>
            </div>
        </div>

        <div v-if="showTeacherResult">
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Class</th>
                    <th>Age</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Title</th>
                    <th>Email</th>
                    <th>Create Time</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="teacher in paginatedList" :key="teacher.id">
                    <td>{{ teacher.name }}</td>
                    <td>{{ teacher.gender }}</td>
                    <td>{{ teacher.inClass}}</td>
                    <td>{{ teacher.age }}</td>
                    <td>{{ teacher.phone }}</td>
                    <td>{{ teacher.addr }}</td>
                    <td>{{ teacher.title }}</td>
                    <td>{{ teacher.email }}</td>
                    <td>{{ teacher.createTime.substring(0,10) }}</td>
                </tr>
                </tbody>
            </table>
            <div class="pageControl">
                <button @click="prevPage">Last Page</button>
                {{ currentPage }}
                <button @click="nextPage">Next Page</button>
            </div>
        </div>
    </div>

    <div class="searchStudent" v-if="showSearchStudent">
        <h3>Search Student</h3>
        <form>
            <label for="addName">Name： </label>
            <input type="text" id="addName" v-model="operationCondition.name" required>
            <label for="addGender">Gender： </label>
            <input type="text" id="addEmail" v-model="operationCondition.gender" required>
            <label for="addClass">Class： </label>
            <input type="number" id="addClass" v-model="operationCondition.inClass" required>
            <label for="addAge">Minmimum Age： </label>
            <input type="number" id="minAge" v-model="operationCondition.minAge" required>
            <label for="addAge">Maximum Age： </label>
            <input type="number" id="maxAge" v-model="operationCondition.maxAge" required>
            <label for="addPhone">Phone： </label>
            <input type="text" id="addPhone" v-model="operationCondition.phone" required>
            <label for="addAddr">Address： </label>
            <input type="text" id="addAddr" v-model="operationCondition.addr" required>

            <button type="submit" @click.prevent="advancedSearch(1)">Search</button>
            <button type="submit" @click="searchMode(1)">Cancel</button>
        </form>
    </div>

    <div class="deleteStudent" v-if="showDeleteStudent">
        <h3>Delete Student</h3>
        <form>
            <label for="addName">Name： </label>
            <input type="text" id="addName" v-model="operationCondition.name" required>
            <label for="addGender">Gender： </label>
            <input type="text" id="addEmail" v-model="operationCondition.gender" required>
            <label for="addClass">Class： </label>
            <input type="number" id="addClass" v-model="operationCondition.inClass" required>
            <label for="addAge">Minmimum Age： </label>
            <input type="number" id="minAge" v-model="operationCondition.minAge" required>
            <label for="addAge">Maximum Age： </label>
            <input type="number" id="maxAge" v-model="operationCondition.maxAge" required>
            <label for="addPhone">Phone： </label>
            <input type="text" id="addPhone" v-model="operationCondition.phone" required>
            <label for="addAddr">Address： </label>
            <input type="text" id="addAddr" v-model="operationCondition.addr" required>

            <button type="submit" @click.prevent="advancedDelete(1)">Delete</button>
            <button type="submit" @click="deleteMode(1)">Cancel</button>
        </form>
    </div>

    <div class="searchStudent" v-if="showSearchTeacher">
        <h3>Search Teacher</h3>
        <form>
            <label for="addName">Name： </label>
            <input type="text" id="addName" v-model="operationCondition.name" required>
            <label for="addGender">Gender： </label>
            <input type="text" id="addEmail" v-model="operationCondition.gender" required>
            <label for="addClass">Class： </label>
            <input type="number" id="addClass" v-model="operationCondition.inClass" required>
            <label for="addAge">Minmimum Age： </label>
            <input type="number" id="minAge" v-model="operationCondition.minAge" required>
            <label for="addAge">Maximum Age： </label>
            <input type="number" id="maxAge" v-model="operationCondition.maxAge" required>
            <label for="addPhone">Phone： </label>
            <input type="text" id="addPhone" v-model="operationCondition.phone" required>
            <label for="addAddr">Address： </label>
            <input type="text" id="addAddr" v-model="operationCondition.addr" required>
            <label for="editEmail">Email： </label>
            <input type="text" id="editEmail" v-model="operationCondition.email" required>
            <label for="editTitle">Title： </label>
            <input type="text" id="editTitle" v-model="operationCondition.title" required>

            <button type="submit" @click.prevent="advancedSearch(2)">Search</button>
            <button type="submit" @click="searchMode(2)">Cancel</button>
        </form>
    </div>

    <div class="deleteStudent" v-if="showDeleteTeacher">
        <h3>Delete Teacher</h3>
        <form>
            <label for="addName">Name： </label>
            <input type="text" id="addName" v-model="operationCondition.name" required>
            <label for="addGender">Gender： </label>
            <input type="text" id="addEmail" v-model="operationCondition.gender" required>
            <label for="addClass">Class： </label>
            <input type="number" id="addClass" v-model="operationCondition.inClass" required>
            <label for="addAge">Minmimum Age： </label>
            <input type="number" id="minAge" v-model="operationCondition.minAge" required>
            <label for="addAge">Maximum Age： </label>
            <input type="number" id="maxAge" v-model="operationCondition.maxAge" required>
            <label for="addPhone">Phone： </label>
            <input type="text" id="addPhone" v-model="operationCondition.phone" required>
            <label for="addAddr">Address： </label>
            <input type="text" id="addAddr" v-model="operationCondition.addr" required>
            <label for="editEmail">Email： </label>
            <input type="text" id="editEmail" v-model="operationCondition.email" required>
            <label for="editTitle">Title： </label>
            <input type="text" id="editTitle" v-model="operationCondition.title" required>

            <button type="submit" @click.prevent="advancedDelete(2)">Delete</button>
            <button type="submit" @click="deleteMode(2)">Cancel</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios'
  export default {
      methods: {
          advancedSearch(num){
              if (num===1) {
                  /* "..." can automatically expand the object we input, see details in ES6. I struggled on passing an obj as param for a while */
                  axios.get("http://localhost:8081/student/getByCondition", {params: {...this.operationCondition}}).then((response) => {
                      this.resultList = response.data;
                  });
                  this.showSearchStudent = false;
                  this.showTeacherResult = false;
                  this.showStudentResult = true;
                  this.currentPage = 1;
              } else {
                  axios.get("http://localhost:8081/teacher/getByCondition", {params: {...this.operationCondition}}).then((response) => {
                      this.resultList = response.data;
                  });
                  this.showSearchTeacher = false;
                  this.showStudentResult = false;
                  this.showTeacherResult = true;
                  this.currentPage = 1;
              }
          },
          advancedDelete(){

          },
          deleteMode(num){
              if (num===1) {
                  this.showSearchTeacher = false;
                  this.showSearchStudent = false;
                  this.showDeleteTeacher = false;
                  this.showDeleteStudent = !this.showDeleteStudent;
              } else {
                  this.showSearchTeacher = false;
                  this.showSearchStudent = false;
                  this.showDeleteStudent = false;
                  this.showDeleteTeacher = !this.showDeleteTeacher;
              }
          },
          searchMode(num){
              if (num===1) {
                  this.showSearchTeacher = false;
                  this.showDeleteStudent = false;
                  this.showDeleteTeacher = false;
                  this.showSearchStudent = !this.showSearchStudent;
              } else {
                  this.showSearchStudent = false;
                  this.showDeleteStudent = false;
                  this.showDeleteTeacher = false;
                  this.showSearchTeacher = !this.showSearchTeacher;
              }
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
      data(){
          return {
              perPage: 10,
              currentPage: 1,
              resultList: [],
              operationCondition: {
                  name:"",
                  gender:"",
                  inClass:"",
                  phone:"",
                  addr:"",
                  minAge:"",
                  maxAge:"",
                  email: "",
                  title: "",
              },
              showSearchStudent: false,
              showDeleteStudent: false,
              showSearchTeacher: false,
              showDeleteTeacher: false,
              showStudentResult: false,
              showTeacherResult: false,
          }
      },
      computed: {
          totalPages() {
              return (this.resultList.length)
          },
          // Calculate the page division
          paginatedList() {
              const start = (this.currentPage - 1) * this.perPage
              const end = start + this.perPage
              return this.resultList.slice(start, end)
          }
      },
  }
</script>

<style>
.result{
    position: absolute;
    top: 6vw;
    left: 6vw;
}

.deleteStudent,
.searchStudent{
    position: absolute;
    top: 10vw;
    right: 2vw;
    width: 30vw;
}
button[type="deleteButton"]{
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    right: 7vw;
    top: 1vw;
    font-size: 24px;
}
button[type="searchButton"]{
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    right: 20vw;
    top: 1vw;
    font-size: 24px;
}
button[type="deleteButtonT"]{
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    right: 7vw;
    top: 5vw;
    font-size: 24px;
}
button[type="searchButtonT"]{
    background-color: #3f698c;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    right: 20vw;
    top: 5vw;
    font-size: 24px;
}
button:hover {
    background-color: #24244f;
}

</style>