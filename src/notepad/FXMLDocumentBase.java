package notepad;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

public class FXMLDocumentBase extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu menu;
    protected final MenuItem New;
    protected final MenuItem Openlow;
    protected final MenuItem Openhigh;
    protected final MenuItem SaveLow;
    protected final MenuItem SaveHigh;
    protected final MenuItem Exit;
    protected final Menu menu0;
    protected final MenuItem Cut;
    protected final MenuItem Copy;
    protected final MenuItem Paste;
    protected final MenuItem SelectAll;
    protected final MenuItem Delete;
    protected final Menu menu1;
    protected final MenuItem About;
    protected final TextArea TextArea;

    public FXMLDocumentBase() {

        menuBar = new MenuBar();
        menu = new Menu();
        New = new MenuItem();
        Openlow = new MenuItem();
        Openhigh = new MenuItem();
        SaveLow = new MenuItem();
        SaveHigh = new MenuItem();
        Exit = new MenuItem();
        menu0 = new Menu();
        Cut = new MenuItem();
        Copy = new MenuItem();
        Paste = new MenuItem();
        SelectAll = new MenuItem();
        Delete = new MenuItem();
        menu1 = new Menu();
        About = new MenuItem();
        TextArea = new TextArea();

        setPrefHeight(200.0);
        setPrefWidth(412.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        menu.setMnemonicParsing(false);
        menu.setText("File");

        New.setMnemonicParsing(false);
        New.setText("New");
        New.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        Openlow.setMnemonicParsing(false);
        Openlow.setText("OpenLow");
        Openlow.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        Openhigh.setMnemonicParsing(false);
        Openhigh.setText("OpenHigh");

        SaveLow.setMnemonicParsing(false);
        SaveLow.setText("SaveLow");
        SaveLow.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        SaveHigh.setMnemonicParsing(false);
        SaveHigh.setText("SavaHigh");

        Exit.setMnemonicParsing(false);
        Exit.setText("Exit");
        Exit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        menu0.setMnemonicParsing(false);
        menu0.setText("Edit");

        Cut.setMnemonicParsing(false);
        Cut.setText("Cut");

        Copy.setMnemonicParsing(false);
        Copy.setText("Copy");

        Paste.setMnemonicParsing(false);
        Paste.setText("Paste");

        SelectAll.setMnemonicParsing(false);
        SelectAll.setText("Select All");

        Delete.setMnemonicParsing(false);
        Delete.setText("Delete");

        menu1.setMnemonicParsing(false);
        menu1.setText("Help");

        About.setMnemonicParsing(false);
        About.setText("About");
        setTop(menuBar);

        BorderPane.setAlignment(TextArea, javafx.geometry.Pos.CENTER);
        TextArea.setPrefHeight(275.0);
        TextArea.setPrefWidth(412.0);
        setCenter(TextArea);

        menu.getItems().add(New);
        menu.getItems().add(Openlow);
        menu.getItems().add(Openhigh);
        menu.getItems().add(SaveLow);
        menu.getItems().add(SaveHigh);
        menu.getItems().add(Exit);
        menuBar.getMenus().add(menu);
        menu0.getItems().add(Cut);
        menu0.getItems().add(Copy);
        menu0.getItems().add(Paste);
        menu0.getItems().add(SelectAll);
        menu0.getItems().add(Delete);
        menuBar.getMenus().add(menu0);
        menu1.getItems().add(About);
        menuBar.getMenus().add(menu1);

    }
}
