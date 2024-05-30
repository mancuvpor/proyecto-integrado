package manuel.proyectointegrado.repositories;

import manuel.proyectointegrado.models.Evento;
import manuel.proyectointegrado.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("SELECT e FROM Evento e WHERE e.usuario = :usuario")

    List<Evento> findByUsuario(Usuario usuario);
}
