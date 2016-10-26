package cmsketch.core;

/*
 * Helper class for calculations related to Count-Min Sketch 
 * 
 * @author Alex Yakovenko
 */
public class SketchAccuracyCalculations {

	private static final double HALF_CHANCE = .5;

	private static final double PERCENT = .01;

	/**
	 * Calculate required depth of Count-Min Sketch for giver max error
	 * 
	 * @param percentUncertainty
	 *            acceptable uncertainty of result
	 * @return depth
	 */
	public static int calcSketchDepth(double percentUncertainty) {
		double inaccuracy = percentsToFraction(percentUncertainty);
		return asInt(Math.log(inaccuracy) / Math.log(HALF_CHANCE));
	}

	/**
	 * Calculate required width of Count-Min Sketch for giver max error
	 * 
	 * @param percentMaxError
	 *            max error (percents)
	 * @return width
	 */
	public static int calcSketchWidth(double percentMaxError) {
		double maxError = percentsToFraction(percentMaxError);
		return asInt(1 / (HALF_CHANCE * maxError));
	}

	/**
	 * Convert percents to fraction values (* 0.01)
	 * 
	 * @param percentValue
	 *            percent
	 * @return fraction
	 */
	public static double percentsToFraction(double percentValue) {
		return percentValue * PERCENT;
	}

	/**
	 * Convert double values to integer, knowing that the value is Count-Min
	 * Sketch attribute
	 * 
	 * @param d
	 *            double value
	 * @return integer
	 */
	public static int asInt(double d) {
		if (d > Integer.MAX_VALUE)
			throw new IllegalStateException("Calculated double is too big to fit in int");
		return (int) Math.ceil(d);
	}
}
