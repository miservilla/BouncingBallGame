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
import javafx.util.Duration;
import java.lang.Math;

public class BouncingBallGame extends Application
{
    private final double radius = 30;
    @Override
    public void start(Stage stage)
    {
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 900, 900, Color.TRANSPARENT);
        Circle ball = new Circle(radius, Color.DARKSLATEBLUE);
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
            System.out.println(mouseEvent.getSceneX());
            System.out.println(mouseEvent.getSceneY());
            double xP = mouseEvent.getSceneX();
            double yP = mouseEvent.getSceneY();
            double xC = ball.getLayoutX();
            double yC = ball.getLayoutY();
            double distance = Math.sqrt((Math.pow((xP - xC), 2 ) + Math.pow((yP - yC), 2)));
            if (distance < radius)
            {
                Platform.exit();
                System.exit(0);
            }
        });

        stage.setTitle("Bouncing Ball Game");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15),
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
