package com.baidu.ssh.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月4日 下午7:31:36
 * @version 1.0.0
 */
public class DateUtil {

	/**
	 * @Description 返回开始的时间,把时分秒全部设置为0 
	 * @param current
	 * @return
	 */
	public static Date getBeginDate(Date current) {
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DATE), 0, 0, 0);
		return c.getTime();
	}

	/**
	 * @Description 返回结束的时间,把时分秒全部设置为0,天数加一天,然后减一秒
	 * @param current
	 * @return
	 */
	public static Date getEndDate(Date current) {
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DATE), 0, 0, 0);
		c.add(Calendar.DATE, 1);
		c.add(Calendar.SECOND, -1);
		return c.getTime();
	}

}
