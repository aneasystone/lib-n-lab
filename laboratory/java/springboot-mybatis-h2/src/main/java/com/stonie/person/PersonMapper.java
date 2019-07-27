package com.stonie.person;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Select("SELECT * FROM person")
    List<PersonEntity> list();

    @Select("SELECT * FROM person WHERE id = #{id}")
    PersonEntity detail(@Param("id") Integer id);
}
