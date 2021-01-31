package com.warframepda.www.repositories;

import com.warframepda.www.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String name);
}
