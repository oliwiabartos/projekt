package com.dashsports.backend.dashsports.controller;

import com.dashsports.backend.dashsports.entity.UserCourse;
import com.dashsports.backend.dashsports.entity.Users;
import com.dashsports.backend.dashsports.repository.UsersCourseRepository;
import com.dashsports.backend.dashsports.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/userCourse")
public class UserCourseController {

    @Autowired
    private UsersCourseRepository repository;

    //    get all
    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<UserCourse> getAll() {
        return repository.findAll ();
    }

    //    get one
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserCourse> findById(@PathVariable("id") Integer id) {
        Optional<UserCourse> event = repository.findById ( id );
        return event.map ( eventResult
                -> new ResponseEntity<> ( eventResult, HttpStatus.OK ) ).orElseGet ( ()
                -> new ResponseEntity<> ( HttpStatus.NOT_FOUND ) );
    }

    //    create
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody UserCourse[] x) {
        for (UserCourse i : x) {
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
