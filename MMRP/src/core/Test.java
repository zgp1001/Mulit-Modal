package core;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import java.io.*;
public class Test extends BaseClass 
{
	public Test()
	{
	}
	
	public Test Load(){return null;}
	public void Update(){};
	public void Delete(){};
	public void printRandmonness()
	{

	}
	public static void main(String[] args) throws IOException
	{
		ArrayList<Shipment> shipments = Shipment.LoadAll("");
		for(int i = 0;i<shipments.size();i++)
		{
			System.out.println("Starting " + (i+1));
			Routing.AStarAlg alg = new Routing.AStarAlg(shipments.get(i));
			ArrayList<Segment> route = alg.getPath();
			shipments.get(i).DeleteAllHistory();
			shipments.get(i).setHistoryFromSegments(route);
			System.out.println("Done with " + (i+1));
		}
		printSolution();
		printShipmentInfoToFile();
		printVehicleInfo();
		printVehicleRoute();
		//File f = new File(".././Output/Vehicles.txt");
		//File f2 = new File(".././Output/Shipments.txt");
		//File f2 = new File("../.Output/ShipmentInfo.txt");
		// w=null;
		//Writer w2=null;
		//try
		//{
		
		//	if(!f.exists()) {
			//	f.createNewFile();
			//}
			//if(!f2.exists()) {
			////	f2.createNewFile();
			//}
		//	w=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			//w2=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f2)));
		//}
		//catch(Exception ex)
		//{
		//	System.out.println("Error " + ex);
		//}
		
		
		

	}
		public void getDistance()
	{
		double[][] arr = new double[10][10];
		//calculates the great circle distance between two points using the haversine formula.  Originally published by Roger Sinnott - Sky & Telescope magazine
		int earthRadius = 6371;			//radius of the earth in kilometers
		/*
		for(int i =1; i<=10;i++)
		{
			Location one = Location.Load(i);
			for(int j =1; j<=10;j++)
			{
				Location two=Location.Load(j);
				double lat2=one.getLatitude();
				double long2=one.getLongitude();
				double lat1=two.getLatitude();
				double long1=two.getLongitude();
				double d2r = Math.PI/180.0;
		
			    double dlong = (long2 - long1) * d2r;
			    double dlat = (lat2 - lat1) * d2r;
			    double a = Math.pow(Math.sin(dlat/2.0), 2) + Math.cos(lat1*d2r) * Math.cos(lat2*d2r) * Math.pow(Math.sin(dlong/2.0), 2);
			    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			    double d = 6367 * c;
				arr[i-1][j-1]= d;
				System.out.print(arr[i-1][j-1]+"\t");
				
			}
			System.out.println();
		}*/
	}
		
		public void generateSegments()
		{
			/*
			ArrayList<Cargo> planes = Cargo.LoadAll("");
			for(int i = 0; i<planes.size();i++)
			{
				try
				{
					int lastArrival;
					int lastLocation;
					int startLocation;
					int currentLocation;
					ArrayList<Map<String,Object>> paths = executeQuery("Select * from temptable where Type = 'SHIP'");
					int rand = (int) Math.floor(Math.random()* paths.size());
					Segment s = new Segment();
					s.setDepartureTime(0);
					double baseDistance=arr[(Integer)paths.get(rand).get("Location1")-1][(Integer)paths.get(rand).get("Location2")-1];
					//plane =.9, truck=1.1, rail=1.2, cargo=1.3
					double offSetDistance = baseDistance * 1.3;
					//plane=750,truck 112,rail=29,cargo=39
					int time = (int)Math.floor(baseDistance/39);
					s.setArrivalTime(s.getDepartureTime()+time);
					lastArrival=s.getArrivalTime();
					currentLocation=(Integer)paths.get(rand).get("Location2");
					lastLocation=(Integer)paths.get(rand).get("Location1");
					startLocation=(Integer)paths.get(rand).get("Location1");
					//plane=.75,truck=.55, rail=.35, cargo=.25
					int cost = (int)Math.floor(baseDistance*.25);
					s.setCost(cost);
					s.setDistance(offSetDistance);
					s.setEndLocation(currentLocation);
					s.setStartLocation(startLocation);
					s.setVehicle(planes.get(i));
					planes.get(i).addToSchedule(s);
					int jumps=1;
					while(jumps<2)
					{
						paths=executeQuery("Select * from temptable where Type = 'SHIP' and Location1 = '" +currentLocation+"'");
						rand=(int) Math.floor(Math.random()* paths.size());
						while((Integer)paths.get(rand).get("Location2")==lastLocation)
						{
							rand=(int) Math.floor(Math.random()* paths.size());
						}
						s = new Segment();
						s.setDepartureTime(lastArrival+10);
						baseDistance=arr[(Integer)paths.get(rand).get("Location1")-1][(Integer)paths.get(rand).get("Location2")-1];
						offSetDistance = baseDistance * 1.3;
						time = (int)Math.floor(baseDistance/39);
						s.setArrivalTime(s.getDepartureTime()+time);
						lastArrival=s.getArrivalTime();
						currentLocation=(Integer)paths.get(rand).get("Location2");
						lastLocation=(Integer)paths.get(rand).get("Location1");
						cost = (int)Math.floor(baseDistance*.25);
						s.setCost(cost);
						s.setDistance(offSetDistance);
						s.setEndLocation(currentLocation);
						s.setStartLocation(lastLocation);
						s.setVehicle(planes.get(i));
						planes.get(i).addToSchedule(s);
						jumps++;
						if(currentLocation==startLocation)
							break;
					}
					
					
				
				}
				catch(Exception ex)
				{
					System.out.println("Error " +ex);
				}
			}*/
		}
		public static void printVehicleRoute() throws IOException
		{
			File f = new File(".././Output/Vehicles.txt");
			Writer w = null;
			try
			{
				if(!f.exists())
					f.createNewFile();
				w=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			}
			catch(Exception ex)
			{
				System.out.println("Error "+ex);
			}
			
			w.write("\t\t\t\t\tTRUCKS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Truck> veh = Truck.LoadAll("");
			for(int i = 0; i<veh.size();i++)
			{
				Truck t = veh.get(i);
				w.write("\n"+t.getTruckName()+"\n\n");
				ArrayList<Segment> segs =t.getSchedule();
				ArrayList<Shipment> old=new ArrayList<Shipment>();
				ArrayList<Shipment> newShips=new ArrayList<Shipment>();
				for(int j=0;j<segs.size();j++)
				{
					Segment s = segs.get(j);
					ArrayList<Shipment> temp = newShips;
					old=temp;
					newShips=s.onBoard;
					
					Location start = s.getStartLocation();
					Location end = s.getEndLocation();
					
					w.write("\tFrom " +s.getStartLocation().getName());// + " to " + s.getEndLocation().getName()+ "\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity()+"\n");
					if(start.getState()==null || start.getState()=="")
					{
						w.write(","+start.getCountry());
					}
					else
					{
						w.write(","+start.getState());
					}
					w.write("   To   " + end.getName());
					
					if(end.getState()==null || end.getState()=="")
					{
						w.write(","+end.getCountry());
					}
					else
					{
						w.write(","+end.getState());
					}
					w.write("\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity());
					//getPickedUp shipments
					for(int n = 0; n<newShips.size();n++)
					{
						boolean exists=false;
						for(int o =0; o<old.size();o++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tPicked Up Shipment " + newShips.get(n).getId());
					}
					//get drop off
					for(int o = 0; o<old.size();o++)
					{
						boolean exists=false;
						for(int n =0; n<newShips.size();n++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tDropped off Shipment " + old.get(o).getId());
					}
					w.write("\n\n");
				}
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tPLANES\n----------------------------------------------------------------\n\n\n");
			ArrayList<Plane> veh1 = Plane.LoadAll("");
			for(int i = 0; i<veh1.size();i++)
			{
				Plane t = veh1.get(i);
				w.write("\n"+t.getPlaneName()+"\n\n");
				ArrayList<Segment> segs =t.getSchedule();
				ArrayList<Shipment> old=new ArrayList<Shipment>();
				ArrayList<Shipment> newShips=new ArrayList<Shipment>();
				for(int j=0;j<segs.size();j++)
				{
					Segment s = segs.get(j);
					ArrayList<Shipment> temp = newShips;
					old=temp;
					newShips=s.onBoard;
					Location start = s.getStartLocation();
					Location end = s.getEndLocation();
					
					w.write("\tFrom " +s.getStartLocation().getName());// + " to " + s.getEndLocation().getName()+ "\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity()+"\n");
					if(start.getState()==null || start.getState()=="")
					{
						w.write(","+start.getCountry());
					}
					else
					{
						w.write(","+start.getState());
					}
					w.write("   To   " + end.getName());
					
					if(end.getState()==null || end.getState()=="")
					{
						w.write(","+end.getCountry());
					}
					else
					{
						w.write(","+end.getState());
					}
					w.write("\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity());
					//getPickedUp shipments
					for(int n = 0; n<newShips.size();n++)
					{
						boolean exists=false;
						for(int o =0; o<old.size();o++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tPicked Up Shipment " + newShips.get(n).getId());
					}
					//get drop off
					for(int o = 0; o<old.size();o++)
					{
						boolean exists=false;
						for(int n =0; n<newShips.size();n++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tDropped off Shipment " + old.get(o).getId());
					}
					w.write("\n\n");
				}
			}
			w.write("\n\n\n\n\n\n");
			w.flush();
			w.write("\t\t\t\t\tTRAINS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Rail> veh2 = Rail.LoadAll("");
			for(int i = 0; i<veh2.size();i++)
			{
				Rail t = veh2.get(i);
				w.write("\n"+t.getRailName()+"\n\n");
				ArrayList<Segment> segs =t.getSchedule();
				
				ArrayList<Shipment> old=new ArrayList<Shipment>();
				ArrayList<Shipment> newShips=new ArrayList<Shipment>();
				for(int j=0;j<segs.size();j++)
				{
					Segment s = segs.get(j);
					ArrayList<Shipment> temp = newShips;
					old=temp;
					newShips=s.onBoard;
					Location start = s.getStartLocation();
					Location end = s.getEndLocation();
					
					w.write("\tFrom " +s.getStartLocation().getName());// + " to " + s.getEndLocation().getName()+ "\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity()+"\n");
					if(start.getState()==null || start.getState()=="")
					{
						w.write(","+start.getCountry());
					}
					else
					{
						w.write(","+start.getState());
					}
					w.write("   To   " + end.getName());
					
					if(end.getState()==null || end.getState()=="")
					{
						w.write(","+end.getCountry());
					}
					else
					{
						w.write(","+end.getState());
					}
					w.write("\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity());
					//getPickedUp shipments
					for(int n = 0; n<newShips.size();n++)
					{
						boolean exists=false;
						for(int o =0; o<old.size();o++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tPicked Up Shipment " + newShips.get(n).getId());
					}
					//get drop off
					for(int o = 0; o<old.size();o++)
					{
						boolean exists=false;
						for(int n =0; n<newShips.size();n++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tDropped off Shipment " + old.get(o).getId());
					}
					w.write("\n\n");
				}
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tCARGOSHIPS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Cargo> veh3 = Cargo.LoadAll("");
			for(int i = 0; i<veh3.size();i++)
			{
				Cargo t = veh3.get(i);
				w.write("\n"+t.getCargoName()+"\n\n");
				ArrayList<Segment> segs =t.getSchedule();
				ArrayList<Shipment> old=new ArrayList<Shipment>();
				ArrayList<Shipment> newShips=new ArrayList<Shipment>();
				for(int j=0;j<segs.size();j++)
				{
					Segment s = segs.get(j);
					ArrayList<Shipment> temp = newShips;
					old=temp;
					newShips=s.onBoard;
					Location start = s.getStartLocation();
					Location end = s.getEndLocation();
					
					w.write("\tFrom " +s.getStartLocation().getName());// + " to " + s.getEndLocation().getName()+ "\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity()+"\n");
					if(start.getState()==null || start.getState()=="")
					{
						w.write(","+start.getCountry());
					}
					else
					{
						w.write(","+start.getState());
					}
					w.write("   To   " + end.getName());
					
					if(end.getState()==null || end.getState()=="")
					{
						w.write(","+end.getCountry());
					}
					else
					{
						w.write(","+end.getState());
					}
					w.write("\n\t\tCapacity: " + t.getCapacity()+"\tActual: " + s.estimateCapacity());
					//getPickedUp shipments
					for(int n = 0; n<newShips.size();n++)
					{
						boolean exists=false;
						for(int o =0; o<old.size();o++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tPicked Up Shipment " + newShips.get(n).getId());
					}
					//get drop off
					for(int o = 0; o<old.size();o++)
					{
						boolean exists=false;
						for(int n =0; n<newShips.size();n++)
						{
							if(old.get(0).getId()==newShips.get(n).getId()) exists=true;
						}
						if(!exists) w.write("\n\t\t\tDropped off Shipment " + old.get(o).getId());
					}
					w.write("\n\n");
				}
			}
			w.flush();
		}
		public static void printSolution()throws IOException
		{
			File f = new File(".././Output/Shipments.txt");
			Writer w2=null;
			try
			{
				if(!f.exists())
					f.createNewFile();
				w2=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			}
			catch(Exception ex)
			{
				System.out.println("Error " +ex);
			}
			ArrayList<Shipment> shipments = Shipment.LoadAll("");
			for(int i = 0; i< shipments.size();i++)
			{
				w2.write("Shipment " + (i+1)+ "    From: " +Location.Load(shipments.get(i).getFromLocationID()).getName() + "    To: "+Location.Load(shipments.get(i).getToLocationID()).getName()+"\n"); 
				//Routing.AStarAlg alg = new Routing.AStarAlg(shipments.get(i));
				
				//ArrayList<Segment> segs = alg.getPath();
				//shipments.get(i).setHistoryFromSegments(segs);
				//w2.write("Shipment " + shipments.get(i).getId()+"\t Earliest: " + shipments.get(i).getEarliestTime()+"\t Latest: "+ shipments.get(i).getLatestTime()+"\n");
				//w2.write("\t Start:" + Location.Load(shipments.get(i).getFromLocationID()).getName()+" End: "+Location.Load(shipments.get(i).getToLocationID()).getName()+"\n");
				ArrayList<ShipmentHistory>hist=shipments.get(i).getHistory();
				for(int j = 0; j<hist.size();j++)
				{
					Segment s = hist.get(j).getSegment();
					Vehicle v =s.getVehicle();
					w2.write("\n\t" + s.getStartLocation().getName() + " to " + s.getEndLocation().getName() + " via " + v.getVehicleName());
					
				}
				w2.write("\n\n\tArrival: " + hist.get(hist.size()-1).getSegment().getArrivalTime()+"\n\n\n\n");
				//if(segs.size()!=0)
				//{
				//for(int j = 0; j<segs.size();j++)
					//w2.write("\t\tSegment id " + segs.get(j).getID()+ "\n\t\t\t"+segs.get(j).getStartLocation().getName()+"\t"+segs.get(j).getEndLocation().getName()+"\n");
				//w2.write("\tArrival: "+ segs.get(segs.size()-1).getArrivalTime()+"\n");
				//}
				System.out.println("Done with " + i);
			}
			w2.flush();
			
		}
		public static void printShipmentInfoToFile()throws IOException
		{
			File f = new File(".././Output/ShipmentInfo.txt");
			Writer w=null;
			try
			{
				if(!f.exists())
					f.createNewFile();
				w=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			}
			catch(Exception ex)
			{
				System.out.println("Error " +ex);
			}
			
			w.write("\t\t\t\t\tShipment Information");
			w.write("\n\n");
			//w.write("ID\tFrom\tTo\tSize\tPriority\tDeparture\tEarliest\tLatest");
			w.write("\n-----------------------------------------------------------------");
			ArrayList<Shipment> s = Shipment.LoadAll("");
			for(int i = 0;i<s.size();i++)
			{
				Shipment temp = s.get(i);
				Location start = Location.Load(temp.getFromLocationID());
				Location end = Location.Load(temp.getToLocationID());
				w.write("\nShipmentID: "+temp.getId());//+"\t"+start.getName());
				w.write("\n\tFrom: " + start.getName());
				if(start.getState()==null || start.getState()=="")
				{
					w.write(","+start.getCountry());
				}
				else
				{
					w.write(","+start.getState());
				}
				w.write("\n\tTo: " + end.getName());
				
				if(end.getState()==null || end.getState()=="")
				{
					w.write(","+end.getCountry());
				}
				else
				{
					w.write(","+end.getState());
				}
				w.write("\n\tSize: "+temp.getSize()+"\n\tPriority: "+temp.getPriority()+"\n\tDeparture: "+temp.getEarliestDepartureTime()+ "\n\tEarliest "+temp.getEarliestArrivalTime()+"\n\tLatest: "+temp.getLatestArrivalTime());
				w.write("\n");
			}
			w.flush();
			
		}
		public static void printVehicleInfo()throws IOException
		{
			File f = new File(".././Output/VehicleInfo.txt");
			Writer w=null;
			try
			{
				if(!f.exists())
					f.createNewFile();
				w=  new BufferedWriter(new OutputStreamWriter( new FileOutputStream(f)));
			}
			catch(Exception ex)
			{
				System.out.println("Error " +ex);
			}
			
			w.write("\t\t\t\t\tVehicle Information");
			w.write("\n\n");
			//w.write("ID\tFrom\tTo\tSize\tPriority\tDeparture\tEarliest\tLatest");
			w.write("\n-----------------------------------------------------------------");
			w.write("\n\n\n\t\t\t\t\t\tTRUCKS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Truck> veh = Truck.LoadAll("");
			for(int i = 0; i<veh.size();i++)
			{
				Truck t = veh.get(i);
				w.write("\nTruckID: "+t.getId());
				w.write("\n\tTruck Name: "+t.getTruckName());
				w.write("\n\tTruck Type: "+t.getTruckType());
				w.write("\n\tContractor: " + t.getContractor());
				w.write("\n\tCapacity: " + t.getCapacity());
				w.write("\n\tStatus: "+t.getStatus());
				w.write("\n");
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tPLANES\n----------------------------------------------------------------\n\n\n");
			ArrayList<Plane> veh1 = Plane.LoadAll("");
			for(int i = 0; i<veh1.size();i++)
			{
				Plane t = veh1.get(i);
				w.write("\nPlaneID: "+t.getId());
				w.write("\n\tPlane Name: "+t.getPlaneName());
				w.write("\n\tPlane Type: "+t.getPlaneType());
				w.write("\n\tContractor: " + t.getContractor());
				w.write("\n\tCapacity: " + t.getCapacity());
				w.write("\n\tStatus: "+t.getStatus());
				w.write("\n");
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tTRAINS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Rail> veh2 = Rail.LoadAll("");
			for(int i = 0; i<veh2.size();i++)
			{
				Rail t = veh2.get(i);
				w.write("\nRailID: "+t.getId());
				w.write("\n\tRail Name: "+t.getRailName());
				w.write("\n\tRail Type: "+t.getRailType());
				w.write("\n\tContractor: " + t.getContractor());
				w.write("\n\tCapacity: " + t.getCapacity());
				w.write("\n\tStatus: "+t.getStatus());
				w.write("\n");
			}
			w.write("\n\n\n\n\n\n");
			w.write("\t\t\t\t\tCARGOSHIPS\n----------------------------------------------------------------\n\n\n");
			ArrayList<Cargo> veh3 = Cargo.LoadAll("");
			for(int i = 0; i<veh3.size();i++)
			{
				Cargo t = veh3.get(i);
				w.write("\nCargoShipID: "+t.getId());
				w.write("\n\tCargo Ship Name: "+t.getCargoName());
				w.write("\n\tCargo Ship Type: "+t.getCargoType());
				w.write("\n\tContractor: " + t.getContractor());
				w.write("\n\tContainers: " + t.getNumOfContainers());
				w.write("\n\tCapacity: " + t.getCapacity());
				w.write("\n\tStatus: "+t.getStatus());
				w.write("\n");
			}
			w.flush();
			
		}
	}


