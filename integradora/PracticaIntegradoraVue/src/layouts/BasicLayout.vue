<!--Contenido de toda la pagina de inicio (header, etc.)-->
<template>
    <div>
        <HeaderVue></HeaderVue>
        <div>
            <slot></slot>
        </div>
        <FooterView></FooterView>
    </div>
</template>

<script>
import FooterView from '../views/FooterView.vue';
import HeaderVue from '../views/HeaderVue.vue';
    export default {
        name: "BasicLayout",

        components: {
    HeaderVue,
    FooterView
}
    };
</script>

<style scoped>
div{
    background-color: white;
    color: white;
    
}

</style>