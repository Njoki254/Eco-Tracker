import org.sql2o.*;

import java.util.Collection;
import java.util.List;

public class Ranger {

    private String email;
    private String name;
    private int id;



    public Ranger(String name, String email){
        this.name = name;
        this.email = email;

    }

    public String getName(){
        return name;

    }
    public String getEmail(){
        return email;

    }
    public int getId() {
        return id;
    }
    /*
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (name, email) VALUES (:name, :email)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .executeUpdate();
        }
    }*/
    //save was altered to ensure even the databased-assigned ID is gathered;
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO Ranger (name, email) VALUES (:name, :email)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Ranger> all() {
        String sql = "SELECT * FROM persons";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    //Override helps to prevent errors when working with interfaces and inheritances
    @Override
    public boolean equals(Object otherRanger){
        if (!(otherRanger instanceof Ranger)) {
            return false;
        } else {
            Ranger newRanger = (Ranger) otherRanger;
            return this.getName().equals(newRanger.getName()) &&
                    this.getEmail().equals(newRanger.getEmail());
        }
    }


}
