package com.redis.om.skeleton.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.om.skeleton.models.Person;
import com.redis.om.skeleton.repositories.PeopleRepository;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleControllerV1 {

  @Autowired
  PeopleRepository repo;

  @GetMapping("age_between")
  Iterable<Person> byAgeBetween( //
      @RequestParam("min") int min, //
      @RequestParam("max") int max) {
    return repo.findByAgeBetween(min, max);
  }

  @GetMapping("homeloc")
  Iterable<Person> byHomeLoc(//
      @RequestParam("lat") double lat, //
      @RequestParam("lon") double lon, //
      @RequestParam("d") double distance) {
    return repo.findByHomeLoc(new Point(lon, lat), new Distance(distance, Metrics.MILES));
  }

  @GetMapping("name")
  Iterable<Person> byFirstNameAndLastName(@RequestParam("first") String firstName, //
      @RequestParam("last") String lastName) {
    return repo.findByFirstNameAndLastName(firstName, lastName);
  }

  @GetMapping("statement")
  Iterable<Person> byPersonalStatement(@PathVariable("q") String q) {
    return repo.searchByPersonalStatement(q);
  }

}
