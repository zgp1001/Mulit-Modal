package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import core.*;
import core.Truck;
import core.Vehicle;

public class VehicleSegmentTable extends JTable {

	ArrayList<Segment> segs;
	Vehicle v;
	public void ShowTable(Vehicle v)
	{
		
		this.v=v;
		setData();
		this.setVisible(true);
	}
	public VehicleSegmentTable()
	{
		super();
		this.setModel(new SegmentModel(new ArrayList<Segment>()));
		//this.v=v;
		//setData();
	}
	
	public void deleteSelectedVehicleSegment()
	{
		if(this.getSelectedRow()!=-1)Segment.Load(Integer.parseInt((this.getValueAt(this.getSelectedRow(), 0).toString()))).Delete();
		setData();
	}
	private void setData()
	{
		ArrayList<Segment> vehicles = v.getSchedule();
		
		this.setModel(new SegmentModel(vehicles));
	}
	class SegmentModel extends AbstractTableModel
	{
		public String[] columnNames={"ID","Start","End"};
		public ArrayList<Segment> trucks;
		
		public SegmentModel(ArrayList<Segment> temp)
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
			Segment t = trucks.get(row);
			if(column.equals("ID"))
				return t.getID();
			if(column.equals("Start"))
				return t.getStartLocation().getName();
			return t.getEndLocation().getName();
			
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

			}
			return null;
		}
	}
}
