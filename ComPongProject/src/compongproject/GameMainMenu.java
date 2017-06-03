/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Uli
 */
public class GameMainMenu extends BasicGameState {

    private Input input;
    private MouseOverArea area;
    private Button buttonGameIngame;
    private Button buttonQuit;
    private Button buttonSettings;
    private Image imgButtonGameIngame;
    private Image imgButtonQuit; 
    private Image imgButtonSettings;
    private Sound sndButtonGameIngame;
    private Sound sndButtonQuit;
    private Sound sndButtonSettings;
    private GameContainer app;
    private GameMain game;
    
    GameMainMenu(GameMain gm) {
        game = gm;
    }


    @Override
    public int getID() {
        return GameMain.GAMESTATE_MAINMENU;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        app = gc;
        input = app.getInput();
        app.setMouseGrabbed(false);
        
        imgButtonGameIngame = new Image(new File("").getAbsolutePath() + "/TestButton.png"); 
        imgButtonQuit = new Image(new File("").getAbsolutePath() + "/TestButton.png"); 
        imgButtonSettings = new Image(new File("").getAbsolutePath() + "/TestButton.png"); 
        
        sndButtonGameIngame = new Sound("/sndCrisp1.ogg");
        sndButtonQuit = new Sound("/sndCrisp2.ogg");
        sndButtonSettings = new Sound("/sndCrisp1.ogg");
        
        //area = new MouseOverArea(gc,imgButtonGameIngame, new Rectangle(800,660,200,100));
        buttonGameIngame = new Button(app,imgButtonGameIngame,app.getWidth()/2 - imgButtonGameIngame.getWidth()/2, app.getHeight()/2 - imgButtonGameIngame.getHeight() - 20 ,"Start", game, GameMain.BUTTON_GAMEMAINMENU_GAMEINGAME);
        buttonSettings = new Button(app,imgButtonSettings,app.getWidth()/2 - imgButtonSettings.getWidth()/2, app.getHeight()/2 + 20 ,"Settings", game, GameMain.BUTTON_GAMEMAINMENU_SETTINGS); 
        buttonQuit = new Button(app,imgButtonQuit,app.getWidth()/2 - imgButtonQuit.getWidth()/2, app.getHeight()/2 + 20 + imgButtonSettings.getHeight() + 40, "Quit", game, GameMain.BUTTON_GAMEMAINMENU_QUIT); 
        
        buttonGameIngame.setMouseDownSound(sndButtonGameIngame);
        buttonQuit.setMouseDownSound(sndButtonQuit);
        buttonSettings.setMouseDownSound(sndButtonSettings);
        
        
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.green);
        g.fillRect(0, 0, 10, 10);        
        
        buttonGameIngame.render(gc, g);
        buttonQuit.render(gc, g);
        buttonSettings.render(gc, g);
       
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
//        if(area.isMouseOver()&&input.isMousePressed(0)){
//            game.changeState(GameMain.GAMESTATE_INGAME);
//        }

        Mouse.setGrabbed(false);

        if(input.isKeyDown(Input.KEY_SPACE)){
            game.changeState(GameMain.GAMESTATE_INGAME);
        }
    }
    
    private void p(Object o){
        System.out.println(o);
    }

    
    
}
