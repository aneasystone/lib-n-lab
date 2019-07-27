package com.stonie.person;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<PersonEntity> list();

    PersonEntity detail(@Param("id") Integer id);

    int insert(PersonEntity person);

    int delete(@Param("id") Integer id);

    int update(PersonEntity person);
}
