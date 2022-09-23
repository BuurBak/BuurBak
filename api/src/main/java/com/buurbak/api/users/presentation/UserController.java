package com.buurbak.api.users.presentation;

import com.buurbak.api.users.application.CustomerService;
import com.buurbak.api.users.application.PersonService;
import com.buurbak.api.users.domain.Customer;
import com.buurbak.api.users.domain.Person;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    private final PersonService personService;
    private final CustomerService customerService;

    @GetMapping
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @PostMapping
    public void registerNewCustomer(@Valid @RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

//    @DeleteMapping(path = "{personId}")
//    public void deleteUser(@PathVariable("personId") UUID id) {
//        personService.deletePerson(id);
//    }

//    @PutMapping(path = "{personId}")
//    public Person patchUser(
//            @PathVariable("personId") UUID personId,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String email
//            ) {
//        return personService.patchPerson(personId, name, email);
//    }

}
