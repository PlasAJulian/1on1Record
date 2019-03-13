package pkg1vs1;
import java.sql.*;
/**
 *
 * @author Julian A Plasencia
 */
public class ScoreR {
        //variables used
    public int id;
    public String p1;
    public int p1S;
    public String p2;
    public int p2S;
    public RecordList list = new RecordList();
    //delfaut variables
    public ScoreR(){
        id = 0;
        p1 = "";
        p1S = 0;
        p2 = "";
        p2S = 0;
    }
    //getter and setter for variables
    public int getId(){
        return id;
    }
    public void setId(int i){
        id = i;
    }
    
    public String getPlayer1(){
        return p1;
    }
    public void setPlayer1(String p){
        p1 = p;
    }
    
    public int getPlayer1S(){
        return p1S;
    }
    public void setPlayer1S(int pS){
        p1S = pS;
    }
    
    public String getPlayer2(){
        return p2;
    }
    public void setPlayer2(String pp){
        p2 = pp;
    }
    
    public int getPlayer2S(){
        return p2S;
    }
    public void setPlayer2S(int ppS){
        p2S = ppS;
    }
    //get last entered id used to pull the id of the new entry in the database.
    public void getUnusedID(){
        try{
		//load driver
            String file = "jdbc:ucanaccess://C:/Users/GhostBit/Desktop/git/1on1record/database/playerDB.mdb";
            Connection con = DriverManager.getConnection(file);
		//statement
            Statement stmt = con.createStatement();
            String sql;
            sql = ("SELECT ID FROM PlayerS Where ID = (SELECT MAX(ID) FROM PlayerS)");
            System.out.println(sql);
            ResultSet rs;
		//excute
            rs = stmt.executeQuery(sql);
		//process
            while(rs.next()){
                id = rs.getInt("ID");
		}
		//close
                con.close();
	}
	catch(SQLException e){
            System.out.println("PP: "+e);
        }
        id = id + 1;
    }
    //display in console for testing
    public void display(){
        System.out.println("ID: " +id);
        System.out.println("Player one: "+ p1);
        System.out.println("Player one's score: "+p1S);
        System.out.println("Player two: "+p2);
        System.out.println("Player two's: "+p2S);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
    }
    //pulls all records in database
    public void dbRecords(){
         try{
             //load driver
            String file = "jdbc:ucanaccess://C:/Users/GhostBit/Desktop/git/1on1record/database/playerDB.mdb";
            Connection con = DriverManager.getConnection(file);
            //statement
            Statement stmt = con.createStatement();
            String sql;
            sql = ("Select ID From PlayerS");
            System.out.println(sql);
            ResultSet rs;
            //excute
            rs = stmt.executeQuery(sql);
            int i;
            ScoreR r1;
            //process, loops to add object to array list
            while(rs.next()){
                i = rs.getInt(1);
                r1 = new ScoreR();
                r1.singleR(i);
                list.addToRList(r1);
            }
            con.close();
         }
         catch(SQLException e){
             System.out.println("PP: "+e);
         }
    }
    //gets single record from database
    public void singleR(int i){
        try{
            //load driver
            String file = "jdbc:ucanaccess://C:/Users/GhostBit/Desktop/git/1on1record/database/playerDB.mdb";
            Connection con = DriverManager.getConnection(file);
            //statement
            Statement stmt = con.createStatement();
            String sql;
            sql = ("Select ID, Player1, p1Score, Player2, p2Score From PlayerS Where ID ='"+(i)+"'");
            System.out.println(sql);
            ResultSet rs;
            //excute
            rs = stmt.executeQuery(sql);
            //process
            while(rs.next() ){
                id = i;
                p1 = rs.getString("Player1");
                p1S = rs.getInt("p1Score");
                p2 = rs.getString("Player2");
                p2S = rs.getInt("p2Score");
            }
            con.close();
        }
        catch(SQLException e){
            System.out.println("PP: "+e);
        }
    }
    //inserts new record to database
    public void insertR(String Pl1, int Pl1s, String Pl2, int Pl2s){
        try{
            getUnusedID();
            //load driver
            String file = "jdbc:ucanaccess://C:/Users/GhostBit/Desktop/git/1on1record/database/playerDB.mdb";
            Connection con = DriverManager.getConnection(file);
            //statement
            Statement stmt = con.createStatement();
            String sql;
            sql = ("INSERT INTO PlayerS (Player1, p1Score, Player2, p2Score)"+
                    "VALUES ('"+Pl1+"',"
                    + " "+Pl1s+","
                    + " '"+Pl2+"',"
                    + " "+Pl2s+");");
            System.out.println(sql);
            //excute
            stmt.executeUpdate(sql);
            System.out.println("You added somthing, i hope your happy");
            //set values in the object
            p1 = Pl1;
            p1S = Pl1s;
            p2 = Pl2;
            p2S = Pl2s;
            con.close();
        }
        catch(SQLException e){
            System.out.println("PP: "+e);
        }
    }
    //updates a single record in database.
    public void updateR(){
        try{
            //load driver
            String file ="jdbc:ucanaccess://C:/Users/GhostBit/Desktop/git/1on1record/database/playerDB.mdb";
            Connection con = DriverManager.getConnection(file);
            //statement
            Statement stmt = con.createStatement();
            String sql;
            sql = ("UPDATE PlayerS set Player1 = '"+p1+
                    "', p1Score = "+p1S+
                    ", Player2 = '"+p2+
                    "', p2Score = "+p2S+
                    " Where ID = '"+id+"'");
            System.out.println(sql);
            //excute
            stmt.executeUpdate(sql);
            System.out.println("Row has been updated, I hope.");
            //close
            con.close();
        }
        catch(SQLException e){
            System.out.println("PP: "+e);
        }
    }
    //main for testing
    public static void main(String[] args){
        ScoreR o = new ScoreR();
        o.dbRecords();
        o.list.display();
    }
}
