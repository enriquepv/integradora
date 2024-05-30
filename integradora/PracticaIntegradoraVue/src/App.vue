<script setup>
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  

  <RouterView />
</template>

<style >


/* .links:hover{
  background-color: rgb(239, 152, 90);
} */
.links{
    display: flex;
    width: 175px;
    height: 50px;
    /* background-color: orange; */
    border-radius: 20px;
    align-items: center;
    justify-content: center;
    margin-right: 10%;
    
}
a{
    color:white;
    text-decoration: none;
    text-transform: uppercase;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-weight: bold;
     
    /* text-shadow: 1px 1px 3px black; */
     
}
.botones{
  display: flex;
  flex-direction: row ;
}


</style>
