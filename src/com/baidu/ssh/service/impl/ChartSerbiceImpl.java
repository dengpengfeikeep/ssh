package com.baidu.ssh.service.impl;

import java.util.List;

import lombok.Setter;

import com.baidu.ssh.dao.IChartDAO;
import com.baidu.ssh.query.ChartQueryObject;
import com.baidu.ssh.query.SaleChartQueryObject;
import com.baidu.ssh.service.IChartService;
import com.baidu.ssh.vo.ChartVO;
import com.baidu.ssh.vo.SaleChartVO;

/**
 * @ClassName ChartSerbiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2018年2月12日 下午8:03:02
 * @version 1.0.0
 */
public class ChartSerbiceImpl implements IChartService {
	@Setter
	private IChartDAO chartDAO;

	public List<ChartVO> queryCharts(ChartQueryObject qo) {

		return chartDAO.queryCharts(qo);
	}

	public List<SaleChartVO> querySaleCharts(SaleChartQueryObject qo) {

		return chartDAO.queryCharts(qo);
	}

}
