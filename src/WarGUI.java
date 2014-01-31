import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.NumberFormatException;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class WarGUI extends JPanel {
	
	private int startingHandFlag;
	private int returningCardFlag;
	
	public WarGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ButtonGroup preSortRadios = new ButtonGroup();
		
        JRadioButton sort0 = new JRadioButton("No Pre-Sorting");
        sort0.addActionListener(new RadioListenerStart(0));
        sort0.setSelected(true);

        JRadioButton sort1 = new JRadioButton("Pre-sort Low to High");
        sort1.addActionListener(new RadioListenerStart(1));
        //basic.setFont(new Font("Serif", Font.PLAIN, 15));
        //advanced.setFont(new Font("Serif", Font.PLAIN, 15));
        
        JRadioButton sort2 = new JRadioButton("Pre-sort High to Low");
        sort2.addActionListener(new RadioListenerStart(2));
        
        JRadioButton sort3 = new JRadioButton("Pre-sort Alternating High to Low from Bottom");
        sort3.addActionListener(new RadioListenerStart(3));
        
        JRadioButton sort4 = new JRadioButton("Pre-sort Alternating Low to High from Bottom");
        sort4.addActionListener(new RadioListenerStart(4));
        
        JRadioButton sort5 = new JRadioButton("Pre-sort Alternating High to Low from Top");
        sort5.addActionListener(new RadioListenerStart(5));
        
        JRadioButton sort6 = new JRadioButton("Pre-sort Alternating Low to High from Top");
        sort6.addActionListener(new RadioListenerStart(6));

        preSortRadios.add(sort0);
        preSortRadios.add(sort1);
        preSortRadios.add(sort2);
        preSortRadios.add(sort3);
        preSortRadios.add(sort4);
        preSortRadios.add(sort5);
        preSortRadios.add(sort6);

        add(sort0);
        add(sort1);
        add(sort2);
        add(sort3);
        add(sort4);
        add(sort5);
        add(sort6);
        
        
        ButtonGroup returnRadios = new ButtonGroup();
		
        JRadioButton return0 = new JRadioButton("Return Cards in Random Order");
        return0.addActionListener(new RadioListenerReturn(0));
        return0.setSelected(true);

        JRadioButton return1 = new JRadioButton("Return Cards in Alternating Low High, High Low");
        return1.addActionListener(new RadioListenerReturn(1));
        //basic.setFont(new Font("Serif", Font.PLAIN, 15));
        //advanced.setFont(new Font("Serif", Font.PLAIN, 15));
        
        JRadioButton return2 = new JRadioButton("Return Cards in Alternating High Low, Low High");
        return2.addActionListener(new RadioListenerReturn(2));
        
        JRadioButton return3 = new JRadioButton("Return Cards High Low, High Low");
        return3.addActionListener(new RadioListenerReturn(3));
        
        JRadioButton return4 = new JRadioButton("Return Cards Low High, Low High");
        return4.addActionListener(new RadioListenerReturn(4));
        
        JRadioButton return5 = new JRadioButton("Return Cards in the Same Way they were Played");
        return5.addActionListener(new RadioListenerReturn(5));

        returnRadios.add(return0);
        returnRadios.add(return1);
        returnRadios.add(return2);
        returnRadios.add(return3);
        returnRadios.add(return4);
        returnRadios.add(return5);

        add(return0);
        add(return1);
        add(return2);
        add(return3);
        add(return4);
        add(return5);
        
        
        JButton button = new JButton("Start Simulation");
        button.addActionListener(new ButtonListener());
        add(button);
        
	}
	
	private class RadioListenerStart implements ActionListener {
		
		private int startFlag;
		
		public RadioListenerStart (int i) {
			startFlag = i;
		}
		
		public void actionPerformed(ActionEvent e) {
			startingHandFlag = startFlag;
		}
	}
	
	private class RadioListenerReturn implements ActionListener {
		
		private int returnFlag;
		
		public RadioListenerReturn (int i) {
			returnFlag = i;
		}
		
		public void actionPerformed(ActionEvent e) {
			returningCardFlag = returnFlag;
		}
	}
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			int times = -1;
			boolean keepGoing = true;
			while (times <= 0 && keepGoing) {
				try {
					times = Integer.parseInt(JOptionPane.showInputDialog(null,
					        "Enter how many times you want to simulate a game of War"));
					if (times == 0) {
						JOptionPane.showMessageDialog(null, "That's boring, try a positive number!");
					}
				} catch (NumberFormatException exception) {
					if (exception.getMessage().equals("null")) {
						keepGoing = false;
					} else {
						JOptionPane.showMessageDialog(null, "That is not correct input");
					}
				}
			}
		 	if (times > 0) {
				WarPlayer.runWar((double) times, startingHandFlag, returningCardFlag);
			}
		}
	}
}
