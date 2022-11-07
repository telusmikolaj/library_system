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
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.plot.CategoryPlot;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class HistogramPanel extends JPanel {

    private DefaultCategoryDataset createDataset() throws SQLException {
        String row = "Row";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Book> topReadedBookList = BookService.getMostReadedBooksForChart();

        for (Book book : topReadedBookList) {
            dataset.addValue(book.getNumOfBorrows(), row, book.getTitle());
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) throws SQLException {


            CategoryPlot plot = new CategoryPlot();
        CategoryItemRenderer lineRender = new LineAndShapeRenderer();
        plot.setDataset(0, createDataset());
        plot.setRenderer(0, lineRender);

        CategoryItemRenderer baRenderer = new BarRenderer();
        plot.setDataset(1, createDataset());
        plot.setRenderer(1, baRenderer);

        plot.getRenderer().setSeriesPaint(0, new Color(128, 0, 0));
        plot.getRenderer().setSeriesPaint(1, new Color(0, 0, 255));
        plot.getRenderer().setSeriesPaint(2, new Color(0, 230, 255));
        plot.getRenderer().setSeriesPaint(2, new Color(0, 230, 255));
        plot.getRenderer().setSeriesPaint(2, new Color(0, 230, 255));

        plot.setDomainAxis(new CategoryAxis("Readers"));
        plot.setRangeAxis(new NumberAxis("Title"));

        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Most Readed Books Chart");


        return chart;

    }

    public void display() throws SQLException {
        JFrame f = new JFrame("BarChart");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new ChartPanel(createChart(createDataset())));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
