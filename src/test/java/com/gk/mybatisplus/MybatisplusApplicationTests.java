package com.gk.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gk.mybatisplus.bean.User;
import com.gk.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusApplicationTests {

    @Autowired
    UserMapper userMapper;

    /**
     * 查询所有
     */
    @Test
    public void queryAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    /**
     * 查询用户根据id
     */
    @Test
    public void queryUserById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    /**
     * 插入用户(主键自动生成)
     */
    @Test
    public void saveUser(){
        User user = new User();
        user.setAge(20);
        user.setEmail("lisi.@qq.com");
        user.setName("李四");
        int row = userMapper.insert(user);
        System.out.println(row);
    }

    /**
     * 修改用户(乐观锁需要先查后修改)
     */
    @Test
    public void updateUser(){
        User user = userMapper.selectById(1L);
        user.setId(1L);
        user.setName("张3");
        userMapper.updateById(user);
    }

    /**
     * map封装查询条件
     */
    @Test
    public void selectUserByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","TOM");
        map.put("age",28);
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 分页
     */
    @Test
    public void testSelectPage(){
        //current当前页 size每页条数
        Page<User> page = new Page<>(1, 5);
        //mp分页插件底层封装：把分页所有数据封装到page对象里面
        userMapper.selectPage(page,null);
        //当前页
        System.out.println(page.getCurrent());
        //总页数
        System.out.println(page.getPages());
        //每页条数
        System.out.println(page.getSize());
        //总条数
        System.out.println(page.getTotal());
        //是否有下一页
        System.out.println(page.hasNext());
        //是否有上一页
        System.out.println(page.hasPrevious());
    }
    @Test
    public void testSelectMapsPage() {

        Page<User> page = new Page<>(1, 5);

        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);

        //注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
        mapIPage.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }
    @Test
    public void deleteById(){
        int i = userMapper.deleteById(1L);
        System.out.println(i);
    }
    @Test
    public void deleteBatchById(){
        int i = userMapper.deleteBatchIds(Arrays.asList(1207254735854276610L,1207245703076716546L,1207158997556232195L));
        System.out.println(i);
    }
    /**
     * 使用mybatis条件构造器(逻辑删除)
     */
    @Test
    public void testDelet(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("name").lt("age",20).isNotNull("email");
        int rows = userMapper.delete(queryWrapper);
        System.out.println(rows);
    }
    //条件查询
    @Test
    public void testSelectDemo1() {
        //插件条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // ge gt le lt
        // wrapper.ge("age",28);

        // eq  ne
        // wrapper.eq("name","Jone");

        //between   [20,30]
        //wrapper.between("age",20,30);

        //like
        //wrapper.like("name","m");

        //orderby
        wrapper.orderByDesc("id");

        //select
        wrapper.select("id","name");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
    @Test
    public void testSelectMaps() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .notLike("name", "e")
                .likeRight("email", "t");

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }
    //子查询
    @Test
    public void testSelectObjs() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.in("id", 1, 2, 3);
        queryWrapper.inSql("id", "select id from user where id < 3");

        List<Object> objects = userMapper.selectObjs(queryWrapper);//返回值是Object列表
        objects.forEach(System.out::println);
    }
}
