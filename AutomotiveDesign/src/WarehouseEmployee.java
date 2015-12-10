/**
 * 
 */

/**
 * @author David
 *
 */
public class WarehouseEmployee extends Employee implements Assemble {

	private String warehouse;
	
	private String warehouseAddress;
	
	

	/**
	 * @return the warehouse
	 */
	public String getWarehouse() {
		return warehouse;
	}

	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
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

	@Override
	public void takeOut(CarParts Parts) {
		// TODO Auto-generated method stub
		
	}
}
