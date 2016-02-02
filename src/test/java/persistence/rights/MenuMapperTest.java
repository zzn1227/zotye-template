package persistence.rights;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.model.rights.Menu;
import com.persistence.rights.MenuMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void selectByPrimaryKeyTest() {
        Menu menu = menuMapper.selectByPrimaryKey(1);
        Assert.assertNotNull(menu);
    }

    @Test
    public void selectListTest() {
        Menu menu = new Menu();
        PageHelper.orderBy("orderNum");
        List<Menu> list = menuMapper.selectList(menu);
        Assert.assertTrue(list.size() > 0);
    }
}
