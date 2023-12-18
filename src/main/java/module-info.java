module fr.hdidogs.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens fr.hdidogs.todolist to javafx.fxml;
    exports fr.hdidogs.todolist;
}