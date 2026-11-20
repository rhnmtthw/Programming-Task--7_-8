import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MJRCResto extends Frame implements ActionListener, ItemListener{
    Label titleLbl = new Label("Choose Your Order and the number of orders: ");

    Checkbox soup = new Checkbox("Soup:    P25.00");
    TextField soupQuantity = new TextField("0", 10);
    Checkbox coffee = new Checkbox("Coffee:  P20.00");
    TextField coffeeQuantity = new TextField("0", 10);
    Checkbox siopao = new Checkbox("Siopao:  P15.00");
    TextField siopaoQuantity = new TextField("0", 10);

    Button computeBtn = new Button("Compute Bill");
    Button clearAllBtn = new Button("Clear All");
    Label totalBillLbl = new Label("Total Bill is: ");
    TextField totalBill = new TextField(30);
    
    Label amountGivenLbl = new Label("Amount Given: ");
    TextField amountGiven = new TextField(30);

    Label changeLbl = new Label("Change: ");
    TextField change = new TextField(30);

    Button computeChangeBtn = new Button("Compute Change");
    Button close = new Button("Close");

    MJRCResto(){

        setLayout(new FlowLayout());
        add(titleLbl);
        add(soup); add(soupQuantity); soup.addItemListener(this); soupQuantity.setEditable(false);
        add(coffee); add(coffeeQuantity); coffee.addItemListener(this); coffeeQuantity.setEditable(false);
        add(siopao); add(siopaoQuantity); siopao.addItemListener(this); siopaoQuantity.setEditable(false);
        add(computeBtn); computeBtn.addActionListener(this);
        add(clearAllBtn); clearAllBtn.addActionListener(this);
        add(totalBillLbl);
        add(totalBill); totalBill.setEditable(false);
        add(amountGivenLbl);
        add(amountGiven);
        add(changeLbl);
        add(change); change.setEditable(false);
        add(computeChangeBtn); computeChangeBtn.addActionListener(this);
        add(close); close.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    public static void main(String [] args){
        MJRCResto resto = new MJRCResto();
        resto.setSize(300, 400);
        resto.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        System.out.println("Button clicked: " + e.getSource());

        if(e.getSource() == computeBtn){
            try{
                double spQuantity = Double.parseDouble(soupQuantity.getText()) * 25;
                double cQuantity = Double.parseDouble(coffeeQuantity.getText()) * 20;
                double soQuantity = Double.parseDouble(siopaoQuantity.getText()) * 15;

                double total = spQuantity + cQuantity + soQuantity;

                totalBill.setText(String.valueOf(total));
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Invalid Input!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if(e.getSource() == computeChangeBtn){
            try{
                double amount = Double.parseDouble(amountGiven.getText());
                double bill = Double.parseDouble(totalBill.getText());

                if(amount > bill){
                    change.setText(String.valueOf(amount - bill));
                    computeBtn.setEnabled(false);
                    clearAllBtn.setEnabled(false);
                    computeChangeBtn.setEnabled(false);
                    computeChangeBtn.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Kulang Bayad Nyo!");
                }

            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if(e.getSource() == clearAllBtn){
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear?", "Confirm", 
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if(confirm == JOptionPane.YES_OPTION){
                soup.setState(false); soupQuantity.setText("0");
                coffee.setState(false); coffeeQuantity.setText("0");
                siopao.setState(false); soupQuantity.setText("0");
                totalBill.setText("");
                amountGiven.setText("");
                change.setText("");
            }
        } else{
            System.exit(0);
        }
    }

    public void itemStateChanged(ItemEvent e){
        if(soup.getState()){
            soupQuantity.setText("1"); 
            soupQuantity.setEditable(true);
        } else {
            soupQuantity.setText("0"); 
            soupQuantity.setEditable(false);
        }

        if(coffee.getState()){
            coffeeQuantity.setText("1");
            coffeeQuantity.setEditable(true);
        } else {
            coffeeQuantity.setText("0");
            coffeeQuantity.setEditable(false);
        }

        if(siopao.getState()){
            siopaoQuantity.setText("1");
            siopaoQuantity.setEditable(true);
        } else {
            siopaoQuantity.setText("0");
            siopaoQuantity.setEditable(false);
        }
    }

}