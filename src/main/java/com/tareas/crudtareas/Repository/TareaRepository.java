package com.tareas.crudtareas.Repository;

import com.tareas.crudtareas.Model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
