
# Flappy Bird in Java (AWT/Swing)

This project is a recreation of the popular game **Flappy Bird** using Java's built-in AWT/Swing graphics libraries. The game runs at **120 FPS**, creating a smooth and responsive gaming experience.

## Features

- **Game Loop**: Smooth gameplay with a frame rate of 120 FPS.
- **Custom JFrame and JPanel**: The game uses a JFrame to display a JPanel where all rendering and game logic occurs.
- **Dynamic Bird Movement**: A simple click handler allows the bird to "jump" with each mouse click.
- **Random Pipe Generation**: Pipes are randomly generated and move across the screen to create obstacles.
- **Collision Detection**: The game checks for collisions between the bird and pipes to determine game over.
- **Score Tracking**: The game keeps a running score, increasing as the bird successfully passes pipes.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- A basic understanding of Java AWT/Swing for graphics rendering.

### Installation

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/flappybird-java-awt-swing.git
   ```
   
2. Navigate to the project directory:
   ```bash
   cd flappybird-java-awt-swing
   ```

3. Compile the Java files:
   ```bash
   javac src/FlappyBirdGame.java
   ```

4. Run the game:
   ```bash
   java src.FlappyBirdGame
   ```

## How to Play

- Click anywhere inside the game window to make the bird "flap" and jump upwards.
- Avoid the moving pipes by timing your jumps.
- The score increases by 1 each time the bird successfully passes between pipes.
- The game ends when the bird collides with a pipe or falls out of the screen.

## Game Architecture

- **JFrame**: The main game window where the game runs.
- **JPanel**: All graphics and game elements (bird, pipes, score) are drawn on this panel.
- **Game Loop**: A `Timer` is used to update the game state and repaint the panel at 120 FPS.
- **Bird Movement**: The bird has basic physics applied with gravity pulling it down and a jump mechanic triggered by a mouse click.
- **Pipes**: Pipes are randomly generated at the top and bottom of the screen, with a gap between them. They scroll horizontally to create the game's obstacles.
- **Collision Detection**: The game detects when the bird collides with pipes or the ground, which results in game over.

## Code Overview

- **FlappyBirdGame.java**: Contains the main game logic, including the game loop, rendering, and event handling.
- **Bird.java**: Manages the bird's position, physics, and rendering.
- **Pipe.java**: Handles pipe generation, movement, and collision detection.
- **Score.java**: Manages the player's score.

## Customization

- Adjust the game speed or difficulty by modifying the pipe gap, bird gravity, or frame rate.
- Change the look of the bird or pipes by replacing or customizing the images used in the game.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
