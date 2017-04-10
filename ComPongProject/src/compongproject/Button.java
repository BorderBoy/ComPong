/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import java.awt.Font;
import java.awt.Label;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Uli
 */
public class Button extends MouseOverArea {
    
    String label;
    Image image;
    
    int x;
    int y;
    
    int id;
    
    GameMain game;
    
    Color color;
    TrueTypeFont font;
            
    public Button(GUIContext container, Image img, int x, int y, String s, GameMain g, int id) {
        super(container,img, x, y);
        
        label = s;
        image = img;
        
        this.x = x;
        this.y = y;
        
        this.id = id;
        
        game = g; 
        
        
        color = new Color(Color.white);
        
        font = new TrueTypeFont(new Font("GrilledCheese BTN Toasted", Font.PLAIN, 50), false);
        
        setMouseOverColor(Color.green);
    }
    
    public void render(GameContainer gc, Graphics g){
        super.render(gc,g);
        g.setFont(font);
        g.setColor(color);
        g.drawString(label, x + image.getWidth()/2 - g.getFont().getWidth(label)/2, y + image.getHeight()/2 - g.getFont().getHeight(label)/2);
    }
    
    public void changeFont(String font, int size, Color color){
         this.font = new TrueTypeFont(new Font(font, Font.PLAIN, size), false);  
         this.color = color;
    }
    
    @Override
    public void mouseReleased(int button,int x,int y){
        if(this.isMouseOver() && (button == 0) && !game.getContainer().isMouseGrabbed()){
            game.buttonClicked(id);
         }
    }
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
    }

    private void p(Object o) {
        System.out.println(o);
    }
}
