package gui;

import java.util.Date;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.FinancialUserSession;
import model.MySQL;
import model.SalaryCalculation;
import net.sf.jasperreports.engine.JREmptyDataSource;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.logging.*;
import javax.swing.table.TableModel;

/**
 *
 * @author Dell
 */
public class FinancialDashboard extends javax.swing.JFrame {

    private String selectedImagePath; // Global variable to store the selected image path
    private static String userName = FinancialUserSession.getInstance().getUsername();
    private static String SystemDateTime;
    private static HashMap<String, String> loadStreamMap = new HashMap<>();
    private static HashMap<String, String> loadSubjectMap = new HashMap<>();
    private static HashMap<String, String> loadStudentMap = new HashMap<>();
    private static HashMap<String, String> loadStatusMap = new HashMap<>();
    private static HashMap<String, String> feeMap = new HashMap<>();
    private static HashMap<String, String> LoadBillType = new HashMap<>();
    private static HashMap<String, String> LoadVendorMap = new HashMap<>();
    private static HashMap<String, String> LoadstatusMap = new HashMap<>();
    private static HashMap<String, String> LoadEmployeeType = new HashMap<>();
    private static HashMap<String, String> LoadMonthMap = new HashMap<>();
    private static HashMap<String, String> LoadStatusmap = new HashMap<>();
    private static HashMap<String, String> MonthMap = new HashMap<>();
    private static HashMap<String, String> MonthMapFeePayment = new HashMap<>();
    private static HashMap<String, String> LoadMonthBillPaymentMap = new HashMap<>();
    public static Logger logger = Logger.getLogger("Finance");

    private void image() {

        FlatSVGIcon icon1 = new FlatSVGIcon("resources//LOGOWHITE.svg", jLabel6.getWidth(), jLabel6.getHeight());
//        FlatSVGIcon icon2 = new FlatSVGIcon("resources//backup.svg", jButton3.getWidth(), jButton3.getHeight());
//        FlatSVGIcon icon3 = new FlatSVGIcon("resources//restore.svg", jButton4.getWidth(), jButton4.getHeight());
//        FlatSVGIcon icon4 = new FlatSVGIcon("resources/student.svg", studentpiclabel.getWidth(), studentpiclabel.getHeight());
//        FlatSVGIcon icon5 = new FlatSVGIcon("resources/teacher.svg", teacherpiclabel.getWidth(), teacherpiclabel.getHeight());
//        FlatSVGIcon icon6 = new FlatSVGIcon("resources/books.svg", subjectpiclabel.getWidth(), subjectpiclabel.getHeight());
//        FlatSVGIcon icon7 = new FlatSVGIcon("resources/profit.svg", profitpiclabel.getWidth(), profitpiclabel.getHeight());
//        FlatSVGIcon icon8 = new FlatSVGIcon("resources/pie-graph.svg", chartpiclabel.getWidth(), chartpiclabel.getHeight());
//        FlatSVGIcon icon9 = new FlatSVGIcon("resources/dues.svg", duepiclabel.getWidth(), duepiclabel.getHeight());
        FlatSVGIcon icon10 = new FlatSVGIcon("resources/profileImage.svg", profilepiclabel.getWidth(), profilepiclabel.getHeight());

//        jButton3.setIcon(icon2);
//        jButton4.setIcon(icon3);
//        studentpiclabel.setIcon(icon4);
//        teacherpiclabel.setIcon(icon5);
//        subjectpiclabel.setIcon(icon6);
//        profitpiclabel.setIcon(icon7);
//        chartpiclabel.setIcon(icon8);
//        duepiclabel.setIcon(icon9);
        profilepiclabel.setIcon(icon10);

        jLabel6.setIcon(icon1);
    }

    /**
     * Creates new form NewDashboard
     */
    public FinancialDashboard() {
        initComponents();
        image();
        loadStream();
        loadSubject();
        loadFeeStructure();
        loadFeePayment();
        loadViewFeePayment();
        loadStudent();
        loadPendingFees();
        loadStatus();
        loadFeeId();
        time();

        loadDues();
        loadoverview();
        loadoverviewAnual();
        loadProgressBar();
        loadChartIntoPanel();

        loadAcademicSalary();
        loadFinanceSalaryDetails();
        loadTeachersSalary();
        loadMaintenanceSalary();
        loadEmployee();
        loadBaseSalary();
        LoadEmployeeType();
        loadMonth();
        LoadPaymentSatus();
        Loadpaysheet();
        loadUserProfile();
        loadExpenses();

        jLabel27.setText(FinancialUserSession.getInstance().getName());

        //EPF and ETF presantages
        //None Editable/Visible Fields
        jFormattedTextField4.setEditable(false);
        financialoverviewpanel.setVisible(true);
        salarymanagementpanel.setVisible(false);
        billspanel.setVisible(false);
        feepanel.setVisible(false);
        financialreportpanel.setVisible(false);
        financialprofilepanel.setVisible(false);
        jLabel85.setVisible(false);
        jLabel86.setVisible(false);
        jLabel88.setVisible(false);
        jLabel89.setVisible(false);

        //Employee Username
        jLabel27.setText(Financelogin.getEmployeeuserName());

        menu1.setBackground(new Color(5, 93, 165));

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        jTable3.setDefaultRenderer(Object.class, render);
        jTable4.setDefaultRenderer(Object.class, render);
        jTable5.setDefaultRenderer(Object.class, render);
        jTable6.setDefaultRenderer(Object.class, render);
        jTable1.setDefaultRenderer(Object.class, render);
        jTable18.setDefaultRenderer(Object.class, render);
        jTable19.setDefaultRenderer(Object.class, render);
        jTable17.setDefaultRenderer(Object.class, render);
        jTable11.setDefaultRenderer(Object.class, render);
        jTable12.setDefaultRenderer(Object.class, render);
        jTable13.setDefaultRenderer(Object.class, render);
        jTable15.setDefaultRenderer(Object.class, render);
        jTable16.setDefaultRenderer(Object.class, render);
        jTable14.setDefaultRenderer(Object.class, render);
        jTable20.setDefaultRenderer(Object.class, render);
        //Bill Payments
        LoadBillTypes();
        LoadVendors();
        LoadPaymentStatus();
        loadBillPayments();

        //Financial Reports
        //MonthLoad
        ReportLoadMonth1();
        ReportLoadMonth2();
        ReportLoadMonth3();
        ReportLoadMonth4();
        ReportLoadMonth5();
        ReportLoadMonth6();
//        ReportLoadMonth7();
        ReportLoadMonth8();
        ReportLoadMonth9();
        ReportLoadMonth10();
        ReportLoadMonth11();
        loadIncomeTable();
        LoadFessDetails();
        LoadDuesTable();
        LoadExpensesTable();
        loadSalaryDetails();
        //Profit cal
        LoadExpensesTableProfitCal();
        loadIncomeTableProfitCal();

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        updateDateTime();

        try {
            FileHandler fileHandler = new FileHandler("Finance.app", true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchFee(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable9.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    private void updateDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        SystemDateTime = formattedDateTime;
    }

    //   Financial Overview
    private void loadProgressBar() {
        int targetIncome = 150000;
        Thread progressThread = new Thread(() -> {
            try {
                ResultSet resultSet = MySQL.executeSearch(
                        "SELECT SUM(amount_paid) AS totalIncome FROM feepayments"
                );
                double currentIncome = 0;
                if (resultSet.next()) {
                    currentIncome = resultSet.getDouble("totalIncome");
                }

                int percentage = (int) ((currentIncome / targetIncome) * 100);
                percentage = Math.min(percentage, 100);

                for (int i = 0; i <= percentage; i++) {
                    jProgressBar1.setValue(i);

                    if (i <= 25) {
                        jLabel111.setText("Patan Gaththa Witari Bn");
                    } else if (i <= 50) {
                        jLabel111.setText("Tawa Tikai Putha");
                    } else if (i <= 75) {
                        jLabel111.setText("Thaniyen me Gema Gahuwe");
                    } else {
                        jLabel111.setText("Easy Bng!");
                    }

                    Thread.sleep(50);
                }

                jProgressBar1.setValue(percentage);
//            loadingText.setText("Progress loaded successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        progressThread.start();
    }

    private void loadoverview() {
        ResultSet resultSet = null;
        try {
            resultSet = MySQL.executeSearch("SELECT SUM(amount_paid) AS total_paid FROM feepayments "
                    + " INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id` "
                    + " WHERE `payment_status`.`status` = 'paid' AND MONTH(payment_date) = MONTH(CURDATE()) AND YEAR(payment_date) = YEAR(CURDATE());");

            if (resultSet.next()) {
                String sum = resultSet.getString("total_paid");
                if (sum == null) {
                    sum = "0.00";
                }
                jLabel19.setText(sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jLabel19.setText("Error");
        }
    }

    private void loadoverviewAnual() {
        ResultSet resultSet = null;
        try {
            resultSet = MySQL.executeSearch("SELECT SUM(amount_paid) AS total_paid FROM `feepayments` "
                    + "INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id`"
                    + "WHERE `payment_status`.`status` = 'paid' AND YEAR(payment_date) = YEAR(CURDATE());");

            if (resultSet.next()) {
                String sum = resultSet.getString("total_paid");
                if (sum == null) {
                    sum = "0.00";
                }
                jLabel45.setText(sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jLabel45.setText("Error");
        }
    }

    private void loadChartIntoPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            // Execute query to get monthly total income for the current year
            ResultSet rs = MySQL.executeSearch("SELECT MONTH(payment_date) AS month, SUM(amount_paid) AS total_income "
                    + "FROM feepayments "
                    + "WHERE YEAR(payment_date) = YEAR(CURDATE()) " // Filter for the current year
                    + "GROUP BY MONTH(payment_date) "
                    + "ORDER BY MONTH(payment_date)");

            String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

            while (rs.next()) {
                int month = rs.getInt("month");
                double totalIncome = rs.getDouble("total_income");

                if (month >= 1 && month <= 12) {
                    dataset.addValue(totalIncome, "Income", months[month - 1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching income data: " + e.getMessage());
            return;
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Monthly Income - Current Year",
                "Month",
                "Income (LKR)",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(0.02);

        Font axisFont = new Font("SansSerif", Font.PLAIN, 10);
        plot.getDomainAxis().setLabelFont(axisFont);
        plot.getDomainAxis().setTickLabelFont(axisFont);
        plot.getRangeAxis().setLabelFont(axisFont);
        plot.getRangeAxis().setTickLabelFont(axisFont);

        BufferedImage chartImage = barChart.createBufferedImage(jPanel9.getWidth(), jPanel9.getHeight());

        ImageIcon chartIcon = new ImageIcon(chartImage);

        JLabel chartLabel = new JLabel(chartIcon);
        jPanel9.removeAll();
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel9.add(chartLabel, java.awt.BorderLayout.CENTER);
        jPanel9.validate();
    }

    private void loadExpenses() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_payments` "
                    + "INNER JOIN `bill_type` ON `bill_payments`.`bill_type_id`= `bill_type`.`id`"
                    + "ORDER BY `bill_payments`.`payment_date` DESC LIMIT 10");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("bill_id"));
                vector.add(resultSet.getString("bill_type.bill_type"));
                vector.add(resultSet.getString("amount"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadDues() {
        try {
            ResultSet resultSet1 = MySQL.executeSearch(
                    "SELECT `employee`.`first_name`, `salary`.`net_amount` AS name1 "
                    + "FROM `salary` "
                    + "INNER JOIN `employee` ON `employee`.`user_id` = `salary`.`employee_user_id` "
                    + "WHERE `payment_status_id` = '2'"
            );

            ResultSet resultSet2 = MySQL.executeSearch(
                    "SELECT `bill_type`.`bill_type`, `bill_payments`.`amount` AS name2 "
                    + "FROM `bill_payments` "
                    + "INNER JOIN `bill_type` ON `bill_payments`.`bill_type_id` = `bill_type`.`id` "
                    + "WHERE `payment_status_id` = '2'"
            );

            DefaultTableModel model = (DefaultTableModel) jTable21.getModel();
            model.setRowCount(0);

            while (resultSet1.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet1.getString("first_name"));
                vector.add(resultSet1.getString("name1"));
                model.addRow(vector);
            }

            while (resultSet2.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet2.getString("bill_type"));
                vector.add(resultSet2.getString("name2"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//   End Financial Overview
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

    private void loadAcademicSalary() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary`"
                    + "INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id`"
                    + "INNER JOIN `month` ON `salary`.`month_id` = `month`.`id`"
                    + "INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id`"
                    + "INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                    + "WHERE `employee`.`employee_type_id` = '1'");

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("first_name") + " " + (resultSet.getString("last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // salary management  

    private void loadFinanceSalaryDetails() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary`"
                    + "INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id`"
                    + "INNER JOIN `month` ON `salary`.`month_id` = `month`.`id`"
                    + "INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id`"
                    + "INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                    + "WHERE `employee`.`employee_type_id` = '2'");

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("first_name") + " " + (resultSet.getString("last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // salary management   

    private void loadTeachersSalary() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary`"
                    + "INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id`"
                    + "INNER JOIN `month` ON `salary`.`month_id` = `month`.`id`"
                    + "INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id`"
                    + "INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                    + "WHERE `employee`.`employee_type_id` = '3'");

            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("first_name") + " " + (resultSet.getString("last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // salary management   
    private void loadMaintenanceSalary() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary`"
                    + "INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id`"
                    + "INNER JOIN `month` ON `salary`.`month_id` = `month`.`id`"
                    + "INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id`"
                    + "INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                    + "WHERE `employee`.`employee_type_id` = '4'");

            DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("first_name") + " " + (resultSet.getString("last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Salary manage
    private void loadBaseSalary() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary_details` INNER JOIN `employee_type`"
                    + "ON `salary_details`.`employee_type_id`=`employee_type`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable19.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("base_salary"));
                vector.add(resultSet.getString("employee_type.type"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Bill payments
    public void LoadBillTypes() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_type`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("bill_type"));
                LoadBillType.put(resultSet.getString("bill_type"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox7.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Bill Payments
    private void LoadVendors() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `vendor`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("vendor_name"));
                LoadVendorMap.put(resultSet.getString("vendor_name"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox11.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Bill Payments
    private void LoadPaymentStatus() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payment_status`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                LoadstatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox12.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Bill Payments table
    private void loadBillPayments() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_payments` INNER JOIN"
                    + "`bill_type` ON `bill_payments`.`bill_type_id`=`bill_type`.`id` INNER JOIN"
                    + "`vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id` INNER JOIN"
                    + "`payment_status` ON `bill_payments`.`payment_status_id`=`payment_status`.`id`"
                    + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("bill_id"));
                vector.add(resultSet.getString("bill_type.bill_type"));
                vector.add(resultSet.getString("vendor.vendor_name"));
                vector.add(resultSet.getString("description"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("payment_status.status"));
                vector.add(resultSet.getString("month.month_name"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployee() {
        try {
            ResultSet resultSet = MySQL.executeSearch(" SELECT * FROM `employee` INNER JOIN `salary_details` ON `employee`.`salary_details_id`=`salary_details`.`id` INNER JOIN `employee_type`"
                    + "ON `employee`.`employee_type_id`=`employee_type`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable18.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("user_id"));
                vector.add(resultSet.getString("employee_type.type"));
                vector.add(resultSet.getString("first_name") + " " + (resultSet.getString("last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //LoadRmployeeType in salary manage
    private void LoadEmployeeType() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee_type`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                LoadEmployeeType.put(resultSet.getString("type"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox14.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable18.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable18.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    public void search1(String str) {
        DefaultTableModel model = (DefaultTableModel) jTable17.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable17.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }

    //Load Month in salary manage
    private void loadMonth() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");
            Vector<String> vector = new Vector<>();
            vector.add("Select Month");

            while (resultSet.next()) {
                vector.add(resultSet.getString("month_name"));
                MonthMap.put(resultSet.getString("month_name"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel<>(vector);
            jComboBox16.setModel(model);
            jComboBox24.setModel(model);
            jComboBox5.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Load status in salary manage
    private void LoadPaymentSatus() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payment_status`");
            Vector<String> vector = new Vector<>();
            vector.add("Select Status");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                LoadStatusmap.put(resultSet.getString("status"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel<>(vector);
            jComboBox17.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Loadpaysheet() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary` INNER JOIN `salary_details` ON `salary`.`salary_details_id`=`salary_details`.`id` INNER JOIN `month` ON `salary`.`month_id`=`month`.`id` INNER JOIN"
                    + " `payment_status` ON `salary`.`payment_status_id`=`payment_status`.`id` ");

            DefaultTableModel model = (DefaultTableModel) jTable17.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee_user_id"));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                vector.add(resultSet.getString("epf_etf_balance"));

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
                loadStreamMap.put(resultSet.getString("stream_name"), resultSet.getString("stream_id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox26.setModel(model);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubject() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `subjects`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("subject_name"));
                loadSubjectMap.put(resultSet.getString("subject_name"), resultSet.getString("subject_id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
            jComboBox25.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadFeeStructure() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `feestructure` INNER JOIN `subjects` "
                    + "ON `feestructure`.subjects_subject_id = `subjects`.`subject_id` "
                    + "INNER JOIN `stream` ON `feestructure`.`stream_stream_id` = `stream`.`stream_id`");

            DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("fee_id"));
                vector.add(resultSet.getString("stream.stream_name"));
                vector.add(resultSet.getString("subjects.subject_name"));
                vector.add(resultSet.getString("amount"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFeePayment() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `feepayments` INNER JOIN `students` "
                    + "ON `feepayments`.students_student_id = `students`.`student_id` "
                    + "INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id` "
                    + "INNER JOIN `feestructure` ON `feepayments`.fee_id = `feestructure`.`fee_id` "
                    + "INNER JOIN `subjects` ON `feestructure`.subjects_subject_id = `subjects`.`subject_id` "
                    + "INNER JOIN `stream` ON `feestructure`.`stream_stream_id` = `stream`.`stream_id`"
            );

            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("payment_id"));
                vector.add(resultSet.getString("first_name") + " " + (resultSet.getString("last_name")));
                vector.add(resultSet.getString("stream_name"));
                vector.add(resultSet.getString("subject_name"));
                vector.add(resultSet.getString("amount_paid"));
                vector.add(resultSet.getString("status"));
                vector.add(resultSet.getString("payment_date"));
                model.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadViewFeePayment() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `feepayments` INNER JOIN `students` "
                    + "ON `feepayments`.students_student_id = `students`.`student_id` "
                    + "INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id` "
                    + "INNER JOIN `feestructure` ON `feepayments`.fee_id = `feestructure`.`fee_id` "
                    + "INNER JOIN `subjects` ON `feestructure`.subjects_subject_id = `subjects`.`subject_id` "
                    + "INNER JOIN `stream` ON `feestructure`.`stream_stream_id` = `stream`.`stream_id`"
                    + "INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id`"
            );

            DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("payment_id"));
                vector.add(resultSet.getString("students.nic"));
                vector.add(resultSet.getString("payment_status.status"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("amount_paid"));
                vector.add(resultSet.getString("subjects.subject_name"));
                vector.add(resultSet.getString("stream.stream_name"));
                vector.add(resultSet.getString("description"));
                vector.add(resultSet.getString("month.month_name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStudent() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `students`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                vector.add(fullName);
                loadStudentMap.put(fullName, resultSet.getString("student_id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox22.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadPendingFees() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `feepayments` INNER JOIN `students` "
                    + "ON `feepayments`.students_student_id = `students`.`student_id` "
                    + "INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id` "
                    + "INNER JOIN `feestructure` ON `feepayments`.fee_id = `feestructure`.`fee_id`"
                    + "INNER JOIN `subjects` ON `feestructure`.subjects_subject_id = `subjects`.`subject_id` "
                    + "INNER JOIN `stream` ON `feestructure`.`stream_stream_id` = `stream`.`stream_id` "
                    + "WHERE `status` = 'unpaid'");

            DefaultTableModel model = (DefaultTableModel) jTable10.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("payment_id"));
                vector.add(resultSet.getString("students.first_name") + " " + resultSet.getString("students.last_name"));
                vector.add(resultSet.getString("payment_status_id"));
                vector.add(resultSet.getString("amount_paid"));
                vector.add(resultSet.getString("stream.stream_name"));
                vector.add(resultSet.getString("subjects.subject_name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStatus() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payment_status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                loadStatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox21.setModel(model);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadFeeId() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `feestructure` "
                    + "INNER JOIN `subjects` ON `feestructure`.subjects_subject_id = `subjects`.`subject_id` "
                    + "INNER JOIN `stream` ON `feestructure`.`stream_stream_id` = `stream`.`stream_id`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Fee");

            while (resultSet.next()) {
                String displayText = resultSet.getString("stream.stream_name") + " -> " + resultSet.getString("subjects.subject_name");
                String feeId = resultSet.getString("fee_id");

                vector.add(displayText);
                feeMap.put(displayText, feeId);
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            jComboBox23.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadUserProfile() {
        try {

            // Use LEFT JOIN to handle null img_path_id
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `users` "
                    + "LEFT JOIN `img_path` ON `users`.`img_path_id`=`img_path`.`id` "
                    + "WHERE `username`='" + userName + "'");

            if (resultSet.next()) {
                jTextField18.setText(resultSet.getString("first_name"));
                jTextField19.setText(resultSet.getString("last_name"));
                jPasswordField1.setText(resultSet.getString("password_hash"));
                jTextField4.setText(resultSet.getString("mobile"));
                jTextField3.setText(resultSet.getString("email"));
                jTextField5.setText(resultSet.getString("nic"));
                jTextField5.setEnabled(false);

                // Load the profile image
                FlatSVGIcon defaultIcon = new FlatSVGIcon("resources/profileImage.svg",
                        profilepiclabel.getWidth(), profilepiclabel.getHeight());

                String imgPath = resultSet.getString("path");
                if (imgPath != null && !imgPath.isEmpty()) {
                    File imgFile = new File(imgPath);
                    if (imgFile.exists()) {
                        // Load and scale the image
                        ImageIcon imageIcon = new ImageIcon(imgFile.getAbsolutePath());
                        Image image = imageIcon.getImage().getScaledInstance(profilepiclabel.getWidth(),
                                profilepiclabel.getHeight(), Image.SCALE_SMOOTH);
                        profilepiclabel.setIcon(new ImageIcon(image));
                    } else {
                        profilepiclabel.setIcon(defaultIcon); // Use default icon if file doesn't exist
                    }
                } else {
                    profilepiclabel.setIcon(defaultIcon); // Use default icon if imgPath is null
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Income table in finnacial report
    private void loadIncomeTable() {
        try {

            DefaultTableModel model2 = (DefaultTableModel) jTable11.getModel();
            model2.setRowCount(0);

            ResultSet resultSet3 = MySQL.executeSearch("SELECT * FROM `feepayments` INNER JOIN `students` ON `feepayments`.`students_student_id`=`students`.`student_id`"
                    + "INNER JOIN `month` ON `feepayments`.`month_id`=`month`.`id`");

            DefaultTableModel model3 = (DefaultTableModel) jTable11.getModel();
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
        getSum1();

    }

    // LoadFessDetails in financial report 
    private void LoadFessDetails() {
        try {
            ResultSet resultSet = MySQL.executeSearch(" SELECT * FROM `feepayments` "
                    + "INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id` "
                    + "INNER JOIN `subjects` ON `feepayments`.`subjects_subject_id` = `subjects`.`subject_id` "
                    + "INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id` "
                    + "INNER JOIN `stream` ON `feepayments`.`stream_stream_id` = `stream`.`stream_id`");
            DefaultTableModel model = (DefaultTableModel) jTable16.getModel();
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
            getSum6();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Load dues table in financila report
    private void LoadDuesTable() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_payments` INNER JOIN `bill_type` ON "
                    + "`bill_payments`.`bill_type_id`=`bill_type`.`id` "
                    + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`"
                    + "INNER JOIN `vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id`"
                    + "WHERE `payment_status_id` = '2'");

            DefaultTableModel model = (DefaultTableModel) jTable13.getModel();
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
        getSum3();
    }

    //Load expenses table in financila report
    private void LoadExpensesTable() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_payments` INNER JOIN "
                    + "`bill_type` ON `bill_payments`.`bill_type_id`=`bill_type`.`id` "
                    + "INNER JOIN `vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id`"
                    + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`"
                    + "WHERE `payment_status_id` = '1'");

            DefaultTableModel model = (DefaultTableModel) jTable12.getModel();
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
        getSum2();

    }

    //Load salary in financial report  
    private void loadSalaryDetails() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary`"
                    + "INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id`"
                    + "INNER JOIN `month` ON `salary`.`month_id` = `month`.`id`"
                    + "INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id`"
                    + "INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`");

            DefaultTableModel model = (DefaultTableModel) jTable15.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.first_name") + " " + (resultSet.getString("employee.last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);

            }
            getSum5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //<--Profit calculation(Income and expense tables load)-->
    //Load expenses table in financila report
    private void LoadExpensesTableProfitCal() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_payments` INNER JOIN "
                    + "`bill_type` ON `bill_payments`.`bill_type_id`=`bill_type`.`id` "
                    + "INNER JOIN `vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id`"
                    + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`"
                    + "WHERE `payment_status_id` = '1'");

            DefaultTableModel model = (DefaultTableModel) jTable20.getModel();
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
        getSumProfitCal2();
        SumOfProfit();
    }

    //Income table in finnacial report
    private void loadIncomeTableProfitCal() {
        try {

            ResultSet resultSet3 = MySQL.executeSearch("SELECT * FROM `feepayments` INNER JOIN `students` ON `feepayments`.`students_student_id`=`students`.`student_id`"
                    + "INNER JOIN `month` ON `feepayments`.`month_id`=`month`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable14.getModel();
            model.setRowCount(0);

            while (resultSet3.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet3.getString("payment_id"));
                vector.add(resultSet3.getString("students.first_name") + " " + (resultSet3.getString("students.last_name")));
                vector.add(resultSet3.getString("month.month_name"));
                vector.add(resultSet3.getString("amount_paid"));
                model.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSumProfitCal1();
        SumOfProfit();
    }

    public void getSumProfitCal1() {
        int sum = 0;
        for (int i = 0; i < jTable14.getRowCount(); i++) {
            sum += Double.parseDouble(jTable14.getValueAt(i, 3).toString());
        }
        jLabel119.setText(Integer.toString(sum));
    }

    public void getSumProfitCal2() {
        int sum = 0;
        for (int i = 0; i < jTable20.getRowCount(); i++) {
            sum += Double.parseDouble(jTable20.getValueAt(i, 3).toString());
        }
        jLabel120.setText(Integer.toString(sum));
    }

    public void SumOfProfit() {
        try {
            double total = 0;
            double incomeValue = 0;
            double expenseValue = 0;

            // Retrieve text
            String income = jLabel119.getText().trim();
            String expense = jLabel120.getText().trim();

            // Check for empty strings and handle them
            if (!income.isEmpty()) {
                incomeValue = Double.parseDouble(income);
            }
            if (!expense.isEmpty()) {
                expenseValue = Double.parseDouble(expense);
            }

            // Calculate total
            total = incomeValue - expenseValue;

            // Convert result to String and display it
            String profit = String.valueOf(total);
            jLabel121.setText(profit);

        } catch (NumberFormatException e) {
            // Handle invalid input
            System.out.println("Invalid input. Please enter numeric values.");
            e.printStackTrace();
        }
    }

    //<--Profit calculation(Income and expense tables load)-->
    //<--Financial report month load-->
    //1
    private void ReportLoadMonth1() {
        try {
            ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector1 = new Vector<>();
            vector1.add("All Months");

            while (resultSet1.next()) {
                vector1.add(resultSet1.getString("month_name"));
                LoadMonthMap.put(resultSet1.getString("month_name"), resultSet1.getString("id"));

            }
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector1);
            jComboBox8.setModel(model1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    private void ReportLoadMonth2() {
        try {
            ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector1 = new Vector<>();
            vector1.add("All Months");

            while (resultSet1.next()) {
                vector1.add(resultSet1.getString("month_name"));
                LoadMonthMap.put(resultSet1.getString("month_name"), resultSet1.getString("id"));

            }
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector1);

            jComboBox9.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3
    private void ReportLoadMonth3() {
        try {
            ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector1 = new Vector<>();
            vector1.add("All Months");

            while (resultSet1.next()) {
                vector1.add(resultSet1.getString("month_name"));
                LoadMonthMap.put(resultSet1.getString("month_name"), resultSet1.getString("id"));

            }
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector1);
            jComboBox20.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4
    private void ReportLoadMonth4() {
        try {
            ResultSet resultSet1 = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector1 = new Vector<>();
            vector1.add("All Months");

            while (resultSet1.next()) {
                vector1.add(resultSet1.getString("month_name"));
                LoadMonthMap.put(resultSet1.getString("month_name"), resultSet1.getString("id"));

            }
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector1);
            jComboBox10.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5
    private void ReportLoadMonth5() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector1 = new Vector<>();
            vector1.add("All Months");

            while (resultSet.next()) {
                vector1.add(resultSet.getString("month_name"));
                LoadMonthMap.put(resultSet.getString("month_name"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector1);
            jComboBox4.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6
    private void ReportLoadMonth6() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector = new Vector<>();
            vector.add("All Months");

            while (resultSet.next()) {
                vector.add(resultSet.getString("month_name"));
                LoadMonthMap.put(resultSet.getString("month_name"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox6.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //7
//    private void ReportLoadMonth7() {
//        try {
//            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");
//
//            Vector<String> vector = new Vector<>();
//            vector.add("All Months");
//
//            while (resultSet.next()) {
//                vector.add(resultSet.getString("month_name"));
//                LoadMonthMap.put(resultSet.getString("month_name"), resultSet.getString("id"));
//
//            }
//            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
//            jComboBox28.setModel(model);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //Salary department month
    //8
    private void ReportLoadMonth8() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector = new Vector<>();
            vector.add("All Months");

            while (resultSet.next()) {
                vector.add(resultSet.getString("month_name"));
                LoadMonthMap.put(resultSet.getString("month_name"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox29.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //9
    private void ReportLoadMonth9() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector = new Vector<>();
            vector.add("All Months");

            while (resultSet.next()) {
                vector.add(resultSet.getString("month_name"));
                LoadMonthMap.put(resultSet.getString("month_name"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox30.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //10
    private void ReportLoadMonth10() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector = new Vector<>();
            vector.add("All Months");

            while (resultSet.next()) {
                vector.add(resultSet.getString("month_name"));
                LoadMonthMap.put(resultSet.getString("month_name"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox31.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //11
    private void ReportLoadMonth11() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `month`");

            Vector<String> vector = new Vector<>();
            vector.add("All Months");

            while (resultSet.next()) {
                vector.add(resultSet.getString("month_name"));
                LoadMonthMap.put(resultSet.getString("month_name"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox32.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //<--Financial report month load-->
    //<--Calculate total-->
    //1
    public void getSum1() {
        int sum = 0;
        for (int i = 0; i < jTable11.getRowCount(); i++) {
            sum += Double.parseDouble(jTable11.getValueAt(i, 3).toString());
        }
        jLabel122.setText(Integer.toString(sum));
    }

    //2
    public void getSum2() {
        int sum = 0;
        for (int i = 0; i < jTable12.getRowCount(); i++) {
            sum += Double.parseDouble(jTable12.getValueAt(i, 3).toString());
        }
        jLabel123.setText(Integer.toString(sum));
    }

    //3
    public void getSum3() {
        int sum = 0;
        for (int i = 0; i < jTable13.getRowCount(); i++) {
            sum += Double.parseDouble(jTable13.getValueAt(i, 3).toString());
        }
        jLabel124.setText(Integer.toString(sum));
    }

    //4
    public void getSum4() {
        int sum = 0;
        for (int i = 0; i < jTable15.getRowCount(); i++) {
            sum += Double.parseDouble(jTable15.getValueAt(i, 3).toString());
        }
        jLabel112.setText(Integer.toString(sum));
    }

    //5
    public void getSum5() {
        int sum = 0;
        for (int i = 0; i < jTable15.getRowCount(); i++) {
            sum += Double.parseDouble(jTable15.getValueAt(i, 3).toString());
        }
        jLabel125.setText(Integer.toString(sum));
    }

    //6
    public void getSum6() {
        int sum = 0;
        for (int i = 0; i < jTable16.getRowCount(); i++) {
            sum += Double.parseDouble(jTable16.getValueAt(i, 7).toString());
        }
        jLabel126.setText(Integer.toString(sum));
    }
    //<--Calculate total-->

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox13 = new javax.swing.JComboBox<>();
        jLabel76 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox<>();
        jLabel94 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
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
        menu6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        Dashboardconstantpanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        changingpanel = new javax.swing.JPanel();
        financialoverviewpanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel53 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable21 = new javax.swing.JTable();
        feepanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jButton33 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jComboBox21 = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox<>();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton34 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jComboBox23 = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jComboBox24 = new javax.swing.JComboBox<>();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jComboBox25 = new javax.swing.JComboBox<>();
        jComboBox26 = new javax.swing.JComboBox<>();
        jButton36 = new javax.swing.JButton();
        jLabel127 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel115 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jLabel116 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        billspanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton19 = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        salarymanagementpanel = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable19 = new javax.swing.JTable();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jComboBox14 = new javax.swing.JComboBox<>();
        jButton18 = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel96 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jComboBox29 = new javax.swing.JComboBox<>();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel97 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jComboBox30 = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel87 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel117 = new javax.swing.JLabel();
        jComboBox31 = new javax.swing.JComboBox<>();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel101 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel118 = new javax.swing.JLabel();
        jComboBox32 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel21 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jTextField10 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel39 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel136 = new javax.swing.JLabel();
        financialreportpanel = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton27 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jLabel122 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jLabel84 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jLabel123 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jLabel90 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel124 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jLabel105 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jLabel91 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jLabel93 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel125 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jLabel92 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel126 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        financialprofilepanel = new javax.swing.JPanel();
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

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel76.setText("jLabel76");

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel94.setText("jLabel94");

        jLabel98.setText("jLabel98");

        jLabel99.setText("jLabel99");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Financial Dashboard");
        setMinimumSize(new java.awt.Dimension(700, 400));

        menupanel.setBackground(new java.awt.Color(0, 51, 102));

        menu1.setBackground(new java.awt.Color(0, 51, 102));
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
            .addGroup(menu1Layout.createSequentialGroup()
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
        menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu2MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fees Management");

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
        menu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bill Payments");

        javax.swing.GroupLayout menu3Layout = new javax.swing.GroupLayout(menu3);
        menu3.setLayout(menu3Layout);
        menu3Layout.setHorizontalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu3Layout.createSequentialGroup()
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
        menu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu4MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Salary Management");

        javax.swing.GroupLayout menu4Layout = new javax.swing.GroupLayout(menu4);
        menu4.setLayout(menu4Layout);
        menu4Layout.setHorizontalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu4Layout.setVerticalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
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
        jLabel5.setText("Financial Reports");

        javax.swing.GroupLayout menu5Layout = new javax.swing.GroupLayout(menu5);
        menu5.setLayout(menu5Layout);
        menu5Layout.setHorizontalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu5Layout.setVerticalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        menu6.setBackground(new java.awt.Color(0, 51, 102));
        menu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu6MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Profile");

        javax.swing.GroupLayout menu6Layout = new javax.swing.GroupLayout(menu6);
        menu6.setLayout(menu6Layout);
        menu6Layout.setHorizontalGroup(
            menu6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menu6Layout.setVerticalGroup(
            menu6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton26.setBackground(new java.awt.Color(0, 102, 255));
        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Log Out");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menupanelLayout = new javax.swing.GroupLayout(menupanel);
        menupanel.setLayout(menupanelLayout);
        menupanelLayout.setHorizontalGroup(
            menupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menupanelLayout.createSequentialGroup()
                .addGroup(menupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menupanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menupanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton26)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        menupanelLayout.setVerticalGroup(
            menupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menupanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton26)
                .addGap(19, 19, 19))
        );

        Dashboardconstantpanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Financial Dashboard");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Welcome Financial Officer,");

        jLabel10.setBackground(new java.awt.Color(0, 52, 101));
        jLabel10.setText("Date: 2024-11-29");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Name");

        jLabel102.setText(" ");

        javax.swing.GroupLayout DashboardconstantpanelLayout = new javax.swing.GroupLayout(Dashboardconstantpanel);
        Dashboardconstantpanel.setLayout(DashboardconstantpanelLayout);
        DashboardconstantpanelLayout.setHorizontalGroup(
            DashboardconstantpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(DashboardconstantpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 437, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
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
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel102)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        financialoverviewpanel.setPreferredSize(new java.awt.Dimension(935, 509));
        financialoverviewpanel.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(0, 52, 101));
        jPanel7.setPreferredSize(new java.awt.Dimension(231, 140));

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/calendar (1)_1.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Monthly Income");

        jLabel19.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 52, 101));
        jPanel8.setPreferredSize(new java.awt.Dimension(237, 140));

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/revenue.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Anual Income");

        jLabel45.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(0, 52, 101));
        jPanel13.setPreferredSize(new java.awt.Dimension(237, 140));

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/targeted.png"))); // NOI18N

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setValue(70);

        jLabel53.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Target Income -150000");

        jLabel111.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(0, 52, 101));

        jLabel63.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Expenses");

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/arrow-down (1).png"))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel63)
                .addContainerGap(146, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );

        jPanel9.setBackground(new java.awt.Color(0, 52, 101));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(0, 52, 101));

        jLabel67.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Dues");

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/due-date (1).png"))); // NOI18N

        jTable21.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane23.setViewportView(jTable21);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                .addContainerGap())
        );

        financialoverviewpanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        feepanel.setMinimumSize(new java.awt.Dimension(934, 509));
        feepanel.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setText("Fees Structure");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Stream", "Subject", "Fee Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jLabel31.setText("Stream");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel32.setText("Subject");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel34.setText("Welcome :");

        jLabel38.setText("Enter Amount");

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setText("Print");

        jButton33.setText("Clrar All");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane7)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, 0, 184, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 69, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addGap(16, 16, 16)
                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(285, 429, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addGap(4, 4, 4))
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton3)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7)
                            .addComponent(jButton33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Fees Structure", jPanel11);

        jTabbedPane4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane4StateChanged(evt);
            }
        });
        jTabbedPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane4MouseClicked(evt);
            }
        });

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Student", "Stream", "Subject", "Amount", " Status", "Payment Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTable9);

        jButton8.setBackground(new java.awt.Color(0, 52, 101));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Print Fee Payment");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jLabel134.setText("Search");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("View Fee Payment", jPanel18);

        jLabel44.setText("Student Name");

        jLabel46.setText("NIC");

        jLabel47.setText("Stream");

        jLabel48.setText("Subject");

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NIC", "Status", "Payment Date", "Amount", "Subject", "Stream", "Description", "Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable8);

        jTextField2.setText(" ");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "paid", "unpaid" }));
        jComboBox21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox21ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(0, 51, 102));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Add");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel69.setText("Amount");

        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox22.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox22ItemStateChanged(evt);
            }
        });
        jComboBox22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox22ActionPerformed(evt);
            }
        });

        jLabel112.setText("Description");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane24.setViewportView(jTextArea3);

        jButton34.setBackground(new java.awt.Color(0, 51, 102));
        jButton34.setForeground(new java.awt.Color(255, 255, 255));
        jButton34.setText("Clear");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jTextField7.setText(" ");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "paid", "unpaid" }));
        jComboBox23.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox23ItemStateChanged(evt);
            }
        });
        jComboBox23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox23ActionPerformed(evt);
            }
        });

        jLabel49.setText("Fee Id");

        jComboBox24.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "paid", "unpaid" }));
        jComboBox24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox24ActionPerformed(evt);
            }
        });

        jLabel113.setText("Month");

        jLabel114.setText("Status");

        jComboBox25.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "paid", "unpaid" }));
        jComboBox25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox25ActionPerformed(evt);
            }
        });

        jComboBox26.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "paid", "unpaid" }));
        jComboBox26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox26ActionPerformed(evt);
            }
        });

        jButton36.setBackground(new java.awt.Color(0, 51, 102));
        jButton36.setForeground(new java.awt.Color(255, 255, 255));
        jButton36.setText("Update");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel127.setText("Double click to delete");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel127)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane9)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel114))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox21, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jComboBox23, 0, 147, Short.MAX_VALUE)
                                    .addComponent(jComboBox22, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel69)
                                                .addComponent(jLabel113))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jComboBox24, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                            .addComponent(jLabel48)
                                            .addGap(18, 18, 18)
                                            .addComponent(jComboBox25, 0, 157, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel112)
                                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel44)
                                        .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel46)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48)
                                    .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel114))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel113))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel112)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jButton14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton34)
                                .addGap(6, 6, 6))
                            .addComponent(jScrollPane24))))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel127)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Add Fee Payment", jPanel16);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Student", "Status", "Amount", "Stream", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable10);

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel51.setText("Update Status");

        jLabel54.setText("Student Name");

        jLabel56.setText("Status");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton35.setBackground(new java.awt.Color(0, 52, 101));
        jButton35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton35.setForeground(new java.awt.Color(255, 255, 255));
        jButton35.setText("Update");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane11)
                        .addContainerGap())))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton35)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel115, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Pending Fee Manage", jPanel19);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        jTabbedPane1.addTab("Fee Payment", jPanel15);

        feepanel.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        billspanel.setPreferredSize(new java.awt.Dimension(944, 576));
        billspanel.setLayout(new java.awt.BorderLayout());

        jButton4.setBackground(new java.awt.Color(0, 52, 101));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Add Vendor");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 52, 101));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add Bill Payment");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton40.setBackground(new java.awt.Color(0, 52, 101));
        jButton40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton40.setForeground(new java.awt.Color(255, 255, 255));
        jButton40.setText("Remove");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 52, 101));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton41.setBackground(new java.awt.Color(0, 52, 101));
        jButton41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton41.setForeground(new java.awt.Color(255, 255, 255));
        jButton41.setText("Print");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jLabel18.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane21.setViewportView(jTextArea1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Bill Type", "Vendor", "Description", "Amount", "Date", "Status", "Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Date : ");

        jLabel22.setText("Vendor");

        jLabel30.setText("Status");

        jLabel33.setText("Amount");

        jButton1.setBackground(new java.awt.Color(0, 52, 101));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add Bill Type");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox7MouseClicked(evt);
            }
        });
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel35.setText("Select Bill Type");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setText("Welcome :");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel37.setText("Bill Payments");

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox11MouseClicked(evt);
            }
        });

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jButton19.setBackground(new java.awt.Color(0, 52, 101));
        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Reset");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel88.setText(" ");

        jLabel89.setText(" ");

        jLabel13.setText(" ");

        jLabel128.setText("Month");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(jComboBox7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel35))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox11, 0, 103, Short.MAX_VALUE)
                                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(jComboBox12, 0, 121, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jButton41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel88)
                            .addComponent(jLabel89)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel35))
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel22)
                                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jLabel30)
                                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4)))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel128)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton40)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton41)
                            .addComponent(jButton19))))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addContainerGap())
        );

        billspanel.add(jPanel6, java.awt.BorderLayout.CENTER);

        salarymanagementpanel.setPreferredSize(new java.awt.Dimension(934, 500));
        salarymanagementpanel.setLayout(new java.awt.BorderLayout());

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel70.setText("Salary Management");

        jLabel85.setText(" ");

        jLabel86.setText(" ");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70)
                .addGap(257, 257, 257)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jLabel85)
                    .addComponent(jLabel86))
                .addGap(15, 15, 15))
        );

        salarymanagementpanel.add(jPanel24, java.awt.BorderLayout.PAGE_START);

        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jTable19.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Base Salary", "Department"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable19MouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(jTable19);

        jLabel73.setText("Base Salary");

        jLabel74.setText("Department");

        jFormattedTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jButton20.setBackground(new java.awt.Color(0, 51, 101));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Add");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(0, 51, 101));
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Update");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(0, 51, 101));
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Reset");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton18.setBackground(new java.awt.Color(0, 51, 101));
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Print");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox14, 0, 175, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel73))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Manage Salary Details", jPanel4);

        jTabbedPane3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane3StateChanged(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Paysheet ID", "User Name", "Base Salary", "Net amount", "Payment Date", "Month", "Status"
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

        jLabel96.setText("Print academic payment details -->");

        jButton11.setBackground(new java.awt.Color(0, 52, 101));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Print Report");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel41.setText("Select Month");

        jComboBox29.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox29.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox29ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel96)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel41)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96)
                    .addComponent(jLabel41)
                    .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Academic", jPanel29);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Paysheet ID", "User Name", "Base Salary", "Net amount", "Payment Date", "Month", "Status"
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

        jLabel97.setText("Print financial payment details -->");

        jButton10.setBackground(new java.awt.Color(0, 52, 101));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Print Report");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel55.setText("Select Month");

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox30.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox30ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel97)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Financial", jPanel30);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Paysheet ID", "User Name", "Base Salary", "Net amount", "Payment Date", "Month", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jLabel87.setText("Print teacher salary details -->");

        jButton13.setBackground(new java.awt.Color(0, 52, 101));
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Print Report");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel117.setText("Select Month");

        jComboBox31.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox31.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox31ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox31, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87)
                    .addComponent(jLabel117)
                    .addComponent(jComboBox31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Teachers", jPanel31);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Paysheet ID", "User Name", "Base Salary", "Net amount", "Payment Date", "Month", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jLabel101.setText("Print maintenence salary details -->");

        jButton15.setBackground(new java.awt.Color(0, 52, 101));
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Print Report");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel118.setText("Select Month");

        jComboBox32.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox32.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox32ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel118)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel118)
                    .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Maintenence", jPanel34);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Payment Details", jPanel28);

        jPanel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Id", "Department", "Name", "Base Salary"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable18MouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(jTable18);

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setText("Search Employee");

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel135.setText("Employee Details");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83)
                    .addComponent(jLabel135))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField9.setText(" ");
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setText("Search Pay Sheet Details");

        jButton17.setBackground(new java.awt.Color(0, 52, 101));
        jButton17.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Print All");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jTable17.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "User id", "Base salary", "Net Amount", "Date", "Month", "Status", "Savings"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable17MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(jTable17);

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setText("Double click to delete");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane18)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel72)
                .addGap(13, 13, 13))
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setText(" user id label");

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel82.setText("User Id");

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel79.setText(" user name label");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel78.setText("Name");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("department label");

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setText("Department");

        jFormattedTextField4.setText("base salary text");
        jFormattedTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFormattedTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField4ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Base Salary");

        jFormattedTextField3.setText("allowance text");
        jFormattedTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField3ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Allowance");

        jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel103.setText(" etf label");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setText("ETF(Rs.)");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setText("EPF(Rs.)");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel75.setText(" epf label");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Date");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel39.setText("Net Total");

        jLabel104.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel104.setText(" net total label");

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("Month");

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel81.setText("Status");

        jButton24.setBackground(new java.awt.Color(0, 52, 101));
        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Calculate");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(0, 52, 101));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Reset");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(0, 52, 101));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Update");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel136.setText("Salary Details");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                                                    .addGap(1, 1, 1)
                                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                            .addGroup(jPanel32Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                                    .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel103, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox16, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox17, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(0, 7, Short.MAX_VALUE)
                                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel136)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel78))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel81))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jScrollPane8.setViewportView(jPanel21);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Pay Sheets", jPanel10);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );

        salarymanagementpanel.add(jPanel1, java.awt.BorderLayout.CENTER);

        financialreportpanel.setMinimumSize(new java.awt.Dimension(0, 0));
        financialreportpanel.setPreferredSize(new java.awt.Dimension(944, 576));
        financialreportpanel.setLayout(new java.awt.BorderLayout());

        jLabel100.setText("jLabel100");
        financialreportpanel.add(jLabel100, java.awt.BorderLayout.CENTER);

        jLabel57.setText("Select Month");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });

        jButton27.setBackground(new java.awt.Color(0, 52, 101));
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Print Report");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel50.setText("Print monthly income report -->");

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Payment id", "Paid Studet", "Month", "Fee Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(jTable11);

        jLabel122.setText(" ");

        jLabel129.setText("Total Amount :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addGap(18, 18, 18)
                                .addComponent(jButton27))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel129)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jButton27))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel129)
                    .addComponent(jLabel122))
                .addContainerGap(350, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Monthly Income Report", jPanel3);

        jLabel58.setText("Select Month");

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Bills", "Salary" }));
        jComboBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox9ItemStateChanged(evt);
            }
        });

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
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
        jScrollPane13.setViewportView(jTable12);

        jLabel84.setText("Print monthly expense report -->");

        jButton31.setBackground(new java.awt.Color(0, 52, 101));
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("Print Report");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jLabel123.setText(" ");

        jLabel130.setText("Total Amount :");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(659, 697, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel84)
                                .addGap(6, 6, 6)
                                .addComponent(jButton31)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel130)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel58))
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel84))
                    .addComponent(jButton31))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130)
                    .addComponent(jLabel123))
                .addContainerGap())
        );

        jTabbedPane5.addTab("Monthly Expense Report", jPanel12);

        jLabel59.setText("Select Month");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Vendor", "Payment Due Date", "Total Amount", "Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(jTable13);

        jLabel90.setText("Print dues report -->");

        jButton12.setBackground(new java.awt.Color(0, 52, 101));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Print Report");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel124.setText(" ");

        jLabel131.setText("Total Amount :");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                    .addComponent(jLabel131)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                    .addComponent(jLabel90)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton12))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(jButton12))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel131)
                    .addComponent(jLabel124))
                .addContainerGap(373, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Dues Report", jPanel20);

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Payment id", "Paid Student", "Month", "Fee amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jTable14);

        jTable20.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Vendor", "Date", "Amount", "Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane22.setViewportView(jTable20);

        jLabel105.setText("Income table");

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox20ItemStateChanged(evt);
            }
        });

        jLabel106.setText("Select month");

        jLabel107.setText("Income");

        jLabel108.setText("Expenses");

        jLabel109.setText("Profit");

        jLabel110.setText("Expense table");

        jLabel119.setText(" ");

        jLabel120.setText(" ");

        jLabel121.setText(" ");

        jButton25.setBackground(new java.awt.Color(0, 52, 101));
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("Print Report");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox20, 0, 148, Short.MAX_VALUE)
                            .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel105)
                    .addComponent(jLabel110))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel106))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(jLabel119))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(jLabel120))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(jLabel121))
                .addGap(26, 26, 26)
                .addComponent(jButton25)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Profit Calculate", jPanel5);

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee Id", "Employee Name", "Base Salary", "Net Amount", "Payment Date", "Month", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jTable15);

        jLabel91.setText("Print salary payment report -->");

        jButton32.setBackground(new java.awt.Color(0, 52, 101));
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setText("Print Report");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jLabel93.setText("Select Month");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jLabel125.setText(" ");

        jLabel132.setText("Total Amount :");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel91)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton32))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel132)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(jButton32))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel132)
                    .addComponent(jLabel125))
                .addContainerGap(367, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Salary Payment Report", jPanel22);

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Payment Id", "St Id", "Stream", "Subject", "Date", "Status", "Month", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(jTable16);

        jLabel92.setText("Print class fee payment report -->");

        jButton9.setBackground(new java.awt.Color(0, 52, 101));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Print Report");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel95.setText("Select Month");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });

        jLabel126.setText(" ");

        jLabel133.setText("Total Amount :");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel92)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel133)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel92)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel133)
                    .addComponent(jLabel126))
                .addContainerGap(364, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Class Fee Payment", jPanel23);

        financialreportpanel.add(jTabbedPane5, java.awt.BorderLayout.PAGE_START);

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
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(0, 66, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(profilepiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
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
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185)))
                .addGap(62, 62, 62))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel23))
                .addGap(0, 0, 0)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
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
                        .addComponent(profilepiclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jButton30)
                .addGap(145, 145, 145))
        );

        javax.swing.GroupLayout financialprofilepanelLayout = new javax.swing.GroupLayout(financialprofilepanel);
        financialprofilepanel.setLayout(financialprofilepanelLayout);
        financialprofilepanelLayout.setHorizontalGroup(
            financialprofilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 929, Short.MAX_VALUE)
            .addGroup(financialprofilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE))
        );
        financialprofilepanelLayout.setVerticalGroup(
            financialprofilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
            .addGroup(financialprofilepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changingpanelLayout = new javax.swing.GroupLayout(changingpanel);
        changingpanel.setLayout(changingpanelLayout);
        changingpanelLayout.setHorizontalGroup(
            changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(financialprofilepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(feepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(billspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(salarymanagementpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(financialoverviewpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(financialreportpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(12, 12, 12)))
        );
        changingpanelLayout.setVerticalGroup(
            changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(financialprofilepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(feepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(billspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(salarymanagementpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(financialoverviewpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(financialreportpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(12, 12, 12)))
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

        loadoverviewAnual();
        loadoverview();
        loadProgressBar();
        loadExpenses();
        loadDues();
        loadChartIntoPanel();

        financialoverviewpanel.setVisible(true);
        salarymanagementpanel.setVisible(false);
        billspanel.setVisible(false);
        feepanel.setVisible(false);
        financialprofilepanel.setVisible(false);
        financialreportpanel.setVisible(false);

        menu1.setBackground(new Color(5, 93, 165));
        menu2.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
        menu6.setBackground(new Color(0, 52, 101));

    }//GEN-LAST:event_menu1MouseClicked

    private void menu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MouseClicked
        // TODO add your handling code here:

        financialoverviewpanel.setVisible(false);
        salarymanagementpanel.setVisible(false);
        billspanel.setVisible(false);
        feepanel.setVisible(true);
        financialprofilepanel.setVisible(false);
        financialreportpanel.setVisible(false);

        menu2.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
        menu6.setBackground(new Color(0, 52, 101));

    }//GEN-LAST:event_menu2MouseClicked

    private void menu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MouseClicked
        // TODO add your handling code here:

        financialoverviewpanel.setVisible(false);
        salarymanagementpanel.setVisible(false);
        billspanel.setVisible(true);
        feepanel.setVisible(false);
        financialprofilepanel.setVisible(false);
        financialreportpanel.setVisible(false);

        menu3.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
        menu6.setBackground(new Color(0, 52, 101));

    }//GEN-LAST:event_menu3MouseClicked

    private void menu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MouseClicked
        // TODO add your handling code here:

        financialoverviewpanel.setVisible(false);
        salarymanagementpanel.setVisible(true);
        billspanel.setVisible(false);
        feepanel.setVisible(false);
        financialprofilepanel.setVisible(false);
        financialreportpanel.setVisible(false);

        menu4.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
        menu6.setBackground(new Color(0, 52, 101));

    }//GEN-LAST:event_menu4MouseClicked

    private void menu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu5MouseClicked
        // TODO add your handling code here:

        financialoverviewpanel.setVisible(false);
        salarymanagementpanel.setVisible(false);
        billspanel.setVisible(false);
        feepanel.setVisible(false);
        financialprofilepanel.setVisible(false);
        financialreportpanel.setVisible(true);

        menu6.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
    }//GEN-LAST:event_menu5MouseClicked

    private void menu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu6MouseClicked
        // TODO add your handling code here:

        financialoverviewpanel.setVisible(false);
        salarymanagementpanel.setVisible(false);
        billspanel.setVisible(false);
        feepanel.setVisible(false);
        financialprofilepanel.setVisible(true);
        financialreportpanel.setVisible(false);

        menu5.setBackground(new Color(0, 52, 101));
        menu6.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
    }//GEN-LAST:event_menu6MouseClicked

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BillType_Manage bm = new BillType_Manage();
        bm.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Manage_Vendor mv = new Manage_Vendor();
        mv.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        //        Manage_Vendor mv = new Manage_Vendor();
        //        mv.setVisible(true);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Add Bill
        try {
            String BillId = String.valueOf(jLabel13.getText());
            String BillType = String.valueOf(jComboBox7.getSelectedItem());
            String Vendor = String.valueOf(jComboBox11.getSelectedItem());
            String Description = String.valueOf(jTextArea1.getText());
            String Amount = String.valueOf(jFormattedTextField2.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(new Date());
            String month = String.valueOf(jComboBox5.getSelectedItem());
            String status = String.valueOf(jComboBox12.getSelectedItem());

            if (BillType == null || BillType.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bill Type", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Vendor == null || Vendor.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Enter Vendor", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (status == null || status.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Enter Status", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (month == null || month.equals("Select Month")) {
                JOptionPane.showMessageDialog(this, "Please Enter Status", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_type` WHERE `bill_type`='" + BillId + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Bill Already Exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    int showConfirm = JOptionPane.showConfirmDialog(this, "Do you want to add new bill",
                            "Add new Bill Payment", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (showConfirm == JOptionPane.YES_OPTION) {
                        MySQL.executeIUD("INSERT INTO `bill_payments`(`bill_type_id`, `vendor_id`, `description`, `amount`, `payment_date`, `payment_status_id`,`month_id`) "
                                + "VALUES ('" + LoadBillType.get(BillType) + "', '" + LoadVendorMap.get(Vendor) + "', '" + Description + "', " + Amount + ","
                                + " '" + date + "', '" + LoadStatusmap.get(status) + "','" + LoadMonthMap.get(month) + "')");

                        loadBillPayments();
                        reset();
                        JOptionPane.showMessageDialog(this, "Bill Type Added Succesfully", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error occurred when try to add bill.", e);

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Update bill
        try {
            String BillId = String.valueOf(jLabel13.getText());
            String BillType = String.valueOf(jComboBox7.getSelectedItem());
            String Vendor = String.valueOf(jComboBox11.getSelectedItem());
            String Description = String.valueOf(jTextArea1.getText());
            String Amount = String.valueOf(jFormattedTextField2.getText());
            String Status = String.valueOf(jComboBox12.getSelectedItem());
            String month = String.valueOf(jComboBox5.getSelectedItem());

            if (BillType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Bill Type", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Vendor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Vendor", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Select Status ", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `bill_payments` WHERE `bill_type_id`='" + BillType + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Bill Type Already Exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    int showConfirm = JOptionPane.showConfirmDialog(this, "Do you want to update this bill",
                            "Add new Bill Payment", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (showConfirm == JOptionPane.YES_OPTION) {
                        MySQL.executeIUD("UPDATE `bill_payments` SET `bill_type_id`='" + LoadBillType.get(BillType) + "',`vendor_id`='" + LoadVendorMap.get(Vendor) + "',`description`='" + Description + "',"
                                + "`amount`='" + Amount + "',`payment_status_id`='" + LoadStatusmap.get(Status) + "',`month_id`='" + LoadMonthMap.get(month) + "' WHERE `bill_id`='" + BillId + "'");
                        reset();
                        loadBillPayments();
                    }
                }
            }
        } catch (Exception e) {

            logger.log(Level.WARNING, "Error occurred when try to update bill.", e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        String BillId = String.valueOf(jTable1.getValueAt(row, 0));
        jLabel13.setText(BillId);
        jLabel13.setVisible(false);

        String Billtype = String.valueOf(jTable1.getValueAt(row, 1));
        jComboBox7.setSelectedItem(Billtype);

        String Vendor = String.valueOf(jTable1.getValueAt(row, 2));
        jComboBox11.setSelectedItem(Vendor);

        String Description = String.valueOf(jTable1.getValueAt(row, 3));
        jTextArea1.setText(Description);

        String Amount = String.valueOf(jTable1.getValueAt(row, 4));
        jFormattedTextField2.setText(Amount);

        String dateString = String.valueOf(jTable1.getValueAt(row, 5)); // Get the value from the JTable
        try {
            // Convert the String to a Date object
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
            Date date = sdf.parse(dateString);

            // Set the Date to the JDateChooser
            jDateChooser1.setDate(date);
//                  jDateChooser1.setEnabled(false);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle invalid date format
        }

        String Status = String.valueOf(jTable1.getValueAt(row, 6));
        jComboBox12.setSelectedItem(Status);

        String month = String.valueOf(jTable1.getValueAt(row, 7));
        jComboBox5.setSelectedItem(month);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        //Remove bill
        try {
            String BillId = String.valueOf(jLabel13.getText());
            String BillType = String.valueOf(jComboBox7.getSelectedIndex());
            String Vendor = String.valueOf(jComboBox11.getSelectedIndex());
//            String Amount = String.valueOf(jFormattedTextField1.getText());
//            String Status = String.valueOf(jComboBox12.getSelectedIndex());
//            String month = String.valueOf(jComboBox28.getSelectedIndex());
            if (BillType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Bill Type To Remove", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Vendor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Vendor To Remove", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ResultSet resultSet = MySQL.executeSearch("SELECT  * FROM `bill_payments` WHERE `bill_id`='" + BillId + "'");

                if (resultSet.next()) {

                    int showConfirm = JOptionPane.showConfirmDialog(this, "Do you want to Remove This Bill?",
                            "Remove Company", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (showConfirm == JOptionPane.YES_OPTION) {

                        MySQL.executeIUD("DELETE FROM `bill_payments`  WHERE  `bill_id` = '" + BillId + "' ");
                        JOptionPane.showMessageDialog(this, "Bill Successfully Removed", "Success", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                        loadBillPayments();
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error occurred when try to remove bill.", e);
        }
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        reset();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel88.setText(String.valueOf(invoiceid1));
            String incoiveid2 = jLabel88.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/bill_payment.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/bill_payment.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", incoiveid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("IMAGE_PATH", imagePath);
            // Data source
            if (jTable1.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print bill table.", e);
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        //Add base salary
        try {

            String BaseSalary = String.valueOf(jFormattedTextField9.getText());
            String Employeetype = String.valueOf(jComboBox14.getSelectedItem());

            if (BaseSalary.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Base Salary", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Employeetype.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Select Employee Type", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (BaseSalary.matches("/^(0|[1-9]\\d*)$/")) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Base Salary", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary_details` WHERE `employee_type_id`='" + LoadEmployeeType.get(Employeetype) + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Salary Details Already Exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
//                     
                    MySQL.executeIUD("INSERT INTO `salary_details`(`base_salary`,`employee_type_id`) "
                            + "VALUES ('" + BaseSalary + "',+'" + LoadEmployeeType.get(Employeetype) + "')");

                    loadBillPayments();
                    reset();
                    JOptionPane.showMessageDialog(this, "Salary Added Succesfully", "Warning", JOptionPane.WARNING_MESSAGE);
                }

                loadBaseSalary();
                reset();
//                
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error occurred when try to add base salary.", e);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        //update basesalary

        try {
            String Id = jLabel85.getText();
            String Salary = jFormattedTextField9.getText();
            String EmployeeType = String.valueOf(jComboBox14.getSelectedItem());

            if (Salary.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter  Salary To Update");
            } else if (EmployeeType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter  Employee Type To Update");
            } else {
//                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `salary_details` WHERE `employee_type_id`='" + EmployeeType + "'");
//                if (resultSet.next()) {
//                    JOptionPane.showMessageDialog(this, "Salary Details Already Exists", "Warning", JOptionPane.WARNING_MESSAGE);
//                } else {
                MySQL.executeIUD("UPDATE `salary_details` SET `base_salary`='" + Salary + "',`employee_type_id`='" + LoadEmployeeType.get(EmployeeType) + "' WHERE `id`='" + Id + "'");
                reset();
                loadBaseSalary();
//                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error occurred when try to update base salary.", e);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jTable19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable19MouseClicked
        int row = jTable19.getSelectedRow();
        String Id = String.valueOf(jTable19.getValueAt(row, 0));
        jLabel85.setText(Id);
        jLabel85.setVisible(false);

        String CompanyName = String.valueOf(jTable19.getValueAt(row, 1));
        jFormattedTextField9.setText(CompanyName);

        String CompanyEmail = String.valueOf(jTable19.getValueAt(row, 2));
        jComboBox14.setSelectedItem(CompanyEmail);


    }//GEN-LAST:event_jTable19MouseClicked

    private void jTable18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable18MouseClicked
        int row = jTable18.getSelectedRow();

        String Id = String.valueOf(jTable18.getValueAt(row, 0));
        jLabel80.setText(Id);

        String Nic = String.valueOf(jTable18.getValueAt(row, 1));
        jLabel71.setText(Nic);

        String EmployeeName = String.valueOf(jTable18.getValueAt(row, 2));
        jLabel79.setText(EmployeeName);

        String Salary = String.valueOf(jTable18.getValueAt(row, 3));
        jFormattedTextField4.setText(Salary);


    }//GEN-LAST:event_jTable18MouseClicked

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed

        boolean isPrinted = false;

        try {
            String baseSalaryAmount = jFormattedTextField4.getText().trim();
            String allowanceAmount = jFormattedTextField3.getText().trim();

            if (baseSalaryAmount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an employee first!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (jDateChooser2.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Please select a valid payment date!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String month = String.valueOf(jComboBox16.getSelectedItem());
            String status = String.valueOf(jComboBox17.getSelectedItem());

            if (month.equals("Select Month") || status.equals("Select Status")) {
                JOptionPane.showMessageDialog(this, "Please select valid month and status!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double baseSalary;
            double allowance = 0.0;

            try {
                baseSalary = Double.parseDouble(baseSalaryAmount);
                if (baseSalary <= 0) {
                    JOptionPane.showMessageDialog(this, "Base salary must be greater than zero!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid base salary entered!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!allowanceAmount.isEmpty()) {
                try {
                    allowance = Double.parseDouble(allowanceAmount);
                    if (allowance < 0) {
                        JOptionPane.showMessageDialog(this, "Allowance cannot be negative!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (allowance > baseSalary * 0.5) {
                        int confirm = JOptionPane.showConfirmDialog(this,
                                "Allowance seems unusually high (> 50% of base salary). Continue?",
                                "Allowance Warning", JOptionPane.YES_NO_OPTION);
                        if (confirm != JOptionPane.YES_OPTION) {
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid allowance entered!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            SalaryCalculation calc = new SalaryCalculation();
            calc.setBaseSalary(baseSalary);
            calc.setAllowance(allowance);
            calc.calculate();

            double netPay = calc.getNetPay();
            double savings = calc.getEPF() + calc.getEPFEmployer() + calc.getETF();

            jLabel75.setText(String.valueOf(calc.getEPF() + calc.getEPFEmployer()));
            jLabel103.setText(String.valueOf(calc.getETF()));
            jLabel104.setText(String.format("%.2f", netPay));

            String employee_user_id = jLabel80.getText();
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            String salaryDetailsId = null;
            ResultSet rs = MySQL.executeSearch("SELECT id FROM salary_details WHERE base_salary = '" + baseSalary + "'");
            if (rs.next()) {
                salaryDetailsId = rs.getString("id");
            } else {
                JOptionPane.showMessageDialog(this, "Salary structure not found for this base salary!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedMonthId = LoadMonthMap.get(month);
            ResultSet checkRs = MySQL.executeSearch("SELECT * FROM salary WHERE employee_user_id = '" + employee_user_id + "' AND month_id = '" + selectedMonthId + "'");
            if (checkRs.next()) {
                JOptionPane.showMessageDialog(this, "A salary record for this employee in the selected month already exists!", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                return;
            }

            MySQL.executeIUD("INSERT INTO salary (employee_user_id, salary_details_id, net_amount, payment_date, month_id, payment_status_id, epf_etf_balance) "
                    + "VALUES ('" + employee_user_id + "', '" + salaryDetailsId + "', '" + netPay + "', '" + formattedDate + "', '" + selectedMonthId + "', '" + LoadStatusmap.get(status) + "', '" + savings + "')");

            if (status.equalsIgnoreCase("paid")) {
                int confirmPrint = JOptionPane.showConfirmDialog(this, "Do you want to print the paysheet now?", "Print Paysheet", JOptionPane.YES_NO_OPTION);
                if (confirmPrint == JOptionPane.YES_OPTION) {
                    long invoiceId = System.currentTimeMillis();
                    jLabel85.setText(String.valueOf(invoiceId));

                    HashMap<String, Object> params = new HashMap<>();
                    params.put("Parameter1", jLabel85.getText());
                    params.put("Parameter2", jLabel27.getText());
                    params.put("Parameter3", formattedDate);
                    params.put("Parameter4", employee_user_id);
                    params.put("Parameter5", jLabel79.getText());
                    params.put("Parameter6", jLabel71.getText());
                    params.put("Parameter7", formattedDate);
                    params.put("Parameter8", month);
                    params.put("Parameter9", String.valueOf(baseSalary));
                    params.put("Parameter10", String.format("%.2f", savings));
                    params.put("Parameter11", String.format("%.2f", netPay));
                    params.put("Parameter12", status);

                    InputStream stream = getClass().getResourceAsStream("/reports/paysheet_1.jasper");
                    JasperPrint print = JasperFillManager.fillReport(stream, params, new JREmptyDataSource());
                    isPrinted = JasperPrintManager.printReport(print, false);
                } else {
                    JOptionPane.showMessageDialog(this, "Paysheet saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Salary saved with status: unpaid.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            Loadpaysheet();
            reset();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number input. Please check the salary or allowance fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Unexpected error. Try again.", "Error", JOptionPane.WARNING_MESSAGE);
            }
            logger.log(Level.SEVERE, "Failed to calculate salary or print paysheet", e);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        String searchString = jTextField10.getText();
        search(searchString);
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        reset();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        String searchString = jTextField9.getText();
        search1(searchString);
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        reset();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/paysheet_2.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/paysheet.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);

            // Data source
            if (jTable17.getRowCount() == 0) {

                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable17.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable17.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print salary details.", e);

        }

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();

            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/base_salary.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/base_salary.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("IMAGE_PATH", imagePath);
            // Data source
            if (jTable19.getRowCount() == 0) {

                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable19.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable19.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print base salary details.", e);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String Total = jLabel123.getText();
            String Month = String.valueOf(jComboBox9.getSelectedItem());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/Monthly_expense.jasper");

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("Parameter4", Month);
            params.put("Parameter5", Total);
            params.put("IMAGE_PATH", imagePath);

            // Data source
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable12.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable12.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print monthly expense report.", e);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String total = jLabel125.getText();
            String month = String.valueOf(jComboBox4.getSelectedItem());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/Monthly_salaryDetails.jasper");

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("Parameter4", month);
            params.put("Parameter5", total);
            params.put("IMAGE_PATH", imagePath);

            // Data source
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable15.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable15.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print monthly slary payment report.", e);
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jFormattedTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField4ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();

            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/paysheet_teacher.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/paysheet_teacher.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("IMAGE_PATH", imagePath);
            // Data source
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable5.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable5.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print teacher salary details.", e);

        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();

            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/paysheet_academic.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/paysheet_academic.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("IMAGE PATH", imagePath);
            // Data source          
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable3.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);

        } catch (Exception e) {
            if (jTable3.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print academic salary details.", e);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/paysheet_finance.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/paysheet_finance.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("IMAGE PATH", imagePath);
            // Data source
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable4.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable4.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print finance salary details.", e);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/paysheet_maintenance.jasper");

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("IMAGE_PATH", imagePath);
            // Data source

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable6.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable6.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print maintenance salary details.", e);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox9ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox9.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable12.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = "SELECT * FROM `bill_payments` INNER JOIN "
                        + "`bill_type` ON `bill_payments`.`bill_type_id`=`bill_type`.`id` "
                        + "INNER JOIN `vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id`"
                        + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`"
                        + "WHERE `payment_status_id` = '1'";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = "SELECT * FROM `bill_payments` INNER JOIN `bill_type` ON "
                        + "`bill_payments`.`bill_type_id`=`bill_type`.`id` INNER JOIN "
                        + "`vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id` INNER JOIN `month` ON "
                        + "`bill_payments`.`month_id`=`month`.`id` WHERE `payment_status_id` = '1' AND `month_id`='" + selectedMonthId + "'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

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
        getSum2();
    }//GEN-LAST:event_jComboBox9ItemStateChanged

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox8.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable11.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = "SELECT * FROM `feepayments` INNER JOIN `students` ON `feepayments`.`students_student_id` = `students`.`student_id` INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id`";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = "SELECT * FROM `feepayments` INNER JOIN `students` ON `feepayments`.`students_student_id` = `students`.`student_id` INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id` WHERE `month`.`id` = '" + selectedMonthId + "'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("payment_id"));
                vector.add(resultSet.getString("students.first_name") + " " + resultSet.getString("students.last_name"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("amount_paid"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSum1();
    }//GEN-LAST:event_jComboBox8ItemStateChanged

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox10.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable13.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = "SELECT * FROM `bill_payments` INNER JOIN "
                        + "`bill_type` ON `bill_payments`.`bill_type_id`=`bill_type`.`id` "
                        + "INNER JOIN `vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id`"
                        + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`"
                        + "WHERE `payment_status_id` = '2'";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = "SELECT * FROM `bill_payments` INNER JOIN `bill_type` ON "
                        + "`bill_payments`.`bill_type_id`=`bill_type`.`id` INNER JOIN "
                        + "`vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id` INNER JOIN `month` ON "
                        + "`bill_payments`.`month_id`=`month`.`id` WHERE `payment_status_id` = '2' AND `month_id`='" + selectedMonthId + "'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

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
        getSum3();
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox4.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable15.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                        + "WHERE `month`.`id` = '" + selectedMonthId + "'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.first_name") + " " + (resultSet.getString("employee.last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);
            }
            getSum5();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSum5();

    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox6.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable16.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = "SELECT * FROM `feepayments` "
                        + "INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id` "
                        + "INNER JOIN `subjects` ON `feepayments`.`subjects_subject_id` = `subjects`.`subject_id` "
                        + "INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id` "
                        + "INNER JOIN `stream` ON `feepayments`.`stream_stream_id` = `stream`.`stream_id`";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = "SELECT * FROM `feepayments` "
                        + "INNER JOIN `payment_status` ON `feepayments`.`payment_status_id` = `payment_status`.`id` "
                        + "INNER JOIN `subjects` ON `feepayments`.`subjects_subject_id` = `subjects`.`subject_id` "
                        + "INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id` "
                        + "INNER JOIN `stream` ON `feepayments`.`stream_stream_id` = `stream`.`stream_id`"
                        + "WHERE `month`.`id` = '" + selectedMonthId + "'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

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
        getSum6();
    }//GEN-LAST:event_jComboBox6ItemStateChanged


    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        loadBaseSalary();
        loadEmployee();
        loadAcademicSalary();
        loadFinanceSalaryDetails();
        loadTeachersSalary();
        loadMaintenanceSalary();

    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String total = jLabel122.getText();
            String month = String.valueOf(jComboBox8.getSelectedItem());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/Monthly_income.jasper");

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("Parameter4", month);
            params.put("Parameter5", total);
            params.put("IMAGE_PATH", imagePath);

            // Data source
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable11.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable11.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print monthly income report.", e);
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
//        String firstName = jTextField18.getText();
//        String lastName = jTextField19.getText();
//        String email = jTextField3.getText();
//        String mobile = jTextField4.getText();
//
//        if (firstName.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter First Name", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (lastName.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (email.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter Email", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (mobile.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
//            JOptionPane.showMessageDialog(this, "Please enter valid Mobile Number");
//        } else {
//
//            try {
//
//                MySQL.executeIUD("UPDATE `users` SET `first_name`='" + firstName + "', `last_name`='" + lastName + "',`email`='" + email + "',"
//                        + "`mobile`='" + mobile + "' WHERE `username`='" + userName + "'");
//
//                JOptionPane.showMessageDialog(this, "Update Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                FinancialUserSession.getInstance().setName(firstName + " " + lastName);
//                jLabel27.setText(FinancialUserSession.getInstance().getName());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }

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
                int imgPathId = -1;

                // If an image path is provided, save it to the database
                if (selectedImagePath != null) {
                    String sqlInsert = "INSERT INTO img_path (path) VALUES ('" + selectedImagePath + "')";
                    MySQL.executeIUD(sqlInsert);

                    // Retrieve the generated img_path_id
                    ResultSet resultSet = MySQL.executeSearch("SELECT LAST_INSERT_ID() AS id");
                    if (resultSet.next()) {
                        imgPathId = resultSet.getInt("id");
                    }
                }

                // Construct the SQL update query for the user
                String sql = "UPDATE `users` SET `first_name`='" + firstName + "', `last_name`='" + lastName + "', `email`='" + email + "', `mobile`='" + mobile + "'";
                if (imgPathId != -1) {
                    sql += ", `img_path_id`=" + imgPathId;
                }
                sql += " WHERE `username`='" + userName + "'";

                MySQL.executeIUD(sql);
                JOptionPane.showMessageDialog(this, "Update Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                FinancialUserSession.getInstance().setName(firstName + " " + lastName);
                jLabel27.setText(FinancialUserSession.getInstance().getName());

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton30ActionPerformed

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

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();

                // Get the absolute path to the "img" folder in the project directory
                File projectRoot = new File(System.getProperty("user.dir"));
                File imgDirectory = new File(projectRoot, "src/img");

                // Create the "img" folder if it doesn't exist
                if (!imgDirectory.exists()) {
                    imgDirectory.mkdirs();
                }

                // Define the target file path in the "img" folder
                File targetFile = new File(imgDirectory, fileName);

                // Copy the selected file to the "img" folder
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Update the relative image path for database storage
                selectedImagePath = "src/img/" + fileName;

                // Display the image in the label
                ImageIcon imageIcon = new ImageIcon(targetFile.getAbsolutePath());
                Image image = imageIcon.getImage().getScaledInstance(profilepiclabel.getWidth(), profilepiclabel.getHeight(), Image.SCALE_SMOOTH);
                profilepiclabel.setIcon(new ImageIcon(image));

                JOptionPane.showMessageDialog(this, "Image uploaded and moved successfully!");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error uploading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        // TODO add your handling code here:
        int row = jTable7.getSelectedRow();

        String id = String.valueOf(jTable7.getValueAt(row, 0));
        jLabel13.setText(id);
        jLabel13.setVisible(false);

        String stream = String.valueOf(jTable7.getValueAt(row, 1));
        jComboBox1.setSelectedItem(stream);

        String subject = String.valueOf(jTable7.getValueAt(row, 2));
        jComboBox2.setSelectedItem(subject);

        String amount = String.valueOf(jTable7.getValueAt(row, 3));
        jFormattedTextField1.setText(amount);

        if (evt.getClickCount() == 2) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this fee structure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    MySQL.executeIUD("DELETE FROM `feestructure` WHERE `fee_id` = '" + id + "'");
                    loadFeeStructure();
                    reset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jTable7MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jLabel13.setVisible(false);
        String stream = String.valueOf(jComboBox1.getSelectedItem());
        String subject = String.valueOf(jComboBox2.getSelectedItem());
        String amount = jFormattedTextField1.getText();

        if (stream.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Stream", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subject.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `feestructure`"
                        + " WHERE `subjects_subject_id` = '" + loadSubjectMap.get(subject) + "' AND `stream_stream_id`='" + loadStreamMap.get(stream) + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This is Already added", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.executeIUD("INSERT INTO `feestructure`(`amount`,`subjects_subject_id`,`stream_stream_id`)"
                            + "VALUES('" + amount + "','" + loadSubjectMap.get(subject) + "','" + loadStreamMap.get(stream) + "')");

                    loadFeeStructure();
                    reset();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        String stream = String.valueOf(jComboBox1.getSelectedItem());
        String subject = String.valueOf(jComboBox2.getSelectedItem());
        String amount = jFormattedTextField1.getText();
        String id = jLabel13.getText();

        if (stream.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Stream", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subject.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `feestructure`"
                        + " WHERE `subjects_subject_id` = '" + loadSubjectMap.get(subject) + "' AND `stream_stream_id`='" + loadStreamMap.get(stream) + "'");

                boolean canUpdate = false;

                if (resultSet.next()) {

                    if (!resultSet.getString("fee_id").equals(id)) {
                        JOptionPane.showMessageDialog(this, "This Subject Added", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        canUpdate = true;
                    }

                } else {
                    canUpdate = true;
                }

                if (canUpdate) {
                    MySQL.executeIUD("UPDATE `feestructure` SET `amount` = '" + amount + "',"
                            + "`subjects_subject_id` = '" + loadSubjectMap.get(subject) + "',`stream_stream_id` = '" + loadStreamMap.get(stream) + "'"
                            + "WHERE `fee_id`='" + id + "' ");

                    loadFeeStructure();
                    reset();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jFormattedTextField1.setText("");
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox21ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        String studentName = String.valueOf(jComboBox22.getSelectedItem());
        String stream = String.valueOf(jComboBox26.getSelectedItem());
        String subjectName = String.valueOf(jComboBox25.getSelectedItem());
        String status = String.valueOf(jComboBox21.getSelectedItem());
        String fee_id = String.valueOf(jComboBox23.getSelectedIndex());
        String month = String.valueOf(jComboBox24.getSelectedItem());
        String amount = jTextField7.getText();
        String nic = jTextField2.getText();
        String desc = jTextArea3.getText();

        if (studentName.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Student Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Student NIC", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (stream.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Stream", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subjectName.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Description", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (month.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Month", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (fee_id.equals("-1")) {
            JOptionPane.showMessageDialog(this, "Please select a Fee", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                LocalDateTime currentDate = LocalDateTime.now();
                String studentID = loadStudentMap.get(studentName);
                String statusId = loadStatusMap.get(status);
                String streamId = loadStreamMap.get(stream);
                String subjectId = loadSubjectMap.get(subjectName);
                String monthId = MonthMap.get(month);
                String feeId = feeMap.get(jComboBox23.getSelectedItem().toString());

                MySQL.executeIUD("INSERT INTO `feepayments` (`students_student_id`, `fee_id`, `payment_date`, `amount_paid`, `payment_status_id`,`subjects_subject_id`,`stream_stream_id` ,`description`,`month_id`) "
                        + "VALUES ('" + studentID + "', '" + feeId + "', '" + currentDate + "', '" + amount + "', '" + statusId + "', '" + subjectId + "' , '" + streamId + "' ,'" + desc + "','" + monthId + "')");

                loadViewFeePayment();
                loadPendingFees();
                loadFeePayment();
                clear();
                JOptionPane.showMessageDialog(this, "Fee payment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jComboBox22ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox22ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {

            String selectedItem = String.valueOf(jComboBox22.getSelectedItem());

            if (loadStudentMap.containsKey(selectedItem)) {
                String id = loadStudentMap.get(selectedItem);

                try {
                    ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `students` WHERE `student_id` = '" + id + "'");

                    if (resultSet.next()) {

                        String nic = resultSet.getString("nic");

                        jTextField2.setText(nic);
                    } else {
                        jTextField2.setText("No Subject Found");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                jTextField2.setText("");

            }
        }
    }//GEN-LAST:event_jComboBox22ItemStateChanged

    private void jComboBox22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox22ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox23ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox23ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String selectedItem = String.valueOf(jComboBox23.getSelectedItem());

            if (feeMap.containsKey(selectedItem)) {
                String feeId = feeMap.get(selectedItem);

                try {
                    ResultSet resultSet = MySQL.executeSearch(
                            "SELECT `amount`,`stream`.`stream_name`, `subjects`.`subject_name` "
                            + "FROM `feestructure` "
                            + "INNER JOIN `subjects` ON `feestructure`.`subjects_subject_id` = `subjects`.`subject_id` "
                            + "INNER JOIN `stream` ON `feestructure`.`stream_stream_id` = `stream`.`stream_id` "
                            + "WHERE `fee_id` = '" + feeId + "'"
                    );

                    if (resultSet.next()) {
                        String subject = resultSet.getString("subject_name");
                        String stream = resultSet.getString("stream_name");
                        String amount = resultSet.getString("amount");

                        jComboBox25.setSelectedItem(subject);
                        jComboBox26.setSelectedItem(stream);
                        jTextField7.setText(amount);
                    } else {
                        jComboBox25.setSelectedIndex(0);
                        jComboBox26.setSelectedItem(0);
                        jTextField7.setText("No Amount Found");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                jComboBox16.setSelectedItem(0);
                jComboBox17.setSelectedItem(0);
            }
        }
    }//GEN-LAST:event_jComboBox23ItemStateChanged

    private void jComboBox23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox23ActionPerformed

    private void jComboBox24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox24ActionPerformed

    private void jComboBox25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox25ActionPerformed

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        // TODO add your handling code here:
        jLabel116.setVisible(false);
        int row = jTable10.getSelectedRow();

        String id = String.valueOf(jTable10.getValueAt(row, 0));
        jLabel116.setText(id);

        String student = String.valueOf(jTable10.getValueAt(row, 1));
        jLabel115.setText(student);

        String status = String.valueOf(jTable10.getValueAt(row, 2));
        jComboBox3.setSelectedIndex(2);

    }//GEN-LAST:event_jTable10MouseClicked

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        String id = jLabel116.getText();

        try {
            MySQL.executeIUD("UPDATE `feepayments` SET `payment_status_id` ='1' WHERE `payment_id` = '" + id + "' ");
            loadPendingFees();
            clearFees();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        try {
            String searchText = jTextField1.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable10.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            // Set the row sorter to the table
            jTable10.setRowSorter(rowSorter);

            // If the search box is empty, show all rows
            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        loadViewFeePayment();
        jLabel41.setVisible(false);

    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        jTextField2.setEditable(false);
        jTextField7.setEditable(false);
        jComboBox26.setEnabled(false);
        jComboBox25.setEnabled(false);
        clear();
        loadFeeStructure();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane4StateChanged
        // TODO add your handling code here:
        loadViewFeePayment();
        loadPendingFees();
        loadFeePayment();
    }//GEN-LAST:event_jTabbedPane4StateChanged

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        loadFeeStructure();
        loadViewFeePayment();
        loadPendingFees();
        loadFeePayment();

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:

//        String studentName = String.valueOf(jComboBox22.getSelectedItem());
        String stream = String.valueOf(jComboBox26.getSelectedItem());
        String subjectName = String.valueOf(jComboBox25.getSelectedItem());
        String status = String.valueOf(jComboBox21.getSelectedItem());
//        String fee_id = String.valueOf(jComboBox23.getSelectedIndex());
        String month = String.valueOf(jComboBox24.getSelectedItem());
        String amount = jTextField7.getText();
        String nic = jTextField2.getText();
        String desc = jTextArea3.getText();
        String payId = jLabel41.getText();

//        if (studentName.equals("Select")) {
//            JOptionPane.showMessageDialog(this, "Please select a Student Name", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else
        if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter the Student NIC", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (stream.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Stream", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subjectName.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Description", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (month.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Month", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (fee_id.equals("-1")) {
//            JOptionPane.showMessageDialog(this, "Please select a Fee", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String statusId = loadStatusMap.get(status);
//                String monthId = MonthMap.get(month);

                MySQL.executeIUD("UPDATE `feepayments` SET "
                        + " `amount_paid` = '" + amount + "' , `payment_status_id` = '" + statusId + "' , "
                        + "`description`='" + desc + "' , `month_id` = '" + MonthMap.get(month) + "'"
                        + "WHERE `payment_id` = '" + payId + "' ");
                loadViewFeePayment();
                loadPendingFees();
                loadFeePayment();
                clear();
                JOptionPane.showMessageDialog(this, "Fee payment updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                jButton14.setEnabled(true);
                jComboBox22.setEnabled(true);
                jComboBox23.setEnabled(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton36ActionPerformed

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked
        // TODO add your handling code here:
        jButton14.setEnabled(false);
        jComboBox22.setEnabled(false);
        jComboBox23.setEnabled(false);

        int row1 = jTable8.getSelectedRow();

        String id = String.valueOf(jTable8.getValueAt(row1, 0));
        jLabel41.setText(id);

        String nic = String.valueOf(jTable8.getValueAt(row1, 1));
        jTextField2.setText(nic);

        String status = String.valueOf(jTable8.getValueAt(row1, 2));
        jComboBox21.setSelectedItem(status);

        String amount = String.valueOf(jTable8.getValueAt(row1, 4));
        jTextField7.setText(amount);

        String subject = String.valueOf(jTable8.getValueAt(row1, 5));
        jComboBox25.setSelectedItem(subject);

        String stream = String.valueOf(jTable8.getValueAt(row1, 6));
        jComboBox26.setSelectedItem(stream);

        String desc = String.valueOf(jTable8.getValueAt(row1, 7));
        jTextArea3.setText(desc);

        String month = String.valueOf(jTable8.getValueAt(row1, 8));
        jComboBox24.setSelectedItem(month);

//        delete the rcord
        if (evt.getClickCount() == 2) {

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete the payment entry?",
                    "Delete Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                try {
                    MySQL.executeIUD("DELETE FROM feepayments WHERE payment_id = '" + id + "'");
                    loadViewFeePayment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Deleted successfully!");
            }

        }


    }//GEN-LAST:event_jTable8MouseClicked

    private void jComboBox20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox20ItemStateChanged
        try {

            String selectedMonthName1 = String.valueOf(jComboBox20.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable14.getModel();
            model.setRowCount(0);

            String query1;

            if (selectedMonthName1.equals("All Months")) {

                query1 = "SELECT * FROM `feepayments` "
                        + "INNER JOIN `students` ON `feepayments`.`students_student_id` = `students`.`student_id` "
                        + "INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id`";
            } else {

                String selectedMonthId = LoadMonthMap.get(selectedMonthName1);
                query1 = "SELECT * FROM `feepayments` "
                        + "INNER JOIN `students` ON `feepayments`.`students_student_id` = `students`.`student_id` "
                        + "INNER JOIN `month` ON `feepayments`.`month_id` = `month`.`id` "
                        + "WHERE `month`.`id` = '" + selectedMonthId + "'";
            }

            ResultSet resultSet1 = MySQL.executeSearch(query1);

            while (resultSet1.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet1.getString("payment_id"));
                vector.add(resultSet1.getString("students.first_name") + " " + resultSet1.getString("students.last_name"));
                vector.add(resultSet1.getString("month.month_name"));
                vector.add(resultSet1.getString("amount_paid"));
                model.addRow(vector);
            }
            getSumProfitCal1();
            SumOfProfit();

            String selectedMonthName2 = String.valueOf(jComboBox20.getSelectedItem());

            DefaultTableModel model2 = (DefaultTableModel) jTable20.getModel();
            model2.setRowCount(0);

            String query2;

            if (selectedMonthName2.equals("All Months")) {

                query2 = "SELECT * FROM `bill_payments` INNER JOIN "
                        + "`bill_type` ON `bill_payments`.`bill_type_id`=`bill_type`.`id` "
                        + "INNER JOIN `vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id`"
                        + "INNER JOIN `month` ON `bill_payments`.`month_id`=`month`.`id`"
                        + "WHERE `payment_status_id` = '1'";
            } else {

                String selectedMonthId2 = LoadMonthMap.get(selectedMonthName2);
                query2 = "SELECT * FROM `bill_payments` INNER JOIN `bill_type` ON "
                        + "`bill_payments`.`bill_type_id`=`bill_type`.`id` INNER JOIN "
                        + "`vendor` ON `bill_payments`.`vendor_id`=`vendor`.`id` INNER JOIN `month` ON "
                        + "`bill_payments`.`month_id`=`month`.`id` WHERE `payment_status_id` = '1' AND `month_id`='" + selectedMonthId2 + "'";
            }

            ResultSet resultSet2 = MySQL.executeSearch(query2);

            while (resultSet2.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet2.getString("bill_id"));
                vector.add(resultSet2.getString("vendor.vendor_name"));
                vector.add(resultSet2.getString("payment_date"));
                vector.add(resultSet2.getString("amount"));
                vector.add(resultSet2.getString("month.month_name"));
                model2.addRow(vector);
            }
            getSumProfitCal2();
            SumOfProfit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox20ItemStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String Total = jLabel124.getText();
            String Month = String.valueOf(jComboBox10.getSelectedItem());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/Dues_Report.jasper");

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("Parameter4", Month);
            params.put("Parameter5", Total);
            params.put("IMAGE_PATH", imagePath);

            // Data source
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable13.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable13.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print monthly dues report.", e);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String income = jLabel119.getText();
            String expense = jLabel120.getText();
            String profit_loss = jLabel121.getText();
            String month = String.valueOf(jComboBox20.getSelectedItem());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();

            if (month == null || month.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a valid month from the combo box.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            MySQL.executeIUD("INSERT INTO `profit`(`income`,`expenses`,`profit`,`month`) "
                    + "VALUES('" + income + "','" + expense + "','" + profit_loss + "','" + month + "')");

            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/Profit_report.jasper");

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("Parameter4", income);
            params.put("Parameter5", expense);
            params.put("Parameter6", profit_loss);
            params.put("Parameter7", month);
            params.put("IMAGE_PATH", imagePath);

            // Data source
            JREmptyDataSource emptyDataSource = new JREmptyDataSource();

            JasperPrint report = JasperFillManager.fillReport(path, params, emptyDataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print profit report.", e);
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        boolean isPrinted = false;
        try {
            long invoiceid1 = System.currentTimeMillis();
            jLabel85.setText(String.valueOf(invoiceid1));
            String invoiceid2 = jLabel85.getText();
            String EmployeeUserName = jLabel27.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String total = jLabel126.getText();
            String month = String.valueOf(jComboBox6.getSelectedItem());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();

            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/ClassFeesReport.jasper");

            // Parameters for the report
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", invoiceid2);
            params.put("Parameter2", EmployeeUserName);
            params.put("Parameter3", dateTime);
            params.put("Parameter4", month);
            params.put("Parameter5", total);
            params.put("IMAGE_PATH", imagePath);

            // Data source
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable16.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable16.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
            logger.log(Level.WARNING, "Error occurred when try to print monthly class fees report.", e);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox26ActionPerformed

    private void jComboBox7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox7MouseClicked
        LoadBillTypes();
    }//GEN-LAST:event_jComboBox7MouseClicked

    private void jComboBox11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox11MouseClicked
        LoadVendors();
    }//GEN-LAST:event_jComboBox11MouseClicked

    private void jTabbedPane3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane3StateChanged
        loadAcademicSalary();
        loadFinanceSalaryDetails();
        loadTeachersSalary();
        loadMaintenanceSalary();
    }//GEN-LAST:event_jTabbedPane3StateChanged

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed

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
                        + "`users`.`user_type_id`=`usertypes`.`user_type_id` WHERE `username` = '" + FinancialUserSession.getInstance().getUsername() + "'");

                if (resultSet.next()) {
                    String description = "Financial Log Out";
                    String user = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                    String userType = resultSet.getString("usertypes.user_type_name");

                    MySQL.executeIUD("INSERT INTO `system_logs`(`timestamp`,`activity`,`user_name`,`user_type`)"
                            + "VALUES ('" + SystemDateTime + "','" + description + "','" + user + "','" + userType + "')");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Perform logout and navigation
            FinancialUserSession.getInstance().logout();
            this.dispose();
            userSelection us = new userSelection();
            us.setVisible(true);
        }

    }//GEN-LAST:event_jButton26ActionPerformed

    private void jComboBox29ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox29ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox29.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id` WHERE `employee`.`employee_type_id`='1'";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                        + "WHERE `month`.`id` = '" + selectedMonthId + "' AND `employee`.`employee_type_id`='1'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.first_name") + " " + (resultSet.getString("employee.last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);
            }
            getSum5();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSum5();

    }//GEN-LAST:event_jComboBox29ItemStateChanged

    private void jComboBox30ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox30ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox30.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON "
                        + "`salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON "
                        + "`salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON "
                        + "`salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON "
                        + "`salary`.`employee_user_id` = `employee`.`user_id` WHERE `employee`.`employee_type_id`='2'";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                        + "WHERE `month`.`id` = '" + selectedMonthId + "' AND `employee`.`employee_type_id`='2'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.first_name") + " " + (resultSet.getString("employee.last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jComboBox30ItemStateChanged

    private void jComboBox31ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox31ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox31.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON "
                        + "`salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON "
                        + "`salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON "
                        + "`salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON "
                        + "`salary`.`employee_user_id` = `employee`.`user_id` WHERE `employee`.`employee_type_id`='3'";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                        + "WHERE `month`.`id` = '" + selectedMonthId + "' AND `employee`.`employee_type_id`='3'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.first_name") + " " + (resultSet.getString("employee.last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jComboBox31ItemStateChanged

    private void jComboBox32ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox32ItemStateChanged
        try {

            String selectedMonthName = String.valueOf(jComboBox32.getSelectedItem());

            DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
            model.setRowCount(0);

            String query;

            if (selectedMonthName.equals("All Months")) {

                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON "
                        + "`salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON "
                        + "`salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON "
                        + "`salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON "
                        + "`salary`.`employee_user_id` = `employee`.`user_id` WHERE `employee`.`employee_type_id`='4'";
            } else {

                String selectedMonthId = MonthMap.get(selectedMonthName);
                query = " SELECT * FROM `salary` INNER JOIN `salary_details` ON `salary`.`salary_details_id` = `salary_details`.`id` INNER JOIN `month` ON `salary`.`month_id` = `month`.`id` INNER JOIN `payment_status` ON `salary`.`payment_status_id` = `payment_status`.`id` INNER JOIN `employee` ON `salary`.`employee_user_id` = `employee`.`user_id`"
                        + "WHERE `month`.`id` = '" + selectedMonthId + "' AND `employee`.`employee_type_id`='4'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("employee.first_name") + " " + (resultSet.getString("employee.last_name")));
                vector.add(resultSet.getString("salary_details.base_salary"));
                vector.add(resultSet.getString("net_amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("month.month_name"));
                vector.add(resultSet.getString("payment_status.status"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jComboBox32ItemStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        boolean isPrinted = false;
        try {
            // Generate a unique invoice ID
            String invoiceid1 = String.valueOf(System.currentTimeMillis());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();

            // Check if the table and a row are valid
            if (jTable9 == null || jTable9.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int selectedRow = jTable9.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to print.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Load the report file
            try (InputStream path = this.getClass().getResourceAsStream("/reports/new_financial_feepayent_1.jasper")) {
                if (path == null) {
                    throw new RuntimeException("Report file not found at /reports/new_financial_feepayent_1.jasper");
                }

                // Parameters for the report
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("Parameter1", invoiceid1);
                parameters.put("IMAGE_PATH", imagePath);

                // Get the selected row data
                TableModel originalModel = jTable9.getModel();
                DefaultTableModel selectedRowModel = new DefaultTableModel();

                // Copy column names
                for (int col = 0; col < originalModel.getColumnCount(); col++) {
                    selectedRowModel.addColumn(originalModel.getColumnName(col));
                }

                // Copy data of the selected row
                Object[] rowData = new Object[originalModel.getColumnCount()];
                for (int col = 0; col < originalModel.getColumnCount(); col++) {
                    rowData[col] = originalModel.getValueAt(selectedRow, col);
                }
                selectedRowModel.addRow(rowData);

                // Create a data source for the selected row
                JRTableModelDataSource dataSource = new JRTableModelDataSource(selectedRowModel);

                // Fill the report
                JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

                // Print the report
                isPrinted = JasperPrintManager.printReport(report, false);
            }
        } catch (Exception e) {
            if (jTable9 != null && jTable9.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            } else {
                JOptionPane.showMessageDialog(this, "An error occurred while printing the report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        String searchFeePay = jTextField6.getText();
        searchFee(searchFeePay);
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // paysheet update
        int row = jTable17.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a record from the table to update.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String id = String.valueOf(jTable17.getValueAt(row, 0));
        String userId = String.valueOf(jTable17.getValueAt(row, 1));

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM salary WHERE id='" + id + "' AND employee_user_id='" + userId + "'");
            if (rs.next()) {
                String baseSalaryAmount = jFormattedTextField4.getText().trim();
                String allowanceAmount = jFormattedTextField3.getText().trim();

                if (baseSalaryAmount.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Base salary cannot be empty!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double baseSalary = Double.parseDouble(baseSalaryAmount);
                double allowance = 0.0;

                if (!allowanceAmount.isEmpty()) {
                    allowance = Double.parseDouble(allowanceAmount);
                }

                String month = String.valueOf(jComboBox16.getSelectedItem());
                String status = String.valueOf(jComboBox17.getSelectedItem());
                if (month.equals("Select Month") || status.equals("Select Status")) {
                    JOptionPane.showMessageDialog(this, "Please select a valid month and status!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Date date = jDateChooser2.getDate();
                if (date == null) {
                    JOptionPane.showMessageDialog(this, "Please select a valid payment date!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                SalaryCalculation calc = new SalaryCalculation();
                calc.setBaseSalary(baseSalary);
                calc.setAllowance(allowance);
                calc.calculate();

                double netPay = calc.getNetPay();
                double savings = calc.getEPF() + calc.getEPFEmployer() + calc.getETF();
                String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

                // Update salary
                String updateQuery = "UPDATE salary SET net_amount='" + netPay + "', payment_date='" + formattedDate
                        + "', month_id='" + LoadMonthMap.get(month) + "', payment_status_id='" + LoadStatusmap.get(status)
                        + "', epf_etf_balance='" + savings + "' WHERE id='" + id + "' AND employee_user_id='" + userId + "'";

                MySQL.executeIUD(updateQuery);

                JOptionPane.showMessageDialog(this, "Salary record updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Loadpaysheet();
                reset();
            } else {
                JOptionPane.showMessageDialog(this, "Selected salary record not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid salary or allowance entered.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while updating salary record: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton22ActionPerformed

    private void jTable17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable17MouseClicked
        int row = jTable17.getSelectedRow();

        if (evt.getClickCount() == 1) {
            String id = String.valueOf(jTable17.getValueAt(row, 0));
            String userId = String.valueOf(jTable17.getValueAt(row, 1));
            try {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM salary WHERE id='" + id + "' AND employee_user_id='" + userId + "' ");
                if (rs.next()) {

                    ResultSet rs2 = MySQL.executeSearch("SELECT CONCAT (employee.first_name, '  ' ,employee.last_name) AS NAME ,"
                            + " employee_type.`type` AS department FROM salary INNER JOIN employee ON employee.user_id=salary.employee_user_id "
                            + "INNER JOIN employee_type ON employee_type.id=employee.employee_type_id WHERE salary.id='" + id + "' ");

                    if (rs2.next()) {

                        String empName = rs2.getString("name");
                        String empDepartment = rs2.getString("department");
                        //getting data from table
                        String baseSalary = String.valueOf(jTable17.getValueAt(row, 2));
                        String netAmont = String.valueOf(jTable17.getValueAt(row, 3));
                        String dateStr = String.valueOf(jTable17.getValueAt(row, 4));
                        String month = String.valueOf(jTable17.getValueAt(row, 5));
                        String status = String.valueOf(jTable17.getValueAt(row, 6));
//                        String savings = String.valueOf(jTable17.getValueAt(row, 7));
                        
                        //convert string values to double
                        double basic = Double.parseDouble(baseSalary);
                        double net = Double.parseDouble(netAmont);
                        
                        
                        //convert date SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(dateStr);
    
                        //calculation part 
                        SalaryCalculation cal = new SalaryCalculation();
                        cal.setBaseSalary(basic);
                        cal.calculate();
                        
                        double employeeEPF = cal.getEPF();
                        double employeREPF = cal.getEPFEmployer();
                        double totalEFP = employeREPF + employeeEPF ;
                        double employeRETF = cal.getETF();
                        double allowance = net - (basic - employeeEPF);
                        //calculation part end 
                        
                        //calculation check
//                        System.out.println("basic : " + basic);
//                        System.out.println("Allowance : " + allowance);
//                        System.out.println("employee EPF (8%) : " + employeeEPF);
//                        System.out.println("employer EPF (12%) : " + employeREPF);
//                        System.out.println("employer ETF (3%) : " + employeRETF);
//                        System.out.println("net amount (basic + allowance -epf(8%)) : " + net);
                        //calculation check end
                        
                        
                        //set data to fields
                        jLabel80.setText(id);
                        jLabel79.setText(empName);
                        jLabel71.setText(empDepartment);
                        jFormattedTextField4.setText(baseSalary);
                        jFormattedTextField3.setText(String.valueOf(allowance)); //allowance
                        jLabel75.setText(String.valueOf(totalEFP));  //epf
                        jLabel103.setText(String.valueOf(employeRETF));  //etf
                        jDateChooser2.setDate(date);  //date
                        jComboBox16.setSelectedItem(month); //month
                        jComboBox17.setSelectedItem(status); //status
                    }
//                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (evt.getClickCount() == 2) {
            String salaryId = String.valueOf(jTable17.getValueAt(row, 0));
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete this entry?",
                    "Delete Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    MySQL.executeIUD("DELETE FROM salary WHERE salary.id='" + salaryId + "' ");

                    JOptionPane.showMessageDialog(null, "Paysheet deleted successfully!");
                    Loadpaysheet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_jTable17MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//
//        FlatMacLightLaf.setup();
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FinancialDashboard().setVisible(true);
//
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboardconstantpanel;
    private javax.swing.JPanel billspanel;
    private javax.swing.JPanel changingpanel;
    private javax.swing.JPanel feepanel;
    private javax.swing.JPanel financialoverviewpanel;
    private javax.swing.JPanel financialprofilepanel;
    private javax.swing.JPanel financialreportpanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
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
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JComboBox<String> jComboBox22;
    private javax.swing.JComboBox<String> jComboBox23;
    private javax.swing.JComboBox<String> jComboBox24;
    private javax.swing.JComboBox<String> jComboBox25;
    private javax.swing.JComboBox<String> jComboBox26;
    private javax.swing.JComboBox<String> jComboBox29;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox30;
    private javax.swing.JComboBox<String> jComboBox31;
    private javax.swing.JComboBox<String> jComboBox32;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
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
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
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
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable19;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable21;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel menu1;
    private javax.swing.JPanel menu2;
    private javax.swing.JPanel menu3;
    private javax.swing.JPanel menu4;
    private javax.swing.JPanel menu5;
    private javax.swing.JPanel menu6;
    private javax.swing.JPanel menupanel;
    private javax.swing.JLabel profilepiclabel;
    private javax.swing.JPanel salarymanagementpanel;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jComboBox7.setSelectedIndex(0);
        jComboBox11.setSelectedIndex(0);
        jComboBox12.setSelectedIndex(0);
        jDateChooser2.setDate(null);
        jFormattedTextField2.setText("");
        jTextArea1.setText("");
        jFormattedTextField9.setText("");
        jComboBox14.setSelectedIndex(0);

        jLabel103.setText("");
        jLabel75.setText("");
        jLabel79.setText("");
        jLabel71.setText("");
        jFormattedTextField4.setText("");
        jFormattedTextField3.setText("");
        jLabel104.setText("");
        jLabel80.setText("");
        jDateChooser1.setDate(null);
        jComboBox16.setSelectedIndex(0);
        jComboBox17.setSelectedIndex(0);

        jTable18.clearSelection();

        jLabel85.setText("");
        jLabel86.setText("");
        jComboBox5.setSelectedIndex(0);

    }

    private void clear() {

        jComboBox22.setEnabled(true);
        jButton14.setEnabled(true);
        jComboBox23.setEnabled(true);
        jTable8.clearSelection();

        jComboBox23.setSelectedIndex(0);
        jComboBox22.setSelectedIndex(0);
        jComboBox26.setSelectedIndex(0);
        jComboBox25.setSelectedIndex(0);
        jComboBox24.setSelectedIndex(0);
        jComboBox21.setSelectedIndex(0);
        jComboBox15.setSelectedIndex(0);
        jTextArea3.setText("");
        jTextField7.setText("");
        jTextField2.setText("");

    }

    private void clearFees() {
        jComboBox3.setSelectedIndex(0);
        jLabel15.setText("");
    }
}
