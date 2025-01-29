package gui;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

public class EMPaddress extends javax.swing.JFrame {

    
    public EMPaddress() {
        initComponents();
    }
    
    public void loadEMPAddress() {
        try {
            // Ensure a selection is made in jComboBox1
            if (jComboBox1.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select an employee NIC from the dropdown.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get the selected NIC from jComboBox1
            String employeeNic = jComboBox1.getSelectedItem().toString();

            // Query to fetch address details and NIC
            String query = "SELECT ea.line1, ea.line2, e.nic "
                    + "FROM emp_address ea "
                    + "JOIN employee e ON ea.employee_user_id = e.user_id "
                    + "WHERE e.nic = '" + employeeNic + "'";

            // Execute the query
            ResultSet resultSet = MySQL.executeSearch(query);

            // Get the table model for jTable3
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0); // Clear the table before adding new rows

            // Populate the table with data
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("line1")); // Address Line 1
                vector.add(resultSet.getString("line2")); // Address Line 2
                vector.add(resultSet.getString("nic"));   // NIC

                model.addRow(vector); // Add the row to the table model
            }

            // Close the ResultSet
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Setter method for Address Line 1
    public void setAddressLineE1(String addressLine1) {
        jTextField1.setText(addressLine1);
    }

    // Setter method for Address Line 2
    public void setAddressLineE2(String addressLine2) {
        jTextField2.setText(addressLine2);
    }

    // Setter method for the ComboBox Model
    public void setComboBoxEModel(DefaultComboBoxModel<String> model) {
        jComboBox1.setModel(model);
    }

    // Setter method to select a specific teacher ID in the ComboBox
    public void setSelectedEmployeeId(String teacherId) {
        jComboBox1.setSelectedItem(teacherId);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setText("Address Line 1");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setText("Address Line 2");

        jTextField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setText("Employee NIC");

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Line 1", "Line 2", "NIC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel2.setText("Employee Address View");

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setText("2024-11-30");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(132, 132, 132)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try {
            // Get the employee's NIC from jComboBox1
            String employeeNic = jComboBox1.getSelectedItem().toString();

            // Get the address details from the text fields
            String line1 = jTextField1.getText(); // Assuming jTextField1 is for line1
            String line2 = jTextField2.getText(); // Assuming jTextField2 is for line2

            // Validate the input fields
            if (employeeNic.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an employee NIC.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (line1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Address Line 1.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (line2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Address Line 2.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                // Query to fetch user_id using NIC
                ResultSet employeeResult = MySQL.executeSearch("SELECT `user_id` FROM `employee` WHERE `nic` = '" + employeeNic + "'");

                if (employeeResult.next()) {
                    int employeeId = employeeResult.getInt("user_id");

                    // Check if an address already exists for this employee
                    ResultSet addressResult = MySQL.executeSearch("SELECT * FROM `emp_address` WHERE `employee_user_id` = " + employeeId);

                    if (addressResult.next()) {
                        JOptionPane.showMessageDialog(this, "An address already exists for this employee.", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Insert the address into the emp_address table
                        MySQL.executeIUD("INSERT INTO `emp_address` (`line1`, `line2`, `employee_user_id`) VALUES ('"
                                + line1 + "', '" + line2 + "', " + employeeId + ")");

                        JOptionPane.showMessageDialog(this, "Address registered successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }

                    // Close the address ResultSet
                    addressResult.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found. Please check the NIC.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                loadEMPAddress();

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try {
            // Get the employee's NIC from jComboBox1
            String employeeNic = jComboBox1.getSelectedItem().toString();

            // Get the address details from the text fields
            String line1 = jTextField1.getText(); // Assuming jTextField1 is for line1
            String line2 = jTextField2.getText(); // Assuming jTextField2 is for line2

            // Validate the input fields
            if (employeeNic.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an employee NIC.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (line1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Address Line 1.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (line2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Address Line 2.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                // Query to fetch user_id using NIC
                ResultSet employeeResult = MySQL.executeSearch("SELECT `user_id` FROM `employee` WHERE `nic` = '" + employeeNic + "'");

                if (employeeResult.next()) {
                    int employeeId = employeeResult.getInt("user_id");

                    // Check if an address already exists for this employee
                    ResultSet addressResult = MySQL.executeSearch("SELECT * FROM `emp_address` WHERE `employee_user_id` = " + employeeId);

                    if (addressResult.next()) {
                        // Update the address in the emp_address table
                        String updateQuery = "UPDATE `emp_address` "
                                + "SET `line1` = '" + line1 + "', `line2` = '" + line2 + "' "
                                + "WHERE `employee_user_id` = " + employeeId;

                        MySQL.executeIUD(updateQuery); // Execute the update query

                        JOptionPane.showMessageDialog(this, "Address updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existing address found for this employee. Please register the address first.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                    // Close the address ResultSet
                    addressResult.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found. Please check the NIC.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Close the employee ResultSet
                employeeResult.close();

                // Reload the table data
                loadEMPAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try {
            // Get the employee's NIC from jComboBox1
            String employeeNic = jComboBox1.getSelectedItem().toString();

            // Validate the NIC selection
            if (employeeNic.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an employee NIC.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                // Query to fetch user_id using NIC
                ResultSet employeeResult = MySQL.executeSearch("SELECT `user_id` FROM `employee` WHERE `nic` = '" + employeeNic + "'");

                if (employeeResult.next()) {
                    int employeeId = employeeResult.getInt("user_id");

                    // Check if an address exists for this employee
                    ResultSet addressResult = MySQL.executeSearch("SELECT * FROM `emp_address` WHERE `employee_user_id` = " + employeeId);

                    if (addressResult.next()) {
                        // Delete the address from the emp_address table
                        String deleteQuery = "DELETE FROM `emp_address` WHERE `employee_user_id` = " + employeeId;

                        MySQL.executeIUD(deleteQuery); // Execute the delete query

                        JOptionPane.showMessageDialog(this, "Address deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No address found for this employee.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                    // Close the address ResultSet
                    addressResult.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found. Please check the NIC.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Close the employee ResultSet
                employeeResult.close();

                // Reload the table data
                loadEMPAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

    }//GEN-LAST:event_jTable3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
