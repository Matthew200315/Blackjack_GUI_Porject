module com.blackjackgui.blackjackgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.blackjackgui.blackjackgui to javafx.fxml;
    exports com.blackjackgui.blackjackgui;
}