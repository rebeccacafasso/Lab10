package it.polito.tdp.porto;

import javafx.scene.control.Button;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	private Model model = new Model();
	
	
	@FXML // fx:id="btnTrovaCoautori"
	 private Button btnTrovaCoautori; // Value injected by FXMLLoader

	@FXML // fx:id="btnSequenza"
	   private Button btnSequenza; // Value injected by FXMLLoader


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	
    	Author autore = boxPrimo.getValue();
    	for (Author a : model.getCoautori(autore)){
    		txtResult.appendText(a+"\n");
    	}
    	
    	boxSecondo.setDisable(false);
    	btnSequenza.setDisable(false);
    	boxSecondo.getItems().addAll(model.getNonCoautori(autore));
    	
    	

    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	
    	model.creaGrafo();
    	
    	Author autore1 = boxPrimo.getValue();
    	Author autore2 = boxSecondo.getValue();
    	
    	List <Paper> ris = model.calcolaCamminoMinimo(autore1, autore2);
    	
    	for (Paper p: ris){
    		txtResult.appendText(p.toString());
    		
    	}

    }

    @FXML
    void initialize() {
       
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnTrovaCoautori != null : "fx:id=\"btnTrovaCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnSequenza != null : "fx:id=\"btnSequenza\" was not injected: check your FXML file 'Porto.fxml'.";
       
        boxSecondo.setDisable(true);
        btnSequenza.setDisable(true);

    }

	public void setModel(Model m) {
		this.model=m;
		
		boxPrimo.getItems().addAll(model.getAutori());
		
	}
}
