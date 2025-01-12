/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import gui.userSelection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.MySQL;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import model.AcademicUserSession;
import model.FinancialUserSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *
 * @author Dell
 */
public class AcademicDashboard extends javax.swing.JFrame {

    private static String userName = AcademicUserSession.getInstance().getUsername();
    private static String SystemDateTime;

    HashMap<String, String> BatchMap = new HashMap<>();
    HashMap<String, String> StreamMap = new HashMap<>();
    HashMap<String, String> SubjectMap = new HashMap<>();
    HashMap<String, String> Subject1Map = new HashMap<>();
    HashMap<String, String> TeachersMap = new HashMap<>();
    HashMap<String, String> StudentsMap = new HashMap<>();

    private String imgPath;
    private String TimgPath;

    private void image() {

        FlatSVGIcon icon1 = new FlatSVGIcon("resources//LOGOWHITE.svg", jLabel6.getWidth(), jLabel6.getHeight());
        jLabel6.setIcon(icon1);
        FlatSVGIcon icon2 = new FlatSVGIcon("resources//profileImage.svg", jLabel12.getWidth(), jLabel12.getHeight());
        jLabel12.setIcon(icon2);
        FlatSVGIcon icon4 = new FlatSVGIcon("resources//profileImage.svg", jLabel7.getWidth(), jLabel7.getHeight());
        jLabel7.setIcon(icon4);
//        FlatSVGIcon icon5 = new FlatSVGIcon("resources//barcode.svg", jLabel13.getWidth(), jLabel13.getHeight());
//        jLabel13.setIcon(icon5);
//        FlatSVGIcon icon6 = new FlatSVGIcon("resources//scan.svg", jLabel17.getWidth(), jLabel17.getHeight());
//        jLabel17.setIcon(icon6);
        FlatSVGIcon icon7 = new FlatSVGIcon("resources//profileImage.svg", jLabel30.getWidth(), jLabel30.getHeight());
        jLabel30.setIcon(icon7);
        FlatSVGIcon icon8 = new FlatSVGIcon("resources//barcode(1).svg", jLabel32.getWidth(), jLabel32.getHeight());
        jLabel32.setIcon(icon8);
//        FlatSVGIcon icon9 = new FlatSVGIcon("resources//scan.svg", jLabel77.getWidth(), jLabel77.getHeight());
//        jLabel77.setIcon(icon9);
        FlatSVGIcon icon10 = new FlatSVGIcon("resources//studentdash.svg", jLabel66.getWidth(), jLabel66.getHeight());
        jLabel66.setIcon(icon10);
        FlatSVGIcon icon11 = new FlatSVGIcon("resources//teacherdash.svg", jLabel92.getWidth(), jLabel92.getHeight());
        jLabel92.setIcon(icon11);
        FlatSVGIcon icon12 = new FlatSVGIcon("resources//subjectdash.svg", jLabel68.getWidth(), jLabel68.getHeight());
        jLabel68.setIcon(icon12);
//        FlatSVGIcon icon13 = new FlatSVGIcon("resources//piedash.svg", jLabel94.getWidth(), jLabel94.getHeight());
//        jLabel94.setIcon(icon13);

        FlatSVGIcon icon14 = new FlatSVGIcon("resources//profileImage.svg", jLabel124.getWidth(), jLabel124.getHeight());
        jLabel124.setIcon(icon14);
        FlatSVGIcon icon15 = new FlatSVGIcon("resources//profileImage.svg", jTprofile.getWidth(), jTprofile.getHeight());
        jTprofile.setIcon(icon15);
        FlatSVGIcon icon16 = new FlatSVGIcon("resources//barcode(1).svg", jLabel13.getWidth(), jLabel13.getHeight());
        jLabel13.setIcon(icon16);
        FlatSVGIcon icon17 = new FlatSVGIcon("resources//profileImage.svg", jLabel103.getWidth(), jLabel103.getHeight());
        jLabel103.setIcon(icon17);
        FlatSVGIcon icon18 = new FlatSVGIcon("resources//barcode(1).svg", jLabel29.getWidth(), jLabel29.getHeight());
        jLabel29.setIcon(icon18);
        FlatSVGIcon icon19 = new FlatSVGIcon("resources//profileImage.svg", jLabel111.getWidth(), jLabel111.getHeight());
        jLabel111.setIcon(icon19);
        FlatSVGIcon icon20 = new FlatSVGIcon("resources//barcode(1).svg", jLabel112.getWidth(), jLabel112.getHeight());
        jLabel112.setIcon(icon20);
    }

    /**
     * Creates new form NewDashboard
     */
    public AcademicDashboard() {
//        initComponents();
//        image();
//        loadBatch();
//        loadBatchforteacherallocation();
//        loadStream();
//        loadSubjects();
//        loadSubject1();
//        loadTeachers();
//        loadStudents();
//        loadSelectedSubjects();
//        filterStudentsComboBox("");
//        loadSubStre();
//        loadshedule();
//        loadMaterialLibraryTable();
//        loadTeacherAssignmentTable();
//        loadTeacherViewTable();
//        loadUserProfile();
//
//        overviewpanel.setVisible(true);
//        studentmanagement.setVisible(false);
//        teachermanagement.setVisible(false);
//        subjectmanagement.setVisible(false);
//        profile.setVisible(false);
//        menu1.setBackground(new Color(5, 93, 165));

        initComponents();
        jLabel132.setText(AcademicUserSession.getInstance().getName());
        image();
        loadBatch();
        loadBatchforteacherallocation();
        loadStream();
        loadSubjects();
        loadSubject1();
        loadTeachers();
        createPieChart();
        createBarChart();
        overviewSTudent();
        overviewTeacher();
        overviewSubject();
        loadStudents();
        loadSelectedSubjects();
        filterStudentsComboBox("");
        loadSubStre();
        loadshedule();
        loadMaterialLibraryTable();
        loadTeacherAssignmentTable();
        loadTeacherViewTable();
        loadTeacherClassTable();
        loadStudentEnrollmentReport();
        loadTeacherEnrollmenrt();
        loadsheduleForReport();
        loadsheduleForSubject();
        loadMatirealLibraryForReport();
        overviewpanel.setVisible(true);
        studentmanagement.setVisible(false);
        teachermanagement.setVisible(false);
        subjectmanagement.setVisible(false);
        profile.setVisible(false);
        menu1.setBackground(new Color(5, 93, 165));
        time();
        loadSTA();
        loadStudentToAttendanceTable();
        loadToTeacherAttendanceTable();
        loadteacherAttendanceTable();
        loadBarcodeTable();
        loadTBarcodeTable();
        loadStudentAttendanceReportTable();
        loadTattReportsTable();
        loadUserProfile();

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

    private void overviewSTudent() {

        int rowCount = 0;

        try {
            // SQL query to count rows in the "students" table
            String query = "SELECT COUNT(*) AS rowCount FROM `students`";

            // Execute the query using MySQL.executeSearch
            ResultSet rs = MySQL.executeSearch(query);

            // Retrieve the result
            if (rs.next()) {
                rowCount = rs.getInt("rowCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String rc = String.valueOf(rowCount);
        jLabel95.setText(rc);

    }

    private void overviewTeacher() {

        int rowCount = 0;

        try {
            // SQL query to count rows in the "students" table
            String query = "SELECT COUNT(*) AS rowCount FROM `teachers`";

            // Execute the query using MySQL.executeSearch
            ResultSet rs = MySQL.executeSearch(query);

            // Retrieve the result
            if (rs.next()) {
                rowCount = rs.getInt("rowCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String rc = String.valueOf(rowCount);
        jLabel99.setText(rc);

    }

    private void overviewSubject() {

        int rowCount = 0;

        try {
            // SQL query to count rows in the "students" table
            String query = "SELECT COUNT(*) AS rowCount FROM `subjects`";

            // Execute the query using MySQL.executeSearch
            ResultSet rs = MySQL.executeSearch(query);

            // Retrieve the result
            if (rs.next()) {
                rowCount = rs.getInt("rowCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String rc = String.valueOf(rowCount);
        jLabel101.setText(rc);

    }

    private void createPieChart() {
        try {

            String query = "SELECT subjects.subject_name, COUNT(*) AS student_count "
                    + "FROM students_has_subjects "
                    + "INNER JOIN subjects ON students_has_subjects.subjects_subject_id = subjects.subject_id "
                    + "GROUP BY subjects.subject_name";

            ResultSet rs = MySQL.executeSearch(query);

            // Create the dataset
            DefaultPieDataset dataset = new DefaultPieDataset();
            int totalStudents = 0;

            List<String> subjectNames = new ArrayList<>();
            List<Integer> studentCounts = new ArrayList<>();
            while (rs.next()) {
                subjectNames.add(rs.getString("subject_name"));
                int count = rs.getInt("student_count");
                studentCounts.add(count);
                totalStudents += count;
            }

            for (int i = 0; i < subjectNames.size(); i++) {
                String subjectName = subjectNames.get(i);
                int studentCount = studentCounts.get(i);
                dataset.setValue(subjectName, studentCount);
            }

            JFreeChart pieChart = ChartFactory.createPieChart3D(
                    "Student Distribution by Subject",
                    dataset,
                    true,
                    true,
                    false
            );

            pieChart.setBackgroundPaint(Color.WHITE);

            // Customize the plot
            PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
            plot.setBackgroundPaint(new Color(230, 230, 230));
            plot.setOutlineVisible(false);
            plot.setCircular(true);
            plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            plot.setForegroundAlpha((float) 0.8);
            plot.setDepthFactor(0.15);

            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}"));

            // Display the chart in the JPanel
            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setOpaque(false);
            jPie.removeAll();
            jPie.setLayout(new BorderLayout());
            jPie.add(chartPanel, BorderLayout.CENTER);
            jPie.revalidate();
            jPie.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating pie chart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createBarChart() {

        int year = java.time.Year.now().getValue();
        String yearS = Integer.toString(year);
        int y1 = year - 2;
        String s1 = Integer.toString(y1);
        int y2 = year - 1;
        String s2 = Integer.toString(y2);
        int y4 = year + 1;
        String s4 = Integer.toString(y4);
        int y5 = year + 2;
        String s5 = Integer.toString(y5);

        try {

            ResultSet A = MySQL.executeSearch("SELECT COUNT(*) AS student_count FROM students "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = students.AL_batch_batch_id "
                    + "WHERE AL_batch.batch_name = '" + s1 + "'");
            A.next();
            int A1 = A.getInt("student_count");

            ResultSet B = MySQL.executeSearch("SELECT COUNT(*) AS student_count FROM students "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = students.AL_batch_batch_id "
                    + "WHERE AL_batch.batch_name = '" + s2 + "'");
            B.next();
            int B1 = B.getInt("student_count");

            ResultSet C = MySQL.executeSearch("SELECT COUNT(*) AS student_count FROM students "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = students.AL_batch_batch_id "
                    + "WHERE AL_batch.batch_name = '" + yearS + "'");
            C.next();
            int C1 = C.getInt("student_count");

            ResultSet D = MySQL.executeSearch("SELECT COUNT(*) AS student_count FROM students "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = students.AL_batch_batch_id "
                    + "WHERE AL_batch.batch_name = '" + s4 + "'");
            D.next();
            int D1 = D.getInt("student_count");

            ResultSet E = MySQL.executeSearch("SELECT COUNT(*) AS student_count FROM students "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = students.AL_batch_batch_id "
                    + "WHERE AL_batch.batch_name = '" + s5 + "'");
            E.next();
            int E1 = E.getInt("student_count");

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(A1, "Students", s1);
            dataset.addValue(B1, "Students", s2);
            dataset.addValue(C1, "Students", yearS);
            dataset.addValue(D1, "Students", s4);
            dataset.addValue(E1, "Students", s5);

            JFreeChart barChart = ChartFactory.createBarChart3D(
                    "Student Count by A/L Batch",
                    "A/L Batch",
                    "Count",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false,
                    true,
                    false
            );

            barChart.setBackgroundPaint(Color.WHITE);
            CategoryPlot plot = (CategoryPlot) barChart.getPlot();
            plot.setBackgroundPaint(new Color(230, 230, 230));
            plot.setRangeGridlinePaint(Color.BLACK);
            plot.setDomainGridlinePaint(Color.BLACK);
            plot.setOutlinePaint(null);

            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(0, 102, 204));
            renderer.setItemMargin(0.05);

            Font axisFont = new Font("SansSerif", Font.PLAIN, 12);
            plot.getDomainAxis().setLabelFont(axisFont);
            plot.getDomainAxis().setTickLabelFont(axisFont);
            plot.getRangeAxis().setLabelFont(axisFont);
            plot.getRangeAxis().setTickLabelFont(axisFont);

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setOpaque(false);
            chartPanel.setPreferredSize(new Dimension(800, 400));

            jBar.removeAll();
            jBar.setLayout(new BorderLayout());
            jBar.add(chartPanel, BorderLayout.CENTER);
            jBar.revalidate();
            jBar.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating the bar chart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        menupanel = new javax.swing.JPanel();
        menu1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menu2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        menu3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        menu4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        menu5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        Dashboardconstantpanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        changingpanel = new javax.swing.JPanel();
        overviewpanel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jPanel65 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jPanel69 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jChart = new javax.swing.JPanel();
        jPie = new javax.swing.JPanel();
        jBar = new javax.swing.JPanel();
        studentmanagement = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel39 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTextField6 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox24 = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        selectedSubjectsTable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jPanel72 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        jAllClassDetailsTable = new javax.swing.JTable();
        jLabel125 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jPanel73 = new javax.swing.JPanel();
        jScrollPane26 = new javax.swing.JScrollPane();
        jStAttendanceTable = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jstID = new javax.swing.JLabel();
        jstName = new javax.swing.JLabel();
        jAttendanceMarkButton = new javax.swing.JButton();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jSID = new javax.swing.JLabel();
        jTName = new javax.swing.JLabel();
        jalbatch = new javax.swing.JLabel();
        jSubject = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel124 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jSAtDate = new javax.swing.JLabel();
        jAttendanceUpdateButton = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jBarcodeScan = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel41 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jBarcodeTable = new javax.swing.JTable();
        jTextField24 = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel38 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jTextField22 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jButton61 = new javax.swing.JButton();
        jTextField41 = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        jStudentAttendanceReportTable = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        teachermanagement = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel44 = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel45 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel49 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jLabel74 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jButton37 = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        teacherViewTable = new javax.swing.JTable();
        jTextField31 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel86 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        teacherAssignmentTable = new javax.swing.JTable();
        jButton59 = new javax.swing.JButton();
        jTextField25 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        teacherClassDetails = new javax.swing.JTable();
        jLabel113 = new javax.swing.JLabel();
        jTeacherClassSearch = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jPanel74 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jteacherAttendanceTable = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jTID = new javax.swing.JLabel();
        jTname = new javax.swing.JLabel();
        jTdate = new javax.swing.JLabel();
        jTtime = new javax.swing.JLabel();
        jTprofile = new javax.swing.JLabel();
        jTmarkButton = new javax.swing.JButton();
        jTupdateButton = new javax.swing.JButton();
        jTclearButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel126 = new javax.swing.JLabel();
        jTsearch = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        jTscheduleID = new javax.swing.JLabel();
        jTeacherBarcodeScan = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jButton44 = new javax.swing.JButton();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jTabbedPane9 = new javax.swing.JTabbedPane();
        jPanel58 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jButton56 = new javax.swing.JButton();
        jTextField38 = new javax.swing.JTextField();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jPanel61 = new javax.swing.JPanel();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jPanel62 = new javax.swing.JPanel();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel63 = new javax.swing.JPanel();
        jButton50 = new javax.swing.JButton();
        jTextField35 = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jLabel52 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jButton54 = new javax.swing.JButton();
        jTextField37 = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTattReportsTable = new javax.swing.JTable();
        jLabel128 = new javax.swing.JLabel();
        subjectmanagement = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel33 = new javax.swing.JPanel();
        jPanel66 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jButton65 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        jButton66 = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        jComboBox23 = new javax.swing.JComboBox<>();
        jLabel84 = new javax.swing.JLabel();
        jComboBox25 = new javax.swing.JComboBox<>();
        jButton57 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton52 = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel73 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jComboBox27 = new javax.swing.JComboBox<>();
        jComboBox28 = new javax.swing.JComboBox<>();
        jButton58 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jLabel75 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jButton45 = new javax.swing.JButton();
        jTextField43 = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jComboBox30 = new javax.swing.JComboBox<>();
        jButton76 = new javax.swing.JButton();
        jButton78 = new javax.swing.JButton();
        jLabel97 = new javax.swing.JLabel();
        jTextField49 = new javax.swing.JTextField();
        jButton79 = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel98 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jButton77 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable19 = new javax.swing.JTable();
        jTextField50 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel32 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jButton68 = new javax.swing.JButton();
        jTextField45 = new javax.swing.JTextField();
        jLabel130 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jButton71 = new javax.swing.JButton();
        jTextField47 = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jButton82 = new javax.swing.JButton();
        jTextField51 = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        profile = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Dashboard");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(16, 16, 16))
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
        jLabel2.setText("Student Management");

        javax.swing.GroupLayout menu2Layout = new javax.swing.GroupLayout(menu2);
        menu2.setLayout(menu2Layout);
        menu2Layout.setHorizontalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(40, 40, 40))
        );
        menu2Layout.setVerticalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addContainerGap(17, Short.MAX_VALUE))
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
        jLabel3.setText("Teacher Management");

        javax.swing.GroupLayout menu3Layout = new javax.swing.GroupLayout(menu3);
        menu3.setLayout(menu3Layout);
        menu3Layout.setHorizontalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(39, 39, 39))
        );
        menu3Layout.setVerticalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(14, 14, 14))
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
        jLabel4.setText("Subject Management");

        javax.swing.GroupLayout menu4Layout = new javax.swing.GroupLayout(menu4);
        menu4.setLayout(menu4Layout);
        menu4Layout.setHorizontalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        menu4Layout.setVerticalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addContainerGap(17, Short.MAX_VALUE))
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

        javax.swing.GroupLayout menu5Layout = new javax.swing.GroupLayout(menu5);
        menu5.setLayout(menu5Layout);
        menu5Layout.setHorizontalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menu5Layout.setVerticalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jButton5.setBackground(new java.awt.Color(0, 102, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Log Out");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menupanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(menu5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menupanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menupanelLayout.setVerticalGroup(
            menupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menupanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(16, 16, 16))
        );

        Dashboardconstantpanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Academic Dashboard");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Welcome Academic,");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Date: 2024-11-29");

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel132.setText("Name");

        javax.swing.GroupLayout DashboardconstantpanelLayout = new javax.swing.GroupLayout(Dashboardconstantpanel);
        Dashboardconstantpanel.setLayout(DashboardconstantpanelLayout);
        DashboardconstantpanelLayout.setHorizontalGroup(
            DashboardconstantpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(DashboardconstantpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(573, Short.MAX_VALUE))
                    .addGroup(DashboardconstantpanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel132)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
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
                    .addComponent(jLabel132))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        overviewpanel.setPreferredSize(new java.awt.Dimension(764, 427));

        jPanel60.setBackground(new java.awt.Color(0, 52, 101));
        jPanel60.setForeground(new java.awt.Color(255, 255, 255));

        jLabel96.setBackground(new java.awt.Color(255, 255, 255));
        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("Teacher Count");

        jLabel99.setBackground(new java.awt.Color(153, 255, 255));
        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 153, 153));
        jLabel99.setText("150");

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel96))
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel99)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addComponent(jLabel96)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel99)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel65.setBackground(new java.awt.Color(0, 52, 101));
        jPanel65.setForeground(new java.awt.Color(255, 255, 255));

        jLabel69.setBackground(new java.awt.Color(255, 255, 255));
        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Student Count");

        jLabel95.setBackground(new java.awt.Color(255, 255, 102));
        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 153));
        jLabel95.setText("150");

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel69))
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel95)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel95)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel69.setBackground(new java.awt.Color(0, 52, 101));
        jPanel69.setForeground(new java.awt.Color(255, 255, 255));

        jLabel100.setBackground(new java.awt.Color(255, 255, 255));
        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("Subject Count");

        jLabel101.setBackground(new java.awt.Color(255, 153, 153));
        jLabel101.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(153, 255, 255));
        jLabel101.setText("150");

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100)
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel101)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel101))
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jChart.setBackground(new java.awt.Color(0, 52, 101));

        jPie.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPieLayout = new javax.swing.GroupLayout(jPie);
        jPie.setLayout(jPieLayout);
        jPieLayout.setHorizontalGroup(
            jPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPieLayout.setVerticalGroup(
            jPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jBar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jBarLayout = new javax.swing.GroupLayout(jBar);
        jBar.setLayout(jBarLayout);
        jBarLayout.setHorizontalGroup(
            jBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jBarLayout.setVerticalGroup(
            jBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jChartLayout = new javax.swing.GroupLayout(jChart);
        jChart.setLayout(jChartLayout);
        jChartLayout.setHorizontalGroup(
            jChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jChartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jChartLayout.setVerticalGroup(
            jChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jChartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(jChart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addComponent(jChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout overviewpanelLayout = new javax.swing.GroupLayout(overviewpanel);
        overviewpanel.setLayout(overviewpanelLayout);
        overviewpanelLayout.setHorizontalGroup(
            overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        overviewpanelLayout.setVerticalGroup(
            overviewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        studentmanagement.setPreferredSize(new java.awt.Dimension(764, 427));

        jButton25.setBackground(new java.awt.Color(0, 52, 101));
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("Upload");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25)
                .addContainerGap())
        );

        jLabel11.setText("Generated Bar Code");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setText("Personal Details");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("First Name");

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("Last Name");

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("NIC");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("Mobile");

        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Date of Birth");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("Gender");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Male");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Female");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText("Address Line 01");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setText("Class Details");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("Batch");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Stream");

        jButton7.setBackground(new java.awt.Color(0, 52, 101));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Register");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(0, 52, 101));
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Clear");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(0, 52, 101));
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Update");
        jButton24.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jButton24ComponentAdded(evt);
            }
        });
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 52, 101));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Guardian Mobile");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Enrollment Date");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("Address Line 02");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jTextField19)
                                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(40, 40, 40)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                        .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(40, 40, 40)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel47)
                                .addGap(0, 0, 0)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel15))
                                .addGap(0, 0, 0)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel42))
                        .addGap(0, 0, 0)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, 0)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel20))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel44)
                .addGap(0, 0, 0)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel37)
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel45)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel14);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Student Registration", jPanel18);

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "First Name", "Last Name", "NIC", "Mobile", "DOB", "guardian", "enrolled", "gender", "Address Line1 ", "address Line2", "Batch", "Stream", "Barcode_Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studentTable);

        jButton10.setBackground(new java.awt.Color(0, 52, 101));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Print");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });

        jLabel49.setText("single click row to update regisration, double click to delete");

        jLabel77.setText("Search");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField8)
                            .addComponent(jLabel49)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel77)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View Student Details", jPanel19);

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel43.setText("Student selected subjects");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("Subject");

        jLabel27.setText("Search Student and Select from dropdown:");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        selectedSubjectsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name", "NIC", "subject "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectedSubjectsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedSubjectsTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(selectedSubjectsTable);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton3.setBackground(new java.awt.Color(0, 52, 101));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 52, 101));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(0, 52, 101));
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setText("Delete");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton46.setBackground(new java.awt.Color(0, 52, 101));
        jButton46.setForeground(new java.awt.Color(255, 255, 255));
        jButton46.setText("Clear");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addGap(0, 0, 0)
                                .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton46, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton32)
                        .addComponent(jButton46))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Student Subject", jPanel9);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Student Profile", jPanel15);

        jAllClassDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "schedule id", "Teacher Name", "A/L Batch", "subject", "date", "time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jAllClassDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAllClassDetailsTableMouseClicked(evt);
            }
        });
        jScrollPane25.setViewportView(jAllClassDetailsTable);

        jLabel125.setText("Search");

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jButton13.setText("Refresh");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel125)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel125)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("Student Class Details", jPanel72);

        jPanel73.setName(""); // NOI18N

        jStAttendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "name", "status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jStAttendanceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jStAttendanceTableMouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(jStAttendanceTable);

        jButton21.setBackground(new java.awt.Color(0, 52, 101));
        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Select Class");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel117.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel117.setText("Student ID :");

        jLabel118.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel118.setText("Student Name :");

        jstID.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jstID.setText("Student ID");

        jstName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jstName.setText("Student Name");

        jAttendanceMarkButton.setBackground(new java.awt.Color(0, 52, 101));
        jAttendanceMarkButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jAttendanceMarkButton.setForeground(new java.awt.Color(255, 255, 255));
        jAttendanceMarkButton.setText("Mark Attendance");
        jAttendanceMarkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAttendanceMarkButtonActionPerformed(evt);
            }
        });

        jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(51, 0, 255));
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel119.setText("Schedule ID :");

        jLabel120.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(51, 0, 255));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel120.setText("Teacher Name :");

        jLabel121.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(51, 0, 255));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel121.setText("A/L Batch :");

        jLabel122.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(51, 0, 255));
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel122.setText("Subject :");

        jSID.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jSID.setForeground(new java.awt.Color(51, 0, 255));
        jSID.setText("Schedule ID");
        jSID.setName(""); // NOI18N

        jTName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTName.setForeground(new java.awt.Color(51, 0, 255));
        jTName.setText("Teacher Name");
        jTName.setName(""); // NOI18N

        jalbatch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jalbatch.setForeground(new java.awt.Color(51, 0, 255));
        jalbatch.setText("A/L Batch");
        jalbatch.setName(""); // NOI18N

        jSubject.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jSubject.setForeground(new java.awt.Color(51, 0, 255));
        jSubject.setText("Subject");
        jSubject.setName(""); // NOI18N

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel123.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(0, 0, 255));
        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel123.setText("Date :");

        jSAtDate.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jSAtDate.setForeground(new java.awt.Color(0, 0, 255));
        jSAtDate.setText("Date");
        jSAtDate.setName(""); // NOI18N

        jAttendanceUpdateButton.setBackground(new java.awt.Color(0, 52, 101));
        jAttendanceUpdateButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jAttendanceUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        jAttendanceUpdateButton.setText("Update Attendance");
        jAttendanceUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAttendanceUpdateButtonActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(0, 52, 101));
        jButton18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Clear All");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel19.setText("Search");

        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField20KeyReleased(evt);
            }
        });

        jBarcodeScan.setForeground(new java.awt.Color(255, 0, 0));
        jBarcodeScan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jBarcodeScanKeyTyped(evt);
            }
        });

        jLabel21.setText("Scan Barcode :");

        jButton14.setBackground(new java.awt.Color(0, 52, 101));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Refresh");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel118, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jstID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGroup(jPanel73Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jBarcodeScan))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                            .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel122, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel121, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel120, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addComponent(jLabel119, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel73Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSAtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jalbatch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel73Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addComponent(jAttendanceUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addComponent(jAttendanceMarkButton, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addGap(0, 78, Short.MAX_VALUE)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel119)
                    .addComponent(jSID))
                .addGap(0, 0, 0)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel120)
                    .addComponent(jTName))
                .addGap(0, 0, 0)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel121)
                    .addComponent(jalbatch))
                .addGap(0, 0, 0)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel122)
                    .addComponent(jSubject))
                .addGap(0, 0, 0)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel123)
                    .addComponent(jSAtDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jBarcodeScan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                        .addComponent(jLabel117)
                        .addGap(0, 0, 0)
                        .addComponent(jstID)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel118)
                        .addGap(0, 0, 0)
                        .addComponent(jstName))
                    .addComponent(jLabel124, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jAttendanceMarkButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jAttendanceUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jButton14))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Student Attendance", jPanel73);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Barcode view here");
        jLabel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton8.setBackground(new java.awt.Color(0, 52, 101));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Print Barcode");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Name:");

        jLabel102.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel102.setText("NIC :");

        jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel94.setText("NIC");

        jLabel106.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel106.setText("Student Name");

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setToolTipText("");
        jLabel103.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(279, 279, 279)
                                .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel105)
                .addGap(17, 17, 17)
                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(jLabel94))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBarcodeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Barcode ID", "Student Name", "NIC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jBarcodeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBarcodeTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jBarcodeTable);

        jTextField24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField24KeyReleased(evt);
            }
        });

        jLabel104.setText("Search");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel104)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("View Barcode", jPanel41);

        jButton29.setBackground(new java.awt.Color(0, 52, 101));
        jButton29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("View Profile");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(0, 52, 101));
        jButton30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("Mark Attendance Manually");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setBackground(new java.awt.Color(0, 52, 101));
        jButton31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("View Reports");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(87, 87, 87))
        );

        jTabbedPane4.addTab("Quick Access", jPanel43);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane4)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Student Barcode", jPanel5);

        jButton27.setBackground(new java.awt.Color(0, 52, 101));
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Print");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jTextField22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField22KeyReleased(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "First Name", "Last Name", "Nic", "Mobile", "Date of Birth", "Gurdian Mobile", "Enrollment Date", "Gender", "Address line1", "Address line 2", "Batch", "Stream"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable4);

        jLabel22.setText("Search");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(0, 357, Short.MAX_VALUE)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane5)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField22)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jTabbedPane3.addTab("Student Enrollment Reports", jPanel38);

        jButton61.setBackground(new java.awt.Color(0, 52, 101));
        jButton61.setForeground(new java.awt.Color(255, 255, 255));
        jButton61.setText("Print");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        jTextField41.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField41KeyReleased(evt);
            }
        });

        jStudentAttendanceReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Month", "A/L Batch", "Subject", "Teacher", "Total Records", "Total_Present", "Total_Absent", "Present %", "Absent %"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane19.setViewportView(jStudentAttendanceReportTable);

        jLabel51.setText("Search");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField41)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Class Attendance Report", jPanel31);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Student Reports", jPanel6);

        javax.swing.GroupLayout studentmanagementLayout = new javax.swing.GroupLayout(studentmanagement);
        studentmanagement.setLayout(studentmanagementLayout);
        studentmanagementLayout.setHorizontalGroup(
            studentmanagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        studentmanagementLayout.setVerticalGroup(
            studentmanagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentmanagementLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, Short.MAX_VALUE))
        );

        teachermanagement.setPreferredSize(new java.awt.Dimension(764, 427));

        jButton33.setBackground(new java.awt.Color(0, 52, 101));
        jButton33.setForeground(new java.awt.Color(255, 255, 255));
        jButton33.setText("Upload");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton33)
                .addContainerGap())
        );

        jLabel31.setText("Generated Bar Code");

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel48.setText("Personal Details");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("First Name");

        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Last Name");

        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel56.setText("NIC");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel57.setText("Mobile");

        jTextField29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField29ActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setText("Date of Birth");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setText("Gender");

        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setText("Male");

        buttonGroup3.add(jRadioButton4);
        jRadioButton4.setText("Female");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel60.setText("Address");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setText("Line 01");

        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setText("Line 02");

        jButton17.setBackground(new java.awt.Color(0, 52, 101));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Register");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton35.setBackground(new java.awt.Color(0, 52, 101));
        jButton35.setForeground(new java.awt.Color(255, 255, 255));
        jButton35.setText("Clear");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setBackground(new java.awt.Color(0, 52, 101));
        jButton36.setForeground(new java.awt.Color(255, 255, 255));
        jButton36.setText("Update");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel74.setText("Contact Details");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Email");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel78.setText("Enrollment Date");

        jButton37.setBackground(new java.awt.Color(0, 52, 101));
        jButton37.setForeground(new java.awt.Color(255, 255, 255));
        jButton37.setText("Delete");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                                    .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateChooser4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40)
                                    .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel49Layout.createSequentialGroup()
                                            .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel58)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel34)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel49Layout.createSequentialGroup()
                                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel50))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel58))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4)))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jLabel34))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jScrollPane8.setViewportView(jPanel49);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane8.addTab("Teacher Registration", jPanel45);

        teacherViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "First Name", "Last Name", "NIC", "DOB", "Gender", "Mobile", "Email", "Address line 2", "Address line 2", "Barcode ID", "Enrollment Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teacherViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherViewTableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(teacherViewTable);

        jTextField31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField31KeyReleased(evt);
            }
        });

        jLabel65.setText("Search");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel65)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane8.addTab("View Teacher Details", jPanel50);

        jLabel79.setText("Teacher ID");

        jLabel82.setText(" A/L batch");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel86.setText("Subject");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel63.setText("Stream");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Add Class");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton53.setText("Select Teacher");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        teacherAssignmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "Name", "A/L Batch", "Stream", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teacherAssignmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherAssignmentTableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(teacherAssignmentTable);

        jButton59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton59.setText("Clear");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });
        jTextField25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField25KeyReleased(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 51, 0));
        jLabel64.setText("Teacher ID");
        jLabel64.setToolTipText("");

        jLabel67.setText("Search");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                        .addComponent(jLabel79)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42))
                                    .addGroup(jPanel30Layout.createSequentialGroup()
                                        .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel82)
                                .addGap(0, 0, 0)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)))
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane12)))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jButton53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79)))
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel82)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel63))
                                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel86)))))
                        .addGap(2, 2, 2)))
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67))
                    .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Teacher Assignment", jPanel30);

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane8)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane8)
                .addContainerGap())
        );

        jTabbedPane7.addTab("Teacher Profile", jPanel44);

        teacherClassDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Teacher ID", "Teacher Name", "A/L Batch", "Stream ", "Subject", "Schedule ID", "Scheduled Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane24.setViewportView(teacherClassDetails);

        jLabel113.setText("Search Teacher ID / Name ");

        jTeacherClassSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTeacherClassSearchKeyReleased(evt);
            }
        });

        jButton9.setText("Refresh");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel113)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTeacherClassSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTeacherClassSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("Teacher Class Details", jPanel25);

        jteacherAttendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule ID", "Teacher ID", "Teacher Name", "A/L Batch", "Subject", "Schedule Date", "Time", "Attendance Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jteacherAttendanceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jteacherAttendanceTableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jteacherAttendanceTable);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Teacher ID:");

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel76.setText("Teacher Name:");

        jLabel114.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel114.setText("Date:");

        jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel115.setText("Time:");

        jTID.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTID.setText("Teacher ID");

        jTname.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTname.setText("Teacher Name");

        jTdate.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTdate.setText("Date");

        jTtime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTtime.setText("Time");

        jTmarkButton.setText("Mark Attendance");
        jTmarkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTmarkButtonActionPerformed(evt);
            }
        });

        jTupdateButton.setText("Update Attendance");
        jTupdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTupdateButtonActionPerformed(evt);
            }
        });

        jTclearButton.setText("Clear");
        jTclearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTclearButtonActionPerformed(evt);
            }
        });

        jLabel126.setText("Search");

        jTsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTsearchKeyReleased(evt);
            }
        });

        jLabel116.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel116.setText("Schedule ID:");

        jTscheduleID.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTscheduleID.setText("Schedule ID");

        jTeacherBarcodeScan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTeacherBarcodeScan.setForeground(new java.awt.Color(255, 0, 0));
        jTeacherBarcodeScan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTeacherBarcodeScanKeyTyped(evt);
            }
        });

        jLabel28.setText("Scan Barcode :");

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(0, 0, 0)
                        .addComponent(jTeacherBarcodeScan, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jTprofile, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel114, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel115, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel116, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                .addComponent(jTID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTtime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jTscheduleID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTmarkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTupdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                            .addComponent(jTclearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addComponent(jTmarkButton)
                                .addGap(15, 15, 15)
                                .addComponent(jTupdateButton))
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTscheduleID)
                                    .addComponent(jLabel116))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(jTID))
                                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTname)
                                    .addComponent(jLabel76))))
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel114)
                                    .addComponent(jTdate))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel115)
                                    .addComponent(jTtime)))
                            .addGroup(jPanel74Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTclearButton))))
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jTprofile, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel126)
                        .addComponent(jTsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTeacherBarcodeScan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane7.addTab("Teacher Attendance", jPanel74);

        jButton44.setBackground(new java.awt.Color(0, 52, 101));
        jButton44.setForeground(new java.awt.Color(255, 255, 255));
        jButton44.setText("Print Barcode");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jLabel107.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel107.setText("Teacher Name :");

        jLabel108.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel108.setText("NIC :");

        jLabel109.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel109.setText("Teacher Name");

        jLabel110.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel110.setText("NIC");

        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel112.setText("Barcode View here");
        jLabel112.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel55Layout.createSequentialGroup()
                                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel107)
                    .addComponent(jLabel109))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(jLabel110))
                .addGap(25, 25, 25)
                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton56.setBackground(new java.awt.Color(0, 52, 101));
        jButton56.setForeground(new java.awt.Color(255, 255, 255));
        jButton56.setText("Search");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "Barcode ID", "Teacher Name", "NIC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable14MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(jTable14);

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addGap(0, 73, Short.MAX_VALUE)
                        .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane9.addTab("Marked Teachers", jPanel58);

        jButton47.setBackground(new java.awt.Color(0, 52, 101));
        jButton47.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton47.setForeground(new java.awt.Color(255, 255, 255));
        jButton47.setText("View Profile");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setBackground(new java.awt.Color(0, 52, 101));
        jButton48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton48.setForeground(new java.awt.Color(255, 255, 255));
        jButton48.setText("Mark Attendance Manually");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jButton49.setBackground(new java.awt.Color(0, 52, 101));
        jButton49.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton49.setForeground(new java.awt.Color(255, 255, 255));
        jButton49.setText("View Reports");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(jButton48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(87, 87, 87))
        );

        jTabbedPane9.addTab("Quick Access", jPanel61);

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addComponent(jTabbedPane9)
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane9))
        );

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane7.addTab("Teacher Barcode", jPanel54);

        jButton50.setBackground(new java.awt.Color(0, 52, 101));
        jButton50.setForeground(new java.awt.Color(255, 255, 255));
        jButton50.setText("Print");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jTextField35.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField35KeyReleased(evt);
            }
        });

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "First Name", "Last Name", "Nic", "Mobile", "Email", "Date of Birth", "Enrollment Date", "Gender", "Address line1", "Address line2", "Stream", "Subject"
            }
        ));
        jScrollPane13.setViewportView(jTable11);

        jLabel52.setText("Search");

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel52)
                .addGap(18, 18, 18)
                .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane10.addTab("Teacher Enrollment Reports", jPanel63);

        jButton54.setBackground(new java.awt.Color(0, 52, 101));
        jButton54.setForeground(new java.awt.Color(255, 255, 255));
        jButton54.setText("Print");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jTextField37.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField37KeyReleased(evt);
            }
        });

        jTattReportsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Month", "AL_Batch", "Subject", "Teacher Name", "Total Records", "Total Present", "Total Absent", "Present %", "Absent %"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jTattReportsTable);

        jLabel128.setText("Search");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jLabel128)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField37)
                    .addComponent(jLabel128))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane10.addTab("Class Attendance Report", jPanel29);

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane10)
                .addContainerGap())
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addComponent(jTabbedPane10)
                .addContainerGap())
        );

        jTabbedPane7.addTab("Teacher Reports", jPanel62);

        javax.swing.GroupLayout teachermanagementLayout = new javax.swing.GroupLayout(teachermanagement);
        teachermanagement.setLayout(teachermanagementLayout);
        teachermanagementLayout.setHorizontalGroup(
            teachermanagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );
        teachermanagementLayout.setVerticalGroup(
            teachermanagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teachermanagementLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        subjectmanagement.setPreferredSize(new java.awt.Dimension(764, 427));

        jTabbedPane6.setPreferredSize(new java.awt.Dimension(552, 410));

        jLabel80.setText("Stream");

        jButton65.setBackground(new java.awt.Color(0, 52, 101));
        jButton65.setForeground(new java.awt.Color(255, 255, 255));
        jButton65.setText("Add");
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });

        jLabel81.setText("Subject");

        jTextField42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField42ActionPerformed(evt);
            }
        });

        jButton66.setBackground(new java.awt.Color(0, 52, 101));
        jButton66.setForeground(new java.awt.Color(255, 255, 255));
        jButton66.setText("Add");
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });

        jLabel83.setText("Stream");

        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel84.setText("Subject");

        jComboBox25.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton57.setBackground(new java.awt.Color(0, 52, 101));
        jButton57.setForeground(new java.awt.Color(255, 255, 255));
        jButton57.setText("Clear");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });

        jButton69.setBackground(new java.awt.Color(0, 52, 101));
        jButton69.setForeground(new java.awt.Color(255, 255, 255));
        jButton69.setText("Add");
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });

        jButton52.setBackground(new java.awt.Color(0, 52, 101));
        jButton52.setForeground(new java.awt.Color(255, 255, 255));
        jButton52.setText("Add");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jLabel70.setText("Batch");

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel72.setText("Batch");

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(jTextField42, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel66Layout.createSequentialGroup()
                                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton66, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                        .addComponent(jButton57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox23, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox25, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel70)
                .addGap(0, 0, 0)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton52))
                .addGap(15, 15, 15)
                .addComponent(jLabel80)
                .addGap(0, 0, 0)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton65))
                .addGap(15, 15, 15)
                .addComponent(jLabel81)
                .addGap(0, 0, 0)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton66))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel72)
                .addGap(0, 0, 0)
                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel83)
                .addGap(0, 0, 0)
                .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel84)
                .addGap(0, 0, 0)
                .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Batch", "Stream", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jLabel73.setText(" Double click to delete");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane6.addTab("All Subjects", jPanel33);

        jLabel85.setText("Teacher");

        jTextField44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField44ActionPerformed(evt);
            }
        });

        jLabel87.setText("Subject");

        jLabel88.setText("Date");

        jLabel89.setText("Start Time");

        jComboBox27.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox27ActionPerformed(evt);
            }
        });

        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton58.setBackground(new java.awt.Color(0, 52, 101));
        jButton58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton58.setForeground(new java.awt.Color(255, 255, 255));
        jButton58.setText("Clear");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        jButton72.setBackground(new java.awt.Color(0, 52, 101));
        jButton72.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton72.setForeground(new java.awt.Color(255, 255, 255));
        jButton72.setText("Update");
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });

        jButton73.setBackground(new java.awt.Color(0, 52, 101));
        jButton73.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton73.setForeground(new java.awt.Color(255, 255, 255));
        jButton73.setText("Add");
        jButton73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton73ActionPerformed(evt);
            }
        });

        jLabel91.setText("Batch");

        jLabel93.setText("End Time");

        jTextField46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField46ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel75.setText("single click row to update regisration, double click to delete");

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton72, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88)
                            .addComponent(jLabel91))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox27, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel67Layout.createSequentialGroup()
                                .addComponent(jLabel85)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel67Layout.createSequentialGroup()
                                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel75)
                .addContainerGap())
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel75)
                .addGap(18, 18, 18)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel88)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89)
                    .addComponent(jLabel93))
                .addGap(0, 0, 0)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule ID", "Date", "Batch", "Subject", "Teacher", "Start Time", "End Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable9);

        jButton45.setBackground(new java.awt.Color(0, 52, 101));
        jButton45.setForeground(new java.awt.Color(255, 255, 255));
        jButton45.setText("Print");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jTextField43.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField43KeyReleased(evt);
            }
        });

        jLabel131.setText("Search");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 48, Short.MAX_VALUE)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField43)
                    .addComponent(jLabel131))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("Class Schedule", jPanel34);

        jLabel90.setText("Document Name");

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select File Type", "docx", "pdf", "png", "jpg", "rar", "zip" }));

        jButton76.setBackground(new java.awt.Color(0, 52, 101));
        jButton76.setForeground(new java.awt.Color(255, 255, 255));
        jButton76.setText("Clear");
        jButton76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton76ActionPerformed(evt);
            }
        });

        jButton78.setBackground(new java.awt.Color(0, 52, 101));
        jButton78.setForeground(new java.awt.Color(255, 255, 255));
        jButton78.setText("Add");
        jButton78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton78ActionPerformed(evt);
            }
        });

        jLabel97.setText("Description");

        jButton79.setBackground(new java.awt.Color(0, 52, 101));
        jButton79.setForeground(new java.awt.Color(255, 255, 255));
        jButton79.setText("Delete");
        jButton79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton79ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane22.setViewportView(jTextArea1);

        jLabel98.setText("File Type");

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        jLabel53.setText("Select File");

        jLabel54.setText("Date");

        jButton77.setBackground(new java.awt.Color(0, 52, 101));
        jButton77.setForeground(new java.awt.Color(255, 255, 255));
        jButton77.setText("Update");
        jButton77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton77ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox30, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane22)
                    .addComponent(jTextField49)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseButton))
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel97)
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54)
                            .addGroup(jPanel68Layout.createSequentialGroup()
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton76, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(jButton78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton79, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel68Layout.createSequentialGroup()
                                        .addComponent(jButton77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(2, 2, 2)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(0, 0, 0)
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel90)
                .addGap(0, 0, 0)
                .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel97)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel98)
                .addGap(0, 0, 0)
                .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel53)
                .addGap(0, 0, 0)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton78)
                    .addComponent(jButton77))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton79)
                    .addComponent(jButton76))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable19.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Document ID", "Document Name", "Description", "File Type", "File Path", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jScrollPane21.setViewportView(jTable19);

        jTextField50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField50KeyReleased(evt);
            }
        });

        jLabel55.setText("Search");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel55)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField50)
                    .addComponent(jLabel55))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane6.addTab("Material Library", jPanel35);

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule ID", "Schedule Date", "Batch", "Subject", "Teacher ", "Start Time", "End Time"
            }
        ));
        jScrollPane17.setViewportView(jTable15);

        jButton68.setBackground(new java.awt.Color(0, 52, 101));
        jButton68.setForeground(new java.awt.Color(255, 255, 255));
        jButton68.setText("Print");
        jButton68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton68ActionPerformed(evt);
            }
        });

        jTextField45.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField45KeyReleased(evt);
            }
        });

        jLabel130.setText("Search");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField45)
                    .addComponent(jLabel130))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 789, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane5.addTab("Class Schedule Report", jPanel32);

        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject ID", "Subject", "Schedule ID", "Schedule Date", "Stream", "Batch", "Teacher"
            }
        ));
        jScrollPane20.setViewportView(jTable18);

        jButton71.setBackground(new java.awt.Color(0, 52, 101));
        jButton71.setForeground(new java.awt.Color(255, 255, 255));
        jButton71.setText("Print");
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });

        jTextField47.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField47KeyReleased(evt);
            }
        });

        jLabel129.setText("Search");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField47)
                    .addComponent(jLabel129))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 789, Short.MAX_VALUE)
            .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel64Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
            .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel64Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane5.addTab("Subject Management Report", jPanel64);

        jTable20.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Document ID", "Document Name", "Description", "File Type", "Date"
            }
        ));
        jScrollPane23.setViewportView(jTable20);

        jButton82.setBackground(new java.awt.Color(0, 52, 101));
        jButton82.setForeground(new java.awt.Color(255, 255, 255));
        jButton82.setText("Print");
        jButton82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton82ActionPerformed(evt);
            }
        });

        jTextField51.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField51KeyReleased(evt);
            }
        });

        jLabel127.setText("Search");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton82, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField51)
                    .addComponent(jLabel127))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 789, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane5.addTab("Material Report", jPanel10);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5)
                .addContainerGap())
        );

        jTabbedPane6.addTab("Subject Reports", jPanel36);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout subjectmanagementLayout = new javax.swing.GroupLayout(subjectmanagement);
        subjectmanagement.setLayout(subjectmanagementLayout);
        subjectmanagementLayout.setHorizontalGroup(
            subjectmanagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        subjectmanagementLayout.setVerticalGroup(
            subjectmanagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        profile.setPreferredSize(new java.awt.Dimension(764, 427));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("First Name");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Last Name");

        jButton11.setBackground(new java.awt.Color(0, 52, 101));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Change Password");

        jButton15.setBackground(new java.awt.Color(0, 52, 101));
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Update");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Email");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Password");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Mobile No :");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("NIC");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(112, 112, 112)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addGap(3, 3, 3)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10))
                .addGap(27, 27, 27)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(3, 3, 3)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(3, 3, 3)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18))
                .addGap(36, 36, 36)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        jButton12.setBackground(new java.awt.Color(0, 52, 101));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Upload Image");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout profileLayout = new javax.swing.GroupLayout(profile);
        profile.setLayout(profileLayout);
        profileLayout.setHorizontalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
            .addGroup(profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        profileLayout.setVerticalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
            .addGroup(profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(profileLayout.createSequentialGroup()
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout changingpanelLayout = new javax.swing.GroupLayout(changingpanel);
        changingpanel.setLayout(changingpanelLayout);
        changingpanelLayout.setHorizontalGroup(
            changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 807, Short.MAX_VALUE)
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addComponent(studentmanagement, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addComponent(subjectmanagement, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addComponent(profile, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changingpanelLayout.createSequentialGroup()
                    .addComponent(teachermanagement, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(overviewpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        changingpanelLayout.setVerticalGroup(
            changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(studentmanagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(subjectmanagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(profile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(teachermanagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
            .addGroup(changingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(changingpanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(overviewpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menupanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changingpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Dashboardconstantpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menupanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Dashboardconstantpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changingpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //sidemenupanelbuttoncode
    private void menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(true);
        studentmanagement.setVisible(false);
        teachermanagement.setVisible(false);
        subjectmanagement.setVisible(false);
        profile.setVisible(false);

        menu1.setBackground(new Color(5, 93, 165));
        menu2.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));

    }//GEN-LAST:event_menu1MouseClicked

    private void menu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(false);
        studentmanagement.setVisible(true);
        teachermanagement.setVisible(false);
        subjectmanagement.setVisible(false);
        profile.setVisible(false);

        menu2.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
    }//GEN-LAST:event_menu2MouseClicked

    private void menu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(false);
        studentmanagement.setVisible(false);
        teachermanagement.setVisible(true);
        subjectmanagement.setVisible(false);
        profile.setVisible(false);

        menu3.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));

    }//GEN-LAST:event_menu3MouseClicked

    private void menu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(false);
        studentmanagement.setVisible(false);
        teachermanagement.setVisible(false);
        subjectmanagement.setVisible(true);
        profile.setVisible(false);

        menu4.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu5.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));

    }//GEN-LAST:event_menu4MouseClicked

    private void menu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu5MouseClicked
        // TODO add your handling code here:

        overviewpanel.setVisible(false);
        studentmanagement.setVisible(false);
        teachermanagement.setVisible(false);
        subjectmanagement.setVisible(false);
        profile.setVisible(true);

        menu5.setBackground(new Color(5, 93, 165));
        menu1.setBackground(new Color(0, 52, 101));
        menu3.setBackground(new Color(0, 52, 101));
        menu2.setBackground(new Color(0, 52, 101));
        menu4.setBackground(new Color(0, 52, 101));
    }//GEN-LAST:event_menu5MouseClicked

    private void loadStudents() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `students` "
                    + "INNER JOIN `gender` ON `students`.`gender_id`=`gender`.`id` "
                    + "INNER JOIN `AL_batch` ON `students`.`AL_batch_batch_id`=`AL_batch`.`batch_id` "
                    + "INNER JOIN `stream` ON `students`.`stream_stream_id`=`stream`.`stream_id` ");

            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
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
                vector.add(resultSet.getString("barcode_id"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSelectedSubjects() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `students_has_subjects` "
                    + "INNER JOIN `students` ON `students_has_subjects`.`students_student_id`=`students`.`student_id` "
                    + "INNER JOIN `subjects` ON `students_has_subjects`.`subjects_subject_id`=`subjects`.`subject_id` ");

            DefaultTableModel model = (DefaultTableModel) selectedSubjectsTable.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector vector = new Vector();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("students.first_name"));
                vector.add(resultSet.getString("students.nic"));
                vector.add(resultSet.getString("subjects.subject_name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubStre() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `stream_subject`"
                    + "INNER JOIN `stream` ON `stream_subject`.`stream_stream_id`=`stream`.`stream_id`"
                    + "INNER JOIN `subjects` ON `stream_subject`.`subjects_subject_id`=`subjects`.`subject_id`"
                    + "INNER JOIN `AL_batch` ON `stream_subject`.`AL_batch_batch_id`=`AL_batch`.`batch_id`");

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("AL_batch.batch_name"));
                vector.add(resultSet.getString("stream.stream_name"));
                vector.add(resultSet.getString("subjects.subject_name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadshedule() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `schedule` INNER JOIN `AL_batch`"
                    + "ON `schedule`.`AL_batch_batch_id` = `AL_batch`.`batch_id` INNER JOIN `subjects` "
                    + "ON `schedule`.`subject_id` = `subjects`.`subject_id` INNER JOIN `teachers` "
                    + "ON `schedule`.`teacher_id` = `teachers`.`teacher_id`");

            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
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

    private void loadMaterialLibraryTable() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `documents`");

            DefaultTableModel model = (DefaultTableModel) jTable19.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector vector = new Vector();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("file_name"));
                vector.add(resultSet.getString("description"));
                vector.add(resultSet.getString("document_type"));
                vector.add(resultSet.getString("file_path"));
                vector.add(resultSet.getString("upload_date"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMaterialSelectedRowData() {
        int selectedRow = jTable19.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel dtm = (DefaultTableModel) jTable19.getModel();

            String Date = String.valueOf(dtm.getValueAt(selectedRow, 5));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;

            try {
                date = sdf.parse(Date);
            } catch (ParseException ex) {
                Logger.getLogger(AcademicDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            jDateChooser3.setDate(date);
            jTextField49.setText(dtm.getValueAt(selectedRow, 1).toString());
            jTextArea1.setText(dtm.getValueAt(selectedRow, 2).toString());
            jComboBox30.setSelectedItem(dtm.getValueAt(selectedRow, 3).toString());
            jTextField9.setText(dtm.getValueAt(selectedRow, 4).toString());

        }

    }

    private void loadTeacherViewTable() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT *From `teachers` INNER JOIN `gender` ON teachers.gender_id=gender.id");
            DefaultTableModel model = (DefaultTableModel) teacherViewTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("teacher_id"));
                v.add(rs.getString("first_name"));
                v.add(rs.getString("last_name"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("gender.type"));
                v.add(rs.getString("mobile"));
                v.add(rs.getString("email"));
                v.add(rs.getString("address_line_1"));
                v.add(rs.getString("address_line_2"));
                v.add(rs.getString("barcode_id"));
                v.add(rs.getString("enrollment_date"));

                model.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTeacherViewRowData() {
        //set update fields
        int selectedRow = teacherViewTable.getSelectedRow();

        try {
            // Extract data from the selected row
            String fname = String.valueOf(teacherViewTable.getValueAt(selectedRow, 1));
            String lname = String.valueOf(teacherViewTable.getValueAt(selectedRow, 2));
            String nic = String.valueOf(teacherViewTable.getValueAt(selectedRow, 3));
            String dob = String.valueOf(teacherViewTable.getValueAt(selectedRow, 4));
            String gender = String.valueOf(teacherViewTable.getValueAt(selectedRow, 5));
            String mobile = String.valueOf(teacherViewTable.getValueAt(selectedRow, 6));
            String email = String.valueOf(teacherViewTable.getValueAt(selectedRow, 7));
            String addressL1 = String.valueOf(teacherViewTable.getValueAt(selectedRow, 8));
            String addressL2 = String.valueOf(teacherViewTable.getValueAt(selectedRow, 9));
            String barcodeID = String.valueOf(teacherViewTable.getValueAt(selectedRow, 10));
            String enrollmentDate = String.valueOf(teacherViewTable.getValueAt(selectedRow, 11));

            // Set data to text fields
            jTextField26.setText(fname);
            jTextField27.setText(lname);
            jTextField28.setText(nic);

            // Parse and set dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            jDateChooser6.setDate(dateFormat.parse(dob));
            jDateChooser4.setDate(dateFormat.parse(enrollmentDate));

            // Set gender radio buttons
            if (gender.equalsIgnoreCase("Male")) {
                jRadioButton3.setSelected(true);
            } else if (gender.equalsIgnoreCase("Female")) {
                jRadioButton4.setSelected(true);
            }

            jTextField11.setText(addressL1);
            jTextField30.setText(addressL2);
            jTextField29.setText(mobile);
            jTextField21.setText(email);

            //image set 
            try {
                ResultSet rs = MySQL.executeSearch("SELECT img_path FROM teachers WHERE teacher_id = '" + String.valueOf(teacherViewTable.getValueAt(selectedRow, 0)) + "'");
                if (rs.next()) {
                    TimgPath = rs.getString("img_path");

                    if (TimgPath != null) {
                        File imageFile = new File(TimgPath);

                        if (imageFile.exists()) {
                            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());

                            Image image = imageIcon.getImage().getScaledInstance(jLabel30.getWidth(), jLabel30.getHeight(), Image.SCALE_SMOOTH);
                            jLabel30.setIcon(new ImageIcon(image));

                        } else {
                            jLabel30.setIcon(null);
                        }
                    } else {
                        jLabel30.setIcon(null);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading teacher data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void loadTeacherAssignmentTable() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM teachers_has_stream_subject INNER JOIN  stream_subject "
                    + "ON teachers_has_stream_subject.stream_subject_id=stream_subject.id  INNER JOIN stream ON"
                    + " stream.stream_id=stream_subject.stream_stream_id  INNER JOIN subjects ON "
                    + "subjects.subject_id=stream_subject.subjects_subject_id INNER JOIN AL_batch "
                    + "ON AL_batch.batch_id=stream_subject.AL_batch_batch_id INNER JOIN teachers ON "
                    + "teachers.teacher_id=teachers_has_stream_subject.teachers_teacher_id");

            DefaultTableModel model = (DefaultTableModel) teacherAssignmentTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("teachers_teacher_id"));
                v.add(rs.getString("teachers.first_name") + rs.getString("teachers.last_name"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("stream_name"));
                v.add(rs.getString("subject_name"));

                model.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRowTeacherAssignmentTable() {
        int SelectedRow = teacherAssignmentTable.getSelectedRow();

        String tID = String.valueOf(teacherAssignmentTable.getValueAt(SelectedRow, 0));
        String batch = String.valueOf(teacherAssignmentTable.getValueAt(SelectedRow, 2));
        String stream = String.valueOf(teacherAssignmentTable.getValueAt(SelectedRow, 3));
        String subject = String.valueOf(teacherAssignmentTable.getValueAt(SelectedRow, 4));

        jLabel64.setText(tID);
        jComboBox6.setSelectedItem(batch);
        jComboBox7.setSelectedItem(stream);
        jComboBox8.setSelectedItem(subject);

    }

    private void loadUserProfile() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `users` WHERE `username`='" + userName + "'");

            if (resultSet.next()) {
                jTextField3.setText(resultSet.getString("first_name"));
                jTextField10.setText(resultSet.getString("last_name"));
                jPasswordField1.setText(resultSet.getString("password_hash"));
                jTextField17.setText(resultSet.getString("mobile"));
                jTextField15.setText(resultSet.getString("email"));
                jTextField18.setText(resultSet.getString("nic"));
                jTextField18.setEnabled(false);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String firstName = jTextField3.getText();
        String lastName = jTextField10.getText();
        String email = jTextField15.getText();
        String mobile = jTextField17.getText();

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
                AcademicUserSession.getInstance().setName(firstName + lastName);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

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
                        + "`users`.`user_type_id`=`usertypes`.`user_type_id` WHERE `username` = '" + AcademicUserSession.getInstance().getUsername() + "'");

                if (resultSet.next()) {
                    String description = "Academic Log Out";
                    String user = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                    String userType = resultSet.getString("usertypes.user_type_name");

                    MySQL.executeIUD("INSERT INTO `system_logs`(`timestamp`,`activity`,`user_name`,`user_type`)"
                            + "VALUES ('" + SystemDateTime + "','" + description + "','" + user + "','" + userType + "')");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Perform logout and navigation
            AcademicUserSession.getInstance().logout();
            this.dispose();
            userSelection us = new userSelection();
            us.setVisible(true);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        //        // academic profile image uploader code:
        //
        //        JFileChooser fileChooser = new JFileChooser();
        //        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //
        //        int returnValue = fileChooser.showOpenDialog(null);
        //
        //        if (returnValue == JFileChooser.APPROVE_OPTION) {
        //
        //            File selectedFile = fileChooser.getSelectedFile();
        //            ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());
        //
        //            Image image = imageIcon.getImage().getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_SMOOTH);
        //
        //            String path = selectedFile.getAbsolutePath();
        //            jLabel7.setIcon(new ImageIcon(image));
        //            imgPath = path;
        //        }

        // Academic profile image uploader code:
        JFileChooser fileChooser = new JFileChooser();

        // Set file filter to allow only PNG and JPG files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files (PNG, JPG)", "png", "jpg");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Validate the selected file extension
            String fileName = selectedFile.getName().toLowerCase();
            if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
                ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());
                Image image = imageIcon.getImage().getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_SMOOTH);

                String path = selectedFile.getAbsolutePath();
                jLabel7.setIcon(new ImageIcon(image));
                imgPath = path;
            } else {
                JOptionPane.showMessageDialog(this, "Invalid file type! Please select a PNG or JPG image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        jTextField19.grabFocus();
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        jDateChooser1.grabFocus();
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        jTextField6.grabFocus();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // student registration button:

        //variable declaration
        String firstName = jTextField12.getText();
        String lastName = jTextField13.getText();
        String nic = jTextField19.getText();
        String mobile = jTextField23.getText();
        Date dateOfBirth = jDateChooser1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String guardianNumber = jTextField1.getText();
        Date dateOfEnrollment = jDateChooser2.getDate();

        int genderId = 0;
        if (jRadioButton1.isSelected()) {
            genderId = 1;
        } else if (jRadioButton2.isSelected()) {
            genderId = 2;
        }

        String addressLine1 = jTextField5.getText();
        String addressLine2 = jTextField6.getText();
        String batch = String.valueOf(jComboBox1.getSelectedItem());
        String stream = String.valueOf(jComboBox2.getSelectedItem());

        //validation
        // Current Date for Comparison
        Date currentDate = new Date();

        // Validation Logic
        if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the First Name!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (lastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the Last Name!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the Mobile Number!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Mobile Number! It should start with 07 and have 10 digits.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the NIC!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (dateOfBirth == null) {
            JOptionPane.showMessageDialog(this, "Please select your Date of Birth!", "Date Selection Error", JOptionPane.WARNING_MESSAGE);
        } else if (dateOfBirth.after(currentDate)) {
            JOptionPane.showMessageDialog(this, "Date of Birth cannot be in the future!", "Date Selection Error", JOptionPane.WARNING_MESSAGE);
        } else if (dateOfEnrollment == null) {
            JOptionPane.showMessageDialog(this, "Please select the Enrollment Date!", "Date Selection Error", JOptionPane.WARNING_MESSAGE);
        } else if (genderId == 0) {
            JOptionPane.showMessageDialog(this, "Please select a Gender!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        } else if (guardianNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the Emergency Contact Number!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (!guardianNumber.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Emergency Contact Number! It should start with 07 and have 10 digits.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        } else if (addressLine1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Address Line 1!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (addressLine2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Address Line 2!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (batch.equals("Select Batch")) {
            JOptionPane.showMessageDialog(this, "Please select a Batch!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        } else if (stream.equals("Select Stream")) {
            JOptionPane.showMessageDialog(this, "Please select a Stream!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        } else if (imgPath == null || imgPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Image Selected to Save!", "Image Error", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet resultset = MySQL.executeSearch("SELECT*FROM `students` WHERE `nic` = '" + nic + "'");
                if (resultset.next()) {
                    JOptionPane.showMessageDialog(this, "Already registered student", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {

                    // Generate unique student ID for barcode
                    ResultSet result = MySQL.executeSearch("SELECT student_id FROM `students` ORDER BY student_id DESC LIMIT 1");
                    int newStudentId = result.next() ? result.getInt("student_id") + 1 : 1; // Generate next student ID
                    // Generate unique barcode data
                    String barcodeData = newStudentId + "_" + nic;

                    // Update the file path using the sanitized data
                    String barcodeFilePath = "src/barcode/barcode_" + barcodeData + ".png";

                    // Ensure the barcode directory exists
                    File barcodeDir = new File("src/barcode");
                    if (!barcodeDir.exists()) {
                        barcodeDir.mkdirs();  // Create the barcode directory if it doesn't exist
                    }

                    // Barcode Generation
                    try {
                        Map<EncodeHintType, Object> hints = new HashMap<>();
                        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

                        MultiFormatWriter writer = new MultiFormatWriter();
                        BitMatrix matrix = writer.encode(barcodeData, BarcodeFormat.CODE_128, 200, 100, hints);

                        BufferedImage barcodeImage = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
                        for (int x = 0; x < matrix.getWidth(); x++) {
                            for (int y = 0; y < matrix.getHeight(); y++) {
                                barcodeImage.setRGB(x, y, matrix.get(x, y) ? 0x000000 : 0xFFFFFF); // Black and white
                            }
                        }

                        File barcodeFile = new File(barcodeFilePath);
                        ImageIO.write(barcodeImage, "PNG", barcodeFile);

                        // Display barcode in JLabel
                        ImageIcon barcodeIcon = new ImageIcon(barcodeFilePath);
                        jLabel13.setIcon(barcodeIcon);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Error generating barcode: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String resourcesPath = "src/studentImg";
                    String newFileName = UUID.randomUUID().toString() + ".jpg"; // Unique file name for the image
                    File saveDir = new File(resourcesPath);

                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }

                    File fileToSave = new File(saveDir, newFileName);

                    try {
                        Path sourcePath = new File(imgPath).toPath();
                        Path destinationPath = fileToSave.toPath();

                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        String newFilePath = fileToSave.getAbsolutePath();

                        newFilePath = newFilePath.replace("\\", "/");

                        imgPath = newFilePath;
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(this, "Error saving image: " + ioException.getMessage());
                    }

                    //query
                    try {

                        //                    String newId;
                        ResultSet result1 = MySQL.executeSearch("SELECT student_id FROM students ORDER BY student_id DESC LIMIT 1");
                        if (result1.next()) {
                            //                        String lastId = result.getString("student_id");
                            //                        int idNum = Integer.parseInt(lastId.substring(2)) + 1;
                            //                        newId = String.format("EM%03d", idNum);
                            MySQL.executeIUD("INSERT INTO`students` "
                                    + "(`first_name`, `last_name`, `nic`, `mobile`, `dob`, `guardian_mobile`, `enrollment_date`, `gender_id`, `address_line_1`,`address_line_2`,`AL_batch_batch_id`,`stream_stream_id`, `barcode_id`,`img_path`) "
                                    + "VALUES ('" + firstName + "', '" + lastName + "', '" + nic + "', '" + mobile + "', '" + sdf.format(dateOfBirth) + "', '" + guardianNumber + "','" + sdf.format(dateOfEnrollment) + "','" + genderId + "','" + addressLine1 + "','" + addressLine2 + "','" + BatchMap.get(batch) + "','" + StreamMap.get(stream) + "', '" + barcodeData + "','" + imgPath + "')");
                            JOptionPane.showMessageDialog(this, "Successfully registered", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            loadStudents();
                            clearStudentReg();
                            overviewSTudent();
                            createPieChart();
                            createBarChart();
                            loadBarcodeTable();
                            loadStAttendanceTable();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //double check variable names and double run to insert
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // student registration clear button:
        clearStudentReg();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jButton24ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ComponentAdded

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        //        // student registration update button:
        //        int row = studentTable.getSelectedRow();
        //        if (row == -1) {
        //            JOptionPane.showMessageDialog(this, "Please select a student from the table!", "Warning", JOptionPane.ERROR_MESSAGE);
        //            return;
        //        }
        //
        //        // Variable Declaration
        //        String firstName = jTextField12.getText();
        //        String lastName = jTextField13.getText();
        //        String nic = jTextField19.getText();
        //        String mobile = jTextField23.getText();
        //        Date dateOfBirth = jDateChooser1.getDate();
        //        Date dateOfEnrollment = jDateChooser2.getDate();
        //        Date currentDate = new Date();  // Current date for validation
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //        String guardianNumber = jTextField1.getText();
        //
        //        int genderId = 0;
        //        if (jRadioButton1.isSelected()) {
        //            genderId = 1;
        //        } else if (jRadioButton2.isSelected()) {
        //            genderId = 2;
        //        }
        //
        //        String addressLine1 = jTextField5.getText();
        //        String addressLine2 = jTextField6.getText();
        //        String batch = String.valueOf(jComboBox1.getSelectedItem());
        //        String stream = String.valueOf(jComboBox2.getSelectedItem());
        //
        //        // === Validation Checks === //
        //        if (firstName.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please fill in the First Name!", "Input Error", JOptionPane.ERROR_MESSAGE);
        //            return;
        //        }
        //        if (lastName.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please fill in the Last Name!", "Input Error", JOptionPane.ERROR_MESSAGE);
        //            return;
        //        }
        //        if (mobile.isEmpty() || !mobile.matches("^07[125678]{1}[0-9]{7}$")) {
        //            JOptionPane.showMessageDialog(this, "Invalid Mobile Number! It should start with 07 and have 10 digits.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (nic.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please fill in the NIC!", "Input Error", JOptionPane.ERROR_MESSAGE);
        //            return;
        //        }
        //        if (dateOfBirth == null || dateOfBirth.after(currentDate)) {
        //            JOptionPane.showMessageDialog(this, "Date of Birth cannot be in the future!", "Date Selection Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (dateOfEnrollment == null) {
        //            JOptionPane.showMessageDialog(this, "Please enter Enrollment Date!", "Date Selection Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (guardianNumber.isEmpty() || !guardianNumber.matches("^07[125678]{1}[0-9]{7}$")) {
        //            JOptionPane.showMessageDialog(this, "Invalid Emergency Contact Number! It should start with 07 and have 10 digits.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (genderId == 0) {
        //            JOptionPane.showMessageDialog(this, "Please select a Gender!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (addressLine1.isEmpty() || addressLine2.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please fill in both Address Lines!", "Input Error", JOptionPane.ERROR_MESSAGE);
        //            return;
        //        }
        //        if (batch.equals("Select Batch")) {
        //            JOptionPane.showMessageDialog(this, "Please select a Batch!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (stream.equals("Select Stream")) {
        //            JOptionPane.showMessageDialog(this, "Please select a Stream!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //
        //        try {
        //            // Update Image If Changed
        //            String imagePathToSave = imgPath;
        //            if (imgPath != null && !imgPath.isEmpty()) {
        //                String resourcesPath = "src/studentImg";
        //                String newFileName = UUID.randomUUID().toString() + ".jpg";  // Unique file name for the image
        //                File saveDir = new File(resourcesPath);
        //
        //                if (!saveDir.exists()) {
        //                    saveDir.mkdirs();
        //                }
        //
        //                File fileToSave = new File(saveDir, newFileName);
        //                Path sourcePath = new File(imgPath).toPath();
        //                Path destinationPath = fileToSave.toPath();
        //
        //                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        //                imagePathToSave = fileToSave.getAbsolutePath().replace("\\", "/");
        //            }
        //
        //            // Update Query
        //            MySQL.executeIUD("UPDATE `students` SET "
        //                    + "`first_name` = '" + firstName + "', "
        //                    + "`last_name`= '" + lastName + "', "
        //                    + "`mobile` = '" + mobile + "', "
        //                    + "`dob` = '" + sdf.format(dateOfBirth) + "', "
        //                    + "`guardian_mobile`='" + guardianNumber + "', "
        //                    + "`enrollment_date` ='" + sdf.format(dateOfEnrollment) + "', "
        //                    + "`gender_id`='" + genderId + "', "
        //                    + "`address_line_1` ='" + addressLine1 + "', "
        //                    + "`address_line_2`='" + addressLine2 + "', "
        //                    + "`AL_batch_batch_id`='" + BatchMap.get(batch) + "', "
        //                    + "`stream_stream_id`='" + StreamMap.get(stream) + "', "
        //                    + "`img_path`='" + imagePathToSave + "' "
        //                    + "WHERE `nic` = '" + nic + "'");
        //
        //            JOptionPane.showMessageDialog(this, "Successfully updated!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        //            loadStudents();
        //            clearStudentReg();
        //
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }

        // Student registration update button
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table!", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Variable Declaration
        String firstName = jTextField12.getText();
        String lastName = jTextField13.getText();
        String nic = jTextField19.getText();
        String mobile = jTextField23.getText();
        Date dateOfBirth = jDateChooser1.getDate();
        Date dateOfEnrollment = jDateChooser2.getDate();
        Date currentDate = new Date();  // Current date for validation
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String guardianNumber = jTextField1.getText();

        int genderId = 0;
        if (jRadioButton1.isSelected()) {
            genderId = 1;
        } else if (jRadioButton2.isSelected()) {
            genderId = 2;
        }

        String addressLine1 = jTextField5.getText();
        String addressLine2 = jTextField6.getText();
        String batch = String.valueOf(jComboBox1.getSelectedItem());
        String stream = String.valueOf(jComboBox2.getSelectedItem());

        if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the First Name!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (lastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the Last Name!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (mobile.isEmpty() || !mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Mobile Number! It should start with 07 and have 10 digits.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the NIC!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (dateOfBirth == null || dateOfBirth.after(currentDate)) {
            JOptionPane.showMessageDialog(this, "Date of Birth cannot be in the future!", "Date Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (dateOfEnrollment == null) {
            JOptionPane.showMessageDialog(this, "Please enter Enrollment Date!", "Date Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (guardianNumber.isEmpty() || !guardianNumber.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Emergency Contact Number! It should start with 07 and have 10 digits.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (genderId == 0) {
            JOptionPane.showMessageDialog(this, "Please select a Gender!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (addressLine1.isEmpty() || addressLine2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both Address Lines!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (batch.equals("Select Batch")) {
            JOptionPane.showMessageDialog(this, "Please select a Batch!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (stream.equals("Select Stream")) {
            JOptionPane.showMessageDialog(this, "Please select a Stream!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Fetch Current Data for Validation
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `students` WHERE `nic` = '" + nic + "'");
            if (rs.next()) {
                String dbFirstName = rs.getString("first_name");
                String dbLastName = rs.getString("last_name");
                String dbMobile = rs.getString("mobile");
                String dbDob = rs.getString("dob");
                String dbGuardianMobile = rs.getString("guardian_mobile");
                String dbEnrollmentDate = rs.getString("enrollment_date");
                int dbGenderId = rs.getInt("gender_id");
                String dbAddressLine1 = rs.getString("address_line_1");
                String dbAddressLine2 = rs.getString("address_line_2");
                String dbBatchId = rs.getString("AL_batch_batch_id");
                String dbStreamId = rs.getString("stream_stream_id");
                String dbImgPath = rs.getString("img_path");

                // Check if any data has changed
                if (dbFirstName.equals(firstName)
                        && dbLastName.equals(lastName)
                        && dbMobile.equals(mobile)
                        && dbDob.equals(sdf.format(dateOfBirth))
                        && dbGuardianMobile.equals(guardianNumber)
                        && dbEnrollmentDate.equals(sdf.format(dateOfEnrollment))
                        && dbGenderId == genderId
                        && dbAddressLine1.equals(addressLine1)
                        && dbAddressLine2.equals(addressLine2)
                        && dbBatchId.equals(BatchMap.get(batch))
                        && dbStreamId.equals(StreamMap.get(stream))
                        && dbImgPath.equals(imgPath)) {
                    JOptionPane.showMessageDialog(this, "No changes detected. Update not required.", "No Update", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            // Proceed with Update
            String imagePathToSave = imgPath;
            if (imgPath != null && !imgPath.isEmpty()) {
                String resourcesPath = "src/studentImg";
                String newFileName = UUID.randomUUID().toString() + ".jpg";
                File saveDir = new File(resourcesPath);

                if (!saveDir.exists()) {
                    saveDir.mkdirs();
                }

                File fileToSave = new File(saveDir, newFileName);
                Path sourcePath = new File(imgPath).toPath();
                Path destinationPath = fileToSave.toPath();

                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                imagePathToSave = fileToSave.getAbsolutePath().replace("\\", "/");
            }

            MySQL.executeIUD("UPDATE `students` SET "
                    + "`first_name` = '" + firstName + "', "
                    + "`last_name`= '" + lastName + "', "
                    + "`mobile` = '" + mobile + "', "
                    + "`dob` = '" + sdf.format(dateOfBirth) + "', "
                    + "`guardian_mobile`='" + guardianNumber + "', "
                    + "`enrollment_date` ='" + sdf.format(dateOfEnrollment) + "', "
                    + "`gender_id`='" + genderId + "', "
                    + "`address_line_1` ='" + addressLine1 + "', "
                    + "`address_line_2`='" + addressLine2 + "', "
                    + "`AL_batch_batch_id`='" + BatchMap.get(batch) + "', "
                    + "`stream_stream_id`='" + StreamMap.get(stream) + "', "
                    + "`img_path`='" + imagePathToSave + "' "
                    + "WHERE `nic` = '" + nic + "'");

            JOptionPane.showMessageDialog(this, "Successfully updated!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            loadStudents();
            clearStudentReg();
            createPieChart();
            createBarChart();
            loadBarcodeTable();
            loadStAttendanceTable();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Delete student
        String nic = jTextField19.getText();
        String fname = jTextField12.getText();

        try {

            ResultSet rs = MySQL.executeSearch("SELECT student_id FROM students WHERE first_name = '" + fname + "' AND nic = '" + nic + "'");

            if (rs.next()) {
                String studentID = rs.getString("student_id");

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete the student?",
                        "Delete Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {

                    MySQL.executeIUD("DELETE FROM students_has_subjects WHERE students_student_id = '" + studentID + "'");
                    MySQL.executeIUD("DELETE FROM `student_attendance` WHERE  `students_student_id`= '" + studentID + "'");
                    MySQL.executeIUD("DELETE FROM students WHERE nic = '" + nic + "'");

                    JOptionPane.showMessageDialog(null, "Student deleted successfully!");
                    loadStudents();
                    clearStudentReg();
                    overviewSTudent();
                    createPieChart();
                    createBarChart();
                    loadBarcodeTable();
                    loadStAttendanceTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student not found! Please check the NIC and First Name.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting student: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jDateChooser2.grabFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        jComboBox1.grabFocus();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void studentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentTableMouseClicked
        // set student reg fields to update:
        int row = studentTable.getSelectedRow();
        //firstname , lastname,  nic , mobile
        jTextField12.setText(String.valueOf(studentTable.getValueAt(row, 1)));
        jTextField13.setText(String.valueOf(studentTable.getValueAt(row, 2)));
        jTextField19.setText(String.valueOf(studentTable.getValueAt(row, 3)));
        jTextField23.setText(String.valueOf(studentTable.getValueAt(row, 4)));

        //dob field
        String dob = (String.valueOf(studentTable.getValueAt(row, 5)));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (evt.getClickCount() == 1) {

            try {
                Date date = dateFormat.parse(dob);
                jDateChooser1.setDate(date);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "invalid date format: " + dob);
            }

            //guardian field
            jTextField1.setText(String.valueOf(studentTable.getValueAt(row, 6)));

            //enrollment date field
            String doe = (String.valueOf(studentTable.getValueAt(row, 7)));
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date date1 = dateFormat1.parse(doe);
                jDateChooser2.setDate(date1);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "invalid date format: " + doe);
            }

            //gender radio buttons
            String gender = (String.valueOf(studentTable.getValueAt(row, 8)));
            if (gender.equals("Male")) {
                jRadioButton1.setSelected(true);
            } else if (gender.equals("Female")) {
                jRadioButton2.setSelected(true);
            }

            //address line 1 and 2
            jTextField5.setText(String.valueOf(studentTable.getValueAt(row, 9)));
            jTextField6.setText(String.valueOf(studentTable.getValueAt(row, 10)));

            //batch and stream combobox
            jComboBox1.setSelectedItem(String.valueOf(studentTable.getValueAt(row, 11)));
            jComboBox2.setSelectedItem(String.valueOf(studentTable.getValueAt(row, 12)));

            jButton7.setEnabled(false);
            jTextField19.setEditable(false);
            jButton24.setEnabled(true);

            //image set
            try {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM students WHERE student_id = '" + String.valueOf(studentTable.getValueAt(row, 0)) + "'");
                if (rs.next()) {
                    imgPath = rs.getString("img_path");

                    if (imgPath != null) {
                        File imageFile = new File(imgPath);

                        if (imageFile.exists()) {
                            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());

                            Image image = imageIcon.getImage().getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_SMOOTH);
                            jLabel7.setIcon(new ImageIcon(image));

                        } else {
                            jLabel7.setIcon(null);
                        }
                    } else {
                        jLabel7.setIcon(null);
                    }

                    // Load barcode
                    String barcode = rs.getString("barcode_id");
                    if (barcode != null) {
                        String barcodePath = "src/barcode/barcode_" + barcode + ".png";
                        File barcodeFile = new File(barcodePath);
                        if (barcodeFile.exists()) {
                            ImageIcon barcodeIcon = new ImageIcon(barcodeFile.getAbsolutePath());
                            Image barcodeImage = barcodeIcon.getImage().getScaledInstance(jLabel13.getWidth(), jLabel13.getHeight(), Image.SCALE_SMOOTH);
                            jLabel13.setIcon(new ImageIcon(barcodeImage));
                        } else {
                            // Set a default or placeholder icon if barcode image is not found
                            FlatSVGIcon defaultIcon = new FlatSVGIcon("resources/barcode(1).svg", jLabel13.getWidth(), jLabel13.getHeight());
                            jLabel13.setIcon(defaultIcon);
                        }
                    } else {
                        // Clear or set placeholder if barcode is null
                        FlatSVGIcon defaultIcon = new FlatSVGIcon("resources/barcode(1).svg", jLabel13.getWidth(), jLabel13.getHeight());
                        jLabel13.setIcon(defaultIcon);

                    }

                    jTabbedPane1.setSelectedIndex(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //        //double click to delete student details
        //        try {
        //            if (evt.getClickCount() == 2) {
        //                int row1 = studentTable.getSelectedRow();
        //                String studentID = String.valueOf(studentTable.getValueAt(row1, 0));
        //
        //                int confirm = JOptionPane.showConfirmDialog(null,
        //                        "Are you sure you want to delete the student?",
        //                        "Delete Confirmation", JOptionPane.YES_NO_OPTION);
        //
        //                if (confirm == JOptionPane.YES_OPTION) {
        //                    // Delete from the linking table first
        //                    MySQL.executeIUD("DELETE FROM students_has_subjects WHERE students_student_id = '" + studentID + "'");
        //
        //                    // Delete from the main students table
        //                    MySQL.executeIUD("DELETE FROM students WHERE student_id = '" + studentID + "'");
        //
        //                    JOptionPane.showMessageDialog(null, "Student deleted successfully!");
        //                    loadStudents();
        //                    overviewSTudent();
        //                }
        //            }
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }//GEN-LAST:event_studentTableMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // student details table search bar here:
        String searchText = jTextField8.getText().trim();  // Get text from the search field and remove extra spaces

        // Construct the SQL query to search based on Name, NIC, Batch, or Stream
        String query = "SELECT * FROM `students` "
                + "INNER JOIN `gender` ON `students`.`gender_id` = `gender`.`id` "
                + "INNER JOIN `AL_batch` ON `students`.`AL_batch_batch_id` = `AL_batch`.`batch_id` "
                + "INNER JOIN `stream` ON `students`.`stream_stream_id` = `stream`.`stream_id` "
                + "WHERE `students`.`student_id` LIKE '%" + searchText + "%' "
                + "OR `students`.`first_name` LIKE '%" + searchText + "%' "
                + "OR `students`.`last_name` LIKE '%" + searchText + "%' "
                + "OR `students`.`nic` LIKE '%" + searchText + "%' "
                + "OR `AL_batch`.`batch_name` LIKE '%" + searchText + "%' " // Use AL_batch.batch_name correctly
                + "OR `stream`.`stream_name` LIKE '%" + searchText + "%'";  // Use stream.stream_name correctly

        try {
            ResultSet resultSet = MySQL.executeSearch(query);
            DefaultTableModel dtm = (DefaultTableModel) studentTable.getModel();
            dtm.setRowCount(0);  // Clear the table before adding new filtered results

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
                vector.add(resultSet.getString("AL_batch.batch_name"));  // Batch Name
                vector.add(resultSet.getString("stream.stream_name"));  // Stream Name
                vector.add(resultSet.getString("barcode_id"));

                dtm.addRow(vector);  // Add the row with data to the table
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while searching!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // search student name for subject registration in combobox:
        String searchText = jTextField2.getText().trim();  // Get text from the search field and remove extra spaces

        // Call the method to filter students in the combo box
        filterStudentsComboBox(searchText);

        // If the search field is empty, load all records in the table
        if (searchText.isEmpty()) {
            loadSelectedSubjects();  // Refresh table with all data
            return;
        }

        // Construct the SQL query to search based on Name, NIC, or Subject
        String query = "SELECT ss.id, s.first_name, s.last_name, s.nic, sub.subject_name "
                + "FROM `students_has_subjects` ss "
                + "INNER JOIN `students` s ON ss.students_student_id = s.student_id "
                + "INNER JOIN `subjects` sub ON ss.subjects_subject_id = sub.subject_id "
                + "WHERE s.first_name LIKE '%" + searchText + "%' "
                + "OR s.last_name LIKE '%" + searchText + "%' "
                + "OR s.nic LIKE '%" + searchText + "%' "
                + "OR sub.subject_name LIKE '%" + searchText + "%'";

        try {
            ResultSet resultSet = MySQL.executeSearch(query);
            DefaultTableModel dtm = (DefaultTableModel) selectedSubjectsTable.getModel();
            dtm.setRowCount(0);  // Clear the table before adding new filtered results

            while (resultSet.next()) {
                Vector vector = new Vector();
                vector.add(resultSet.getString("id"));  // Subject Allocation ID
                vector.add(resultSet.getString("first_name"));  // Student First Name
                vector.add(resultSet.getString("nic"));  // Student NIC (Fixed)
                vector.add(resultSet.getString("subject_name"));  // Subject Name
                dtm.addRow(vector);  // Add the row with data to the table
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while searching!", "Error", JOptionPane.ERROR_MESSAGE);
        }        // Call the filtering method
    }//GEN-LAST:event_jTextField2KeyReleased

    private void selectedSubjectsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectedSubjectsTableMouseClicked
        //select subject allocate row to update
        int row = selectedSubjectsTable.getSelectedRow();

        // Populate fields for updating
        jTextField2.setText(String.valueOf(selectedSubjectsTable.getValueAt(row, 1)));
        jComboBox18.setSelectedItem(String.valueOf(selectedSubjectsTable.getValueAt(row, 3)));

        jButton3.setEnabled(false);  // Disable Add button
        jTextField2.setEditable(false);  // Prevent name editing

        // Double-click to delete
        try {
            if (evt.getClickCount() == 2) {  // Check for double-click
                int row1 = selectedSubjectsTable.getSelectedRow();
                String selectedSubId = String.valueOf(selectedSubjectsTable.getValueAt(row1, 0));  // Correct table reference

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete the selected subject allocation?",
                        "Delete Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    MySQL.executeIUD("DELETE FROM `students_has_subjects` WHERE `id` = '" + selectedSubId + "'");

                    JOptionPane.showMessageDialog(null, "Allocated subject removed successfully!");
                    loadSelectedSubjects();  // Refresh table after deletion
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while deleting.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_selectedSubjectsTableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Add selected subjects button:
        // Insert into the separate selected_subjects table
        String selectedstudent = String.valueOf(jComboBox24.getSelectedItem());
        String subject = String.valueOf(jComboBox18.getSelectedItem());

        if (selectedstudent.equals("Select Student")) {
            JOptionPane.showMessageDialog(this, "Please select a student", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subject.equals("Select Subject")) {
            JOptionPane.showMessageDialog(this, "Please select a subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                // Check if the combination of student and subject already exists
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `students_has_subjects` WHERE `students_student_id` = '"
                        + StudentsMap.get(selectedstudent) + "' AND `subjects_subject_id` = '" + Subject1Map.get(subject) + "'");

                if (resultSet.next()) {
                    // If the combination already exists, show a warning
                    JOptionPane.showMessageDialog(this, "This subject is already added for the selected student", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    // If not, insert the new combination
                    MySQL.executeIUD("INSERT INTO `students_has_subjects`(`students_student_id`, `subjects_subject_id`) "
                            + "VALUES('" + StudentsMap.get(selectedstudent) + "', '" + Subject1Map.get(subject) + "')");

                    // Success message
                    JOptionPane.showMessageDialog(this, "Successfully registered subject", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    loadSelectedSubjects();  // Refresh the table
                    clearSelectedSub();      // Reset fields
                    createPieChart();
                    loadStAttendanceTable();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // student selected subjects update button:
        int row = selectedSubjectsTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a subject allocation from the table!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve form values
        String selectedSubId = String.valueOf(selectedSubjectsTable.getValueAt(row, 0));  // Primary Key
        String studentName = jTextField2.getText();
        String selectedSubject = String.valueOf(jComboBox18.getSelectedItem());

        // === Validation Checks ===
        if (studentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a student!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (selectedSubject.equals("Select Subject")) {
            JOptionPane.showMessageDialog(this, "Please select a subject!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Get Subject ID from Database
            ResultSet rs = MySQL.executeSearch("SELECT `subject_id` FROM `subjects` WHERE `subject_name` = '" + selectedSubject + "'");

            if (rs.next()) {
                String subjectId = rs.getString("subject_id");

                // Check if the subject is already allocated
                ResultSet checkDuplicate = MySQL.executeSearch(
                        "SELECT * FROM `students_has_subjects` WHERE `students_student_id` = (SELECT `student_id` FROM `students` WHERE `first_name` = '" + studentName + "') "
                        + "AND `subjects_subject_id` = '" + subjectId + "'"
                );

                if (checkDuplicate.next()) {
                    JOptionPane.showMessageDialog(this, "Subject already allocated to this student!", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Execute Update Query
                int rowsAffected = MySQL.executeIUD(
                        "UPDATE `students_has_subjects` SET `subjects_subject_id` = '" + subjectId + "' WHERE `id` = '" + selectedSubId + "'");

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Subject allocation updated successfully!");
                    loadSelectedSubjects();  // Refresh Table
                    clearSelectedSub();  // Reset Fields
                    createPieChart();
                    loadStAttendanceTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Subject not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while updating!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // selected subject delete button:
        int row = selectedSubjectsTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a subject allocation to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the subject allocation ID
        String selectedSubId = String.valueOf(selectedSubjectsTable.getValueAt(row, 0));  // Primary Key (ID)

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this subject allocation?",
                "Delete Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Execute DELETE Query to remove the allocation
                int rowsAffected = MySQL.executeIUD(
                        "DELETE FROM `students_has_subjects` WHERE `id` = '" + selectedSubId + "'");

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Subject allocation deleted successfully!");
                    loadSelectedSubjects();  // Refresh table after deletion
                    clearSelectedSub();  // Reset the form fields
                    createPieChart();
                    loadStAttendanceTable();

                } else {
                    JOptionPane.showMessageDialog(this, "Deletion failed! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while deleting!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // clear selected subjects fields button:
        clearSelectedSub();
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jAllClassDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAllClassDetailsTableMouseClicked

    }//GEN-LAST:event_jAllClassDetailsTableMouseClicked

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        String searchText = jTextField7.getText().replace("'", "''"); // Escape single quotes to avoid SQL injection
        try {
            DefaultTableModel dtm = (DefaultTableModel) jAllClassDetailsTable.getModel();
            dtm.setRowCount(0);

            String query = "SELECT * FROM schedule INNER "
                    + "JOIN teachers ON teachers.teacher_id = `schedule`.teacher_id INNER JOIN "
                    + "stream_subject ON stream_subject.id = `schedule`.stream_subject_id "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = stream_subject.AL_batch_batch_id "
                    + "INNER JOIN subjects ON subjects.subject_id = stream_subject.subjects_subject_id "
                    + "WHERE schedule_id LIKE '%" + searchText + "%' "
                    + "OR teachers.first_name LIKE '%" + searchText + "%' "
                    + "OR teachers.last_name LIKE '%" + searchText + "%' "
                    + "OR batch_name LIKE '%" + searchText + "%' "
                    + "OR subject_name LIKE '%" + searchText + "%' "
                    + "OR sheduled_date LIKE '%" + searchText + "%'";

            ResultSet rs = model.MySQL.executeSearch(query);

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("schedule_id"));
                v.add(rs.getString("teachers.first_name") + " " + rs.getString("teachers.last_name"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("subject_name"));
                v.add(rs.getString("sheduled_date"));
                v.add(rs.getString("start_time") + " - " + rs.getString("end_time"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while searching: " + e.getMessage());
        }
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jStAttendanceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStAttendanceTableMouseClicked

        int row = jStAttendanceTable.getSelectedRow();
        if (evt.getClickCount() == 1) {

            String stId = String.valueOf(jStAttendanceTable.getValueAt(row, 0));
            String stName = String.valueOf(jStAttendanceTable.getValueAt(row, 1));
            String status = String.valueOf(jStAttendanceTable.getValueAt(row, 2));

            jstID.setText(stId);
            jstName.setText(stName);

            if (status.equals("Present")) {
                jAttendanceMarkButton.setEnabled(false);
                jAttendanceUpdateButton.setEnabled(true);

            } else if (status.equals("Absent")) {
                jAttendanceUpdateButton.setEnabled(false);
                jAttendanceMarkButton.setEnabled(true);
            }

            try {
                ResultSet rs = MySQL.executeSearch("SELECT img_path FROM `students` WHERE `student_id`='" + stId + "' ");
                if (rs.next()) {
                    imgPath = rs.getString("img_path");

                    if (imgPath != null && !imgPath.isEmpty()) {
                        File imageFile = new File(imgPath);

                        if (imageFile.exists()) {
                            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                            Image image = imageIcon.getImage().getScaledInstance(jLabel124.getWidth(), jLabel124.getHeight(), Image.SCALE_SMOOTH);
                            jLabel124.setIcon(new ImageIcon(image));
                        } else {
                            // Set default image if file does not exist
                            FlatSVGIcon icon14 = new FlatSVGIcon("resources//profileImage.svg", jLabel124.getWidth(), jLabel124.getHeight());
                            jLabel124.setIcon(icon14);
                        }
                    } else {
                        // Set default image if imgPath is null or empty
                        FlatSVGIcon icon14 = new FlatSVGIcon("resources//profileImage.svg", jLabel124.getWidth(), jLabel124.getHeight());
                        jLabel124.setIcon(icon14);
                    }
                } else {
                    // Set default image if no result is found in the database
                    FlatSVGIcon icon14 = new FlatSVGIcon("resources//profileImage.svg", jLabel124.getWidth(), jLabel124.getHeight());
                    jLabel124.setIcon(icon14);
                }
            } catch (Exception e) {
                e.printStackTrace();
                FlatSVGIcon icon14 = new FlatSVGIcon("resources//profileImage.svg", jLabel124.getWidth(), jLabel124.getHeight());
                jLabel124.setIcon(icon14); // Fallback to default image in case of error
            }
        }
    }//GEN-LAST:event_jStAttendanceTableMouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        jTabbedPane2.setSelectedIndex(1);

        // Listen for table row selection
        jAllClassDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = jAllClassDetailsTable.getSelectedRow();

                if (selectedRow != -1) {
                    // Get teacher ID from the table (assuming it's in column 0)

                    String sid = String.valueOf(jAllClassDetailsTable.getValueAt(selectedRow, 0));
                    String TName = String.valueOf(jAllClassDetailsTable.getValueAt(selectedRow, 1));
                    String batch = String.valueOf(jAllClassDetailsTable.getValueAt(selectedRow, 2));
                    String subject = String.valueOf(jAllClassDetailsTable.getValueAt(selectedRow, 3));
                    String date = String.valueOf(jAllClassDetailsTable.getValueAt(selectedRow, 4));

                    jSID.setText(sid);
                    jTName.setText(TName);
                    jalbatch.setText(batch);
                    jSubject.setText(subject);
                    jSAtDate.setText(date);

                    loadStAttendanceTable();

                    //return
                    jTabbedPane2.setSelectedIndex(2);

                    // Adjust based on your tab structure
                    jAllClassDetailsTable.removeMouseListener(this);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a student from the table!", "Selection Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jAttendanceMarkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAttendanceMarkButtonActionPerformed
        //st attendance mark button

        String stID = jstID.getText();
        String stName = jstName.getText();
        String scheduleID = jSID.getText();
        String attendanceDate = jSAtDate.getText();

        String studentAttendance = "Present";

        if (scheduleID.equals("Schedule ID")) {
            JOptionPane.showMessageDialog(this, "Please Select a class!", "warning", JOptionPane.WARNING_MESSAGE);

        } else if (stID.equals("Student ID") || stName.equals("Student Name")) {
            JOptionPane.showMessageDialog(this, "Please Select a student to mark attendance!", "warning", JOptionPane.WARNING_MESSAGE);

        } else {
            try {
                ResultSet rs = MySQL.executeSearch(" SELECT * FROM `student_attendance` WHERE "
                        + "`attendance_date`='" + attendanceDate + "' AND"
                        + " `schedule_id`='" + scheduleID + "'"
                        + " AND `students_student_id`='" + stID + "' AND `status`='" + studentAttendance + "' ");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Attendance Allready marked!", "error", JOptionPane.ERROR_MESSAGE);
                    clearSTattendance();
                } else {

                    MySQL.executeIUD("UPDATE `student_attendance` SET `status`='" + studentAttendance + "' WHERE `schedule_id`='" + scheduleID + "' AND `students_student_id`='" + stID + "' ");

                    loadStAttendanceTable();

                    JOptionPane.showMessageDialog(this, "Attendance marked successfully!", "success", JOptionPane.INFORMATION_MESSAGE);

                    jstName.setText("Student Name");
                    jstID.setText("Sudent ID");

                }

            } catch (Exception e) {

                e.printStackTrace();

            }
        }
    }//GEN-LAST:event_jAttendanceMarkButtonActionPerformed

    private void jAttendanceUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAttendanceUpdateButtonActionPerformed
        // Student Attendance Update Button

        int row = jStAttendanceTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the attendance table!", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String stID = jstID.getText();
        String stName = jstName.getText();
        String scheduleID = jSID.getText();
        String attendanceDate = jSAtDate.getText();
        String studentAttendance = String.valueOf(jStAttendanceTable.getValueAt(row, 2));

        if (scheduleID.equals("Schedule ID")) {
            JOptionPane.showMessageDialog(this, "Please select a class!", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (stID.equals("Student ID") || stName.equals("Student Name")) {
            JOptionPane.showMessageDialog(this, "Please select a student to update attendance!", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String newStatus = studentAttendance.equals("Present") ? "Absent" : "Present";
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to update the attendance status to '" + newStatus + "'?",
                    "Confirm Update",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                MySQL.executeIUD("UPDATE `student_attendance` SET `status`='" + newStatus + "' WHERE `schedule_id`='" + scheduleID + "' AND `students_student_id`='" + stID + "' ");
                JOptionPane.showMessageDialog(this, "Attendance updated to '" + newStatus + "' successfully!", "success", JOptionPane.INFORMATION_MESSAGE);

                // Refresh the table and reset fields
                loadStAttendanceTable();
                jstName.setText("Student Name");
                jstID.setText("Student ID");
                clearSTattendance();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jAttendanceUpdateButtonActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // st attendance rest

        clearSTattendance();
    }//GEN-LAST:event_jButton18ActionPerformed


    private void jBarcodeScanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBarcodeScanKeyTyped

        // Scan barcode and mark attendance
        String barcodeID = jBarcodeScan.getText().trim();
        String attendanceDate = jSAtDate.getText().trim();
        String scheduleID = jSID.getText().trim();
        String attendanceStatus = "Present"; // Default status

        // Validate inputs
        if (scheduleID.equals("Schedule ID")) {
            JOptionPane.showMessageDialog(this, "Please select a class!", "Warning", JOptionPane.WARNING_MESSAGE);
            jBarcodeScan.setText(" ");
            return;
        }

        if (barcodeID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please scan a valid barcode!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Fetch student details using the scanned barcode
            ResultSet studentResult = MySQL.executeSearch(
                    "SELECT student_id, first_name, last_name FROM students WHERE barcode_id = '" + barcodeID + "'");

            if (studentResult.next()) {
                String studentID = studentResult.getString("student_id");
                String studentName = studentResult.getString("first_name") + " " + studentResult.getString("last_name");

                // Check if the student is assigned to the selected schedule
                ResultSet classAssignmentResult = MySQL.executeSearch(
                        "SELECT * FROM student_attendance "
                        + "WHERE schedule_id = '" + scheduleID + "' AND "
                        + "attendance_date = '" + attendanceDate + "' AND "
                        + "students_student_id = '" + studentID + "'");

                if (classAssignmentResult.next()) {
                    // Check if attendance is already marked as "Present"
                    ResultSet attendanceCheckResult = MySQL.executeSearch(
                            "SELECT * FROM student_attendance WHERE "
                            + "attendance_date = '" + attendanceDate + "' AND "
                            + "schedule_id = '" + scheduleID + "' AND "
                            + "students_student_id = '" + studentID + "' AND "
                            + "status = '" + attendanceStatus + "'");

                    if (attendanceCheckResult.next()) {
                        // Attendance already marked
                        JOptionPane.showMessageDialog(this,
                                "Attendance already marked for " + studentName,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        jBarcodeScan.setText("Scan Barcode");

                    } else {
                        // Update attendance status to "Present"
                        MySQL.executeIUD("UPDATE student_attendance SET status = '" + attendanceStatus + "' "
                                + "WHERE schedule_id = '" + scheduleID + "' AND "
                                + "students_student_id = '" + studentID + "'");

                        // Refresh attendance table and reset fields
                        loadStAttendanceTable();
                        jBarcodeScan.setText(" ");
                        JOptionPane.showMessageDialog(this,
                                "Attendance marked successfully for " + studentName,
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    // Student is not assigned to this schedule
                    JOptionPane.showMessageDialog(this,
                            "This student is not assigned to this class: " + studentName,
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    jBarcodeScan.setText("Scan Barcode");
                }
            } else {
                // No student found for the given barcode
                JOptionPane.showMessageDialog(this,
                        "No student found for the scanned barcode!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred while marking attendance. " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBarcodeScanKeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // print st barcode

        try {

            String name = jLabel106.getText();
            String nic = jLabel94.getText();
            Icon barcodeIcon = jLabel29.getIcon();

            if (name.isEmpty() || nic.isEmpty() || barcodeIcon == null) {
                JOptionPane.showMessageDialog(this, "Incomplete details or missing barcode.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintable(new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return NO_SUCH_PAGE;
                    }

                    Graphics2D g2 = (Graphics2D) graphics;
                    g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                    int boxWidth = 250;
                    int boxHeight = 150;
                    int x = 50;
                    int y = 50;

                    // Draw a border for the box
                    g2.drawRect(x, y, boxWidth, boxHeight);

                    // Add the name
                    g2.setFont(new Font("Arial", Font.PLAIN, 12));
                    g2.drawString("Name: " + name, x + 10, y + 20);

                    // Add the NIC
                    g2.drawString("NIC: " + nic, x + 10, y + 40);

                    // Add the barcode image
                    if (barcodeIcon instanceof ImageIcon) {
                        ImageIcon imageIcon = (ImageIcon) barcodeIcon;
                        Image barcodeImage = imageIcon.getImage();
                        g2.drawImage(barcodeImage, x + 10, y + 50, 200, 60, null);
                    }

                    return PAGE_EXISTS;
                }
            });

            // Display print dialog and print if confirmed
            boolean doPrint = printerJob.printDialog();
            if (doPrint) {
                printerJob.print();

                jLabel106.setText("Student Name");
                jLabel94.setText("NIC");

                FlatSVGIcon icon17 = new FlatSVGIcon("resources//profileImage.svg", jLabel103.getWidth(), jLabel103.getHeight());
                jLabel103.setIcon(icon17);

                FlatSVGIcon icon18 = new FlatSVGIcon("resources//barcode(1).svg", jLabel29.getWidth(), jLabel29.getHeight());
                jLabel29.setIcon(icon18);

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while printing.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(4);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jTextField29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField29ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        //teacher registration add button
        String fname = jTextField26.getText().trim();
        String lname = jTextField27.getText().trim();
        String nic = jTextField28.getText().trim();
        Date dob = jDateChooser6.getDate();
        Date currentDate = new Date();
        Date enrollmentDate = jDateChooser4.getDate();
        String line1 = jTextField11.getText().trim();
        String line2 = jTextField30.getText().trim();
        String mobile = jTextField29.getText().trim();
        String email = jTextField21.getText().trim();

        int teacherGender = 0;
        if (jRadioButton3.isSelected()) {
            teacherGender = 1;  // Male
        } else if (jRadioButton4.isSelected()) {
            teacherGender = 2;  // Female
        }

        // Validation checks
        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter first name!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter last name!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter NIC!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (dob == null) {
            JOptionPane.showMessageDialog(this, "Please select the date of birth!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (dob.after(currentDate)) {
            JOptionPane.showMessageDialog(this, "Date of Birth cannot be in the future!", "Date Selection Error", JOptionPane.WARNING_MESSAGE);
        } else if (enrollmentDate == null) {
            JOptionPane.showMessageDialog(this, "Please select the date of enrollment date!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (teacherGender == 0) {
            JOptionPane.showMessageDialog(this, "Please select the gender!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (line1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Address Line 1!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (line2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Address Line 2!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid mobile Number! It should start with 07 and have 10 digits.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email address!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (TimgPath == null || TimgPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Image Selected to Save!", "Image Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String formattedDob = new SimpleDateFormat("yyyy-MM-dd").format(dob);
            String formattedEnrollmentDate = new SimpleDateFormat("yyyy-MM-dd").format(enrollmentDate);

            try {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `teachers` WHERE `nic`='" + nic + "' OR `mobile`='" + mobile + "' OR `email`='" + email + "'");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Teacher already exists!", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {
                    // Save image with teacher's name
                    String resourcesPath = "src/teacherImg";
                    String cleanName = (fname + "_" + lname).replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9_]", "");
                    String newFileName = cleanName + "_" + System.currentTimeMillis() + ".jpg"; // Unique with timestamp

                    File saveDir = new File(resourcesPath);
                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }

                    File fileToSave = new File(saveDir, newFileName);

                    try {
                        Path sourcePath = new File(TimgPath).toPath();
                        Path destinationPath = fileToSave.toPath();

                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        String newFilePath = fileToSave.getAbsolutePath().replace("\\", "/");
                        TimgPath = newFilePath;
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(this, "Error saving image: " + ioException.getMessage());
                    }

                    MySQL.executeIUD("INSERT INTO `teachers` (`first_name`, `last_name`, `nic`, `mobile`, `email`, `dob`, `enrollment_date`, `gender_id`, `address_line_1`, `address_line_2`,`barcode_id`,`img_path`) "
                            + "VALUES ('" + fname + "', '" + lname + "', '" + nic + "', '" + mobile + "', '" + email + "', '" + formattedDob + "', '" + formattedEnrollmentDate + "', " + teacherGender + ", '" + line1 + "', '" + line2 + "' ,'1', '" + TimgPath + "')");

                    loadTeacherViewTable();
                    teacherDetailsReset();
                    overviewTeacher();
                    loadTBarcodeTable();
                    JOptionPane.showMessageDialog(this, "Teacher successfully registered!", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while registering teacher: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed

        teacherDetailsReset();
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        //update teacher reg button
        int row = teacherViewTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a teacher from the table!", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Fetch Data
        String fname = jTextField26.getText().trim();
        String lname = jTextField27.getText().trim();
        String nic = jTextField28.getText().trim();
        Date dob = jDateChooser6.getDate();
        Date enrollmentDate = jDateChooser4.getDate();
        String line1 = jTextField11.getText().trim();
        String line2 = jTextField30.getText().trim();
        String mobile = jTextField29.getText().trim();
        String email = jTextField21.getText().trim();

        int teacherGender = 0;
        if (jRadioButton3.isSelected()) {
            teacherGender = 1;  // Male
        } else if (jRadioButton4.isSelected()) {
            teacherGender = 2;  // Female
        }

        // Validation
        if (fname.isEmpty() || lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "First and Last Name are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "please enter NIC !", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (dob == null || dob.after(new Date())) {
            JOptionPane.showMessageDialog(this, "Date of Birth cannot be in the future!", "Date Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (enrollmentDate == null || enrollmentDate.before(dob)) {
            JOptionPane.showMessageDialog(this, "Enrollment Date must be after Date of Birth!", "Date Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (teacherGender == 0) {
            JOptionPane.showMessageDialog(this, "Please select a Gender!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (line1.isEmpty() || line2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address fields cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (mobile.isEmpty() || !mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Mobile Number! It should start with 07 and have 10 digits.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Enter a valid Email!", "Email Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Update Image If Changed
            String imagePathToSave = TimgPath;
            if (TimgPath != null && !TimgPath.isEmpty()) {
                String resourcesPath = "src/teacherImg";
                String cleanName = (fname + "_" + lname).replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9_]", "");
                String newFileName = cleanName + "_" + System.currentTimeMillis() + ".jpg"; // Unique with timestamp

                File saveDir = new File(resourcesPath);
                if (!saveDir.exists()) {
                    saveDir.mkdirs();
                }

                File fileToSave = new File(saveDir, newFileName);
                Path sourcePath = new File(TimgPath).toPath();
                Path destinationPath = fileToSave.toPath();

                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                imagePathToSave = fileToSave.getAbsolutePath().replace("\\", "/");
            }

            // SQL Update Query
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String query = "UPDATE `teachers` SET "
                    + "`first_name` = '" + fname + "', "
                    + "`last_name`= '" + lname + "', "
                    + "`mobile` = '" + mobile + "', "
                    + "`dob` = '" + sdf.format(dob) + "', "
                    + "`enrollment_date` ='" + sdf.format(enrollmentDate) + "', "
                    + "`gender_id`='" + teacherGender + "', "
                    + "`address_line_1` ='" + line1 + "', "
                    + "`address_line_2`='" + line2 + "', "
                    + "`email`='" + email + "', "
                    + "`img_path`='" + imagePathToSave + "' "
                    + "WHERE `nic` = '" + nic + "'";

            int rowsAffected = MySQL.executeIUD(query);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Teacher successfully updated!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                // Optionally Reload Data or Clear Form
                loadTeacherViewTable();
                teacherDetailsReset();
                loadTBarcodeTable();
            } else {
                JOptionPane.showMessageDialog(this, "Teacher not found or update failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while updating teacher: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // Teacher delete
        int row1 = teacherViewTable.getSelectedRow();
        String teacherID = String.valueOf(teacherViewTable.getValueAt(row1, 0));

        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete the teacher?",
                "Delete Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Delete from the linking table first
                MySQL.executeIUD("DELETE FROM teachers_has_stream_subject WHERE teachers_teacher_id = '" + teacherID + "'");
            } catch (Exception ex) {
                Logger.getLogger(AcademicDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                // Delete from the main teacher table
                MySQL.executeIUD("DELETE FROM teachers WHERE teacher_id = '" + teacherID + "'");
            } catch (Exception ex) {
                Logger.getLogger(AcademicDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "teacher deleted successfully!");
            loadTeacherViewTable();
            teacherDetailsReset();
            loadTBarcodeTable();
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void teacherViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherViewTableMouseClicked

        //        if (evt.getClickCount() == 2) {
        //            int row1 = teacherViewTable.getSelectedRow();
        //            String teacherID = String.valueOf(teacherViewTable.getValueAt(row1, 0));
        //
        //            int confirm = JOptionPane.showConfirmDialog(null,
        //                    "Are you sure you want to delete the teacher?",
        //                    "Delete Confirmation", JOptionPane.YES_NO_OPTION);
        //
        //            if (confirm == JOptionPane.YES_OPTION) {
        //                try {
        //                    // Delete from the linking table first
        //                    MySQL.executeIUD("DELETE FROM teachers_has_stream_subject WHERE teachers_teacher_id = '" + teacherID + "'");
        //                } catch (Exception ex) {
        //                    Logger.getLogger(AcademicDashboard.class.getName()).log(Level.SEVERE, null, ex);
        //                }
        //
        //                try {
        //                    // Delete from the main teacher table
        //                    MySQL.executeIUD("DELETE FROM teachers WHERE teacher_id = '" + teacherID + "'");
        //                } catch (Exception ex) {
        //                    Logger.getLogger(AcademicDashboard.class.getName()).log(Level.SEVERE, null, ex);
        //                }
        //
        //                JOptionPane.showMessageDialog(null, "teacher deleted successfully!");
        //                loadTeacherViewTable();
        //            }
        //        }
        if (evt.getClickCount() == 1) {
            loadTeacherViewRowData();
            jButton17.setEnabled(false);
            jTextField28.setEditable(false);

            // Redirect to the specific tab containing the text fields
            jTabbedPane8.setSelectedIndex(0); // Assuming the tab index for the target tab is 1
            overviewTeacher();

        }
    }//GEN-LAST:event_teacherViewTableMouseClicked

    private void jTextField31KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField31KeyReleased

        String searchText = jTextField31.getText();

        try {

            ResultSet rs = MySQL.executeSearch("SELECT *From `teachers` INNER JOIN `gender` ON teachers.gender_id=gender.id "
                    + " WHERE teacher_id LIKE '%" + searchText + "%' OR "
                    + "first_name LIKE '%" + searchText + "%' OR "
                    + "last_name LIKE '%" + searchText + "%' OR "
                    + "nic LIKE '%" + searchText + "%' OR "
                    + "mobile LIKE '%" + searchText + "%' OR "
                    + "email LIKE '%" + searchText + "%' OR "
                    + "dob LIKE '%" + searchText + "%' OR "
                    + "`gender`.`type` LIKE '%" + searchText + "%' OR "
                    + "enrollment_date LIKE '%" + searchText + "%' OR "
                    + "address_line_1 LIKE '%" + searchText + "%' OR "
                    + "address_line_2 LIKE '%" + searchText + "%' ");

            DefaultTableModel model = (DefaultTableModel) teacherViewTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("teacher_id"));
                v.add(rs.getString("first_name"));
                v.add(rs.getString("last_name"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("type"));
                v.add(rs.getString("mobile"));
                v.add(rs.getString("email"));
                v.add(rs.getString("address_line_1"));
                v.add(rs.getString("address_line_2"));
                v.add(rs.getString("barcode_id"));
                v.add(rs.getString("enrollment_date"));

                model.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField31KeyReleased

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //add subject for teacher
        String tID = jLabel64.getText();
        String batch = String.valueOf(jComboBox6.getSelectedItem());
        String stream = String.valueOf(jComboBox7.getSelectedItem());
        String subject = String.valueOf(jComboBox8.getSelectedItem());

        if (tID.equals("Teacher ID")) {
            JOptionPane.showMessageDialog(null, "Please select a teacher !", "Selection Error", JOptionPane.WARNING_MESSAGE);
        } else if (batch.equals("Select Batch")) {
            JOptionPane.showMessageDialog(null, "Please select a A/L Batch !", "Selection Error", JOptionPane.WARNING_MESSAGE);

        } else if (stream.equals("Select Stream")) {
            JOptionPane.showMessageDialog(null, "Please select a Stream!", "Selection Error", JOptionPane.WARNING_MESSAGE);

        } else if (subject.equals("Select Subject")) {
            JOptionPane.showMessageDialog(null, "Please select a Subject !", "Selection Error", JOptionPane.WARNING_MESSAGE);

        } else {

            try {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM teachers_has_stream_subject INNER JOIN  stream_subject ON teachers_has_stream_subject.stream_subject_id=stream_subject.id  INNER JOIN stream ON stream.stream_id=stream_subject.stream_stream_id  INNER JOIN subjects ON subjects.subject_id=stream_subject.subjects_subject_id"
                        + " INNER JOIN AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id WHERE `teachers_teacher_id`='" + tID + "' AND `stream_name`='" + stream + "' AND `batch_name`='" + batch + "' AND `subject_name`='" + subject + "' ");
                if (rs.next()) {

                    JOptionPane.showMessageDialog(null, "This Class already exist!", "Error", JOptionPane.WARNING_MESSAGE);

                } else {
                    try {
                        ResultSet rs1 = MySQL.executeSearch("SELECT `id` FROM stream_subject INNER JOIN"
                                + " subjects ON subjects.subject_id=stream_subject.subjects_subject_id "
                                + "INNER JOIN AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id "
                                + "INNER JOIN stream ON stream.stream_id = stream_subject.stream_stream_id "
                                + "WHERE `stream_name`='" + stream + "' AND `batch_name`='" + batch + "' AND"
                                + " `subject_name`='" + subject + "' ");

                        if (rs1.next()) {
                            String ssid = rs1.getString("id");

                            MySQL.executeIUD("INSERT INTO `teachers_has_stream_subject` (`teachers_teacher_id`,`stream_subject_id`) VALUES ('" + tID + "' , '" + ssid + "')");
                            JOptionPane.showMessageDialog(this, "Class successfully registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            loadTeacherAssignmentTable();
                            teacherAssignmentReset();
                        } else {
                            JOptionPane.showMessageDialog(this, "select a registered subject within institute under correct stream!", "error", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //        //teachers subject allocation update button
        //        String tID = jLabel64.getText();
        //        String batch = String.valueOf(jComboBox6.getSelectedItem());
        //        String stream = String.valueOf(jComboBox7.getSelectedItem());
        //        String subject = String.valueOf(jComboBox8.getSelectedItem());
        //
        //        if (tID.equals("Teacher ID")) {
        //            JOptionPane.showMessageDialog(null, "Please select a teacher !", "Selection Error", JOptionPane.WARNING_MESSAGE);
        //        } else if (batch.equals("Select Batch")) {
        //            JOptionPane.showMessageDialog(null, "Please select a A/L Batch !", "Selection Error", JOptionPane.WARNING_MESSAGE);
        //
        //        } else if (stream.equals("Select Stream")) {
        //            JOptionPane.showMessageDialog(null, "Please select a Stream!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        //
        //        } else if (subject.equals("Select Subject")) {
        //            JOptionPane.showMessageDialog(null, "Please select a Subject !", "Selection Error", JOptionPane.WARNING_MESSAGE);
        //
        //        } else {
        //
        //            try {
        //                ResultSet rs = MySQL.executeSearch("SELECT * FROM teachers_has_stream_subject INNER JOIN  stream_subject ON teachers_has_stream_subject.stream_subject_id=stream_subject.id  INNER JOIN stream ON stream.stream_id=stream_subject.stream_stream_id  INNER JOIN subjects ON subjects.subject_id=stream_subject.subjects_subject_id"
        //                        + " INNER JOIN AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id WHERE `teachers_teacher_id`='" + tID + "' AND `stream_name`='" + stream + "' AND `batch_name`='" + batch + "' AND `subject_name`='" + subject + "' ");
        //                if (rs.next()) {
        //
        //                    JOptionPane.showMessageDialog(null, "This Class already exist!", "Error", JOptionPane.WARNING_MESSAGE);
        //
        //                } else {
        //                    try {
        //                        ResultSet rs1 = MySQL.executeSearch("SELECT `id` FROM stream_subject INNER JOIN"
        //                                + " subjects ON subjects.subject_id=stream_subject.subjects_subject_id "
        //                                + "INNER JOIN AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id "
        //                                + "INNER JOIN stream ON stream.stream_id = stream_subject.stream_stream_id "
        //                                + "WHERE `stream_name`='" + stream + "' AND `batch_name`='" + batch + "' AND"
        //                                + " `subject_name`='" + subject + "' ");
        //
        //                        if (rs1.next()) {
        //                            String ssid = rs1.getString("id");
        //
        //                            //                            MySQL.executeIUD("INSERT INTO `teachers_has_stream_subject` (`teachers_teacher_id`,`stream_subject_id`) VALUES ('" + tID + "' , '" + ssid + "')");
        //                            MySQL.executeIUD("UPDATE teachers_has_stream_subject SET stream_subject_id = '" + ssid + "' WHERE teachers_teacher_id = '" + tID + "'");
        //
        //                            JOptionPane.showMessageDialog(this, "Class successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        //                            loadTeacherAssignmentTable();
        //                        } else {
        //                            JOptionPane.showMessageDialog(this, "select a registered subject within institute under correct stream!", "error", JOptionPane.INFORMATION_MESSAGE);
        //                        }
        //                    } catch (Exception e) {
        //                        e.printStackTrace();
        //                    }
        //
        //                }
        //
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //            }
        //        }

        // Teachers subject allocation update button
        String tID = jLabel64.getText();
        String batch = String.valueOf(jComboBox6.getSelectedItem());
        String stream = String.valueOf(jComboBox7.getSelectedItem());
        String subject = String.valueOf(jComboBox8.getSelectedItem());

        // Input Validation
        if (tID.equals("Teacher ID")) {
            JOptionPane.showMessageDialog(null, "Please select a teacher!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (batch.equals("Select Batch")) {
            JOptionPane.showMessageDialog(null, "Please select an A/L Batch!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (stream.equals("Select Stream")) {
            JOptionPane.showMessageDialog(null, "Please select a Stream!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (subject.equals("Select Subject")) {
            JOptionPane.showMessageDialog(null, "Please select a Subject!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Check if the exact allocation already exists
            ResultSet rs = MySQL.executeSearch("SELECT * FROM teachers_has_stream_subject "
                    + "INNER JOIN stream_subject ON teachers_has_stream_subject.stream_subject_id = stream_subject.id "
                    + "INNER JOIN stream ON stream.stream_id = stream_subject.stream_stream_id "
                    + "INNER JOIN subjects ON subjects.subject_id = stream_subject.subjects_subject_id "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = stream_subject.AL_batch_batch_id "
                    + "WHERE teachers_teacher_id = '" + tID + "' AND stream_name = '" + stream + "' "
                    + "AND batch_name = '" + batch + "' AND subject_name = '" + subject + "'");

            if (rs.next()) {
                // If the record already exists
                JOptionPane.showMessageDialog(null, "This Class already exists!", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Fetch the ID for the new stream_subject combination
            ResultSet rs1 = MySQL.executeSearch("SELECT id FROM stream_subject "
                    + "INNER JOIN subjects ON subjects.subject_id = stream_subject.subjects_subject_id "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = stream_subject.AL_batch_batch_id "
                    + "INNER JOIN stream ON stream.stream_id = stream_subject.stream_stream_id "
                    + "WHERE stream_name = '" + stream + "' AND batch_name = '" + batch + "' "
                    + "AND subject_name = '" + subject + "'");

            if (rs1.next()) {
                String ssid = rs1.getString("id");

                // Update the teacher's subject allocation
                MySQL.executeIUD("UPDATE teachers_has_stream_subject SET stream_subject_id = '" + ssid + "' "
                        + "WHERE teachers_teacher_id = '" + tID + "'");

                JOptionPane.showMessageDialog(this, "Class successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadTeacherAssignmentTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a registered subject within the institute under the correct stream!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while updating. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed

        // Switch to the tab containing the teacherViewTable
        jTabbedPane8.setSelectedIndex(1);  // Adjust to the correct tab index for teacherViewTable

        // Listen for table row selection
        teacherViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = teacherViewTable.getSelectedRow();

                if (selectedRow != -1) {
                    // Get teacher ID from the table (assuming it's in column 0)
                    String teacherID = String.valueOf(teacherViewTable.getValueAt(selectedRow, 0));

                    // Set teacher ID to jLabel1
                    jLabel64.setText(teacherID);

                    // Switch back to the original tab
                    jTabbedPane8.setSelectedIndex(2);

                    // Remove the listener to avoid duplicate triggers
                    teacherViewTable.removeMouseListener(this);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a teacher from the table!", "Selection Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }//GEN-LAST:event_jButton53ActionPerformed

    private void teacherAssignmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherAssignmentTableMouseClicked

        if (evt.getClickCount() == 1) {
            loadRowTeacherAssignmentTable();
            jButton1.setEnabled(false);
            jButton53.setEnabled(false);

        }

        if (evt.getClickCount() == 2) {

            //delete subject for teacher
            String tID = jLabel64.getText();
            String batch = String.valueOf(jComboBox6.getSelectedItem());
            String stream = String.valueOf(jComboBox7.getSelectedItem());
            String subject = String.valueOf(jComboBox8.getSelectedItem());

            if (tID.equals("Teacher ID")) {
                JOptionPane.showMessageDialog(null, "Please select a teacher !", "Selection Error", JOptionPane.WARNING_MESSAGE);
            } else if (batch.equals("Select Batch")) {
                JOptionPane.showMessageDialog(null, "Please select an A/L Batch !", "Selection Error", JOptionPane.WARNING_MESSAGE);
            } else if (stream.equals("Select Stream")) {
                JOptionPane.showMessageDialog(null, "Please select a Stream!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            } else if (subject.equals("Select Subject")) {
                JOptionPane.showMessageDialog(null, "Please select a Subject !", "Selection Error", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    // Find the record to delete
                    ResultSet rs = MySQL.executeSearch("SELECT teachers_has_stream_subject.id FROM teachers_has_stream_subject "
                            + "INNER JOIN stream_subject ON teachers_has_stream_subject.stream_subject_id = stream_subject.id "
                            + "INNER JOIN stream ON stream.stream_id = stream_subject.stream_stream_id "
                            + "INNER JOIN subjects ON subjects.subject_id = stream_subject.subjects_subject_id "
                            + "INNER JOIN AL_batch ON AL_batch.batch_id = stream_subject.AL_batch_batch_id "
                            + "WHERE teachers_teacher_id = '" + tID + "' AND stream_name = '" + stream + "' "
                            + "AND batch_name = '" + batch + "' AND subject_name = '" + subject + "'");

                    if (rs.next()) {
                        String deleteID = rs.getString("id");

                        int confirm = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete this assignment?",
                                "Delete Confirmation", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            // Delete from the linking table first

                            MySQL.executeIUD("DELETE FROM teachers_has_stream_subject WHERE id = '" + deleteID + "'");
                            JOptionPane.showMessageDialog(this, "Class assignment deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            // Refresh the table and UI
                            loadTeacherAssignmentTable();
                            teacherAssignmentReset();
                        }

                    }

                    // Delete the record
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "An error occurred while deleting the class assignment.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_teacherAssignmentTableMouseClicked

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed

        teacherAssignmentReset();
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void jTextField25KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField25KeyReleased

        String searchText = jTextField25.getText();

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM teachers_has_stream_subject "
                    + "INNER JOIN  stream_subject ON teachers_has_stream_subject.stream_subject_id=stream_subject.id "
                    + " INNER JOIN stream ON stream.stream_id=stream_subject.stream_stream_id  INNER JOIN subjects "
                    + "ON subjects.subject_id=stream_subject.subjects_subject_id INNER JOIN AL_batch ON "
                    + "AL_batch.batch_id=stream_subject.AL_batch_batch_id "
                    + "INNER JOIN teachers ON teachers.teacher_id=teachers_has_stream_subject.teachers_teacher_id "
                    + " WHERE teacher_id LIKE '%" + searchText + "%' OR "
                    + "first_name LIKE '%" + searchText + "%' OR "
                    + "last_name LIKE '%" + searchText + "%' OR "
                    + "batch_name LIKE '%" + searchText + "%' OR "
                    + "subject_name LIKE '%" + searchText + "%' OR "
                    + "stream_name LIKE '%" + searchText + "%' ");

            DefaultTableModel model = (DefaultTableModel) teacherAssignmentTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("teachers_teacher_id"));
                v.add(rs.getString("teachers.first_name") + rs.getString("teachers.last_name"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("stream_name"));
                v.add(rs.getString("subject_name"));

                model.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField25KeyReleased

    private void jTeacherClassSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTeacherClassSearchKeyReleased
        // teacher cls  details search
        String search = jTeacherClassSearch.getText();

        DefaultTableModel dtm = (DefaultTableModel) teacherClassDetails.getModel();
        dtm.setRowCount(0);

        try {
            // Construct the search query
            String query = "SELECT * FROM teacher_attendance "
                    + "INNER JOIN teachers ON teachers.teacher_id = teacher_attendance.teachers_teacher_id "
                    + "INNER JOIN teachers_has_stream_subject ON teachers_has_stream_subject.teachers_teacher_id = teachers.teacher_id "
                    + "INNER JOIN stream_subject ON stream_subject.id = teachers_has_stream_subject.stream_subject_id "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = stream_subject.AL_batch_batch_id "
                    + "INNER JOIN stream ON stream.stream_id = stream_subject.stream_stream_id "
                    + "INNER JOIN subjects ON subjects.subject_id = stream_subject.subjects_subject_id "
                    + "WHERE teachers.teacher_id LIKE '%" + search + "%' "
                    + "OR CONCAT(teachers.first_name, ' ', teachers.last_name) LIKE '%" + search + "%'";

            // Execute the search query
            ResultSet rs = MySQL.executeSearch(query);

            // Populate the table with the search results
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("teacher_id"));
                v.add(rs.getString("first_name") + " " + rs.getString("last_name"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("stream_name"));
                v.add(rs.getString("subject_name"));
                v.add(rs.getString("schedule_id"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTeacherClassSearchKeyReleased

    private void jteacherAttendanceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jteacherAttendanceTableMouseClicked

        int row = jteacherAttendanceTable.getSelectedRow();

        if (evt.getClickCount() == 1) {
            String sid = String.valueOf(jteacherAttendanceTable.getValueAt(row, 0));
            String tid = String.valueOf(jteacherAttendanceTable.getValueAt(row, 1));
            String tname = String.valueOf(jteacherAttendanceTable.getValueAt(row, 2));
            String batch = String.valueOf(jteacherAttendanceTable.getValueAt(row, 3));
            String subject = String.valueOf(jteacherAttendanceTable.getValueAt(row, 4));
            String date = String.valueOf(jteacherAttendanceTable.getValueAt(row, 5));
            String time = String.valueOf(jteacherAttendanceTable.getValueAt(row, 6));
            String status = String.valueOf(jteacherAttendanceTable.getValueAt(row, 7));

            if (status.equals("Present")) {
                jTmarkButton.setEnabled(false);
                jTupdateButton.setEnabled(true);
            } else {
                jTmarkButton.setEnabled(true);
                jTupdateButton.setEnabled(false);
            }

            jTID.setText(tid);
            jTname.setText(tname);
            jTscheduleID.setText(sid);
            jTdate.setText(date);
            jTtime.setText(time);

            try {
                ResultSet rs = MySQL.executeSearch("SELECT img_path FROM `teachers` WHERE `teacher_id`='" + tid + "' ");
                if (rs.next()) {
                    imgPath = rs.getString("img_path");

                    if (imgPath != null && !imgPath.isEmpty()) {
                        File imageFile = new File(imgPath);

                        if (imageFile.exists()) {
                            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                            Image image = imageIcon.getImage().getScaledInstance(jTprofile.getWidth(), jTprofile.getHeight(), Image.SCALE_SMOOTH);
                            jTprofile.setIcon(new ImageIcon(image));
                        } else {
                            // Set default image if file does not exist
                            FlatSVGIcon icon7 = new FlatSVGIcon("resources//profileImage.svg", jTprofile.getWidth(), jTprofile.getHeight());
                            jTprofile.setIcon(icon7);
                        }
                    } else {
                        // Set default image if imgPath is null or empty
                        FlatSVGIcon icon15 = new FlatSVGIcon("resources//profileImage.svg", jTprofile.getWidth(), jTprofile.getHeight());
                        jTprofile.setIcon(icon15);
                    }
                } else {
                    // Set default image if no result is found in the database
                    FlatSVGIcon icon15 = new FlatSVGIcon("resources//profileImage.svg", jTprofile.getWidth(), jTprofile.getHeight());
                    jTprofile.setIcon(icon15);
                }
            } catch (Exception e) {
                e.printStackTrace();
                FlatSVGIcon icon15 = new FlatSVGIcon("resources//profileImage.svg", jTprofile.getWidth(), jTprofile.getHeight());
                jTprofile.setIcon(icon15); // Fallback to default image in case of error
            }
        }
    }//GEN-LAST:event_jteacherAttendanceTableMouseClicked

    private void jTmarkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTmarkButtonActionPerformed
        // teacher attendance marking
        String tid = jTID.getText().trim();
        String tname = jTname.getText().trim();
        String sid = jTscheduleID.getText().trim();
        String date = jTdate.getText().trim();
        String time = jTtime.getText().trim();

        // Validate inputs
        if (tid.isEmpty() || tname.isEmpty() || sid.isEmpty() || date.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "All fields are required. Please ensure no fields are left empty.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirm the attendance marking action
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to mark attendance for:\n"
                + "Teacher ID: " + tid + "\n"
                + "Name: " + tname + "\n"
                + "Schedule ID: " + sid + "\n"
                + "Date: " + date + "\n"
                + "Time: " + time + "\n\n"
                + "Click 'Yes' to confirm.",
                "Confirm Attendance Marking",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                // Mark attendance as present
                int rowsUpdated = MySQL.executeIUD(
                        "UPDATE `teacher_attendance` SET `status`='Present' "
                        + "WHERE `schedule_id`='" + sid + "' AND `teachers_teacher_id`='" + tid + "'"
                );

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Attendance marked successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadteacherAttendanceTable(); // Reload attendance table
                    teacherAttendanceRest(); // Reset form
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No record found for the given Schedule ID and Teacher ID. Please check your input.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "An error occurred while marking attendance: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTmarkButtonActionPerformed

    private void jTupdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTupdateButtonActionPerformed
        // Teacher attendance update
        String tid = jTID.getText();
        String tname = jTname.getText();
        String sid = jTscheduleID.getText();
        String date = jTdate.getText();
        String time = jTtime.getText();

        int row = jteacherAttendanceTable.getSelectedRow();
        String status = String.valueOf(jteacherAttendanceTable.getValueAt(row, 7));

        // Validate inputs
        if (tid.isEmpty() || tname.isEmpty() || sid.isEmpty() || date.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "All fields are required. Please ensure no fields are left empty.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String newStatus = status.equals("Present") ? "Absent" : "Present";
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to update the attendance status to '" + newStatus + "'?",
                    "Confirm Update",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                MySQL.executeIUD("UPDATE `teacher_attendance` SET `status`='Absent' WHERE `schedule_id`='" + sid + "' AND `teachers_teacher_id`='" + tid + "'");
                JOptionPane.showMessageDialog(this, "Attendance updated to '" + newStatus + "' successfully!", "success", JOptionPane.INFORMATION_MESSAGE);

                // Refresh the table and reset fields
                loadteacherAttendanceTable(); // Reload table
                teacherAttendanceRest(); // Reset form
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jTupdateButtonActionPerformed

    private void jTclearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTclearButtonActionPerformed
        // Teacher att clear
        teacherAttendanceRest();
    }//GEN-LAST:event_jTclearButtonActionPerformed

    private void jTsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTsearchKeyReleased
        // Teacher attendance search
        String searchText = jTsearch.getText().trim().replace("'", "''"); // Escape single quotes to prevent SQL injection

        try {
            DefaultTableModel dtm = (DefaultTableModel) jteacherAttendanceTable.getModel();
            dtm.setRowCount(0);

            // SQL query with search functionality
            String query = "SELECT * FROM `schedule` "
                    + "INNER JOIN teachers ON teachers.teacher_id=`schedule`.teacher_id "
                    + "INNER JOIN stream_subject ON stream_subject.id=`schedule`.stream_subject_id "
                    + "INNER JOIN subjects ON subjects.subject_id=stream_subject.subjects_subject_id "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id "
                    + "INNER JOIN teacher_attendance ON teacher_attendance.schedule_id=`schedule`.schedule_id "
                    + "WHERE schedule.schedule_id LIKE '%" + searchText + "%' "
                    + "OR teachers.teacher_id LIKE '%" + searchText + "%' "
                    + "OR teachers.first_name LIKE '%" + searchText + "%' "
                    + "OR teachers.last_name LIKE '%" + searchText + "%' "
                    + "OR batch_name LIKE '%" + searchText + "%' "
                    + "OR subject_name LIKE '%" + searchText + "%' "
                    + "OR sheduled_date LIKE '%" + searchText + "%' "
                    + "OR teacher_attendance.status LIKE '%" + searchText + "%'";

            ResultSet rs = model.MySQL.executeSearch(query);

            // Populate the table with search results
            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("schedule_id"));
                v.add(rs.getString("teacher_id"));
                v.add(rs.getString("teachers.first_name") + " " + rs.getString("teachers.last_name"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("subject_name"));
                v.add(rs.getString("sheduled_date"));
                v.add(rs.getString("start_time") + " - " + rs.getString("end_time"));
                v.add(rs.getString("status"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
            JOptionPane.showMessageDialog(this, "Error while searching: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTsearchKeyReleased

    private void jTeacherBarcodeScanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTeacherBarcodeScanKeyTyped

        // Scan barcode and load schedule for the teacher
        String teacherBarcode = jTeacherBarcodeScan.getText(); // Get the scanned barcode
        try {
            // Find the teacher using the barcode
            ResultSet rs = MySQL.executeSearch("SELECT * FROM teachers WHERE barcode_id = '" + teacherBarcode + "'");
            if (rs.next()) {
                String tid = rs.getString("teacher_id");

                // Find the teacher's schedule for today
                ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `schedule` WHERE sheduled_date = CURDATE() AND teacher_id = '" + tid + "'");
                if (rs1.next()) {

                    // Get schedule details (start_time, end_time)
                    ResultSet rs2 = MySQL.executeSearch("SELECT start_time, end_time, schedule_id, "
                            + "STR_TO_DATE(REPLACE(REPLACE(start_time, '.', ':'), ' ', ''), '%l:%i%p') AS converted_start_time, "
                            + "STR_TO_DATE(REPLACE(REPLACE(end_time, '.', ':'), ' ', ''), '%l:%i%p') AS converted_end_time "
                            + "FROM `schedule` WHERE `sheduled_date` = CURDATE() AND teacher_id = '" + tid + "'");

                    if (rs2.next()) {
                        String startTime = rs2.getString("converted_start_time");
                        String endTime = rs2.getString("converted_end_time");
                        String scheduleId = rs2.getString("schedule_id");

                        // Get the current time and the time 30 minutes from now in Java
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        Date currentTimeDate = timeFormat.parse(timeFormat.format(new Date()));
                        Date timePlus30MinDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000); // 30 minutes from now

                        // Convert start and end times to Date for comparison
                        Date startTimeDate = timeFormat.parse(startTime);
                        Date endTimeDate = timeFormat.parse(endTime);

                        // Check if current time is between start_time and end_time (ongoing schedule)
                        if ((currentTimeDate.after(startTimeDate) && currentTimeDate.before(endTimeDate))
                                || (startTimeDate.after(currentTimeDate) && startTimeDate.before(timePlus30MinDate))) {

                            // Mark teacher attendance as present
                            String updateQuery = "UPDATE `teacher_attendance` SET `status` = 'Present' "
                                    + "WHERE `schedule_id` = '" + scheduleId + "' AND `teachers_teacher_id` = '" + tid + "'";
                            MySQL.executeIUD(updateQuery);
                            loadteacherAttendanceTable();

                            JOptionPane.showMessageDialog(this, "Teacher's attendance marked as Present.", "Attendance Updated", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "No upcoming or ongoing schedule for this teacher.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "No schedule allocated for today!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid barcode!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(AcademicDashboard.class.getName()).log(Level.SEVERE, null, ex);

        }

    }//GEN-LAST:event_jTeacherBarcodeScanKeyTyped

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // Teacher barcode print button

        try {
            // Retrieve teacher details
            String name = jLabel109.getText();
            String nic = jLabel110.getText();
            Icon barcodeIcon = jLabel112.getIcon();

            if (name.isEmpty() || nic.isEmpty() || barcodeIcon == null) {
                JOptionPane.showMessageDialog(this, "Incomplete details or missing barcode.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a printable job
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintable(new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return NO_SUCH_PAGE;
                    }

                    // Translate to the printable area
                    Graphics2D g2 = (Graphics2D) graphics;
                    g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                    // Define box dimensions
                    int boxWidth = 250;
                    int boxHeight = 150;
                    int x = 50;
                    int y = 50;

                    // Draw a border for the box
                    g2.drawRect(x, y, boxWidth, boxHeight);

                    // Add the name
                    g2.setFont(new Font("Arial", Font.PLAIN, 12));
                    g2.drawString("Name: " + name, x + 10, y + 20);

                    // Add the NIC
                    g2.drawString("NIC: " + nic, x + 10, y + 40);

                    // Add the barcode image
                    if (barcodeIcon instanceof ImageIcon) {
                        ImageIcon imageIcon = (ImageIcon) barcodeIcon;
                        Image barcodeImage = imageIcon.getImage();
                        g2.drawImage(barcodeImage, x + 10, y + 50, 200, 60, null);
                    }

                    return PAGE_EXISTS;
                }
            });

            // Display print dialog and print if confirmed
            boolean doPrint = printerJob.printDialog();
            if (doPrint) {
                printerJob.print();

                jLabel109.setText("NIC");
                jLabel108.setText("Teacher Name");
                FlatSVGIcon icon19 = new FlatSVGIcon("resources//profileImage.svg", jLabel111.getWidth(), jLabel111.getHeight());
                jLabel111.setIcon(icon19);
                FlatSVGIcon icon20 = new FlatSVGIcon("resources//barcode(1).svg", jLabel112.getWidth(), jLabel112.getHeight());
                jLabel112.setIcon(icon20);

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while printing.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jTable14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable14MouseClicked
        // Select row
        int selectedRow = jTable14.getSelectedRow();

        if (evt.getClickCount() == 1 && selectedRow != -1) {
            String tid = String.valueOf(jTable14.getValueAt(selectedRow, 0));
            String bid = String.valueOf(jTable14.getValueAt(selectedRow, 1));
            String tname = String.valueOf(jTable14.getValueAt(selectedRow, 2));
            String nic = String.valueOf(jTable14.getValueAt(selectedRow, 3));

            jLabel109.setText(tname);
            jLabel110.setText(nic);

            try {
                // Query to fetch teacher data
                ResultSet rs = MySQL.executeSearch("SELECT * FROM teachers WHERE nic = '" + nic + "'");

                if (rs.next()) {
                    // Load teacher's image
                    String imgPath = rs.getString("img_path");
                    if (imgPath != null) {
                        File imageFile = new File(imgPath);
                        if (imageFile.exists()) {
                            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                            Image image = imageIcon.getImage().getScaledInstance(jLabel111.getWidth(), jLabel111.getHeight(), Image.SCALE_SMOOTH);
                            jLabel111.setIcon(new ImageIcon(image));
                        } else {
                            FlatSVGIcon icon19 = new FlatSVGIcon("resources//profileImage.svg", jLabel111.getWidth(), jLabel111.getHeight());
                            jLabel111.setIcon(icon19);
                        }
                    } else {
                        FlatSVGIcon icon19 = new FlatSVGIcon("resources//profileImage.svg", jLabel111.getWidth(), jLabel111.getHeight());
                        jLabel111.setIcon(icon19);
                    }

                    // Load barcode
                    String barcode = rs.getString("barcode_id");
                    if (barcode != null) {
                        String barcodePath = "src/barcode/barcode_" + barcode + ".png";
                        File barcodeFile = new File(barcodePath);
                        if (barcodeFile.exists()) {
                            ImageIcon barcodeIcon = new ImageIcon(barcodeFile.getAbsolutePath());
                            Image barcodeImage = barcodeIcon.getImage().getScaledInstance(jLabel112.getWidth(), jLabel112.getHeight(), Image.SCALE_SMOOTH);
                            jLabel112.setIcon(new ImageIcon(barcodeImage));
                        } else {
                            // Set default icon if barcode image is not found
                            FlatSVGIcon defaultIcon = new FlatSVGIcon("resources/barcode(1).svg", jLabel112.getWidth(), jLabel112.getHeight());
                            jLabel112.setIcon(defaultIcon);
                        }
                    } else {
                        // Set default icon if barcode is null
                        FlatSVGIcon defaultIcon = new FlatSVGIcon("resources/barcode(1).svg", jLabel112.getWidth(), jLabel112.getHeight());
                        jLabel112.setIcon(defaultIcon);

                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(AcademicDashboard.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable14MouseClicked

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:

        jTabbedPane7.setSelectedIndex(0);
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:
        jTabbedPane7.setSelectedIndex(2);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
        jTabbedPane7.setSelectedIndex(4);
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
        // stream Button Add:

        String stream = jTextField39.getText();

        if (stream.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter Stream", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!stream.matches("[a-zA-Z0-9 ]+")) { // Validation for allowed characters
            JOptionPane.showMessageDialog(this, "Stream name can only contain letters, numbers, and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (stream.length() > 50) { // Length validation
            JOptionPane.showMessageDialog(this, "Stream name cannot exceed 50 characters.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) FROM `stream` WHERE `stream_name` = '" + stream + "'");

                if (rs.next() && rs.getInt(1) > 0) {
                    // Stream already exists
                    JOptionPane.showMessageDialog(this, "Stream already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `stream`(`stream_name`)"
                            + "VALUES('" + stream + "') ");

                    if (isActive()) {
                        JOptionPane.showMessageDialog(this, "Stream added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        jTextField39.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add stream. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                jTextField39.setText("");
                loadSubStre();
                loadStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton65ActionPerformed

    private void jTextField42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField42ActionPerformed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        // subject Add Button:
        // String stream = jTextField39.getText();
        String subject = jTextField42.getText();

        if (subject.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter Stream", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!subject.matches("[a-zA-Z0-9 ]+")) { // Validation for allowed characters
            JOptionPane.showMessageDialog(this, "Subject name can only contain letters, numbers, and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (subject.length() > 50) { // Length validation
            JOptionPane.showMessageDialog(this, "Subject name cannot exceed 50 characters.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) FROM `subjects` WHERE `subject_name` = '" + subject + "'");

                if (rs.next() && rs.getInt(1) > 0) {
                    // Subject already exists
                    JOptionPane.showMessageDialog(this, "Subject already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `subjects`(`subject_name`)"
                            + "VALUES('" + subject + "') ");

                    jTextField42.setText("");
                }
                if (isActive()) {
                    JOptionPane.showMessageDialog(this, "Subject added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    jTextField42.setText("");

                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add subject. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                loadSubStre();
                loadSubjects();
                overviewSubject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        // Clear Subject Details:
        reset();
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
        // Stream Subject Add Button:

        try {
            String batch = String.valueOf(jComboBox9.getSelectedItem());

            if (!BatchMap.containsKey(batch)) {
                JOptionPane.showMessageDialog(this, "Invalid Batch selected. Please check the available options.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String stream = String.valueOf(jComboBox23.getSelectedItem());

            if (!StreamMap.containsKey(stream)) {
                JOptionPane.showMessageDialog(this, "Invalid stream selected. Please check the available options.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String subject = String.valueOf(jComboBox25.getSelectedItem());

            if (!SubjectMap.containsKey(subject)) {
                JOptionPane.showMessageDialog(this, "Invalid subject selected. Please check the available options.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `stream_subject` WHERE `stream_stream_id` = '"
                    + StreamMap.get(stream) + "' AND `subjects_subject_id` = '" + SubjectMap.get(subject) + "' AND `AL_batch_batch_id` = '" + BatchMap.get(batch) + "' ");

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "This stream and subject association already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                MySQL.executeIUD("INSERT INTO `stream_subject`(`stream_stream_id`,`subjects_subject_id`,AL_batch_batch_id)"
                        + "VALUES('" + StreamMap.get(stream) + "','" + SubjectMap.get(subject) + "','" + BatchMap.get(batch) + "')");

                loadSubStre();
                reset();

                JOptionPane.showMessageDialog(this, "Batch,Stream and subject association added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton69ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // ADD Batch:

        String batch = jTextField4.getText();

        if (batch.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter Batch", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!batch.matches("[a-zA-Z0-9 ]+")) { // Validation for allowed characters
            JOptionPane.showMessageDialog(this, "Batch name can only contain letters, numbers, and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (batch.length() > 50) { // Length validation
            JOptionPane.showMessageDialog(this, "Batch name cannot exceed 50 characters.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) FROM `AL_batch` WHERE `batch_name` = '" + batch + "'");

                if (rs.next() && rs.getInt(1) > 0) {
                    // Stream already exists
                    JOptionPane.showMessageDialog(this, "Batch already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `AL_batch`(`batch_name`)"
                            + "VALUES('" + batch + "') ");

                    if (isActive()) {
                        JOptionPane.showMessageDialog(this, "Batch added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        jTextField39.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add Batch. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                jTextField4.setText("");
                loadSubStre();
                loadBatch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        // Table Row Double Click
        int row = jTable3.getSelectedRow();
        String id = String.valueOf(jTable3.getValueAt(row, 0));

        if (evt.getClickCount() == 2) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this stream and subject association?",
                    "Delete Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // Delete dependent rows in the schedule table
                    MySQL.executeIUD("DELETE FROM `schedule` WHERE `stream_subject_id` = '" + id + "'");

                    // Now delete the row from the stream_subject table
                    MySQL.executeIUD("DELETE FROM `stream_subject` WHERE `id` = '" + id + "'");

                    reset();
                    loadSubStre();
                    overviewSubject();
                    JOptionPane.showMessageDialog(this, "Stream and subject association deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting stream and subject association: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTextField44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField44ActionPerformed

    private void jComboBox27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox27ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // clear Class Shedule:
        resetScheduleSubject();
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        // Update Shedule Table:
        //        String id = jLabel51.getText();
        Date date = jDateChooser7.getDate();

        if (date == null) {
            JOptionPane.showMessageDialog(this, "Please select the date!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sdf = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String Batch = String.valueOf(jComboBox27.getSelectedItem());
        String Subject = String.valueOf(jComboBox28.getSelectedItem());
        String Teacher = String.valueOf(jComboBox3.getSelectedItem());
        String Start_Time = jTextField44.getText().trim();
        String End_Time = jTextField46.getText().trim();

        try {
            int selectedRow = jTable9.getSelectedRow();
            String id = String.valueOf(jTable9.getValueAt(selectedRow, 0));

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row first!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation Checks
            if (Batch.equals("Select Batch")) {
                JOptionPane.showMessageDialog(this, "Please select the batch!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Subject.equals("Select Subject")) {
                JOptionPane.showMessageDialog(this, "Please select the subject!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Teacher.equals("Select Teacher")) {
                JOptionPane.showMessageDialog(this, "Please select the teacher!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Start_Time.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the start time!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (End_Time.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the end time!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for existing schedule with the same details
            String checkQuery = "SELECT * FROM schedule WHERE teacher_id = '" + TeachersMap.get(Teacher) + "' "
                    + "AND subject_id = '" + SubjectMap.get(Subject) + "' "
                    + "AND start_time = '" + Start_Time + "' "
                    + "AND end_time = '" + End_Time + "' "
                    + "AND schedule_id = '" + id + "'";

            ResultSet checkResult = MySQL.executeSearch(checkQuery);

            if (checkResult.next()) {
                JOptionPane.showMessageDialog(this, "A schedule with the same teacher, subject, and time already exists!",
                        "Duplicate Found", JOptionPane.WARNING_MESSAGE);

            } else {

                // Update the schedule
                String updateQuery = "UPDATE schedule SET sheduled_date = '" + sdf + "', "
                        + "AL_batch_batch_id = '" + BatchMap.get(Batch) + "', "
                        + "subject_id = '" + SubjectMap.get(Subject) + "', "
                        + "teacher_id = '" + TeachersMap.get(Teacher) + "', "
                        + "start_time = '" + Start_Time + "', "
                        + "end_time = '" + End_Time + "' "
                        + "WHERE schedule_id = '" + id + "'";

                MySQL.executeIUD(updateQuery);

                loadshedule();  // Refresh the schedule table
                JOptionPane.showMessageDialog(this, "Schedule updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetScheduleSubject();  // Reset form fields
                loadSTA();
                loadteacherAttendanceTable();

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton73ActionPerformed
        // Class Schedule Add Button:

        Date sheduleDate = jDateChooser7.getDate();

        String Batch = String.valueOf(jComboBox27.getSelectedItem());

        String Subject = String.valueOf(jComboBox28.getSelectedItem());

        String Teacher = String.valueOf(jComboBox3.getSelectedItem());

        String startTime = jTextField44.getText();

        String EndTime = jTextField46.getText();

        if (sheduleDate == null) {
            JOptionPane.showMessageDialog(this, "Please select the date!");
        } else if (Batch.equals("Select Batch")) {
            JOptionPane.showMessageDialog(this, "Please select the batch!");

        } else if (Subject.equals("Select Subject")) {
            JOptionPane.showMessageDialog(this, "Please select the subject!");

        } else if (Teacher.equals("Select Teacher")) {
            JOptionPane.showMessageDialog(this, "Please select the Teacher!");

        } else if (startTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the start time!");

        } else if (EndTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the end time!");

        } else {
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(sheduleDate);
            try {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `schedule` WHERE `sheduled_date` = '"
                        + formattedDate + "' AND `AL_batch_batch_id` = '" + BatchMap.get(Batch) + "' AND `subject_id` = '" + SubjectMap.get(Subject) + "' AND `teacher_id` = '" + TeachersMap.get(Teacher) + "' AND `start_time` = '" + startTime + "' AND `end_time` = '" + EndTime + "'");

                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "This stream and subject association already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `schedule` (`sheduled_date`, `AL_batch_batch_id`, `subject_id`, `teacher_id`, `stream_subject_id`, `start_time`, `end_time`) SELECT '" + formattedDate + "', '" + BatchMap.get(Batch) + "', '" + SubjectMap.get(Subject) + "', '" + TeachersMap.get(Teacher) + "', `id`, '" + startTime + "', '" + EndTime + "' FROM `stream_subject` WHERE `subjects_subject_id` = '" + SubjectMap.get(Subject) + "' AND `AL_batch_batch_id` = '" + BatchMap.get(Batch) + "'");

                    loadshedule();
                    loadSTA();
                    loadteacherAttendanceTable();
                    JOptionPane.showMessageDialog(this, "Schedule added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                resetScheduleSubject();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton73ActionPerformed

    private void jTextField46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField46ActionPerformed

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        // Schedule Table click:
        int row = jTable9.getSelectedRow();

        String id = String.valueOf(jTable9.getValueAt(row, 0));
        //        jLabel51.setText(id);

        String scheduleDate = String.valueOf(jTable9.getValueAt(row, 1));
        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
        jButton73.setEnabled(false);
        try {
            Date date = dateFormate.parse(scheduleDate);
            jDateChooser7.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();

        }
        String Batch = String.valueOf(jTable9.getValueAt(row, 2));
        jComboBox27.setSelectedItem(Batch);
        String subject = String.valueOf(jTable9.getValueAt(row, 3));
        jComboBox28.setSelectedItem(subject);
        //jButton69.setEnabled(false);
        String teacher = String.valueOf(jTable9.getValueAt(row, 4));
        jComboBox3.setSelectedItem(teacher);
        String Start_Time = String.valueOf(jTable9.getValueAt(row, 5));
        jTextField44.setText(Start_Time);
        String End_Time = String.valueOf(jTable9.getValueAt(row, 6));
        jTextField46.setText(End_Time);

        if (evt.getClickCount() == 1) {

            //            jComboBox27.setEnabled(false);
            //            jComboBox28.setEnabled(false);
            //            jComboBox3.setEnabled(false);
            jButton73.setEnabled(false);

        }

        if (evt.getClickCount() == 2) {

            if (evt.getClickCount() == 2) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete this schedule?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        // Retrieve the ID of the selected row

                        // Execute the delete query
                        MySQL.executeIUD("DELETE  FROM `student_attendance` WHERE `schedule_id`='" + id + "'");
                        MySQL.executeIUD("DELETE  FROM `teacher_attendance` WHERE `schedule_id`='" + id + "'");
                        MySQL.executeIUD("DELETE  FROM `schedule` WHERE `schedule_id`='" + id + "'");

                        // Refresh the table or perform any additional operations
                        loadshedule();
                        resetScheduleSubject();
                        loadSTA();
                        loadteacherAttendanceTable();
                        JOptionPane.showMessageDialog(this, "Schedule deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error deleting schedule: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

    }//GEN-LAST:event_jTable9MouseClicked

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jTextField43KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField43KeyReleased
        // Schedule search:

        // Schedule search:
        String searchText = jTextField43.getText().trim(); // Get input from the search text field

        try {
            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
            model.setRowCount(0); // Clear the table before inserting new rows

            // Corrected SQL query
            String query = "SELECT schedule.schedule_id, schedule.sheduled_date, AL_batch.batch_name, "
                    + "subjects.subject_name, CONCAT(teachers.first_name, ' ', teachers.last_name) AS teacher_name, "
                    + "schedule.start_time, schedule.end_time "
                    + "FROM schedule "
                    + "INNER JOIN subjects ON subjects.subject_id = schedule.subject_id "
                    + "INNER JOIN teachers ON teachers.teacher_id = schedule.teacher_id "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id = schedule.AL_batch_batch_id "
                    + "WHERE schedule.schedule_id LIKE '%" + searchText + "%' OR "
                    + "schedule.sheduled_date LIKE '%" + searchText + "%' OR "
                    + "AL_batch.batch_name LIKE '%" + searchText + "%' OR "
                    + "subjects.subject_name LIKE '%" + searchText + "%' OR "
                    + "teachers.first_name LIKE '%" + searchText + "%' OR "
                    + "teachers.last_name LIKE '%" + searchText + "%' OR "
                    + "schedule.start_time LIKE '%" + searchText + "%' OR "
                    + "schedule.end_time LIKE '%" + searchText + "%'";

            // Execute the query
            ResultSet rs = MySQL.executeSearch(query);

            // Populate the table with the results
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("schedule_id"));
                row.add(rs.getString("sheduled_date"));
                row.add(rs.getString("batch_name"));
                row.add(rs.getString("subject_name"));
                row.add(rs.getString("teacher_name")); // Combined teacher name
                row.add(rs.getString("start_time"));
                row.add(rs.getString("end_time"));

                model.addRow(row); // Add the row to the table
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField43KeyReleased

    private void jButton76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton76ActionPerformed

        resetMaterial();
        jButton78.setEnabled(true);
    }//GEN-LAST:event_jButton76ActionPerformed

    private void jButton78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton78ActionPerformed
        //add material button
        //        Date uploadedDate = jDateChooser3.getDate();
        //        String documentName = jTextField49.getText().trim();
        //        String Description = jTextArea1.getText().trim();
        //        String fileType = String.valueOf(jComboBox30.getSelectedItem()).trim();
        //        String FilePath = jTextField9.getText().trim();
        //
        //        if (uploadedDate == null) {
        //            JOptionPane.showMessageDialog(this, "Please select the date!");
        //        } else if (documentName.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please Enter the Document Name!");
        //        } else if (Description.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please Enter the Description!");
        //        } else if (fileType.equals("Select File Type")) {
        //            JOptionPane.showMessageDialog(this, "Please Select the File Type!");
        //        } else if (FilePath.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please Enter The File Path!");
        //        } else {
        //            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(uploadedDate);
        //
        //            try {
        //                ResultSet rs = model.MySQL.executeSearch("SELECT * FROM `documents` WHERE "
        //                        + "file_name = '" + documentName + "' AND file_path = '" + FilePath + "' AND upload_date = '" + formattedDate + "'");
        //
        //                if (rs.next()) {
        //                    JOptionPane.showMessageDialog(this, "This document already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
        //                } else {
        //                    String resourcesPath = "src/documents";
        //
        //                    // Append timestamp to avoid name conflicts
        //                    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //                    String newFileName = documentName + "_" + timeStamp + "." + fileType;
        //
        //                    File saveDir = new File(resourcesPath);
        //                    if (!saveDir.exists()) {
        //                        saveDir.mkdirs();
        //                    }
        //
        //                    File fileToSave = new File(saveDir, newFileName);
        //
        //                    try {
        //                        Path sourcePath = new File(FilePath).toPath();
        //                        Path destinationPath = fileToSave.toPath();
        //
        //                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        //
        //                        String newFilePath = fileToSave.getAbsolutePath().replace("\\", "/");
        //                        FilePath = newFilePath;
        //
        //                    } catch (IOException ioException) {
        //                        JOptionPane.showMessageDialog(this, "Error saving document: " + ioException.getMessage());
        //                    }
        //
        //                    try {
        //                        model.MySQL.executeIUD("INSERT INTO `documents` (`file_name`,`description`,`document_type`,`file_path`,`upload_date`)"
        //                                + " VALUES('" + documentName + "' ,'" + Description + "' , '" + fileType + "' ,'" + FilePath + "' , '" + formattedDate + "')");
        //
        //                        JOptionPane.showMessageDialog(this, "File uploaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        //                        loadMaterialLibraryTable();
        //                        resetMaterial();
        //
        //                    } catch (Exception e) {
        //                        e.printStackTrace();
        //                    }
        //                }
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //            }
        //        }

        // Add material button
        Date uploadedDate = jDateChooser3.getDate();
        String documentName = jTextField49.getText().trim();
        String description = jTextArea1.getText().trim();
        String fileType = String.valueOf(jComboBox30.getSelectedItem()).trim();
        String filePath = jTextField9.getText().trim();

        // === Validation Checks ===
        if (uploadedDate == null) {
            JOptionPane.showMessageDialog(this, "Please select the upload date!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (documentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the document name!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a description!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (fileType.equals("Select File Type")) {
            JOptionPane.showMessageDialog(this, "Please select a valid file type!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide the file path!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!filePath.endsWith("." + fileType)) {
            JOptionPane.showMessageDialog(this, "File type does not match the selected file extension!", "File Type Mismatch", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(uploadedDate);

        try {
            // Check if a document with the same name or path already exists
            ResultSet rs = model.MySQL.executeSearch("SELECT * FROM `documents` WHERE `file_name` = '" + documentName + "' OR `file_path` = '" + filePath + "'");

            if (rs.next()) {
                String existingFileName = rs.getString("file_name");
                String existingFilePath = rs.getString("file_path");

                if (existingFileName.equals(documentName)) {
                    JOptionPane.showMessageDialog(this, "A document with this name already exists!", "Duplicate Document Name", JOptionPane.WARNING_MESSAGE);
                } else if (existingFilePath.equals(filePath)) {
                    JOptionPane.showMessageDialog(this, "A document with this file path already exists!", "Duplicate File Path", JOptionPane.WARNING_MESSAGE);
                }
                return;
            }

            // Prepare directory and file name
            String resourcesPath = "src/documents";
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String newFileName = documentName.replaceAll("\\s+", "_") + "_" + timeStamp + "." + fileType;

            File saveDir = new File(resourcesPath);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            File fileToSave = new File(saveDir, newFileName);

            // Copy the file to the destination
            try {
                Path sourcePath = new File(filePath).toPath();
                Path destinationPath = fileToSave.toPath();

                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                // Update file path to reflect the saved location
                filePath = fileToSave.getAbsolutePath().replace("\\", "/");

            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this, "Error saving document: " + ioException.getMessage(), "File Save Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insert data into the database
            try {
                model.MySQL.executeIUD("INSERT INTO `documents` (`file_name`, `description`, `document_type`, `file_path`, `upload_date`) "
                        + "VALUES ('" + documentName + "', '" + description + "', '" + fileType + "', '" + filePath + "', '" + formattedDate + "')");

                JOptionPane.showMessageDialog(this, "File uploaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadMaterialLibraryTable();
                resetMaterial();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error inserting document into the database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton78ActionPerformed

    private void jButton79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton79ActionPerformed
        //delete material button
        int selectedRow = jTable19.getSelectedRow();
        Date uploadedDate = jDateChooser3.getDate();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(uploadedDate);
        String documentName = jTextField49.getText();
        String Description = jTextArea1.getText();
        String fileType = String.valueOf(jComboBox30.getSelectedItem());
        String FilePath = jTextField9.getText();
        String id = String.valueOf(jTable19.getValueAt(selectedRow, 0));
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            return;
        } else {

            try {
                ResultSet rs = model.MySQL.executeSearch("SELECT *FROM `documents` WHERE `id`='" + id + "' "
                        + "AND `file_name`='" + documentName + "' AND `description`='" + Description + "' AND "
                        + "`document_type`='" + fileType + "' AND `file_path`='" + FilePath + "' ");

                if (rs.next()) {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to delete this document?",
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {

                        try {
                            // Retrieve the file path
                            ResultSet result = model.MySQL.executeSearch("SELECT file_path FROM `documents` WHERE id = '" + id + "'  "
                                    + "AND `file_name`='" + documentName + "' AND `description`='" + Description + "' AND "
                                    + "`document_type`='" + fileType + "' AND `file_path`='" + FilePath + "' ");
                            if (result.next()) {
                                String filePath = rs.getString("file_path");
                                File file = new File(filePath);

                                // Delete the file from disk
                                if (file.exists() && file.delete()) {
                                    // Delete the database record
                                    model.MySQL.executeIUD("DELETE FROM `documents` WHERE id = '" + id + "'");
                                    JOptionPane.showMessageDialog(this, "Document deleted successfully!",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                    loadMaterialLibraryTable();
                                    resetMaterial();
                                    jButton78.setEnabled(true);

                                } else {
                                    JOptionPane.showMessageDialog(this, "Failed to delete the file from disk!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Error deleting document: " + e.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete the file!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton79ActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName().toLowerCase();
            String fileType = String.valueOf(jComboBox30.getSelectedItem());

            if (fileType.equals("Select File Type")) {
                JOptionPane.showMessageDialog(this,
                        "Please select the file type first!",
                        "Please select the file type first", JOptionPane.ERROR_MESSAGE);
            } else if (fileName.endsWith("." + fileType)) {

                jTextField9.setText(selectedFile.getAbsolutePath());

            } else {
                JOptionPane.showMessageDialog(this,
                        "File type doesn't match!",
                        "Invalid File Type", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void jButton77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton77ActionPerformed
        // Update material button
        //        int selectedRow = jTable19.getSelectedRow();
        //
        //        if (selectedRow == -1) {
        //            JOptionPane.showMessageDialog(this, "Please select a row to update!", "Selection Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //
        //        // Retrieve input values
        //        Date uploadedDate = jDateChooser3.getDate();
        //        String documentName = jTextField49.getText();
        //        String description = jTextArea1.getText();
        //        String fileType = String.valueOf(jComboBox30.getSelectedItem());
        //        String filePath = jTextField9.getText();
        //        String id = String.valueOf(jTable19.getValueAt(selectedRow, 0));
        //
        //        // Validate inputs
        //        if (uploadedDate == null) {
        //            JOptionPane.showMessageDialog(this, "Please select the upload date!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (documentName.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please enter the document name!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (description.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please enter the document description!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (fileType.equals("Select File Type")) {
        //            JOptionPane.showMessageDialog(this, "Please select the file type!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //        if (filePath.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "Please enter the file path!", "Validation Error", JOptionPane.WARNING_MESSAGE);
        //            return;
        //        }
        //
        //        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this document?", "Confirm Update", JOptionPane.YES_NO_OPTION);
        //        if (confirm != JOptionPane.YES_OPTION) {
        //            return;
        //        }
        //
        //        try {
        //            // Format the date
        //            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(uploadedDate);
        //
        //            // Check for duplicate document
        //            ResultSet rs = model.MySQL.executeSearch("SELECT * FROM `documents` WHERE `document_type` = '" + fileType + "' "
        //                    + "AND `file_name` = '" + documentName + "' AND `file_path` = '" + filePath + "' "
        //                    + "AND `upload_date` = '" + formattedDate + "' AND `id` != '" + id + "'");
        //            if (rs.next()) {
        //                JOptionPane.showMessageDialog(this, "A document with the same details already exists!", "Duplicate Found", JOptionPane.WARNING_MESSAGE);
        //                return;
        //            }
        //
        //            // File copying process
        //            String filePathToSave = filePath;
        //            if (!filePath.isEmpty()) {
        //                try {
        //                    String resourcesPath = "src/documents";
        //                    String newFileName = UUID.randomUUID().toString() + "." + fileType;
        //                    File saveDir = new File(resourcesPath);
        //
        //                    if (!saveDir.exists()) {
        //                        saveDir.mkdirs();
        //                    }
        //
        //                    File fileToSave = new File(saveDir, newFileName);
        //                    Path sourcePath = new File(filePath).toPath();
        //                    Path destinationPath = fileToSave.toPath();
        //
        //                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        //                    filePathToSave = fileToSave.getAbsolutePath().replace("\\", "/");
        //
        //                } catch (IOException ioException) {
        //                    JOptionPane.showMessageDialog(this, "Error saving the file: " + ioException.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        //                    return;
        //                }
        //            }
        //
        //            // Update the document in the database
        //            model.MySQL.executeIUD("UPDATE `documents` SET `file_name` = '" + documentName + "', "
        //                    + "`description` = '" + description + "', `document_type` = '" + fileType + "', "
        //                    + "`file_path` = '" + filePathToSave + "', `upload_date` = '" + formattedDate + "' WHERE `id` = '" + id + "'");
        //
        //            JOptionPane.showMessageDialog(this, "Document updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        //
        //            // Refresh table and reset form
        //            loadMaterialLibraryTable();
        //            resetMaterial();
        //            jButton78.setEnabled(true);
        //
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            JOptionPane.showMessageDialog(this, "Error updating the document: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        //        }

        int selectedRow = jTable19.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve input values
        Date uploadedDate = jDateChooser3.getDate();
        String documentName = jTextField49.getText().trim();
        String description = jTextArea1.getText().trim();
        String fileType = String.valueOf(jComboBox30.getSelectedItem()).trim();
        String filePath = jTextField9.getText().trim();
        String id = String.valueOf(jTable19.getValueAt(selectedRow, 0));

        // === Input Validation ===
        if (uploadedDate == null) {
            JOptionPane.showMessageDialog(this, "Please select the upload date!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (documentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the document name!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the document description!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (fileType.equals("Select File Type")) {
            JOptionPane.showMessageDialog(this, "Please select the file type!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the file path!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this document?", "Confirm Update", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Format the date
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(uploadedDate);

            // Check for duplicate document
            ResultSet rs = model.MySQL.executeSearch("SELECT * FROM `documents` WHERE `file_name` = '" + documentName + "' "
                    + "AND `file_path` = '" + filePath + "' AND `upload_date` = '" + formattedDate + "' AND `id` != '" + id + "'");

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "A document with the same name, path, or upload date already exists!", "Duplicate Found", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Retrieve existing data for comparison
            ResultSet existingData = model.MySQL.executeSearch("SELECT * FROM `documents` WHERE `id` = '" + id + "'");
            if (existingData.next()) {
                String existingName = existingData.getString("file_name");
                String existingDescription = existingData.getString("description");
                String existingFileType = existingData.getString("document_type");
                String existingFilePath = existingData.getString("file_path");
                String existingDate = existingData.getString("upload_date");

                // Check if there are changes
                if (existingName.equals(documentName) && existingDescription.equals(description)
                        && existingFileType.equals(fileType) && existingFilePath.equals(filePath)
                        && existingDate.equals(formattedDate)) {
                    JOptionPane.showMessageDialog(this, "No changes detected. Update is not required.", "No Update Needed", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            // File copying process
            String filePathToSave = filePath;
            if (!filePath.isEmpty()) {
                try {
                    String resourcesPath = "src/documents";
                    String newFileName = UUID.randomUUID().toString() + "." + fileType;
                    File saveDir = new File(resourcesPath);

                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }

                    File fileToSave = new File(saveDir, newFileName);
                    Path sourcePath = new File(filePath).toPath();
                    Path destinationPath = fileToSave.toPath();

                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    filePathToSave = fileToSave.getAbsolutePath().replace("\\", "/");

                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(this, "Error saving the file: " + ioException.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Update the document in the database
            model.MySQL.executeIUD("UPDATE `documents` SET `file_name` = '" + documentName + "', "
                    + "`description` = '" + description + "', `document_type` = '" + fileType + "', "
                    + "`file_path` = '" + filePathToSave + "', `upload_date` = '" + formattedDate + "' WHERE `id` = '" + id + "'");

            JOptionPane.showMessageDialog(this, "Document updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Refresh table and reset form
            loadMaterialLibraryTable();
            resetMaterial();
            jButton78.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating the document: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton77ActionPerformed

    private void jTable19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable19MouseClicked

        if (evt.getClickCount() == 1) {
            loadMaterialSelectedRowData();
            jButton78.setEnabled(false);

        }
    }//GEN-LAST:event_jTable19MouseClicked

    private void jTextField50KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField50KeyReleased

        String searchText = jTextField50.getText();
        String query = "SELECT * FROM `documents` WHERE `id` LIKE '%" + searchText + "%' "
                + "OR `file_name` LIKE '%" + searchText + "%' "
                + "OR `description` LIKE '%" + searchText + "%' "
                + "OR `document_type` LIKE '%" + searchText + "%' "
                + "OR `file_path` LIKE '%" + searchText + "%' "
                + "OR `upload_date` LIKE '%" + searchText + "%'";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            DefaultTableModel dtm = (DefaultTableModel) jTable19.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("id"));
                v.add(rs.getString("file_name"));
                v.add(rs.getString("description"));
                v.add(rs.getString("document_type"));
                v.add(rs.getString("file_path"));
                v.add(rs.getString("upload_date"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField50KeyReleased

    private void jBarcodeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBarcodeTableMouseClicked
        // student barcode view button
        int selectedRow = jBarcodeTable.getSelectedRow();

        if (evt.getClickCount() == 1 && selectedRow != -1) {
            String sid = String.valueOf(jBarcodeTable.getValueAt(selectedRow, 0));
            String bid = String.valueOf(jBarcodeTable.getValueAt(selectedRow, 1));
            String sname = String.valueOf(jBarcodeTable.getValueAt(selectedRow, 2));
            String nic = String.valueOf(jBarcodeTable.getValueAt(selectedRow, 3));

            jLabel106.setText(sname);
            jLabel94.setText(nic);

            try {
                // Query to fetch teacher data
                ResultSet rs = MySQL.executeSearch("SELECT * FROM students WHERE nic = '" + nic + "'");

                if (rs.next()) {
                    // Load teachers's image
                    String imgPath = rs.getString("img_path");
                    if (imgPath != null) {
                        File imageFile = new File(imgPath);
                        if (imageFile.exists()) {
                            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                            Image image = imageIcon.getImage().getScaledInstance(jLabel103.getWidth(), jLabel103.getHeight(), Image.SCALE_SMOOTH);
                            jLabel103.setIcon(new ImageIcon(image));
                        } else {
                            FlatSVGIcon icon17 = new FlatSVGIcon("resources//profileImage.svg", jLabel103.getWidth(), jLabel103.getHeight());
                            jLabel103.setIcon(icon17);
                        }
                    } else {
                        FlatSVGIcon icon17 = new FlatSVGIcon("resources//profileImage.svg", jLabel103.getWidth(), jLabel103.getHeight());
                        jLabel103.setIcon(icon17);
                    }

                    // Load barcode
                    String barcode = rs.getString("barcode_id");
                    if (barcode != null) {
                        String barcodePath = "src/barcode/barcode_" + barcode + ".png";
                        File barcodeFile = new File(barcodePath);
                        if (barcodeFile.exists()) {
                            ImageIcon barcodeIcon = new ImageIcon(barcodeFile.getAbsolutePath());
                            Image barcodeImage = barcodeIcon.getImage().getScaledInstance(jLabel29.getWidth(), jLabel29.getHeight(), Image.SCALE_SMOOTH);
                            jLabel29.setIcon(new ImageIcon(barcodeImage));
                        } else {
                            // Set default icon if barcode image is not found
                            FlatSVGIcon defaultIcon = new FlatSVGIcon("resources/barcode(1).svg", jLabel29.getWidth(), jLabel29.getHeight());
                            jLabel29.setIcon(defaultIcon);
                        }
                    } else {
                        // Set default icon if barcode is null
                        FlatSVGIcon defaultIcon = new FlatSVGIcon("resources/barcode(1).svg", jLabel29.getWidth(), jLabel29.getHeight());
                        jLabel29.setIcon(defaultIcon);

                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(AcademicDashboard.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jBarcodeTableMouseClicked

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed

        //        JFileChooser fileChooser = new JFileChooser();
        //        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //
        //        int returnValue = fileChooser.showOpenDialog(null);
        //
        //        if (returnValue == JFileChooser.APPROVE_OPTION) {
        //
        //            File selectedFile = fileChooser.getSelectedFile();
        //            ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());
        //
        //            Image image = imageIcon.getImage().getScaledInstance(jLabel30.getWidth(), jLabel30.getHeight(), Image.SCALE_SMOOTH);
        //
        //            String path = selectedFile.getAbsolutePath();
        //            jLabel30.setIcon(new ImageIcon(image));
        //            TimgPath = path;
        //        }
        JFileChooser fileChooser = new JFileChooser();

        // Set file filter to allow only PNG and JPG files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files (PNG, JPG)", "png", "jpg");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Validate the selected file extension
            String fileName = selectedFile.getName().toLowerCase();
            if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
                ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());
                Image image = imageIcon.getImage().getScaledInstance(jLabel30.getWidth(), jLabel30.getHeight(), Image.SCALE_SMOOTH);

                String path = selectedFile.getAbsolutePath();
                jLabel30.setIcon(new ImageIcon(image));
                TimgPath = path;
            } else {
                JOptionPane.showMessageDialog(this, "Invalid file type! Please select a PNG or JPG image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed

        //print Report button enrollment:
        boolean isPrinted = false;
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
            if (jTable4.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable4.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);

        } catch (Exception e) {
            if (jTable14.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }

        }


    }//GEN-LAST:event_jButton27ActionPerformed

    private void jTextField22KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField22KeyReleased
        // search

        try {
            String searchText = jTextField22.getText().trim().toLowerCase();

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

    }//GEN-LAST:event_jTextField22KeyReleased

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // Class Attendence Report :
        boolean isPrinted = false;
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
            if (jStudentAttendanceReportTable.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jStudentAttendanceReportTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jStudentAttendanceReportTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jTextField41KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField41KeyReleased
        // search

        try {
            String searchText = jTextField41.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jStudentAttendanceReportTable.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jStudentAttendanceReportTable.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField41KeyReleased

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // Teacher Enrollment Report:
        boolean isPrinted = false;
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
            if (jTable11.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable11.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable11.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }

        }


    }//GEN-LAST:event_jButton50ActionPerformed

    private void jTextField35KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField35KeyReleased
        // search

        try {
            String searchText = jTextField35.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable11.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable11.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField35KeyReleased

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // Teacher Class Attendence:
        boolean isPrinted = false;
        try {
//            long invoiceid = System.currentTimeMillis();
//            jLabel84.setText(String.valueOf(invoiceid));
//            String EmployeeUserName = jLabel85.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();
            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/TeacherAttendenceTest.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/TeacherAttendenceTest.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", dateTime);
            parameters.put("IMAGE_PATH", imagePath);
//            params.put("Parameter2", Department);
//            params.put("Parameter3", Basesalary);
            // Data source
            if (jTattReportsTable.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTattReportsTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTattReportsTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }

        }


    }//GEN-LAST:event_jButton54ActionPerformed

    private void jTextField37KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField37KeyReleased
        // search

        try {
            String searchText = jTextField37.getText().trim().toLowerCase();
            DefaultTableModel dtm = (DefaultTableModel) jTattReportsTable.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);
            jTattReportsTable.setRowSorter(rowSorter);

            // Apply the filter using the search text
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));

        } catch (Exception e) {
            e.printStackTrace(); // Print error details for debugging
        }
    }//GEN-LAST:event_jTextField37KeyReleased

    private void jButton68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton68ActionPerformed
        // Class Schedule Report:
        boolean isPrinted = false;
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
            if (jTable15.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable15.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable15.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }//GEN-LAST:event_jButton68ActionPerformed

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
        // Subject Management Report:
        boolean isPrinted = false;
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
            if (jTable18.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable18.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable18.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }//GEN-LAST:event_jButton71ActionPerformed

    private void jButton82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton82ActionPerformed
        // Matirial Report:
        boolean isPrinted = false;
        try {
//            long invoiceid = System.currentTimeMillis();
//            jLabel84.setText(String.valueOf(invoiceid));
//            String EmployeeUserName = jLabel85.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String imagePath = getClass().getResource("/resources/LOGO.png").toString();

            // Load report file
            InputStream path = this.getClass().getResourceAsStream("/reports/MatirialReportTest.jasper");
            if (path == null) {
                throw new RuntimeException("Report file not found at /reports/MatirialReportTest.jasper");
            }

            // Parameters for the report
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", dateTime);
            parameters.put("IMAGE_PATH", imagePath);
//            params.put("Parameter2", Department);
//            params.put("Parameter3", Basesalary);
            // Data source
            if (jTable20.getRowCount() == 0) {
                System.out.println("Table is empty.");
                throw new RuntimeException("Table has no data to generate the report.");
            }
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable20.getModel());

            JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);

            isPrinted = JasperPrintManager.printReport(report, false);
        } catch (Exception e) {
            if (jTable20.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Table has no data to generate report", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else if (!isPrinted) {
                JOptionPane.showMessageDialog(this, "Printing was canceled by the user.", "Printing Canceled", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton82ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        jTextField13.grabFocus();
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField24KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField24KeyReleased
        // search

        try {
            String searchText = jTextField24.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jBarcodeTable.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jBarcodeTable.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField24KeyReleased

    private void jTextField20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyReleased
        // search

        String searchText = jTextField20.getText();
        DefaultTableModel dtm = (DefaultTableModel) jStAttendanceTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtm);
        jStAttendanceTable.setRowSorter(sorter);

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            try {

                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));

            } catch (PatternSyntaxException e) {

                System.out.println("Invalid search pattern: " + e.getMessage());

            }
        }
    }//GEN-LAST:event_jTextField20KeyReleased

    private void jTextField51KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField51KeyReleased
        // search

        try {
            String searchText = jTextField51.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable20.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable20.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField51KeyReleased

    private void jTextField47KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField47KeyReleased
        // search

        try {
            String searchText = jTextField47.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable18.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable18.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField47KeyReleased

    private void jTextField45KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField45KeyReleased
        // search

        try {
            String searchText = jTextField45.getText().trim().toLowerCase();

            DefaultTableModel dtm = (DefaultTableModel) jTable15.getModel();
            TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(dtm);

            jTable15.setRowSorter(rowSorter);

            if (searchText.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextField45KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        loadTeacherClassTable();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        loadSTA();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        loadStAttendanceTable();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void filterStudentsComboBox(String searchText) {
        try {
            // Query the database: show all if searchText is empty
            String query;
            if (searchText.isEmpty()) {
                query = "SELECT * FROM `students`";  // Load all students by default
            } else {
                query = "SELECT * FROM `students` WHERE `first_name` LIKE '" + searchText + "%' OR`last_name` LIKE '" + searchText + "%' OR `nic` LIKE '" + searchText + "%'";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            // Populate the combo box
            Vector<String> vector = new Vector<>();
            vector.add("Select Student");  // Default option

            // Populate combo box with matching results
            while (resultSet.next()) {
                String studentName = resultSet.getString("first_name");
                vector.add(studentName);
                StudentsMap.put(studentName, resultSet.getString("student_id"));
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            jComboBox24.setModel(model);  // Update combo box items

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//
//        FlatMacLightLaf.setup();
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AcademicDashboard().setVisible(true);
//
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboardconstantpanel;
    private javax.swing.JButton browseButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JPanel changingpanel;
    private javax.swing.JTable jAllClassDetailsTable;
    private javax.swing.JButton jAttendanceMarkButton;
    private javax.swing.JButton jAttendanceUpdateButton;
    private javax.swing.JPanel jBar;
    private javax.swing.JTextField jBarcodeScan;
    private javax.swing.JTable jBarcodeTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButton78;
    private javax.swing.JButton jButton79;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton82;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jChart;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox23;
    private javax.swing.JComboBox<String> jComboBox24;
    private javax.swing.JComboBox<String> jComboBox25;
    private javax.swing.JComboBox<String> jComboBox27;
    private javax.swing.JComboBox<String> jComboBox28;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox30;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
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
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPanel jPie;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JLabel jSAtDate;
    private javax.swing.JLabel jSID;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jStAttendanceTable;
    private javax.swing.JTable jStudentAttendanceReportTable;
    private javax.swing.JLabel jSubject;
    private javax.swing.JLabel jTID;
    private javax.swing.JLabel jTName;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTabbedPane jTabbedPane9;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable19;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable9;
    private javax.swing.JTable jTattReportsTable;
    private javax.swing.JButton jTclearButton;
    private javax.swing.JLabel jTdate;
    private javax.swing.JTextField jTeacherBarcodeScan;
    private javax.swing.JTextField jTeacherClassSearch;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton jTmarkButton;
    private javax.swing.JLabel jTname;
    private javax.swing.JLabel jTprofile;
    private javax.swing.JLabel jTscheduleID;
    private javax.swing.JTextField jTsearch;
    private javax.swing.JLabel jTtime;
    private javax.swing.JButton jTupdateButton;
    private javax.swing.JLabel jalbatch;
    private javax.swing.JLabel jstID;
    private javax.swing.JLabel jstName;
    private javax.swing.JTable jteacherAttendanceTable;
    private javax.swing.JPanel menu1;
    private javax.swing.JPanel menu2;
    private javax.swing.JPanel menu3;
    private javax.swing.JPanel menu4;
    private javax.swing.JPanel menu5;
    private javax.swing.JPanel menupanel;
    private javax.swing.JPanel overviewpanel;
    private javax.swing.JPanel profile;
    private javax.swing.JTable selectedSubjectsTable;
    private javax.swing.JTable studentTable;
    private javax.swing.JPanel studentmanagement;
    private javax.swing.JPanel subjectmanagement;
    private javax.swing.JTable teacherAssignmentTable;
    private javax.swing.JTable teacherClassDetails;
    private javax.swing.JTable teacherViewTable;
    private javax.swing.JPanel teachermanagement;
    // End of variables declaration//GEN-END:variables

    private void loadBatch() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `AL_batch`");

            Vector vector = new Vector();
            vector.add("Select Batch");

            while (resultSet.next()) {
                vector.add(resultSet.getString("batch_name"));
                BatchMap.put(resultSet.getString("batch_name"), resultSet.getString("batch_id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
//                jComboBox22.setModel(model);
            jComboBox27.setModel(model);
            jComboBox1.setModel(model);
//                jComboBox6.setModel(model);
            jComboBox9.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBatchforteacherallocation() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `AL_batch`");

            Vector vector = new Vector();
            vector.add("Select Batch");

            while (resultSet.next()) {
                vector.add(resultSet.getString("batch_name"));
                BatchMap.put(resultSet.getString("batch_name"), resultSet.getString("batch_id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox6.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStream() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `stream`");

            Vector vector = new Vector();
            vector.add("Select Stream");

            while (resultSet.next()) {
                vector.add(resultSet.getString("stream_name"));
                StreamMap.put(resultSet.getString("stream_name"), resultSet.getString("stream_id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox23.setModel(model);
            jComboBox7.setModel(model);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubjects() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `subjects`");

            Vector vector = new Vector();
            vector.add("Select Subject");

            while (resultSet.next()) {
                vector.add(resultSet.getString("subject_name"));
                SubjectMap.put(resultSet.getString("subject_name"), resultSet.getString("subject_id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox25.setModel(model);
            jComboBox28.setModel(model);
            jComboBox8.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubject1() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `subjects`");

            Vector vector = new Vector();
            vector.add("Select Subject");

            while (resultSet.next()) {
                vector.add(resultSet.getString("subject_name"));
                Subject1Map.put(resultSet.getString("subject_name"), resultSet.getString("subject_id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox18.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTeachers() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `teachers`");

            Vector vector = new Vector();
            vector.add("Select Teacher");

            while (resultSet.next()) {
                String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                vector.add(fullName);
                TeachersMap.put(fullName, resultSet.getString("teacher_id")); // Store full name as key
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
//            jComboBox16.setModel(model);
//            jComboBox17.setModel(model);
//            jComboBox18.setModel(model);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearStudentReg() {
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField19.setText("");
        jTextField23.setText("");
        jButton7.setEnabled(true);
        // Clear DateChoosers
        Date date = null;
        jDateChooser1.setDate(date);
        jTextField1.setText("");
        jDateChooser2.setDate(date);

        // Reset the icon for jLabel30
        FlatSVGIcon icon4 = new FlatSVGIcon("resources//profileImage.svg", jLabel7.getWidth(), jLabel7.getHeight());
        jLabel7.setIcon(icon4);

        FlatSVGIcon icon16 = new FlatSVGIcon("resources//barcode(1).svg", jLabel13.getWidth(), jLabel13.getHeight());
        jLabel13.setIcon(icon16);

        // Clear Radio Buttons
        buttonGroup1.clearSelection();  // Correct way to clear radio buttons

        jTextField5.setText("");
        jTextField6.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        // Reset other fields and focus
        jButton7.setEnabled(true);
        jTextField19.setEditable(true);
        studentTable.clearSelection();

        imgPath = null;
        jTextField12.requestFocus();
        jButton24.setEnabled(false);
    }

    private void clearSelectedSub() {
        jTextField2.setText("");
        jComboBox18.setSelectedIndex(0);
        jComboBox24.setSelectedIndex(0);
        jButton3.setEnabled(true);  // Disable Add button
        jTextField2.setEditable(true);  // Prevent name editing
        selectedSubjectsTable.clearSelection();
    }

    private void reset() {
        jComboBox23.setEnabled(true);
        jComboBox25.setEnabled(true);
        jComboBox9.setSelectedIndex(0);
        jComboBox23.setSelectedIndex(0);
        jComboBox25.setSelectedIndex(0);
        jButton69.setEnabled(true);
    }

    private void resetScheduleSubject() {
        jDateChooser7.setDate(null);
        jComboBox27.setSelectedItem("Select Batch");
        jComboBox28.setSelectedItem("Select Subject");
        jComboBox3.setSelectedItem("Select Teacher");
        jTextField44.setText("");
        jTextField46.setText("");
        jDateChooser7.grabFocus();
        jButton73.setEnabled(true);
        jButton73.setEnabled(true);
    }

    private void resetMaterial() {
        jDateChooser3.setDate(null);
        jTextField49.setText("");
        jTextArea1.setText("");
        jComboBox30.setSelectedIndex(0);
        jTextField9.setText("");
        jDateChooser3.grabFocus();
    }

    private void teacherAssignmentReset() {
        jLabel64.setText("Teacher ID");
        jComboBox6.setSelectedItem("Select Batch");
        jComboBox7.setSelectedItem("Select Stream");
        jComboBox8.setSelectedItem("Select Subject");
        jButton1.setEnabled(true);
        jButton53.setEnabled(true);
    }

    private void teacherDetailsReset() {
        jTextField26.setText("");
        jTextField27.setText("");
        jTextField28.setText("");
        jDateChooser6.setDate(null);
        jDateChooser4.setDate(null);
        jTextField11.setText("");
        jTextField30.setText("");
        jTextField29.setText("");
        jTextField21.setText("");
        TimgPath = null;
        jTextField28.setEditable(true);

        // Reset the icon for jLabel30
        FlatSVGIcon icon7 = new FlatSVGIcon("resources//profileImage.svg", jLabel30.getWidth(), jLabel30.getHeight());
        jLabel30.setIcon(icon7);

        buttonGroup3.clearSelection();  // Correct way to clear radio buttons

        jTextField28.setEditable(true);
        jTextField26.grabFocus();
        jButton17.setEnabled(true);
    }

    //ta
    private void loadToTeacherAttendanceTable() {
        try {
            // Fetch schedules and teachers not in the attendance table
            ResultSet rs = MySQL.executeSearch(
                    "SELECT schedule.schedule_id, teachers.teacher_id, schedule.sheduled_date "
                    + "FROM `schedule` "
                    + "INNER JOIN teachers ON teachers.teacher_id = `schedule`.teacher_id "
                    + "WHERE NOT EXISTS ("
                    + "    SELECT 1 FROM teacher_attendance "
                    + "    WHERE teacher_attendance.schedule_id = schedule.schedule_id "
                    + "    AND teacher_attendance.teachers_teacher_id = teachers.teacher_id"
                    + ");"
            );

            while (rs.next()) {
                String sid = rs.getString("schedule_id");
                String tID = rs.getString("teacher_id");
                String scheduleDate = rs.getString("sheduled_date");

                // Insert new teachers into the attendance table with a default status of 'Absent'
                String insertQuery = "INSERT INTO `teacher_attendance` "
                        + "(`attendance_date`, `schedule_id`, `teachers_teacher_id`, `status`) "
                        + "VALUES ('" + scheduleDate + "', '" + sid + "', '" + tID + "', 'Absent')";

                MySQL.executeIUD(insertQuery);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the error
        }
    }

    private void loadteacherAttendanceTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jteacherAttendanceTable.getModel();
            dtm.setRowCount(0);

            ResultSet rs = model.MySQL.executeSearch("SELECT * FROM `schedule` INNER JOIN teachers ON teachers.teacher_id=`schedule`.teacher_id  "
                    + "INNER JOIN stream_subject ON stream_subject.id=`schedule`.stream_subject_id "
                    + "INNER JOIN subjects ON subjects.subject_id=stream_subject.subjects_subject_id "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id "
                    + "INNER JOIN teacher_attendance ON teacher_attendance.schedule_id=`schedule`.schedule_id");

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("schedule_id"));
                v.add(rs.getString("teacher_id"));
                v.add(rs.getString("teachers.first_name") + " " + rs.getString("teachers.last_name"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("subject_name"));
                v.add(rs.getString("sheduled_date"));
                v.add(rs.getString("start_time") + " - " + rs.getString("end_time"));
                v.add(rs.getString("status"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
    }

    private void teacherAttendanceRest() {
        jTtime.setText("Time");
        jTdate.setText("Date");
        jTscheduleID.setText("Schedule ID");
        jTname.setText("Teacher Name");
        jTID.setText("Teacher ID");
        jTmarkButton.setEnabled(true);
        jTupdateButton.setEnabled(true);
        FlatSVGIcon icon15 = new FlatSVGIcon("resources//profileImage.svg", jTprofile.getWidth(), jTprofile.getHeight());
        jTprofile.setIcon(icon15);
    }

    // st attendance
    private void clearSTattendance() {
        jAttendanceMarkButton.setEnabled(true);
        jstName.setText("Student Name");
        jstID.setText("Student ID");
        jSAtDate.setText("date");
        jSubject.setText("Subject");
        jalbatch.setText("A/L Batch");
        jTName.setText("Teacher Name");
        jSID.setText("Schedule ID");
        FlatSVGIcon icon14 = new FlatSVGIcon("resources//profileImage.svg", jLabel124.getWidth(), jLabel124.getHeight());
        jLabel124.setIcon(icon14);
        jAttendanceMarkButton.setEnabled(true);
        jAttendanceUpdateButton.setEnabled(true);
    }

    private void loadStAttendanceTable() {
        String sid = jSID.getText();
        String date = jSAtDate.getText();
        String stID = jstID.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate;
        try {
            parsedDate = sdf.parse(date);
        } catch (ParseException e) {
//            System.out.println("ok");
            return; // Exit the method if the date format is invalid
        }
        String formattedDate = sdf.format(parsedDate);

        try {
            ResultSet rs1 = MySQL.executeSearch("SELECT * FROM student_attendance INNER JOIN students ON"
                    + "  students.student_id=student_attendance.students_student_id"
                    + " WHERE schedule_id='" + sid + "' AND attendance_date='" + formattedDate + "' ");

            DefaultTableModel dtm = (DefaultTableModel) jStAttendanceTable.getModel();
            dtm.setRowCount(0);

            while (rs1.next()) {
                Vector v = new Vector();
                v.add(rs1.getString("student_id"));
                v.add(rs1.getString("first_name") + " " + rs1.getString("last_name"));
                v.add(rs1.getString("status"));

                dtm.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStudentToAttendanceTable() {
        try {
            // Query to retrieve all students associated with a schedule but not yet in the attendance table
            ResultSet rs = MySQL.executeSearch(
                    "SELECT schedule.schedule_id, students.student_id, schedule.sheduled_date "
                    + "FROM `schedule` "
                    + "INNER JOIN subjects ON subjects.subject_id = schedule.subject_id "
                    + "INNER JOIN students_has_subjects ON students_has_subjects.subjects_subject_id = subjects.subject_id "
                    + "INNER JOIN students ON students.student_id = students_has_subjects.students_student_id "
                    + "WHERE NOT EXISTS ("
                    + "    SELECT 1 FROM student_attendance "
                    + "    WHERE student_attendance.schedule_id = schedule.schedule_id "
                    + "    AND student_attendance.students_student_id = students.student_id)"
            );

            while (rs.next()) {
                String sid = rs.getString("schedule_id");
                String stID = rs.getString("student_id");
                String scheduleDate = rs.getString("sheduled_date");

                // Insert new student into the attendance table with a default status of 'Absent'
                MySQL.executeIUD(
                        "INSERT INTO `student_attendance` (`attendance_date`, `schedule_id`, `students_student_id`, `status`) "
                        + "VALUES ('" + scheduleDate + "', '" + sid + "', '" + stID + "', 'Absent')"
                );
            }

            // Automatically refresh the attendance table after adding new entries
            loadStAttendanceTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSTA() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jAllClassDetailsTable.getModel();
            dtm.setRowCount(0);

            ResultSet rs = model.MySQL.executeSearch("SELECT * FROM schedule INNER "
                    + "JOIN teachers ON teachers.teacher_id = `schedule`.teacher_id INNER JOIN "
                    + "stream_subject ON stream_subject.id=`schedule`.stream_subject_id "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id"
                    + " INNER JOIN subjects ON"
                    + "  subjects.subject_id=stream_subject.subjects_subject_id ");

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("schedule_id"));
                v.add(rs.getString("teachers.first_name") + " " + rs.getString("teachers.last_name"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("subject_name"));
                v.add(rs.getString("sheduled_date"));
                v.add(rs.getString("start_time") + " - " + rs.getString("end_time"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
    }

    private void loadBarcodeTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jBarcodeTable.getModel();
            dtm.setRowCount(0);

            ResultSet rs = model.MySQL.executeSearch("SELECT `student_id`,`barcode_id`,CONCAT(students.first_name, ' ',"
                    + " students.last_name) AS student_name , `nic` FROM `students` ");

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("student_id"));
                v.add(rs.getString("barcode_id"));
                v.add(rs.getString("student_name"));
                v.add(rs.getString("nic"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
    }

    private void loadTBarcodeTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable14.getModel();
            dtm.setRowCount(0);

            ResultSet rs = model.MySQL.executeSearch("SELECT `teacher_id`,`barcode_id`,CONCAT(teachers.first_name, ' ',"
                    + " teachers.last_name) AS teacher_name , `nic` FROM `teachers` ");

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("teacher_id"));
                v.add(rs.getString("barcode_id"));
                v.add(rs.getString("teacher_name"));
                v.add(rs.getString("nic"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }

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

    private void loadTeacherClassTable() {
        DefaultTableModel dtm = (DefaultTableModel) teacherClassDetails.getModel();
        dtm.setRowCount(0);

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM teacher_attendance "
                    + "INNER JOIN teachers ON teachers.teacher_id=teacher_attendance.teachers_teacher_id "
                    + "INNER JOIN teachers_has_stream_subject ON teachers_has_stream_subject.teachers_teacher_id=teachers.teacher_id "
                    + "INNER JOIN stream_subject ON stream_subject.id=teachers_has_stream_subject.stream_subject_id  "
                    + "INNER JOIN AL_batch ON AL_batch.batch_id= stream_subject.AL_batch_batch_id  "
                    + "INNER JOIN stream ON stream.stream_id=stream_subject.stream_stream_id  "
                    + "INNER JOIN subjects ON subjects.subject_id=stream_subject.subjects_subject_id "
                    + "INNER JOIN schedule ON schedule.schedule_id=teacher_attendance.schedule_id");

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("teacher_id"));
                v.add(rs.getString("first_name") + " " + rs.getString("last_name"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("stream_name"));
                v.add(rs.getString("subject_name"));
                v.add(rs.getString("schedule.schedule_id"));
                v.add(rs.getString("schedule.sheduled_date"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentEnrollmentReport() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `students` "
                    + "INNER JOIN `gender` ON `students`.`gender_id`=`gender`.`id` "
                    + "INNER JOIN `AL_batch` ON `students`.`AL_batch_batch_id`=`AL_batch`.`batch_id` "
                    + "INNER JOIN `stream` ON `students`.`stream_stream_id`=`stream`.`stream_id` ");

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
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

    private void loadTeacherEnrollmenrt() {

        try {
//            ResultSet rs = MySQL.executeSearch("SELECT *From `teachers` INNER JOIN `gender` ON `teachers`.`gender_id`= `gender`.`id`"
//                    + "INNER JOIN `teachers_has_stream_subject` ON `teachers`.`teacher_id` = `teachers_has_stream_subject`.`id`"
//                    + "INNER JOIN `stream_subject` ON `stream_subject`.`id` = `teachers_has_stream_subject`.`id`");
//            
//            

            ResultSet rs = MySQL.executeSearch("SELECT *From teachers INNER JOIN gender ON teachers.gender_id= gender.id INNER JOIN teachers_has_stream_subject ON teachers.teacher_id = teachers_has_stream_subject.id INNER JOIN stream_subject ON stream_subject.id = teachers_has_stream_subject.id INNER JOIN stream ON stream.stream_id=stream_subject.stream_stream_id INNER JOIN subjects ON subjects.subject_id=stream_subject.subjects_subject_id");

            DefaultTableModel model = (DefaultTableModel) jTable11.getModel();
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

    private void loadsheduleForReport() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `schedule` INNER JOIN `AL_batch`"
                    + "ON `schedule`.`AL_batch_batch_id` = `AL_batch`.`batch_id` INNER JOIN `subjects` "
                    + "ON `schedule`.`subject_id` = `subjects`.`subject_id` INNER JOIN `teachers` "
                    + "ON `schedule`.`teacher_id` = `teachers`.`teacher_id`");

            DefaultTableModel model = (DefaultTableModel) jTable15.getModel();
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

    private void loadsheduleForSubject() {

        try {

//            
            ResultSet resultSet = MySQL.executeSearch("SELECT *FROM subjects INNER JOIN stream_subject ON"
                    + " stream_subject.subjects_subject_id=subjects.subject_id INNER JOIN"
                    + " AL_batch ON AL_batch.batch_id=stream_subject.AL_batch_batch_id INNER JOIN "
                    + "stream ON stream.stream_id=stream_subject.stream_stream_id INNER JOIN "
                    + "schedule ON schedule.schedule_id=stream_subject.id INNER JOIN "
                    + "teachers ON teachers.teacher_id=schedule.teacher_id");

            DefaultTableModel model = (DefaultTableModel) jTable18.getModel();
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

    private void loadMatirealLibraryForReport() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `documents`");

            DefaultTableModel model = (DefaultTableModel) jTable20.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("file_name"));
                vector.add(resultSet.getString("description"));
                vector.add(resultSet.getString("document_type"));
                vector.add(resultSet.getString("upload_date"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStudentAttendanceReportTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jStudentAttendanceReportTable.getModel();
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
                v.add(rs.getString("total_present"));
                v.add(rs.getString("total_absent"));
                v.add(rs.getString("percentage_present"));
                v.add(rs.getString("percentage_absent"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
    }

    private void loadTattReportsTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTattReportsTable.getModel();
            dtm.setRowCount(0);

            ResultSet rs = model.MySQL.executeSearch("SELECT YEAR(teacher_attendance.attendance_date) AS year,"
                    + " MONTH(teacher_attendance.attendance_date) AS month,"
                    + " AL_batch.batch_name,"
                    + " subjects.subject_name,"
                    + " CONCAT(teachers.first_name, ' ', teachers.last_name) AS teacher_name,"
                    + " COUNT(*) AS total_records,"
                    + " SUM(CASE WHEN teacher_attendance.status = 'Present' THEN 1 ELSE 0 END) AS total_present"
                    + ", SUM(CASE WHEN teacher_attendance.status = 'Absent' THEN 1 ELSE 0 END) AS total_absent,"
                    + " ROUND((SUM(CASE WHEN teacher_attendance.status = 'Present' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)), 2) AS percentage_present,"
                    + " ROUND((SUM(CASE WHEN teacher_attendance.status = 'Absent' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)), 2) AS percentage_absent"
                    + " FROM teacher_attendance "
                    + "INNER JOIN schedule ON schedule.schedule_id = teacher_attendance.schedule_id"
                    + " INNER JOIN stream_subject ON stream_subject.id = schedule.stream_subject_id"
                    + " INNER JOIN subjects ON subjects.subject_id = stream_subject.subjects_subject_id"
                    + " INNER JOIN AL_batch ON AL_batch.batch_id = stream_subject.AL_batch_batch_id"
                    + " INNER JOIN teachers_has_stream_subject ON teachers_has_stream_subject.stream_subject_id = stream_subject.stream_stream_id"
                    + " INNER JOIN teachers ON teachers.teacher_id = teachers_has_stream_subject.teachers_teacher_id"
                    + " GROUP BY YEAR(teacher_attendance.attendance_date),"
                    + " MONTH(teacher_attendance.attendance_date),"
                    + " AL_batch.batch_name,"
                    + " subjects.subject_name,"
                    + " teacher_name"
                    + " ORDER BY "
                    + "year, month");

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("year"));
                v.add(rs.getString("month"));
                v.add(rs.getString("batch_name"));
                v.add(rs.getString("subject_name"));
                v.add(rs.getString("teacher_name"));
                v.add(rs.getString("total_records"));
                v.add(rs.getString("total_present"));
                v.add(rs.getString("total_absent"));
                v.add(rs.getString("percentage_present"));
                v.add(rs.getString("percentage_absent"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
    }

}
