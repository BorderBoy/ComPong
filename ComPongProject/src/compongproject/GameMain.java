/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

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

     private AppGameContainer app;
     private Input input;
    
    public GameMain(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        app = (AppGameContainer) gc;
        input = app.getInput();
        
        addState(new GameMainMenu());
        addState(new GameIngame(0,1,0,1, "fafawfafaf", "hgdfhdfghhdhdhdh"));
        enterState(0);
        
    }
    
    public void keyPressed(int i, char c){
        if(i == Input.KEY_ESCAPE){
            app.exit();
        }
        
        if(i == Input.KEY_R){
            enterState(100);
        }
    }       
    
}
