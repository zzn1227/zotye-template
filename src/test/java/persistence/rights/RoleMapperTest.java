package persistence.rights;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.rights.Role;
import com.persistence.rights.RoleMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void selectByPrimaryKeyTest() {
        Role role = roleMapper.selectByPrimaryKey(1);
        Assert.assertNotNull(role);
    }

}
