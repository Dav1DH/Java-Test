import java.util.Set;

/**
 * 
 */

/**
 * @author David
 *
 */
public class Manager extends Employee {

	private Set<Employee> Employee;

	/**
	 * @return the employee
	 */
	public Set<Employee> getEmployee() {
		return Employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Set<Employee> employee) {
		Employee = employee;
	}


}
