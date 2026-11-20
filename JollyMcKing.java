import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JollyMcKing extends Frame implements ActionListener{

    Label username = new Label("Username: ");
    TextField txt1 = new TextField(20);
    Label password = new Label("Password: ");
    TextField txt2 = new TextField(20);
    Button okBtn = new Button("Ok");

    Label chooseOrderLbl = new Label("Choose Your Order: ", Label.CENTER);
    Checkbox valueML1 = new Checkbox("Value Meal #1: P25.00");
    Checkbox valueML2 = new Checkbox("Value Meal #2: P30.00");
    Checkbox valueML3 = new Checkbox("Value Meal #3: P35.00");
    Choice beverages = new Choice();

    CheckboxGroup rice = new CheckboxGroup();
    Checkbox xtraRice = new Checkbox("Extra Rice", false, rice);
    Checkbox noXtraRice = new Checkbox("No Extra Rice", false, rice);
    Button computeBtn = new Button("Compute");

    Label totalBill = new Label("Total Bill is: ");
    TextField trueBill = new TextField(20);

    Button clearBtn = new Button("Clear");
    Button closeBtn = new Button("Close");

    JollyMcKing(){

        setLayout(new FlowLayout());
        add(username);
        add(txt1);
        add(password);
        txt2.setEchoChar('*');
        add(txt2);
        add(okBtn); okBtn.addActionListener(this);
        add(chooseOrderLbl);  chooseOrderLbl.setEnabled(false);
        add(valueML1); valueML1.setEnabled(false);
        add(valueML2); valueML2.setEnabled(false);
        add(valueML3); valueML3.setEnabled(false);
        beverages.add("Coke"); 
        beverages.add("Sprite");
        beverages.add("Pepsi");
        add(beverages); beverages.setEnabled(false);
        add(xtraRice); xtraRice.setEnabled(false);
        add(noXtraRice); noXtraRice.setEnabled(false);
        add(computeBtn); computeBtn.addActionListener(this); computeBtn.setEnabled(false);
        add(totalBill); totalBill.setEnabled(false);
        add(trueBill); trueBill.setEnabled(false); trueBill.setEditable(false);
        add(clearBtn); clearBtn.addActionListener(this); clearBtn.setEnabled(false);
        add(closeBtn); closeBtn.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
    
    public static void main(String [] args){
        JollyMcKing jMcKing = new JollyMcKing();
        jMcKing.setSize(300, 500);
        jMcKing.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        
            if(e.getSource() == okBtn){

                try {
                    Integer.parseInt(txt2.getText());

                    if(txt1.getText().equals("admin") && txt2.getText().equals("1234")){
                        chooseOrderLbl.setEnabled(true);
                        valueML1.setEnabled(true);
                        valueML2.setEnabled(true);
                        valueML3.setEnabled(true);
                        beverages.setEnabled(true);
                        xtraRice.setEnabled(true);
                        noXtraRice.setEnabled(true);
                        computeBtn.setEnabled(true);
                        totalBill.setEnabled(true);
                        trueBill.setEnabled(true);
                        clearBtn.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Password");
                    }
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if(e.getSource() == closeBtn){
                int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", 
                    "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                if(answer == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            } else if(e.getSource() == computeBtn){
                double select1 = valueML1.getState() ? 25 : 0;
                double select2 = valueML2.getState() ? 30 : 0;
                double select3 = valueML3.getState() ? 35 : 0;
                double selectrice = rice.getSelectedCheckbox() != null && 
                rice.getSelectedCheckbox().getLabel().equals("Extra Rice") ? 25 : 0;
                double total = select1 + select2 + select3 + selectrice;

                trueBill.setText(String.valueOf(total));
            } else if(e.getSource() == clearBtn){
                int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear?", 
                    "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                );

                if(answer == JOptionPane.YES_OPTION){
                    valueML1.setState(false);
                    valueML2.setState(false);
                    valueML3.setState(false);
                    beverages.select(0);
                    rice.setSelectedCheckbox(null);
                    trueBill.setText("");
                }
            } 
        }
    }