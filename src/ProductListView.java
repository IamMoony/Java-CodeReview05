import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductListView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        TextField prodName = new TextField("Prod. Name");

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
