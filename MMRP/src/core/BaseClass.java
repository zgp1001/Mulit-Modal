/**
 * @author Christopher Solomon, Jordan Schiller, Dan Miller, Zach Petrusch
 * @version 2.0
 */
package core;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseClass {
	private Boolean dirty;																	//The dirty/clean flag for DB management
	private Boolean newObject;																//The new/old flag for DB management
	private static final String DRIVER = "com.mysql.jdbc.Driver";							//Location of the driver
	private static final String URL="jdbc:mysql://Excalibur.sru.edu:3306/Multi-Modal";		//URL to connect to the database
	private static final String USER = "thangiah";											//Default user name
	private static final String PSWD="thangiah12345";										//Default password
	public static int connectionCounter=0;
	/**
	 * Return the value of Dirty.
	 * <p>
	 * If the object extending this class has been updated since it was loaded, True is returned. Else False Is returned.
	 * @return true if Dirty, otherwise false
	 */
	public boolean isDirty()
	{
		if(dirty==null)
			return false;
		return dirty;
	}//End of isDirty()
	
	/**
	 * Return the value of newObject.
	 * <p>
	 * If the object extending this class has not be inserted into Database, True is returned. Else False Is returned.
	 * @return true if New, otherwise false
	 */
	public boolean isNew()
	{
		if(newObject==null)
			return false;
		return newObject;
	}//End of isNew()
	
	//This function sets the dirty flag to true
	/**
	 * Marks the object as Dirty
	 * <p>
	 * To be called when a change is made to the extending object
	 * 
	 */
	public void MarkDirty()
	{
		dirty=true;
	}//End of MarkDirty()
	
	/**
	 * Marks the Object as Clean
	 * <p>
	 * To be called when a change to an object is pushed to the database 
	 * 
	 */
	public void MarkClean()
	{
		dirty=false;
	}//End of MarkClean()
	
	/**
	 * Marks the object as new
	 * <p>
	 * To be called when an object is created without loading it from the database as to flag it for insert  
	 * 
	 */
	public void MarkNew()
	{
		newObject=true;
	}//End of MarkNew()
	
	/**
	 * Marks the object as old
	 * <p>
	 * To be called when the a 'new' object is inserted into database
	 */
	public void MarkOld()
	{
			newObject=false;
	}//End of MarkOld
	
	/**
	 * Returns the connection to the database
	 * @return Connection to the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static Connection getConnection() throws SQLException,ClassNotFoundException
	{
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL,USER,PSWD);
	}//End of getConnection()
	
	/**
	 * Executes and returns the results of a query of the database
	 * @param sql The query to be executed
	 * @return ArrayList<Map<String,Object>>	maps the column name in the query to the value in the field
	 * @throws SQLException
	 */
	public static ArrayList<Map<String,Object>> executeQuery(String sql) throws Exception 
	{
		Connection c=null;
		try
		{
			c = getConnection();															//Create the connection
			connectionCounter++;
			ResultSet rs = c.createStatement().executeQuery(sql);							//Execute the query
			ResultSetMetaData md = rs.getMetaData();										//Save the metadata from the results
			ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>();		//Create a new ArrayList of Maps of Strings to Objects
			while(rs.next())
			{
				Map<String,Object> temp = new HashMap<String,Object>(md.getColumnCount());	//While we still have results create a new HashMap object 
				for(int i = 1; i<=md.getColumnCount();i++)									//For each of the columns place the string and object pair
				{
					temp.put(md.getColumnName(i),rs.getObject(i));
				}//End of for loop
				data.add(temp);																//Add the temp variable to the data
				
			}//End of while loop
			return data;																	//Return the data
			
		}//End of the try block
		catch(Exception ex)
		{
			System.out.println("Error " + ex);												//Print out the error to the screen
			System.out.println("Number of Connections " + connectionCounter);
			return null;
		}//End of the catch block
		finally
		{
			
			if(c!=null && !c.isClosed())													//Make sure the connection is closed
				c.close();		
			Thread.sleep(5);
			//connectionCounter--;
		}//End of finally clause
	}//End of executeQuery(String sql)
	
	/**
	 * Executes a command on the database
	 * @param sql Command to be executed
	 * @return True if command successfully executed, otherwise false 
	 * @throws SQLException
	 */
	public static boolean executeCommand(String sql)throws Exception
	{
		
		Connection c=null;
		try
		{
			c = getConnection();												//Create a connection to the database\
			connectionCounter++;
			c.createStatement().execute(sql);									//Execute the SQL statement
			return true;														//Return that it was successful
			
		}//End of try block	
		catch(Exception ex)
		{
			System.out.println("Error " + ex);		
			System.out.println("Number of Connections " + connectionCounter);//Print out the error
			return false;														//Return that the SQL command failed
		}//End of catch block
		finally
		{
			if(c!=null && !c.isClosed())										//Close the connection
				c.close();
			Thread.sleep(5);
			//connectionCounter--;
		}//End of finally block
	}//End of executeCommand(String sql)
	
	//Abstract Functions
	abstract void Update();
	abstract void Delete();

}//End of BaseClass


/*setup
 * Flights:
 * 1-2
 * 1-7
 * 1-8
 * 1-10
 * 2-1
 * 2-7
 * 2-8
 * 2-10
 * 3-4
 * 3-5
 * 3-6
 * 3-7
 * 3-8
 * 3-9
 * 4-3
 * 4-5
 * 4-6
 * 4-8
 * 4-9
 * 5-3
 * 5-4
 * 5-6
 * 5-9
 * 6-3
 * 6-4
 * 6-5
 * 6-9
 * 7-1
 * 7-2
 * 7-3
 * 7-6
 * 7-8
 * 7-10
 * 8-1
 * 8-2
 * 8-3
 * 8-4
 * 8-7
 * 8-10
 * 9-3
 * 9-4
 * 9-5
 * 9-6
 * 10-1
 * 10-2
 * 10-7
 * 10-8
 */
