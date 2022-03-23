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
import com.redis.om.skeleton.services.PeopleService;

@RestController
@RequestMapping("/api/v2/people")
public class PeopleControllerV2 {

  @Autowired
  PeopleService service;

  @GetMapping("age_between")
  Iterable<Person> byAgeBetween(
      @RequestParam("min") int min, //
      @RequestParam("max") int max) {
    return service.findByAgeBetween(min, max);
  }

  @GetMapping("name")
  Iterable<Person> byFirstNameAndLastName(
      @RequestParam("first") String firstName, //
      @RequestParam("last") String lastName) {
    return service.findByFirstNameAndLastName(firstName, lastName);
  }

  @GetMapping("homeloc")
  Iterable<Person> byHomeLoc(//
      @RequestParam("lat") double lat, //
      @RequestParam("lon") double lon, //
      @RequestParam("d") double distance) {
    return service.findByHomeLoc(new Point(lon, lat), new Distance(distance, Metrics.MILES));
  }

  @GetMapping("statement")
  Iterable<Person> byPersonalStatement(@PathVariable("q") String q) {
    return service.searchByPersonalStatement(q);
  }

}
