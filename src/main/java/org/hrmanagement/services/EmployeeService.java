package org.hrmanagement.services;

import lombok.Getter;
import lombok.Setter;
import org.hrmanagement.models.Employee;
import org.hrmanagement.repositores.EmployeeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter @Setter
public class EmployeeService {
    @Autowired
    EmployeeProxy employeeProxy;
    public Employee getEmployee(final Long id){
        Employee employee = employeeProxy.getEmployee(id);
        return employee;
    }
    public Iterable<Employee> getAllEmployee(){
        return employeeProxy.getEmployees();
    }
    public  void deleteEmploye(final  Long id){
        employeeProxy.deleteEmployee(id);
    }
    public Employee saveEmployee(Employee employee){
        Employee savedEmployee;
        // régle de gestion : le nom de famille de l'emplyer doit Etre en majuscule..
        employee.setLastName(employee.getLastName().toUpperCase());
        if (employee.getId()== null){
            // si l'employee n'existe pas on va le créer
            savedEmployee = (Employee) employeeProxy.createEmployee(employee);
        }
        else {
            //sinon on va appeler la methode pour modifier un employee
            savedEmployee = (Employee) employeeProxy.updateEmployee(employee);
        }
        return  savedEmployee;
    }


}
