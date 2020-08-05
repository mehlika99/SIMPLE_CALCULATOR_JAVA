import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SimpleCalculator extends JPanel implements ActionListener{
    private static final long serialVersionUID=1L;
    public static final int WIDTH =600;
    public static final int HEIGHT=630;

    private GridBagLayout layout;
    private GridBagConstraints gbc;

    private JButton[] numberButtons;
    private JButton[] otherButtons;
    private JToggleButton[] numericButtons;//hex,dec,oct
    private JButton[] chButtons;//A,D,E,F

    private JTextField field;


    private int num1,num2;
    private int operation;

    int radix=10;
    private double dnum1,dnum2;



    //[0]=gridx, [1]=gridy ,[2]=gridwithd, [3]=gridheight
    private int[][] numConstraints=new int[][]{//buttons postion here
            {3,5,1,1},//button zero
            {2,4,1,1},
            {3,4,1,1},
            {4,4,1,1},
            {2,3,1,1},
            {3,3,1,1},
            {4,3,1,1},
            {2,2,1,1},
            {3,2,1,1},
            {4,2,1,1},

    };
    private int[][] otherConstrains=new int[][]{//we give operations buttons postion here
            {4,5,1,1},
            {5,5,1,1},
            {5,3,1,1},
            {2,1,1,1},
            {3,1,1,1},
            {4,1,1,1},
            {5,1,1,1},
            {5,2,1,1},
            {2,5,1,1},
            {5,4,1,1},

    };
    private int[][] numericConstrains=new int[][]{
            {0,4,1,1},
            {1,4,1,1},
            {0,5,1,1},
            {1,5,1,1},
    } ;
    private int[][] chConstrains=new int[][]{
            {0,1,1,1},
            {1,1,1,1},
            {0,2,1,1},
            {1,2,1,1},
            {0,3,1,1},
            {1,3,1,1},
    };



    public  SimpleCalculator(){

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        layout=new GridBagLayout();
        layout.columnWidths=new int[] {80,80,80,80};//we have 4 column//80,80,80,80
        layout.rowHeights=new int[] {80,80,80,80,80,80};//we have 6 row
        setLayout(layout);

        gbc=new GridBagConstraints();

        numberButtons=new JButton[10];

        for(int i=0;i<numberButtons.length;i++){
            numberButtons[i]=new JButton(""+ i);
            numberButtons[i].addActionListener(this);


            gbc.gridx=numConstraints[i][0];
            gbc.gridy=numConstraints[i][1];
            gbc.gridwidth=numConstraints[i][2];
            gbc.gridheight=numConstraints[i][3];
            gbc.fill=GridBagConstraints.BOTH;
            gbc.insets=new Insets(2,2,2,2);


            add(numberButtons[i],gbc);
        }

        otherButtons=new JButton[10];
        otherButtons[0]=new JButton(".");//decimal buttons
        otherButtons[1]=new JButton("=");
        otherButtons[2]=new JButton("%");
        otherButtons[3]=new JButton("+");
        otherButtons[4]=new JButton("-");
        otherButtons[5]=new JButton("*");
        otherButtons[6]=new JButton("/");
        otherButtons[7]=new JButton("√");
        otherButtons[8]=new JButton("AC");
        otherButtons[9]=new JButton("DEL");

        for(int i=0;i<otherButtons.length;i++){
            gbc.gridx=otherConstrains[i][0];
            gbc.gridy=otherConstrains[i][1];
            gbc.gridwidth=otherConstrains[i][2];
            gbc.gridheight=otherConstrains[i][3];

            otherButtons[i].addActionListener(this);

            add(otherButtons[i],gbc);
        }

        numericButtons=new JToggleButton[4];
        numericButtons[0]=new JToggleButton("BIN");
        numericButtons[1]=new JToggleButton("OCT");
        numericButtons[2]=new JToggleButton("DEC");
        numericButtons[3]=new JToggleButton("HEX");

        for(int i=0;i<numericButtons.length;i++){
            gbc.gridx=numericConstrains[i][0];
            gbc.gridy=numericConstrains[i][1];
            gbc.gridwidth=numericConstrains[i][2];
            gbc.gridheight=numericConstrains[i][3];

            numericButtons[i].addActionListener(this);

            add(numericButtons[i],gbc);

        }

        chButtons=new JButton[6];
        chButtons[0]=new JButton("A");
        chButtons[1]=new JButton("B");
        chButtons[2]=new JButton("C");
        chButtons[3]=new JButton("D");
        chButtons[4]=new JButton("E");
        chButtons[5]=new JButton("F");

        for(int i=0;i<chButtons.length;i++){
            gbc.gridx=chConstrains[i][0];
            gbc.gridy=chConstrains[i][1];
            gbc.gridwidth=chConstrains[i][2];
            gbc.gridheight=chConstrains[i][3];

            chButtons[i].addActionListener(this);

            add(chButtons[i],gbc);

        }
        chButtons[0].setEnabled(false);
        chButtons[1].setEnabled(false);
        chButtons[2].setEnabled(false);
        chButtons[3].setEnabled(false);
        chButtons[4].setEnabled(false);
        chButtons[5].setEnabled(false);


        field=new JTextField();
        field.setEditable(false);
        field.setFont(new Font("Sans_Serif",Font.PLAIN,80));
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=6;
        gbc.gridheight=1;

        add(field,gbc);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<numberButtons.length;i++){
            if(e.getSource()==numberButtons[i]){
                field.setText(field.getText()+i);
            }
        }
        if(e.getSource()==otherButtons[0] && !field.getText().contains(".")){
            field.setText(field.getText()+".");
            field.requestFocus();
        }
        if(e.getSource()==otherButtons[7]){//"√" buttom

            double square =Double.parseDouble(field.getText());
            if(square > 0) {
                double newsq = Math.sqrt(square);
                field.setText(""+newsq);
                field.requestFocus();


            }else {
                field.setText("error!");
                field.requestFocus();
            }
        }
        if(e.getSource()==otherButtons[8]){//AC
            field.setText("");
            field.requestFocus();

        }
        if(e.getSource()==otherButtons[2]){//(%) button
//
            operation = 1;
            if(radix==10){
                dnum1= Double.parseDouble(field.getText());
            }else{
                num1 = Integer.parseInt(field.getText(),radix);
            }
            field.setText("");
        }
        if(e.getSource()==otherButtons[3]) {//for addition (+) button

            operation = 2;
            if(radix==10){
                dnum1= Double.parseDouble(field.getText());
            }else{
                num1 = Integer.parseInt(field.getText(),radix);
            }
            field.setText("");
        }
        if(e.getSource()==otherButtons[4]) {//for  (-) button

            operation = 3;
            if(radix==10){
                dnum1= Double.parseDouble(field.getText());
            }else{
                num1 = Integer.parseInt(field.getText(),radix);
            }
            field.setText("");
        }
        if(e.getSource()==otherButtons[5]) {//for  (*) button

            operation = 4;
            if(radix==10){
                dnum1= Double.parseDouble(field.getText());
            }else{
                num1 = Integer.parseInt(field.getText(),radix);
            }
            field.setText("");
        }
        if(e.getSource()==otherButtons[6]) {//for  (/) button

            operation = 5;
            if(radix==10){
                dnum1= Double.parseDouble(field.getText());
            }else{
                num1 = Integer.parseInt(field.getText(),radix);
            }
            field.setText("");
        }
        if(e.getSource()==otherButtons[9]){//del
            try {
                String text = field.getText();
                String delast = text.substring(0, text.length() - 1);
                field.setText(delast);
                field.requestFocus();
            }catch (Exception ex){
                field.setText("0");
            }
        }
        if(e.getSource()==chButtons[0]){
            String text=field.getText();
            if(text!=null&& !text.isEmpty()){
                if (text.charAt(0) == '0') {
                    String newchr = text.substring(1);
                    field.setText(newchr);
                }
            }
            field.setText(field.getText() + "A");
            field.requestFocus();
        }
        if(e.getSource()==chButtons[1]){
            String text=field.getText();
            if(text!=null&& !text.isEmpty()){
                if (text.charAt(0) == '0') {
                    String newchr = text.substring(1);
                    field.setText(newchr);
                }
            }
            field.setText(field.getText() + "B");
            field.requestFocus();
        }
        if(e.getSource()==chButtons[2]){
            String text=field.getText();
            if(text!=null&& !text.isEmpty()){
                if (text.charAt(0) == '0') {
                    String newchr = text.substring(1);
                    field.setText(newchr);
                }
            }
            field.setText(field.getText() + "C");
            field.requestFocus();
        }
        if(e.getSource()==chButtons[3]){
            String text=field.getText();
            if(text!=null&& !text.isEmpty()){
                if (text.charAt(0) == '0') {
                    String newchr = text.substring(1);
                    field.setText(newchr);
                }
            }
            field.setText(field.getText() + "D");
            field.requestFocus();
        }
        if(e.getSource()==chButtons[4]){
            String text=field.getText();
            if(text!=null&& !text.isEmpty()){
                if (text.charAt(0) == '0') {
                    String newchr = text.substring(1);
                    field.setText(newchr);
                }
            }
            field.setText(field.getText() + "E");
            field.requestFocus();
        }
        if(e.getSource()==chButtons[5]){
            String text=field.getText();
            if(text!=null&& !text.isEmpty()){
                if (text.charAt(0) == '0') {
                    String newchr = text.substring(1);
                    field.setText(newchr);
                }
            }
            field.setText(field.getText() + "F");
            field.requestFocus();
        }


        if(e.getSource()==otherButtons[1]){//for(=)button

            if(radix==10){
                dnum2= Double.parseDouble(field.getText());
            }else{
                num2 = Integer.parseInt(field.getText(),radix);
            }
            switch (operation){
                case (1):
                    field.setText(Double.toString(dnum1*(dnum2/100)));
                    break;
                case (2):
                    if(radix==2)
                        field.setText(Integer.toBinaryString(num1+num2));
                    else if(radix==8)
                        field.setText(Integer.toOctalString(num1+num2));
                    else if(radix==16)
                        field.setText(Integer.toHexString(num1+num2));
                    else
                        field.setText(Double.toString(dnum1+dnum2));
                    break;
                case (3):
                    if(radix==2)
                        field.setText(Integer.toBinaryString(num1-num2));
                    else if(radix==8)
                        field.setText(Integer.toOctalString(num1-num2));
                    else if(radix==16)
                        field.setText(Integer.toHexString(num1-num2));
                    else
                        field.setText(Double.toString(dnum1-dnum2));
                    break;
                case (4):
                    if(radix==2)
                        field.setText(Integer.toBinaryString(num1*num2));
                    else if(radix==8)
                        field.setText(Integer.toOctalString(num1*num2));
                    else if(radix==16)
                        field.setText(Integer.toHexString(num1*num2));
                    else
                        field.setText(Double.toString(dnum1*dnum2));
                    break;
                case (5):
                    if(radix==2)
                        field.setText(Integer.toBinaryString(num1/num2));
                    else if(radix==8)
                        field.setText(Integer.toOctalString(num1/num2));
                    else if(radix==16)
                        field.setText(Integer.toHexString(num1/num2));
                    else
                        field.setText(Double.toString(dnum1/dnum2));
                default:
                    break;

            }


            operation=0;

            field.requestFocus();

        }


        numericButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numericButtons[0].isSelected()){//bin
                    numericButtons[3].setSelected(false);
                    numericButtons[1].setSelected(false);
                    numericButtons[2].setSelected(false);
                    chButtons[0].setEnabled(false);
                    chButtons[1].setEnabled(false);
                    chButtons[2].setEnabled(false);
                    chButtons[3].setEnabled(false);
                    chButtons[4].setEnabled(false);
                    chButtons[5].setEnabled(false);
                    numberButtons[8].setEnabled(false);
                    numberButtons[9].setEnabled(false);
                    numberButtons[7].setEnabled(false);
                    numberButtons[6].setEnabled(false);
                    numberButtons[5].setEnabled(false);
                    numberButtons[4].setEnabled(false);
                    numberButtons[3].setEnabled(false);
                    numberButtons[2].setEnabled(false);
                    otherButtons[2].setEnabled(false);
                    otherButtons[7].setEnabled(false);
                    otherButtons[0].setEnabled(false);

                    field.requestFocus();
                    radix=2;

                }else{
                    chButtons[0].setEnabled(false);
                    chButtons[1].setEnabled(false);
                    chButtons[2].setEnabled(false);
                    chButtons[3].setEnabled(false);
                    chButtons[4].setEnabled(false);
                    chButtons[5].setEnabled(false);
                    numberButtons[8].setEnabled(true);
                    numberButtons[9].setEnabled(true);
                    numberButtons[7].setEnabled(true);
                    numberButtons[6].setEnabled(true);
                    numberButtons[5].setEnabled(true);
                    numberButtons[4].setEnabled(true);
                    numberButtons[3].setEnabled(true);
                    numberButtons[2].setEnabled(true);
                    otherButtons[2].setEnabled(true);
                    otherButtons[7].setEnabled(true);
                    otherButtons[0].setEnabled(true);

                }

            }
        });



        numericButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numericButtons[1].isSelected()){//oct
                    numericButtons[3].setSelected(false);
                    numericButtons[0].setSelected(false);
                    numericButtons[2].setSelected(false);
                    chButtons[0].setEnabled(false);
                    chButtons[1].setEnabled(false);
                    chButtons[2].setEnabled(false);
                    chButtons[3].setEnabled(false);
                    chButtons[4].setEnabled(false);
                    chButtons[5].setEnabled(false);
                    numberButtons[8].setEnabled(false);
                    numberButtons[9].setEnabled(false);
                    numberButtons[7].setEnabled(true);
                    numberButtons[6].setEnabled(true);
                    numberButtons[5].setEnabled(true);
                    numberButtons[4].setEnabled(true);
                    numberButtons[3].setEnabled(true);
                    numberButtons[2].setEnabled(true);
                    otherButtons[7].setEnabled(false);
                    otherButtons[2].setEnabled(false);
                    otherButtons[0].setEnabled(false);

                    field.requestFocus();
                    radix=8;

                }else{
                    chButtons[0].setEnabled(false);
                    chButtons[1].setEnabled(false);
                    chButtons[2].setEnabled(false);
                    chButtons[3].setEnabled(false);
                    chButtons[4].setEnabled(false);
                    chButtons[5].setEnabled(false);
                    numberButtons[8].setEnabled(true);
                    numberButtons[9].setEnabled(true);
                    otherButtons[7].setEnabled(true);
                    otherButtons[2].setEnabled(true);
                    otherButtons[0].setEnabled(true);

                }
            }
        });

        numericButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numericButtons[2].isSelected()){//dec
                    numericButtons[3].setSelected(false);
                    numericButtons[0].setSelected(false);
                    numericButtons[1].setSelected(false);
                    chButtons[0].setEnabled(false);
                    chButtons[1].setEnabled(false);
                    chButtons[2].setEnabled(false);
                    chButtons[3].setEnabled(false);
                    chButtons[4].setEnabled(false);
                    chButtons[5].setEnabled(false);
                    numberButtons[8].setEnabled(true);
                    numberButtons[9].setEnabled(true);
                    numberButtons[7].setEnabled(true);
                    numberButtons[6].setEnabled(true);
                    numberButtons[5].setEnabled(true);
                    numberButtons[4].setEnabled(true);
                    numberButtons[3].setEnabled(true);
                    numberButtons[2].setEnabled(true);
                    otherButtons[7].setEnabled(true);
                    otherButtons[2].setEnabled(true);
                    otherButtons[0].setEnabled(true);

                    field.requestFocus();
                    radix=10;

                }else{
                    chButtons[0].setEnabled(false);
                    chButtons[1].setEnabled(false);
                    chButtons[2].setEnabled(false);
                    chButtons[3].setEnabled(false);
                    chButtons[4].setEnabled(false);
                    chButtons[5].setEnabled(false);

                }
            }
        });

        numericButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numericButtons[3].isSelected()){//hex
                    chButtons[0].setEnabled(true);
                    chButtons[1].setEnabled(true);
                    chButtons[2].setEnabled(true);
                    chButtons[3].setEnabled(true);
                    chButtons[4].setEnabled(true);
                    chButtons[5].setEnabled(true);
                    numericButtons[2].setSelected(false);
                    numericButtons[0].setSelected(false);
                    numericButtons[1].setSelected(false);
                    numberButtons[8].setEnabled(true);
                    numberButtons[9].setEnabled(true);
                    numberButtons[7].setEnabled(true);
                    numberButtons[6].setEnabled(true);
                    numberButtons[5].setEnabled(true);
                    numberButtons[4].setEnabled(true);
                    numberButtons[3].setEnabled(true);
                    numberButtons[2].setEnabled(true);
                    otherButtons[7].setEnabled(false);
                    otherButtons[2].setEnabled(false);
                    otherButtons[0].setEnabled(false);

                    field.requestFocus();
                    radix=16;

                }else{
                    chButtons[0].setEnabled(false);
                    chButtons[1].setEnabled(false);
                    chButtons[2].setEnabled(false);
                    chButtons[3].setEnabled(false);
                    chButtons[4].setEnabled(false);
                    chButtons[5].setEnabled(false);
                    otherButtons[7].setEnabled(true);
                    otherButtons[2].setEnabled(true);
                    otherButtons[0].setEnabled(true);
                }
            }
        });



    }
}
