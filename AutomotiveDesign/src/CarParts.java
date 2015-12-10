import java.util.List;

/**
 * 
 */

/**
 * @author David
 *
 */
public class CarParts {
	
	private String warehouseAddress;
	
	private String name;
	
	private List<CarParts> CarParts;

	/**
	 * @return the carParts
	 */
	public List<CarParts> getCarParts() {
		return CarParts;
	}

	/**
	 * @param carParts the carParts to set
	 */
	public void setCarParts(List<CarParts> carParts) {
		CarParts = carParts;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the warehouseAddress
	 */
	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	/**
	 * @param warehouseAddress the warehouseAddress to set
	 */
	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

}
