package org.hrmanagement.controller;

import lombok.Getter;
import lombok.Setter;
import org.hrmanagement.models.Employee;
import org.hrmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Getter @Setter
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @GetMapping("/")
    public String home(Model model){
        Iterable<Employee> listEmployee;
        listEmployee = service.getAllEmployee();
        model.addAttribute("employees", listEmployee);
        return "home";
    }
    @GetMapping("/createEmployee")
    public String createEmployee(Model model){
        Employee e = new Employee();
        model.addAttribute("employee", e);
        return "formNewEmployee";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") final Long id, Model model){
        Employee employee = service.getEmployee(id);
        model.addAttribute("employee", employee);
        return "formUpdateEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public  ModelAndView deleteEmployee(@PathVariable("id") final  Long id){
        service.deleteEmploye(id.longValue());
        return new ModelAndView("redirect:/");
    }
    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee){
        if (employee.getId()!=null){

            // Employee from update form has the password field not filled,
            // so we fill it with the current password.
            Employee current = service.getEmployee(employee.getId());

            employee.setPassword(current.getPassword());

        }
        service.saveEmployee(employee);
        return new ModelAndView("redirect:/");
    }

}
