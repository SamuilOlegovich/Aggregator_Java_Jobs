package aggregator.view;


import aggregator.Controller;
import aggregator.vo.Vacancy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import java.awt.*;

// НЕ ДОДЕЛАН!!!
public class SwingView extends JFrame implements View {
private Controller controller;
private JLabel label = new JLabel("Input:");
private JButton button = new JButton("Press");
private JTextField input = new JTextField("", 5);
private JRadioButton radio1 = new JRadioButton("Select this");
private JRadioButton radio2 = new JRadioButton("Select that");
private JCheckBox check = new JCheckBox("Check", false);


public SwingView() {
super("Simple Example");
this.setBounds(100,100,250,100);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

Container container = this.getContentPane();
container.setLayout(new GridLayout(3,2,2,2));
container.add(label);
container.add(input);

ButtonGroup group = new ButtonGroup();
group.add(radio1);
group.add(radio2);
container.add(radio1);

radio1.setSelected(true);
container.add(radio2);
container.add(check);
button.addActionListener(new ButtonEventListener());
container.add(button);
}


class ButtonEventListener implements ActionListener {
public void actionPerformed(ActionEvent e) {
    String message = "";
    message += "Button was pressed\n";
    message += "Text is " + input.getText() + "\n";
    message += (radio1.isSelected()?"Radio #1":"Radio #2")
            + " is selected\n";
    message += "CheckBox is " + ((check.isSelected())
            ?"checked":"unchecked");
    JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
}
}

public void update(List<Vacancy> vacancies) {

}

public void setController(Controller controller) {
this.controller = controller;
}
}
