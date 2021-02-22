package program;

import model.Employee;
import repository.EmployeeRepository;
import repository.Key;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            EmployeeRepository repositorySection = new EmployeeRepository("section.csv");
           /* System.out.println(repositorySection);
            System.out.println(repositorySection.getCoolestEmployees());
            System.out.println(repositorySection.getMaxScores());*/
            //System.out.println(repositorySection.getAverageScores());
           // System.out.println(repositorySection.getCountCoolestEmployees());
            //System.out.println(repositorySection.getCoolestEmployeesAll());
            System.out.println(repositorySection.getSubCoolestEmployees());
            System.out.println(repositorySection.getMaxScoresSubCoolestEmployees());
            //System.out.println(repositorySection.getMaxScoreAll());
            EmployeeRepository repositoryDepartment = new EmployeeRepository("department.csv");
            //System.out.println(repositoryDepartment);
            System.out.println(repositoryDepartment.getMaxCountDepartments());
            System.out.println(repositoryDepartment.getMinCountDepartments());
            //System.out.println(repositoryDepartment.sort(Key.N));
            //System.out.println(repositoryDepartment.departmentCoolestEmployees());
            //System.out.println(repositoryDepartment.getAverageScores());
            //System.out.println(repositoryDepartment.greatAverageScoreDepartments());
            System.out.println(repositoryDepartment.getCoolestEmployeesAll());
            System.out.println(repositoryDepartment.maxCountCoolestEmployeeDepartments());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
