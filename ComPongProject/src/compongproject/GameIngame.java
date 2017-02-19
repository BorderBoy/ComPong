/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import java.io.File;
import net.java.games.input.ControllerEnvironment;
import org.lwjgl.input.Controller;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Jonas
 */
public class GameIngame extends BasicGameState {
 
    private AppGameContainer app;
    private Color shapeColor;
    private Input input;
    private BatSkin bat1;
    private final int bat1Skin;
    private final int bat2Skin;
    private final int ball1Skin;
    private final int ball2Skin;
    private BatSkin bat2;
    private BallSkin ball1;
    private BallSkin ball2;
    private float bat1Offset;
    private float bat2Offset;
    private boolean ballIsStopped;
    private float ballVelX;
    private float ballVelY;
    private float ballOffsetX;
    private float ballOffsetY;
    private int vSpacingToBat;
    private int score1;
    private int score2;
    private int lastScorer;
    private float baseBatSpeed;
    private float baseBallSpeedX;
    private float baseBallSpeedY;
    private float batSpeed;    
    private float ballSpeedX;
    private float ballSpeedY;
    private final String player1Name;
    private final String player2Name;
    private boolean gameEnd;
    private long time;
    private int bounces;

    
    public GameIngame(int bat1S, int bat2S, int ball1S, int ball2S, String name1, String name2) {
        //super("ComPong");
        bat1Skin = bat1S;
        bat2Skin = bat2S;
        ball1Skin = ball1S;
        ball2Skin = ball2S;
        player1Name = name1;
        player2Name = name2;
    }

    @Override
    public int getID(){
        return 100;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        if (gc instanceof AppGameContainer) {
            app = (AppGameContainer) gc;
	}
        
       //app.setMinimumLogicUpdateInterval(30);
       
        
        //make cursor invisible
        app.setMouseGrabbed(true);
        app.setShowFPS(false);
        shapeColor = Color.white;
        input = app.getInput();
        String projectPath = new File("").getAbsolutePath() + "\\src\\compongproject\\";
        vSpacingToBat = 60;
        bat1 = new BatSkin(bat1Skin);
        bat2 = new BatSkin(bat2Skin);
        ball1 = new BallSkin(ball1Skin);
        ball2 = new BallSkin(ball2Skin);
        bat1Offset = 0;
        bat2Offset = 0;
        ballIsStopped = true;
        ballVelX = 0;
        ballVelY = 0;
        ballOffsetX = 0;
        ballOffsetY = 0;
        score1 = 0;
        score2 = 0;
        lastScorer = 0;
        baseBatSpeed = .7f;
        baseBallSpeedX = 1f;
        baseBallSpeedY = 1f;
        gameEnd=false;
        bounces = 0;
        
        
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        float incRate = .4f;
        float logSpeed = (float) (1-incRate+Math.log(bounces+Math.E)*incRate);
        
        
        ballSpeedX = baseBallSpeedX * delta * logSpeed;
                
        ballSpeedY = baseBallSpeedY * delta * logSpeed;
        
        batSpeed = baseBatSpeed * delta;
       
        
        
        //Controls
        
        if((input.isKeyDown(Input.KEY_W)) && !ballIsStopped){
            if(bat1Offset > -app.getHeight()/2 + bat1.getHeight()/2){
                bat1Offset = bat1Offset - batSpeed;
            }
        }
        
        if((input.isKeyDown(Input.KEY_S)) && !ballIsStopped){
            if(bat1Offset < app.getHeight()/2 - bat1.getHeight()/2)
                bat1Offset = bat1Offset + batSpeed;
        }
        
        if((input.isKeyDown(Input.KEY_UP)) && !ballIsStopped){
            if(bat2Offset > -app.getHeight()/2 + bat2.getHeight()/2){
                bat2Offset = bat2Offset - batSpeed;
            }
        }
        
        if((input.isKeyDown(Input.KEY_DOWN)) && !ballIsStopped){
            if(bat2Offset < app.getHeight()/2 - bat2.getHeight()/2){
                bat2Offset = bat2Offset + batSpeed;
            }
        }
        
        
        
        //Ball movement
        if(ballIsStopped == false){
            if(ballVelX == 1){
                ballOffsetX = ballOffsetX + ballSpeedX;
            } else if(ballVelX == -1){
                ballOffsetX = ballOffsetX - ballSpeedX;
            }
            
            if (ballVelY == 1){
                ballOffsetY = ballOffsetY + ballSpeedY;
            } else if (ballVelY == -1) {
                ballOffsetY = ballOffsetY - ballSpeedY;
            }
        }
        
        
        //right bat-ball collision
        if(ballOffsetX >= app.getWidth()/2 - ball1.getWidth()/2 - vSpacingToBat - bat2.getWidth() && 
           ballOffsetX < app.getWidth()/2 - vSpacingToBat + ballSpeedX &&
           Math.abs(bat2Offset-ballOffsetY) < bat2.getHeight()/2 + ball1.getWidth()/2){
            bounces++;            
            ballVelX = -1;
            
            //vertical velocity decision
            if((ballOffsetY-bat2Offset) > bat2.getHeight()/6){
                if(ballVelY != 1){
                    ballVelY = ballVelY + 1;
                }
            } else if((ballOffsetY-bat2Offset)< -bat2.getHeight()/6){
                if(ballVelY != -1){
                    ballVelY = ballVelY - 1;
                }
            }
           
        }
        
        //left bat-ball collision
        if (ballOffsetX <= -app.getWidth()/2 + ball1.getWidth()/2 + vSpacingToBat + bat1.getWidth() &&
            ballOffsetX > -app.getWidth()/2 + vSpacingToBat - ballSpeedX  &&    
            Math.abs(bat1Offset-ballOffsetY) < bat1.getHeight()/2 + ball1.getWidth()/2){
            bounces++;
             ballVelX = ballVelX = 1;
             
             //vertical velocity decision
             if((ballOffsetY-bat1Offset) > bat1.getHeight()/6){
                if(ballVelY != 1){
                    ballVelY = ballVelY + 1;
                }
            } else if((ballOffsetY-bat1Offset)< -bat1.getHeight()/6){
                if(ballVelY != -1){
                    ballVelY = ballVelY - 1;
                }
            }
            
        }
        
        //top and down collision
        if (ballOffsetY > app.getHeight()/2 - ball1.getWidth()/2){
            ballVelY = -1;
        }
        
        if (ballOffsetY < -app.getHeight()/2 + ball1.getWidth()/2){
            ballVelY = 1;
        }
        
        //goal right
        if(ballOffsetX > app.getWidth()/2){
            resetBall(-1);
        }
        
        
        //goal left
        if (ballOffsetX < -app.getWidth()/2){
           resetBall(1);
        }
        
        //game reset
        if(score1 > 9 || score2 > 9){
            gameEnd = true;    
            ballIsStopped = true; 
            
        }
        
        /*if(bounces == 10 ){
            p("time for 10 bounces : " + (System.currentTimeMillis()-time));
            time = System.currentTimeMillis();
            bounces = 0 ;
            
        }*/

    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.setColor(shapeColor);
        //draw bats
        bat1.draw(vSpacingToBat, (app.getHeight()/2 - bat1.getHeight()/2) + bat1Offset);
        bat2.draw(app.getWidth() - vSpacingToBat - bat2.getWidth(), (app.getHeight()/2 - bat1.getHeight()/2) + bat2Offset);
        //middle line
        g.fillRect(app.getWidth()/2-1, 0, 2, app.getHeight());
        //draw scores
        String s1 = Integer.toString(score1);
        String s2 = Integer.toString(score2);
        g.drawString(s1, app.getWidth()/2 - 20 - g.getFont().getWidth(s1), 10);
        g.drawString(s2, app.getWidth()/2 + 20, 10);
        
        g.drawString(player1Name, app.getWidth()/2 - 50 - g.getFont().getWidth(player1Name) , 10);
        g.drawString(player2Name, app.getWidth()/2 + 50, 10);
        
         
        //draw ball
        if(!ballIsStopped){
            //set ball skins
            if(ballOffsetX > 0){
                ball2.draw(app.getWidth()/2 - ball1.getWidth()/2 + ballOffsetX, app.getHeight()/2 - ball1.getWidth()/2 + ballOffsetY, ball1.getWidth(), ball1.getWidth());
            } else {
                ball1.draw(app.getWidth()/2 - ball1.getWidth()/2 + ballOffsetX, app.getHeight()/2 - ball1.getWidth()/2+ ballOffsetY, ball1.getWidth(), ball1.getWidth());
            }
        } else {
            //pre match confoguration
            g.fillOval(app.getWidth()/2 - ball1.getWidth()/2, app.getHeight()/2 - ball1.getWidth()/2, ball1.getWidth(), ball1.getWidth());
            String str = "Press 'Enter' to start.";
            g.getFont().drawString(app.getWidth()/2 - g.getFont().getWidth(str)/2, .25f * app.getHeight(), str, Color.green);
            
        }
        if(gameEnd){            
            if(score1==10){
                String winnerMsg = player1Name+"has won ! ! !\nCongratulations !";
                g.drawString(winnerMsg, app.getWidth()/4-g.getFont().getWidth(winnerMsg)/2, app.getHeight()/2-g.getFont().getHeight(winnerMsg)/2);
            }
            else {
                String winnerMsg = player2Name+"has won ! ! !\nCongratulations !";
                g.drawString(winnerMsg, 3*app.getWidth()/4-g.getFont().getWidth(winnerMsg)/2, app.getHeight()/2-g.getFont().getHeight(winnerMsg)/2);
            }
        }
    }
    
    //reset ball
    private void resetBall(int scorer){
        if (scorer == 1){
            score2++;
        } else {
            score1++;
        }
        ballIsStopped = true;
        ballOffsetX = 0;
        ballOffsetY = 0;
        ballVelX = 0;
        ballVelY = 0;
        bat1Offset = 0;
        bat2Offset = 0;
        lastScorer = scorer;
        bounces = 0;
    }
    
    private void resetGame(){        
        score2 = 0;
        score1 = 0;            
        ballOffsetX = 0;
        ballOffsetY = 0;
        ballVelX = 0;
        ballVelY = 0;
        lastScorer = 0;
        gameEnd = false;
    }     
        
    @Override
    public void controllerButtonPressed(int controller, int button) {
        super.controllerButtonPressed(controller, button);
		
        
        if(ballIsStopped == true && button == 1){
            ballIsStopped = false;
            switch (lastScorer) {
                case 0:
                    if(Math.random() <= .5){
                        ballVelX = 1;
                    } else {
                        ballVelX = -1;
                    }   break;
                case -1:
                    ballVelX = 1;
                    break;
                default:
                    ballVelX = -1;
                    break;
            }
        }  
}  
    
    public void keyPressed(int i, char c){
        if(i == 28){
            if (gameEnd){
                //restart game
                resetGame();
            } else if (ballIsStopped){                
                //kick-off
                time = System.currentTimeMillis();
                ballIsStopped = false;
                switch (lastScorer) {
                    case 0:
                        if(Math.random() <= .5){
                            ballVelX = 1;
                        } else {
                            ballVelX = -1;
                        }   break;
                    case -1:
                        ballVelX = 1;
                        break;
                    default:
                        ballVelX = -1;
                        break;
                }
            }
        }       
      
    }
    
    private void p(Object o){
        System.out.println(o);
    }
}