package com.buurbak.api.users.presentation;

import com.buurbak.api.users.application.PersonService;
import com.buurbak.api.users.domain.Huurder;
import com.buurbak.api.users.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    private final PersonService personService;

    @Autowired
    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getUsers() {
        return personService.getPersons();
    }

    @PostMapping
    public void registerNewUser(@RequestBody Huurder person) {
        personService.addNewPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void deleteUser(@PathVariable("personId") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "{personId}")
    public Person patchUser(
            @PathVariable("personId") UUID personId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
            ) {
        return personService.patchPerson(personId, name, email);
    }
}
