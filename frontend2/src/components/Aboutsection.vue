<template>
  <div class="section-container">
    <div class="section-text">
      <h3>SOBRE EL ESTUDIO</h3>
      <p>
        En Estudio Jurídico {NOMBRE}, nos enfocamos en brindar soluciones legales integrales y personalizadas a
        nuestros clientes. Nuestro equipo de abogados asociados, con años de experiencia y especialización en
        diversas áreas del derecho, trabajan en conjunto para ofrecer asesoramiento y representación de alta
        calidad
      </p>
      <p>
        Nuestro estudio jurídico cuenta con profesionales especializados en derecho civil, familia, laboral,
        penal. Nos encontramos comprometidos con la excelencia y la innovación.
      </p>
      <p>
        Valoramos la confianza y la lealtad de nuestros clientes y procuramos construir relaciones a largo
        plazo. Nuestro objetivo es brindar soluciones efectivas y eficientes que se adapten a las necesidades
        específicas de cada cliente.
      </p>
    </div>
  </div>
</template>

<script lang="ts" setup>
</script>

<style scoped>
.section-container {
  background-color: #00305b;
  min-height: 50vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px 20px;
  box-sizing: border-box;
}

.section-text {
  color: #ffffff;
  max-width: 800px;
  text-align: center;
  line-height: 1.6;
  font-size: 1rem;
  word-wrap: break-word;
  overflow-wrap: break-word;
}

/* responsive */
@media (max-width: 1024px) {
  .section-text {
    font-size: 0.95rem;
    padding: 0 10px;
  }
}

@media (max-width: 768px) {
  .section-container {
    padding: 30px 15px;
  }
  .section-text {
    font-size: 0.9rem;
    line-height: 1.5;
    text-align: left;
  }
}

@media (max-width: 480px) {
  .section-container {
    padding: 20px 10px;
  }
  .section-text {
    font-size: 0.85rem;
    line-height: 1.4;
  }
}
</style>
