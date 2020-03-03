package com.example.api_hw_week7.repository;

import com.example.api_hw_week7.model.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends CrudRepository<Query,Long> {

}
