import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

import java.util.Arrays;

public class RangerTest {
   @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void ranger_instantiatesCorrectly_true() {
        Ranger testRanger = new Ranger("Henry", "[email protected] (Links to an external site.)");
        assertEquals(true, testRanger instanceof Ranger);
    }
    @Test
    public void getName_rangerInstantiatesWithName_Henry() {
        Ranger testRanger = new Ranger("Henry", "[email protected] (Links to an external site.)");
        assertEquals("Henry", testRanger.getName());
    }

    @Test
    public void getEmail_rangerInstantiatesWithEmail_String() {
        Ranger testRanger = new Ranger("Henry", "[email protected] (Links to an external site.)");
        assertEquals("[email protected] (Links to an external site.)", testRanger.getEmail());
    }
    @Test
    public void equals_returnsTrueIfNameAndEmailAreSame_true() {
        Ranger firstRanger = new Ranger("Henry", "[email protected] (Links to an external site.)");
        Ranger anotherRanger = new Ranger("Henry", "[email protected] (Links to an external site.)");
        assertTrue(firstRanger.equals(anotherRanger));
    }
    //test whether ranger is saved into database
    //save relies on all method
    @Test
    public void save_insertsObjectIntoDatabase_Person() {
        Ranger testRanger = new Ranger("Henry", "[email protected] (Links to an external site.)");
        testRanger.save();
        assertTrue(Ranger.all().get(0).equals(testRanger));
    }
    @Test
    public void all_returnsAllInstancesOfPerson_true() {
        Ranger firstRanger = new Ranger("Henry", "henry@henry.com");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Harriet", "harriet@harriet.com");
        secondRanger.save();
        assertEquals(true, Ranger.all().get(0).equals(firstRanger));
        assertEquals(true, Ranger.all().get(1).equals(secondRanger));
    }
    @Test
    public void save_assignsIdToObject() {
        Ranger testRanger = new Ranger("Henry", "henry@henry.com");
        testRanger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(testRanger.getId(), savedRanger.getId());
    }
    //test for function returning all animal objects sighted by one single ranger
  /*  @Test
    public void getAnimal_retrievesAllAnimalsFromDatabase_animalsList() {
        Ranger testRanger = new Ranger("Henry", "henry@henry.com");
        testRanger.save();
        Endangered firstEndangered = new Endangered("Bubbles", testRanger.getId());
        firstEndangered.save();
        Endangered secondEndangered = new Animal("Spud", testRanger.getId());
        secondEndangered.save();
        Animal[] animals = new Animal[] { firstEndangered, secondEndangered };
        assertTrue(testRanger.getEndangered().containsAll(Arrays.asList(animals)));
    }*/

}