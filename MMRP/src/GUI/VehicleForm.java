package GUI;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Cargo;
import core.Plane;
import core.Rail;
import core.Truck;
import core.Vehicle;

public class VehicleForm extends JPanel {
	JLabel contractorLabel,nameLabel;
	JComboBox<String> contractorDropDown;
	JTextField nameText;
	//private Vehicle t;
	private Truck t;
	private Rail r;
	private Cargo c;
	private Plane p;
	public VehicleForm()
	{
		String[] contractor = {Vehicle.Contractors.DHL.toString(),Vehicle.Contractors.FedEX.toString(),Vehicle.Contractors.UPS.toString(),Vehicle.Contractors.USPS.toString()};
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("48px"),
				ColumnSpec.decode("69px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("56px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		 contractorLabel = new JLabel();
		contractorLabel.setText("Contractor");
		add(contractorLabel, "2, 2, center, center");
		contractorDropDown = new JComboBox(Vehicle.Contractors.values());
		contractorDropDown.setSelectedItem(Vehicle.Contractors.DHL);
		add(contractorDropDown, "4, 2, left, top");
		 nameLabel = new JLabel("Name");
		nameText = new JTextField(20);
		nameText.setText("NEWTRUCK");
		//nameText.setSize(69, Integer.parseInt(FormFactory.LINE_GAP_ROWSPEC.toString()));
		add(nameLabel,"2, 4, center,center");
		add(nameText,"4, 4, left, top");
	}
	private void loadVehicle(int id,String type)
	{
		clearGUI();
		if(id>0)
		{
			if(type.equals("Truck")){
				t=Truck.Load(id);
				setTruck();
			}
			else
			{
				if(type.equals("Rail")){
					r=Rail.Load(id);
					setRail();
				}
				else
				{
					if(type.equals("Plane")){
						p=Plane.Load(id);
						setPlane();
					}
					else
					{
						if(type.equals("Cargo")){
							c= Cargo.Load(id);
							setCargo();
						}
						else
						{
							t=Truck.Load(id);
							setTruck();
						}
					}
				}
				
			}
			//t=Truck.Load(id);
			//this.contractorDropDown.setSelectedItem(t.getContractor());
			//this.nameText.setText(t.getContractor()+""+t.getTravelType()+""+t.getId());
		}			
	}
	public void showPanel()
	{
		clearGUI();
		loadNew();
		this.setVisible(true);
	}
	public void showPanel(int id,String type)
	{
		loadVehicle(id,type);
		this.setVisible(true);
	}
	public void hidePanel()
	{
		clearGUI();
		this.setVisible(false);
	}
	private void clearGUI()
	{
		this.contractorDropDown.setSelectedIndex(0);
		this.nameText.setText("");
	}
	private void loadNew()
	{

	}
	private void setRail()
	{
		this.contractorDropDown.setSelectedItem(Vehicle.loadContractor(r.getContractor()));
		this.nameText.setText(r.getRailName());
	}
	private void setTruck()
	{
		this.contractorDropDown.setSelectedItem(Vehicle.loadContractor(t.getContractor()));
		this.nameText.setText(t.getTruckName());
	}
	private void setPlane()
	{
		this.contractorDropDown.setSelectedItem(Vehicle.loadContractor(p.getContractor()));
		this.nameText.setText(p.getPlaneName());
	}
	private void setCargo()
	{
		this.contractorDropDown.setSelectedItem(Vehicle.loadContractor(c.getContractor()));
		this.nameText.setText(c.getCargoName());
	}
}
