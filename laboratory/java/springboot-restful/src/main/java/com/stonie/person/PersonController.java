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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PersonEntity> list() {
        return personMapper.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PersonEntity detail(@PathVariable Integer id) {
        return personMapper.detail(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public int insert(@RequestBody PersonEntity person) {
        return personMapper.insert(person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable Integer id) {
        return personMapper.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public int update(@RequestBody PersonEntity person) {
        return personMapper.update(person);
    }
}
