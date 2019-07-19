package com.practise.spring;

public class EmployeeServiceImpl implements EmployeeService {

	
	
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Override
	public boolean incrementSalary(int empno, int amount) {
		// TODO Auto-generated method stub
		
	int amount_test=employeeDAO.getSalaty(1234);
	amount_test=amount_test+500;
    employeeDAO.setSalay(1234, amount_test);
    System.out.println(amount_test);
	return true;
	
	
	}

		
 EmployeeDAO employeeDAO;



}
