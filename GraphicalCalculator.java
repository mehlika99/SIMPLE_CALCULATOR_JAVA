import javax.swing.*;
import javax.swing.border.LineBorder;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class GraphicalCalculator extends JPanel  {


    static JList<String> list;


    Storage model;
    JButton btncolor;
    Color grapcol;
    String s;
    JCheckBox check;
    Graphics2D grap2d;
    int i ;



    int range = 60;
    int[] array = new int[2 * range];


    public GraphicalCalculator() {

        //*****THE TOP OF MAIN*****

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 630));


        //*****BOTTOM PANEL*****

        JPanel bottPan = new JPanel();

        bottPan.setPreferredSize(new Dimension(600, 235));

        bottPan.setBorder(new LineBorder(Color.BLUE, 10));
        bottPan.setLayout(new BorderLayout());
        bottPan.setBackground(Color.WHITE);



        //*****COLOR VISIBILITY AND LIST PANEL*****

        JPanel top = new JPanel();

        top.setPreferredSize(new Dimension(100, 90));
        top.setLayout(new GridLayout(1, 3, 20, 20));

        model = new Storage(this);
        list = new JList<>(model);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Serif", Font.BOLD, 16));



        JScrollPane scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        check = new JCheckBox("Visibility :  ");
        check.setFont(new Font("Serif", Font.BOLD, 18));
        check.setForeground(Color.BLUE);
        check.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        check.setBackground(Color.WHITE);
        check.setSelected(true);




        check.addActionListener(new ActionListener() {//ACTIVATE VISIBILTY BUTTON
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check.isSelected()){
                    repaint();
                }
            }
        });

        i = list.getSelectedIndex();

        btncolor = new JButton("COLOR");
        btncolor.setPreferredSize(new Dimension(75, 50));
        btncolor.addActionListener((e) ->
        {

            grapcol = JColorChooser.showDialog(
                    GraphicalCalculator.this,
                    "",
                    null
            );

        });


        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (check.isSelected()) {
                    s = list.getSelectedValue();
                }
            }
        });

        top.add(check);
        top.add(scroll);
        top.add(btncolor);



        //****REMOVE AND ADD PART*****
        JPanel bottom = new JPanel();


        bottom.setBackground(Color.WHITE);
        bottom.setLayout(new FlowLayout());

        JButton addbutton = new JButton("ADD");
        JButton deletebutton = new JButton("REMOVE");


        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GET INFO FROM THE USER
                s = JOptionPane.showInputDialog(bottPan, "ENTER POLYNOMIAL",
                        "POLYNOMIAL", JOptionPane.QUESTION_MESSAGE);
                if(s.contains("^-")){
                    JOptionPane.showMessageDialog(bottPan,s+" IS NOT A POLYNOMIAL",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }else {
                    model.add(s);
                }
                repaint();


            }
        });
        addbutton.setBackground(Color.WHITE);


        deletebutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.isSelectionEmpty())
                    return;
                String i = list.getSelectedValue();
                model.remove(i);

                s="";

                repaint();
            }
        });
        deletebutton.setBackground(Color.WHITE);

        bottom.add(deletebutton);
        bottom.add(addbutton);

        bottPan.add(top, BorderLayout.NORTH);
        bottPan.add(bottom, BorderLayout.SOUTH);

        add(bottPan, BorderLayout.SOUTH);

        s="x^3";

    }


    public void paintComponent(Graphics g) {

        grap2d = (Graphics2D) g;
        grap2d.setRenderingHint(
                RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
        grap2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        grap2d .setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        super.paintComponent(g);

        grap2d.setColor(Color.BLUE);

        grap2d.setStroke(new BasicStroke(4));
        grap2d.drawLine(getWidth() / 2, 20, getWidth() / 2, getHeight() - 260);// 300 ,300 , y exsent

        grap2d.setFont(new Font("SansSerif", Font.BOLD, 17));
        grap2d.drawString("Y", getWidth() / 2 + 8, 30);


        grap2d.drawLine(40, getHeight() / 2 - 120, getWidth() - 40, getHeight() / 2 - 120);//x exsent
        grap2d.drawString("X", getWidth() - 50, getHeight()/2-135);
        grap2d.setFont(new Font("SansSerif", Font.BOLD, 25));

        grap2d.drawString("^", getWidth() / 2 - 7, 32);
        grap2d.drawString("v", getWidth() / 2 - 8, getHeight() - 255);
        grap2d.drawString(">", getWidth() - 49, getHeight() / 2 - 111);
        grap2d.drawString("<", 36, getHeight() / 2 - 111);

        int index = 0;

        if(s.contains("^-") || s.equals("")){
            range =0;
        }else{

            for (int x = -range; x < range; x++) {

                int res = function(x);
                array[index] = res;
                index++;
            }
        }
        if(check.isSelected()){
            for (int i = -range; i < range; i++) {
                if (i + range + 1 < 2 * range) {
                    grap2d.setColor(Color.MAGENTA);


                    grap2d.setColor(grapcol);
                    grap2d.draw(new Line2D.Double(getWidth() / 2 + i,
                            getHeight() / 2 - array[i + range] - 120,
                            getWidth() / 2 + i + 1,
                            getHeight() / 2 - array[i + range + 1] - 120));

                    repaint();

                } else {
                    repaint();
                }

            }
        }
    }


    public int function(int x){

        int ust=0;
        int kat=0;

        s = s.replace("-", "+-");
        String term[] = s.split("\\+");
        int value[] = new int[term.length];
        int y= 0;
        for (int t = 0; t < term.length; t++) {
            if(term[t]!= ""){

                if (!term[t].contains("x")) {
                    ust = 0;
                    try {
                        kat = Integer.parseInt(term[t]);
                    }catch (Exception e){
                        kat=0;
                    }
                } else if (!term[t].contains("^")) {
                    ust = 1;
                    try {
                        kat = Integer.parseInt(term[t].substring(0, term[t].indexOf("x")));


                    }catch (Exception e) {
                        if (term[t].substring(0, term[t].indexOf("x")).contains("-")) {
                            kat = -1;
                        } else {
                            kat = 1;
                        }
                    }

                } else {

                    try {
                        kat = Integer.parseInt(term[t].substring(0, term[t].indexOf("x")));


                    }catch (Exception e){
                        String a = term[t].substring(0, term[t].indexOf("x"));
                        if(a.contains("-")){
                            kat=-1;
                        }else {
                            kat =1;
                        }
                    }finally {
                        if (term[t].contains("^"))
                            ust = Integer.parseInt(term[t].substring(term[t].indexOf("^") + 1));
                        else
                            ust=1;
                    }

                }
            }
            value[t] = kat;
            for (int i = 0; i < ust; i++) {
                value[t] =value[t] * x;
            }
        }

        for(int a= 0; a <value.length ;a++){
            y=y+value[a];
        }

        return y;
    }


}

