<template>
    <LoggedBasicLayout>
        <div class="tiendas">
            <div>
                <h2>WODASPORTS la Castellana</h2>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1189.3310198786203!2d-3.6910070299696542!3d40.438721970066766!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd4228952eae116f%3A0xca9c69d3176fa562!2sCastellana%2C%20Madrid!5e0!3m2!1ses!2ses!4v1684163953426!5m2!1ses!2ses" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
            <div>
                <h2>WODASPORTS Tres Cantos</h2>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1981.31499090267!2d-3.715833521029732!3d40.61653126842499!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd43d51c39ab514b%3A0x1b8aecf9235eedd3!2s28760%20Tres%20Cantos%2C%20Madrid!5e0!3m2!1ses!2ses!4v1684163833920!5m2!1ses!2ses" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
            <div>
                <h2>WODASPORTS H2O</h2>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3041.2413665760187!2d-3.5284010846065006!3d40.336991379374446!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd423c75f4c8f879%3A0x27022fe98b57c59f!2sH2O%20Centro%20Comercial!5e0!3m2!1ses!2ses!4v1684163670561!5m2!1ses!2ses" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
            <div>
                <h2>WODASPORTS Alcorcón</h2>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d521.4895859180214!2d-3.828571479095076!3d40.34666182877152!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd418eb59615f603%3A0x81ece93d66c81fd1!2zQy4gQ29sw7NuLCAzMSwgMjg5MjEgQWxjb3Jjw7NuLCBNYWRyaWQ!5e0!3m2!1ses!2ses!4v1684164001270!5m2!1ses!2ses" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </div>
    </LoggedBasicLayout>
</template>

<script setup>
import LoggedBasicLayout from '../../layouts/LoggedBasicLayout.vue';


</script>

<style scoped>
     .tiendas{
        margin:50px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
        background-color:  #6495ed;
        border-radius: 20px;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        height: auto;
        padding-bottom: 20px;
    }
    iframe{
        width: 30em;
        height: 20em;
        border-radius: 20px;
    }
</style>