package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.AnagrammiModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    @FXML
    private Label timeLabel;

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
    	
    	String inputWord = this.inputTextField.getText().trim();
    	
    	if(inputWord.length() > 9)
    	{
    		String message = "word too long! try a shorter word";
    		this.anagrammiCorrettiTextArea.setText(message);
        	this.anagrammiErratiTextArea.setText(message);
        	return;
    	}
    	
    	double initialTime = (double)System.nanoTime();
    	String meaningfulAnagrams = this.model.printMeaningfulAnagramsOf(inputWord);
    	String meaninglessAnagrams = this.model.printMeaninglessAnagramsOf(inputWord);
    	double finalTime = (double)System.nanoTime();
    	
    	this.anagrammiCorrettiTextArea.setText(meaningfulAnagrams);
    	this.anagrammiErratiTextArea.setText(meaninglessAnagrams);
    	
    	double msDiff = (finalTime-initialTime)/1000000.0;
    	String timeMessage;
    	if(msDiff < 1000.0)
    		timeMessage = String.format("Tempo impiegato: %.3f ms", msDiff);
    	else
    		timeMessage = String.format("Tempo impiegato: %.3f s", (msDiff/1000.0));
    	
    	this.timeLabel.setText(timeMessage);
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
        assert timeLabel != null : "fx:id=\"timeLabel\" was not injected: check your FXML file 'Scene_lab05.fxml'.";
        assert resetButton != null : "fx:id=\"resetButton\" was not injected: check your FXML file 'Scene_lab05.fxml'.";
    }
}
