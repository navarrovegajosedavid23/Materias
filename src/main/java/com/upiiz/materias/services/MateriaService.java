package com.upiiz.materias.services;
import com.upiiz.materias.models.Materia;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MateriaService {
    private List<Materia> materias = new ArrayList<>();
    private AtomicLong contador = new AtomicLong();

    public List<Materia> obtenerTodas() { return materias; }

    public void guardar(Materia materia) {
        if (materia.getId() == null) {
            materia.setId(contador.incrementAndGet());
            materias.add(materia);
        } else {
            actualizar(materia);
        }
    }

    public Materia obtenerPorId(Long id) {
        return materias.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public void actualizar(Materia materiaActualizada) {
        for (int i = 0; i < materias.size(); i++) {
            if (materias.get(i).getId().equals(materiaActualizada.getId())) {
                materias.set(i, materiaActualizada);
                return;
            }
        }
    }

    public void eliminar(Long id) {
        materias.removeIf(m -> m.getId().equals(id));
    }
}