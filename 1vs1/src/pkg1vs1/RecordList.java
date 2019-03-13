package pkg1vs1;

import java.util.ArrayList;

/**
 *
 * @author Julian A Plasencia
 */
//list of records from database.
public class RecordList {
    public int count = 0;
    public ArrayList<ScoreR> records = new ArrayList<ScoreR>();
    //adds object to list
    public void addToRList(ScoreR r){
        records.add(r);
        count++;
    }
    //uses object's display method to display in console
    public void display(){
        for(ScoreR r : records){
            r.display();
        }
    }
}
