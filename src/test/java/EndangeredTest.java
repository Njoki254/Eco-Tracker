import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndangeredTest{
    @Test
    public void monster_instantiatesWithHalfFullPlayLevel(){
        Endangered testEndangered= new Endangered("Bubbles", 1);
        assertEquals(testEndangered.getHealthLevel(), (Endangered.MAX_HEALTH_LEVEL / 2));
    }

}