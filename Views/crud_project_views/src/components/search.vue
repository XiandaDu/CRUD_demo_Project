<template>
    <button @click="searchMode" type="searchButton">Search Students</button>
    <button @click="deleteMode" type="deleteButton">Delete Students</button>
    <div class="result">
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
            <td>{{student.createTime}}</td>
        </tr>
        </tbody>
    </table>
    <div class="pageControl">
        <button @click="prevPage">Last Page</button>
        {{ currentPage }}
        <button @click="nextPage">Next Page</button>
    </div>

    </div>
    <div class="searchStudent" v-if="showSearchStudent">
        <h3>Search Student</h3>
        <form>
            <label for="addName">Name： </label>
            <input type="text" id="addName" v-model="oprationCondition.name" required>
            <label for="addGender">Gender： </label>
            <input type="text" id="addEmail" v-model="oprationCondition.gender" required>
            <label for="addClass">Class： </label>
            <input type="number" id="addClass" v-model="oprationCondition.inClass" required>
            <label for="addAge">Minmimum Age： </label>
            <input type="number" id="minAge" v-model="oprationCondition.minAge" required>
            <label for="addAge">Maximum Age： </label>
            <input type="number" id="maxAge" v-model="oprationCondition.maxAge" required>
            <label for="addPhone">Phone： </label>
            <input type="text" id="addPhone" v-model="oprationCondition.phone" required>
            <label for="addAddr">Address： </label>
            <input type="text" id="addAddr" v-model="oprationCondition.addr" required>

            <button type="submit" @click.prevent="advancedSearch">Search</button>
            <button type="submit" @click="searchMode">Cancel</button>
        </form>
    </div>

    <div class="deleteStudent" v-if="showDeleteStudent">
        <h3>Delete Student</h3>
        <form>
            <label for="addName">Name： </label>
            <input type="text" id="addName" v-model="oprationCondition.name" required>
            <label for="addGender">Gender： </label>
            <input type="text" id="addEmail" v-model="oprationCondition.gender" required>
            <label for="addClass">Class： </label>
            <input type="number" id="addClass" v-model="oprationCondition.inClass" required>
            <label for="addAge">Minmimum Age： </label>
            <input type="number" id="minAge" v-model="oprationCondition.minAge" required>
            <label for="addAge">Maximum Age： </label>
            <input type="number" id="maxAge" v-model="oprationCondition.maxAge" required>
            <label for="addPhone">Phone： </label>
            <input type="text" id="addPhone" v-model="oprationCondition.phone" required>
            <label for="addAddr">Address： </label>
            <input type="text" id="addAddr" v-model="oprationCondition.addr" required>

            <button type="submit" @click.prevent="advancedDelete">Delete</button>
            <button type="submit" @click="deleteMode">Cancel</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios'
  export default {
      methods: {
          advancedSearch(){
              axios.get("http://localhost:8081/student/getByCondition", this.oprationCondition).then((response) => {
                  this.resultList = response.data;
              });
              this.showSearchStudent = false;
          },
          advancedDelete(){

          },
          deleteMode(){
              this.showSearchStudent = false;
              this.showDeleteStudent = !this.showDeleteStudent;
          },
          searchMode(){
              this.showDeleteStudent = false;
              this.showSearchStudent = !this.showSearchStudent;
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
              perPage: 6,
              currentPage: 1,
              resultList: [],
              oprationCondition: {
                  id:0,
                  name:"",
                  gender:"",
                  inClass:0,
                  age:0,
                  phone:"",
                  addr:"",
                  createTime:"",
                  updateTime:"",
                  minAge:0,
                  maxAge:0,
              },
              showSearchStudent: false,
              showDeleteStudent: false,
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
    left: 12vw;
}

.deleteStudent,
.searchStudent{
    position: absolute;
    top: 6vw;
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
    right: 10vw;
    top: 0vw;
}
button[type="deleteButton"]:hover {
    background-color: #24244f;
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
     right: 19vw;
     top: 0vw;
 }
button[type="searchButton"]:hover {
    background-color: #24244f;
}
</style>