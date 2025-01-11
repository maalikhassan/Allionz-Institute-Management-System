package gui;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.AdminUserSession;
import model.FinancialUserSession;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Dell
 */
public class AdminDashboard extends javax.swing.JFrame {

    private static String userName = AdminUserSession.getInstance().getUsername();
    private static String SystemDateTime;
    private static HashMap<String, String> streamMap = new HashMap<>();

    private void image() {

        FlatSVGIcon icon1 = new FlatSVGIcon("resources//LOGOWHITE.svg", jLabel6.getWidth(), jLabel6.getHeight());
        FlatSVGIcon icon2 = new FlatSVGIcon("resources//backup.svg", jButton3.getWidth(), jButton3.getHeight());
        FlatSVGIcon icon3 = new FlatSVGIcon("resources//restore.svg", jButton4.getWidth(), jButton4.getHeight());
        FlatSVGIcon icon4 = new FlatSVGIcon("resources/student.svg", studentpiclabel.getWidth(), studentpiclabel.getHeight());
        FlatSVGIcon icon5 = new FlatSVGIcon("resources/teacher.svg", teacherpiclabel.getWidth(), teacherpiclabel.getHeight());
        FlatSVGIcon icon6 = new FlatSVGIcon("resources/books.svg", subjectpiclabel.getWidth(), subjectpiclabel.getHeight());
        FlatSVGIcon icon7 = new FlatSVGIcon("resources/profit.svg", profitpiclabel.getWidth(), profitpiclabel.getHeight());
        FlatSVGIcon icon9 = new FlatSVGIcon("resources/dues.svg", duepiclabel.getWidth(), duepiclabel.getHeight());
        FlatSVGIcon icon10 = new FlatSVGIcon("resources/profileImage.svg", profilepiclabel.getWidth(), profilepiclabel.getHeight());

        jButton3.setIcon(icon2);
        jButton4.setIcon(icon3);
        studentpiclabel.setIcon(icon4);
        teacherpiclabel.setIcon(icon5);
        subjectpiclabel.setIcon(icon6);
        profitpiclabel.setIcon(icon7);
        duepiclabel.setIcon(icon9);
        profilepiclabel.setIcon(icon10);

        jLabel6.setIcon(icon1);
    }

    /**
     * Creates new form NewDashboard
     */
    public AdminDashboard() {
        initComponents();
        image();
        loadStudents("");
        loadTeachers("");
        loadLogins("");
        loadEmployee("");
        loadStream();
        loadUserProfile();
        jLabel44.setText(AdminUserSession.getInstance().getName());
        time();
        overviewpanel.setVisible(true);
        managementpanel.setVisible(false);
        reportspanel.setVisible(false);
        activitypanel.setVisible(false);
        profilepanel.setVisible(false);
        menu1.setBackground(new Color(5, 93, 165));
        loadAdminStudentEnrollmentReport();
        loadAdminSheduleForSubject();
        loadAdminSheduleForReport();
        loadAdminStudentAttendanceReportTable();
        loadAdminStudentAttendanceReportTable();
        //Financial report load 
        loadIncomeTable();
        LoadExpensesTable();
        loadSalaryDetailsStaff();
        loadSalaryDetailsTeacher();
        LoadFessDetails();
        LoadDuesTable();
        loadProfitLoss();
        loadAdminTeacherEnrollmenrt();
        loadProfitLoss();
        loadChartIntoPanel();
        loadPieChartIntoPanel();
        calculateProfit();
        loadTotalSubjects();
        loadDues();
        loadTotalStudents();
        loadTotalTeachers();
        loadActivityLog();

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        jTable12.setDefaultRenderer(Object.class, render);
        jTable9.setDefaultRenderer(Object.class, render);
        jTable10.setDefaultRenderer(Object.class, render);
        jTable17.setDefaultRenderer(Object.class, render);
        jTable18.setDefaultRenderer(Object.class, render);
        jTable8.setDefaultRenderer(Object.class, render);

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        updateDateTime();
    }

    private void updateDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        SystemDateTime = formattedDateTime;
    }

    private void loadTotalStudents() {
        int totalStudents = 0;

        try {
            // Execute query to get the total count of students
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS total FROM students");

            // Process the result set
            if (rs.next()) {
                totalStudents = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching total students: " + e.getMessage());
            return;
        }

        // Set the total students count and text color to jLabel13
        jLabel13.setText(String.valueOf(totalStudents));
        jLabel13.setForeground(Color.WHITE); // Set text color to white
    }

    private void loadTotalTeachers() {
        int totalTeachers = 0;

        try {
            // Execute query to get the total count of teachers
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS total FROM teachers");

            // Process the result set
            if (rs.next()) {
                totalTeachers = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching total teachers: " + e.getMessage());
            return;
        }

        // Set the total teachers count and text color to jLabel17
        jLabel17.setText(String.valueOf(totalTeachers));
        jLabel17.setForeground(Color.WHITE); // Set text color to white
    }

    private void loadDues() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            private double totalDues = 0;

            @Override
            protected Void doInBackground() {
                try {
                    // Fetch total pending feepayments
                    ResultSet rsPendingFees = MySQL.executeSearch(
                            "SELECT SUM(amount_paid) AS total_pending_fees "
                            + "FROM feepayments "
                            + "WHERE payment_status_id = (SELECT id FROM payment_status WHERE status = 'unpaid')"
                    );
                    if (rsPendingFees.next()) {
                        totalDues += rsPendingFees.getDouble("total_pending_fees");
                    }

                    // Fetch total unpaid salaries
                    ResultSet rsUnpaidSalaries = MySQL.executeSearch(
                            "SELECT SUM(net_amount) AS total_unpaid_salaries "
                            + "FROM salary "
                            + "WHERE payment_status_id = (SELECT id FROM payment_status WHERE status = 'unpaid')"
                    );
                    if (rsUnpaidSalaries.next()) {
                        totalDues += rsUnpaidSalaries.getDouble("total_unpaid_salaries");
                    }

                    // Fetch total unpaid bill payments
                    ResultSet rsUnpaidBills = MySQL.executeSearch(
                            "SELECT SUM(amount) AS total_unpaid_bills "
                            + "FROM bill_payments "
                            + "WHERE payment_status_id = (SELECT id FROM payment_status WHERE status = 'unpaid')"
                    );
                    if (rsUnpaidBills.next()) {
                        totalDues += rsUnpaidBills.getDouble("total_unpaid_bills");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error fetching dues: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void done() {

                Color lightRed = new Color(255, 102, 102);
                // Display dues in jLabel15
                jLabel15.setText(String.format("%.2f", totalDues)); // Format with 2 decimal places
                jLabel15.setForeground(totalDues > 0 ? lightRed : Color.GREEN); // Red for dues, Green if no dues
            }
        };

        worker.execute();
    }

    private void loadTotalSubjects() {
        int totalSubjects = 0;

        try {
            // Execute query to get the total count of subjects
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS total FROM subjects");

            // Process the result set
            if (rs.next()) {
                totalSubjects = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching total subjects: " + e.getMessage());
            return;
        }

        // Set the total subjects count and text color to jLabel20
        jLabel20.setText(String.valueOf(totalSubjects));
        jLabel20.setForeground(Color.WHITE); // Set text color to white
    }

    private void calculateProfit() {
        // Create a SwingWorker for the background task
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            private double totalIncome = 0;
            private double totalExpenses = 0;
            private double profit = 0;

            @Override
            protected Void doInBackground() {
                try {
                    // Fetch total income from feepayments
                    ResultSet rsIncome = MySQL.executeSearch("SELECT SUM(amount_paid) AS total_income FROM feepayments");
                    if (rsIncome.next()) {
                        totalIncome = rsIncome.getDouble("total_income");
                    }

                    // Fetch total expenses from salary
                    ResultSet rsSalary = MySQL.executeSearch("SELECT SUM(net_amount) AS total_salary FROM salary");
                    if (rsSalary.next()) {
                        totalExpenses += rsSalary.getDouble("total_salary");
                    }

                    // Fetch total expenses from billpayments
                    ResultSet rsBillPayments = MySQL.executeSearch("SELECT SUM(amount) AS total_bill FROM bill_payments");
                    if (rsBillPayments.next()) {
                        totalExpenses += rsBillPayments.getDouble("total_bill");
                    }

                    // Calculate profit
                    profit = totalIncome - totalExpenses;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error calculating profit: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void done() {
                // Update the JLabel in the EDT
                jLabel32.setText(String.format("%.2f", profit)); // Display profit with 2 decimal places
                jLabel32.setForeground(profit >= 0 ? Color.GREEN : Color.RED); // Green for profit, Red for loss
            }
        };

        // Execute the SwingWorker
        worker.execute();
    }

    private void loadPieChartIntoPanel() {
        int totalAttend = 0;
        int totalAbsent = 0;

        try {
            // Use your MySQL.executeSearch method to retrieve data
            ResultSet rs = MySQL.executeSearch("SELECT status, COUNT(*) AS count FROM `student_attendance` GROUP BY `status`");

            // Process the ResultSet
            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt("count");

                if ("Present".equalsIgnoreCase(status)) {
                    totalAttend = count;
                } else if ("Absent".equalsIgnoreCase(status)) {
                    totalAbsent = count;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching attendance data: " + e.getMessage());
            return;
        }

        // Create the dataset for attendance
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Attend", totalAttend);
        dataset.setValue("Absent", totalAbsent);

        // Create the Pie Chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Attendance", // Chart title
                dataset, // Dataset
                true, // Include legend
                true, // Include tooltips
                false // Don't include URLs
        );

        // Customize the Pie Chart
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("Attend", Color.GREEN);   // Green for Attend
        plot.setSectionPaint("Absent", Color.RED);     // Red for Absent

        // Render the chart as a BufferedImage
        BufferedImage chartImage = pieChart.createBufferedImage(jPanel9.getWidth(), jPanel9.getHeight());

        // Create an ImageIcon from the BufferedImage
        ImageIcon chartIcon = new ImageIcon(chartImage);

        // Add the ImageIcon to jPanel9
        JLabel chartLabel = new JLabel(chartIcon);
        jPanel9.removeAll();  // Clear existing components
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel9.add(chartLabel, java.awt.BorderLayout.CENTER);
        jPanel9.validate();   // Refresh the panel

        // Set the text for jLabel28 (Attend) with dark green color
        jLabel29.setText("Attend: " + totalAttend);
        jLabel29.setForeground(new Color(0, 100, 0));  // Dark green color

        // Set the text for jLabel29 (Absent) with dark red color
        jLabel53.setText("Absent: " + totalAbsent);
        jLabel53.setForeground(new Color(139, 0, 0));  // Dark red color
    }

    private void loadChartIntoPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            // Get the current year
            int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

            // Execute query to get monthly total income for the current year
            ResultSet rs = MySQL.executeSearch(
                    "SELECT MONTH(payment_date) AS month, SUM(amount_paid) AS total_income "
                    + "FROM feepayments "
                    + "WHERE YEAR(payment_date) = " + currentYear + " "
                    + "GROUP BY MONTH(payment_date) "
                    + "ORDER BY MONTH(payment_date)");

            // Map of month numbers to names
            String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

            // Populate dataset with the result set
            while (rs.next()) {
                int month = rs.getInt("month");
                double totalIncome = rs.getDouble("total_income");

                // Add data to the dataset (ensure month index is valid)
                if (month >= 1 && month <= 12) {
                    dataset.addValue(totalIncome, "Income", months[month - 1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching income data: " + e.getMessage());
            return;
        }

        // Create the Bar Chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Monthly Income (" + java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) + ")", // Chart title with the current year
                "Month", // X-axis label
                "Income (USD)", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Chart orientation
                true, // Include legend
                true, // Include tooltips
                false // Don't include URLs
        );

        // Render the chart as a BufferedImage
        BufferedImage chartImage = barChart.createBufferedImage(jPanel20.getWidth(), jPanel20.getHeight());

        // Create an ImageIcon from the BufferedImage
        ImageIcon chartIcon = new ImageIcon(chartImage);

        // Add the ImageIcon to jPanel20
        JLabel chartLabel = new JLabel(chartIcon);
        jPanel20.removeAll();  // Clear existing components
        jPanel20.setLayout(new java.awt.BorderLayout());
        jPanel20.add(chartLabel, java.awt.BorderLayout.CENTER);
        jPanel20.validate();   // Refresh the panel
    }

    private void time() {

        java.lang.Runnable runnable = new java.lang.Runnable() {

            @Override

            public void run() {

                while (true) {

                    java.util.Date date1 = new java.util.Date();

                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss");

                    String finaldate = dateFormat.format(date1);
                    jLabel10.setText(finaldate);
                }
            }
        };

        java.lang.Thread thread = new java.lang.Thread(runnable);

        thread.start();

    }

    public void loadStudents(String searchTerm) {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `students` "
                    + "INNER JOIN `stream` ON `students`.`stream_stream_id` = `stream`.`stream_id` "
                    + "INNER JOIN `AL_batch` ON `students`.`AL_batch_batch_id` = `AL_batch`.`batch_id` ");

            // Get the table model and clear existing rows
            DefaultTableModel model = (DefaultTableModel) jTable13.getModel();
            model.setRowCount(0);

            // Populate table with search results
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("student_id"));
                vector.add(resultSet.getString("first_name"));
                vector.add(resultSet.getString("last_name"));
                vector.add(resultSet.getString("nic"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("stream.stream_name"));
                vector.add(resultSet.getString("AL_batch.batch_name"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTeachers(String searchTerm) {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `teachers` "
                    + "INNER JOIN `gender` ON `teachers`.`gender_id`");

            // Get the table model and clear existing rows
            DefaultTableModel model = (DefaultTableModel) jTable14.getModel();
            model.setRowCount(0);

            // Populate table with search results
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("teacher_id"));
                vector.add(resultSet.getString("first_name"));
                vector.add(resultSet.getString("last_name"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("gender.type"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLogins(String searchTerm) {
        try {

            ResultSet resultSet = MySQL.executeSearch(
                    "SELECT * FROM `users` "
                    + "INNER JOIN `gender` ON `users`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `usertypes` ON `users`.`user_type_id` = `usertypes`.`user_type_id` "
            );

            // Get the table model and clear existing rows
            DefaultTableModel model = (DefaultTableModel) jTable15.getModel();
            model.setRowCount(0);

            // Populate table with search results
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("user_id"));
                vector.add(resultSet.getString("username"));
                vector.add(resultSet.getString("first_name"));
                vector.add(resultSet.getString("last_name"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("gender.type"));
                vector.add(resultSet.getString("usertypes.user_type_name"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEmployee(String searchTerm) {
        try {

            ResultSet resultSet = MySQL.executeSearch(
                    "SELECT * FROM `employee` "
                    + "INNER JOIN `gender` ON `employee`.`gender_id` = `gender`.`id` "
                    + "INNER JOIN `employee_type` ON `employee`.`employee_type_id` = `employee_type`.`id` "
            );

            // Get the table model and clear existing rows
            DefaultTableModel model = (DefaultTableModel) jTable21.getModel();
            model.setRowCount(0);

            // Populate table with search results
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("user_id"));
                vector.add(resultSet.getString("first_name"));
                vector.add(resultSet.getString("last_name"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("nic"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("employee_type.type"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStream() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `stream`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("stream_name"));
                streamMap.put(resultSet.getString("stream_name"), resultSet.getString("stream_id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadUserProfile() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `users` WHERE `username`='" + userName + "'");

            if (resultSet.next()) {
                jTextField18.setText(resultSet.getString("first_name"));
                jTextField19.setText(resultSet.getString("last_name"));
                jPasswordField1.setText(resultSet.getString("password_hash"));
                jTextField4.setText(resultSet.getString("mobile"));
                jTextField3.setText(resultSet.getString("email"));
                jTextField5.setText(resultSet.getString("nic"));
                jTextField5.setEnabled(false);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Income table in admin reports
    private void loadIncomeTable() {
        try {

            DefaultTableModel model2 = (DefaultTableModel) jTable12.getModel();
            model2.setRowCount(0);

            ResultSet resultSet3 = MySQL.executeSearch("SELECT * FROM `feepayments` INNER JOIN `students` ON `feepayments`.`students_student_id`=`students`.`student_id`"
                    + "INNER JOIN `month` ON `feepayments`.`month_id`=`month`.`id`");

            DefaultTableModel model3 = (DefaultTableModel) jTable12.getModel();
            model3.setRowCount(0);

            while (resultSet3.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet3.getString("payment_id"));
                vector.add(resultSet3.getString("students.first_name") + " " + (resultSet3.getString("students.last_name")));
                vector.add(resultSet3.getString("month.month_name"));
                vector.add(resultSet3.getString("amount_paid"));
                model3.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getSum1();

    }

    //Load expenses table in admin report
    private void LoadExpensesTable() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_payments` INNER JOIN "
                    + "`bill_type` ON `bill_payments`.`bill_type_id`=`bill_type`.`id` "
                    + "INNER JOIN `vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id`"
                    + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`"
                    + "WHERE `payment_status_id` = '1'");

            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("bill_id"));
                vector.add(resultSet.getString("vendor.vendor_name"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("month.month_name"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getSum2();

    }

    //Load teacher salary in admin report  
    private void loadSalaryDetailsTeacher() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary`"
                    + "INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id`"
                    + "INNER JOIN `month` ON `salary`.`month_id` = `month`.`id`"
                    + "INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id`"
                    + "INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id` WHERE `employee`.`employee_type_id`='5'");

            DefaultTableModel model = (DefaultTableModel) jTable10.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.first_name") + " " + (resultSet.getString("employee.last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);

            }
//            getSum5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Load staff salary in admin report  
    private void loadSalaryDetailsStaff() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary`"
                    + "INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id`"
                    + "INNER JOIN `month` ON `salary`.`month_id` = `month`.`id`"
                    + "INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id`"
                    + "INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id` WHERE `employee`.`employee_type_id`='1' AND '2' AND '3' AND '5'");

            DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.first_name") + " " + (resultSet.getString("employee.last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);

            }
//            getSum5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LoadFessDetails in admin report 
    private void LoadFessDetails() {
        try {
            ResultSet resultSet = MySQL.executeSearch(" SELECT * FROM `feepayments` "
                    + "INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id` "
                    + "INNER JOIN `subjects` ON `feepayments`.`subjects_subject_id` = `subjects`.`subject_id` "
                    + "INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id` "
                    + "INNER JOIN `stream` ON `feepayments`.`stream_stream_id` = `stream`.`stream_id`");
            DefaultTableModel model = (DefaultTableModel) jTable17.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("payment_id"));
                vector.add(resultSet.getString("students_student_id"));
                vector.add(resultSet.getString("stream.stream_name"));
                vector.add(resultSet.getString("subjects.subject_name"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("payment_status.status"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("amount_paid"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Load dues table in admin report
    private void LoadDuesTable() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_payments` INNER JOIN `bill_type` ON "
                    + "`bill_payments`.`bill_type_id`=`bill_type`.`id` "
                    + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`"
                    + "INNER JOIN `vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id`"
                    + "WHERE `payment_status_id` = '2'");

            DefaultTableModel model = (DefaultTableModel) jTable18.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("bill_id"));
                vector.add(resultSet.getString("vendor.vendor_name"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("month.month_name"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getSum3();
    }

    private void loadActivityLog() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `system_logs`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("log_id"));
                vector.add(resultSet.getString("user_name"));
                vector.add(resultSet.getString("user_type"));
                vector.add(resultSet.getString("activity"));
                vector.add(resultSet.getString("timestamp"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchincome(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable12.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable12.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    public void searchexpense(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable9.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    public void searchsalaryteacher(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable10.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable10.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    public void searchsalarystaff(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable8.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    public void searchclassfees(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable17.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable17.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    public void searchoutstanding(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable18.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable18.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jComboBox2 = new javax.swing.JComboBox<>();
        menupanel = new javax.swing.JPanel();
        menu1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menu2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        menu3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        menu4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        menu5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        Dashboardconstantpanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        changingpanel = new javax.swing.JPanel();
        overviewpanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        studentpiclabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        profitpiclabel = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        duepiclabel = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        teacherpiclabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        subjectpiclabel = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        profilepanel = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        profilepiclabel = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel26 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        activitypanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane31 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        reportspanel = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField10 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel31 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel32 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jTextField22 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jTextField15 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jPanel34 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jTextField16 = new javax.swing.JTextField();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel35 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        managementpanel = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jTextField20 = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jButton31 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jButton38 = new javax.swing.JButton();
        jTextField24 = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable21 = new javax.swing.JTable();

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Dashboard");
        setMinimumSize(new java.awt.Dimension(700, 400));

        menupanel.setBackground(new java.awt.Color(0, 51, 102));

        menu1.setBackground(new java.awt.Color(0, 51, 102));
        menu1.setPreferredSize(new java.awt.Dimension(76, 54));
        menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu1MouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Overview");

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        menu2.setBackground(new java.awt.Color(0, 51, 102));
        menu2.setPreferredSize(new java.awt.Dimension(76, 54));
        menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu2MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Management");

        javax.swing.GroupLayout menu2Layout = new javax.swing.GroupLayout(menu2);
        menu2.setLayout(menu2Layout);
        menu2Layout.setHorizontalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu2Layout.setVerticalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        menu3.setBackground(new java.awt.Color(0, 51, 102));
        menu3.setPreferredSize(new java.awt.Dimension(76, 54));
        menu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Reports");

        javax.swing.GroupLayout menu3Layout = new javax.swing.GroupLayout(menu3);
        menu3.setLayout(menu3Layout);
        menu3Layout.setHorizontalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu3Layout.setVerticalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        menu4.setBackground(new java.awt.Color(0, 51, 102));
        menu4.setPreferredSize(new java.awt.Dimension(76, 54));
        menu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu4MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Activity Log / Backup");

        javax.swing.GroupLayout menu4Layout = new javax.swing.GroupLayout(menu4);
        menu4.setLayout(menu4Layout);
        menu4Layout.setHorizontalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menu4Layout.setVerticalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        menu5.setBackground(new java.awt.Color(0, 51, 102));
        menu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu5MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Profile");
        jLabel5.setPreferredSize(new java.awt.Dimension(76, 20));

        javax.swing.GroupLayout menu5Layout = new javax.swing.GroupLayout(menu5);
        menu5.setLayout(menu5Layout);
        menu5Layout.setHorizontalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu5Layout.setVerticalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton32.setBackground(new java.awt.Color(0, 102, 255));
        jButton32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setText("Log Out");
        jButton32.setToolTipText("");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menupanelLayout = new javax.swing.GroupLayout(menupanel);
        menupanel.setLayout(menupanelLayout);
        menupanelLayout.setHorizontalGroup(
            menupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addComponent(menu2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addComponent(menu3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addComponent(menu4, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addComponent(menu5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menupanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(menupanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jButton32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menupanelLayout.setVerticalGroup(
            menupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menupanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jButton32)
                .addGap(20, 20, 20))
        );

        Dashboardconstantpanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Admin Dashboard");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Welcome Admin,");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Date: 2024-11-29");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setText("Name");

        javax.swing.GroupLayout DashboardconstantpanelLayout = new javax.swing.GroupLayout(Dashboardconstantpanel);
        Dashboardconstantpanel.setLayout(DashboardconstantpanelLayout);
        DashboardconstantpanelLayout.setHorizontalGroup(
            DashboardconstantpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(DashboardconstantpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(719, Short.MAX_VALUE))
                    .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        DashboardconstantpanelLayout.setVerticalGroup(
            DashboardconstantpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DashboardconstantpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel44))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        overviewpanel.setMinimumSize(new java.awt.Dimension(974, 535));
        overviewpanel.setPreferredSize(new java.awt.Dimension(974, 535));

        jPanel1.setBackground(new java.awt.Color(0, 52, 101));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel52.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Total Students");
        jLabel52.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(studentpiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addContainerGap(14, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(studentpiclabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(0, 52, 101));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Profit");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(profitpiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(profitpiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 52, 101));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("DUES");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(duepiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(duepiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(0, 52, 101));
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Total Teachers");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(teacherpiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(teacherpiclabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 52, 101));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Subjects");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(subjectpiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subjectpiclabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout overviewpanelLayout = new javax.swing.GroupLayout(overviewpanel);
        overviewpanel.setLayout(overviewpanelLayout);
        overviewpanelLayout.setHorizontalGroup(
            overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewpanelLayout.createSequentialGroup()
                .addGroup(overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(overviewpanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(overviewpanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(overviewpanelLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        overviewpanelLayout.setVerticalGroup(
            overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewpanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addGroup(overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(overviewpanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addGroup(overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(overviewpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );

        jPanel17.setPreferredSize(new java.awt.Dimension(825, 476));

        profilepiclabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profilepiclabel.setMaximumSize(new java.awt.Dimension(256, 256));
        profilepiclabel.setMinimumSize(new java.awt.Dimension(256, 256));

        jButton28.setBackground(new java.awt.Color(0, 52, 101));
        jButton28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Upload Image");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("First Name");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Last Name");

        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Email");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Password");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Mobile No.");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("NIC");

        jButton29.setBackground(new java.awt.Color(0, 52, 101));
        jButton29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("Change Password");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Profile Setting");

        jButton30.setBackground(new java.awt.Color(0, 52, 101));
        jButton30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("Update Profile");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profilepiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 89, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel24)
                    .addComponent(jLabel26))
                .addGap(24, 24, 24)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23)
                    .addComponent(jLabel25)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel23))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel26))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(profilepiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(82, 82, 82)
                .addComponent(jButton30)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout profilepanelLayout = new javax.swing.GroupLayout(profilepanel);
        profilepanel.setLayout(profilepanelLayout);
        profilepanelLayout.setHorizontalGroup(
            profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
            .addGroup(profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE))
        );
        profilepanelLayout.setVerticalGroup(
            profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(profilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(0, 52, 101));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 52, 101));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Search");

        jTextField1.setText("Search NIC");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Log ID", "User Name", "User Role", "Activity ", "Timestamp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane31.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Activity Log", jPanel6);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setIconTextGap(0);
        jButton4.setMinimumSize(new java.awt.Dimension(286, 283));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Restore");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Backup");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap(268, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Backup", jPanel28);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout activitypanelLayout = new javax.swing.GroupLayout(activitypanel);
        activitypanel.setLayout(activitypanelLayout);
        activitypanelLayout.setHorizontalGroup(
            activitypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 937, Short.MAX_VALUE)
            .addGroup(activitypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        activitypanelLayout.setVerticalGroup(
            activitypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
            .addGroup(activitypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.setPreferredSize(new java.awt.Dimension(825, 476));

        jButton10.setBackground(new java.awt.Color(0, 52, 101));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Print");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "First Name", "Last Name", "NIC", "Mobile", "Date of Birth", "Gurdian Mobile", "Enrollment Date", "Gender", "Address line 1", "Address line2", "Batch", "Stream"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jLabel27.setText("Search");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton10)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10)
                    .addComponent(jLabel27))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Student Enrollment Reports", jPanel15);

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(0, 52, 101));
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Print");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule ID", "Schedule Date", "A/L Batch", "Subject", "Teacher", "Start Time", "End Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel45.setText("Search");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13)
                    .addComponent(jLabel45))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Class Schedule Report", jPanel4);

        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 52, 101));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Print");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject ID", "Subject Name", "Schedule ID", "Schedule Date", "Stream", "A/L Batch", "Teacher "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel46.setText("Search");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 570, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jLabel46))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        jTabbedPane2.addTab("Subject Management Report", jPanel5);

        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(0, 52, 101));
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Print");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "First Name", "Last name", "Gender", "Mobile", "Barcode ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jLabel33.setText("Search");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 570, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15)
                    .addComponent(jLabel33))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );

        jTabbedPane2.addTab("Teacher List", jPanel16);

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(0, 52, 101));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Print");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Month", "A/L Batch", "Subject", "Teacher", "Total Records", "Tota_Present", "Total_Absent", "Present %", "Absent %"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jLabel47.setText("Search");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGap(0, 570, Short.MAX_VALUE)
                        .addComponent(jButton17)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17)
                    .addComponent(jLabel47))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        jTabbedPane2.addTab("Class Attendance Reports", jPanel29);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Academic", jPanel14);

        jButton18.setBackground(new java.awt.Color(0, 52, 101));
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Print");

        jButton19.setBackground(new java.awt.Color(0, 52, 101));
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Search");

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField13KeyReleased(evt);
            }
        });

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payment id", "Paid Student", "Month", "Fee Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(jTable12);

        jLabel18.setText("Select Month ");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", " " }));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                        .addComponent(jButton18)
                        .addGap(30, 30, 30)
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Monthly Income Report", jPanel31);

        jButton20.setBackground(new java.awt.Color(0, 52, 101));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Print");

        jButton21.setBackground(new java.awt.Color(0, 52, 101));
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Search");

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
        });

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Vendor", "Settle Date", "Total Amount", "Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable9);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Bill Payments", "Salary payments", "Maintenance" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel11.setText("Select Month");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                        .addComponent(jButton20)
                        .addGap(18, 18, 18)
                        .addComponent(jButton21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Monthly Expense Report", jPanel32);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee Id", "Employee Name", "Base Salary", "Net Amount", "Month", "Payment date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTable10);

        jButton34.setBackground(new java.awt.Color(0, 52, 101));
        jButton34.setForeground(new java.awt.Color(255, 255, 255));
        jButton34.setText("Print");

        jButton35.setBackground(new java.awt.Color(0, 52, 101));
        jButton35.setForeground(new java.awt.Color(255, 255, 255));
        jButton35.setText("Search");

        jTextField22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField22KeyReleased(evt);
            }
        });

        jLabel49.setText("Select Month");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
                        .addComponent(jButton34)
                        .addGap(18, 18, 18)
                        .addComponent(jButton35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Teacher ", jPanel25);

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee Id", "Employee Name", "Base Salary", "Net Amount", "Month", "Payment Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable8);

        jButton22.setBackground(new java.awt.Color(0, 52, 101));
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Print");

        jButton23.setBackground(new java.awt.Color(0, 52, 101));
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Search");

        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField15KeyReleased(evt);
            }
        });

        jLabel50.setText("Select Month");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                        .addComponent(jButton22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );

        jTabbedPane6.addTab("Staff", jPanel26);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Salary Payment Report", jPanel33);

        jButton24.setBackground(new java.awt.Color(0, 52, 101));
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Print");

        jButton25.setBackground(new java.awt.Color(0, 52, 101));
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("Search");

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField16KeyReleased(evt);
            }
        });

        jTable17.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payment Id", "St Id", "Stream", "Subject", "Date", "Status", "Month", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(jTable17);

        jLabel48.setText("Select Month");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton24)
                        .addGap(28, 28, 28)
                        .addComponent(jButton25)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        jTabbedPane3.addTab("Class Fee Payment Report", jPanel34);

        jButton26.setBackground(new java.awt.Color(0, 52, 101));
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Print");

        jButton27.setBackground(new java.awt.Color(0, 52, 101));
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Search");

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField17KeyReleased(evt);
            }
        });

        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Vendor", "Payment Due Date", "Total Amount", "Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane18.setViewportView(jTable18);

        jLabel51.setText("Select Month");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton26)
                        .addGap(32, 32, 32)
                        .addComponent(jButton27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Outsanding Fee Report", jPanel35);

        jButton36.setBackground(new java.awt.Color(0, 52, 101));
        jButton36.setForeground(new java.awt.Color(255, 255, 255));
        jButton36.setText("Print");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Description", "Current Month", "Previous Month", "Budgeted Amount", "Variance / Due Amount", "%Difference"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jTable16);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton36)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Profit Loss Report", jPanel12);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane3))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Financial", jPanel30);

        javax.swing.GroupLayout reportspanelLayout = new javax.swing.GroupLayout(reportspanel);
        reportspanel.setLayout(reportspanelLayout);
        reportspanelLayout.setHorizontalGroup(
            reportspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
        );
        reportspanelLayout.setVerticalGroup(
            reportspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportspanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 51, 102));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Search");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "First Name", "Last Name", "NIC", "Mobile Number", "Stream", "AL-Batch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(jTable13);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Students", jPanel13);

        jButton8.setBackground(new java.awt.Color(0, 51, 102));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Search");

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "First Name", "Last Name", "Mobile Number", "Gender"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(jTable14);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Teachers", jPanel18);

        jButton9.setBackground(new java.awt.Color(0, 51, 102));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Search");

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "User Name", "First Name", "Last Name", "Email", "Gender", "User Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable15MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(jTable15);

        jButton31.setBackground(new java.awt.Color(0, 51, 102));
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("Add / Update");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel42.setText("Login Credentials");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton31)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel42)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Logins", jPanel19);

        jButton38.setBackground(new java.awt.Color(0, 51, 102));
        jButton38.setForeground(new java.awt.Color(255, 255, 255));
        jButton38.setText("Search");

        jButton39.setBackground(new java.awt.Color(0, 51, 102));
        jButton39.setForeground(new java.awt.Color(255, 255, 255));
        jButton39.setText("Add / Update");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jTable21.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "First Name", "Last Name", "Email", "NIC", "Mobile Number", "Employee Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane21.setViewportView(jTable21);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(0, 551, Short.MAX_VALUE)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton38)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Employee Details", jPanel27);

        javax.swing.GroupLayout managementpanelLayout = new javax.swing.GroupLayout(managementpanel);
        managementpanel.setLayout(managementpanelLayout);
        managementpanelLayout.setHorizontalGroup(
            managementpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managementpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        managementpanelLayout.setVerticalGroup(
            managementpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        javax.swing.GroupLayout changingpanelLayout = new javax.swing.GroupLayout(changingpanel);
        changingpanel.setLayout(changingpanelLayout);
        changingpanelLayout.setHorizontalGroup(
            changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 949, Short.MAX_VALUE)
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(profilepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(activitypanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(reportspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(managementpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(overviewpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        changingpanelLayout.setVerticalGroup(
            changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(profilepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(activitypanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(reportspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(managementpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(overviewpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menupanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Dashboardconstantpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changingpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menupanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Dashboardconstantpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changingpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(true);
        managementpanel.setVisible(false);
        reportspanel.setVisible(false);
        activitypanel.setVisible(false);
        profilepanel.setVisible(false);

        menu1.setBackground(new Color(5, 93, 165));
        menu2.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));

    }//GEN-LAST:event_menu1MouseClicked

    private void menu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(false);
        managementpanel.setVisible(true);
        reportspanel.setVisible(false);
        activitypanel.setVisible(false);
        profilepanel.setVisible(false);

        menu2.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
    }//GEN-LAST:event_menu2MouseClicked

    private void menu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(false);
        managementpanel.setVisible(false);
        reportspanel.setVisible(true);
        activitypanel.setVisible(false);
        profilepanel.setVisible(false);

        menu3.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
    }//GEN-LAST:event_menu3MouseClicked

    private void menu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(false);
        managementpanel.setVisible(false);
        reportspanel.setVisible(false);
        activitypanel.setVisible(true);
        profilepanel.setVisible(false);

        menu4.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
    }//GEN-LAST:event_menu4MouseClicked

    private void menu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu5MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(false);
        managementpanel.setVisible(false);
        reportspanel.setVisible(false);
        activitypanel.setVisible(false);
        profilepanel.setVisible(true);

        menu5.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
    }//GEN-LAST:event_menu5MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());

            Image image = imageIcon.getImage().getScaledInstance(jLabel1.getWidth(), profilepiclabel.getHeight(), Image.SCALE_SMOOTH);

            String path = selectedFile.getAbsolutePath();
            profilepiclabel.setIcon(new ImageIcon(image));
            String imgPath = path;

        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        String firstName = jTextField18.getText();
        String lastName = jTextField19.getText();
        String email = jTextField3.getText();
        String mobile = jTextField4.getText();

        if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter First Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Email", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid Mobile Number");
        } else {

            try {

                MySQL.executeIUD("UPDATE `users` SET `first_name`='" + firstName + "', `last_name`='" + lastName + "',`email`='" + email + "',"
                        + "`mobile`='" + mobile + "' WHERE `username`='" + userName + "'");

                JOptionPane.showMessageDialog(this, "Update Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                AdminUserSession.getInstance().setName(firstName + lastName);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        this.dispose();
        otherEmployees FR = new otherEmployees();
        FR.setVisible(true);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        this.dispose();
        loginRegistration AR = new loginRegistration();
        AR.setVisible(true);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jTable15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable15MouseClicked

        int row = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {

            int row1 = jTable1.getSelectedRow();
            String email1 = String.valueOf(jTable1.getValueAt(row1, 0));

            AFaddress addressView = new AFaddress();
            addressView.setVisible(true);
        }
    }//GEN-LAST:event_jTable15MouseClicked

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        String stream = String.valueOf(jComboBox1.getSelectedItem());

        try {
            if (!stream.equals("Select")) {
                // Define the SQL query
                String query = "SELECT *"
                        + "FROM students "
                        + "INNER JOIN stream ON students.stream_stream_id = stream.stream_id "
                        + "INNER JOIN AL_batch ON students.AL_batch_batch_id = AL_batch.batch_id "
                        + "WHERE stream.stream_name = '" + stream + "'";

                // Execute the query and get the result set
                ResultSet resultSet = MySQL.executeSearch(query);

                // Fetch the table model of jTable13
                DefaultTableModel tableModel = (DefaultTableModel) jTable13.getModel();
                tableModel.setRowCount(0); // Clear existing rows

                // Populate the table using ResultSet
                while (resultSet.next()) {
                    Object[] rowData = {
                        resultSet.getInt("student_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("nic"),
                        resultSet.getString("mobile"),
                        resultSet.getString("stream.stream_name"),
                        resultSet.getString("AL_batch.batch_name")
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        String password = String.valueOf(jPasswordField1.getPassword());

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `users` WHERE `username`='" + userName + "'");

            if (resultSet.next()) {

                if (password.equals(resultSet.getString("password_hash"))) {
                    JOptionPane.showMessageDialog(this, "Password is entered previously!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("UPDATE `users` SET `password_hash`='" + password + "' "
                            + "WHERE `username`='" + userName + "'");
                    JOptionPane.showMessageDialog(this, "Password Changed!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed

        // System Log
        int response = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            // System Log
            try {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `users` INNER JOIN `usertypes` ON "
                        + "`users`.`user_type_id`=`usertypes`.`user_type_id` WHERE `username` = '" + AdminUserSession.getInstance().getUsername() + "'");

                if (resultSet.next()) {
                    String description = "Admin Log Out";
                    String user = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                    String userType = resultSet.getString("usertypes.user_type_name");

                    MySQL.executeIUD("INSERT INTO `system_logs`(`timestamp`,`activity`,`user_name`,`user_type`)"
                            + "VALUES ('" + SystemDateTime + "','" + description + "','" + user + "','" + userType + "')");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Perform logout and navigation
            AdminUserSession.getInstance().logout();
            this.dispose();
            userSelection us = new userSelection();
            us.setVisible(true);
        }

    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        //print Report button enrollment:
        try {
//            long invoiceid = System.currentTimeMillis();
//            jLabel84.setText(String.valueOf(invoiceid));
//            String EmployeeUserName = jLabel85.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/StudentEnrollmentTest.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/StudentEnrollmentTest.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", dateTime);
            parameters.put("IMAGE_PATH", imagePath);
//            params.put("Parameter2", Department);
//            params.put("Parameter3", Basesalary);
            // Data source
            if (jTable6.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable6.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // Class Schedule Report:

        try {
//            long invoiceid = System.currentTimeMillis();
//            jLabel84.setText(String.valueOf(invoiceid));
//            String EmployeeUserName = jLabel85.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/ClassSheduleTest.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/ClassSheduleTest.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", dateTime);
            parameters.put("IMAGE_PATH", imagePath);
//            params.put("Parameter2", Department);
//            params.put("Parameter3", Basesalary);
            // Data source
            if (jTable3.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable3.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Subject Management Report:

        try {
//            long invoiceid = System.currentTimeMillis();
//            jLabel84.setText(String.valueOf(invoiceid));
//            String EmployeeUserName = jLabel85.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/SubjectManagementReportTest.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/SubjectManagementReportTest.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", dateTime);
            parameters.put("IMAGE_PATH", imagePath);
//            params.put("Parameter2", Department);
//            params.put("Parameter3", Basesalary);
            // Data source
            if (jTable4.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable4.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // Class Attendence Report :

        try {
//            long invoiceid = System.currentTimeMillis();
//            jLabel84.setText(String.valueOf(invoiceid));
//            String EmployeeUserName = jLabel85.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();

            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/StudentAttendenceTest.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/StudentAttendenceTest.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", dateTime);
            parameters.put("IMAGE_PATH", imagePath);
//            params.put("Parameter2", Department);
//            params.put("Parameter3", Basesalary);
            // Data source
            if (jTable7.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable7.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextField13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyReleased
        String searchString = jTextField13.getText();
        searchincome(searchString);
    }//GEN-LAST:event_jTextField13KeyReleased

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
        String searchString = jTextField14.getText();
        searchexpense(searchString);
    }//GEN-LAST:event_jTextField14KeyReleased

    private void jTextField22KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField22KeyReleased
        String searchString = jTextField22.getText();
        searchsalaryteacher(searchString);
    }//GEN-LAST:event_jTextField22KeyReleased

    private void jTextField15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyReleased
        String searchString = jTextField15.getText();
        searchsalarystaff(searchString);
    }//GEN-LAST:event_jTextField15KeyReleased

    private void jTextField16KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyReleased
        String searchString = jTextField16.getText();
        searchclassfees(searchString);
    }//GEN-LAST:event_jTextField16KeyReleased

    private void jTextField17KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyReleased
        String searchString = jTextField17.getText();
        searchoutstanding(searchString);
    }//GEN-LAST:event_jTextField17KeyReleased

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // search

        try {
            String searchText = jTextField12.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable7.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable7.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField12KeyReleased

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // Teacher Enrollment Report:

        try {
//            long invoiceid = System.currentTimeMillis();
//            jLabel84.setText(String.valueOf(invoiceid));
//            String EmployeeUserName = jLabel85.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/TeacherEnrollmentTest.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/TeacherEnrollmentTest.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", dateTime);
            parameters.put("IMAGE_PATH", imagePath);
//            params.put("Parameter2", Department);
//            params.put("Parameter3", Basesalary);
            // Data source
            if (jTable5.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable5.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // search

        try {
            String searchText = jTextField11.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable5.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        // search

        try {
            String searchText = jTextField10.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable4.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // search

        try {
            String searchText = jTextField2.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable3.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        // search

        try {
            String searchText = jTextField9.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable6.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable6.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField9KeyReleased

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // profit loss Report:

        try {
//            long invoiceid = System.currentTimeMillis();
//            jLabel84.setText(String.valueOf(invoiceid));
//            String EmployeeUserName = jLabel85.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/ProfitLostTest.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/ProfitLostTest.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", dateTime);
            parameters.put("IMAGE_PATH", imagePath);
//            params.put("Parameter2", Department);
//            params.put("Parameter3", Basesalary);
            // Data source
            if (jTable16.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable16.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton36ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//
//        FlatMacLightLaf.setup();
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AdminDashboard().setVisible(true);
//
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboardconstantpanel;
    private javax.swing.JPanel activitypanel;
    private javax.swing.JPanel changingpanel;
    private javax.swing.JLabel duepiclabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable21;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel managementpanel;
    private javax.swing.JPanel menu1;
    private javax.swing.JPanel menu2;
    private javax.swing.JPanel menu3;
    private javax.swing.JPanel menu4;
    private javax.swing.JPanel menu5;
    private javax.swing.JPanel menupanel;
    private javax.swing.JPanel overviewpanel;
    private javax.swing.JPanel profilepanel;
    private javax.swing.JLabel profilepiclabel;
    private javax.swing.JLabel profitpiclabel;
    private javax.swing.JPanel reportspanel;
    private javax.swing.JLabel studentpiclabel;
    private javax.swing.JLabel subjectpiclabel;
    private javax.swing.JLabel teacherpiclabel;
    // End of variables declaration//GEN-END:variables

    private void loadAdminStudentEnrollmentReport() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `students` "
                    + "INNER JOIN `gender` ON `students`.`gender_id`=`gender`.`id` "
                    + "INNER JOIN `AL_batch` ON `students`.`AL_batch_batch_id`=`AL_batch`.`batch_id` "
                    + "INNER JOIN `stream` ON `students`.`stream_stream_id`=`stream`.`stream_id` ");

            DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector vector = new Vector();
                vector.add(resultSet.getString("student_id"));
                vector.add(resultSet.getString("first_name"));
                vector.add(resultSet.getString("last_name"));
                vector.add(resultSet.getString("nic"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("dob"));
                vector.add(resultSet.getString("guardian_mobile"));
                vector.add(resultSet.getString("enrollment_date"));
                vector.add(resultSet.getString("gender.type"));
                vector.add(resultSet.getString("address_line_1"));
                vector.add(resultSet.getString("address_line_2"));
                vector.add(resultSet.getString("AL_batch.batch_name"));
                vector.add(resultSet.getString("stream.stream_name"));
                //  vector.add(resultSet.getString("barcode_id"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadAdminSheduleForReport() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `schedule` INNER JOIN `AL_batch`"
                    + "ON `schedule`.`AL_batch_batch_id` = `AL_batch`.`batch_id` INNER JOIN `subjects` "
                    + "ON `schedule`.`subject_id` = `subjects`.`subject_id` INNER JOIN `teachers` "
                    + "ON `schedule`.`teacher_id` = `teachers`.`teacher_id`");

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("schedule_id"));
                vector.add(resultSet.getString("sheduled_date"));
                vector.add(resultSet.getString("AL_batch.batch_name"));
                vector.add(resultSet.getString("subjects.subject_name"));
                vector.add(resultSet.getString("teachers.first_name") + " " + resultSet.getString("teachers.last_name"));
                vector.add(resultSet.getString("start_time"));
                vector.add(resultSet.getString("end_time"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAdminSheduleForSubject() {

        try {

//            
            ResultSet resultSet = MySQL.executeSearch("SELECT *FROM subjects INNER JOIN stream_subject ON"
                    + " stream_subject.subjects_subject_id=subjects.subject_id INNER JOIN"
                    + " AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id INNER JOIN "
                    + "stream ON stream.stream_id=stream_subject.stream_stream_id INNER JOIN "
                    + "schedule ON schedule.schedule_id=stream_subject.id INNER JOIN "
                    + "teachers ON teachers.teacher_id=schedule.teacher_id");

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("subject_id"));
                vector.add(resultSet.getString("subjects.subject_name"));
                vector.add(resultSet.getString("schedule_id"));
                vector.add(resultSet.getString("sheduled_date"));
                vector.add(resultSet.getString("stream_name"));
                vector.add(resultSet.getString("AL_batch.batch_name"));
                vector.add(resultSet.getString("teachers.first_name") + " " + resultSet.getString("teachers.last_name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adjustTableHeightToFitRows(JTable table) {
        int totalRowHeight = table.getRowCount() * table.getRowHeight(); // Calculate total height
        Dimension tableSize = table.getPreferredSize(); // Get current table size
        tableSize.height = totalRowHeight; // Set new height
        table.setPreferredSize(tableSize); // Apply new size
        table.revalidate(); // Refresh table layout
    }

    private void loadProfitLoss() {
        // Row headers as descriptions
        String[] rowHeaders = {
            "Total Class Fees",
            "Total Registration Fees",
            "Total Revenue",
            "Total Salaries",
            "Total Utilities",
            "Total Expenses",
            "Net Profit / Loss"
        };

        // Table column headers
        String[] columnHeaders = {
            "Description", "Current Month", "Previous Month", "Budgeted Amount", "Variance / Due Amount", "% Change"
        };

        // Rows to highlight with bold text
        Set<String> boldHeaders = new HashSet<>(Arrays.asList(
                "Total Revenue",
                "Total Expenses",
                "Net Profit / Loss"
        ));

        try {
            // Initialize table model
            DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);
            jTable16.setModel(tableModel);

            // SQL queries for data
            String[] queries = {
                "SELECT SUM(amount_paid) AS total FROM feepayments WHERE fee_id != 7 AND MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())", // Total Class Fees
                "SELECT SUM(amount_paid) AS total FROM feepayments WHERE fee_id = 7 AND MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())", // Total Registration Fees
                "SELECT SUM(amount_paid) AS total FROM feepayments WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())", // Total Revenue
                "SELECT SUM(net_amount) AS total FROM salary WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())", // Total Salaries
                "SELECT SUM(amount) AS total FROM bill_payments WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())", // Total Utilities
                "SELECT SUM(amount) + (SELECT SUM(net_amount) FROM salary WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())) AS total FROM bill_payments WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())", // Total Expenses
                "SELECT ((SELECT SUM(amount_paid) FROM feepayments WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())) - (SELECT SUM(amount) FROM bill_payments WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE())) - (SELECT SUM(net_amount) FROM salary WHERE MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE()))) AS total" // Net Profit/Loss
            };

            // Retrieve budgeted amounts
            double budgetClassFees = 0;
            double budgetRegFees = 0;
            double budgetSalary = 0;
            double budgetUtilities = 20000.0; // Fixed value
            double budgetTotalRevenue;
            double budgetTotalExpenses;

            ResultSet rs = MySQL.executeSearch("SELECT SUM(amount) AS total FROM feestructure WHERE subjects_subject_id != 8");
            if (rs.next()) {
                budgetClassFees = rs.getDouble("total");
            }
            rs.close();

            rs = MySQL.executeSearch("SELECT SUM(amount) AS total FROM feestructure WHERE subjects_subject_id = 8");
            if (rs.next()) {
                budgetRegFees = rs.getDouble("total");
            }
            rs.close();

            rs = MySQL.executeSearch("SELECT COUNT(user_id) * 50000 AS total FROM employee");
            if (rs.next()) {
                budgetSalary = rs.getDouble("total");
            }
            rs.close();

            budgetTotalRevenue = budgetClassFees + budgetRegFees;
            budgetTotalExpenses = budgetSalary + budgetUtilities;

            // Load data into the table
            for (int i = 0; i < rowHeaders.length; i++) {
                rs = MySQL.executeSearch(queries[i]);
                if (rs.next()) {
                    double currentMonth = rs.getDouble("total");
                    double previousMonth = 0; // Placeholder for the previous month's data
                    double budgeted = 0;

                    // Assign budgeted data
                    if (i == 0) {
                        budgeted = budgetClassFees; // Total Class Fees
                    } else if (i == 1) {
                        budgeted = budgetRegFees; // Total Registration Fees
                    } else if (i == 2) {
                        budgeted = budgetTotalRevenue; // Total Revenue
                    } else if (i == 3) {
                        budgeted = budgetSalary; // Total Salaries
                    } else if (i == 4) {
                        budgeted = budgetUtilities; // Total Utilities
                    } else if (i == 5) {
                        budgeted = budgetTotalExpenses; // Total Expenses
                    }
                    double variance = currentMonth - budgeted;
                    double percentageChange = (budgeted != 0) ? (variance / budgeted) * 100 : 0;

                    // Add row to table
                    tableModel.addRow(new Object[]{
                        rowHeaders[i],
                        currentMonth,
                        previousMonth,
                        budgeted,
                        variance,
                        percentageChange
                    });
                }
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Highlight Description Column
        jTable16.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (boldHeaders.contains(value)) {
                    component.setFont(component.getFont().deriveFont(Font.BOLD));
                }
                component.setBackground(new Color(173, 216, 230)); // Set background color
                return component;
            }
        });
    }

    private void loadAdminStudentAttendanceReportTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable7.getModel();
            dtm.setRowCount(0);

            ResultSet rs = model.MySQL.executeSearch("SELECT YEAR(student_attendance.attendance_date) "
                    + "AS year, MONTH(student_attendance.attendance_date) AS month, "
                    + "AL_batch.batch_name, subjects.subject_name, CONCAT(teachers.first_name, ' ', "
                    + "teachers.last_name) AS teacher_name, COUNT(*) AS total_records, "
                    + "SUM(CASE WHEN student_attendance.status = 'Present' THEN 1 ELSE 0 END) AS"
                    + " total_present, SUM(CASE WHEN student_attendance.status = 'Absent' THEN 1 ELSE "
                    + "0 END) AS total_absent, ROUND((SUM(CASE WHEN student_attendance.status = 'Present' "
                    + "THEN 1 ELSE 0 END) * 100.0 / COUNT(*)), 2) AS percentage_present, "
                    + "ROUND((SUM(CASE WHEN student_attendance.status = 'Absent' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)"
                    + "), 2) AS percentage_absent FROM student_attendance INNER JOIN `schedule` ON "
                    + "`schedule`.schedule_id = student_attendance.schedule_id INNER JOIN stream_subject ON "
                    + "stream_subject.id = `schedule`.stream_subject_id INNER JOIN subjects ON"
                    + " subjects.subject_id = stream_subject.subjects_subject_id INNER JOIN AL_batch ON "
                    + "AL_batch.batch_id = stream_subject.AL_batch_batch_id INNER JOIN teachers_has_stream_subject"
                    + " ON teachers_has_stream_subject.stream_subject_id = stream_subject.stream_stream_id "
                    + "INNER JOIN teachers ON teachers.teacher_id = teachers_has_stream_subject.teachers_teacher_id"
                    + " GROUP BY YEAR(student_attendance.attendance_date), MONTH(student_attendance.attendance_date), "
                    + "AL_batch.batch_name, subjects.subject_name, teacher_name ORDER BY year, month");

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("year"));
                v.add(rs.getString("month"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("subject_name"));
                v.add(rs.getString("teacher_name"));
                v.add(rs.getString("total_records"));
                v.add(rs.getString("total_absent"));
                v.add(rs.getString("total_present"));
                v.add(rs.getString("percentage_absent"));
                v.add(rs.getString("percentage_present"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
    }

    private void loadAdminTeacherEnrollmenrt() {

        try {
//            ResultSet rs = MySQL.executeSearch("SELECT *From `teachers` INNER JOIN `gender` ON `teachers`.`gender_id`= `gender`.`id`"
//                    + "INNER JOIN `teachers_has_stream_subject` ON `teachers`.`teacher_id` = `teachers_has_stream_subject`.`id`"
//                    + "INNER JOIN `stream_subject` ON `stream_subject`.`id` = `teachers_has_stream_subject`.`id`");
//            
//            

            ResultSet rs = MySQL.executeSearch("SELECT *From teachers INNER JOIN gender ON teachers.gender_id= gender.id INNER JOIN teachers_has_stream_subject ON teachers.teacher_id = teachers_has_stream_subject.id INNER JOIN stream_subject ON stream_subject.id = teachers_has_stream_subject.id INNER JOIN stream ON stream.stream_id=stream_subject.stream_stream_id INNER JOIN subjects ON subjects.subject_id=stream_subject.subjects_subject_id");

            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("teacher_id"));
                v.add(rs.getString("first_name"));
                v.add(rs.getString("last_name"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("mobile"));
                v.add(rs.getString("email"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("enrollment_date"));
                v.add(rs.getString("type"));
                v.add(rs.getString("address_line_2"));
                v.add(rs.getString("address_line_2"));
                v.add(rs.getString("stream_name"));
                v.add(rs.getString("subject_name"));

                model.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
