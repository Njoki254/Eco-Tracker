import org.sql2o.Connection;

import java.util.List;

public class Endangered extends Animal{


    private int age;
    private int healthLevel;

    public static final int MAX_HEALTH_LEVEL = 10;
    public static final int MIN_HEALTH_LEVEL = 0;



    public Endangered(String species, int animalId, int age, int healthLevel) {

        this.species = species;
        this.animalId = animalId;
        this.id= id;
        this.age = age;
        this.healthLevel = healthLevel;
    }
    public static List<Endangered> all() {
        String sql = "SELECT * FROM animals;";
        try(Connection con = DB.sql2o.open()) {
            return ((Connection) con).createQuery(sql).executeAndFetch(Endangered.class);
        }
    }
    public static Endangered find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM monsters where id=:id";
            Endangered animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Endangered.class);
            return animal;
        }
    }
    public boolean isAlive() {
        if (healthLevel <= MIN_HEALTH_LEVEL) {
            return false;
        }
        return true;
    }
    public void healthInput(){
        if (healthLevel >= MAX_HEALTH_LEVEL){
            throw new UnsupportedOperationException("The animal's health rating cannot exceed 10");
        }
        healthLevel++;
    }


    public int getHealthLevel() {
        return healthLevel;
    }
  /*  @Override
    public boolean isAlive() {
        if (healthLevel <= MAX_HEALTH_LEVEL) {
            return false;
        }
        return true;
    }*/
}
