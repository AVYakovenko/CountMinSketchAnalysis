package cmsketch.core;

/**
 * Preparation of Count-Min Sketch using given accuracies
 * 
 * @author Alex Yakovenko
 */
public class CountMinSketchBuilder {

	/**
	 * Create Count-Min Sketch
	 * 
	 * @param percentMaxError
	 *            max estimation error of each count (in percents to sum of all
	 *            counts)
	 * @param percentUncertainty
	 *            uncertainty of estimated value (in percents)
	 * @return prepared data structure
	 */
	public static CountMinSketch buildCountMinSketch(double percentMaxError, double percentUncertainty) {
		int width = SketchAccuracyCalculations.calcSketchWidth(percentMaxError);
		int depth = SketchAccuracyCalculations.calcSketchDepth(percentUncertainty);
		HashFunctionsGroup hashFunctions = new LinearRandomHashFunctionsGroup(depth, width);
		return new CountMinSketch(depth, width, hashFunctions);
	}

}
