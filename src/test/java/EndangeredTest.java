import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class EndangeredTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Endangered_instantiatesCorrectly_true() {
        Endangered testEndangered = new Endangered("Bubbles", 1,15,5);
        assertEquals(true, testEndangered instanceof Endangered);
    }
    @Test
    public void Endangered_instantiatesWithName_String() {
        Endangered testEndangered = new Endangered("Bubbles", 1,15,5);
        assertEquals("Bubbles", testEndangered.getSpecies());
    }
    @Test
    public void Endangered_instantiatesWithPersonId_int() {
        Endangered testMonster = new Endangered("Bubbles", 1,15,5);
        assertEquals(1, testMonster.getAnimalId());
    }
    @Test
    public void equals_returnsTrueIfNameAndPersonIdAreSame_true() {
        Endangered testEndangered = new Endangered("Bubbles", 1,15,5);
        Endangered anotherEndangered = new Endangered("Bubbles", 1,15,5);
        assertTrue(testEndangered.equals(anotherEndangered));
    }
    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        Endangered testEndangered = new Endangered("Bubbles", 1,15,5);
        testEndangered.save();
        assertTrue(Endangered.all().get(0).equals(testEndangered));
    }
    @Test
    public void save_assignsIdToEndangered() {
        Endangered testEndangered = new Endangered("Bubbles", 1,15,5);
        testEndangered.save();
        Endangered savedEndangered = Endangered.all().get(0);
        assertEquals(savedEndangered.getId(), testEndangered.getId());
    }
    @Test
    public void all_returnsAllInstancesOfEndangered_true() {
        Endangered firstEndangered = new Endangered("Bubbles", 1,15,5);
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("Bubbles", 1,15,5);
        secondEndangered.save();
        assertEquals(true, Endangered.all().get(0).equals(firstEndangered));
        assertEquals(true, Endangered.all().get(1).equals(secondEndangered));
    }
    @Test
    public void find_returnsEndangeredWithSameId_secondEndangered() {
        Endangered firstEndangered = new Endangered("Bubbles", 1,15,5);
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("Bubbles", 1,15,5);
        secondEndangered.save();
        assertEquals(Endangered.find(secondEndangered.getId()), secondEndangered);
    }
    //test to ensure the connection between a ranger and an Endangered
   /* @Test
    public void save_savesRangerIdIntoDB_true() {
        Ranger testRanger = new Ranger("Henry", "henry@henry.com");
        testRanger.save();
        Endangered testEndangered = new Endangered("Bubble, testRanger.getId());
        testEndangered.save();
        Endangered savedEndangered = Endangered.find(testEndangered.getId());
        assertEquals(savedEndangered.getAnimalId(), testRanger.getId());
    }*/
    @Test
    public void monster_instantiatesWithHalfFullPlayLevel(){
        Endangered testEndangered= new Endangered("Bubbles", 1,15,5);
        assertEquals(testEndangered.getHealthLevel(), (Endangered.MAX_HEALTH_LEVEL / 2));
    }
    @Test
    public void isAlive_confirmsEndangeredIsAliveIfAllLevelsAboveMinimum_true(){
        Endangered testEndangered = new Endangered("Bubbles", 1,15,5);
        assertEquals(testEndangered.isAlive(), true);
    }
    @Test
    public void monster_foodLevelCannotGoBeyondMaxValue(){
        Endangered testEndangered = new Endangered("Bubbles", 1,15,5);
        for(int i = Endangered.MIN_HEALTH_LEVEL; i <= (Endangered.MAX_HEALTH_LEVEL); i++){
            testEndangered.healthInput();
        }
        assertTrue(testEndangered.getHealthLevel() <= Endangered.MAX_HEALTH_LEVEL);
    }

}