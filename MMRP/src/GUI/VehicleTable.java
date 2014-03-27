package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.*;
import core.Truck;
import core.Vehicle;

public class VehicleTable extends JTable {

	String type;
	public VehicleTable(String type)
	{
		super();
		this.type=type;
		setData();
	}
	
	public void deleteSelectedVehicle()
	{
		if(type.equals("Truck")){
			if(this.getSelectedRow()!=-1)
			{
				Truck.Load(Integer.parseInt((this.getValueAt(this.getSelectedRow(), 0).toString()))).Delete();
			}
		}
		else
		{
			if(type.equals("Rail")){
				if(this.getSelectedRow()!=-1)
				{
					Rail.Load(Integer.parseInt((this.getValueAt(this.getSelectedRow(), 0).toString()))).Delete();
				}
			}
			else
			{
				if(type.equals("Plane")){
					if(this.getSelectedRow()!=-1)
					{
						Plane.Load(Integer.parseInt((this.getValueAt(this.getSelectedRow(), 0).toString()))).Delete();
					}
				}
				else
				{
					if(type.equals("Cargo")){
						if(this.getSelectedRow()!=-1)
						{
							Cargo.Load(Integer.parseInt((this.getValueAt(this.getSelectedRow(), 0).toString()))).Delete();
						}
					}
				}
			}
		}
		setData();
	}
	private void setData()
	{
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		if(type.equals("Truck")){
			vehicles.addAll(Truck.LoadAll(""));
		}
		else
		{
			if(type.equals("Rail")){
				vehicles.addAll(Rail.LoadAll(""));
			}
			else
			{
				if(type.equals("Plane")){
					vehicles.addAll(Plane.LoadAll(""));
				}
				else
				{
					if(type.equals("Cargo")){
						vehicles.addAll(Cargo.LoadAll(""));
					}
					else
					{
						vehicles.addAll(Truck.LoadAll(""));
					}
				}
			}
		}
		this.setModel(new VehicleModel(vehicles));
	}
	class VehicleModel extends AbstractTableModel
	{
		public String[] columnNames={"ID","Carrier","Type","StartingLocation","EndingLocation"};
		public ArrayList<Vehicle> trucks;
		
		public VehicleModel(ArrayList<Vehicle> temp)
		{
			trucks=temp;
		}
		public int getColumnCount()
		{
			return columnNames.length;
		}
		
		public int getRowCount()
		{
			return trucks.size();
		}
		

		@Override
		public String getColumnName(int column)
		{
			return columnNames[column];
		}
		
		public Object getValueAt(int row, int col)
		{
			String column = getColumnName(col);
			Vehicle t = trucks.get(row);
			if(column.equals("ID"))
				return t.getId();
			if(column.equals("Carrier"))
				return t.getContractor();
			if(column.equals("StartingLocation"))
				return 0;
			if(column.equals("EndingLocation"))
				return 0;
			if(column.equals("Type"))
				return t.getTravelType();
			return null;
		}
		@Override
		public Class<?> getColumnClass(int c)
		{
			switch (c)
			{
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return Integer.class;
			case 4:
				return Integer.class;
			}
			return null;
		}
	}
}
