package uk.co.mavisto.swingscorecard.app;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

public class NewCourse {
	
	public static Component createNewCoursePanel(Container parentFrame) {
		final JPanel newPanel = new JPanel(new GridLayout(1, 2));
		newPanel.setPreferredSize(new Dimension(800, 300));
		JLabel name1 = new JLabel("TEST Panel");
		name1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		name1.setPreferredSize(new Dimension(50, 50));
		JButton button = new JButton("TEST Button");
		newPanel.add(name1, "0,0");
		newPanel.add(button, "0,1");
		return newPanel;
	}

}
