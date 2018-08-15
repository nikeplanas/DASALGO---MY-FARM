package MyFarm.Model;

/**	This abstract class is a representation of the Item model.
 *	@author Ma. Patricia Y. Villaroman
 *	@author Nicole Danielle Planas
 *	@version 1.0
 */

public abstract class Item {
	private String name;
	private String description;

	/**
	 * This constructor instantiates Item
	 */
	public Item () {

	}

	/**
	 * This method is the getter for description
	 * @return the description of the Item
	 */
	public String getDescription () {
		return description;
	}

	/**
	 * This method is the getter for name
	 * @return the name of the Item
	 */
	public String getName () {
		return name;
	}

	/**
	 * This method is the setter for name
	 * @param name where the name is set
	 */
	public void setName (String name) {
		this.name = name;
	}

	/**
	 * This method is the setter for description
	 * @param description where the description is set
	 */
	public void setDescription (String description) {
		this.description = description;
	}
}
