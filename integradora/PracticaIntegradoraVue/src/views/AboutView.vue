<template>
  <BasicLayout>
    <div class="about">
    
    <h1>Sobre nosotros</h1>
    <p>
      Somos una tienda con origen en Varsovia cuya especialidad son los articulos de deportes acuaticos <br> 
      Ofrecemos servicio al cliente de 12:00 a 20:00 tanto para clientes que hayan obtenido alguno de nuestros productos<br>
      como para los que aun están pensando en su futura tabla de surf 🤙🤙🤙<br>
      Tenemos un amplio catálogo de productos que incluye gafas de buceo, monos de neopreno, tablas de surf y más sorpresas<br>
      que podrás descubrir en nuestro catálogo.
    </p>

  </div>
  </BasicLayout>
  
</template>
<script setup>
import BasicLayout from '../layouts/BasicLayout.vue';

</script>

<style>

  .about {
    width: 90%;
    height: 300px;
    color: #6495ed;
    margin: 50px;
    background-color: white;
    border-radius: 20px;
    /* padding-top: 2%; */
    padding-left: 10%;
    padding-right: 10%;
    font-weight: lighter;
    font-size: 25px;
    font-family: "Bebas Neue", sans-serif;
    /* font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; */
  }

</style>
