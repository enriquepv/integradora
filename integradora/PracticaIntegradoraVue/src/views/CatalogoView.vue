<template>
   <BasicLayout>
    <div class="catalogo">
      <div class="categoria">
        <RouterLink to = "/aeg" class = "menu-link" id="menu5">
          <img id="fotoAeg" src="../assets/playa.webp" alt="aeg">
        </RouterLink>
        <p class="texto">Productos de playa</p>
      </div>
      <div class="categoria">
        <RouterLink to = "/gbb" class = "menu-link" id="menu6">
          <img id="fotoGbb" src="../assets/piscina.jpeg" alt="gbb">
        </RouterLink>
        <p class="texto">Productos de piscina</p>
        
      </div>
    </div>
    <div class="productos">
      <div v-for="producto in productos" :key="producto.idProducto" class="productItem">
        

        <div class="productDetails">
          <h3>{{ producto.nombre }}</h3>
          <p>Descripcion: {{ producto.descripcion }}</p>
          <p>Precio: {{ producto.precio }} €</p>
          <button @click="agregarProducto(producto)">Añadir al carrito</button>
          
        </div>
          
            <img :src="producto.imagen" id="productImg" alt="Imagen del producto">
          
      </div>
    </div>
  </BasicLayout>
</template>

<script >
import BasicLayout from '../layouts/BasicLayout.vue';


export default {
  components: {
    BasicLayout,
  },
  data() {
    return {
      productos: [],
    };
  },
  mounted() {
    fetch("src/components/Productos.json")
      .then((response) => response.json())
      .then((data) => {
        this.productos = data;
      });
  },
  methods:{
  agregarProducto() {
    window.alert('Esta función solo está disponible al iniciar sesión/registrarse')
  },
}
};

</script>

<style >
.catalogo{
    margin-top: 50px;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    flex-flow: row wrap;
    
    height: auto;
    padding-bottom: 20px;
    border-radius: 20px;
    padding-top: 2%;
    
}

.categoria{
    display: flex;
    justify-content: center;
    align-items: center;
}


#fotoAeg:hover{
  opacity: 30%;
  
}

#fotoAeg{
  position: relative;
  width: 500px;
  border-radius: 20px;
  z-index: 1;
}


#fotoGbb:hover{
  opacity: 30%;
  
}

#fotoGbb{
  position: relative;
  width: 500px;
  border-radius: 20px;
  z-index: 1;
}



#productImg{
  width: 175px;
  height: 100%;
  background-color: white;
}
.productos {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
    padding: 10px;
}

.productItem {
  border: 2px solid #6495ed;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  
}
.productDetails{
padding-left: 10%;
padding-bottom: 5%;
color: #6495ed;
font-family: "Bebas Neue", sans-serif;;
}

button:hover{
background-color:rgb(184, 246, 96) ;
}
button{
background-color: #6495ed;
color: white;
font-weight: bold;
border-radius: 20px;
}



</style>