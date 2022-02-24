package com.redis.om.skeleton;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;

import com.redis.om.skeleton.models.Person;
import com.redis.om.skeleton.repositories.PeopleRepository;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "com.redis.om.skeleton.*")
public class SkeletonApplication {
  
  @Autowired
  PeopleRepository repo;

  @Bean
  CommandLineRunner loadTestData() {
    return args -> {
      repo.deleteAll();
      
      Person thor = Person.of("Chris", "Hemsworth", 38, "", new Point(-28.716667, 153.616667));
      Person ironman = Person.of("Robert", "Downey", 56, "", new Point(40.9190747,-72.5371874));
      Person blackWidow = Person.of("Scarlett", "Johansson", 37, "", new Point(40.7215259,-74.0129994));
      Person wandaMaximoff = Person.of("Elizabeth", "Olsen", 32, "", new Point(40.6976701,-74.2598641));
      Person gamora = Person.of("Zoe", "Saldana", 43, "", new Point(34.082615,-118.4345534));
      Person nickFury = Person.of("Samuel L.", "Jackson", 73, "", new Point(34.082615,-118.4345534));

      repo.saveAll(List.of(thor, ironman, blackWidow, wandaMaximoff, gamora, nickFury));
    };
  }

	public static void main(String[] args) {
		SpringApplication.run(SkeletonApplication.class, args);
	}

}
