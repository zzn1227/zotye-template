package service.rights;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.rights.MenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void getMenuTreeTest() {
        String menuIds = "1,2,3,4,5,6,7,8,9,10";
        String contextPath = "http://localhost:8090";
        String sb = menuService.getMenuTree(menuIds, contextPath);
        Assert.assertNotNull(sb);
    }
}
