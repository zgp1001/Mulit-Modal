package GUI;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import core.Cargo;
import core.Plane;
import core.Rail;
import core.Truck;
import core.Vehicle;
import GUI.*;
public class VehiclePanel extends JPanel {
	
	JTable jt;
	JScrollPane sp;
	VehicleTruckForm tf; 
	VehiclePlaneForm pf;
	VehicleRailForm rf;
	VehicleCargoForm cf;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnNew;
	JButton btnSave;
	JButton btnCancel;
	JTabbedPane tabbedPane;
	VehicleTable trucks,planes,rails,cargo;
	private JComboBox comboBox;
	//JTabbedPane tabbedPane;
	public VehiclePanel() 
	{
		initTable();
		addForm();
		addButtons();	
	}
	private void initTable()
	{
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("396px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("89px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("108px"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		trucks=new VehicleTable("Truck");
		trucks.getSelectionModel().addListSelectionListener(new TableListener());
		planes=new VehicleTable("Plane");
		planes.getSelectionModel().addListSelectionListener(new TableListener());
		rails=new VehicleTable("Rail");
		rails.getSelectionModel().addListSelectionListener(new TableListener());
		cargo=new VehicleTable("Cargo");
		cargo.getSelectionModel().addListSelectionListener(new TableListener());
		sp=new JScrollPane();
		sp.setBounds(10,31,457,200);
		sp.setViewportView(trucks);
		tabbedPane.addTab("Truck",sp);
		sp=new JScrollPane();
		sp.setBounds(10,31,457,200);
		sp.setViewportView(rails);
		tabbedPane.addTab("Rail",sp);
		sp=new JScrollPane();
		sp.setBounds(10,31,457,200);
		sp.setViewportView(cargo);
		tabbedPane.addTab("Cargo",sp);
		sp=new JScrollPane();
		sp.setBounds(10,31,457,200);
		sp.setViewportView(planes);
		tabbedPane.addTab("Plane",sp);
		tabbedPane.addChangeListener(new TabListener());
		add(tabbedPane, "2, 2, 5, 1");
	
	}
	private void hidePanels()
	{
		tf.hidePanel();
		cf.hidePanel();
		pf.hidePanel();
		rf.hidePanel();
	}
	private void addForm()
	{
		tf=new VehicleTruckForm();
		tf.setVisible(false);
		
		comboBox = new JComboBox(Vehicle.TravelTypes.values());
		comboBox.setSize(500, 50);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				hidePanels();
				switch((Vehicle.TravelTypes)comboBox.getSelectedItem())
				{
				case Truck:
					tf.showPanel();
					break;
				case Rail:
					rf.showPanel();
					break;
				case Plane:
					pf.showPanel();
					break;
				case Cargo:
					cf.showPanel();
					break;
				}
			}
		});
		comboBox.setVisible(false);
		add(comboBox, "2, 6, center, center");
		add(tf, "2, 8, 5, 1, fill, fill");
		cf=new VehicleCargoForm();
		cf.setVisible(false);
		add(cf, "2, 8, 5, 1, fill, fill");
		pf=new VehiclePlaneForm();
		pf.setVisible(false);
		add(pf, "2, 8, 5, 1, fill, fill");
		rf=new VehicleRailForm();
		rf.setVisible(false);
		add(rf, "2, 8, 5, 1, fill, fill");

	}
	private void addButtons()
	{
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				int index = tabbedPane.getSelectedIndex();
				if(index!=-1)
				{
					switch(index)
					{
					case 0:
					{
						int selectedIndex = trucks.getSelectedRow();
						if(selectedIndex!=-1)
						{
							tf.showPanel(Integer.parseInt(trucks.getValueAt(trucks.getSelectedRow(),0).toString()));
						}
						break;
					}
					case 1:
					{
						int selectedIndex = rails.getSelectedRow();
						if(selectedIndex!=-1)
						{
							rf.showPanel(Integer.parseInt(rails.getValueAt(rails.getSelectedRow(),0).toString()));
						}
						break;
					}
					case 2:
					{
						int selectedIndex = cargo.getSelectedRow();
						if(selectedIndex!=-1)
						{
							cf.showPanel(Integer.parseInt(cargo.getValueAt(cargo.getSelectedRow(),0).toString()));
						}
						break;
					}
					case 3:
					{
						int selectedIndex = planes.getSelectedRow();
						if(selectedIndex!=-1)
						{
							pf.showPanel(Integer.parseInt(planes.getValueAt(planes.getSelectedRow(),0).toString()));
						}
						break;
					}
					}
					tabbedPane.setVisible(false);
					btnEdit.setVisible(false);
					btnDelete.setVisible(false);
					btnNew.setVisible(false);
					btnSave.setVisible(true);
					btnCancel.setVisible(true);
				}
			}
		});
		btnEdit.setEnabled(false);
		add(btnEdit, "2, 4, right, top");
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				int index = tabbedPane.getSelectedIndex();
				if(index!=-1)
				{
					switch(index)
					{
					case 0:
					{
						trucks.deleteSelectedVehicle();
						break;
					}
					case 1:
					{
						rails.deleteSelectedVehicle();
						break;
					}
					case 2:
					{
						cargo.deleteSelectedVehicle();
						break;
					}
					case 3:
					{
						planes.deleteSelectedVehicle();
						break;
					}
					}
				}
			}
		});
		btnDelete.setEnabled(false);
		add(btnDelete, "4, 4, fill, top");
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				comboBox.setVisible(true);
				//tf.showPanel();
				tabbedPane.setVisible(false);
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
				btnNew.setVisible(false);
				btnSave.setVisible(true);
				btnCancel.setVisible(true);
				
			}
		});
		add(btnNew, "6, 4, fill, top");
		
		btnSave = new JButton("Save");
		btnSave.setVisible(false);
		add(btnSave, "4, 10, fill, top");
		btnCancel = new JButton("Cancel");
		btnCancel.setVisible(false);
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				tf.setVisible(false);
				tabbedPane.setVisible(true);
				btnEdit.setVisible(true);
				btnDelete.setVisible(true);
				btnNew.setVisible(true);
				btnCancel.setVisible(false);
				btnSave.setVisible(false);
			}
		});
		add(btnCancel, "6, 10, fill, top");
		
	}
	private void hidePanel()
	{
		this.setVisible(false);
	}
	
	class TabListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			VehicleTable temp = getSelectedTable();
			if(temp.getSelectedRow()!=-1)
				btnEdit.setEnabled(true);
			else
				btnEdit.setEnabled(false);
			
		}
		private VehicleTable getSelectedTable()
		{
			switch(tabbedPane.getSelectedIndex())
			{
			case 0:
			{
				return trucks;
			}
			case 1:
			{
				return rails;
			}
			case 2:
			{
				return cargo;
			}
			case 3:
			{
				return planes;
			}
			}
			return null;
		}
	}
	class TableListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			if(e.getLastIndex()!=-1)
			{
				ChangeEvent ev = new ChangeEvent(this);
				tabbedPane.getChangeListeners()[0].stateChanged(ev);
			}
		}
	}
}

