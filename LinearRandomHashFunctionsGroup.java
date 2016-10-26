package cmsketch.core;

import java.util.Random;

/*
 * Group of basic hash functions:
 * hash(x) = (a * x + b) mod big_prime mod hash_values_range 
 * 
 * Uses random coefficients a and b.
 * 
 * @author Alex Yakovenko
 */
public class LinearRandomHashFunctionsGroup implements HashFunctionsGroup {

	/**
	 * Some prime that is bigger than max hash value
	 */
	private final int BIG_PRIME = Integer.MAX_VALUE;

	/**
	 * Size of functions group
	 */
	private int functionsQuantity;

	/**
	 * Max hash value
	 */
	private int hashValuesRange;

	/**
	 * Coefficient a
	 */
	private int aCoeff[];

	/**
	 * Coefficient b
	 */
	private int bCoeff[];

	/*
	 * Create and init new group of hash functions
	 */
	public LinearRandomHashFunctionsGroup(int functionsQuantity, int hashValuesRange) {
		super();
		this.functionsQuantity = functionsQuantity;
		this.hashValuesRange = hashValuesRange;
		initRandomCoefficients();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cmsketch.HashFunctionsGroup#calcHash(int, long)
	 */
	@Override
	public int calcHash(int hashIndex, long value) {
		if (hashIndex < 0 || hashIndex >= functionsQuantity) {
			throw new IllegalArgumentException("Invalid function index " + hashIndex);
		}
		if (value < 0) {
			throw new IllegalArgumentException("Invalid value " + value);			
		}
		long saltedValue = applyRandomizedFunction(hashIndex, value);
		long hashValue = Math.abs(saltedValue % BIG_PRIME % hashValuesRange);
		return (int) hashValue; // we can convert because hashValuesRange is int
	}

	@Override
	public int getFunctionsQuantity() {
		return functionsQuantity;
	}

	private long applyRandomizedFunction(int hashIndex, long value) {
		return aCoeff[hashIndex] * value + bCoeff[hashIndex];
	}

	private void initRandomCoefficients() {
		Random randomGenerator = new Random();
		aCoeff = new int[functionsQuantity];
		bCoeff = new int[functionsQuantity];

		for (int i = 0; i < functionsQuantity; i++) {
			aCoeff[i] = getRandomCoefficient(randomGenerator);
			bCoeff[i] = getRandomCoefficient(randomGenerator);
		}
	}

	private int getRandomCoefficient(Random random) {
		return 1 + random.nextInt(BIG_PRIME - 1);
	}

	@Override
	public int getMaxHashValue() {
		return hashValuesRange;
	}
}
