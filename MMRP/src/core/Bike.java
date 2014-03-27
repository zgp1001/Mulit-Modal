/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 */

package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Bike extends Vehicle {

	public static enum BikeType 
	{
		Standard("Standard"),
		Other("OTHER");
		private String type;
		//This contructor will allow the creation of new BikeTypes
		BikeType(String s)
		{
			type=s;
		}//End of BikeType constructor
		//This overridden toString function will return the enumeration as a string
		@Override public String toString()
		{
			return type;
		}//End of toString()
	
	}//End of BikeType enumeration
	
	private BikeType type;								//The type of the bike
	
	/**
	 * Default Constructor for Bike Class
	 * <p>
	 * Creates a new Vehicle of type Bike that is set to new
	 */
	public Bike()
	{
		setTravelType(TravelTypes.Bike);				//Set the TravelType to Bike
		MarkNew();										//Mark this as a new object
	}//End of Bike()
	
	//This is the arguemented Bike constructor to set the id of the Bike
	/**
	 * Constructor for Bike Class when object is loaded from Database
	 * @param id BikeId in database table
	 */
	public Bike(int id)
	{
		setTravelType(TravelTypes.Bike);				//Set the TravelType to Bike
		this.id=id;										//Set the bike id
						//SHOULD THIS ALSO BE MARKED AS NEW OR ATLEAST DIRTY?
	}//End of Bike(int id)
	
	/**
	 * Sets the name of the Bike
	 * @param s Name of the bike
	 */
	public void setBikeName(String s)
	{
		if(name==null || !this.name.equals(s))
		{
			super.setVehicleName(s);					//Set the name of the Bike (as a vehicle)
		}//End of valid name if
	
	}//End of setBikeName(String s)
	
	/**
	 * Returns the name of the Bike
	 * @return String name of the Bike
	 */
	public String getBikeName()
	{
		return super.getVehicleName();					//Return the name of the Bike (from vehicle)
	}//End of getBikeName()
	
	/**
	 * Sets the type of Bike
	 * @param s BikeType
	 * @see core.Bike.BikeType
	 */
	public void setBikeType(BikeType s)
	{
		if(type==null || !this.type.equals(s))
		{	
			type = s;									//Set the type of the Bike
			MarkDirty();								//Mark the bike as dirty
		}//End of valid BikeType if
	}//End of setBikeType(BikeType s)
	
	/**
	 * Sets the type of Bike
	 * @param s String representation of BikeType
	 */
	public void setBikeType(String s)
	{
		if(type==null || !this.type.toString().equals(s))
		{
			type = loadBikeType(s);						//Set the type of the Bike
			MarkDirty();								//Mark the Bike as dirty
		}//End of valid BikeType if
	}//End of setBikeType(String s)
	
	/**
	 * Returns the String representation of BikeType
	 * @return String representation of BikeType
	 */
	
	public String getBikeType()
	{
		return type.toString();							//Return the BikeType as a String
	}//End of getBiketype()
	
	/**
	 * Private method that loads the corresponding BikeType for a String
	 * @param s String representation of a BikeType
	 * @return BikeType
	 */
	private BikeType loadBikeType(String s)
	{
		if(s.equals(BikeType.Standard.toString()))
				return BikeType.Standard;					//Return Standard
		return BikeType.Other;							//Return Other
	
	}//End of loadBikeType(String s)
	
	/**
	 * Updates the database entry for this object.
	 * 
	 * If the object is new it will be inserted into the database.
	 * If the object is dirty, the database entry will be updated
	 */
	@Override
	public void Update() 
	{
		try
		{
		//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the bike is new insert it into the database by executing the following
				executeCommand("Insert into Bike (BikeName,Contractor,Longitude,Latitude,LocationName,BikeType,Capacity,Status) Values ('"+
				getBikeName() + "','" + getContractor() + "','"+ this.getLongitude()+"','"+this.getLatitude() + "','" + this.getLocationName() + "','" + this.getBikeType()+ "','"+
				this.getCapacity()+"','"+this.getStatus()+"')");
				
				//Grab this bike from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select BikeID from Bike where BikeName = '" + this.getBikeName() + "' AND Contractor = '"+this.getContractor()+
				"' AND Longitude = '" + this.getLongitude() + "' AND Latitude = '" + this.getLatitude() + "' AND LocationName = '" + this.getLocationName() + 
				"' AND BikeType = '" + this.getBikeType() + "' AND Capacity = '" +this.getCapacity() + "' AND Status = '" + this.getStatus()+"'");
				//If this bike exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("BikeID");				//Set this Bike's id
					MarkClean();												//Set the bike to clean
					MarkOld();													//Set the bike to old
				}//End of entry found if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Bike is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Bike Set BikeName = '" + this.getBikeName() + "' , Contractor = '"+this.getContractor()+
					"' , Longitude = '" + this.getLongitude() + "' , Latitude = '" + this.getLatitude() + "' , LocationName = '" + this.getLocationName() + 
					"' , BikeType = '" + this.getBikeType() + "' , Capacity = '" +this.getCapacity() + "' , Status = '" + this.getStatus() + "' Where BikeID = " +this.id);
					MarkClean();												//Now mark the bike as clean
				}//End of isDirty if
			}//End of isOld else
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);									//Print out the error
		}//End of catch block
	
	}//End of overridden Update()
	
	/**
	 * Deletes the bike from the database
	 */
	@Override
	public  void Delete() 
	{
		try
		{
			executeCommand("Delete from Bike Where BikeID = " + this.id);		//Delete this Bike from the database
		}//End of try block
		catch(Exception ex)
		{	
			System.out.println("Error " + ex);									//Print out the error
		}//End of catch block
	
	}//End of overridden Delete()
	/**
	 * Static method that loads a bike from the databse
	 * @param id BikeID
	 * @return Instance of Bike where BikedID=id
	 */
	public static Bike Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Bike where BikeID = " + id);
			if(temp.size()>0)
			{
				Bike b = BuildFromDataRow(temp.get(0));
				b.getSchedule();
				return b;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}
	/**
	 * Loads all the bikes from a database meeting conditions
	 * @param where Conditions to be meet for Bike
	 * @return ArrayList<Bike>
	 */
	public static ArrayList<Bike> LoadAll(String where)
	{
		ArrayList<Bike> returnList = new ArrayList<Bike>();
		try 
		{
			
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Bike " +  where);
			for(int i = 0; i<temp.size();i++)
			{
				Bike  b = BuildFromDataRow(temp.get(i));
				b.getSchedule();
				returnList.add(b);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}
	/**
	 * Builds a Bike object from the results obtained from a database query
	 * @param data Mapping from database query
	 * @return
	 * @throws SQLException
	 */
	public static Bike BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
	
		//This code grabs each element that will be found in the database on the Bikes table and set the appropriate values for a new Bike
		Bike b = new Bike((Integer)data.get("BikeID"));//rs.getInt("BikeID"));
		b.setBikeName((String)data.get("BikeName"));//rs.getString("BikeName"));
		b.setCapacity((Integer)data.get("Capacity"));//rs.getInt("Capacity"));
		b.setContractor((String)data.get("Contractor"));//rs.getString("Contractor"));
		b.setLocation(Double.parseDouble(data.get("Latitude").toString()),Double.parseDouble(data.get("Longitude").toString()),(String)data.get("LocationName"));
		b.setBikeType((String)data.get("BikeType"));//rs.getString("BikeType"));
		b.setStatus((String)data.get("Status"));//rs.getString("Status"));		
		b.MarkClean();
		return b;
		
	}//End of BuildFromDataRow(Map<String,Object> data)
	
	//This function overrides the toString function and returns the name of the Bike
	@Override
	public String toString()
	{
		return getBikeName();								//Return the name of the Bike
	}//End of the overridden toString()


}
