import javax.swing.*;
 class Main extends JFrame  {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                ()->{ new Main();}
        );
    }
    public Main(){
        super("Graphical Calculator");


        GraphicalCalculator graph =new GraphicalCalculator();
        SimpleCalculator simple = new SimpleCalculator();

        JTabbedPane tab= new JTabbedPane();
        tab.addTab("Simple Calculator",simple);
        tab.addTab("Graphical Calculator",graph);

        add(tab);
        repaint();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



    }

}
