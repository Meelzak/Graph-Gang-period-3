package Testing;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class Tester extends Application {

    private ArrayList files = new ArrayList();
    private ArrayList queue = new ArrayList();
    //-----------------------------------------
    private HBox h1 = new HBox();
    private VBox vBox = new VBox();
    private VBox v1 = new VBox();
    private HBox h2 = new HBox();
    private ListView listView = new ListView();
    private Button button1 = new Button("Submit");
    private Button button2 = new Button("Cancel");
    private Button add = new Button("Add");
    private Button go = new Button("Start");
    private Label calculating = new Label("calculating: ");
    private Label queueLabel = new Label("queue: ");
    private Scene scene;
    private Stage primaryStage;
    private Stage stage;
    private Scene scene2;
    private Test test=new Test();
    public static ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    //---------------------------------------------
    private ListView queueListView = new ListView();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) {
        //Top
        primaryStage=s;
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        primaryStage.setResizable(false);
        h1.setMinHeight(50);
        h1.getChildren().addAll(add,go);
        h1.setSpacing(20);
        h1.setAlignment(Pos.CENTER);

        h2.getChildren().setAll(button1,button2);
        h2.setSpacing(20);
        h2.setAlignment(Pos.CENTER);
        h1.setMinHeight(50);
        vBox.getChildren().addAll(h1,calculating,queueLabel,queueListView);
        //add files to an ArrayList of Strings:
        File file = new File(Testing.Parameters.path);
        String[] f = file.list();
        for(int i=0;i<f.length;i++){
            if(f[i].endsWith(".txt")){
                files.add(f[i]);
            }
        }
        listView.setItems(FXCollections.observableList(files));
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        v1.getChildren().addAll(listView,h2);
        scene = new Scene(vBox,300,500);
        scene2 = new Scene(v1,200,300);
        listen();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tester");
        primaryStage.show();
    }
    public void listen(){
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            queue.addAll(listView.getSelectionModel().getSelectedItems());
            update();
            stage.close();
        });
        button2.setOnAction(event -> {
            stage.close();
        });
        add.setOnAction(event -> {
            stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(scene2);
            stage.setX(primaryStage.getX()+50);
            stage.setY(primaryStage.getY()+60);
            stage.show();
        });
        go.setOnAction(event -> {
            if(!queue.isEmpty()){
                calculating.setText("calculating: "+queue.get(0).toString());
                for (int i=0;i<queue.size();i++){
                    threadPoolExecutor.execute(()->{
                        String s = queue.remove(0).toString();
                        Platform.runLater(() -> {
                            calculating.setText("calculating: "+s);
                            update();
                        });
                        test.testing(s);
                    });
                }
            }else{
                System.out.println("Finished");
            }
        });
    }
    public void update(){
        queueLabel.setText("queue: "+queue.size());
        queueListView.setItems(FXCollections.observableList(queue));
    }
}
