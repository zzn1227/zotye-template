package service.rights;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.model.rights.User;
import com.service.rights.UserService;
import com.system.page.PageParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void queryByPageTest() {
        User param = new User();
        PageParam page = new PageParam(1, 3);
        // page.setOrderBy("name desc");
        Page<User> list = userService.queryByPage(param, page);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void insertTest() {
        User record = new User();
        record.setLoginName("test");
        record.setPassword("xxxxxx");
        record.setName("测试");
        userService.insert(record);
    }
}
