
package com.aim.util;

import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * A simple demonstration application showing how to create a bar chart with a
 * custom item label generator.
 */
public class MyJfreeChart extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	public MyJfreeChart(String title) {
		super(title);
	}

	/**
	 * A custom label generator.
	 */
	static class LabelGenerator extends StandardCategoryItemLabelGenerator {
		private static final long serialVersionUID = 1L;

		public String generateItemLabel(CategoryDataset dataset, int series, int category) {
			return dataset.getRowKey(series).toString();
		}
	}

}
