<script setup>
import BasicLayout from '../layouts/BasicLayout.vue';
</script>

<template>
  <BasicLayout>
    <div id="contenedorPrincipalHome">
      <div class="foto">
        <RouterLink to = "/about" class = "menu-link" id="menu2">
          <img id="fotoAboutUs" src="../assets/WODASPORTS.jpg" alt="sobre nosotros">
        </RouterLink>
        <p class="texto">¿Quienes somos?</p>
      </div>
      <div class="foto">
        <RouterLink to = "/catalogo" class = "menu-link" id="menu3">
          <img id="fotoCatalogo" src="../assets/productos.jpg" alt="catalogo">
        </RouterLink>
        <p class="texto">Catálogo</p>
        
      </div>
      <div class="foto">
        <RouterLink to = "/tiendas" class = "menu-link" id="menu4">
          <img id="fotoTiendas" src="../assets/DondeEstamos.jpg" alt="ubicacion">
        </RouterLink>
        <p class="texto">¿Dónde estamos?</p>
        
      </div>
    </div>
  </BasicLayout>
</template>
<style>
/* body{
  background-color: rgb(32, 32, 32);
} */



#contenedorPrincipalHome{
  margin-top: 50px;
  display: flex;
  justify-content: space-evenly;

  flex-flow: row wrap;

}

.foto{
  display: flex;
  justify-content: center;
  align-items: center;
}

#fotoAboutUs:hover{
  opacity: 30%;
  
}

#fotoAboutUs{
  position: relative;
  width: 500px;
  height: 275px;
  border-radius: 20px;
  z-index: 1;
}


#fotoCatalogo:hover{
  opacity: 30%;
  
}

#fotoCatalogo{
  position: relative;
  width: 500px;
  height: 275px;
  border-radius: 20px;
  z-index: 1;
}


#fotoTiendas:hover{
  opacity: 30%;
  
}

#fotoTiendas{
  position: relative;
  width: 700px;
  border-radius: 20px;
  z-index: 1;
  margin-top: 20px;
}

.texto{
  color: rgb(64, 141, 234);
  text-transform: uppercase;
  font-size:x-large;
  font-weight: bold;
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  /* text-shadow: 1px 1px 4px black; */
  position: absolute;
  z-index: 0;
}
</style>
