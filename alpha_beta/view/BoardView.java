package alpha_beta.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class BoardView extends BorderPane {

    public BoardView () {
        super();
        ImageView img = new ImageView(new Image("./resources/board.jpg"));
        setCenter(img);
    }

}
