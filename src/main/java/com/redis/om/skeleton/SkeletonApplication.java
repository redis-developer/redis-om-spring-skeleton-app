package com.redis.om.skeleton;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.redis.om.skeleton.models.Address;
import com.redis.om.skeleton.models.Person;
import com.redis.om.skeleton.repositories.PeopleRepository;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;

@SpringBootApplication
@EnableSwagger2
@EnableRedisDocumentRepositories(basePackages = "com.redis.om.skeleton.*")
public class SkeletonApplication {

  @Autowired
  PeopleRepository repo;

  @Bean
  CommandLineRunner loadTestData() {
    return args -> {
      repo.deleteAll();
      
      String thorSays = "The Rabbit Is Correct, And Clearly The Smartest One Among You.";
      String ironmanSays = "Doth mother know you weareth her drapes?";
      String blackWidowSays = "Hey, fellas. Either one of you know where the Smithsonian is? I'm here to pick up a fossil.";
      String wandaMaximoffSays = "You Guys Know I Can Move Things With My Mind, Right?";
      String gamoraSays = "I Am Going To Die Surrounded By The Biggest Idiots In The Galaxy.";
      String nickFurySays = "Sir, I’m Gonna Have To Ask You To Exit The Donut";
      
      // Serendipity, 248 Seven Mile Beach Rd, Broken Head NSW 2481, Australia
      Address thorsAddress = Address.of("248", "Seven Mile Beach Rd", "Broken Head", "NSW", "2481", "Australia");
      
      // 11 Commerce Dr, Riverhead, NY 11901
      Address ironmansAddress = Address.of("11", "Commerce Dr", "Riverhead", "NY",  "11901", "US");
      
      // 605 W 48th St, New York, NY 10019
      Address blackWidowAddress = Address.of("605", "48th St", "New York", "NY", "10019", "US");
      
      // 20 W 34th St, New York, NY 10001
      Address wandaMaximoffsAddress = Address.of("20", "W 34th St", "New York", "NY", "10001", "US");
      
      // 107 S Beverly Glen Blvd, Los Angeles, CA 90024
      Address gamorasAddress = Address.of("107", "S Beverly Glen Blvd", "Los Angeles", "CA", "90024", "US");
      
      // 11461 Sunset Blvd, Los Angeles, CA 90049
      Address nickFuryAddress = Address.of("11461", "Sunset Blvd", "Los Angeles", "CA", "90049", "US");

      Person thor = Person.of("Chris", "Hemsworth", 38, thorSays, new Point(153.616667, -28.716667), thorsAddress, Set.of("hammer", "biceps", "hair", "heart"));
      Person ironman = Person.of("Robert", "Downey", 56, ironmanSays, new Point(40.9190747, -72.5371874), ironmansAddress, Set.of("tech", "money", "one-liners", "intelligence", "resources"));
      Person blackWidow = Person.of("Scarlett", "Johansson", 37, blackWidowSays, new Point(40.7215259, -74.0129994), blackWidowAddress, Set.of("deception", "martial_arts"));
      Person wandaMaximoff = Person.of("Elizabeth", "Olsen", 32, wandaMaximoffSays, new Point(40.6976701, -74.2598641), wandaMaximoffsAddress, Set.of("magic", "loyalty"));
      Person gamora = Person.of("Zoe", "Saldana", 43, gamoraSays, new Point(34.082615, -118.4345534), gamorasAddress, Set.of("skills", "martial_arts"));
      Person nickFury = Person.of("Samuel L.", "Jackson", 73, nickFurySays, new Point(34.082615, -118.4345534), nickFuryAddress, Set.of("planning", "deception", "resources"));

      repo.saveAll(List.of(thor, ironman, blackWidow, wandaMaximoff, gamora, nickFury));
    };
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  public static void main(String[] args) {
    SpringApplication.run(SkeletonApplication.class, args);
  }

}
