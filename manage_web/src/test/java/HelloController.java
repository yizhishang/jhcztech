import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.pojo.User;
import com.jhcz.web.aspect.MethodLog;
import com.jhcz.web.service.UserService;

@Controller
public class HelloController
{
	@Resource
	UserService userService;
	
	@RequestMapping("/greeting.action")
	@MethodLog(remark="欢迎")
	public ModelAndView greeting()
	{
		User user = userService.getUserById(1);
		ModelAndView mv = new ModelAndView("/test/hello1");
		mv.addObject("user", user);
		return mv;
	}
}
