package com.stonie.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonMapper extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByName(String name);
}
