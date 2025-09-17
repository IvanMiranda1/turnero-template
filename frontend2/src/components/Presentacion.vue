<template>
  <div id="about" class="section-container">
    <section class="presentacion-section">
      <div class="presentacion">
        <div class="presentacion-text">
          <h1>ESTUDIO JURIDICO</h1>
          <h2>Estudio jurídico especializado en derecho de familia, civil, laboral y penal.</h2>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts" setup>
</script>

<style scoped>
.section-container {
  background: url('./pilar.gif') no-repeat center center;
  background-size: cover;
  height: 100vh;
  display: flex;
  justify-content: flex-start;
}

/* panel con fondo semitransparente */
.presentacion-section {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  width: 40%;
  background-color: rgba(4, 21, 37, 0.5);
   box-shadow: 0 -10px 20px rgba(255, 255, 255, 0.5); 
  border-left: 5px solid #ffffff;
  padding: 20px;
}

/* texto */
.presentacion-text h1 {
  font-size: 3rem;
  color: #fff;
  font-weight: bold;
  margin: 0 0 10px 0;
}
.presentacion-text h2 {
  font-size: 1.2rem;
  color: #f0f0f0;
  font-weight: normal;
  line-height: 1.5;
  margin: 0;
}

/* responsive */
@media (max-width: 1024px) {
  .presentacion-section {
    width: 60%;
  }
  .presentacion-text h1 {
    font-size: 2.5rem;
  }
  .presentacion-text h2 {
    font-size: 1rem;
  }
}

@media (max-width: 768px) {
  .section-container {
    justify-content: center;
  }
  .presentacion-section {
    width: 100%;
    height: auto;
    min-height: 50vh;
    align-items: center;
    text-align: center;
    border-left: none;
    border-top: 5px solid #ffffff;
  }
  .presentacion-text h1 {
    font-size: 2rem;
  }
  .presentacion-text h2 {
    font-size: 0.95rem;
  }
}

@media (max-width: 480px) {
  .presentacion-section {
    padding: 15px;
  }
  .presentacion-text h1 {
    font-size: 1.5rem;
  }
  .presentacion-text h2 {
    font-size: 0.85rem;
  }
}
</style>
