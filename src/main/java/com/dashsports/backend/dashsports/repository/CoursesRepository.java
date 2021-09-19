package com.dashsports.backend.dashsports.repository;

import com.dashsports.backend.dashsports.entity.Addresses;
import com.dashsports.backend.dashsports.entity.Courses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursesRepository extends CrudRepository<Courses, Integer> {


}
