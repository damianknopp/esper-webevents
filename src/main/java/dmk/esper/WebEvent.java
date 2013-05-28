package dmk.esper;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author dmknopp
 * A web event
 */
public class WebEvent {
	long time;
	String event;
	String source;
	String user;
	String uri;
	
	/**
	 * constructor params seemed to confuse the esper CSVInputAdapter
	 */
	public WebEvent() {
		super();
	}

	public long getTime() {
		return time;
	}
	
	public Date getTimeAsDate(){
		Date tmp = new Date();
		tmp.setTime(time);
		return tmp;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}