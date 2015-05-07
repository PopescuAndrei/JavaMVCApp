package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.ParameterAnnotation;
import ro.teamnet.zth.app.domain.Employee;
import ro.teamnet.zth.app.service.EmployeeServiceImpl;

import java.util.List;

/**
 * Created by Andrei on 5/6/2015.
 */
@MyController(urlPath = "/employees")
public class EmployeesController {

    public EmployeesController() {
    }

    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public List<Employee> getAllEmployees() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        return employeeService.findAllEmployees();
    }

    @MyRequestMethod(urlPath = "/one",methodType = "GET")
    public Employee getOneEmployee( @ParameterAnnotation(parameterName = "employeeId")String employeeId){
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        return employeeService.getEmployee(Integer.parseInt(employeeId));
    }

}
