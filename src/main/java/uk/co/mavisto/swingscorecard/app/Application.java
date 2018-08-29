package uk.co.mavisto.swingscorecard.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextArea contactsTextArea;

	private DefaultListModel<Contact> contactsListModel;
	private JList<Contact> contactsList;

	private Action refreshAction;
	private Action newAction;
	private Action saveAction;
	private Action deleteAction;
	private Action switchAction;

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

		// add(createTabedPanel(), BorderLayout.EAST);

		add(createCardPanel(), BorderLayout.EAST);
		// add(createTestPanel(), BorderLayout.EAST);
		// add(createNewPanel(), BorderLayout.EAST);
	}

	private Component createCardPanel() {
		final JPanel cards;
		final String ROUNDCARD = "Card for Rounds";
		final String COURSECARD = "Card for Courses";

		final CardLayout cl = new CardLayout();

		JPanel card1 = new JPanel();
		JPanel card2 = new JPanel();
	
		cards = new JPanel(cl);

		cards.setPreferredSize(new Dimension(900, 400));

		card1.add(NewRound.createRoundScorePanel(this));
		card2.add(NewCourse.createNewCoursePanel(this));
		
		card1.setBackground(Color.BLACK);
		card2.setBackground(Color.BLUE);
				
		JButton b1 = new JButton("Switch");
		b1.setPreferredSize(new Dimension(100, 50));
		b1.setBackground(Color.YELLOW);
		card1.add(b1);
		JButton b2 = new JButton("Switch");
		b2.setPreferredSize(new Dimension(100, 50));
		b2.setBackground(Color.WHITE);
		card1.add(b1);
		card2.add(b2);
		cards.add(card1, ROUNDCARD);
		cards.add(card2, COURSECARD);
		cl.show(cards, ROUNDCARD);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, COURSECARD);

			}
		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, ROUNDCARD);

			}
		});

		return cards;
	}

	private Component createTabedPanel() {
		JTabbedPane mainPane = new JTabbedPane();
		mainPane.setTabPlacement(JTabbedPane.LEFT);
		mainPane.add("Round", NewRound.createRoundScorePanel(this));
		mainPane.add("Course", NewCourse.createNewCoursePanel(this));
		return mainPane;
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
