package com.capstonegroup2.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.capstonegroup2.backend.models.RoleName;
import com.capstonegroup2.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(RoleName roleName);
    List<Role> findAll();
}
