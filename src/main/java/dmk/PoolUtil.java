package dmk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * borrowed from Venkat's "Programming Concurrency on the JVM"
 */
public class PoolUtil {
	protected static Logger logger = LoggerFactory.getLogger(PoolUtil.class);
	
	public static int computePoolSize(){
		final double blockingCoefficient = 0.9;
		return computePoolSize(blockingCoefficient);
	}
	
	/**
	 * @param blockingCoefficient
	 * @return
	 */
	public static int computePoolSize(final double blockingCoefficient){
		final int numberOfCores = Runtime.getRuntime().availableProcessors(); 
		final int poolSize = (int)(numberOfCores / (1 - blockingCoefficient));
		logger.warn("Number of Cores available is " + numberOfCores); 
		logger.warn("Pool size is " + poolSize);
		return poolSize;
	}
}
