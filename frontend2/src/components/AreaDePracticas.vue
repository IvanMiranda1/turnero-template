<template>
  <div class="section-container">
    <div class="section-titulo">
      <h3>Áreas de Prácticas</h3>
    </div>
    <div class="section-imagen-container">
      <div class="section-content">
        <div class="card">
          <div class="inicial-area">
            <span>F</span>
          </div>
          <div class="titulo-area">Derecho Familiar</div>
          <div class="content">
            <span>Divorcio</span>
            <span>Cuota alimentaria</span>
            <span>Violencia familiar</span>
            <span>Filiación</span>
          </div>
          <div class="card-button">
            <span><button>Ver más</button></span>
          </div>
        </div>

        <div class="card">
          <div class="inicial-area">
            <span>L</span>
          </div>
          <div class="titulo-area">Derecho Laboral</div>
          <div class="content">
            <span>Despidos</span>
            <span>Accidentes</span>
            <span>Diferencias salariales</span>
            <span>Suspensiones</span>
            <span>Inasistencias Justificadas</span>
            <span>Cálculo de indemnización</span>
            <span>Conciliaciones</span>
          </div>
          <div class="card-button">
            <span><button>Ver más</button></span>
          </div>
        </div>

        <div class="card">
          <div class="inicial-area">
            <span>S</span>
          </div>
          <div class="titulo-area">Sucesiones</div>
          <div class="content">
            <span>Ab - intestato</span>
            <span>Testamentaria</span>
            <span>Partición hereditaria</span>
            <span>Declaratorias de herederos</span>
          </div>
          <div class="card-button">
            <span><button>Ver más</button></span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
</script>

<style scoped>
.section-container {
  background-color: #00305b;
  display: flex;
  flex-direction: column;
}
.section-titulo {
  margin: 0 auto;
  font-size: 56px;
  font-family: 'Noticia Text';
  text-align: center;
}
.section-imagen-container {
  background: url('./penal.png') no-repeat center center;
  background-size: cover;
  padding: 80px;
}
.section-content {
  display: flex;
  justify-content: center;
  gap: 40px;
  flex-wrap: wrap;
}
.section-content .card {
  color: #00305b;
  min-width: 260px;
  max-width: 320px;
  min-height: 400px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  background-color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  justify-content: space-between;
  padding: 30px 20px;
  flex: 1;
}
.section-content .card .inicial-area {
  background-color: #00305b;
  color: #fff;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 36px;
  font-weight: bold;
  margin-top: -60px;
}
.section-content .card .titulo-area {
  font-size: 22px;
  font-weight: bold;
  margin: 10px 0 20px 0;
}
.section-content .card .content {
  display: flex;
  flex-direction: column;
  gap: 10px;
  flex-grow: 1;
  margin-bottom: 30px;
}
.card-button button {
  background-color: #00305b;
  color: #fff;
  border: none;
  padding: 8px 16px;
  cursor: pointer;
  font-size: 16px;
  border-radius: 4px;
  width: 150px;
}
.card-button button:hover {
  background-color: #0056a3;
}

/* RESPONSIVE */
@media (max-width: 1024px) {
  .section-titulo {
    font-size: 42px;
    line-height: normal;
  }
  .section-imagen-container {
    padding: 40px;
    background-position: center top;
  }
  .section-content {
    gap: 20px;
  }
}
@media (max-width: 768px) {
  .section-titulo {
    font-size: 32px;
  }
  .section-imagen-container {
    padding: 30px 15px;
    background-position: center top 50px;
  }
  .section-content {
    flex-direction: column;
    align-items: center;
  }
  .section-content .card {
    max-width: 90%;
    margin: 0 auto;
  }
  .section-content .card .inicial-area {
    margin-top: -10px;
  }
}
@media (max-width: 480px) {
  .section-titulo {
    font-size: 26px;
  }
  .section-imagen-container {
    padding: 20px 10px;
    background-position: center top 100px; /* desplaza más la imagen hacia abajo */
  }
  .section-content .card {
    padding: 20px 15px;
    min-height: auto;
  }
  .section-content .card .inicial-area {
    width: 60px;
    height: 60px;
    font-size: 28px;
    margin-top: -10px;
  }
  .section-content .card .titulo-area {
    font-size: 20px;
  }
  .card-button button {
    width: 120px;
    font-size: 14px;
  }
}
</style>
