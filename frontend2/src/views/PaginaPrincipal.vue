<template>
  <div class="main-container"> <!-- contiene toda la app -->
  <Navbar/>
  <Presentacion/>
  <AreaDePracticas/>
  <Aboutsection/>
  </div>
</template>

<script lang="ts" setup>
import Navbar from '@/components/Navbar.vue';
import Presentacion from '@/components/Presentacion.vue';
import Aboutsection from '@/components/Aboutsection.vue';
import AreaDePracticas from '@/components/AreaDePracticas.vue';
</script>

<style scoped>

</style>