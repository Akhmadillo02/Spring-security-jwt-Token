package uz.online.springsecurityfour.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.online.springsecurityfour.domein.Employee;
import uz.online.springsecurityfour.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }

    public List<Employee> findByName(String name){
        return employeeRepository.findByNameQueryNative(name);
    }


    public List<Employee> findAllByParam(String name){
        return employeeRepository.findAllNativeLike(name);
    }

    public void delete(Long id){
        Employee employee = employeeRepository.getOne(id);
        employeeRepository.delete(employee);
    }

    @Scheduled(cron = "0 45 23 * * *")
    public Employee saveSchedule(){
        Employee employee1 = new Employee();
        employee1.setName("ddddddddd");
        employee1.setLastName("ddddd");
        return employeeRepository.save(employee1);
    }



}