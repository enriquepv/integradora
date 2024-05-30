<template>
    <LoggedBasicLayout>
        <div id="carrito">
            <h2>Carrito de compras</h2>
                <div id="detalles"  v-for="producto in carrito" :key="producto.id">
                    <p>Nombre: {{ producto.nombre }}</p>
                    <p>Precio: {{ producto.precio }}€</p>
                    <button class="boton" @click="eliminarProducto(producto)">Eliminar producto</button>
                    <hr>
                </div>


                <p id="total">Total: {{ calcularTotal() }}€</p>
                <button class="botonCompra" @click="realizarCompra">Realizar compra</button>

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
            carrito: [],
        };
    },
    mounted() {
        const storedItems = localStorage.getItem('carrito');
        if (storedItems) {
            this.carrito = JSON.parse(storedItems);
        }
    },
    methods: {
        eliminarProducto(producto) {
            const index = this.carrito.indexOf(producto);
            if (index > -1) {
                this.carrito.splice(index, 1);
                localStorage.setItem('carrito', JSON.stringify(this.carrito));
            }
        },

        calcularTotal() {
        let total = 0;
        for (let producto of this.carrito) {
            total += producto.precio;
        }
        return total;
        },

        realizarCompra() {
            this.carrito = [];
            this.$router.push({ name: 'Inicio' });
            alert('Compra realizada.');
        }
    }
};



</script>

<style>
#total{
    font-family: "Bebas Neue", sans-serif;
    color: #6495ed;
    font-size: 20px;
}
#detalles{
    font-family: "Bebas Neue", sans-serif;
    color: #6495ed;
}

.boton:hover{
  background-color:rgb(252, 109, 109) ;
}
.boton{
  background-color: red;
  color: white;
  font-weight: bold;
  border-radius: 20px;
}
.botonCompra:hover{
    background-color: rgb(105, 213, 105);
}
.botonCompra{
    background-color:rgb(20, 233, 20) ;
}
</style>