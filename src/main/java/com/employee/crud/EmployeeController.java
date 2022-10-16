package com.employee.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
  @Autowired
  EmployeeRepository _employeeRepository;

  @GetMapping("/get-all")
  public List<EmployeeEntity> getAll() {
    return _employeeRepository.findAll();
  }

  @GetMapping("/get/{id}")
  public EmployeeEntity getById(@PathVariable(value = "id") Integer empId) {
    return _employeeRepository.findById(empId).get();
  }

  @PostMapping("/create")
  public EmployeeEntity create(@RequestBody EmployeeEntity emp) {
    return _employeeRepository.save(emp);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<EmployeeEntity> update(@PathVariable(value = "id") Integer empId,
      @RequestBody EmployeeEntity updateEmp) {
    EmployeeEntity savedEmployee = _employeeRepository.getReferenceById(empId);
    savedEmployee.setEmailId(updateEmp.getEmailId());
    savedEmployee.setName(updateEmp.getName());
    savedEmployee.setLocation(updateEmp.getLocation());
    final EmployeeEntity updatedEmployee = _employeeRepository.save(savedEmployee);
    return ResponseEntity.ok(updatedEmployee);
  }

  @DeleteMapping("/delete/{id}")
  public String delete(@PathVariable(value = "id") Integer empId) {
    _employeeRepository.deleteById(empId);
    return "DELETED";
  }
}
