package model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String secondName;
    private int rating;
    private int numberSection;
    private int numberDepartment;


    public Employee(String firstName, String secondName, int rating, int numberDepartment) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.rating = rating;
        this.numberDepartment = numberDepartment;
    }

    public Employee(String firstName, String secondName, int rating, int numberSection, int numberDepartment) {
        this(firstName, secondName, rating, numberDepartment);
        this.numberSection = numberSection;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumberSection() {
        return numberSection;
    }

    public void setNumberSection(int numberSection) {
        this.numberSection = numberSection;
    }

    public int getNumberDepartment() {
        return numberDepartment;
    }

    public void setNumberDepartment(int numberDepartment) {
        this.numberDepartment = numberDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return rating == employee.rating &&
                numberSection == employee.numberSection &&
                numberDepartment == employee.numberDepartment &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(secondName, employee.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, rating, numberSection, numberDepartment);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", rating=" + rating +
                ", numberSection=" + numberSection +
                ", numberDepartment=" + numberDepartment +
                '}';
    }
}
