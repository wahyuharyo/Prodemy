
package com.haryo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haryo.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
