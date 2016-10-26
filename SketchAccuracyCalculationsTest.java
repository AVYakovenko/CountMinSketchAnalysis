package cmsketch.core.test;

import org.junit.Assert;
import org.junit.Test;

import cmsketch.core.SketchAccuracyCalculations;

/**
 * SketchAccuracyCalculations Test
 * 
 * @author Alex Yakovenko
 */
public class SketchAccuracyCalculationsTest {

	/**
	 * Test method for
	 * {@link cmsketch.SketchAccuracyCalculations#calcSketchDepth(double)}.
	 */
	@Test
	public void testCalcSketchDepth() {
		double percentUncertainty = 1;
		int depth = SketchAccuracyCalculations.calcSketchDepth(percentUncertainty);
		Assert.assertEquals("CM-Sketch depth", 7, depth);
	}

	/**
	 * Test method for
	 * {@link cmsketch.SketchAccuracyCalculations#calcSketchWidth(double)}.
	 */
	@Test
	public void testCalcSketchWidth() {
		double percentMaxError = 10;
		int width = SketchAccuracyCalculations.calcSketchWidth(percentMaxError);
		Assert.assertEquals("CM-Sketch depth", 20, width);
	}

}
