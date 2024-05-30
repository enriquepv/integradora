<template>
    <LoggedBasicLayout>
    <div>
        <template>
        <div v-if="producto">
            <div class="producto-detalle">
                <h2>{{ producto.nombre }}</h2>
                <p>Descripción: {{ producto.descripcion }}</p>
                <p>Precio: {{ producto.precio }}€</p>
            </div>
        </div>
        </template>

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
      LoggedBasicLayout,
      producto: null,
    };
  },
  mounted() {
    const idProducto=this.$route.params.idProducto
    fetch("../../../src/components/Productos.json")
      .then((response) => response.json())
      .then((data) => {
        this.productos = data.find((producto) => producto.idProducto === parseInt(idProducto));
      });
    },

};
</script>


<style lang="scss" scoped>

</style>