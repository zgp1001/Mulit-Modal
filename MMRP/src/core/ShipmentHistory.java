package core;
import java.util.ArrayList;
import java.util.Map;


public class ShipmentHistory extends BaseClass {

	private int id;
	private int segmentID;
	private int shipmentID;
	private int nodeNumber;
	
	public ShipmentHistory()
	{
		MarkNew();
	}
	public ShipmentHistory(int id)
	{
		this.id=id;
		MarkOld();
	}
	
	public void setSegmentID(int id)
	{
		if(this.segmentID!=id)
		{
			segmentID=id;
			MarkDirty();
		}
	}
	public int getSegmentID()
	{
		return segmentID;
	}
	public Segment getSegment()
	{
		return Segment.Load(this.segmentID);
	}
	
	public void setShipmentID(int id)
	{
		if(this.shipmentID!=id)
		{
			shipmentID=id;
			MarkDirty();
		}
	}
	public int getShipmentID()
	{
		return this.shipmentID;
	}
	public Shipment getShipment()
	{
		return Shipment.Load(this.shipmentID);
	}
	public void setNodeNumber(int i)
	{
		if(this.nodeNumber!=i)
		{
			nodeNumber=i;
			MarkDirty();
		}
	}
	public int getNodeNumber()
	{
		return nodeNumber;
	}
	
	public static ArrayList<ShipmentHistory> LoadAllForShipment(int id)
	{
		ArrayList<ShipmentHistory> returnList = new ArrayList<ShipmentHistory>();
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from ShipmentHistory where ShipmentID = " + id + " order by NodeNumber");
			for(int i = 0;i<temp.size();i++)
			{
				returnList.add(BuildFromDataRow(temp.get(i)));
			}
			return returnList;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return null;
	}
	public static ShipmentHistory BuildFromDataRow(Map<String,Object> data)
	{
		ShipmentHistory sh = new ShipmentHistory(Integer.parseInt(data.get("ShipmentHistoryID").toString()));
		sh.setSegmentID(Integer.parseInt(data.get("SegmentID").toString()));
		sh.setShipmentID((Integer)data.get("ShipmentID"));
		sh.setNodeNumber((Integer)data.get("NodeNumber"));
		sh.MarkClean();
		return sh;
	}
	@Override
	void Update() {
		try
		{
			//toDo: set id on insert set update statement
			if(isNew())
			{
				//If the plane is new insert it into the database by executing the following
				executeCommand("Insert into ShipmentHistory (SegmentID,ShipmentID,NodeNumber) Values ('"+
						this.segmentID+"','"+this.shipmentID+"','"+this.nodeNumber+"')");
				//Grab this plane from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select ShipmentHistoryID from ShipmentHistory where SegmentID = '" + this.segmentID + "' AND ShipmentID = '"+this.shipmentID+
						"' AND NodeNumber = '" + this.nodeNumber+ "'");
				//If this plane exists on the database mark it as old and clean
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("ShipmentHistoryID");								//Set this Plane's id to the database id
					MarkClean();																//Mark the Plane as clean
					MarkOld();																	//Mark the Plane as old
				}//End of found something if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
					//If the Plane is not new, but is dirty then it needs to be updated by the following SQL command
					executeCommand("Update ShipmentHistory Set SegmentID = '" + this.segmentID + "' , ShipmentID = '"+this.shipmentID+
						"' , NodeNumber = '" + this.nodeNumber + "' where ShipmentHistorID ='"+this.id+"'");
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
			executeCommand("Delete from ShipmentHistory where ShipmentHistoryID = '"+this.id+"'");
		}
		catch(Exception ex)
		{
			System.out.println("Error "+ex);
		}

	}

}
