package com.tareas.crudtareas.Controller;

import com.tareas.crudtareas.Model.Tarea;
import com.tareas.crudtareas.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    @Autowired
    private TareaRepository tareaRepository;

    @GetMapping
    public List<Tarea> listarTareas() { return tareaRepository.findAll(); }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) { return tareaRepository.save(tarea); }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id) {
        return tareaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada) {
        return tareaRepository.findById(id).map(tarea -> {
            tarea.setTitulo(tareaActualizada.getTitulo());
            tarea.setCompletado(tareaActualizada.isCompletado());
            return ResponseEntity.ok(tareaRepository.save(tarea));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
