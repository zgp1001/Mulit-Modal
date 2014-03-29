package GUI;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Truck;
import core.Vehicle;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTabbedPane;

public class VehicleTruckForm extends JPanel {
	JLabel contractorLabel,nameLabel,statusLabel;
	JComboBox<String> contractorDropDown;
	JTextField nameText;
	VehicleSegmentTable vst;
	//private Vehicle t;
	private Truck t;
	private JTabbedPane tabbedPane;
	private JPanel basic;
	private JComboBox<String> status;
	public VehicleTruckForm()
	{
		String[] contractor = {Vehicle.Contractors.DHL.toString(),Vehicle.Contractors.FedEX.toString(),Vehicle.Contractors.UPS.toString(),Vehicle.Contractors.USPS.toString()};
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("48px"),
				ColumnSpec.decode("69px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("56px"),
				ColumnSpec.decode("48px"),
				ColumnSpec.decode("69px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("56px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		basic = new JPanel();
		
		add(tabbedPane, "2, 2, 7, 17, fill, fill");
		basic.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("103px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("58px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("10px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("37px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("56px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		//add(contractorDropDown, "4, 20, left, top");
		nameLabel = new JLabel("Name");
		basic.add(nameLabel, "2, 2, left, center");
		//nameText.setSize(69, Integer.parseInt(FormFactory.LINE_GAP_ROWSPEC.toString()));
		//add(nameLabel,"2, 22, center, center");
		nameText = new JTextField(20);
		nameText.setText(setName());
		basic.add(nameText, "4, 2, left, top");
		contractorLabel = new JLabel();
		contractorLabel.setText("Contractor");
		basic.add(contractorLabel, "2, 4, left, center");
		tabbedPane.addTab("Basic", basic);
		//add(contractorLabel, "2, 20, center, center");
		contractorDropDown = new JComboBox(Vehicle.Contractors.values());
		contractorDropDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String name = setName();
				if(nameText!=null)
					nameText.setText(name);
			}
		});
		contractorDropDown.setSelectedItem(Vehicle.Contractors.DHL);
		basic.add(contractorDropDown, "4, 4, left, top");
			this.statusLabel=new JLabel("Status");
			//	add(nameText,"4, 22, left, top");
				basic.add(statusLabel, "2, 6, left, center");
			this.status=new JComboBox(Vehicle.Status.values());
			basic.add(status, "4, 6, left, top");
		this.setTypeForm();
	}
	private void loadVehicle(int id)
	{
		clearGUI();
		if(id>0)
		{
			t=Truck.Load(id);
			setTruck();
		}			
		vst.ShowTable(t);
	}
	public void showPanel()
	{
		clearGUI();
		loadNew();
		this.setVisible(true);
	}
	public void showPanel(int id)
	{
		loadVehicle(id);
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
		this.nameText.setText(setName());
	}
	private void loadNew()
	{

	}
	private void setTruck()
	{
		this.contractorDropDown.setSelectedItem(Vehicle.loadContractor(t.getContractor()));
		this.nameText.setText(t.getTruckName());
	}
	private String setName(){
		int randomInt;
		boolean goodNumber = false;
		String name = "NEWTRUCK";
		ArrayList<Truck> trucks = new ArrayList<Truck>();
		int trycounter=0;
		if(contractorDropDown!=null)
		{
		while(!goodNumber)
		{
			randomInt = (int)Math.floor(Math.random()*10000);
			name = this.contractorDropDown.getSelectedItem().toString() + "Truck" + randomInt;
			trucks = Truck.LoadAll("Where TruckName ='" +name + "'");
			trycounter++;
			if(!(trucks.size() > 0)){
				goodNumber = true;
			}
			else
			{
				if(trycounter==300)
				{
					name="NEWPLANE";
					goodNumber=true;
				}
			}
		}
		}
		return name;
	}
	private void setTypeForm()
	{
		final JPanel segments = new JPanel();
		vst = new VehicleSegmentTable();
		segments.add(new JScrollPane(vst));
		tabbedPane.addTab("Segments",segments);
		
		
	}
}
