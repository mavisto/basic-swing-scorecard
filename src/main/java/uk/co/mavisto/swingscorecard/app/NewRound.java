package uk.co.mavisto.swingscorecard.app;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.GridBagConstraints;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;

import layout.TableLayout;

public class NewRound {
	
	public static JTextField hole1Score;
	public static JTextField hole2Score;
	public static JTextField hole3Score;
	public static JTextField hole4Score;
	public static JTextField hole5Score;
	public static JTextField hole6Score;
	public static JTextField hole7Score;
	public static JTextField hole8Score;
	public static JTextField hole9Score;
	public static JTextField hole10Score;
	public static JTextField hole11Score;
	public static JTextField hole12Score;
	public static JTextField hole13Score;
	public static JTextField hole14Score;
	public static JTextField hole15Score;
	public static JTextField hole16Score;
	public static JTextField hole17Score;
	public static JTextField hole18Score;
	public static JLabel outScore;
	public static JLabel inScore;
	public static JLabel totalScore;
	public static JPanel testPanel;
	public static JLabel tmpLabel; 
	
	private GolfCourse getGolfCourseDate(long courseId, long courseDataId) {
		return null;
		
	}
	
	public static Component createRoundScorePanel(Container parentFrame) {

		double size[][] = { { 0.05, 0.07, 0.07, 0.07, 0.05, 0.07, 0.07, 0.05, 0.04, 0.07, 0.07, 0.07, 0.05, 0.07, 0.07, 0.07, 0.07 }, { 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20 } };

		// final JPanel testPanel = new JPanel(new TableLayout(size));
		JPanel testPanel = new JPanel(new TableLayout(size));
		testPanel.setPreferredSize(new Dimension(800, 300));
		testPanel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 0;

		JLabel name1 = new JLabel("Hole");
		name1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		name1.setPreferredSize(new Dimension(20, 20));
		testPanel.add(name1, "0,0");
		JLabel name2 = new JLabel("Hole");
		name2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(name2, "8,0");
		JLabel yards1 = new JLabel("Yards");
		yards1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(yards1, "1,0");
		JLabel yards2 = new JLabel("Yards");
		yards2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(yards2, "9,0");
		JLabel par1 = new JLabel("Par");
		par1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(par1, "2,0");
		JLabel par2 = new JLabel("Par");
		par2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(par2, "10,0");
		JLabel si1 = new JLabel("SI");
		si1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(si1, "3,0");
		JLabel si2 = new JLabel("SI");
		si2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(si2, "11,0");
		JLabel score1 = new JLabel("Score");
		score1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(score1, "4,0");
		JLabel score2 = new JLabel("Score");
		score2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(score2, "12,0");
		JLabel net1 = new JLabel("Net");
		net1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(net1, "5,0");
		JLabel net2 = new JLabel("Net");
		net2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(net2, "13,0");
		JLabel points1 = new JLabel("Points");
		points1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(points1, "6,0");
		JLabel points2 = new JLabel("Points");
		points2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		testPanel.add(points2, "14,0");

		// Hole 1
		JLabel hole1Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole1Label, "1"), "0,1");
		// Yards
		JLabel hole1Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole1Yds, "345"), "1,1");
		// Par
		JLabel hole1Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole1Par, "4"), "2,1");
		// SI
		JLabel hole1SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole1SI, "18"), "3,1");
		// Score
		hole1Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole1Score), "4,1");
		// Net
		JLabel hole1Net = new JLabel();
		String hole1NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole1Net, hole1NetScore), "5,1");
		// Points
		JLabel hole1Points = new JLabel();
		String tmp = Integer.toString(2);
		testPanel.add(FormatHelper.createHoleLabel(hole1Points, tmp), "6,1");

		// Hole 2
		JLabel hole2Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole2Label, "2"), "0,2");
		// Yards
		JLabel hole2Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole2Yds, "456"), "1,2");
		// Par
		JLabel hole2Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole2Par, "4"), "2,2");
		// SI
		JLabel hole2SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole2SI, "17"), "3,2");
		// Score
		hole2Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole2Score), "4,2");
		// Net
		JLabel hole2Net = new JLabel();
		String hole2NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole2Net, hole2NetScore), "5,2");
		// Points
		JLabel hole2Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole2Points, tmp), "6,2");

		// Hole 3
		JLabel hole3Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole3Label, "3"), "0,3");
		// Yards
		JLabel hole3Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole3Yds, "123"), "1,3");
		// Par
		JLabel hole3Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole3Par, "3"), "2,3");
		// SI
		JLabel hole3SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole3SI, "16"), "3,3");
		// Score
		hole3Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole3Score), "4,3");
		// Net
		JLabel hole3Net = new JLabel();
		String hole3NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole3Net, hole3NetScore), "5,3");
		// Points
		JLabel hole3Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole3Points, tmp), "6,3");

		// Hole 4
		JLabel hole4Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole4Label, "4"), "0,4");
		// Yards
		JLabel hole4Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole4Yds, "123"), "1,4");
		// Par
		JLabel hole4Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole4Par, "3"), "2,4");
		// SI
		JLabel hole4SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole4SI, "15"), "3,4");
		// Score
		hole4Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole4Score), "4,4");
		// Net
		JLabel hole4Net = new JLabel();
		String hole4NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole4Net, hole4NetScore), "5,4");
		// Points
		JLabel hole4Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole4Points, tmp), "6,4");

		// Hole 5
		JLabel hole5Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole5Label, "5"), "0,5");
		// Yards
		JLabel hole5Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole5Yds, "503"), "1,5");
		// Par
		JLabel hole5Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole5Par, "5"), "2,5");
		// SI
		JLabel hole5SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole5SI, "14"), "3,5");
		// Score
		hole5Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole5Score), "4,5");
		// Net
		JLabel hole5Net = new JLabel();
		String hole5NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole5Net, hole5NetScore), "5,5");
		// Points
		JLabel hole5Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole5Points, tmp), "6,5");

		// Hole 6
		JLabel hole6Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole6Label, "6"), "0,6");
		// Yards
		JLabel hole6Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole6Yds, "603"), "1,6");
		// Par
		JLabel hole6Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole6Par, "6"), "2,6");
		// SI
		JLabel hole6SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole6SI, "14"), "3,6");
		// Score
		hole6Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole6Score), "4,6");
		// Net
		JLabel hole6Net = new JLabel();
		String hole6NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole6Net, hole6NetScore), "5,6");
		// Points
		JLabel hole6Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole6Points, tmp), "6,6");

		// Hole 7
		JLabel hole7Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole7Label, "7"), "0,7");
		// Yards
		JLabel hole7Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole7Yds, "703"), "1,7");
		// Par
		JLabel hole7Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole7Par, "7"), "2,7");
		// SI
		JLabel hole7SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole7SI, "14"), "3,7");
		// Score
		hole7Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole7Score), "4,7");
		// Net
		JLabel hole7Net = new JLabel();
		String hole7NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole7Net, hole7NetScore), "5,7");
		// Points
		JLabel hole7Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole7Points, tmp), "6,7");

		// Hole 8
		JLabel hole8Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole8Label, "8"), "0,8");
		// Yards
		JLabel hole8Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole8Yds, "683"), "1,8");
		// Par
		JLabel hole8Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole8Par, "8"), "2,8");
		// SI
		JLabel hole8SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole8SI, "14"), "3,8");
		// Score
		hole8Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole8Score), "4,8");
		// Net
		JLabel hole8Net = new JLabel();
		String hole8NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole8Net, hole8NetScore), "5,8");
		// Points
		JLabel hole8Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole8Points, tmp), "6,8");

		// Hole 9
		JLabel hole9Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole9Label, "9"), "0,9");
		// Yards
		JLabel hole9Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole9Yds, "693"), "1,9");
		// Par
		JLabel hole9Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole9Par, "9"), "2,9");
		// SI
		JLabel hole9SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole9SI, "14"), "3,9");
		// Score
		hole9Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole9Score), "4,9");
		// Net
		JLabel hole9Net = new JLabel();
		String hole9NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole9Net, hole9NetScore), "5,9");
		// Points
		JLabel hole9Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole9Points, tmp), "6,9");

		// Hole 10
		JLabel hole10Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole10Label, "10"), "8,1");
		// Yards
		JLabel hole10Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole10Yds, "693"), "9,1");
		// Par
		JLabel hole10Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole10Par, "9"), "10,1");
		// SI
		JLabel hole10SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole10SI, "14"), "11,1");
		// Score
		hole10Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole10Score), "12,1");
		// Net
		JLabel hole10Net = new JLabel();
		String hole10NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole10Net, hole10NetScore), "13,1");
		// Points
		JLabel hole10Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole10Points, tmp), "14,1");

		// Hole 11
		JLabel hole11Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole11Label, "11"), "8,2");
		// Yards
		JLabel hole11Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole11Yds, "693"), "9,2");
		// Par
		JLabel hole11Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole11Par, "9"), "10,2");
		// SI
		JLabel hole11SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole11SI, "14"), "11,2");
		// Score
		hole11Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole11Score), "12,2");
		// Net
		JLabel hole11Net = new JLabel();
		String hole11NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole11Net, hole11NetScore), "13,2");
		// Points
		JLabel hole11Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole11Points, tmp), "14,2");

		// Hole 12
		JLabel hole12Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole12Label, "12"), "8,3");
		// Yards
		JLabel hole12Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole12Yds, "693"), "9,3");
		// Par
		JLabel hole12Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole12Par, "9"), "10,3");
		// SI
		JLabel hole12SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole12SI, "14"), "11,3");
		// Score
		hole12Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole12Score), "12,3");
		// Net
		JLabel hole12Net = new JLabel();
		String hole12NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole12Net, hole12NetScore), "13,3");
		// Points
		JLabel hole12Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole12Points, tmp), "14,3");

		// Hole 13
		JLabel hole13Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole13Label, "13"), "8,4");
		// Yards
		JLabel hole13Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole13Yds, "693"), "9,4");
		// Par
		JLabel hole13Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole13Par, "9"), "10,4");
		// SI
		JLabel hole13SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole13SI, "14"), "11,4");
		// Score
		hole13Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole13Score), "12,4");
		// Net
		JLabel hole13Net = new JLabel();
		String hole13NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole13Net, hole13NetScore), "13,4");
		// Points
		JLabel hole13Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole13Points, tmp), "14,4");

		// Hole 14
		JLabel hole14Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole14Label, "14"), "8,5");
		// Yards
		JLabel hole14Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole14Yds, "693"), "9,5");
		// Par
		JLabel hole14Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole14Par, "9"), "10,5");
		// SI
		JLabel hole14SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole14SI, "14"), "11,5");
		// Score
		hole14Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole14Score), "12,5");
		// Net
		JLabel hole14Net = new JLabel();
		String hole14NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole14Net, hole14NetScore), "13,5");
		// Points
		JLabel hole14Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole14Points, tmp), "14,5");

		// Hole 15
		JLabel hole15Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole15Label, "15"), "8,6");
		// Yards
		JLabel hole15Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole15Yds, "693"), "9,6");
		// Par
		JLabel hole15Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole15Par, "9"), "10,6");
		// SI
		JLabel hole15SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole15SI, "14"), "11,6");
		// Score
		hole15Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole15Score), "12,6");
		// Net
		JLabel hole15Net = new JLabel();
		String hole15NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole15Net, hole15NetScore), "13,6");
		// Points
		JLabel hole15Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole15Points, tmp), "14,6");

		// Hole 16
		JLabel hole16Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole16Label, "16"), "8,7");
		// Yards
		JLabel hole16Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole16Yds, "693"), "9,7");
		// Par
		JLabel hole16Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole16Par, "9"), "10,7");
		// SI
		JLabel hole16SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole16SI, "14"), "11,7");
		// Score
		hole16Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole16Score), "12,7");
		// Net
		JLabel hole16Net = new JLabel();
		String hole16NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole16Net, hole16NetScore), "13,7");
		// Points
		JLabel hole16Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole16Points, tmp), "14,7");

		// Hole 17
		JLabel hole17Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole17Label, "17"), "8,8");
		// Yards
		JLabel hole17Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole17Yds, "693"), "9,8");
		// Par
		JLabel hole17Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole17Par, "9"), "10,8");
		// SI
		JLabel hole17SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole17SI, "14"), "11,8");
		// Score
		hole17Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole17Score), "12,8");
		// Net
		JLabel hole17Net = new JLabel();
		String hole17NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole17Net, hole17NetScore), "13,8");
		// Points
		JLabel hole17Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole17Points, tmp), "14,8");

		// Hole 18
		JLabel hole18Label = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole18Label, "18"), "8,9");
		// Yards
		JLabel hole18Yds = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole18Yds, "693"), "9,9");
		// Par
		JLabel hole18Par = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole18Par, "9"), "10,9");
		// SI
		JLabel hole18SI = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole18SI, "14"), "11,9");
		// Score
		hole18Score = new JTextField();
		testPanel.add(FormatHelper.createHoleTextField(hole18Score), "12,9");
		// Net
		JLabel hole18Net = new JLabel();
		String hole18NetScore = Integer.toString(5 - 1);
		testPanel.add(FormatHelper.createHoleLabel(hole18Net, hole18NetScore), "13,9");
		// Points
		JLabel hole18Points = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(hole18Points, tmp), "14,9");

		// Out
		JLabel outLabel = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(outLabel, "Out"), "11,10");
		outScore = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(outScore, "36"), "12,10");
		// Out Net
		JLabel outNet = new JLabel();
		String outNetScore = Integer.toString(46 - 1);
		testPanel.add(FormatHelper.createHoleLabel(outNet, outNetScore), "13,10");
		// Out Points
		JLabel outPoints = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(outPoints, "38"), "14,10");

		// In
		JLabel inLabel = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(inLabel, "In"), "11,11");
		inScore = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(inScore, "36"), "12,11");
		// In Net
		JLabel inNet = new JLabel();
		String inNetScore = Integer.toString(42 - 1);
		testPanel.add(FormatHelper.createHoleLabel(inNet, inNetScore), "13,11");
		// Out Points
		JLabel inPoints = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(inPoints, "42"), "14,11");

		int tmp1 = 36;
		int tmp2 = 36;
		// Total
		JLabel totalLabel = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(totalLabel, "Total"), "11,13");
		totalScore = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(totalScore, Integer.toString(tmp1 + tmp2)), "12,13");
		// Total Net
		JLabel totalNet = new JLabel();
		String totalNetScore = Integer.toString(86);
		testPanel.add(FormatHelper.createHoleLabel(totalNet, totalNetScore), "13,13");
		// Total Points
		JLabel totalPoints = new JLabel();
		testPanel.add(FormatHelper.createHoleLabel(totalPoints, "80"), "14,13");

		Vector<Component> order = new Vector<Component>(18);
		order.add(hole1Score);
		order.add(hole2Score);
		order.add(hole3Score);
		order.add(hole4Score);
		order.add(hole5Score);
		order.add(hole6Score);
		order.add(hole7Score);
		order.add(hole8Score);
		order.add(hole9Score);
		order.add(hole10Score);
		order.add(hole11Score);
		order.add(hole12Score);
		order.add(hole13Score);
		order.add(hole14Score);
		order.add(hole15Score);
		order.add(hole16Score);
		order.add(hole17Score);
		order.add(hole18Score);

		MyOwnFocusTraversalPolicy newPolicy = new MyOwnFocusTraversalPolicy(order);
		parentFrame.setFocusTraversalPolicy(newPolicy);

		return testPanel;
	}

}
