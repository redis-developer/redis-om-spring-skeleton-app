package com.redis.om.skeleton.models;

import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Address {

  @NonNull
  @Indexed
  private String houseNumber;

  @NonNull
  @Searchable(nostem = true)
  private String street;

  @NonNull
  @Indexed
  private String city;
  
  @NonNull
  @Indexed
  private String state;

  @NonNull
  @Indexed
  private String postalCode;
  
  @NonNull
  @Indexed
  private String country;
}
