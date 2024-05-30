<template>
    <div class="botones">
        <!-- <div class="links"><RouterLink to = "/" class = "menu-link" id="menu1">Inicio</RouterLink></div>
        <div class="links"><RouterLink to = "/about" class = "menu-link" id="menu2">Sobre nosotros</RouterLink></div>
        <div class="links"><RouterLink to = "/catalogo" class = "menu-link" id="menu3">Catalogo</RouterLink></div>
        <div class="links"><RouterLink to = "/tiendas" class = "menu-link" id="menu4">¿Dónde estamos?</RouterLink></div> -->
        <p>2024 WODASPORTS. Todos los derechos reservados.</p>
    </div>
</template>

<script>
    import {RouterLink} from 'vue-router';

    export default {
        name: "HeaderVue",
    };
</script>

<style scoped>
.botones{
    display:flex;
    position: static;
    bottom: 0;
    left: 0;
    right: 0;
    flex-flow: row wrap;
    justify-content: center ;
    align-items: center;
    background-color: #6495ed;
    margin-top: 20px;
    height: 90px;
    width: 100%;
    padding-bottom: 20px;
    color: white;
    font-family: "Bebas Neue", sans-serif;
    font-weight: lighter;;
    font-size: 20px ;
}

.links{
    display: flex;
    margin-top: 20px;
    height: 50px;
    background-color: white;
    border-radius: 20px;
    align-items: center;
}
.menu-link{
    font-family: "Bebas Neue", sans-serif;
    color:#6495ed;
    text-decoration: none;
    text-transform: uppercase;
    font-weight: lighter;;
    font-size: 25px ;
     
   
     
}
.links:hover{
  background-color: rgb(235, 225, 225);
}

</style>