package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.dao.DepartmentDao;
import ro.teamnet.zth.app.dao.EmployeeDao;
import ro.teamnet.zth.app.domain.Department;
import ro.teamnet.zth.app.domain.Employee;

import java.util.List;

/**
 * Created by Andrei on 5/7/2015.
 */
public class EmployeeServiceImpl implements EmployeeService{
    @Override
    public List<Employee> findAllEmployees() {
        EmployeeDao dao = new EmployeeDao();
        List<Employee> list = dao.getAllEmployees();
        return list;
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return new EmployeeDao().getEmployeeById(employeeId);
    }
}
