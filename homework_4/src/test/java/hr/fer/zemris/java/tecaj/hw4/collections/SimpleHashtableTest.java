package hr.fer.zemris.java.tecaj.hw4.collections;

//import java.util.ConcurrentModificationException;
import java.util.Iterator;

//import hr.fer.zemris.java.tecaj.hw4.collections.*;


import org.junit.Assert;
import org.junit.Test;

public class SimpleHashtableTest {
	

	@Test
	public void constructorTest() {
		SimpleHashtable t1 = new SimpleHashtable();
		Assert.assertEquals(0, t1.size());
		Assert.assertEquals(16, t1.getCapacity());
	}
	
	@Test
	public void constructorSizeTest() {
		SimpleHashtable t = new SimpleHashtable(22);
		Assert.assertEquals(0, t.size());
		Assert.assertEquals(32, t.getCapacity());
	}
	
	@Test
	public void putTest() {
		SimpleHashtable examMarks = new SimpleHashtable(3);
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(2));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.put("Ivana", Integer.valueOf(5));
		Assert.assertTrue(examMarks.containsKey("Ivana"));
		Assert.assertTrue(examMarks.size() == 4);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void putException(){
		SimpleHashtable examMarks = new SimpleHashtable(3);
		examMarks.put(null, Integer.valueOf(5));
	}
	
	@Test
	public void getTest() {
		SimpleHashtable examMarks = new SimpleHashtable(3);
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(2));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.put("Ivana", Integer.valueOf(5));
		examMarks.put("Kristina", Integer.valueOf(5));
		Assert.assertTrue((Integer)(examMarks.get("Ivana")) == 5);
		Assert.assertTrue((Integer)examMarks.get("Ante") == 2);
		Assert.assertTrue((Integer)examMarks.get("Jasna") == 2);
	}
	
	@Test
	public void containsTest() {
		SimpleHashtable examMarks = new SimpleHashtable();
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(4));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		Assert.assertFalse(examMarks.containsKey("Krista"));
		Assert.assertFalse(examMarks.containsKey("Maja"));
		Assert.assertTrue(examMarks.containsValue(Integer.valueOf(5)));
		Assert.assertFalse(examMarks.containsValue(Integer.valueOf(88)));
	}
	
	@Test
	public void clearTest() {
		SimpleHashtable examMarks = new SimpleHashtable();
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(4));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.clear();
		Assert.assertTrue(examMarks.size() == 0);
		Assert.assertTrue(examMarks.getCapacity() == 16);
	}
	
	@Test
	public void removeTest() {
		SimpleHashtable examMarks = new SimpleHashtable();
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(4));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.remove("Kristina");
		Assert.assertFalse(examMarks.containsKey("Kristina"));
		examMarks.remove("Ivana");
		examMarks.remove("Jasna");
		Assert.assertFalse(examMarks.containsKey("Ivana"));
		Assert.assertFalse(examMarks.containsKey("Jasna"));	
	}
	
	@Test
	public void isEmptyTest() {
		SimpleHashtable examMarks = new SimpleHashtable();
		Assert.assertTrue(examMarks.isEmpty());
		examMarks.put("Kristina", Integer.valueOf(5));
		Assert.assertFalse(examMarks.isEmpty());
	}
	
	@Test 
	public void toStringTest() {
		SimpleHashtable examMarks = new SimpleHashtable();
		examMarks.put("Ivana", Integer.valueOf(2));
		Assert.assertTrue(examMarks.toString().equals("[Ivana=2]"));
	}
	
	@Test
	public void iteratorTest() {
		SimpleHashtable examMarks = new SimpleHashtable(2);
		// fill data:
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(2));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.put("Ivana", Integer.valueOf(5)); 
		for(SimpleHashtable.TableEntry pair : examMarks) {
			Assert.assertTrue(pair.getValue() == Integer.valueOf(2));
			break;
		}
	}
	
	@Test(expected=Exception.class) 
	public void IteratorRemoveTest() {
		SimpleHashtable examMarks = new SimpleHashtable(2);
		// fill data:
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(2));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.put("Ivana", Integer.valueOf(5)); 
		Iterator<SimpleHashtable.TableEntry> iter = examMarks.iterator();

		while(iter.hasNext()) {
			SimpleHashtable.TableEntry pair = iter.next();
			if(pair.getKey().equals("Ivana")) {
				iter.remove();
				iter.remove();
				
			}
		}
	}
	
}
