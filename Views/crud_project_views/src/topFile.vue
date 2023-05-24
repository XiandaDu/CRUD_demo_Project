<template>
    <div class="header" v-if="this.$route.path!=='/notFound'">
        <router-link to="/"><button type="welcome" :class=" {'active': this.$route.path==='/'} ">Welcome</button></router-link>
        <router-link to="/table"><button type="table" :class=" {'active': this.$route.path==='/table'} ">Table</button></router-link>
        <router-link to="/search"><button type="search" :class=" {'active': this.$route.path==='/search'} ">Search</button></router-link>
    </div>
    <router-view v-slot="{ Component }">
        <transition name="bounce">
            <component :is="Component" />
        </transition>
    </router-view>
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
    z-index: 50;
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
    z-index: 50;
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
    z-index: 50;
}
button:hover {
    background-color: #2e4472;
}

button.active{
   color: black;
}

.bounce-enter-active {
    animation: bounce-in 0.3s;
}
.bounce-leave-active {
    animation: bounce-in 0.3s reverse;
}
@keyframes bounce-in {
    0% {
        transform: scale(0);
    }
    50% {
        transform: scale(1.25);
    }
    100% {
        transform: scale(1);
    }
}

</style>
<script setup>
</script>