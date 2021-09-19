package com.dashsports.backend.dashsports.repository;

import com.dashsports.backend.dashsports.entity.UserCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersCourseRepository extends CrudRepository<UserCourse, Integer> {


}
