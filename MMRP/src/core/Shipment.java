package core;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

//Shipment class
//Lets try this again
//YEAH!!!! ITS WORKING!!!!!
public class Shipment extends BaseClass {

	private int fromLocationID,toLocationID;
	private int priority;
	private int size;
	private int weight;
	private int earliestArrival, latestArrival;
	private int id;
	private int earliestDeparture,latestDeparture;
	private ArrayList<ShipmentHistory> history;
	private int currentLocation;
	private int timeToLoad,timeToUnload;
	private int shipperID;
	private Boolean tollRoads;
	private Boolean congestionByPass;
	private int maxStops;
	//TODO:Need to added hazmat constraints to vehicles as well
	private String hazmatConstraints;
	
	//TODO: Might be better suited as a property of vehicles
	private int loadingRate;
	//TODO: I Have no clue where these should go just yet
	private String trailerType,loadingType,unloadingType;
	//TODO: prefCarriers should probably be a property of a shipper not a shipment
	private String prefCarriers;
	
	public Shipment()
	{
		MarkNew();
	}
	public Shipment(int id)
	{
		this.id=id;
		MarkOld();
	}
	public int getCurrentLocationID()
	{
		return currentLocation;
	}
	public void setCurrentLocation(int loc)
	{
		if(this.currentLocation!=loc)
		{
			currentLocation=loc;
			MarkDirty();
		}
	}
	public int getFromLocationID() {
		return fromLocationID;
	}
	public void setFromLocationID(int fromLocationID) {
		if(this.fromLocationID!=fromLocationID)
		{
			this.fromLocationID = fromLocationID;
			MarkDirty();
		}
	}
	public int getToLocationID() {
		return toLocationID;
	}
	public void setToLocationID(int toLocationID) {
		if(this.toLocationID != toLocationID)
		{
			this.toLocationID = toLocationID;
			MarkDirty();
		}
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		if(this.priority != priority)
		{
			this.priority = priority;
			MarkDirty();
		}
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		if(this.size!=size)
		{
			this.size = size;
			MarkDirty();
		}
	}
	public int getEarliestArrivalTime() {
		return earliestArrival;
	}
	public void setEarliestArrivalTime(int time) {
		if(this.earliestArrival!=time)
		{
			this.earliestArrival = time;
			MarkDirty();
		}
	}
	public int getLatestArrivalTime() {
		return latestArrival;
	}
	public void setLatestArrivalTime(int time) {
		if(this.latestArrival!=time)
		{
			this.latestArrival = time;
			MarkDirty();
		}
	}
	public int getEarliestDepartureTime() {
		return earliestDeparture;
	}
	public void setEarliestDepartureTime(int departureTime) {
		if(this.earliestDeparture!=departureTime)
		{
			this.earliestDeparture = departureTime;
			MarkDirty();
		}
	}
	public int getLatestDepartureTime()
	{
		return latestDeparture;
	}
	public void setLatestDepartureTime(int departureTime)
	{
		if(this.latestDeparture!=departureTime)
		{
			this.latestDeparture=departureTime;
			MarkDirty();
		}
	}
	public int getId() {
		return id;
	}

	public int getTimeToLoad()
	{
		return this.timeToLoad;
	}
	public void setTimeToLoad(int t)
	{
		if(this.timeToLoad != t)
		{
			this.timeToLoad=t;
			MarkDirty();
		}
	}
	public int getTimeToUnload()
	{
		return this.timeToUnload;
	}
	public void setTimeToUnload(int t)
	{
		if(this.timeToUnload!=t)
		{
			this.timeToUnload=t;
			MarkDirty();
		}
	}
	public void setShipperID(int id)
	{
		if(this.shipperID!=id)
		{
			this.shipperID=id;
			MarkDirty();
		}
	}
	public int getShipperID()
	{
		return this.shipperID;
	}
	
	public void setTollRoads(Boolean r)
	{
		if(this.tollRoads!=r)
		{
			tollRoads=r;
			MarkDirty();
		}
	}
	public Boolean getTollRoads()
	{
		return tollRoads;
	}
	public void setCongestionByPass(Boolean c)
	{
		if(this.congestionByPass!=c)
		{
			this.congestionByPass=c;
			MarkDirty();
		}
	}
	public Boolean getCongestionByPass()
	{
		return this.congestionByPass;
	}
	public void setHazmat(String s)
	{
		if(this.hazmatConstraints==null || !this.hazmatConstraints.equals(s))
		{
			this.hazmatConstraints=s;
			MarkDirty();
		}
	}
	public String getHazmat()
	{
		return this.hazmatConstraints;
	}
	public void setMaxStops(int s)
	{
		if(this.maxStops!=s)
		{
			maxStops=s;
			MarkDirty();
		}
	}
	public int getMaxStops()
	{
		return maxStops;
	}
	
	public int getLoadingRate()
	{
		return this.loadingRate;
	}
	public void setLoadingRate(int r)
	{
		if(this.loadingRate!=r)
		{
			this.loadingRate=r;
			MarkDirty();
		}
	}
	
	public String getUnloadType()
	{
		return this.unloadingType;
	}
	public void setUnloadType(String t)
	{
		if(this.unloadingType==null||!unloadingType.equals(t))
		{
			this.unloadingType=t;
			MarkDirty();
		}
	}
	public void setLoadingType(String t)
	{
		if(this.loadingType==null || !this.loadingType.equals(t))
		{
			this.loadingType=t;
			MarkDirty();
		}
	}
	public void setTrailerType(String t)
	{
		if(this.trailerType==null || !this.trailerType.equals(t))
		{
			this.trailerType=t;
			MarkDirty();
		}
	}
	public String getTrailerType()
	{
		return this.trailerType;
	}
	
	public void setPrefCarrier(String s)
	{
		if(this.prefCarriers==null || !this.prefCarriers.equals(s))
		{
			this.prefCarriers=s;
			MarkDirty();
		}
	}
	public String getPrefCarriers()
	{
		return this.prefCarriers;
	}
	public void setHistory(ArrayList<ShipmentHistory> hist)
	{
		history=hist;
		for(int i = 0; i<hist.size();i++)
		{
			
		}
	}
	public void setWeight(int w)
	{
		if(this.weight!=w)
		{
			this.weight=w;
			MarkDirty();
		}
	}
	public int getWeight()
	{
		return this.weight;
	}
	public void setHistoryFromSegments(ArrayList<Segment> hist)
	{
		history=new ArrayList<ShipmentHistory>();
		for(int i = 0; i<hist.size();i++)
		{
			ShipmentHistory temp = new ShipmentHistory();
			temp.setNodeNumber(i);
			temp.setSegmentID(hist.get(i).getID());
			temp.setShipmentID(this.id);
			temp.Update();
			history.add(temp);
		}
	}
	public ArrayList<ShipmentHistory> getHistory()
	{
		return history;
	}
	public Location loadStartLocation()
	{
		return Location.Load(fromLocationID);
	}
	public Location loadEndLocation()
	{
		return Location.Load(toLocationID);
	}
	public static Shipment Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Shipment where ShipmentID = " + id);
			if(temp.size()>0)
			{
				Shipment s = BuildFromDataRow(temp.get(0));
				s.setHistory(ShipmentHistory.LoadAllForShipment(id));
				return s;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}
	
	public static ArrayList<Shipment> LoadAll(String where)
	{
		ArrayList<Shipment> returnList = new ArrayList<Shipment>();
		try 
		{
			
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Shipment " +  where);
			for(int i = 0; i<temp.size();i++)
			{
				Shipment s =BuildFromDataRow(temp.get(i));
				s.setHistory(ShipmentHistory.LoadAllForShipment(s.getId()));
				returnList.add(s);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}
	
	public static ArrayList<Shipment> LoadAllForSegment(int segID)
	{
		ArrayList<Shipment> returnList = new ArrayList<Shipment>();
		try 
		{
			/*
			 * SELECT * FROM `multi-modal`.shipment where shipmentID in
(
	Select shipmentID from `multi-modal`.shipmentHistory where segmentID=104

);
			 */
			ArrayList<Map<String,Object>> temp =executeQuery("Select * from Shipment where shipmentID in ( Select shipmentID from shipmentHistory where segmentID = " +  segID+")");
			for(int i = 0; i<temp.size();i++)
			{
				Shipment s =BuildFromDataRow(temp.get(i));
				s.setHistory(ShipmentHistory.LoadAllForShipment(s.getId()));
				returnList.add(s);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}
	public static Shipment BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		Shipment s = new Shipment((Integer)data.get("ShipmentID"));
		s.setFromLocationID((Integer)data.get("FromLocationID"));
		s.setToLocationID((Integer)data.get("ToLocationID"));
		s.setPriority((Integer)data.get("Priority"));
		s.setEarliestDepartureTime((Integer)data.get("EarliestDepartureFromStart"));
		s.setLatestDepartureTime((Integer)data.get("LatestDepartureFromStart"));
		s.setEarliestArrivalTime((Integer)data.get("EarliestArrival"));
		s.setLatestArrivalTime((Integer)data.get("LatestArrival"));
		s.setSize((Integer)data.get("Size"));
		if((Integer)data.get("weight")!=null)
			s.setWeight((Integer)data.get("weight"));
		if((Integer)data.get("CurrentLocation")!=null)
			s.setCurrentLocation((Integer)data.get("CurrentLocation"));
		if((Integer)data.get("loadingTime")!=null)
			s.setTimeToLoad((Integer)data.get("loadingTime"));
		if((Integer)data.get("unloadingTime")!=null)
			s.setTimeToUnload((Integer)data.get("unloadingTime"));
		if((Integer)data.get("shipper")!=null)
			s.setShipperID((Integer)data.get("shipper"));
		s.setTollRoads((Boolean)data.get("takeTollRoads"));
		s.setCongestionByPass((Boolean)data.get("localCongestionByPass"));
		s.setTrailerType((String)data.get("trailerType"));
		s.setLoadingType((String)data.get("loadingType"));
		s.setUnloadType((String)data.get("unloadingType"));
		s.setHazmat((String)data.get("hazmatConstraints"));
		s.setPrefCarrier((String)data.get("prefCarriers"));
		if((Integer)data.get("maxStops")!=null)
			s.setMaxStops((Integer)data.get("maxStops"));
		s.MarkClean();
		return s;
		
	}
	@Override
	void Update() {
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the plane is new insert it into the database by executing the following
				executeCommand("Insert into Shipment (FromLocationID,ToLocationID,Priority,EarliestDepartureFromStart,LatestDepartureFromStart,EarliestArrival,LatestArrival,Size,weight,CurrentLocation,loadingTime"+
				",unloadingTime,loadingRate,shipper,takeTollRoads,localCongestionByPass,trailerType,loadingType,unloadingType,hazmatConstraints,prefCarriers,maxStops) Values ('"+
						this.fromLocationID+ "','" + this.toLocationID + "','"+ this.priority+"','"+this.earliestDeparture+"','"+this.latestDeparture+"','" + this.earliestArrival + "','" + this.latestArrival+ "','"+
						this.size+"','"+this.weight+"','"+this.currentLocation+this.timeToLoad+"','"+this.timeToUnload+"','"+this.loadingRate+"','"+this.shipperID+"','"+this.tollRoads+"','"+this.congestionByPass+
						"','"+this.trailerType+"','"+this.loadingType+"','"+this.unloadingType+"','"+this.hazmatConstraints+"','"+this.prefCarriers+"','"+this.maxStops+"')");
				//Grab this plane from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select ShipmentID from Shipment where "+
						"FromLocationID = '" + this.fromLocationID +"' "+
						"AND ToLocationID = '" + this.toLocationID +"' "+
						"AND Priority = '" + this.priority +"' "+
						"AND EarliestDepartureFromStart = '" + this.earliestDeparture + "' "+
						"AND LatestDepartureFromStart = '" + this.latestDeparture +"' " +
						"AND EarliestArrival = '" + this.earliestArrival +"' "+
						"AND LatestArrival = '" + this.latestArrival +"' "+
						"AND Size = '" + this.size + "' "+
						"AND weight = '" + this.weight +"' "+
						"AND CurrentLocation = '" + this.currentLocation +"' "+
						"AND loadingTime = '"+ this.timeToLoad +"' "+
						"AND unloadingTime = '" + this.timeToUnload + "' "+
						"AND loadingRate = '" + this.loadingRate +"' "+
						"AND shipper = '" + this.shipperID +"' "+
						"AND takeTollRoads = '" + this.tollRoads +"' "+
						"AND localCongestionByPass = '" + this.congestionByPass +"' "+
						"AND trailerType = '" + this.trailerType +"' "+
						"AND loadingType = '" + this.loadingType+"' "+
						"AND unloadingType = '" + this.unloadingType+"' "+
						"AND hazmatConstraints = '" + this.hazmatConstraints + "' "+
						"AND prefCarriers = '" + this.prefCarriers +"' "+
						"AND maxStops = '"+this.maxStops+"'");
						
				//If this plane exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("ShipmentID");								//Set this Plane's id to the database id
					MarkClean();																//Mark the Plane as clean
					MarkOld();																	//Mark the Plane as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Plane is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update Shipment Set "+
							"FromLocationID = '" + this.fromLocationID +"' "+
							", ToLocationID = '" + this.toLocationID +"' "+
							", Priority = '" + this.priority +"' "+
							", EarliestDepartureFromStart = '" + this.earliestDeparture + "' "+
							", LatestDepartureFromStart = '" + this.latestDeparture +"' " +
							", EarliestArrival = '" + this.earliestArrival +"' "+
							", LatestArrival = '" + this.latestArrival +"' "+
							", Size = '" + this.size + "' "+
							", weight = '" + this.weight +"' "+
							", CurrentLocation = '" + this.currentLocation +"' "+
							", loadingTime = '"+ this.timeToLoad +"' "+
							", unloadingTime = '" + this.timeToUnload + "' "+
							", loadingRate = '" + this.loadingRate +"' "+
							", shipper = '" + this.shipperID +"' "+
							", takeTollRoads = '" + this.tollRoads +"' "+
							", localCongestionByPass = '" + this.congestionByPass +"' "+
							", trailerType = '" + this.trailerType +"' "+
							", loadingType = '" + this.loadingType+"' "+
							", unloadingType = '" + this.unloadingType+"' "+
							", hazmatConstraints = '" + this.hazmatConstraints + "' "+
							", prefCarriers = '" + this.prefCarriers +"' "+
							", maxStops = '"+this.maxStops+"'"+
							" where ShipmentID = '" + this.id +"'");
							
					MarkClean();
				}//End of isDirty if
			}//End of isOld else
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);													//Print out the error
		}//End of catch block

	}

	@Override
	void Delete() {
		try
		{
			executeCommand("Delete from Shipment Where ShipmentID = " + this.id);					//Delete the plane
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);	
		}

	}
	public void DeleteAllHistory()
	{
		for(int i=0;i<history.size();i++)
		{
			history.get(i).Delete();
		}
	}

}
