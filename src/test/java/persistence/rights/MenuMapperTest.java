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

    @Test
    public void insertTest() {
        Menu record = new Menu();
        record.setParentId(1);
        record.setName("仓库管理");
        record.setIsFunction(0);
        int count = menuMapper.insert(record);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void deleteTest() {
        Menu record = new Menu();
        record.setId(12);
        int count = menuMapper.deleteByPrimaryKey(record);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void updateByPrimaryKeySelectiveTest() {
        Menu record = new Menu();
        record.setId(7);
        record.setName("产品管理");
        int count = menuMapper.updateByPrimaryKeySelective(record);
        Assert.assertTrue(count > 0);
    }

}
