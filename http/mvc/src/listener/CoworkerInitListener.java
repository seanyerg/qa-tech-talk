package listener;
/**
 * User: seanyerg
 */

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.Coworker;

@WebListener()
public class CoworkerInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Map<Integer, Coworker> coworkerMap = new HashMap<>();

		coworkerMap.put(1, new Coworker(1, "Sean Yergensen", "Did I tell you about the time that I...?"));
		coworkerMap.put(2, new Coworker(2, "Dave Martin", "If only BYU didn't throw six interceptions they would have totally won."));
		coworkerMap.put(3, new Coworker(3, "Steve Starks", "Before I couldn't even spell Ukulele, but now I can play one!"));
		coworkerMap.put(4, new Coworker(4, "Christina Anderson", "It sounds like you haven't got your sea legs yet, a couple more cruises should get you there."));
		coworkerMap.put(5, new Coworker(5, "Mark Sampson", "What team has scored more points than anyone else and still has a losing record?"));
		coworkerMap.put(6, new Coworker(6, "Carl Canlas", "It's not called table tennis!  It's called ping pong!"));

		servletContextEvent.getServletContext().setAttribute("coworkerMap", coworkerMap);
		servletContextEvent.getServletContext().setAttribute("coworkers", coworkerMap.values());
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}
}
