package com.buurbak.api.users.application;

import com.buurbak.api.users.data.PersonRepository;
import com.buurbak.api.users.domain.Huurder;
import com.buurbak.api.users.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository<Person> personRepository;

    @Autowired
    public PersonService(PersonRepository<Person> personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void addNewPerson(Huurder person) {
        Optional<Person> personByEmail = personRepository.findPersonByEmail(person.getEmail());

        if (personByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        personRepository.save(person);
    }

    public void deletePerson(UUID personId) {
        if (!personRepository.existsById(personId)) {
            throw new IllegalStateException("Student does not exist");
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public Person patchPerson(UUID personId, String name, String email) throws IllegalStateException {
        Person person = personRepository.findById(personId).orElseThrow(() -> new IllegalStateException("student with id " + personId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(person.getName(), name)) {
            person.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
            // Check if email exists
            Optional<Person> optionalStudent = personRepository.findPersonByEmail(email);

            if (optionalStudent.isPresent()) {
                throw new IllegalStateException("email already taken");
            }

            person.setEmail(email);
        }

        return person;
    }
}
