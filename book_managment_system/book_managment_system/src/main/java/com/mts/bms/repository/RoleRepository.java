package com.mts.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mts.bms.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
