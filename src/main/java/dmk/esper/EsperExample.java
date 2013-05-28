package dmk.esper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import dmk.esper.listener.EchoWebEventListener;

/**
 * @author dmknopp
 * 
 */
public class EsperExample {
	protected static Logger logger = LoggerFactory
			.getLogger(EsperExample.class);

	public EsperExample() {
		super();
		init();
	}

	public void init() {
		logger.info("starting esper example");
		// The Configuration is meant only as an initialization-time object.
		Configuration cepConfig = new Configuration();
		cepConfig.addEventType("WebEvent", WebEvent.class.getName());
		EPServiceProvider epService = EPServiceProviderManager.getProvider(
				"myCEPEngine", cepConfig);
		EPAdministrator esperAdmin = epService.getEPAdministrator();
		final String epl = String
				.format("select * from WebEvent.win:time_batch(2 sec)");
		EPStatement eplStatement = esperAdmin.createEPL(epl);
		eplStatement.addListener(new EchoWebEventListener());
		
		// Uncomment one ingest sample
		
		this.sampleData2(epService);
		
//		this.sampleData1(epService);

	}

	/**
	 * 
	 * @param epService
	 */
	public void sampleData2(final EPServiceProvider epService){
		// sample 2 to send an event
		WebEventGenerator.csvToWebEvents(epService, "/webevents.csv");
		final long millis = 1000 * 5;
		try {
			if(logger.isDebugEnabled()){
				logger.debug("sleeping for " + millis);
			}
			Thread.sleep(millis);
			if(logger.isDebugEnabled()){
				logger.debug("waking from sleep");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sampleData1(final EPServiceProvider epService){
		// sample 1 to send an event
		 EPRuntime esperRuntime = epService.getEPRuntime();
		 WebEventGenerator.genWebEvents(esperRuntime, 5, 1000);
	}
	/**
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		new EsperExample();
		System.exit(1);
	}
}