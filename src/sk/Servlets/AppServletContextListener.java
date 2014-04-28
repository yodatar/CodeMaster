package sk.Servlets;

import sk.BusinessLogic.Resources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: pipo
 * Date: 10.3.2014
 * Time: 21:21
 */


public class AppServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Resources resources = Resources.getInstance();
		resources.setProjectId(3);
		resources.setChangesetFromId(1045);
		resources.setChangesetToId(1585);
	}
}