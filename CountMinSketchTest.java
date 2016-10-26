package cmsketch.core.test;

import org.junit.Assert;
import org.junit.Test;

import cmsketch.core.CountMinSketch;
import cmsketch.core.CountMinSketchBuilder;

/**
 * CountMinSketch Test
 * 
 * @author Alex Yakovenko
 */
public class CountMinSketchTest {

	/**
	 * Test method for {@link cmsketch.CountMinSketch#estimate(long)}.
	 */
	@Test
	public void testEstimate() {
		CountMinSketch sketch = CountMinSketchBuilder.buildCountMinSketch(1, 1);
		sketch.update(1, 100);
		Assert.assertEquals("Estimated count value", 100, sketch.estimate(1));
	}
}
