package com.portfolioweb.bkd.Security.Repository;

import com.portfolioweb.bkd.Security.Entity.Rol;
import com.portfolioweb.bkd.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    

}
