package persistence;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.model.CarType;
import com.persistence.CarTypeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class CarTypeMapperTest {

    @Autowired
    private CarTypeMapper carTypeMapper;

    @Test
    public void getCarTypesTest() {
        List<CarType> list = carTypeMapper.getCarTypes();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void getCarTypeByPageTest() {
        int page = 1; // 页号
        int pageSize = 3; // 每页数据条数
        PageBounds pageBounds = new PageBounds(page, pageSize, Order.formString("carTypeNum.asc"), true);
        List<CarType> list = carTypeMapper.getCarTypeByPage(pageBounds);
        // 获得结果集条总数
        PageList<CarType> pageList = (PageList<CarType>) list;
        System.out.println("totalCount: " + pageList.getPaginator().getTotalCount());
    }
}
