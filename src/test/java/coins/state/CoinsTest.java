package coins.state;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {

    private Coins state1 = new Coins(7, 3); // the original initial state

    private Coins state2; // the goal state
    {
        BitSet bs = new BitSet(7);
        bs.set(0, 7);
        state2 = new Coins(7, 3, bs);
    }

    @Test
    void isGoalTest() {
        assertFalse(state1.isGoal());
        assertTrue(state2.isGoal());
    }

    @Test
    void canFlipTest() {
        BitSet bs = new BitSet(7);
        assertFalse(state1.canFlip(bs));
        bs.set(0, 3, true);
        assertTrue(state1.canFlip(bs));
        assertTrue(state2.canFlip(bs));
    }

    @Test
    void flipTest() {
        Coins state3 = new Coins(7, 3);
        state3.flip(state1.getFlips().get(0));
        assertEquals(state3.getFlips().get(0),state1.getFlips().get(0));
    }

    @Test
    void generateFlipsTest() {
        assertEquals(CombinatoricsUtils.binomialCoefficient(7,3),Coins.generateFlips(7,3).size());
    }

    @Test
    void getFlipsTest() {
        assertEquals(CombinatoricsUtils.binomialCoefficient(state1.getN(),state1.getM()),state1.getFlips().size());
    }

    @Test
    void EqualsTest() {
        assertEquals(state1, new Coins(7, 3));
        assertNotEquals(state1, state2);
    }

    @Test
    void HashCodeTest() {
        assertEquals((new Coins(7,3).hashCode()), (new Coins(7,3).hashCode()));
        assertEquals(state1.hashCode(), (new Coins(7,3).hashCode()));
    }

    @Test
    void CloneTest() {
        assertEquals(state1.clone(), state1);
        assertNotSame(state1.clone(), state1);
    }

    @Test
    void ToStringTest() {
        assertEquals(state1.toString(), "O|O|O|O|O|O|O");
        assertEquals(state2.toString(), "1|1|1|1|1|1|1");
    }
}