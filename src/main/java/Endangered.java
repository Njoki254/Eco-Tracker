public class Endangered extends Animal{


    private int age;
    private int healthlevel;

    public static final int MAX_HEALTH_LEVEL = 10;



    public Endangered(String species, int animalId, int age, int healthlevel) {
        super();
        this.species = species;
        this.animalId = animalId;
        this.id= id;
        this.age = age;
        this.healthlevel = healthlevel;
    }
    public int getHealthLevel(){
        return healthlevel;
    }
}
