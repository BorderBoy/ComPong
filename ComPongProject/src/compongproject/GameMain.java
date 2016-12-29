/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import java.io.File;
import net.java.games.input.ControllerEnvironment;
import org.lwjgl.input.Controller;
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

/**
 *
 * @author Jonas
 */
public class GameMain extends BasicGame {

    private AppGameContainer app;
    private Color shapeColor;
    private Input input;
    private Image bat1Skin;
    private Image bat2Skin;
    private float bat1Offset;
    private float bat2Offset;
    private boolean ballIsStopped;
    private float ballVelX;
    private float ballVelY;
    private float ballOffsetX;
    private float ballOffsetY;
    private int ballRadius;
    private int vSpacingToBat;
    private int score1;
    private int score2;
    private int lastScorer;

    
    public GameMain() {
        super("Test");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        if (gc instanceof AppGameContainer) {
            app = (AppGameContainer) gc;
	}
        
        //make cursor invisible
        app.setMouseGrabbed(true);
        shapeColor = Color.white;
        input = app.getInput();
        String projectPath = new File("").getAbsolutePath() + "\\src\\compongproject\\";
        ballRadius = 15;
        vSpacingToBat = 40;
        bat1Skin = new Image(projectPath + "bat1Skin.png");
        bat2Skin = new Image(projectPath + "bat2Skin.png");
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
       
        
        
        
    }
 
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
        //Controls
        if (input.isKeyPressed(Input.KEY_ESCAPE)){
            app.exit();
        }
        
        if(input.isKeyDown(Input.KEY_W) || controllerUp[0] && !ballIsStopped){
            if(bat1Offset > -app.getHeight()/2 + bat1Skin.getHeight()/2){
                bat1Offset = bat1Offset - .5f;
            }
        }
        
        if(input.isKeyDown(Input.KEY_S) || controllerDown[0] && !ballIsStopped){
            if(bat1Offset < app.getHeight()/2 - bat1Skin.getHeight()/2)
                bat1Offset = bat1Offset + .5f;
        }
        
        if(input.isKeyDown(Input.KEY_UP) || controllerUp[4] && !ballIsStopped){
            if(bat2Offset > -app.getHeight()/2 + bat2Skin.getHeight()/2){
                bat2Offset = bat2Offset - .5f;
            }
        }
        
        if(input.isKeyDown(Input.KEY_DOWN) || controllerDown[4] && !ballIsStopped){
            if(bat2Offset < app.getHeight()/2 - bat2Skin.getHeight()/2){
                bat2Offset = bat2Offset + .5f;
            }
        }
        
        
        //Kick-off
        if(ballIsStopped == true && input.isKeyPressed(Input.KEY_ENTER)){
            ballIsStopped = false;
            if (lastScorer == 0){
                if(Math.random() <= .5){
                    ballVelX = 1;
                } else {
                    ballVelX = -1;
                }
            } else if (lastScorer == -1) {
                ballVelX = 1;
            } else {
                ballVelX = -1;
            }
        }
        
        //Ball movement
        if(ballIsStopped == false){
            if(ballVelX == 1){
                ballOffsetX = ballOffsetX + .6f;
            } else if(ballVelX == -1){
                ballOffsetX = ballOffsetX - .6f;
            }
            
            if (ballVelY == 1){
                ballOffsetY = ballOffsetY + .6f;
            } else if (ballVelY == -1) {
                ballOffsetY = ballOffsetY - .6f;
            }
        }
        
        
        //right bat-ball collision
        if(ballOffsetX >= app.getWidth()/2 - ballRadius - vSpacingToBat - bat2Skin.getWidth() && 
           ballOffsetX < app.getWidth()/2 - vSpacingToBat && 
           Math.abs(bat2Offset-ballOffsetY) < bat2Skin.getHeight()/2 + ballRadius){
            ballVelX = -1;
            
            //vertical velocity decision
            if((ballOffsetY-bat2Offset) > bat2Skin.getHeight()/6){
                if(ballVelY != 1){
                    ballVelY = ballVelY + 1;
                }
            } else if((ballOffsetY-bat2Offset)< -bat2Skin.getHeight()/6){
                if(ballVelY != -1){
                    ballVelY = ballVelY - 1;
                }
            }
           
        }
        
        //left bat-ball collision
        if (ballOffsetX <= -app.getWidth()/2 + ballRadius + vSpacingToBat + bat1Skin.getWidth() &&
            ballOffsetX > -app.getWidth()/2 + vSpacingToBat &&    
            Math.abs(bat1Offset-ballOffsetY) < bat1Skin.getHeight()/2 + ballRadius){
             ballVelX = ballVelX = 1;
             
             //vertical velocity decision
             if((ballOffsetY-bat1Offset) > bat1Skin.getHeight()/6){
                if(ballVelY != 1){
                    ballVelY = ballVelY + 1;
                }
            } else if((ballOffsetY-bat1Offset)< -bat1Skin.getHeight()/6){
                if(ballVelY != -1){
                    ballVelY = ballVelY - 1;
                }
            }
            
        }
        
        //top and down collision
        if (Math.abs(ballOffsetY) > app.getHeight()/2 - ballRadius){
            ballVelY = ballVelY * -1;
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
        if(score1 > 10 || score2 > 10){
            score2 = 0;
            score1 = 0;
            ballIsStopped = true;
            ballOffsetX = 0;
            ballOffsetY = 0;
            ballVelX = 0;
            ballVelY = 0;
            lastScorer = 0;
        }
        

    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        
        g.setColor(shapeColor);
        //draw bats
        bat1Skin.draw(vSpacingToBat, (app.getHeight()/2 - bat1Skin.getHeight()/2) + bat1Offset);
        bat2Skin.draw(app.getWidth() - vSpacingToBat - bat2Skin.getWidth(), (app.getHeight()/2 - bat1Skin.getHeight()/2) + bat2Offset);
        //middle line
        g.fillRect(app.getWidth()/2-1, 0, 2, app.getHeight());
        //draw scores
        String s1 = Integer.toString(score1);
        String s2 = Integer.toString(score2);
        g.drawString(s1, app.getWidth()/2 - 20 - g.getFont().getWidth(s1), 10);
        g.drawString(s2, app.getWidth()/2 + 20, 10);
         
        //draw ball
        if(!ballIsStopped){
            //set ball color
            if(ballOffsetX > 0){
                g.setColor(Color.red);
                g.fillOval(app.getWidth()/2 - ballRadius + ballOffsetX, app.getHeight()/2 - ballRadius + ballOffsetY, ballRadius*2, ballRadius*2);
                g.setColor(shapeColor);
            } else {
                g.setColor(Color.blue);
                g.fillOval(app.getWidth()/2 - ballRadius + ballOffsetX, app.getHeight()/2 - ballRadius + ballOffsetY, ballRadius*2, ballRadius*2);
                g.setColor(shapeColor);
            }
        } else {
            //pre match confoguration
            g.fillOval(app.getWidth()/2 - ballRadius, app.getHeight()/2 - ballRadius, ballRadius*2, ballRadius*2);
            String str = "Press 'Enter' to start.";
            g.getFont().drawString(app.getWidth()/2 - g.getFont().getWidth(str)/2, .25f * app.getHeight(), str, Color.green);
            
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
    }
    
    public void controllerButtonPressed(int controller, int button) {
        super.controllerButtonPressed(controller, button);
		
        
        if(ballIsStopped == true && button == 1){
            ballIsStopped = false;
            if (lastScorer == 0){
                if(Math.random() <= .5){
                    ballVelX = 1;
                } else {
                    ballVelX = -1;
                }
            } else if (lastScorer == -1) {
                ballVelX = 1;
            } else {
                ballVelX = -1;
            }
        }  
}

    
    private void p(Object o){
        System.out.println(o);
    }
    
}
