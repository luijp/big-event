package com.luijp.mapper;

import com.luijp.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT INTO category(category_name, category_alias, create_user, create_time, update_time)" +
            " VALUES(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    @Select("SELECT * FROM category WHERE create_user = #{userId}")
    List<Category> list(Integer userId);

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(Integer id);

    @Update("UPDATE category SET category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = #{updateTime} WHERE id = #{id}")
    void update(Category category);

}
