package com.redis.om.skeleton.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Document
public class Person {
  // Id Field, also indexed
  @Id
  @Indexed
  public String id; 

  // Indexed for exact text matching
  @Indexed @NonNull public String firstName;
  @Indexed @NonNull public String lastName;

  //Indexed for numeric matches
  @Indexed @NonNull public Integer age;

  //Indexed for Full Text matches
  @Searchable @NonNull public String personalStatement;

  //Indexed for Geo Filtering
  @Indexed @NonNull public Point homeLoc;
  
  // Nest indexed object
  @NonNull
  @Indexed
  private Address address;
}
