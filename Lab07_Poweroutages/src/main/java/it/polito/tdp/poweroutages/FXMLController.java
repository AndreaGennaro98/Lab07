package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.BlackOut;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    public void setModel(Model model) {
		this.model = model;
	}
    
    ObservableList<Nerc> listaNerc=FXCollections.observableArrayList(model.getNercList());

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ChoiceBox<Nerc> txtNERC;

    @FXML
    private TextField txtAnniMax;

    @FXML
    private Label txtOreMax;

    @FXML
    private Button btnCalcola;

    @FXML
    void calcola(ActionEvent event) {
    	
    	int anni;
    	int ore;
    	try{
    		anni=Integer.parseInt(txtAnniMax.getText());
    		ore=Integer.parseInt(txtOreMax.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Gli anni e le ore devono essere dei numeri e non devono contenere ");
    		return;
    	}
    	Nerc n=txtNERC.getValue();
    	List<BlackOut> listaBlackOut=new ArrayList<BlackOut>(this.model.getBlackOutList(n, anni, ore));
    	int totale=0;
    	for(BlackOut b:listaBlackOut) {
    		txtResult.appendText(b.toString()+"\n");
    		totale+=b.getCoinvolti();
    	}
    	txtResult.appendText("Il numero massimo di utenti coinvolti nell'arco di "+anni+" e in un range di "+ore+" è: "+totale+"\n\n");
    }

    @FXML
    void initialize() {
    	txtNERC.setItems(listaNerc);
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNERC != null : "fx:id=\"txtNERC\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnniMax != null : "fx:id=\"txtAnniMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOreMax != null : "fx:id=\"txtOreMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

