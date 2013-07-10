/********
 * Author: Ji
 * 
 * Description: This object will be instantiated ONLY ONCE in the life cycle of the WebServer, and will be
 * added as a attribute[name = Application] to the ServletContext.
 * 
 * Through this object, stateful applications can be stored and invoked in every servlet in this Web Application.
 * See internal documentation for init()
 */

package applicationLogic;

import java.util.HashMap;

import database.DatabaseController;

public class ApplicationObject {
	private HashMap<String, Object> applicationList;
	public ApplicationObject(){
		this.applicationList = new HashMap<String, Object>();
		init();
	}
	
	/*******
	 * Adds applications to the ApplicationObject
	 */
	private void init(){
		//#################
		// Example
		//#################
		//this.applicationList.put("ApplicationOne", new SomeApplication());
		//this.applicationList.put("ApplicationTwo", new SomeApplication2());
		//this.applicationList.put("ApplicationThree", new SomeApplicationX());
		//... 
		this.applicationList.put("DatabaseController", new DatabaseController());
		this.applicationList.put("ModelController", new ModelController());
	}
	
	public Object getApplication(String name){
		return this.applicationList.get(name);
	}
}
