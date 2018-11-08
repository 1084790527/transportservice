package com.example.transportservice.dao;

import com.example.transportservice.bean.car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;
import java.util.List;

@Mapper
public interface carDao {

    @Select("select * from car")
    Collection<car> getAll();

    @Select("select * from car where carid=#{carid}")
    car getmoneybycarid(@Param("carid") int carid);

    @Update("UPDATE car SET money = #{money} WHERE carid = #{carid}")
    void setmoney(@Param("carid") int carid,@Param("money") int money);

    @Select("select * from car")
    List<car> getlist();
}
