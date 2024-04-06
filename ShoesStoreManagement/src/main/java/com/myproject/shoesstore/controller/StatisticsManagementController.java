package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.bean.BeanProduct;
import com.myproject.shoesstore.service.StatisticsService;
import com.myproject.shoesstore.service.impl.StatisticsServiceImpl;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.TaskSeries;

/**
 *
 * @author Tadaboh;
 */
public class StatisticsManagementController {
    private StatisticsService statisticsService = null;

    public StatisticsManagementController() {
        this.statisticsService = new StatisticsServiceImpl();
    }

    public void setDataToChart1(JPanel jpnItem) {
        List<BeanProduct> listItem = statisticsService.getListByProduct();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (BeanProduct item : listItem) {
                dataset.addValue(item.getQuantityProduct(), "Sản phẩm", item.getDateSell());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê số lượng sản phẩm bán ra".toUpperCase(),
                "Ngày bán", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

//    public void setDataToChart2(JPanel jpnItem) {
//        List<KhoaHocBean> listItem = statisticsService.getListByKhoaHoc();
//
//        TaskSeriesCollection ds = new TaskSeriesCollection();
//        JFreeChart ganttChart = ChartFactory.createGanttChart(
//                "BIỂU ĐỒ THEO DÕI TÌNH TRẠNG KHÓA HỌC",
//                "Khóa học", "Thời gian", ds, true, false, false
//        );
//
//        TaskSeries taskSeries;
//        Task task;
//
//        if (listItem != null) {
//            for (KhoaHocBean item : listItem) {
//                taskSeries = new TaskSeries(item.getTen_khoa_hoc());
//                task = new Task(item.getTen_khoa_hoc(), new SimpleTimePeriod(item.getNgay_bat_dau(), item.getNgay_ket_thuc()));
//                taskSeries.add(task);
//                ds.add(taskSeries);
//            }
//        }
//
//        ChartPanel chartPanel = new ChartPanel(ganttChart);
//        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));
//
//        jpnItem.removeAll();
//        jpnItem.setLayout(new CardLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.repaint();
//    }
}
