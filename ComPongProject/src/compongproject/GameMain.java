/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import java.io.File;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Uli
 */
public class GameMain extends StateBasedGame{

    public static final int GAMESTATE_MAINMENU = 0;
    public static final int GAMESTATE_INGAME = 100;
    
    private AppGameContainer app;
    private Input input;
    
    public GameMain(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        
        app = (AppGameContainer) gc;
        input = app.getInput();
        
      
        
        addState(new GameMainMenu(this));
        addState(new GameIngame(0,1,0,1, "fafawfafaf", "hgdfhdfghhdhdhdh", this));
        changeState(GAMESTATE_MAINMENU);
        
      
    }
    
    @Override
    public void keyPressed(int i, char c){
        
        switch(i){
            case Input.KEY_ESCAPE:
                app.exit();
                break;
            case Input.KEY_R:
                changeState(GAMESTATE_INGAME);
                break;
            default:              
        }
    }       
    
    public void changeState(int i){
        enterState(i);
        switch(i){
            case GAMESTATE_INGAME: 
                app.setMouseGrabbed(true);
                break;
            default: 
                app.setMouseGrabbed(false);
        }
    }
    
    private void p(Object o){
        
        
        System.out.println(o);
    }
    
}
