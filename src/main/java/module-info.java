module com.banapomodoro {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;
    
    opens com.banapomodoro to javafx.fxml;
    exports com.banapomodoro;
}
