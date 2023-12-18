module fr.hdidogs.todolist {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.hdidogs.todolist to javafx.fxml;
    exports fr.hdidogs.todolist;
}