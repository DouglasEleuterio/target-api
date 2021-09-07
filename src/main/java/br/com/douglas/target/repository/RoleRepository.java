package br.com.douglas.target.repository;

import br.com.douglas.target.models.ERole;
import br.com.douglas.target.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
