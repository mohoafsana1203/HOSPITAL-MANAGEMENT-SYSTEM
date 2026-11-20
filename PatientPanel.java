// PatientPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientPanel extends JPanel {
    private PatientManager manager;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtName, txtEmail, txtPhone, txtAddress, txtDisease, txtBloodGroup, txtAge, txtSearch;

    public PatientPanel() {
        manager = new PatientManager();
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));

        txtName = new JTextField(15);
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        txtAddress = new JTextField();
        txtDisease = new JTextField();
        txtBloodGroup = new JTextField();
        txtAge = new JTextField();
        txtSearch = new JTextField();

        formPanel.add(new JLabel("Name:")); formPanel.add(txtName);
        formPanel.add(new JLabel("Email:")); formPanel.add(txtEmail);
        formPanel.add(new JLabel("Phone:")); formPanel.add(txtPhone);
        formPanel.add(new JLabel("Address:")); formPanel.add(txtAddress);
        formPanel.add(new JLabel("Disease:")); formPanel.add(txtDisease);
        formPanel.add(new JLabel("Blood Group:")); formPanel.add(txtBloodGroup);
        formPanel.add(new JLabel("Age:")); formPanel.add(txtAge);
        formPanel.add(new JLabel("Search:")); formPanel.add(txtSearch);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Add Patient");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnSearch = new JButton("Search");
        JButton btnRefresh = new JButton("Refresh");

        btnPanel.add(btnAdd); btnPanel.add(btnUpdate); btnPanel.add(btnDelete);
        btnPanel.add(btnSearch); btnPanel.add(btnRefresh);

        String[] columns = {"ID", "Name", "Email", "Phone", "Disease", "Blood Group", "Age"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add(formPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addPatient());
        btnUpdate.addActionListener(e -> updatePatient());
        btnDelete.addActionListener(e -> deletePatient());
        btnSearch.addActionListener(e -> searchPatient());
        btnRefresh.addActionListener(e -> refreshTable());
    }

    private void addPatient() {
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Patient p = new Patient(0, txtName.getText(), txtEmail.getText(), txtPhone.getText(),
                txtAddress.getText(), txtDisease.getText(), txtBloodGroup.getText(),
                Integer.parseInt(txtAge.getText()));

        manager.add(p);
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this, "Patient Added Successfully!");
    }

    private void updatePatient() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update!");
            return;
        }
        // Similar logic as add...
        // (আমি সংক্ষেপে দিলাম, চাইলে পুরোটা দিতে পারি)
    }

    private void deletePatient() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        int confirm = JOptionPane.showConfirmDialog(this, "Delete this patient?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) model.getValueAt(row, 0);
            manager.delete(id);
            refreshTable();
        }
    }

    private void searchPatient() {
        String keyword = txtSearch.getText().trim();
        List<Patient> list = manager.search(keyword);
        model.setRowCount(0);
        for (Patient p : list) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getEmail(), p.getPhone(), 
                                     p.getDisease(), p.getBloodGroup(), p.getAge()});
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Patient p : manager.getAll()) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getEmail(), p.getPhone(), 
                                     p.getDisease(), p.getBloodGroup(), p.getAge()});
        }
    }

    private void clearFields() {
        txtName.setText(""); txtEmail.setText(""); txtPhone.setText(""); txtAddress.setText("");
        txtDisease.setText(""); txtBloodGroup.setText(""); txtAge.setText("");
    }
}
