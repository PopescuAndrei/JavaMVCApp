package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.ParameterAnnotation;
import ro.teamnet.zth.app.domain.Department;
import ro.teamnet.zth.app.service.DepartmentServiceImpl;

import java.util.List;

/**
 * Created by Andrei on 5/6/2015.
 */
@MyController(urlPath = "/departments")
public class DepartmentsController {

    @MyRequestMethod(urlPath = "/all",methodType = "GET")
    public List<Department> getAllDepartments() {
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.findAllDepartments();
    }

    @MyRequestMethod(urlPath = "/one",methodType = "GET")
    public Department getOneDepartment( @ParameterAnnotation(parameterName = "departmentId") String departmentId) {
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.getDepartment(Integer.parseInt(departmentId));
    }
}
