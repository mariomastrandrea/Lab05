package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.AnagrammiModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FXMLController 
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputTextField;

    @FXML
    private Button calcolaAnagrammiButton;

    @FXML
    private TextArea anagrammiCorrettiTextArea;

    @FXML
    private TextArea anagrammiErratiTextArea;

    @FXML
    private Button resetButton;

	private AnagrammiModel model;

    public void setModel(AnagrammiModel model)
    {
    	this.model = model;
    }
    
    @FXML
    void handleCalcolaAnagrammi(ActionEvent event) 
    {
    	if(this.inputTextField.getText().isBlank())
    		throw new RuntimeException("Error: input text cannot be blank");
    	
    	String inputWord = this.inputTextField.getText();
    	
    	this.model.computeAnagramsOf(inputWord);
    	String meaningfulAnagramsList = this.model.printMeaningfulAnagramsOf(inputWord);
    	String meaninglessAnagramsList = this.model.printMeaninglessAnagramsOf(inputWord);
    	
    	this.anagrammiCorrettiTextArea.setText(meaningfulAnagramsList);
    	this.anagrammiErratiTextArea.setText(meaninglessAnagramsList);
    }

    @FXML
    void handleOnKeyTyped(KeyEvent event) 
    {
    	if(this.inputTextField.getText().isBlank())
    		this.calcolaAnagrammiButton.setDisable(true);
    	else 
			this.calcolaAnagrammiButton.setDisable(false);
		
    	
    	if( !this.inputTextField.getText().isBlank() ||
    		!this.anagrammiCorrettiTextArea.getText().isBlank() ||
    		!this.anagrammiErratiTextArea.getText().isBlank())
    		{
				this.resetButton.setDisable(false);
    		}
    	else 
			this.resetButton.setDisable(true);
    }

    @FXML
    void handleReset(ActionEvent event) 
    {
    	this.inputTextField.clear();
    	this.anagrammiCorrettiTextArea.clear();
    	this.anagrammiErratiTextArea.clear();
    	this.resetButton.setDisable(true);
    	this.calcolaAnagrammiButton.setDisable(true);
    }

    @FXML
    void initialize() 
    {
        assert inputTextField != null : "fx:id=\"inputTextField\" was not injected: check your FXML file 'Scene_lab05.fxml'.";
        assert calcolaAnagrammiButton != null : "fx:id=\"calcolaAnagrammiButton\" was not injected: check your FXML file 'Scene_lab05.fxml'.";
        assert anagrammiCorrettiTextArea != null : "fx:id=\"anagrammiCorrettiTextArea\" was not injected: check your FXML file 'Scene_lab05.fxml'.";
        assert anagrammiErratiTextArea != null : "fx:id=\"anagrammiErratiTextArea\" was not injected: check your FXML file 'Scene_lab05.fxml'.";
        assert resetButton != null : "fx:id=\"resetButton\" was not injected: check your FXML file 'Scene_lab05.fxml'.";
    }
}
