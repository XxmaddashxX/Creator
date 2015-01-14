package net.creator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClassData {
	
	String[] columns = {"Class", "Temperature (K)", "Colour/Color", "Luminosity", "Inner HZ (AU)", "Outer HZ (AU"};
	Object[] [] data = 	{{"O", ">30,000  ", "Blue", ">30,000", "N/A", "N/A"},
						{"B", "10,000 - 30,000 ", "Deep Blue / White", "25 - 30,000", "N/A", "N/A"},
						{"A", "7,500 - 10,000 ", "Blue / White", "5 - 25", "3.38", "5.826"},
						{"F", "6,000 - 7,500 ", "White", "1.5 - 5", "1.53", "2.65"},
						{"G", "5,200 - 6,000 ", "Yellowish White", "0.6 - 1.5", "1.0", "1.7"},
						{"K", "3,700 - 5,200 ", "Pale Yellow / Orange", "0.08 - 0.6", "0.647", "1.21"},
						{"M", "2,400 - 3,700 ", "Light Orange / Red", "<0.08", "0.208", "0.41"},
						{"L", "1,300 - 2,400 ", "Scarlet", "0.00005–0.001", "N/A", "N/A"},
						{"T", "500 - 1,300 ", "Magenta", "0.000001–0.00005", "N/A", "N/A"},
						{"Y", "<500 ", "Black", "0.0000001–0.000001", "N/A", "N/A"}};
						
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	public ClassData(){
		frame = new JFrame();
		frame.setTitle("Star/Planet Class Data");
		frame.setResizable(false);
		frame.setSize(800, 400);
		table = new JTable(data, columns);
		scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		table.setFillsViewportHeight(true);
		
		frame.setVisible(true);
	}

}
