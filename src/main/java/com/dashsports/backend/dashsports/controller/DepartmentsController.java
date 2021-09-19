package com.dashsports.backend.dashsports.controller;

import com.dashsports.backend.dashsports.entity.Departments;
import com.dashsports.backend.dashsports.entity.Users;
import com.dashsports.backend.dashsports.repository.DepartmentsRepository;
import com.dashsports.backend.dashsports.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentsRepository repository;

    @PersistenceContext
    private EntityManager entityManager;


//    https://dzone.com/articles/transaction-management-with-spring-and-mysql

    private final static int departmentId = 8; // id departamentu z bazy

    //    get all
    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<Departments> getAll() {
        return repository.findAll ();
    }

    //    get one
    @GetMapping(value = "/{id}")
    public ResponseEntity<Departments> findById(@PathVariable("id") Integer id) {
        Optional<Departments> event = repository.findById ( id );
        return event.map ( eventResult
                -> new ResponseEntity<> ( eventResult, HttpStatus.OK ) ).orElseGet ( ()
                -> new ResponseEntity<> ( HttpStatus.NOT_FOUND ) );
    }

    //    create
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Departments[] x) {
        for (Departments i : x) {
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

    @GetMapping(value = "/serializable")
    public @ResponseBody
    void Serializable() {
        T1S ();
    }

    @GetMapping(value = "/repeatableRead")
    public @ResponseBody
    void RepeatableRead () {
        T1RR ();
    }

    @GetMapping(value = "/readCommitted")
    public @ResponseBody
    void ReadCommitted() {
        T1RC ();
    }



    //  ------------  Serializable ------------

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void T1S() {
        Departments t = repository.findById ( departmentId ).get ();
        System.out.println ( "T1" + t.getDeptName () );
        t.setDeptName ( String.format ( "Name - %s", new Date ().getTime () ) );
        repository.save ( t );
        T2S ();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.SERIALIZABLE)
    public void T2S() {
        Departments t = repository.findById ( departmentId ).get ();
        System.out.println ( "T2" + t.getDeptName () );
    }


    //  ------------  Repeatable Read ------------
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void T1RR() {
        Departments t = repository.findById ( departmentId ).get ();
        System.out.print ( t.getDeptName () );
        T2RR ();
        entityManager.detach ( t );
        t = repository.findById ( departmentId ).get ();
        System.out.print ( t.getDeptName () );
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED)
    public void T2RR() {
        Departments t = repository.findById ( departmentId ).get ();
        t.setDeptName ( String.format ( "Name - %s", new Date ().getTime () ) );
    }

    //  ------------  Read Committed ------------

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void T1RC() {
        Departments t = repository.findById ( departmentId ).get ();
        System.out.print ( t.getDeptName () );
        T2RC ();
        entityManager.detach ( t );
        t = repository.findById ( departmentId ).get ();
        System.out.print ( t.getDeptName () );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED)
    public void T2RC() {
        Departments t = repository.findById ( departmentId ).get ();
        t.setDeptName ( String.format ( "Name - %s", new Date ().getTime () ) );
    }

}
