package com.Learnit.service;

import com.Learnit.model.Person;

import java.util.ArrayList;

public class LoginService {

    private PersonService personService;

    public LoginService(PersonService personService) {
        this.personService = personService;
    }

    public Person loginUser(String userLogin, String passLogin){
        ArrayList<Person> registeredUsers =  personService.getPersons();

       for(Person p: registeredUsers){
           if (p.getUsuario().equals(userLogin) && p.getPassword().equals(passLogin)){
               return p;
           }
       }

        return null;
    }
}
