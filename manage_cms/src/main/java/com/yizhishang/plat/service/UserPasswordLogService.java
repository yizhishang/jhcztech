package com.yizhishang.plat.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.dao.UserPasswordLogDao;
import com.yizhishang.plat.domain.UserPasswordLog;
import com.yizhishang.plat.service.exception.LoginFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserPasswordLogService extends BaseService
{

    private static Logger logger = LoggerFactory.getLogger(UserPasswordLogService.class);

    @Resource
    private UserPasswordLogDao passwordLogDao;

    /**
     * 添加一条用户密码日志信息
     *
     * @param log 日志信息对象
     */
    public void addLog(UserPasswordLog log)
    {
        String id = getSequenceGenerator().getNextSequence("T_USER_PASSWORD_LOG");
        log.setLogId(Integer.parseInt(id));
        log.setCreateDate(DateHelper.formatDate(new Date(), DateHelper.pattern_time));
        passwordLogDao.addLog(log);
    }

    /**
     * 根据日志ID，删除一条日志信息，
     *
     * @param id
     */
    public void deleteLog(int id)
    {
        passwordLogDao.deleteLog(id);
    }

    /**
     * 根据用户ID，删除该用户所有的密码修改记录
     *
     * @param uid
     */
    public void deleteLogByUserId(String uid)
    {
        List<DynaModel> dataList = passwordLogDao.queryLogByUserId(uid);
        for (Object data : dataList) {
            int id = ((DynaModel) data).getInt("log_id");
            passwordLogDao.deleteLog(id);
        }
    }

    /**
     * @param pwdValidity 密码有效期
     * @param userId      用户名
     * @return String 返回密码到期提示语
     * @throws LoginFailedException
     * @描述：
     * @作者：袁永君
     * @时间：2011-1-4 下午06:49:08
     */
    public String isPasswordValidity(int pwdValidity, String loginId) throws LoginFailedException
    {
        if (pwdValidity > 0) {
            String lastModiyfPwdDate = passwordLogDao.getPwdValidity(loginId);//密码最后更新时间
            if (StringHelper.isEmpty(lastModiyfPwdDate)) {
                return null;
            }
            if (lastModiyfPwdDate.length() < 10) {
                logger.error("日期【" + lastModiyfPwdDate + "】格式错误");
                throw new LoginFailedException("系统发生异常");
            }
            int useNum = DateHelper.getDateDiff(new Date(), DateHelper.parseString(lastModiyfPwdDate, DateHelper
                    .pattern_date));

            int surplus = pwdValidity - useNum;

            if (surplus <= 0) {
                throw new LoginFailedException("您的密码已过期，请与管理员联系！");
            } else if (surplus <= 5) {
                return "您的密码将于" + surplus + "日后过期，请尽快更新您的密码！";
            } else {
                return "";
            }
        } else {
            return "";
        }

    }
}
