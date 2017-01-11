/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tests.InputTest;

/**
 *
 * @author Jonas
 */
public class ComPongMain {
    
    
    public static void main(String[] args) throws LWJGLException {
        try {
            AppGameContainer container = new AppGameContainer(new GameMain(0,1,0,1, "fafawfafaf", "hgdfhdfghhdhdhdh"));
            DisplayMode mode = Display.getDesktopDisplayMode();
            container.setDisplayMode(mode.getWidth(),mode.getHeight(),true);
            container.start();
            //yo
        } catch (SlickException e) {
                e.printStackTrace();
        }

    }
    
    private void p(Object o){
        System.out.println(o);
    }
    
}
