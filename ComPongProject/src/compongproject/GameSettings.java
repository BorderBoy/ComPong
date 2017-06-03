/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Uli
 */
public class GameSettings extends BasicGameState {

    private AppGameContainer app;
    private GameMain game;
    
    public GameSettings(GameMain gm){
        game = gm;
    }
    
    @Override
    public int getID() {
        return GameMain.GAMESTATE_SETTINGS;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        if (gc instanceof AppGameContainer) {
            app = (AppGameContainer) gc;
	}
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }
    
}
