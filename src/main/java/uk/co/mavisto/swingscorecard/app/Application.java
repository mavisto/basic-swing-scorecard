package uk.co.mavisto.swingscorecard.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import layout.TableLayout;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextArea contactsTextArea;

	private JTextField holeOne;
	private JTextField holeTwo;
	private JTextField holeTen;
	private JTextField hole11;

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
	}

	private Component createTestPanel() {

		double size[][] = { { 0.2, 0.1, 0.1, 0.1, 0.2, 0.1, 0.1, 0.1 }, { 40, 40, 40, 40, 40 } };

		final JPanel testPanel = new JPanel(new TableLayout(size));
		testPanel.setPreferredSize(new Dimension(500, 200));
		testPanel.setBorder(new EtchedBorder());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		JLabel name1 = new JLabel("Name");
		testPanel.add(name1, "0,0");
		JLabel name2 = new JLabel("Name");
		testPanel.add(name2, "4,0");
		JLabel si1 = new JLabel("S.I.");
		testPanel.add(si1, "1,0");
		JLabel si2 = new JLabel("S.I.");
		testPanel.add(si2, "5,0");
		JLabel gir1 = new JLabel("GIR");
		testPanel.add(gir1, "2,0");
		JLabel gir2 = new JLabel("GIR");
		testPanel.add(gir2, "6,0");
		JLabel score1 = new JLabel("Score");
		testPanel.add(score1, "3,0");
		JLabel score2 = new JLabel("Score");
		testPanel.add(score2, "7,0");

		// Hole 1
		JLabel holeOneLabel = new JLabel("Hole 1");
		testPanel.add(holeOneLabel, "0,1");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 1;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		holeOne = new JTextField();
		holeOne.setPreferredSize(new Dimension(20, 20));
		testPanel.add(holeOne, "3,1");

		// Hole 10
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel holeTenLabel = new JLabel("Hole 10");
		testPanel.add(holeTenLabel, "4,1");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		holeTen = new JTextField();
		holeTen.setPreferredSize(new Dimension(20, 20));
		testPanel.add(holeTen, "7,1");

		// Hole 2
		JLabel holeTw0Label = new JLabel("Hole 2");
		testPanel.add(holeTw0Label, "0,2");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 1;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		holeTwo = new JTextField();
		holeTwo.setPreferredSize(new Dimension(20, 20));
		testPanel.add(holeTwo, "3,2");

		// Hole 11
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 3;
		constraints.gridy = 0;
		JLabel holeElevenLabel = new JLabel("Hole 11");
		testPanel.add(holeElevenLabel, "4,2");

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 4;
		constraints.gridy = 0;
		// constraints.ipadx = 100;
		hole11 = new JTextField();
		hole11.setPreferredSize(new Dimension(20, 20));
		testPanel.add(hole11, "7,2");

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
