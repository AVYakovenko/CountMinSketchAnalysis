package cmsketch.core;

/**
 * Count–min sketch (CM sketch) is a probabilistic data structure that serves as
 * a frequency table of events in a stream of data.
 * 
 * It is based on "An Improved Data Stream Summary: The Count-Min Sketch and its Applications"
 * by Graham Cormode and S. Muthukrishnan.
 * 
 * 
 * @author Alex Yakovenko
 */
public class CountMinSketch {

	private int depth;

	private int width;

	private long counters[][];

	private HashFunctionsGroup hashFunctions;

	public CountMinSketch(int depth, int width, HashFunctionsGroup hashFunctions) {
		super();
		this.depth = depth;
		this.width = width;
		this.hashFunctions = hashFunctions;
		counters = new long[depth][width];
	}

	/**
	 * Increment counter for given key
	 * 
	 * @param key
	 *            key
	 */
	public void increment(long key) {
		update(key, 1);
	}

	/**
	 * Update counter for given key
	 * 
	 * @param key
	 *            key
	 * @param count
	 *            count change
	 */
	public void update(long key, int count) {
		for (int hashIndex = 0; hashIndex < depth; hashIndex++) {
			int keyHash = calcHash(key, hashIndex);
			counters[hashIndex][keyHash] += count;
		}
	}

	/**
	 * Get estimated value for given key
	 * 
	 * @param key
	 *            key
	 * @return count value
	 */
	public long estimate(long key) {
		long minCount = Long.MAX_VALUE;
		for (int hashIndex = 0; hashIndex < depth; hashIndex++) {
			int keyHash = calcHash(key, hashIndex);
			long estimateCount = counters[hashIndex][keyHash];
			minCount = Math.min(minCount, estimateCount);
		}
		return minCount;
	}

	private int calcHash(long key, int hashIndex) {
		int keyHash = hashFunctions.calcHash(hashIndex, key);
		if (keyHash < 0 || keyHash >= width) {
			throw new IllegalStateException("Illegal hash " + keyHash);
		}
		return keyHash;
	}

	public int getDepth() {
		return depth;
	}

	public int getWidth() {
		return width;
	}

	/**
	 * Get size of required memory
	 * 
	 * @return size in blocks, each block size is equal to size of long
	 */
	public int getRequiredMemorySize() {
		return width * depth;
	}

}
