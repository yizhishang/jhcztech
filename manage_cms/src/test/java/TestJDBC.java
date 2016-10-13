import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jhcz.base.jdbc.JdbcTemplate;


/**
 * 描述: 测试jdbc的连接情况
 * 版权: Copyright (c) 2016
 * 公司: 思迪科技 yuanyj@thinkive.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-23
 * 创建时间: 下午2:10:44
 */
public class TestJDBC
{
	@Test
    public void testJdbc()
    {
        JdbcTemplate jdbc = new JdbcTemplate();
        String sql = "select sysdate from dual";
        System.out.println(jdbc.queryString(sql));
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void testMap()
    {
        Map map1 = new HashMap();
        map1.put("name", "Jack");
        map1.put("age", "14");
        Map map2 = new HashMap();
        map2.put("name", "Lucy");
        System.out.println(map1);
        map1.putAll(map2);
        System.out.println(map1);
    }
    
    public void testSplit()
    {
        String str = "form.is_special_format";
        System.out.println(str.indexOf("form."));
        String thisName = str.split("form\\.")[1];
        System.out.println(thisName);
    }
}
