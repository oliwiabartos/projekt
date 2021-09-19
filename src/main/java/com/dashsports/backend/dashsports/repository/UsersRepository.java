package com.dashsports.backend.dashsports.repository;

import com.dashsports.backend.dashsports.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<Users, Integer> {


}
