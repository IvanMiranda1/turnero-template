<template>
  <header>
    <!-- Logo -->
    <div class="logo-container">
      <div class="img-container">
        <img class="logo" src="/logo.png" alt="Logo" />
      </div>
      <div class="label-logo-container">
        ESTUDIO JURIDICO MARTIN & ASOCIADOS
      </div>
    </div>


    <!-- Navegación -->
    <nav :class="{ open: isOpen }">
      <ul class="nav-links">
        <li><a href="#home">INICIO</a></li>
        <li><a href="#about">SOBRE NOSOTROS</a></li>
        <li><a href="#contact">CONTACTOS</a></li>
        <li><a href="#contact">TURNOS</a></li>
      </ul>
    </nav>
    
    <!-- Botón menú hamburguesa -->
    <button class="menu-toggle" @click="toggleMenu">
      ☰
    </button>
  </header>
</template>

<script lang="ts" setup>
import { ref } from "vue";

const isOpen = ref(false);
const toggleMenu = () => {
  isOpen.value = !isOpen.value;
};
</script>

<style scoped>
/* HEADER */
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  max-width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 80px;
  padding: 0 10px;
  background-color: #ffffff;
  z-index: 1000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* LOGO */
.logo-container {
  display: flex;
  align-items: center;
}
.logo-container .img-container {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}
.logo-container .img-container img {
  width: 100%;
  height: 100%;
}
.label-logo-container {
  font-size: 20px;
  letter-spacing: 0.5px;
  line-height: 22px;
  color: #00305b;
  font-family: "Noticia Text";
  white-space: nowrap;
}

/* NAVBAR */
nav {
  transition: max-height 0.3s ease;
}
nav ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}
.nav-links li {
  padding: 0 15px;
}
.nav-links a {
  color: rgb(15, 76, 133);
  text-decoration: none;
  font-size: 16px;
}

/* BOTÓN MENU HAMBURGUESA */
.menu-toggle {
  display: none;
  background: none;
  border: none;
  font-size: 2rem;
  cursor: pointer;
  color: #00305b;
  z-index: 1100;
}

/* RESPONSIVE */
@media (max-width: 768px) {
  .menu-toggle {
    display: block;
    align-self: flex-end;
  }
  .label-logo-container {
    display: none; /* ocultar el texto del logo */
  }
  nav {
    overflow: hidden;
    max-height: 0;
    width: 100%;
    background: #ffffff;
  }
  nav.open {
    max-height:500px;
  }
  nav ul {
    flex-direction: column;
    align-items: center;
    padding: 10px 0;
    margin-top: 80px;

  }
  .nav-links li {
    padding: 10px 0;
  }
}

</style>
