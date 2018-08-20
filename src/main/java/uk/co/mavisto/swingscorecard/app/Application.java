package uk.co.mavisto.swingscorecard.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import layout.TableLayout;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextArea contactsTextArea;

	private JTextField hole1Score;
	private JTextField hole2Score;
	private JTextField hole3Score;
	private JTextField hole4Score;
	private JTextField hole5Score;
	private JTextField hole6Score;
	private JTextField hole7Score;
	private JTextField hole8Score;
	private JTextField hole9Score;
	private JTextField hole10Score;
	private JTextField hole11Score;
	private JTextField hole12Score;
	private JTextField hole13Score;
	private JTextField hole14Score;
	private JTextField hole15Score;
	private JTextField hole16Score;
	private JTextField hole17Score;
	private JTextField hole18Score;
	private JTextField outScore;
	private JTextField inScore;
	private JTextField totalScore;
	private JPanel testPanel;
	private JLabel tmpLabel;

	private DefaultListModel<Contact> contactsListModel;
	private JList<Contact> contactsList;

	private Action refreshAction;
	private Action newAction;
	private Action saveAction;
	private Action deleteAction;

	private Contact selected;

	public Application() {
		initActions();
		initComponents();

		refreshData();
	}

	private ImageIcon load(final String name) {
		return new ImageIcon(getClass().getResource("/icons/" + name + ".png"));
	}

	private void save() {

		if (selected != null) {
			selected.setName(nameTextField.getText());
			selected.setContacts(contactsTextArea.getText());
			try {
				selected.save();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Failed to save the selected contact", "Save", JOptionPane.WARNING_MESSAGE);
			} finally {
				refreshData();
			}
		}

	}

	private void createNew() {
		Contact contact = new Contact();
		contact.setName("New Contact Naame");
		contact.setContacts("New Contact Details");
		setSelectedcontact(contact);
	}

	private void delete() {
		if (selected != null) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Delete?", "Delete", JOptionPane.YES_NO_OPTION)) {
				try {
					selected.delete();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Failed to delete the selected contact", "Delete", JOptionPane.WARNING_MESSAGE);
				} finally {
					setSelectedcontact(null);
					refreshData();
				}
			}
		}
	}

	private JToolBar createToolBar() {
		final JToolBar toolBar = new JToolBar();
		toolBar.add(refreshAction);
		toolBar.addSeparator();
		toolBar.add(newAction);
		toolBar.add(saveAction);
		toolBar.addSeparator();
		toolBar.add(deleteAction);

		return toolBar;
	}

	private void initActions() {
		refreshAction = new AbstractAction("Refresh", load("Refresh")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshData();
			}
		};

		newAction = new AbstractAction("New", load("New")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createNew();
			}
		};

		saveAction = new AbstractAction("Save", load("Save")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		};

		deleteAction = new AbstractAction("Delete", load("Delete")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		};

	}

	private void initComponents() {
		add(createToolBar(), BorderLayout.PAGE_START);
		add(createListPanel(), BorderLayout.WEST);
		add(createEditor(), BorderLayout.CENTER);
		add(createTestPanel(), BorderLayout.EAST);
		// add(createNewPanel(), BorderLayout.EAST);
	}

	private Component createNewPanel() {
		final JPanel newPanel = new JPanel(new GridLayout(1, 2));
		newPanel.setPreferredSize(new Dimension(100, 100));
		JLabel name1 = new JLabel("TEST Panel");
		name1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		name1.setPreferredSize(new Dimension(50, 50));
		JButton button = new JButton("TEST Button");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newPanel.setVisible(false);
				testPanel.setVisible(true);
			}
		});
		newPanel.add(name1, "0,0");
		newPanel.add(button, "0,1");
		return newPanel;
	}

	private JLabel createHoleLabel(JLabel tmpLabel, String text) {
		tmpLabel.setText(text);
		tmpLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		tmpLabel.setPreferredSize(new Dimension(20, 20));
		tmpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return tmpLabel;
	}

	private JTextField createHoleTextField(JTextField tmpTxtFld) {
		tmpTxtFld.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		tmpTxtFld.setPreferredSize(new Dimension(20, 20));
		tmpTxtFld.setHorizontalAlignment(SwingConstants.CENTER);
		return tmpTxtFld;
	}

	private JLabel createHoleParLabel(JLabel holePar, String text) {
		holePar.setText(text);
		holePar.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		holePar.setPreferredSize(new Dimension(20, 20));
		holePar.setHorizontalAlignment(SwingConstants.CENTER);
		return holePar;
	}

	private Component createTestPanel() {

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
		testPanel.add(createHoleLabel(hole1Label, "1"), "0,1");
		// Yards
		JLabel hole1Yds = new JLabel();
		testPanel.add(createHoleLabel(hole1Yds, "345"), "1,1");
		// Par
		JLabel hole1Par = new JLabel();
		testPanel.add(createHoleLabel(hole1Par, "4"), "2,1");
		// SI
		JLabel hole1SI = new JLabel();
		testPanel.add(createHoleLabel(hole1SI, "18"), "3,1");
		// Score
		hole1Score = new JTextField();
		testPanel.add(createHoleTextField(hole1Score), "4,1");
		// Net
		JLabel hole1Net = new JLabel();
		String hole1NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole1Net, hole1NetScore), "5,1");
		// Points
		JLabel hole1Points = new JLabel();
		String tmp = Integer.toString(2);
		testPanel.add(createHoleLabel(hole1Points, tmp), "6,1");

		// Hole 2
		JLabel hole2Label = new JLabel();
		testPanel.add(createHoleLabel(hole2Label, "2"), "0,2");
		// Yards
		JLabel hole2Yds = new JLabel();
		testPanel.add(createHoleLabel(hole2Yds, "456"), "1,2");
		// Par
		JLabel hole2Par = new JLabel();
		testPanel.add(createHoleLabel(hole2Par, "4"), "2,2");
		// SI
		JLabel hole2SI = new JLabel();
		testPanel.add(createHoleLabel(hole2SI, "17"), "3,2");
		// Score
		hole2Score = new JTextField();
		testPanel.add(createHoleTextField(hole2Score), "4,2");
		// Net
		JLabel hole2Net = new JLabel();
		String hole2NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole2Net, hole2NetScore), "5,2");
		// Points
		JLabel hole2Points = new JLabel();
		testPanel.add(createHoleLabel(hole2Points, tmp), "6,2");

		// Hole 3
		JLabel hole3Label = new JLabel();
		testPanel.add(createHoleLabel(hole3Label, "3"), "0,3");
		// Yards
		JLabel hole3Yds = new JLabel();
		testPanel.add(createHoleLabel(hole3Yds, "123"), "1,3");
		// Par
		JLabel hole3Par = new JLabel();
		testPanel.add(createHoleLabel(hole3Par, "3"), "2,3");
		// SI
		JLabel hole3SI = new JLabel();
		testPanel.add(createHoleLabel(hole3SI, "16"), "3,3");
		// Score
		hole3Score = new JTextField();
		testPanel.add(createHoleTextField(hole3Score), "4,3");
		// Net
		JLabel hole3Net = new JLabel();
		String hole3NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole3Net, hole3NetScore), "5,3");
		// Points
		JLabel hole3Points = new JLabel();
		testPanel.add(createHoleLabel(hole3Points, tmp), "6,3");

		// Hole 4
		JLabel hole4Label = new JLabel();
		testPanel.add(createHoleLabel(hole4Label, "4"), "0,4");
		// Yards
		JLabel hole4Yds = new JLabel();
		testPanel.add(createHoleLabel(hole4Yds, "123"), "1,4");
		// Par
		JLabel hole4Par = new JLabel();
		testPanel.add(createHoleLabel(hole4Par, "3"), "2,4");
		// SI
		JLabel hole4SI = new JLabel();
		testPanel.add(createHoleLabel(hole4SI, "15"), "3,4");
		// Score
		hole4Score = new JTextField();
		testPanel.add(createHoleTextField(hole4Score), "4,4");
		// Net
		JLabel hole4Net = new JLabel();
		String hole4NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole4Net, hole4NetScore), "5,4");
		// Points
		JLabel hole4Points = new JLabel();
		testPanel.add(createHoleLabel(hole4Points, tmp), "6,4");

		// Hole 5
		JLabel hole5Label = new JLabel();
		testPanel.add(createHoleLabel(hole5Label, "5"), "0,5");
		// Yards
		JLabel hole5Yds = new JLabel();
		testPanel.add(createHoleLabel(hole5Yds, "503"), "1,5");
		// Par
		JLabel hole5Par = new JLabel();
		testPanel.add(createHoleLabel(hole5Par, "5"), "2,5");
		// SI
		JLabel hole5SI = new JLabel();
		testPanel.add(createHoleLabel(hole5SI, "14"), "3,5");
		// Score
		hole5Score = new JTextField();
		testPanel.add(createHoleTextField(hole5Score), "4,5");
		// Net
		JLabel hole5Net = new JLabel();
		String hole5NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole5Net, hole5NetScore), "5,5");
		// Points
		JLabel hole5Points = new JLabel();
		testPanel.add(createHoleLabel(hole5Points, tmp), "6,5");

		// Hole 6		
		JLabel hole6Label = new JLabel();
		testPanel.add(createHoleLabel(hole6Label, "6"), "0,6");
		// Yards
		JLabel hole6Yds = new JLabel();
		testPanel.add(createHoleLabel(hole6Yds, "603"), "1,6");
		// Par
		JLabel hole6Par = new JLabel();
		testPanel.add(createHoleLabel(hole6Par, "6"), "2,6");
		// SI
		JLabel hole6SI = new JLabel();
		testPanel.add(createHoleLabel(hole6SI, "14"), "3,6");
		// Score
		hole6Score = new JTextField();
		testPanel.add(createHoleTextField(hole6Score), "4,6");
		// Net
		JLabel hole6Net = new JLabel();
		String hole6NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole6Net, hole6NetScore), "5,6");
		// Points
		JLabel hole6Points = new JLabel();
		testPanel.add(createHoleLabel(hole6Points, tmp), "6,6");

		// Hole 7
		JLabel hole7Label = new JLabel();
		testPanel.add(createHoleLabel(hole7Label, "7"), "0,7");
		// Yards
		JLabel hole7Yds = new JLabel();
		testPanel.add(createHoleLabel(hole7Yds, "703"), "1,7");
		// Par
		JLabel hole7Par = new JLabel();
		testPanel.add(createHoleLabel(hole7Par, "7"), "2,7");
		// SI
		JLabel hole7SI = new JLabel();
		testPanel.add(createHoleLabel(hole7SI, "14"), "3,7");
		// Score
		hole7Score = new JTextField();
		testPanel.add(createHoleTextField(hole7Score), "4,7");
		// Net
		JLabel hole7Net = new JLabel();
		String hole7NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole7Net, hole7NetScore), "5,7");
		// Points
		JLabel hole7Points = new JLabel();
		testPanel.add(createHoleLabel(hole7Points, tmp), "6,7");
		
		// Hole 8
		JLabel hole8Label = new JLabel();
		testPanel.add(createHoleLabel(hole8Label, "6"), "0,8");
		// Yards
		JLabel hole8Yds = new JLabel();
		testPanel.add(createHoleLabel(hole8Yds, "683"), "1,8");
		// Par
		JLabel hole8Par = new JLabel();
		testPanel.add(createHoleLabel(hole8Par, "8"), "2,8");
		// SI
		JLabel hole8SI = new JLabel();
		testPanel.add(createHoleLabel(hole8SI, "14"), "3,8");
		// Score
		hole8Score = new JTextField();
		testPanel.add(createHoleTextField(hole8Score), "4,8");
		// Net
		JLabel hole8Net = new JLabel();
		String hole8NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole8Net, hole8NetScore), "5,8");
		// Points
		JLabel hole8Points = new JLabel();
		testPanel.add(createHoleLabel(hole8Points, tmp), "6,8");

		// Hole 9
		JLabel hole9Label = new JLabel();
		testPanel.add(createHoleLabel(hole9Label, "6"), "0,9");
		// Yards
		JLabel hole9Yds = new JLabel();
		testPanel.add(createHoleLabel(hole9Yds, "693"), "1,9");
		// Par
		JLabel hole9Par = new JLabel();
		testPanel.add(createHoleLabel(hole9Par, "9"), "2,9");
		// SI
		JLabel hole9SI = new JLabel();
		testPanel.add(createHoleLabel(hole9SI, "14"), "3,9");
		// Score
		hole9Score = new JTextField();
		testPanel.add(createHoleTextField(hole9Score), "4,9");
		// Net
		JLabel hole9Net = new JLabel();
		String hole9NetScore = Integer.toString(5 - 1);
		testPanel.add(createHoleLabel(hole9Net, hole9NetScore), "5,9");
		// Points
		JLabel hole9Points = new JLabel();
		testPanel.add(createHoleLabel(hole9Points, tmp), "6,9");

		// Hole 10
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole10Label = new JLabel("10");
		hole10Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole10Label.setPreferredSize(new Dimension(20, 20));
		hole10Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole10Label, "8,1");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole10Score = new JTextField();
		hole10Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole10Score, "12,1");

		// Hole 11
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole11Label = new JLabel("11");
		hole11Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole11Label.setPreferredSize(new Dimension(20, 20));
		hole11Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole11Label, "8,2");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole11Score = new JTextField();
		hole11Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole11Score, "12,2");

		// Hole 12
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole12Label = new JLabel("12");
		hole12Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole12Label.setPreferredSize(new Dimension(20, 20));
		hole12Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole12Label, "8,3");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole12Score = new JTextField();
		hole12Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole12Score, "12,3");

		// Hole 13
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole13Label = new JLabel("13");
		hole13Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole13Label.setPreferredSize(new Dimension(20, 20));
		hole13Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole13Label, "8,4");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole13Score = new JTextField();
		hole13Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole13Score, "12,4");

		// Hole 14
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole14Label = new JLabel("14");
		hole14Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole14Label.setPreferredSize(new Dimension(20, 20));
		hole14Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole14Label, "8,5");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole14Score = new JTextField();
		hole14Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole14Score, "12,5");

		// Hole 15
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole15Label = new JLabel("15");
		hole15Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole15Label.setPreferredSize(new Dimension(20, 20));
		hole15Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole15Label, "8,6");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole15Score = new JTextField();
		hole15Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole15Score, "12,6");

		// Hole 16
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole16Label = new JLabel("16");
		hole16Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole16Label.setPreferredSize(new Dimension(20, 20));
		hole16Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole16Label, "8,7");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole16Score = new JTextField();
		hole16Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole16Score, "12,7");

		// Hole 17
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole17Label = new JLabel("17");
		hole17Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole17Label.setPreferredSize(new Dimension(20, 20));
		hole17Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole17Label, "8,8");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole17Score = new JTextField();
		hole17Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole17Score, "12,8");

		// Hole 18
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel hole18Label = new JLabel("18");
		hole18Label.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		hole18Label.setPreferredSize(new Dimension(20, 20));
		hole18Label.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(hole18Label, "8,9");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole18Score = new JTextField();
		hole18Score.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole18Score, "12,9");

		// Out
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel outLabel = new JLabel("Out");
		outLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		outLabel.setPreferredSize(new Dimension(20, 20));
		outLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(outLabel, "11,10");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		outScore = new JTextField();
		outScore.setPreferredSize(new Dimension(20, 20));
		testPanel.add(outScore, "12,10");

		// In
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel inLabel = new JLabel("In");
		inLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		inLabel.setPreferredSize(new Dimension(20, 20));
		inLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(inLabel, "11,11");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		inScore = new JTextField();
		inScore.setPreferredSize(new Dimension(20, 20));
		testPanel.add(inScore, "12,11");

		// Total
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel totalLabel = new JLabel("Total");
		totalLabel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		totalLabel.setPreferredSize(new Dimension(20, 20));
		totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testPanel.add(totalLabel, "11,13");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		totalScore = new JTextField();
		totalScore.setPreferredSize(new Dimension(20, 20));
		testPanel.add(totalScore, "12,13");

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
		this.setFocusTraversalPolicy(newPolicy);

		return testPanel;
	}

	private JComponent createListPanel() {
		contactsListModel = new DefaultListModel<>();
		contactsList = new JList<>(contactsListModel);
		contactsList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Contact selected = contactsList.getSelectedValue();
					setSelectedcontact(selected);
				}
			}
		});
		return new JScrollPane(contactsList);
	}

	private void setSelectedcontact(Contact contact) {
		this.selected = contact;
		if (contact == null) {
			idTextField.setText("");
			nameTextField.setText("");
			contactsTextArea.setText("");
		} else {
			idTextField.setText(String.valueOf(contact.getId()));
			nameTextField.setText(contact.getName());
			contactsTextArea.setText(contact.getContacts());
		}
	}

	private JComponent createEditor() {
		final JPanel panel = new JPanel(new GridBagLayout());

		// Id
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(2, 2, 2, 2);
		panel.add(new JLabel("Id"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.fill = GridBagConstraints.BOTH;
		idTextField = new JTextField();
		idTextField.setEditable(false);
		panel.add(idTextField, constraints);

		// Name
		constraints = new GridBagConstraints();
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(2, 2, 2, 2);
		panel.add(new JLabel("Name"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.fill = GridBagConstraints.BOTH;
		nameTextField = new JTextField();
		panel.add(nameTextField, constraints);

		// Contacts
		constraints = new GridBagConstraints();
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.insets = new Insets(2, 2, 2, 2);
		panel.add(new JLabel("Contact"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.fill = GridBagConstraints.BOTH;
		contactsTextArea = new JTextArea();
		panel.add(new JScrollPane(contactsTextArea), constraints);

		return panel;
	}

	private void refreshData() {

		contactsListModel.removeAllElements();
		SwingWorker<Void, Contact> worker = new SwingWorker<Void, Contact>() {
			@Override
			protected Void doInBackground() throws Exception {
				List<Contact> contacts = ContactsHelper.getInstance().getContacts();
				for (Contact contact : contacts) {
					publish(contact);
				}
				return null;
			}

			@Override
			protected void process(List<Contact> chunks) {
				for (Contact contact : chunks) {
					contactsListModel.addElement(contact);
				}
			}
		};
		worker.execute();
	}

}
