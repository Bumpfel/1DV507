/**
 * TestMathSet.java
 * Author: Jonas Lundberg
 * 24 feb. 2018
 */
package math_set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author jlnmsi
 *
 */
public class TestMathSet {
	private static MathSetGenerator generator;
	
    @BeforeAll
    public static void oneTimeSetUp() {
       generator = new MathSetGenerator();
       //MathSet ms = generator.getEmptySet();
       //System.out.println("Sets used in TestMySet: "+ms.getClass().getName());
    }
 
    @Test
    public void testConstructorSize() {
    	MathSet ms = generator.getEmptySet();
    	assertEquals(0,ms.size());
    	assertTrue(ms.isEmpty());
    	
    	ms = generator.getSpecificSet(1,3,5);
    	assertEquals(3,ms.size());
    	assertFalse(ms.isEmpty());
    	
    	ms = generator.getRangeSet(1, 10);
    	assertEquals(10,ms.size());
    	
    	ms = generator.getSpecificSet(1,3,5,7,9,1,3,5,7,9);    // Duplicates
    	assertEquals(5,ms.size());   	
    }
    
    @Test
    public void testEqualsHashCode() {
    	MathSet empty = generator.getEmptySet();
    	MathSet ms1to5 = generator.getRangeSet(1, 5);
    	MathSet ms1to5v2 = generator.getRangeSet(1, 5);
    	MathSet ms1to10 = generator.getRangeSet(1, 10);
    	MathSet ms1to10v2 = generator.getRangeSet(1, 10);

    	// Test hashCode
    	assertFalse(empty.hashCode() == ms1to5.hashCode());
    	assertFalse(ms1to10.hashCode() == ms1to5.hashCode());
    	assertTrue(ms1to10.hashCode() == ms1to10v2.hashCode());
    	assertTrue(ms1to5.hashCode() == ms1to5v2.hashCode());
    	
    	// Test equals
    	assertFalse(empty.equals(ms1to5));
    	assertFalse(ms1to10.equals(ms1to5));
    	assertTrue(ms1to10.equals(ms1to10v2));
    	assertTrue(ms1to5.equals(ms1to5v2));
    	assertFalse(ms1to5.equals("Hello!"));
    	
    	// Test both equals and hashCode
    	HashSet<MathSet> hs = new HashSet<MathSet>();
    	hs.add(empty);hs.add(ms1to5);hs.add(ms1to5v2);hs.add(ms1to10);hs.add(ms1to10v2);
    	assertEquals(3,hs.size()); 
    	
    }
    
    @Test
    public void testIteratorContains() {
    	MathSet empty = generator.getEmptySet();
    	MathSet ms1to5 = generator.getRangeSet(1, 5);
    	MathSet ms6to10 = generator.getRangeSet(6, 10);
    	MathSet ms1to10 = generator.getRangeSet(1, 10);
    	
    	assertFalse(empty.iterator().hasNext());
    	Iterator<Object> it = ms1to5.iterator();
    	int count = 0;
    	while (it.hasNext()) {
    		Object obj = it.next();
    		assertTrue(ms1to10.contains(obj));
    		assertFalse(ms6to10.contains(obj));
    		count++;
    	}
    	assertEquals(5,count);    	
    }
    
    @Test
    public void testIteratorContainsLarge() {
    	MathSet empty = generator.getEmptySet();
    	MathSet ms1to100 = generator.getRangeSet(1, 100);
    	MathSet ms101to151 = generator.getRangeSet(101, 151);
    	MathSet ms1to151 = generator.getRangeSet(1, 151);
    	
    	assertFalse(empty.iterator().hasNext());
    	Iterator<Object> it = ms1to100.iterator();
    	int count = 0;
    	while (it.hasNext()) {
    		Object obj = it.next();
    		assertTrue(ms1to151.contains(obj));
    		assertFalse(ms101to151.contains(obj));
    		count++;
    	}
    	assertEquals(100,count);    	
    }
    
    @Test
    public void testIteratorContainsObjects() {
    	MathSet empty = generator.getEmptySet();
    	
    	Object[] objs = new Object[]{"1s", "2n", "3r","4t", "5t"};
    	MathSet ms1to5 = generator.getObjectSet("1s", "2n", "3r","4t", "5t");

    	assertFalse(empty.iterator().hasNext());
    	Iterator<Object> it = ms1to5.iterator();
    	ArrayList<Object> testList = new ArrayList<Object>();
    	int count = 0;
    	while (it.hasNext()) {
    		Object obj = it.next();
    		testList.add(obj);
    		count++;
    	}
    	assertEquals(5,count);
    	assertTrue(testList.containsAll(Arrays.asList(objs)));
    }
    
    @Test
    public void testIteratorContainsObjectsMixed() {
    	MathSet empty = generator.getEmptySet();

    	Object[] objsMixed = new Object[]{"first", "second", "third",4.0, true};
    	MathSet objMixed = generator.getObjectSet("first", "second", "third",4.0, true);
    	
    	int count = 0;
    	Iterator<Object> itMixed = objMixed.iterator();
    	ArrayList<Object> testList = new ArrayList<Object>();
    	while (itMixed.hasNext()) {
    		Object obj = itMixed.next();
    		testList.add(obj);
    		count++;
    	}
    	assertEquals(5,count);
    	assertTrue(testList.containsAll(Arrays.asList(objsMixed)));

    }
    
    @Test
    public void testIntersection() {
    	MathSet empty = generator.getEmptySet();
    	MathSet ms1to5 = generator.getRangeSet(1, 5);
    	MathSet ms6to10 = generator.getRangeSet(6, 10);
    	MathSet ms1to10 = generator.getRangeSet(1, 10);
    	
    	MathSet ms = empty.intersection(ms1to10);    	
    	assertTrue(ms.isEmpty());
    	assertEquals(0,ms.size());
    	assertNotSame(ms,empty);
    	
    	ms = ms1to5.intersection(ms1to10); 
    	assertTrue(ms.equals(ms1to5));
    	assertEquals(5,ms.size());
    	ms = ms1to10.intersection(ms1to5); 
    	assertTrue(ms.equals(ms1to5));
    	assertNotSame(ms,ms1to5);    	
    	
    	ms = ms1to5.intersection(ms6to10); // Non-overlapping
    	assertTrue(ms.equals(empty));
    	ms = ms6to10.intersection(ms1to5); 
    	assertTrue(ms.equals(empty));
    	assertNotSame(ms,empty);    	
    }
    
    @Test
    public void testUnion() {
    	MathSet empty = generator.getEmptySet();
    	MathSet ms1to5 = generator.getRangeSet(1, 5);
    	MathSet ms6to10 = generator.getRangeSet(6, 10);
    	MathSet ms1to10 = generator.getRangeSet(1, 10);
    	
    	MathSet ms = empty.union(ms1to10);    	
    	assertTrue(ms.equals(ms1to10));
    	assertEquals(10,ms.size());
    	assertNotSame(ms,empty);
    	
    	ms = ms1to5.union(ms1to10); 
    	assertTrue(ms.equals(ms1to10));
    	ms = ms1to10.union(ms1to5); 
    	assertTrue(ms.equals(ms1to10));
    	assertNotSame(ms,ms1to10);    	
    	
    	ms = ms1to5.union(ms6to10); // Non-overlapping
    	assertTrue(ms.equals(ms1to10));
    	ms = ms6to10.union(ms1to5); 
    	assertTrue(ms.equals(ms1to10));
    	assertNotSame(ms,ms1to10);    	
    }
    
    @Test
    public void testDifference() {
    	MathSet empty = generator.getEmptySet();
    	MathSet ms1to5 = generator.getRangeSet(1, 5);
    	MathSet ms6to10 = generator.getRangeSet(6, 10);
    	MathSet ms1to10 = generator.getRangeSet(1, 10);
    	
    	MathSet ms = empty.difference(ms1to10);    	
    	assertTrue(ms.isEmpty());
    	assertNotSame(ms,empty);
    	ms = ms1to10.difference(empty);    	
    	assertTrue(ms.equals(ms1to10));
    	assertNotSame(ms,ms1to10);
    	
    	ms = ms1to5.difference(ms1to10);
    	assertTrue(ms.isEmpty());
    	ms = ms1to10.difference(ms1to5);
    	assertTrue(ms.equals(ms6to10));
    	assertNotSame(ms,ms6to10);    	
    	
    	ms = ms1to5.difference(ms6to10); // Non-overlapping
    	assertTrue(ms.equals(ms1to5));
    	assertNotSame(ms,ms1to5);    	
    }
    
    @Test
    public void testCopyToString() {
    	MathSet empty = generator.getEmptySet();
    	MathSet ms6to10 = generator.getRangeSet(6, 10);
    	MathSet ms1to10 = generator.getRangeSet(1, 10);
    	
    	MathSet ms = empty.copy();    	
    	assertTrue(ms.isEmpty());
    	assertNotSame(ms,empty);
    	
    	ms = ms1to10.copy();    	
    	assertTrue(ms.equals(ms1to10));
    	assertTrue(ms.contains(10));
    	assertTrue(ms.contains(5));
    	assertFalse(ms.contains(11));
    	assertNotSame(ms,ms1to10);
    	
    	System.out.println("Testing toString()");
    	System.out.println("\tExpecting: 6 7 8 9 10");

    	System.out.println("\ttoString(): "+ms6to10.toString());
    	assertEquals(-1,ms6to10.toString().indexOf("3"));
    	
    	String str = ms.toString();
    	assertTrue(str.indexOf("10")>1);
    	assertTrue(str.length()>10);
    }
    
    @Test
    public void testOtherDataTypes() {
    	MathSet str1 = generator.getObjectSet("Do","Re","Mi", "Fa", "So", "La");
    	MathSet str2 = generator.getObjectSet("Do","Re","Mi");
    	MathSet str3 = generator.getObjectSet("Fa", "So", "La");
    	MathSet ms6to10 = generator.getRangeSet(6, 10);
    	
    	MathSet ms = str2.union(str3);    	
    	assertTrue(ms.equals(str1));
    	ms = str2.intersection(str1);   	
    	assertTrue(ms.equals(str2)); 
    	ms = str1.difference(str2);   	
    	assertTrue(ms.equals(str3)); 
    	
    	ms = ms6to10.union(str1);
    	assertEquals(11,ms.size());
    	assertFalse(ms6to10.equals(str1));
    	
    	
    	System.out.println("Testing toString()");
    	System.out.println("\tExpecting: Do Re Mi Fa So La");

    	System.out.println("\ttoString(): "+str1.toString());
    	assertTrue(str1.toString().indexOf("Mi")>=0);
    	assertTrue(str1.toString().indexOf("La")>=0);
    }
    
    @Test
    public void testUsingInt() {
    	// Using the Int class where all elements have hashCode 1. 
    	MathSet set1 = generator.getObjectSet(new Int(1),new Int(2),new Int(3), new Int(4), new Int(5));
    	assertEquals(5,set1.size());
    	assertTrue(set1.contains(new Int(3)));
    	assertFalse(set1.contains(new Int(7)));
    	
    	
    	MathSet set2 = generator.getObjectSet(new Int(4), new Int(5), new Int(5),new Int(6),new Int(7), new Int(8));   	
    	assertEquals(5,set2.size());
    	assertFalse(set2.contains(new Int(3)));
    	assertTrue(set2.contains(new Int(7)));
    	
    	MathSet set3 = set1.intersection(set2);
    	assertEquals(2,set3.size());
    	assertFalse(set3.contains(new Int(3)));
    	assertTrue(set3.contains(new Int(5)));
    	
    	
    	// Copy of set2
    	MathSet set4 = generator.getObjectSet(new Int(4), new Int(5),new Int(6),new Int(7), new Int(8));
    	assertNotSame(set2,set4);
    	assertEquals(set2,set4);
    	
    	
    	System.out.println("Testing Int.toString()");
    	System.out.println("\tExpecting: 4 5");
    	System.out.println("\ttoString(): "+set3.toString());
    	
    }
    

    

}
