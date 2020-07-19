import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EndangeredSighting extends Animal implements Sighting {
    private int id;
    private int AnimalId;
    private String location;
    private String rangerName;
    private Timestamp dateSighted;


    public EndangeredSighting(int AnimalId, String location, String rangerName) {
        this.id = id;
        this.AnimalId = AnimalId;
        this.location = location;
        this.rangerName = rangerName;
        dateSighted  = new Timestamp(new Date().getTime());

    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateSighted() {
        return dateSighted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndangeredSighting)) return false;
        EndangeredSighting sighting = (EndangeredSighting) o;
        return getId() == sighting.getId() &&
                getAnimalId() == sighting.getAnimalId() &&
                getLocation().equals(sighting.getLocation()) &&
                getRangerName().equals(sighting.getRangerName()) &&
                getDateSighted().equals(sighting.getDateSighted());
    }

    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO communities (name, description) VALUES (:name, :description)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM communities WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
            String joinDeleteQuery = "DELETE FROM Singtings_rangerss WHERE animal_id = :communityId";
            con.createQuery(joinDeleteQuery)
                    .addParameter("animalId", this.getId())
                    .executeUpdate();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnimalId(), getLocation(), getRangerName(), getDateSighted());
    }

    public void setDateSpotted(Timestamp dateSpotted) {
        this.dateSighted = dateSpotted;
    }
    public int getAnimalId() {
        return AnimalId;
    }

    public void setAnimalId(int animalId) {
        AnimalId = animalId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }

    public void saveSighting(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (location, rangerName, animalId, dateSpotted) VALUES (:location, :rangerName, :animalId, :dateSpotted)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("dateSpotted", this.dateSighted)
                    .addParameter("animalId", this.AnimalId)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println("found "+ex);
        }

    }

    public static List<EndangeredSighting> getSightings(){
        String sql = "SELECT * FROM sightings;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredSighting.class);
        }
    }

    public int getId(){
        return id;
    }

    public static EndangeredSighting find(int findId) {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE id=:id;")
                    .addParameter("id",findId)
                    .executeAndFetchFirst(EndangeredSighting.class);
        }
    }

    public static List<Animal> getAll(){
        String sql = "SELECT id, name FROM animals;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Animal.class);
        }
    }


}