/******
 * Author: Ji
 */

package applicationLogic;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class SurveyContextListener implements ServletContextListener {
	
	private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event) {
    	ApplicationObject app = new ApplicationObject(); 
        context = event.getServletContext();
        context.setAttribute("Application", app);
    }
    public void contextDestroyed(ServletContextEvent event) {
    }
}