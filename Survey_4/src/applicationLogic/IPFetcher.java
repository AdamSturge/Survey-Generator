package applicationLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class IPFetcher {

	public static String getIP(String remoteHost){
		String ip = null;
		try {
			URL whatismyip;
			whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					whatismyip.openStream()));

			ip = in.readLine(); //you get the IP as a String
			System.out.println("@IPFetcher remoteHost = "+remoteHost);
			//handles setting IP to local host if you are using the server computer
			if(remoteHost.equals("0:0:0:0:0:0:0:1") || remoteHost.equals("127.0.0.1")){
				ip = "localhost";
			}
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
}
