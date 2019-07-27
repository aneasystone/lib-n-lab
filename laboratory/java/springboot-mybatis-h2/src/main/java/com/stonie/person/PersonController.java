package com.stonie.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<PersonEntity> list() {
        return personMapper.list();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public PersonEntity detail(@PathVariable Integer id) {
        return personMapper.detail(id);
    }
}
