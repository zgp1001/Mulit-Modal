package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;


import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;



public class Main_Source {

	private JFrame frmMmrp;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Source window = new Main_Source();
					window.frmMmrp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Log.createLogg();
				Log.writeLogInfo("Application Created");

			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Source() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMmrp = new JFrame();
		frmMmrp.setTitle("MMRP");
		frmMmrp.setBackground(new Color(255, 255, 255));
		frmMmrp.setBounds(100, 100, 919, 600);
		frmMmrp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * Code defined here is for the shipments button
		 */
		frmMmrp.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("117px"),
				ColumnSpec.decode("17px"),
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("100px"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("58px"),}));
		final JButton btnShipments = new JButton("Shipments");
		btnShipments.setToolTipText("Click here to view shipment information");
		btnShipments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShipments.setMnemonic(KeyEvent.VK_S);
		frmMmrp.getContentPane().add(btnShipments, "2, 4, fill, fill");

		final JButton btnVehicles = new JButton("Vehicles");
		btnVehicles.setToolTipText("Click here to view vehicle information");
		btnVehicles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVehicles.setMnemonic(KeyEvent.VK_V);
		frmMmrp.getContentPane().add(btnVehicles, "2, 6, fill, fill");

		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit MMRP");
		frmMmrp.getContentPane().add(btnExit, "2, 8, fill, fill");
		btnExit.setMnemonic(KeyEvent.VK_E);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));

		final JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCreate.setEnabled(false);
		frmMmrp.getContentPane().add(btnCreate, "4, 2, fill, fill");

		final JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDelete.setEnabled(false);
		frmMmrp.getContentPane().add(btnDelete, "6, 2, fill, fill");

		final JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEdit.setEnabled(false);
		frmMmrp.getContentPane().add(btnEdit, "8, 2, fill, fill");

		final JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnImport.setEnabled(false);
		frmMmrp.getContentPane().add(btnImport, "10, 2, fill, fill");
		
		final VehiclePanel vp = new VehiclePanel();
		vp.setVisible(false);
		
		frmMmrp.getContentPane().add(vp, new CellConstraints().xywh(4,4,11,11));//, row, colSpan, rowSpan)"4,4,fill,fill");
		
		btnShipments.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        Log.writeLogInfo("Clicked Shipments, Disabling Shipment Button - Enabling Top Menu Buttons");
		        btnShipments.setEnabled(false);
		        
		        if (!btnVehicles.isEnabled()) {
		        	btnVehicles.setEnabled(true);
		        }
		        unhide(btnCreate, btnImport, btnEdit,btnDelete);
		        
		        
		    }
		});
		
		btnVehicles.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        Log.writeLogInfo("Clicked Vehicles, Disabling Vehicle Button - Enabling Top Menu Buttons");
		        btnVehicles.setEnabled(false);
		        
		        if (!btnShipments.isEnabled()) {
		        	btnShipments.setEnabled(true);
		        }
		        vp.setVisible(true);
		        unhide(btnCreate, btnImport, btnEdit,btnDelete);
		        
		    }
		});
		
		btnExit.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Log.writeLogSevere("User selected to Exit Program");
				System.exit(0);
				
			}
		});
	}
	
	
	/**
	 * Function written to unhide the top buttons when a user has selecetd shipments or vehicles
	 * @param b1 - The first button to be unhidden
	 * @param b2 - The second button to be enabled
	 * @param b3 - The third button to be enabled
	 * @param b4 - The fourth button to be enabled
	 */
	private static void unhide(JButton b1, JButton b2, JButton b3, JButton b4) {
		Log.writeLogInfo("Unhiding Top Menu Buttons");
		if (!b1.isEnabled())
			b1.setEnabled(true);
		if (!b2.isEnabled())
			b2.setEnabled(true);
		if (!b3.isEnabled())
			b3.setEnabled(true);
		if (!b4.isEnabled())
			b4.setEnabled(true);
	}
}
