package persistence.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.baseinfo.Factory;
import com.persistence.baseinfo.FactoryMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class FactoryMapperTest {

    @Autowired
    private FactoryMapper factoryMapper;

    @Test
    public void selectListTest() {
        Factory factory = new Factory();
        factory.setFactoryNum("1000");
        List<Factory> list = factoryMapper.selectList();
        Assert.assertTrue(list.size() > 0);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("factoryNum", "1000");
        List<Factory> list2 = factoryMapper.selectList(param);
        Assert.assertTrue(list2.size() > 0);
    }
}
