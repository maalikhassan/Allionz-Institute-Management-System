/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pramu
 */
public class SalaryCalculation {

//    /**
//     * @return the Basesalary
//     */
//    public double getBasesalary() {
//        return Basesalary;
//    }
//
//    /**
//     * @param Basesalary the Basesalary to set
//     */
//    public void setBasesalary(double Basesalary) {
//        this.Basesalary = Basesalary;
//    }
//
//    /**
//     * @return the Allowance
//     */
//    public double getAllowance() {
//        return Allowance;
//    }
//
//    /**
//     * @param Allowance the Allowance to set
//     */
//    public void setAllowance(double Allowance) {
//        this.Allowance = Allowance;
//    }
//    private double Basesalary;
//    private double Allowance;
//
//    public double calculateTotal() {
//         return Basesalary + Allowance;
//    }
//
//    public void setBasesalary(String basesalary) {
//         this.Basesalary = Basesalary;
//    }
//
//    public void setAllowance(String allowance) {
//        this.Allowance = Allowance;
//    }
    private double baseSalary;
    private double allowance;
    private double epf;
    private double epfEmployer;
    private double etf;
    private double netPay;

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getAllowance() {
        return allowance;
    }

    public double getTotalEarnings() {
        return baseSalary + allowance;
    }

    public double getEPF() {
        return epf;
    }

    public double getEPFEmployer() {
        return epfEmployer;
    }

    public double getETF() {
        return etf;
    }

    public double getNetPay() {
        return netPay;
    }

    public void calculate() {
        this.epf = baseSalary * 0.08;
        this.epfEmployer = baseSalary * 0.12;
        this.etf = baseSalary * 0.03;
        this.netPay = (baseSalary + allowance) - epf;
    }

}
