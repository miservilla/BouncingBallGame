import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class BouncingBallGame extends Application
{
    @Override
    public void start(Stage stage)
    {
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 900, 900, Color.TRANSPARENT);
        Circle ball = new Circle(30, Color.DARKSLATEBLUE);
        ball.relocate(0,0);

        canvas.getChildren().add(ball);

//        stage.initStyle(StageStyle.TRANSPARENT);

//        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent mouseEvent)
//            {
//                Platform.exit();
//                System.exit(0);
//            }
//        });
//Above anonymous inner class and over ridden method replaced with lambda below.


        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> //Mouse click ends program.
        {
            Platform.exit();
            System.exit(0);
        });

        stage.setTitle("Bouncing Ball Game");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5),
                new EventHandler<>()
                {
                    double dx = 7; //Sets step on x (or velocity).
                    double dy = 3; //Sets step on y.

                    @Override
                    public void handle(ActionEvent t)
                    {
                        //Moves the ball.
                        ball.setLayoutX(ball.getLayoutX() + dx);
                        ball.setLayoutY(ball.getLayoutY() + dy);

                        Bounds bounds = canvas.getBoundsInLocal();

                        if(ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius()) ||
                                ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()))
                        {
                            dx = -dx;
                        }

                        if (ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius()) ||
                                (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius())))
                        {
                            dy = -dy;
                        }

                    }
                }));


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }




}
