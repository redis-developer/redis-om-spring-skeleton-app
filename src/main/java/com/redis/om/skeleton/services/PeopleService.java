package com.redis.om.skeleton.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.redis.om.skeleton.models.Person;
import com.redis.om.skeleton.models.Person$;
import com.redis.om.spring.search.stream.EntityStream;

import redis.clients.jedis.search.aggr.SortedField.SortOrder;

@Service
public class PeopleService {
  @Autowired
  EntityStream entityStream;

  // Find people by age range
  public Iterable<Person> findByAgeBetween(int minAge, int maxAge) {
    return entityStream //
        .of(Person.class) //
        .filter(Person$.AGE.between(minAge, maxAge)) //
        .sorted(Person$.AGE, SortOrder.ASC) //
        .collect(Collectors.toList());
  }

  // Find people by their first and last name
  public Iterable<Person> findByFirstNameAndLastName(String firstName, String lastName) {
    return entityStream //
        .of(Person.class) //
        .filter(Person$.FIRST_NAME.eq(firstName)) //
        .filter(Person$.LAST_NAME.eq(lastName)) //
        .collect(Collectors.toList());
  }

  public Iterable<Person> findByHomeLoc(//
      Point point, Distance distance) {
    return entityStream //
        .of(Person.class) //
        .filter(Person$.HOME_LOC.near(point, distance)) //
        .collect(Collectors.toList());
  }

  // Performs full text search on a person's personal Statement
  public Iterable<Person> searchByPersonalStatement(String text) {
    return entityStream //
        .of(Person.class) //
        .filter(Person$.PERSONAL_STATEMENT.eq(text)) //
        .collect(Collectors.toList());
  }

}
