<template>
    <div class="header">
        <router-link to="/welcome"><button type="welcome">Welcome</button></router-link>
        <router-link to="/table"><button type="table">Table</button></router-link>
        <router-link to="/search"><button type="search">Search</button></router-link>
    </div>
    <router-view></router-view>
</template>

<style>

button[type='welcome']{
    font-size: 48px;
    background-color: #3f698c;
    color: white;
    border: 1px solid black;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    left: 20vw;
    top: 0vw;
    width: 15vw;
    height: 5vw;
}
button[type='table']{
    font-size: 48px;
    background-color: #3f698c;
    color: white;
    border: 1px solid black;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    left: 35vw;
    top: 0vw;
    width: 15vw;
    height: 5vw;
}
button[type='search']{
    font-size: 48px;
    background-color: #3f698c;
    color: white;
    border: 1px solid black;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    position: absolute;
    left: 50vw;
    top: 0vw;
    width: 15vw;
    height: 5vw;
}
button:hover {
    background-color: #2e4472;
}
</style>
<script setup>
</script>