package com.stonie.person;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Select("SELECT * FROM `person`")
    List<PersonEntity> list();

    @Select("SELECT * FROM `person` WHERE `id` = #{id}")
    PersonEntity detail(@Param("id") Integer id);

    @Insert("INSERT INTO `person` (`name`, `age`) VALUES (#{name}, #{age})")
    int insert(PersonEntity person);

    @Delete("DELETE FROM `person` WHERE `id` = #{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE `person` SET `name` = #{name}, `age` = #{age} WHERE `id` = #{id}")
    int update(PersonEntity person);
}
