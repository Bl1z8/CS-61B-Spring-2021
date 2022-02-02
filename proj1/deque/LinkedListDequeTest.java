package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    }
    @Test
    public void RandomizedTest() {
        LinkedListDeque<Integer> LSD = new LinkedListDeque<>();
        LinkedList<Integer> LS = new LinkedList<>();
        int N = 5000;
        for (int i = 0 ; i < N ; i ++) {
            int operationNumber = StdRandom.uniform(0,10);
            if (operationNumber == 0) {
                int RandomValue = StdRandom.uniform(0,100);
                LSD.addFirst(RandomValue);
                LS.addFirst(RandomValue);
                //System.out.println("addFirst: " + RandomValue);
            }
            if (operationNumber == 1) {
                int RandomValue = StdRandom.uniform(0,100);
                LSD.addLast(RandomValue);
                LS.addLast(RandomValue);
                //System.out.println("addLast: " + RandomValue);
            }
            if (operationNumber == 2) {
                if (LS.size() > 0) {
                    int RandomValue = StdRandom.uniform(0, LSD.size());
                    int correct = LS.get(RandomValue);
                    int broken = LSD.get(RandomValue);
                    int Recursive = LSD.getRecursive(RandomValue);
                    //System.out.println("get(index): Correct: " + correct + " Broken: " + broken + "Recursive: " + Recursive);
                    assertEquals(correct, broken);
                    assertEquals(broken, Recursive);
                    assertEquals(correct, Recursive);
                }
            }
            if (operationNumber == 3) {
                if (LS.size() > 0) {
                    int correct = LS.removeFirst();
                    int broken = LSD.removeFirst();
                    assertEquals(correct, broken);
                }
            }
            if (operationNumber == 4) {
                if (LS.size() > 0) {
                    int correct = LS.removeLast();
                    int broken = LSD.removeLast();
                    assertEquals(correct, broken);
                }
            }
            if (operationNumber == 5) {
                int c = LS.size();
                int b = LSD.size();
                assertEquals(c,b);
            }
            if (operationNumber == 6) {
                assertEquals(LS.isEmpty(),LSD.isEmpty());
            }
            if (operationNumber == 7) {
                LinkedListDeque<Integer> ls1 = new LinkedListDeque<>();
                LinkedListDeque<Integer> ls2 = new LinkedListDeque<>();
                for (int i1 = 0 ; i1 < 10 ; i1 ++) {
                    ls1.addLast(i1);
                    ls2.addLast(i1);
                }
                //等价关系：传递性、对称性、自反
                boolean b1 = ls1.equals(ls2);//对称
                boolean b2 = ls2.equals(ls1);//对称
                boolean b3 = ls1.equals(null);
                boolean b4 = ls1.equals(ls1);//自反
                assertEquals(b1,b2);
                assertEquals(true,b4);
                assertEquals(false,b3);
            }
            if (operationNumber == 8) {
                System.out.println("---------------------------------Test printDeque:");
                System.out.println("Printing Deque:");
                LSD.printDeque();
                System.out.println("Printing List:");
                System.out.println(LS);
                System.out.println("----------------------------------");
            }
            if (operationNumber == 9) {
                System.out.println("-------------------Test for-each loop:");
                for (int c : LSD) {
                    System.out.print(c);
                }
                System.out.println();
                for (int d : LS) {
                    System.out.print(d);
                }
                System.out.println("---------------------------");
            }
        }
    }
}
