package core;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import GUI.VehiclePanel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TestingWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestingWindow window = new TestingWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestingWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JList Trucks;
	JList Schedule;
	JPanel SchedulePanel;
	//TruckForm tform;
	JScrollPane scroll1;
	JTable table1;
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(41, 51, 581, 331);
		tabbedPane.addTab("Truck",new VehiclePanel("Truck"));
		tabbedPane.addTab("Rail",new VehiclePanel("Rail"));
		tabbedPane.addTab("Cargo",new VehiclePanel("Cargo"));
		tabbedPane.addTab("Plane",new VehiclePanel("Plane"));
		frame.getContentPane().add(tabbedPane);
		//JButton testButton = new JButton("Truck1");
		//testButton.addActionListener( new ActionListener() {
 
         //  public void actionPerformed(ActionEvent e)
         //   {
           //     tform.setTruck(1);
             //   tform.showPanel();
            //}
       // });
		//JButton testButton2 = new JButton("New Truck");
		///testButton2.addActionListener( new ActionListener() {
 
           // public void actionPerformed(ActionEvent e)
            //{
            	//tform=new TruckForm();
              //  tform.showPanel();
            //}
        //});      
		//table1 = new JTable();
		//scroll1=new JScrollPane();
		//table1.setModel(new TruckPanel());
		//scroll1.setViewportView(table1);
		//tTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		//tTable.setFillsViewportHeight(true);
		//javax.swing.JScrollPane whatevs = new JScrollPane();
		//whatevs.add(tTable);
		SchedulePanel = new JPanel();
		//SchedulePanel.add(testButton);
		//SchedulePanel.add(testButton2);
		//SchedulePanel.add(tform);

		//frame.add(tform);
	}
}

