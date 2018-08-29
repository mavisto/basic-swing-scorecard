package uk.co.mavisto.swingscorecard.app;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

public class FormatHelper {

	public static JLabel createHoleLabel(JLabel tmpLabel, String text) {
		tmpLabel.setText(text);
		tmpLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		tmpLabel.setPreferredSize(new Dimension(20, 20));
		tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return tmpLabel;
	}
	
	public static JTextField createHoleTextField(JTextField tmpTxtFld) {
		tmpTxtFld.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		tmpTxtFld.setPreferredSize(new Dimension(20, 20));
		tmpTxtFld.setHorizontalAlignment(SwingConstants.CENTER);
		return tmpTxtFld;
	}

}
