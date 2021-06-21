package com.bambrow.redis.mapper;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class StudentProvider implements ProviderMethodResolver {

    private final static String TABLE_NAME = "student";

    // StudentMapper.selectByNameLike
    public static String selectByNameLike(String name) {
        SQL sql = new SQL().SELECT("*").FROM(TABLE_NAME);
        if (name != null && name.length() > 0) {
            sql.WHERE("name LIKE CONCAT('%', #{name}, '%')");
        }
        return sql.toString();
    }

}
