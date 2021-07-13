/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.SimulationResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void doSimula(ActionEvent event) {
    	
    	try {
			double k = Double.parseDouble(txtK.getText());
			SimulationResult sr = model.simula(boxRiver.getValue(), k);
			txtResult.setText("Numero di giorni \"critici\": "
					+ sr.getNumGiorniNo() + "\n");
			txtResult.appendText("Occupazione media del bacino: " + sr.getcAvg() + "\n");
			txtResult.appendText("SIMULAZIONE TERMINATA!\n");
		} catch (NumberFormatException nfe) {
			txtResult.setText("Devi inserire un valore numerico per il fattore k");
		}

    }

    @FXML
    void setData(ActionEvent event) {
    	this.txtEndDate.clear();
    	this.txtFMed.clear();
    	this.txtK.clear();
    	this.txtNumMeasurements.clear();
    	this.txtStartDate.clear();
    	this.txtResult.clear();
    	
    	River r = this.boxRiver.getValue();
    	if(r==null) {
    		this.txtResult.setText("selezionare un fiume");
    		return;
    	}
    	
    	List<Flow> flows = this.model.getFlows(r);
    	
    	if(flows.size()==0) {
    		this.txtResult.setText("nessuna misurazione per il fiume selezionato");
    		return;
    	}
    	
    	this.txtEndDate.setText(String.valueOf(flows.get(flows.size()-1).getDay()));
    	
    	this.txtStartDate.setText(String.valueOf(flows.get(0).getDay()));
    	
    	this.txtNumMeasurements.setText(Integer.toString(flows.size()));
    	
    	this.txtFMed.setText(Double.toString(this.model.calcolaAvg(r, flows)));
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxRiver.getItems().addAll(this.model.getRivers());
    }
}
