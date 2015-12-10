import java.util.List;

/**
 * 
 */

/**
 * @author David
 *
 */
public class WareHouse {

	private String warehouseAddress;
	
	private List<Car> Cars;
	
	private List<CarParts> CarParts;

	
	/**
	 * @return the cars
	 */
	public List<Car> getCars() {
		return Cars;
	}

	/**
	 * @param cars the cars to set
	 */
	public void setCars(List<Car> cars) {
		Cars = cars;
	}

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
