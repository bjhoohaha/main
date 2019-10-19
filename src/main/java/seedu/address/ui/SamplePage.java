package seedu.address.ui;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

// todo: remove this class when everyone has implemented their page
public class SamplePage implements Page {
    private final static PageType pageType = PageType.SAMPLE;
    Scene sampleScene = new Scene(new VBox());

    public PageType getPageType() {
        return pageType;
    }

    public Scene getScene() {
        return sampleScene;
    }
}
