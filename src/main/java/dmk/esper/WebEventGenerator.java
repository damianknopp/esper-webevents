package dmk.esper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.adapter.InputAdapter;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esperio.AdapterInputSource;
import com.espertech.esperio.csv.CSVInputAdapter;
import com.espertech.esperio.csv.CSVInputAdapterSpec;

/**
 * Generate test data for esper to crunch on webevents
 * 
 * @author dmknopp
 * 
 */
public class WebEventGenerator {
	protected static Logger logger = LoggerFactory
			.getLogger(WebEventGenerator.class);

	/**
	 * read all events from CSV file and send them to the engine
	 * 
	 * @param epService
	 * @param classpathFileName
	 */
	public static void csvToWebEvents(final EPServiceProvider epService,
			final String classpathFileName) {

		if (logger.isDebugEnabled()) {
			logger.debug("input csv values from " + classpathFileName);
		}
		// Simple CSV input
		// (new CSVInputAdapter(epService, new
		// AdapterInputSource(classpathFileName), "WebEvent")).start();

		// CSV input
		CSVInputAdapterSpec spec = new CSVInputAdapterSpec(
				new AdapterInputSource(classpathFileName), "WebEvent");
		spec.setEventsPerSec(200);
		spec.setLooping(false);
		// if(logger.isDebugEnabled()){
		// logger.debug(spec.getPropertyTypes());
		// }
		InputAdapter inputAdapter = new CSVInputAdapter(epService, spec);
		inputAdapter.start(); // method blocks unless engine thread option is on
	}

	/**
	 * generate web events and send them to the esper runtime
	 * 
	 * @param esperRuntime
	 * @param numEvents
	 */
	public static void genWebEvents(final EPRuntime esperRuntime,
			final int numEvents, final long sleepMillis) {
		for (int i = 0; i < numEvents; i++) {
			final WebEvent we = genWebEvent();
			logger.debug("Sending web event:" + we);
			esperRuntime.sendEvent(we);
			try {
				Thread.sleep(sleepMillis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * generate a webevent for testing
	 * 
	 * @return
	 */
	public static WebEvent genWebEvent() {
		final WebEvent we = new WebEvent();
		// DomainSource.SYSTEM1.toString(), EventType.FOCUS.toString(),
		// ts, "user1", "http://localhost/home"
		we.setEvent(EventType.FOCUS.toString());
		we.setSource(DomainSource.SYSTEM1.toString());
		we.setTime(System.currentTimeMillis());
		we.setUri("http://localhost/home");
		we.setUser("user1");
		return we;
	}
}