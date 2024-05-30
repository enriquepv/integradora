<template>
<header >
    <div id = "variable">
        <!-- <img alt="Gunfire logo" class="logo" src="@/assets/gunfire_logo.jpg" height="125" /> -->
        <div>
            <h1 id="nombreEmpresa">WODASPORTS</h1>
        </div>
        <div class="botones">
            <div id="register-button" class="links"><a class="menu-link" href="http://localhost:8080/register/registro_paso1">register</a></div>
            
            <div id="login-button" class="links"><a  class="menu-link" href="http://localhost:8080/login/login_paso1">log in</a></div>
        </div>
    </div>
    <div class="botonesVue">
        <div class="links"><RouterLink to = "/" class = "menu-link" id="menu1">Inicio</RouterLink></div>
        <div class="links"><RouterLink to = "/about" class = "menu-link" id="menu2">Sobre nosotros</RouterLink></div>
        <div class="links"><RouterLink to = "/catalogo" class = "menu-link" id="menu3">Catalogo</RouterLink></div>
        <div class="links"><RouterLink to = "/tiendas" class = "menu-link" id="menu4">¿Dónde estamos?</RouterLink></div>

    </div>
</header>
</template>

<script>
    import {RouterLink} from 'vue-router';

    export default {
        name: "HeaderVue",
    };
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Jaro:opsz@6..72&display=swap');

#nombreEmpresa{
    color: white;
    font-size: 70px;
    font-family: "Bebas Neue", sans-serif;

}
#login-button{
    background-color: rgb(184, 246, 96);
}
#register-button{
    background-color: rgb(184, 246, 96);
}

header{
  background-color: #6495ed;
  color: white;
  flex-direction: column;
  display: flex;  
  
}

#variable{
    display: flex;
    align-items: center;
    justify-content: space-around;
}
.botones{
    display:flex;
    flex-flow: row wrap;
    justify-content:flex-end ;
    background-color: #6495ed;
    height: auto;
    width: 70%;
    padding-bottom: 20px;
    
}
.botonesVue{
    display:flex;
    flex-flow: row wrap;
    justify-content:flex-end ;
    background-color: #6495ed;
    height: auto;
    width: 100%;
    padding-bottom: 20px;
}





.links{
    display: flex;
    height: 50px;
    background-color: white;
    border-radius: 20px;
    align-items: center;
}
.menu-link{
    color:#6495ed;
    text-decoration: none;
    text-transform: uppercase;
    /* font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; */
    font-weight:lighter;
    font-size: 25px;
    font-family: "Bebas Neue", sans-serif;
     
    /* text-shadow: 1px 1px 3px black; */
     
}
.links:hover{
  background-color: rgb(235, 225, 225);
}

/* 
#menu1{
    color:white;
    text-decoration: none;
    background-color: orange; 
    width: 100px;  
    
}
#menu2{
    color:white;
    text-decoration: none;
    background-color: orange;
    
    
}
#menu3{
    color:white;
    text-decoration: none;
    background-color: orange;
    
}

#menu4{
    color:white;
    text-decoration: none;
    background-color: orange;
    
} */
</style>