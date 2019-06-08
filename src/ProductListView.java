import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jdk.jfr.Description;

import java.io.InputStream;

public class ProductListView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Data model
        ObservableList<Product> items = FXCollections.observableArrayList (
                new Product(
                        "Pfeffer",
                        "1 Stueck",
                        3.49,
                        2.97,
                        "/images/pfeffer__600x600.jpg",
                        "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, besonders wenn er länger mitgekocht wird."),
                new Product(
                        "Schafmilchkaese",
                        "200 Gramm Packung",
                        2.59,
                        1.99,
                        "/images/cheese_salakis__600x600.jpg",
                        "Frisch vom Bio-Schaf"),
                new Product(
                        "Voeslauer",
                        "1.5 Liter Flasche",
                        0.75,
                        0.49,
                        "/images/voslauer__600x600.jpg",
                        "Spritziges Voeslauer Mineralwasser"),
                new Product(
                        "Zucker",
                        "500 Gramm Packung",
                        1.39,
                        0.89,
                        "/images/zucker__600x600.jpg",
                        "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen Verhältnis mit Zitronensäure und Kristallzucker abgemischt wurde.")
        );

        ListView<Product> list = new ListView<Product>();
        list.setItems(items);


        // View
        Label lblProdName = new Label("Prod. Name");
        Label lblQuanitity = new Label("Quantity");
        Label lbloldPrice = new Label("Old price");
        Label lblnewPrice = new Label("New price");
        Label lblDescrTitle = new Label("Description");
        lblDescrTitle.setFont(Font.font("", FontWeight.BOLD, 20));
        Label lblDescr = new Label();
        lblDescr.setWrapText(true);

        TextField txtprodName = new TextField();
        txtprodName.setEditable(false);
        TextField txtQuanitity = new TextField();
        txtQuanitity.setEditable(false);
        TextField txtOldPrice = new TextField();
        TextField txtNewPrice = new TextField();
/*
        // Image test
        InputStream inputTest = this.getClass().getResourceAsStream("/images/pfeffer__600x600.jpg");
        Image imgTest = new Image(inputTest);
        ImageView imgViewTest = new ImageView(imgTest);
*/
        ImageView prodImageView = new ImageView();
        prodImageView.setFitHeight(200);
        prodImageView.setFitWidth(200);

        Button btnUpdate = new Button("Update");
        Button btnReport = new Button("Report");


        HBox hboxProdName = new HBox(10, lblProdName, txtprodName);
        HBox hboxQuantity = new HBox(10, lblQuanitity, txtQuanitity);
        HBox hboxoldPrice = new HBox(10, lbloldPrice, txtOldPrice);
        HBox hboxNewPrice = new HBox(10, lblnewPrice, txtNewPrice);


        VBox vboxFields = new VBox(hboxProdName, hboxQuantity, hboxoldPrice, hboxNewPrice, prodImageView, lblDescrTitle, lblDescr, btnUpdate, btnReport);
        vboxFields.setSpacing(20);

        HBox hboxMain = new HBox(vboxFields, list);

        // View product
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            public void changed(ObservableValue<? extends Product> ov, Product old_val, Product new_val) {


                txtprodName.setText(new_val.getName());
                txtQuanitity.setText(new_val.getQuantity());
                txtOldPrice.setText(Double.toString(new_val.getOldPrice()));
                txtNewPrice.setText(Double.toString(new_val.getNewPrice()));

                String imagePath = new_val.getImagePath();
                InputStream input = this.getClass().getResourceAsStream(imagePath);
                Image prodImg = new Image(input);
                prodImageView.setImage(prodImg);

                lblDescr.setText(new_val.getDescription());



                    }
                });

        btnUpdate.setOnAction(actionEvent ->  {
            int selIdx = list.getSelectionModel().getSelectedIndex();
            if (selIdx != -1) {
                double newProdOldPrice = Double.valueOf(txtOldPrice.getText());
                double newProdNewPrice = Double.valueOf(txtNewPrice.getText());
                list.getItems().get(selIdx).setOldPrice(newProdOldPrice);
                list.getItems().get(selIdx).setNewPrice(newProdNewPrice);
                list.refresh();
            }

        });

        Scene scene = new Scene(hboxMain);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        Application.launch(args);
    }

}
