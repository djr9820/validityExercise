package validityExercise;


/**
 * Basic class for contact information based on Validity Data Files
 * Assumes that at least a first name and last name is given
 * 
 * @author Dan Roche <roched1@wit.edu>
 */
public class ContactInfo {

	String [] contact;
	String fName;
	String lName;
	String phone;
	
	/**
	 * Basic constructor for contactInfo
	 * @param info String array to make contactInfo from
	 */
	public ContactInfo(String [] info) {
		this.contact = new String [info.length];
		for(int i = 0; i < info.length; i++) {
			this.contact[i] = info[i];
		}
		
		this.lName = this.contact[2];
		this.fName= this.contact[1];
		this.phone = this.contact[this.contact.length-1];
		
	}
	
	/**
	 * Gives the unique id associated with this contactInfo instance
	 * @return String unique id
	 */
	public String getID() { return this.contact[0]; }
	
	/**
	 * Overridden toString method to convert contactInfo to a String
	 * @return String conversion from String [] contact
	 */
	@Override
	public String toString() {
		
		//return this.contact[0] + ", " + this.fName + ", " + this.lName + ", " + this.phone;
		
		String s = "";
		for(int i = 1; i < this.contact.length-2; i++) {
			s += this.contact[i] + ", ";
		}
		return s + this.contact[this.contact.length-1];
		
	}
	/**
	 * Overridden method to compare two contactInfo's equality
	 * 
	 * @param o	Object to compare this instance to
	 * @return boolean whether they were equal
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(!(o instanceof ContactInfo))
			return false;
		
		ContactInfo newO = (ContactInfo) o;
		
		if(newO.getID().equals(this.getID()))	// use unique id to determine equality
			return true;
		
		return false;
	}
	
	
	
}
