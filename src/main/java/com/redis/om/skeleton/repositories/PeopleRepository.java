package com.redis.om.skeleton.repositories;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.redis.om.skeleton.models.Person;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface PeopleRepository extends RedisDocumentRepository<Person, String> {
  // Find people by age range
  Iterable<Person> findByAgeBetween(int minAge, int maxAge);
  
  // Draws a circular geofilter around a spot and returns all people in that radius
  Iterable<Person> findByHomeLoc(Point point, Distance distance);
  
  // Find people by their first and last name
  Iterable<Person> findByFirstNameAndLastName(String firstName, String lastName);
  
  // Performs full text search on a person's personal Statement
  Iterable<Person> searchByPersonalStatement(String text);
}