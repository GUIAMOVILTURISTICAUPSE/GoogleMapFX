package controllers;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ControllerGoogleMap {
	@FXML WebView wView;
	@FXML TextField txtLatitud;
	@FXML TextField txtLongitud;
	@FXML Button btnBuscar;
	@FXML Button btnObtener;
	
	double latitud;
	double longitud;
	WebEngine webEngine;
	MyBrowser myBrowser;
	
	class MyBrowser {
		
		public MyBrowser(){
			webEngine = wView.getEngine();
			final URL urlGoogleMaps = getClass().getResource("demo.html");
			webEngine.load(urlGoogleMaps.toExternalForm());
			
		}
	}
	
	public void initialize()
	{
		myBrowser = new MyBrowser();
		txtLatitud.setText("-" + 0.2298500);
		txtLongitud.setText("-" + 78.5249500);
		
		btnBuscar.setOnAction(e ->{
			latitud = Double.parseDouble(txtLatitud.getText());
			longitud = Double.parseDouble(txtLongitud.getText());
			webEngine.executeScript("" +
					"window.lat = " + latitud + ";" +
					"window.lon = " + longitud + ";" +
					"document.goToLocation(window.lat, window.lon);"
					);
		});
		
		
		btnObtener.setOnAction(e -> {
			latitud = (double) webEngine.executeScript("document.obtenerLatitud();");
			txtLatitud.setText(String.valueOf(latitud));
			
			longitud = (double) webEngine.executeScript("document.obtenerLongitud();");
			txtLongitud.setText(String.valueOf(longitud));
		});
	}
	
}
