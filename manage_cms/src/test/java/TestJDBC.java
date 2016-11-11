import org.junit.Test;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.plat.domain.Site;


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
    public void testMap(){
    	DynaModel dm = new DynaModel();
    	dm.set("name", "main");
    	Site site = new Site();
    	site.fromMap(dm);
    	System.out.println(site);
    	System.out.println(site.toMap());
    }
}
