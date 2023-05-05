package org.hrmanagement.repositores;

import lombok.extern.slf4j.Slf4j;
import org.hrmanagement.CostumProperties;
import org.hrmanagement.models.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    public CostumProperties props;



    /*Get all employees //
    *return an iterable of all employees
     */
    public Iterable<Employee> getEmployees(){
        String  baseApiUrl = props.getApiUrlBase();
        String getEmployeesUrl = baseApiUrl + "/employees";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {
                }
        );
        log.debug("Get All Employees call", response.getStatusCode().toString());
        return response.getBody();
    }
    /**
     * Get an employee by the id
     * @param id The id of the employee
     * @return The employee which matches the id
     */
    public  Employee getEmployee(Long id){
        String baseApiUrl = props.getApiUrlBase();
        String getEmployeeUrl = baseApiUrl + "/employee/"+id;
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class
        );
        log.debug("Get an employee by the id call", response.getStatusCode());
                return response.getBody();
    }
    /**
     * Add a new employee
     * @param e A new employee (without an id)
     * @return The employee full filled (with an id)
     */

    public Employee createEmployee(Employee employee){

        String  baseApiUrl = props.getApiUrlBase();
        String createEmployeeUrl = baseApiUrl + "/employee";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(employee);
        ResponseEntity<Employee>  response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class);
        log.debug("Create Employee call", response.getStatusCode().toString());
        return response.getBody();
    }
    /**
     * Update an employee - using the PUT HTTP Method.
     * @param e Existing employee to update
     */

    public Employee updateEmployee( Employee e){
        String  baseApiUrl = props.getApiUrlBase();
        String updateEmployeeUrl = baseApiUrl + "/employee/"+e.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(e);
        ResponseEntity<Employee>  response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class);
        log.debug("Update Employee call", response.getStatusCode().toString());
        return response.getBody();
    }
    /*
     * Delete an employee using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * param  The employee to delete
     */
    public void deleteEmployee(@NotNull Long id){
        String  baseApiUrl = props.getApiUrlBase();
        String deleteEmployeeUrl = baseApiUrl + "/employee/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void>  response = restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null ,
                Void.class);
        //log.debug("Delete Employee call", response.getStatusCode().toString());

    }
}
