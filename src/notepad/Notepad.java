/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.util.EnumSet.range;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 *
 * @author sara
 */
public class Notepad extends Application {

    String path;
    String s;
    FXMLDocumentBase root;
    String strcopy = "";
    Stage stage2;
    String content;
    private FileChooser fileChooser;

    File file;

    @Override
    @SuppressWarnings("Convert2Lambda")
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        root = new FXMLDocumentBase();
        file = null;
        stage2 = stage;
Class c=stage2.getClass();
      System.out.println( c.getClassLoader());

        // stage.setWidth(100);
        stage.setTitle("untitled" + " - Notepad");
        New(stage);
        OpenLow(stage);
        OpenHigh(stage);
        Copy(stage);
        About(stage);
        Cut(stage);
        SaveLow();
        SaveHigh(stage);
        Paste(stage);
        Delete(stage);
        Exit(stage);
        selectall(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void New(Stage stage) {
        root.New.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String str = root.TextArea.getText().toString();
                if (!str.equals("") && stage.getTitle().equals("untitled" + " - Notepad")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Notepad");
                    alert.setContentText("Do you want to save changes ? ");
                    ButtonType buttonTypeOne = new ButtonType("Save");
                    ButtonType buttonTypeTwo = new ButtonType("Don't save");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get().getText().equals(buttonTypeOne.getText())) {
                        System.out.print(result.get().getText() + " " + buttonTypeOne.getText());
                        Save();

                    } else if (result.get() == buttonTypeTwo) {
                        root.TextArea.clear();
                        stage.setTitle("untitled" + " - Notepad");

                    } else {
                        alert.close();
                    }

                } else {
                    root.TextArea.clear();
                    stage.setTitle("untitled" + " - Notepad");
                }
            }
        });
    }

    public void OpenLow(Stage stage) {
        root.Openlow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fil_chooser = new FileChooser();
                File file = fil_chooser.showOpenDialog(stage);
                path = file.getAbsolutePath();
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(path);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                }
                int size = 0;
                try {
                    size = fis.available();
                } catch (IOException ex) {
                    Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] b = new byte[size];
                try {
                    fis.read(b);
                } catch (IOException ex) {
                    Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                }
                root.TextArea.setText(new String(b));
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void SaveLow() {
        root.SaveLow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser = new FileChooser();
                content = root.TextArea.getText();
                if (file != null) {
                    try {
                        // if file doesnt exists, then create it
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileOutputStream fos = null;
                        fos = new FileOutputStream(file.getAbsoluteFile());
                        byte[] b = root.TextArea.getText().getBytes();
                        fos.write(b);
                        fos.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // open a file dialog box
                    file = fileChooser.showSaveDialog(null);
                    if (file != null) {
//  Stage stage = (Stage) root.TextArea.getScene().getWindow();
                        stage2.setTitle(file.getName() + " - Notepad");
                        try {
                            // if file doesnt exists, then create it
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            FileOutputStream fos = null;
                            fos = new FileOutputStream(file.getAbsoluteFile());
                            byte[] b = root.TextArea.getText().getBytes();
                            fos.write(b);
                            fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                file = null;
            }
        });

    }

    public void Save() {
        fileChooser = new FileChooser();
        content = root.TextArea.getText();
        if (file != null) {
            try {
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fos = null;
                fos = new FileOutputStream(file.getAbsoluteFile());
                byte[] b = root.TextArea.getText().getBytes();
                fos.write(b);
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // open a file dialog box
            file = fileChooser.showSaveDialog(null);
            if (file != null) {
//  Stage stage = (Stage) root.TextArea.getScene().getWindow();
                stage2.setTitle(file.getName() + " - Notepad");
                try {
                    // if file doesnt exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = null;
                    fos = new FileOutputStream(file.getAbsoluteFile());
                    byte[] b = root.TextArea.getText().getBytes();
                    fos.write(b);
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        file = null;
    }

    public void SaveHigh(Stage stage) {
        root.SaveHigh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser = new FileChooser();
                String content = root.TextArea.getText();
                if (file != null) {
                    try {
                        // if file doesnt exists, then create it
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(content);
                        bw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // open a file dialog box
                    file = fileChooser.showSaveDialog(null);
                    if (file != null) {
                        stage.setTitle(file.getName() + " - Notepad");
                        try {
                            // if file doesnt exists, then create it
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            FileWriter fw = new FileWriter(file.getAbsoluteFile());
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(content);
                            bw.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                file = null;
            }

        });
    }

    public void OpenHigh(Stage stage) {
        root.Openhigh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fil_chooser = new FileChooser();
                File file = fil_chooser.showOpenDialog(stage);
                path = file.getAbsolutePath();
                if (file != null) {
                    try {

                        FileReader fw = new FileReader(file.getAbsoluteFile());
                        BufferedReader bw = new BufferedReader(fw);
                        String sCurrentLine;
                        while ((sCurrentLine = bw.readLine()) != null) {
                            root.TextArea.appendText(sCurrentLine + "\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void Exit(Stage stage) {
        root.Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
          savedialog();
            }

        });
    }

    public void Copy(Stage stage) {
        root.Copy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = root.TextArea.getSelectedText();
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(text);
                clipboard.setContent(content);
            }
        });

    }

    public void Cut(Stage stage) {
        root.Cut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = root.TextArea.getSelectedText().replace("", "");

                root.TextArea.setText(root.TextArea.getText().replace(root.TextArea.getSelectedText(), ""));
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(text);
                clipboard.setContent(content);
            }
        });
    }

    public void Paste(Stage stage) {
        root.Paste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text;

                //root.TextArea.setText(root.TextArea.getText().replace(root.TextArea.getSelectedText(), ""));
                Clipboard clipboard = Clipboard.getSystemClipboard();
                //ClipboardContent content=new ClipboardContent();
                // text= content.getString();

                text = clipboard.getString();
                int index = root.TextArea.getAnchor();
                String str1 = root.TextArea.getText().substring(0, index);

                String str2 = root.TextArea.getText().substring(index);
                root.TextArea.setText(str1 + text + str2);
            }
            //////////root.TextArea.setText(path);
        });
    }

    /*     * @param args the command line arguments
     */
    public void selectall(Stage stage) {
        root.SelectAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                root.TextArea.selectAll();

            }
            //////////root.TextArea.setText(path);
        });
    }

    public void Delete(Stage stage) {
        root.Delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String text = root.TextArea.getSelectedText().replace("", "");

                root.TextArea.setText(root.TextArea.getText().replace(root.TextArea.getSelectedText(), ""));
            }
        });
    }

    public void About(Stage stage) {
        root.About.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("About Notepad");
                alert.setHeaderText("Look , Information about Notepad");
                alert.setContentText("this a Notepad \n you can open file or creat New File or save \n you can copy , cut , paste ,delete, select text");
                alert.show();
            }
        });
    }

    @Override
    public void stop() {
     savedialog();
    }
    public void savedialog()
    {
            String str = root.TextArea.getText().toString();
                if (!str.equals("") && stage2.getTitle().equals("untitled" + " - Notepad")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Notepad");
                    alert.setContentText("Do you want to save changes ? ");
                    ButtonType buttonTypeOne = new ButtonType("Save");
                    ButtonType buttonTypeTwo = new ButtonType("Don't save");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get().getText().equals(buttonTypeOne.getText())) {
                        System.out.print(result.get().getText() + " " + buttonTypeOne.getText());
                        Save();

                    } else if (result.get() == buttonTypeTwo) {
                        root.TextArea.clear();
                        stage2.setTitle("untitled" + " - Notepad");

                    } else {
                        alert.close();
                    }

                } else {
                    stage2.close();

                }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
