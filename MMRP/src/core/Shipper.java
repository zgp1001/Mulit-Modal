package core;

import java.util.ArrayList;
import java.util.Map;

public class Shipper extends BaseClass {
	
	private int _id;
	private int _locationID;
	private String _prefCarriers;
	private String _companyName;
	private String _contactName;
	private String _phone;
	private String _email;
	
	public Shipper()
	{
		MarkNew();
	}
	public Shipper(int id)
	{
		this._id=id;
		MarkOld();
	}
	//Setters & getters
	public int id()
	{
		return _id;
	}
	public void LocationID(int id)
	{
		if(this._locationID!=id)
		{
			this._locationID=id;
			MarkDirty();
		}
	}
	public int LocationID()
	{
		return this._locationID;
	}
	public void PrefferedCarriers(String s)
	{
		if(this._prefCarriers==null || !this._prefCarriers.equals(s))
		{
			this._prefCarriers=s;
			MarkDirty();
		}
	}
	public String PrefferedCarriers()
	{
		return this._prefCarriers;
	}
	
	public void CompanyName(String s)
	{
		if(this._companyName==null || !this._companyName.equals(s))
		{
			this._companyName=s;
			MarkDirty();
		}
	}
	
	public String CompanyName()
	{
		return this._companyName;
	}
	
	public void ContactName(String s)
	{
		if(this._contactName==null || !this._contactName.equals(s))
		{
			this._contactName=s;
			MarkDirty();
		}
	}
	
	public String ContactName()
	{
		return this._contactName;
	}
	public void Phone(String s)
	{
		if(this._phone==null|| !this._phone.equals(s))
		{
			this._phone=s;
			MarkDirty();
		}
	}
	public String Phone()
	{
		return this._phone;
	}
	
	public void Email(String s)
	{
		if(this._email==null || ! this._email.equals(s))
		{
			this._email=s;
			MarkDirty();
		}
	}
	public String Email()
	{
		return this.Email();
	}
	
	public static Shipper Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * From Shipper where ShipperID = '" + id+"'");
			if(temp.size()>0)
				return BuildFromDataRow(temp.get(0));
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			return null;
		}
	}
	
	public static ArrayList<Shipper> LoadAll(String where)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Shipper "+where);
			ArrayList<Shipper> returnList=new ArrayList<Shipper>();
			for(int i = 0;i<temp.size();i++ )
			{
				returnList.add(BuildFromDataRow(temp.get(i)));
			}
			return returnList;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
			return null;
		}
	}
	public static Shipper BuildFromDataRow(Map<String,Object> data)
	{
		Shipper s = new Shipper((Integer)data.get("ShipperID"));
		s.LocationID((Integer)data.get("locationID"));
		s.PrefferedCarriers((String)data.get("prefCarriers"));
		s.CompanyName((String)data.get("CompanyName"));
		s.ContactName((String)data.get("ContactName"));
		s.Phone((String)data.get("phone"));
		s.Email((String)data.get("email"));
		s.MarkClean();
		return s;
	}
	@Override
	void Update() {
		if(isNew())
		{
			//If the Segment is new insert it into the database by executing the following
			try
			{
				executeCommand("Insert into Shipper (locationID,prefCarriers,CompanyName,ContactName,phone,email) Values ('"+
						this._locationID+"','"+this._prefCarriers+"','"+this._companyName+"','"+this._contactName+"','"+this._phone+"','"+
						this._email+"')");
				//Grab this Segment from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select ShipperID from Shipper where locationID ='"+ this._locationID+"' "+
						"AND prefCarriers ='" + this._prefCarriers +"' "+
						"AND CompanyName ='" + this._companyName+"' "+
						"And ContactName='" + this._contactName+"' "+
						"And phone='"+this._phone+"' "+
						"And email ='"+this._email+"'");
						
				//If this Segment exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this._id = (Integer)temp.get(0).get("ShipperID");				//Set the Segment id to the id from the database
					MarkClean();													//Mark the Segment as clean
					MarkOld();														//Mark the Segment as old
				}//End of found something if
			}
			catch(Exception ex)
			{
				System.out.println("Error " +ex);
			}
			
		}//End of isNew if
		else
		{
			if(isDirty())
			{
				try
				{
				executeCommand("Update Shipper Set locationID ='"+ this._locationID+"' "+
						"AND prefCarriers ='" + this._prefCarriers +"' "+
						"AND CompanyName ='" + this._companyName+"' "+
						"And ContactName='" + this._contactName+"' "+
						"And phone='"+this._phone+"' "+
						"And email ='"+this._email+"' "+
						"Where ShipperID = '" +this._id +"'");
				
				
				//If the Segment is not new, but is dirty then it needs to be updated by the following SQL command
		
				MarkClean();													//Mark the Segment as clean
				}
				catch(Exception ex)
				{
					System.out.println("Error " +ex);
				}
			}//End of isDirty if
		}//End of isOld else
	}//End of try block
	

	/* (non-Javadoc)
	 * @see core.BaseClass#Delete()
	 */
	@Override
	void Delete() {
		try
		{
			executeCommand("Delete from Shipper where ShipperID = '" + this._id+"'");
		}
		catch(Exception ex)
		{
			System.out.println("Error " +ex);
		}

	}

}
