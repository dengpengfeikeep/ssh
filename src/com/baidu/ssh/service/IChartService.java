package com.baidu.ssh.service;

import java.util.List;

import com.baidu.ssh.query.ChartQueryObject;
import com.baidu.ssh.vo.ChartVO;

/**
 * @ClassName IChartService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月12日 下午8:02:15
 * @version 1.0.0
 */
public interface IChartService {
	List<ChartVO> queryCharts(ChartQueryObject qo);

}
