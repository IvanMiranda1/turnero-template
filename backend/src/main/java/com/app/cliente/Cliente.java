package com.app.cliente;

import java.util.List;
import java.util.UUID;

import com.app.turno.Turno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String dni;

    //relacion con turnos
    //Sirve para obtener los turnos de un cliente
    //y para hacer consultas JPQL que involucren turnos desde cliente sin necesidad consultas tan largas
    // mas ordenado
    @OneToMany(mappedBy = "cliente")
    private List<Turno> turnos;
    /*** mappedBy
     * Así JPA sabe que la relación está mapeada por el campo cliente en Turno.
     * Si no ponés mappedBy, JPA crea una tabla intermedia innecesaria.
     * */

}
