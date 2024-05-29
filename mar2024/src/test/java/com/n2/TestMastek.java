package com.n2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TestMastek {

  public List<Employee> solution(List<Employee> employees, BigDecimal threshHold) {
    return employees.stream()
        .filter(emp->emp.salary.compareTo(threshHold)>0)
        .collect(Collectors.toList());
  }

  @Test
  public void testSimple1() {
    List<Employee> employeeList = List.of(
        new Employee(1,"A",BigDecimal.valueOf(24000)),
        new Employee(1,"B",BigDecimal.valueOf(25000)),
        new Employee(1,"C",BigDecimal.valueOf(26000)),
        new Employee(1,"D",BigDecimal.valueOf(27000))
    );
    List<Employee> actualEmployeeList = solution(employeeList,BigDecimal.valueOf(25000));
    assertEquals(2, actualEmployeeList.size());
  }
  @Test
  public void testSimple2() {
    List<Employee> employeeList = List.of(
        new Employee(1,"A",BigDecimal.valueOf(24000)),
        new Employee(2,"B",BigDecimal.valueOf(25000)),
        new Employee(3,"C",BigDecimal.valueOf(26000)),
        new Employee(4,"D",BigDecimal.valueOf(27000))
    );
    List<Employee> actualEmployeeList = solution(employeeList,BigDecimal.valueOf(25000));
    System.out.println(actualEmployeeList);
    for (Employee e : actualEmployeeList) {
      if (e.id == 1 || e.id==2) {
        fail("Invalid Employee returned");
      }
    }
  }
}

class Employee {
  int id;
  String name;
  BigDecimal salary;
  Employee(int id, String name, BigDecimal salary) {
    this.id = id;
    this.name = name;
    this.salary = salary;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }
  @Override
  public String toString() {
    return "Employee["+id+","+name+","+salary+"]";
  }
}
