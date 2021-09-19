package com.dashsports.backend.dashsports.controller;

import com.dashsports.backend.dashsports.entity.Courses;
import com.dashsports.backend.dashsports.entity.Users;
import com.dashsports.backend.dashsports.repository.CoursesRepository;
import com.dashsports.backend.dashsports.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/courses")
public class CoursesController {

    @Autowired
    private CoursesRepository repository;

    //    get all
    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<Courses> getAll() {
        return repository.findAll ();
    }

    //    get one
    @GetMapping(value = "/{id}")
    public ResponseEntity<Courses> findById(@PathVariable("id") Integer id) {
        Optional<Courses> event = repository.findById ( id );
        return event.map ( eventResult
                -> new ResponseEntity<> ( eventResult, HttpStatus.OK ) ).orElseGet ( ()
                -> new ResponseEntity<> ( HttpStatus.NOT_FOUND ) );
    }

    //    create
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Courses[] x) {
        for (Courses i : x) {
            repository.save ( i );
        }
        return new ResponseEntity<> ( HttpStatus.CREATED );
    }


    //    delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        repository.deleteById ( id );
        return new ResponseEntity<> ( HttpStatus.OK );
    }


}
