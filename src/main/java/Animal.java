import org.sql2o.Connection;

import java.util.List;

public abstract class Animal {

    public String species;
    public int animalId;
    public int id;
    public Animal (String name, int id){
        this.species = name;
        this.animalId = animalId;

    }

    public String getSpecies() {
        return species;
    }

    public int getAnimalId() {
        return animalId;
    }
    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getSpecies().equals(newAnimal.getSpecies()) &&
                    this.getAnimalId() == newAnimal.getAnimalId();
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO monsters (name, personid) VALUES (:name, :personId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.species)
                    .addParameter("personId", this.animalId)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Animal> all() {
        String sql = "SELECT * FROM monsters";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }
    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM monsters where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }


}
