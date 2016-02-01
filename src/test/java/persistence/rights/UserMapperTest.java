package persistence.rights;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.model.rights.User;
import com.persistence.rights.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest() {
        // for (int i = 1; i < 12; i++) {
        User record = new User();
        record.setLoginName("test");
        record.setPassword("xxxxxx");
        record.setName("测试");
        int count = userMapper.insert(record);
        Assert.assertTrue(count > 0);
        // }
    }

    @Test
    public void selectListTest() {
        final User record = new User();
        PageHelper.startPage(2, 3, "name desc");
        List<User> list = userMapper.selectList(record);
        Assert.assertTrue(list.size() > 0);

        long count = PageHelper.count(new ISelect() {

            @Override
            public void doSelect() {
                userMapper.selectList(record);
            }
        });
        Assert.assertTrue(count > 0);

        List<User> list2 = userMapper.selectList();
        Assert.assertTrue(list2.size() > 0);
    }

    @Test
    public void checkUserTest() {
        User user = new User();
        user.setLoginName("zhaozhineng");
        User returnUser = userMapper.checkUser(user);
        Assert.assertNotNull(returnUser);
    }
}
