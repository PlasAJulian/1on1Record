package pkg1vs1;
import gui.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Julian A Plasencia
 */
public class Main {

    public static void main(String[] args) {
        //creates table window and creates object and uses method to pull all records from database
        PlayerTb n = new PlayerTb();
        ScoreR t = new ScoreR();
        t.dbRecords();
        //loops array list to add each object and its data to the table.
        for(int i = 0; i < t.list.records.size(); i++){
            int x = t.list.records.get(i).getId();
            String p = t.list.records.get(i).getPlayer1();
            int p1 = t.list.records.get(i).getPlayer1S();
            String pp = t.list.records.get(i).getPlayer2();
            int p2 = t.list.records.get(i).getPlayer2S();
            Object[] ROW = { x, p, p1, pp, p2 };
            DefaultTableModel model = (DefaultTableModel) n.jTable1.getModel();
            model.addRow(ROW);
        }
        //shows new window and prevent resizing window
        n.setVisible(true);
        n.setResizable(false);
    }
}
