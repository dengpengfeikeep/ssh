package com.baidu.ssh.dao;

import java.util.List;

import com.baidu.ssh.query.ChartQueryObject;
import com.baidu.ssh.query.SaleChartQueryObject;
import com.baidu.ssh.vo.ChartVO;
import com.baidu.ssh.vo.SaleChartVO;

/**
 * @ClassName IChartDAO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月12日 下午7:44:54
 * @version 1.0.0
 */
public interface IChartDAO {
	List<ChartVO> queryCharts(ChartQueryObject qo);// 订货报表查询

	List<SaleChartVO> queryCharts(SaleChartQueryObject qo);// 销售账表查询
}
