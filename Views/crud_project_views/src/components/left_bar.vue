

<template>

    <div class="slider-container">

        <div class="slider-handle" @click="toggleSlider">
            <span v-if="!isOpen">Click to open</span>
            <span v-else>Click to close</span>
        </div>

        <div class="slider" :class="{ 'open': isOpen}">
            <div class="slider-content1" @click="change(1)" :class="{'active':ChangePage===1}">
                <p>Students Information</p>
            </div>
            <div class="slider-content2" :class="{'open':isOpen, 'active':ChangePage===2}" @click="change(2)">
                <p>Teachers Information</p>
            </div>
        </div>

        <div class="slider-class_info" :class="{'open': isOpen, 'active':ChangePage===3}"  @click="change(3)">
            <p>Class information</p>
        </div>
        <div class="slider-log" :class="{'open': isOpen, 'active':ChangePage===4}"  @click="change(4)">
            <p>Log record</p>
        </div>
    </div>

    <p>{{ChangePage}} test in LB</p>

</template>

<script>
export default {
    props: {
        toTop: {
            type: Number,
            default: 0
        }
    },

    data() {
        return {
            ChangePage:0,
            isOpen: false
        }
    },

    methods: {
        toggleSlider() {
            this.isOpen = !this.isOpen
        },
        change(page){
            this.ChangePage=page
            this.$emit("on-change-page", this.ChangePage);
        }
    }
}
</script>

<style>
.slider-container {
    position: absolute;
    left: 0;
    top:0;
    width: 20vw;
    height: 100vw;
    overflow: hidden;
    background-color: #282828;
}

.slider {
    position: absolute;
    top:0;
    left:0;
    width: 100%;
    /*height's value will impact how much does the blocks slides*/
    height: 5%;
    background-color: #181818;
    transition: transform 0.3s ease-in-out;
}

.slider.open {
    transform: translateY(+100%);
}

.slider-handle {
    background-color: #2e4472;
    color: #fff;
    cursor: pointer;
    padding: 10px;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 5%;
    z-index: 1;
    border: 1px solid #000000;
}

.slider-handle span {
    font-size: 14px;
}

.slider-content1 {
    color: #ffffff;
    background-color: #20335d;
    padding: 10px;
    position: inherit;
    top: 0;
    left: 0;
    transform: translateY(0);
    width: 100%;
    height: 100%;
    z-index: 0;
    cursor: pointer;
    border: 1px solid #000000;
}
.slider-content1.active{
    border: 1px solid #ffffff;
}

.slider-content2 {
    color: #ffffff;
    background-color: #20335d;
    padding: 10px;
    position: inherit;
    top: 0;
    left: 0;
    transform: translateY(0);
    width: 100%;
    height: 100%;
    z-index: 0;
    cursor: pointer;
    border: 1px solid #000000;
}

.slider-content2.open{
    transform: translateY(100%);
}
.slider-content2.active{
    border: 1px solid #ffffff;
}

.slider-class_info{
    color: #ffffff;
    background-color: #2e4472;
    padding: 10px;
    position: inherit;
    top: 5%;
    left: 0;
    width: 100%;
    height: 5%;
    z-index: 0;
    transition: transform 0.3s ease-in-out;
    cursor: pointer;
    border: 1px solid #000000;
}

.slider-class_info.open{
    transform: translateY(200%);
}
.slider-class_info.active{
    border: 1px solid #ffffff;
}
.slider-log{
    color: #ffffff;
    background-color: #2e4472;
    padding: 10px;
    position: inherit;
    top: 10%;
    left: 0;
    width: 100%;
    height: 5%;
    z-index: 0;
    transition: transform 0.3s ease-in-out;
    cursor: pointer;
    border: 1px solid #000000;
}

.slider-log.open{
    transform: translateY(200%);
}
.slider-log.active{
    border: 1px solid #ffffff;
}
</style>
