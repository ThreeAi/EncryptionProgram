package edu.program.encryptionprogram;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainController {

    @FXML
    private TextArea encode_key_textarea;
    @FXML
    private TextArea encode_input_textarea;
    @FXML
    private TextArea encode_output_textarea;
    @FXML
    private TextArea decode_key_textarea;
    @FXML
    private TextArea decode_input_textarea;
    @FXML
    private TextArea decode_output_textarea;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleEncode() {
        String text = encode_input_textarea.getText();
        String key = encode_key_textarea.getText();
        String encryptedText = VigenereCipher.encrypt(text, key);
        encode_output_textarea.setText(encryptedText);
    }

    @FXML
    private void handleDecode() {
        String text = decode_input_textarea.getText();
        String key = decode_key_textarea.getText();
        String decryptedText = VigenereCipher.decrypt(text, key);
        decode_output_textarea.setText(decryptedText);
    }

    @FXML
    private void handleEncodeKeyImport() {
        importFileContent(encode_key_textarea);
    }

    @FXML
    private void handleEncodeKeyExport() {
        exportFileContent(encode_key_textarea);
    }

    @FXML
    private void handleEncodeTextImport() {
        importFileContent(encode_input_textarea);
    }

    @FXML
    private void handleEncodeTextExport() {
        exportFileContent(encode_output_textarea);
    }

    @FXML
    private void handleDecodeKeyImport() {
        importFileContent(decode_key_textarea);
    }

    @FXML
    private void handleDecodeKeyExport() {
        exportFileContent(decode_key_textarea);
    }

    @FXML
    private void handleDecodeTextImport() {
        importFileContent(decode_input_textarea);
    }

    @FXML
    private void handleDecodeTextExport() {
        exportFileContent(decode_output_textarea);
    }

    private void importFileContent(TextArea targetTextArea) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл для импорта");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
                targetTextArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void exportFileContent(TextArea sourceTextArea) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(sourceTextArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
