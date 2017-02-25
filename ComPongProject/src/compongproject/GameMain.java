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
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Uli
 */
public class GameMain extends StateBasedGame{

    public static final int GAMESTATE_MAINMENU = 0;
    public static final int GAMESTATE_INGAME = 100;
    
    public static final int BUTTON_GAMEMAINMENU_GAMEINGAME = 0;
    public static final int BUTTON_GAMEINGAME_GAMEMAINMENU = 1;
    public static final int BUTTON_GAMEINGAME_REMATCH = 2;
    
    private AppGameContainer app;
    private Input input;
    private GameIngame gameIngame;
    private GameMainMenu gameMainMenu;
    
    public GameMain(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        
        app = (AppGameContainer) gc;
        input = app.getInput();
      
        gameIngame = new GameIngame(0,1,0,1, "fafawfafaf", "hgdfhdfghhdhdhdh", this);
        gameMainMenu = new GameMainMenu(this);
        
        addState(gameMainMenu);
        addState(gameIngame);
        changeState(GAMESTATE_MAINMENU);
        
      
    }
    
    public void setMouseVisibility(boolean b){
        app.setMouseGrabbed(!b);
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
    
    public void buttonClicked(int id){
        switch(id){
            case BUTTON_GAMEMAINMENU_GAMEINGAME:
                changeState(GAMESTATE_INGAME);
                break;
            case BUTTON_GAMEINGAME_GAMEMAINMENU:
                changeState(GAMESTATE_MAINMENU);
                break;
            case BUTTON_GAMEINGAME_REMATCH:
                gameIngame.resetGame();
        }
    }
    
    private void p(Object o){
        
        
        System.out.println(o);
    }
    
}
