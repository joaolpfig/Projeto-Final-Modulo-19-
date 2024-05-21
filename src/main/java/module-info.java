module com.example.projeto_final.projeto_final_gestao {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projeto_final.projeto_final_gestao to javafx.fxml;
    exports com.example.projeto_final.projeto_final_gestao;
}