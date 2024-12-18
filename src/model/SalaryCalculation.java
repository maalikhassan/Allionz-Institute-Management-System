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

    /**
     * @return the Basesalary
     */
    public double getBasesalary() {
        return Basesalary;
    }

    /**
     * @param Basesalary the Basesalary to set
     */
    public void setBasesalary(double Basesalary) {
        this.Basesalary = Basesalary;
    }

    /**
     * @return the Allowance
     */
    public double getAllowance() {
        return Allowance;
    }

    /**
     * @param Allowance the Allowance to set
     */
    public void setAllowance(double Allowance) {
        this.Allowance = Allowance;
    }
    private double Basesalary;
    private double Allowance;

    public double calculateTotal() {
         return Basesalary + Allowance;
    }

    public void setBasesalary(String basesalary) {
         this.Basesalary = Basesalary;
    }

    public void setAllowance(String allowance) {
        this.Allowance = Allowance;
    }
}
