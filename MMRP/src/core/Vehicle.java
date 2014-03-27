/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 */
package core;
import java.util.ArrayList;


public abstract class Vehicle extends BaseClass {
	//This is an enumeration for the different TravelTypes that the shipment may use
	public static enum TravelTypes 
	{
			Truck ("TRUCK"),
			Rail("RAIL"),
			Cargo("CARGO"),
			Plane("PLANE"),
			Bike("BIKE");
			private String type;
			TravelTypes(String s)
			{
				type=s;
			}
			@Override public String toString()
			{
				return type;
			}

	}//End of TravelTypes enumeration
	
	//This is an enumeration for the different Contractors that a vehicle may belong to
	public static enum Contractors 
	{
			UPS("UPS"),
			DHL("DHL"),
			FedEX("FEDEX"),
			USPS("USPS");
			private String contractor;
			Contractors(String s)
			{
				contractor=s;
			}
			@Override public String toString()
			{
				return contractor;
			}
			
	}//End of Contractors enumeration
	
	//Modifier Variables For Each Carrier.  Tries to introduce real world differences between contractors
	public static double [] COST_MODIFIER_PLANE = {0.8,0.9,1.0,1.1,1.2};
	
	
	
	//This is an enumeration for the different states that a vehicle may find itself in
	public static enum Status
	{
		Running("RUNNING"),
		Delayed("DELAYED"),
		Disabled("DISABLED");
		private String status;
		Status(String s)
		{
			status=s;
		}
		@Override public String toString()
		{
			return status;
		}
		
	}//End of Status enumeration

	protected Contractors contractor;					//The contractor for the vehicle
	protected double longitude;							//The longitude of the vehicle
	protected double latitude;							//The latitude of the vehicle
	protected String name;								//The name of the vehicle
	protected String locationName;						//The name of the location the vehicle is currently at
	protected int capacity;								//The amount the vehicle can carry
	protected Status status;							//The current status of the vehicle
	protected TravelTypes type;							//The type of vehicle
	protected int id;									//The unique vehicle ID
	private ArrayList<Shipment> shipments;
	private ArrayList<Segment> schedule;
	
	/**
	 * Sets the travel type of the vehicle 
	 * @param t TravelType 
	 */
	protected void setTravelType(TravelTypes t)
	{
		if(type==null || !type.equals(t))				//Make sure we have a valid type
		{
			type=t;										//Set the type to t
			MarkDirty();								//Mark the vehicle as dirty
		}//End of if statement
	}//End of setTravelType(TravelType t)
	/**
	 * Sets the travel type of the vehicle from a string
	 * @param t Travel Type name
	 */
	protected void setTravelType(String t)
	{
		if(type==null || !type.toString().equals(t))	//Make sure we have a valid type
		{
			type = loadType(t);							//Set the type of the vehicle
			MarkDirty();								//Mark the vehicle as dirty
		}//End of if statement
	}//End of setTravelType(String t)
		/**
		* Converts string to a TravelType
		* @param t Travel Type name
		* @return TravelType 
		*/
		public static TravelTypes loadType(String t)
		{
			if(t.equals(TravelTypes.Bike.toString()))
				return TravelTypes.Bike;
			if(t.equals(TravelTypes.Cargo.toString()))
				return TravelTypes.Cargo;
			if(t.equals(TravelTypes.Plane.toString()))
				return TravelTypes.Plane;
			if(t.equals(TravelTypes.Rail.toString()))
				return TravelTypes.Rail;
			if(t.equals(TravelTypes.Truck.toString()))
				return TravelTypes.Truck;
			return null;
		}//End of loadType(String t)
		
		/**
		* This function returns the TravelType of the vehicle
		* @return The TravelType of the vehicle
		*/
		public String getTravelType()
		{
			return type.toString();
		}//End of getTravelType
		
		/**
		* This function sets the id of the vehicle
		* @param ID of the vehicle
		*/
		public void setId(int id)
		{
			this.id=id;											//Set the id
			MarkDirty();										//Mark the vehicle as dirty
		}//End of setId(int id)
		
		/**
		* This function returns the id of the vehicle
		* @return ID of the vehicle
		*/
		public int  getId()
		{
			return id;				
		}//End of getId()
		
		/**
		* This function sets the status of the vehicle
		* @param s Status of the vehicle
		*/
		public void setStatus(Status s)
		{
			if(status == null || status!=(s))
			{
				status=s;										//Set the status
				MarkDirty();									//Mark the vehicle as dirty
			}//End of the if statement
		}//End of setStatus(Status s)
		
		/**
		* This function sets the status of the vehicle based on a string
		* @param s string Status of the vehicle
		*/
		public void setStatus(String s)
		{
			if(status==null || !status.toString().equals(s))
			{
				status=loadStatus(s);								//Set the status of the vehicle
				MarkDirty();										//Mark the vehicle as dirty
			}
		}//End of the setStatus(String s) function
		
		//This function will take a string and return the appropriate status
		/**
		* This function returns a status based on the string passed
		* @param val String value for status
		* @return Status type 
		*/
		public static Status loadStatus(String val)
		{
			if(val.equals(Status.Delayed.toString()))
			{
				return Status.Delayed;
			}//End of if value == delayed
			else
			{
				if(val.equals(Status.Disabled.toString()))
				{
					return Status.Disabled;
				}//End of if value ==  disabled
				else
				{
					return Status.Running;
				}//End of value == running else
			}//End of value != delayed else
		}//End of loadStatus(String val)
		
		/**
		* This function returns the status of the vehicle
		* @return Status of the vehicle
		*/
		public String getStatus()
		{
			return status.toString();
		}//End of getStatus
		
		/**
		* This function sets the contractor of the vehicle
		* @param c Contractor of the vehicle
		*/
		public void setContractor(Contractors c)
		{
			if(contractor==null || !contractor.equals(c))				//Make sure it is a valid contractor
			{
				contractor=c;											//Set the contractor
				MarkDirty();											//Mark the vehicle as dirty
			}//End of valid contractor if
		}//End of setContractor(Contractor c)
		
		/**
		* This function sets the status of the vehicle from a string
		* @param s string Contractor of the vehicle
		*/
		public void setContractor(String c)
		{
			if(contractor==null || !contractor.toString().equals(c))	//Make sure it is a valid contractor
			{
				contractor=loadContractor(c);							//Set the contractor for the vehicle
				MarkDirty();											//Mark the vehicle as dirty
			}//End of valid contractor if
		}//End of setContractor(String c)
		
		/**
		* This function converts a string to a Contractor type 
		* @param val string Contractor of the vehicle
		* @return Contractor type
		*/
		public static Contractors loadContractor(String val)
		{
			if(val.equals(Contractors.DHL.toString()))
			{
				return Contractors.DHL;
			}//End of DHL if
			else
			{
				if(val.equals(Contractors.FedEX.toString()))
				{
					return Contractors.FedEX;
				}//End of FedEx if
				else
				{
					if(val.equals(Contractors.UPS.toString()))
					{
						return Contractors.UPS;
					}//End of UPS if
					else
						return Contractors.USPS;
				}//End of !FEdEx else
			}//End of !DHL else
		}//End of loadContractor(String val)
		
		/**
		* This function returns the Contractor of the vehicle
		* @return Contractor of the vehicle
		*/
		public String getContractor()
		{
			return contractor.toString();
		}//End of getContractor()
		
		/**
		* This function sets the location (latitude and longitude) and the name of the vehicle
		* @param lat Latitude, lon Longitude, name Name of Location
		*/
		public void setLocation(double lat, double lon, String name)
		{
			if(this.latitude!=lat || this.longitude!=lon || this.locationName==null ||!this.locationName.equals(name))
			{
				latitude=lat;								//Set the latitude
				longitude=lon;								//Set the longitude
				locationName=name;							//Set the locationName
				MarkDirty();								//Mark the vehicle as dirty
			}//End of valid location if
		}//End of the setLocation(double lat, double lon, String name)
		
		/**
		* This function sets the location of the vehicle
		* @param lat Latitude, lon Longitude 
		*/
		public void setLocation(double lat, double lon)
		{
			if(this.latitude!=lat || this.longitude!=lon)
			{
				latitude=lat;								//Set the latitude
				longitude=lon;								//Set the longitude
				locationName="";							//Set the locationName to en empty string
				MarkDirty();								//Mark the vehicle as dirty
			}//End of valid location input if
		}//End of setLocation(double lat, double lon)
		
		/**
		* This function sets the longitude of the vehicle
		* @param lon Longitude of the vehicle
		*/
		public void setLongitude(double lon)
		{
			if(this.longitude!=lon)
			{
				longitude=lon;								//Set the longitude
				MarkDirty();								//Mark the vehicle as dirty
			}//End of update longitude if
		}//End of the setLongitude(double lon)
		
		/**
		* This function sets the Latitude of the vehicle
		* @param lat Latitude of the vehicle
		*/
		public void setLatitude(double lat)
		{
			if(this.latitude!=lat)
			{
				this.latitude=lat;							//Set the latitude
				MarkDirty();								//Mark the vehicle as dirty
			}//End of update latitude if
		}//End of setLatitude(double lat)
		
		/**
		* This function returns the Longitude of the vehicle
		* @return Longitude of the vehicle
		*/
		public double getLongitude()
		{
			return longitude;								//Return the longitude
		}//End of getLongitude()
		
		/**
		* This function returns the Latitude of the vehicle
		* @return Latitude of the vehicle
		*/
		public double getLatitude()
		{
			return latitude;								//Return the latitude
		}//End of getLatitude()
		
		/**
		* This function sets the location name of the vehicle
		* @param name String name of the location
		*/
		public void setLocationName(String name)
		{
			if(this.locationName==null || !this.locationName.equals(name))
			{
				this.locationName=name;						//Set the locationName
				MarkDirty();								//Mark the vehicle as dirty
			}//End of valid locationName if
		}//End of setLocationName(String name)
		
		/**
		* This function returns the Longitude of the vehicle
		* @return String name of the location of the vehicle
		*/
		public String getLocationName()
		{
			return locationName;							//Return the locationName
		}//End of getLocationName
		
		/**
		* This function sets the vehicle's name 
		* @param name String name of the vehicle
		*/
		protected void setVehicleName(String name)
		{
			if(this.name==null || !this.name.equals(name) )
			{
				this.name=name;								//Set the vehicle name
				MarkDirty();								//Mark the vehicle as dirty
			}//End of valid name if
		}//End of setVehicleName(String name)
		
		/**
		* This function returns the name of the vehicle
		* @return name of the vehicle
		*/
		protected String getVehicleName()
		{
			return name;									//Return the name
		}//End of getVehicleName()
		
		/**
		* This function sets the capacity of the vehicle
		* @param Capacity of the vehicle
		*/
		public void setCapacity(int capac)
		{
			if(capacity!=capac)
			{
				capacity=capac;								//Set the capacity
				MarkDirty();								//Mark the vehicle as dirty
			}//End of update capacity if
		}//End of setCapacity(int capac)
		
		/**
		* This function returns the capacity of the vehicle
		* @return Capacity of the vehicle
		*/
		public int getCapacity()
		{
			return capacity;								//Return the capacity
		}//End of getCapacity()
		
		//Abtract Functions
		abstract void Update();
		abstract void Delete();
	
	public ArrayList<Segment> getSchedule()
	{
		if(schedule==null)
			schedule=Segment.LoadAll("where ModeType = '"+type.toString()+"' and VehicleID= " + id);
		return schedule;
	}

	/**
	* This function adds a segment to the trucks schedule
	* @param s Segment to be added to schedule
	*/
	public void addToSchedule(Segment s)
	{
		schedule.add(s);
		s.Update();
	}
	
	/**
	* This function loads the truck at the Location indicated 
	* @param L Location to be loaded at
	*/
	public void LoadAtLocation(Location L)
	{
		if(shipments==null)
		{
			shipments=new ArrayList<Shipment>();
		}
		//load all shipments to be loaded on to the vehicle at said location
	}
	
	/**
	* This function unloads the truck at the Location indicated 
	* @param L Location to be unloaded at
	*/
	public void UnLoadAtLocation(Location l)
	{
		if(shipments==null || shipments.size()==0)
		{
			return;
		}
		//remove all shipments that get off at this location
	}
	
	/**
	* This function loads a shipment into the truck 
	* @param s Shipment to be loaded 
	*/
	public void LoadShipment(Shipment s)
	{
		if(shipments==null)
		{
			shipments=new ArrayList<Shipment>();
		}
		//todo Check capacity restraints... remove lower priorty shipment if necessary
		shipments.add(s);
	}
	/**
	* This function removes a shipment from the truck 
	* @param s Shipment to be removed
	*/
	public void RemoveShipment(Shipment s)
	{
		shipments.remove(s);
	}

}
