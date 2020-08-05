import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Storage extends AbstractListModel<String> {

    ArrayList<String> polyStore =new ArrayList<>();
    Component component;

    public Storage(Component c){
        component =c;
        polyStore.add("-x^3");
        polyStore.add("x^3");
        polyStore.add("x^2");


    }
    public void add(String s){
        if(polyStore.contains(s)){
            JOptionPane.showMessageDialog(component,s+" EXISTS",
                    "ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        polyStore.add(s);
        fireIntervalAdded(this,0,polyStore.size());
    }
    public void remove(String s){
        if(!polyStore.contains(s)){
            JOptionPane.showMessageDialog(component,"NO"+ s +"IN THE LIST",
                    "ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        polyStore.remove(s);
        fireIntervalRemoved(this,0,polyStore.size());
    }

    @Override
    public int getSize() {
        return polyStore.size();
    }

    @Override
    public String getElementAt(int index) {

        return polyStore.get(index);


    }
}
