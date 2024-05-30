<template>
    <LoggedBasicLayout>
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
    </LoggedBasicLayout>
</template>

<script>
import LoggedBasicLayout from '../../layouts/LoggedBasicLayout.vue';


export default {
  components: {
    LoggedBasicLayout
},
  data() {
    return {
      productos: [],
    };
  },
  mounted() {
    fetch("src/components/ProductosPlaya.json")
      .then((response) => response.json())
      .then((data) => {
        this.productos = data;
      });
  },
  methods: {
        agregarProducto(producto) {
            const carrito = localStorage.getItem('carrito');
            let items = [];
            if (carrito) {
                items = JSON.parse(carrito);
            }
            items.push(producto);
            localStorage.setItem('carrito', JSON.stringify(items));
            alert('Producto añadido al carrito');
        },

        verDetalle(producto) {
            this.productoSeleccionado = producto;
            this.$router.push({ name: 'DetalleProducto', params: { id: producto.id } });
        },
    }
};
</script>
<style scoped>
#productImg{
  width: 175px;
  height: 175px;
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
