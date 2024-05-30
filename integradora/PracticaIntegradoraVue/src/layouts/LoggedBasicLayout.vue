<!--Contenido de toda la pagina de inicio (header, etc.)-->
<template>
    <div>
        <LoggedHeader></LoggedHeader>
        <div>
            <slot></slot>
        </div>
        <LoggedFooter></LoggedFooter>
    </div>
</template>

<script>

import LoggedFooter from '../views/logged/LoggedFooter.vue';
import LoggedHeader from '../views/logged/LoggedHeader.vue';
    export default {
        name: "BasicLayout",

        components: {
    
    LoggedHeader,
    LoggedFooter
}
    };
</script>

<style scoped>
div{
    background-color: white;
    color: white;
}



</style>