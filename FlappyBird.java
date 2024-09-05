import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int boardWidth = 360;
    int boardHeight = 640;

    //Images
    Image backgroundImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    @Override
    public void actionPerformed(ActionEvent e) {//ActionPerformed every 120 a second
        move();
        repaint(); //Calls the painComponent function every time
        if (gameOver){
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(gameOver){
                //Restart the game with default values
                bird.y = birdY;
                velocityY =0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    //Bird
    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird{ //Created a class to easily access the variables
        //Position of bird at the start of the game
        int x = birdX;
        int y = birdY;

        //Size of bird
        int width = birdWidth;
        int height = birdHeight;

        //Image of bird
        Image img;

        Bird(Image img){
            this.img = img;
        }
    }

    //Pipes
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe{
        //Position of pipe
        int x = pipeX;
        int y = pipeY;

        //Size of the pipes
        int width = pipeWidth;
        int height = pipeHeight;

        //Image of the pipe
        Image img;
        boolean passed = false;// Checks if the bird passed the pipe or not

        Pipe(Image img){
            this.img = img;
        }
    }
    //Game logic
    Bird bird;
    int velocityY=0;
    int gravity =1;
    int velocityX=-4; //Speed at which the pipes move

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameOver = false;
    double score =0;

    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setFocusable(true); //It makes sure that this class is going to take in the key events
        addKeyListener(this); //Makes sure that the Key functions are checked

        //Loading the Images
        backgroundImage = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage(); //Get class refers to the current class and get resource refers to the location of the current class
        birdImage =  new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //Bird
        bird = new Bird(birdImage);
        pipes = new ArrayList<Pipe>();

        //Place Pipe Timer
        placePipesTimer = new Timer(1650, new ActionListener() {//1500 = 1.5 sec
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //Game Timer
        gameLoop = new Timer(1000/120,this); //This refers to the current class
        gameLoop.start();  //We need game loop to continues draw the frames for the game
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); //Super refers to JPanel
        draw(g);
    }

    private void draw(Graphics g) {
        //Back_Ground
        g.drawImage(backgroundImage,0,0,boardWidth,boardHeight,null);// Top left corner is (0,0), bottom right is (360,640) // x and y are the starting position

        //Bird
        g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);

        //Pipes
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
        }

        g.setColor(Color.darkGray);
        g.setFont( new Font("Arial",Font.PLAIN,32));
        if(gameOver){
            g.drawString("Game Over : "+String.valueOf((int) score),70,320);
        }
        else{
            g.drawString(String.valueOf((int) score),10,35);
        }
    }

    public void move(){
        //Bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y,0); //Makes sure that the bird doesn't cross the title bar

        //Pipe
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if(!pipe.passed && bird.x > pipe.x + pipe.width){
                pipe.passed = true;
                score+=0.5; //0.5 because there are two pipes so for 1 pass 1 point
            }

            if(collision(bird,pipe)){
                gameOver = true;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    public void placePipes(){
        int randomPipeY=(int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace = boardHeight/4;

        Pipe topPipe = new Pipe(topPipeImage);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottompipe = new Pipe(bottomPipeImage);
        bottompipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottompipe);
    }

    public boolean collision(Bird a, Pipe b){
        return a.x < b.x + b.width && // a's top letf corner doesn't reach b's top right corner
                a.x + a.width > b.x && // a's top right corner passes b's top left corner
                a.y < b.y + b.height && // a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y; // a's bottom left corner passes b's top left corner
    }
}
