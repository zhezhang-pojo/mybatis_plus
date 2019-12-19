package com.gk.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.mybatisplus.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @create 2019-12-18 12:32
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
