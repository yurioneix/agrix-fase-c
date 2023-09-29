package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.PersonResponseDto;
import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller da classe Person.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Rota POST /persons.
   */
  @PostMapping()
  public ResponseEntity<PersonResponseDto> createPerson(
      @RequestBody Person person
  ) {
    Person newPerson = personService.create(person);

    PersonResponseDto personResponseDto = new PersonResponseDto(
        newPerson.getId(),
        newPerson.getUsername(),
        newPerson.getRole()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(personResponseDto);
  }
}
