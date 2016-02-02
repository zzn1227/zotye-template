package persistence.rights;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.rights.Authority;
import com.persistence.rights.AuthorityMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class AuthorityMapperTest {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Test
    public void selectListTest() {
        Authority o = new Authority();
        String[] ids = new String[] { "1", "2" };
        o.setIds(ids);
        List<Authority> list = authorityMapper.selectList(o);
        Assert.assertTrue(list.size() > 0);
    }

}
