/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 */
package core;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Carrier extends BaseClass 
{
	private int id;
	private String carrierCode;
	private String carrierName;
	private Boolean sendByFax;
	private Boolean sendByEmail;
	private String areaCode;
	private String faxNumber;
	private String emailAddress;
	private int safetyRating;
	private String safetyRateDate;
	private int authorize;
	private String contractDate;
	private String insEndDate;
	private int costModifierTruck;
	private int costModifierBike;
	private int costModifierCargoShip;
	private int costModifierRail;
	private int costModifierPlane;
	
	/**
	 * Default constructor for a Carrier
	 */
	public Carrier()
	{
		MarkNew();
	}
	/**
	 * Constructor for Carrier Class when object is loaded from Database
	 * @param id CarrierId in database table
	 */
	public Carrier(int id)
	{
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
		MarkDirty();
	}
	/**
	 * @return the carrierCode
	 */
	public String getCarrierCode() {
		return carrierCode;
	}
	/**
	 * @param carrierCode the carrierCode to set
	 */
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
		MarkDirty();
	}
	/**
	 * @return the carrierName
	 */
	public String getCarrierName() {
		return carrierName;
	}
	/**
	 * @param carrierName the carrierName to set
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
		MarkDirty();
	}
	/**
	 * @return the sendByFax
	 */
	public Boolean isSendByFax() {
		return sendByFax;
	}
	/**
	 * @param sendByFax the sendByFax to set
	 */
	public void setSendByFax(Boolean sendByFax) {
		this.sendByFax = sendByFax;
		MarkDirty();
	}
	/**
	 * @return the sendByEmail
	 */
	public Boolean isSendByEmail() {
		return sendByEmail;
	}
	/**
	 * @param sendByEmail the sendByEmail to set
	 */
	public void setSendByEmail(Boolean sendByEmail) {
		this.sendByEmail = sendByEmail;
		MarkDirty();
	}
	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
		MarkDirty();
	}
	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}
	/**
	 * @param faxNumber the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
		MarkDirty();
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		MarkDirty();
	}
	/**
	 * @return the safetyRating
	 */
	public int getSafetyRating() {
		return safetyRating;
	}
	/**
	 * @param safetyRating the safetyRating to set
	 */
	public void setSafetyRating(int safetyRating) {
		this.safetyRating = safetyRating;
		MarkDirty();
	}
	/**
	 * @return the authorize
	 */
	public int getAuthorize() {
		return authorize;
	}
	/**
	 * @param authorize the authorize to set
	 */
	public void setAuthorize(int authorize) {
		this.authorize = authorize;
		MarkDirty();
	}
	/**
	 * @return the insEndDate
	 */
	public String getInsEndDate() {
		return insEndDate;
	}
	/**
	 * @param insEndDate the insEndDate to set
	 */
	public void setInsEndDate(String insEndDate) {
		this.insEndDate = insEndDate;
		MarkDirty();
	}
	/**
	 * @return the costModifierCargoShip
	 */
	public int getCostModifierCargoShip() {
		return costModifierCargoShip;
	}
	/**
	 * @param costModifierCargoShip the costModifierCargoShip to set
	 */
	public void setCostModifierCargoShip(int costModifierCargoShip) {
		this.costModifierCargoShip = costModifierCargoShip;
	}
	/**
	 * @return the costModifierTruck
	 */
	public int getCostModifierTruck() {
		return costModifierTruck;
	}
	/**
	 * @param costModifierTruck the costModifierTruck to set
	 */
	public void setCostModifierTruck(int costModifierTruck) {
		this.costModifierTruck = costModifierTruck;
	}
	/**
	 * @return the costModifierBike
	 */
	public int getCostModifierBike() {
		return costModifierBike;
	}
	/**
	 * @param costModifierBike the costModifierBike to set
	 */
	public void setCostModifierBike(int costModifierBike) {
		this.costModifierBike = costModifierBike;
	}
	/**
	 * @return the costModifierRail
	 */
	public int getCostModifierRail() {
		return costModifierRail;
	}
	/**
	 * @param costModifierRail the costModifierRail to set
	 */
	public void setCostModifierRail(int costModifierRail) {
		this.costModifierRail = costModifierRail;
	}
	/**
	 * @return the costModifierPlane
	 */
	public int getCostModifierPlane() {
		return costModifierPlane;
	}
	/**
	 * @param costModifierPlane the costModifierPlane to set
	 */
	public void setCostModifierPlane(int costModifierPlane) {
		this.costModifierPlane = costModifierPlane;
	}
	/**
	 * @return the safetyRateDate
	 */
	public String getSafetyRateDate() {
		return safetyRateDate;
	}
	/**
	 * @param safetyRateDate the safetyRateDate to set
	 */
	public void setSafetyRateDate(String safetyRateDate) {
		this.safetyRateDate = safetyRateDate;
	}
	/**
	 * @return the contractDate
	 */
	public String getContractDate() {
		return contractDate;
	}
	/**
	 * @param contractDate the contractDate to set
	 */
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
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
				//If the Carrier is new insert it into the database by executing the following
				executeCommand("Insert into Carriers (CarrierCode,CarrierName,CostModifierTruck,CostModifierBike" +
				",CostModifierCargoShip,CostModifierRail,CostModifierPlane,SendByFax,SendByEmail,AreaCode,FaxNumber,EmailAddress" +
				"SafetyRating,SafetyRateDate,Authorize,ContractDate,InsEndDate) Values ('"+ getCarrierCode() + "','" + getCarrierName()+
				"','" +getCostModifierTruck()+"','"+ getCostModifierBike()+"','"+getCostModifierCargoShip()+
				"','"+ getCostModifierRail()+"','"+ getCostModifierPlane()+"','"+isSendByFax()+"','"+isSendByEmail() +
				"','" + getAreaCode() + "','" + getFaxNumber()+ "','"+ getEmailAddress()+"','"+ getSafetyRating()+"','"+getSafetyRateDate()+"','"+getAuthorize()+
				"','"+getContractDate()+"','"+getInsEndDate()+"')");
				
				//Grab this Carrier from the database
				ArrayList<Map<String,Object>> temp =executeQuery("Select CarrierID from Carrier where CarrierCode = '" + this.getCarrierCode() + "' AND CarrierName = '"+this.getCarrierName()+
				"' AND CostModifierTruck = '" + this.getCostModifierTruck() + "' AND CostModifierBike = '" + this.getCostModifierBike() + "' AND CostModifierCargoShip = '" + this.getCostModifierCargoShip() +
				"' AND CostModifierRail = '" + this.getCostModifierRail() + "' AND CostModifierPlane = '" + this.getCostModifierPlane() +
				"' AND SendByFax = '" + this.isSendByFax() + "' AND SendByEmail = '" + this.isSendByEmail() + "' AND AreaCode = '" + this.getAreaCode() + 
				"' AND FaxNumber = '" + this.getFaxNumber() + "' AND EmailAddress = '" +this.getEmailAddress() + "' AND SafetyRating = '" + this.getSafetyRating()+
				"' AND SafetyRateDate ='"+ this.getSafetyRateDate() + "' AND Authorize = '" + this.getAuthorize() + "' AND ContractDate = '"+ this.getContractDate()+
				"' AND InsEndDate = '"+ this.getInsEndDate()+"'");
				if(temp.size()>0)
				{
					this.id = (Integer)temp.get(0).get("CarrierId");				
					MarkClean();												
					MarkOld();													
				}//End of entry found if
			}//End of isNew if
			else
			{
				if(isDirty())
				{
				executeQuery("Update Carrier set CarrierCode = '" + this.getCarrierCode() + "' , CarrierName = '"+this.getCarrierName()+
				"' , CostModifierTruck = '" + this.getCostModifierTruck() + "' , CostModifierBike = '" + this.getCostModifierBike() +"' , CostModifierCargoShip = '" + this.getCostModifierCargoShip() +
				"' , CostModifierRail = '" + this.getCostModifierRail() + "' , CostModifierPlane = '" + this.getCostModifierPlane() +
				"' , SendByFax = '" + this.isSendByFax() + "' , SendByEmail = '" + this.isSendByEmail() + "' , AreaCode = '" + this.getAreaCode() + 
				"' , FaxNumber = '" + this.getFaxNumber() + "' , EmailAddress = '" +this.getEmailAddress() + "' , SafetyRating = '" + this.getSafetyRating()+"'"+
				"' , SafetyRateDate = '"+ this.getSafetyRateDate() + " , Authorize = '" + this.getAuthorize() + 
				"', ContractDate = '"+ this.getContractDate() + "' , InsEndDate = '"+ this.getInsEndDate()+"'");
				MarkClean();												
				}//End of isDirty if
			}//End of isOld else
		}//End of try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);									//Print out the error
		}//End of catch block
	
	}//End of overridden Update()
	
	@Override
	void Delete() {
		try
		{
			executeCommand("Delete from Carriers Where CarrierID = " + this.id);					
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);	
		}

	}
	public static Carrier Load(int id)
	{
		try
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Carriers where CarrierID = " + id);
			if(temp.size()>0)
			{
				Carrier c = BuildFromDataRow(temp.get(0));
				return c;
			}
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
 		return null;
	}
	public static ArrayList<Carrier> LoadAll(String where)
	{
		ArrayList<Carrier> returnList = new ArrayList<Carrier>();
		try 
		{
			ArrayList<Map<String,Object>> temp = executeQuery("Select * from Carrier " +  where);
			for(int i = 0 ; i<temp.size();i++)
			{
				Carrier c = BuildFromDataRow(temp.get(i));
				returnList.add(c);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
		return returnList;
	}
	public static Carrier BuildFromDataRow(Map<String,Object> data) throws SQLException
	{
		Carrier c = new Carrier((Integer)data.get("CarrierID"));
		if((String)data.get("CarrierCode") != null)
		{
			c.setCarrierCode((String)data.get("CarrierCode"));
		}
		if((String)data.get("CarrierName") != null)
		{
			c.setCarrierCode((String)data.get("CarrierName"));
		}
		if((Integer)data.get("CostModifierTruck") != null)
		{
			c.setCostModifierTruck((Integer)data.get("CostModifierTruck"));
		}
		if((Integer)data.get("CostModifierBike") != null)
		{
			c.setCostModifierBike((Integer)data.get("CostModifierBike"));
		}
		if((Integer)data.get("CostModifierCargoShip") != null)
		{
			c.setCostModifierCargoShip((Integer)data.get("CostModifierCargoShip"));
		}
		if((Integer)data.get("CostModifierRail") != null)
		{
			c.setCostModifierRail((Integer)data.get("CostModifierRail"));
		}
		if((Integer)data.get("CostModifierPlane") != null)
		{
			c.setCostModifierPlane((Integer)data.get("CostModifierPlane"));
		}
		if((Boolean)data.get("SendByFax") != null)
		{
			c.setSendByFax((Boolean)data.get("SendByFax"));
		}
		if((Boolean)data.get("SendByEmail") != null)
		{
			c.setSendByEmail((Boolean)data.get("SendByEmail"));
		}
		if((String)data.get("AreaCode") != null)
		{
			c.setAreaCode((String)data.get("AreaCode"));
		}
		if((String)data.get("FaxNumber") != null)
		{
			c.setFaxNumber((String)data.get("FaxNumber"));
		}
		if((String)data.get("EmailAddress") != null)
		{
			c.setEmailAddress((String)data.get("EmailAddress"));
		}
		if((Integer)data.get("SafetyRating") != null)
		{
			c.setSafetyRating((Integer)data.get("SafetyRating"));
		}
		if((String)data.get("SafetyRateDate") != null)
		{
			c.setSafetyRateDate((String)data.get("SafetyRateDate"));
		}
		if((Integer)data.get("Authorize") != null)
		{
			c.setAuthorize((Integer)data.get("Authorize"));
		}
		if((String)data.get("ContractDate") != null)
		{
			c.setContractDate((String)data.get("ContractDate"));
		}
		if((String)data.get("InsEndDate") != null)
		{
			c.setInsEndDate((String)data.get("InsEndDate"));
		}
		c.MarkClean();
		return c;
		
	}//End of the BuildFromDataRow(Map<String, Object> data)
	
}