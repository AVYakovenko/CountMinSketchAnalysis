package cmsketch.core;

/**
 * Group of hash functions (for long values currently)
 * 
 * @author Alex Yakovenko
 */
public interface HashFunctionsGroup {
	
	/**
	 * Apply hash function to value
	 */
	public int calcHash(int hashIndex, long value);

	/**
	 * Get quantity of hash functions
	 */
	public int getFunctionsQuantity();
	
	/**
	 * Get predefined max range value for hashes
	 */
	public int getMaxHashValue();
}
