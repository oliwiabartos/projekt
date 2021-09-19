package com.dashsports.backend.dashsports.repository;

import com.dashsports.backend.dashsports.entity.Addresses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressesRepository extends CrudRepository<Addresses, Integer> {

    @Query(value = "select * from test", nativeQuery = true)
    List<Addresses> transaction();

}
