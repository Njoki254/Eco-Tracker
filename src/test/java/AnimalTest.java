import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest{

   @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("Bubbles", 1);
        assertEquals(true, testAnimal instanceof Animal);
    }
    @Test
    public void Animal_instantiatesWithName_String() {
        Animal testAnimal = new Animal("Bubbles", 1);
        assertEquals("Bubbles", testAnimal.getSpecies());
    }
    @Test
    public void Animal_instantiatesWithPersonId_int() {
        Animal testMonster = new Animal("Bubbles", 1);
        assertEquals(1, testMonster.getAnimalId());
    }
    @Test
    public void equals_returnsTrueIfNameAndPersonIdAreSame_true() {
        Animal testAnimal = new Animal("Bubbles", 1);
        Animal anotherAnimal = new Animal("Bubbles", 1);
        assertTrue(testAnimal.equals(anotherAnimal));
    }
    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        Animal testAnimal = new Animal("Bubbles", 1);
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }
    @Test
    public void save_assignsIdToAnimal() {
        Animal testAnimal = new Animal("Bubbles", 1);
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(savedAnimal.getId(), testAnimal.getId());
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal firstAnimal = new Animal("Bubbles", 1);
        firstAnimal.save();
        Animal secondAnimal = new Animal("Spud", 1);
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }
    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = new Animal("Bubbles", 1);
        firstAnimal.save();
        Animal secondAnimal = new Animal("Spud", 3);
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }
    //test to ensure the connection between a ranger and an animal
    @Test
    public void save_savesRangerIdIntoDB_true() {
        Ranger testRanger = new Ranger("Henry", "henry@henry.com");
        testRanger.save();
        Animal testAnimal = new Animal("Bubbles", testRanger.getId());
        testAnimal.save();
        Animal savedAnimal = Animal.find(testAnimal.getId());
        assertEquals(savedAnimal.getAnimalId(), testRanger.getId());
    }

}