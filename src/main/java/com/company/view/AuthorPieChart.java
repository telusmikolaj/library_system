package com.company.view;

import com.company.model.Book;
import com.company.service.BookService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AuthorPieChart {

    public JFreeChart createDataset() throws SQLException {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        Map<String, Integer> topAuthorMap = BookService.getMostReadedAuthorsForChart();
        for (String key : topAuthorMap.keySet()) {
            pieDataset.setValue(key, topAuthorMap.get(key));
        }


        JFreeChart chart = ChartFactory.createPieChart
                ("Top Authors",
                        pieDataset,
                        true,
                        true,
                        false );


        return chart;
    }

    public void display() throws SQLException {
        JFrame f = new JFrame("Top Authors");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new ChartPanel(createDataset()));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }



}
