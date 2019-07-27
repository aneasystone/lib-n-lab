package com.stonie.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<PersonEntity> list() {
        return personMapper.findAll();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public PersonEntity detail(@PathVariable Long id) {
        return personMapper.findById(id).orElse(null);
    }

    @RequestMapping(value = "/detailByName", method = RequestMethod.GET)
    public PersonEntity detail(String name) {
        return personMapper.findByName(name).orElse(null);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public PersonEntity insert(@RequestBody PersonEntity person) {
        return personMapper.save(person);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public int delete(@PathVariable Long id) {
        personMapper.deleteById(id);
        return 1;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PersonEntity update(@RequestBody PersonEntity person) {
        return personMapper.save(person);
    }
}
