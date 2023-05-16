
<template>
    <LB :to-top="OnPage" @on-change-page="handleChangePage"></LB>
    <p>TopFile Test {{OnPage}}</p>
        <p v-if="OnPage===2"><Teachers></Teachers></p>
        <p v-else-if="OnPage===1"><Students></Students></p>
        <p v-else-if="OnPage===3"><Classes></Classes></p>
        <p v-else-if="OnPage===4"><Log></Log></p>
        <p v-else>An unknown error happens</p>
</template>

<script>
import LB from "./components/left_bar.vue";
import Log from "@/components/log_record/show_logs.vue";
import Classes from "@/components/class_info/show_classes.vue";
import Teachers from "@/components/teacher_info/show_teacher.vue";
import Students from "@/components/student_info/show_students.vue";

export default {
    components: {
        LB,
        Log,
        Classes,
        Teachers,
        Students
    },
    data() {
        return {
            OnPage: 0
        };
    },
    methods: {
        handleChangePage(page) {
            this.OnPage = page;
        }
    }
};
</script>