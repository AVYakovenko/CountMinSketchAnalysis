package cmsketch.core.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import cmsketch.core.HashFunctionsGroup;
import cmsketch.core.LinearRandomHashFunctionsGroup;

/**
 * @author Alex Yakovenko
 *
 */
public class LinearRandomHashFunctionsGroupTest {

	/**
	 * Test method for {@link cmsketch.core.LinearRandomHashFunctionsGroup#calcHash(int, long)}.
	 */
	@Test
	public void testCalcHash() {
		HashFunctionsGroup group = new LinearRandomHashFunctionsGroup(5, 100);
		int hash = group.calcHash(3, 200);
		Assert.assertTrue("Hash must be limited", hash < 100);
	}

	/**
	 * Test method for {@link cmsketch.core.LinearRandomHashFunctionsGroup#getFunctionsQuantity()}.
	 */
	@Test
	public void testGetFunctionsQuantity() {
		HashFunctionsGroup group = new LinearRandomHashFunctionsGroup(5, 100);
		Assert.assertEquals("Functions quantity", 5, group.getFunctionsQuantity());
	}
}
