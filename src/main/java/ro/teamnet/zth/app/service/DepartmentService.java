package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.domain.Department;
import ro.teamnet.zth.app.domain.Employee;

import java.util.List;

/**
 * Created by Andrei on 5/7/2015.
 */
public interface DepartmentService {
    public List<Department> findAllDepartments();
    public Department getDepartment(int departmentId);
}
