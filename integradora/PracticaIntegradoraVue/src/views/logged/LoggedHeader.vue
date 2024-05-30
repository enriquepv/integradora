<template>
    <header >
        <div id = "variable">
            <h1 id="nombreEmpresa">WODASPORTS</h1>
            <p id="textoDeBienvenida">BIENVENIDO</p>
            <div ><RouterLink to = "/carrito" class = "menu-link" ><img id="carrito" src="../../assets/carritoSinFondo.png"></RouterLink></div>
            <div id="logout" class="links"><RouterLink to = "/" class = "menu-link" id="menu0">Cerrar sesión</RouterLink></div>
        </div>
        <div class="botonesVue">
            <div class="links"><RouterLink to = "/loggedhome" class = "menu-link" id="menu1">Inicio</RouterLink></div>
            <div class="links"><RouterLink to = "/loggedabout" class = "menu-link" id="menu2">Sobre nosotros</RouterLink></div>
            <div class="links"><RouterLink to = "/loggedcatalogo" class = "menu-link" id="menu3">Catalogo</RouterLink></div>
            <div class="links"><RouterLink to = "/loggedtiendas" class = "menu-link" id="menu4">¿Dónde estamos?</RouterLink></div>
    
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

    #textoDeBienvenida{
        text-transform: uppercase;
        font-family: "Bebas Neue", sans-serif;
        
        font-size: 30px;
         
       
    }
    
    #carrito{
        width: 125px;
    }


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
    flex-flow: row  ;
    align-items: flex-end;
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
    
    #logout{
        color:white;
        text-decoration: none;
        background-color: red; 
         
        
    }
    #menu0{
        color: white;
    }
    
    </style>