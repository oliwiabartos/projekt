package com.dashsports.backend.dashsports.controller;

import com.dashsports.backend.dashsports.entity.Addresses;
import com.dashsports.backend.dashsports.entity.Users;
import com.dashsports.backend.dashsports.repository.AddressesRepository;
import com.dashsports.backend.dashsports.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/users")
public class UsersController {

    @Autowired
    private UsersRepository repository;

    //    get all
    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<Users> getAll() {
        return repository.findAll ();
    }

    //    get one
    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> findById(@PathVariable("id") Integer id) {
        Optional<Users> event = repository.findById ( id );
        return event.map ( eventResult
                -> new ResponseEntity<> ( eventResult, HttpStatus.OK ) ).orElseGet ( ()
                -> new ResponseEntity<> ( HttpStatus.NOT_FOUND ) );
    }

    //    create
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Users[] x) {
        for (Users i : x) {
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
