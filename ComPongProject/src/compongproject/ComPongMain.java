/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Jonas
 */
public class ComPongMain {
    
    
    public static void main(String[] args) throws LWJGLException {
        
        boolean vSyncArg = false;
        
        if (args.length == 1){
            if(args[0].equals("true") || args[0].equals("false")){
                
                if (args[0].equals("true")) {
                    vSyncArg = true;
                } else {
                    vSyncArg = false;
                }
                
                try {
                //AppGameContainer container = new AppGameContainer(new GameIngame(0,1,0,1, "fafawfafaf", "hgdfhdfghhdhdhdh"));

                AppGameContainer container = new AppGameContainer(new GameMain("ComPong", vSyncArg));
                DisplayMode mode = Display.getDesktopDisplayMode();
                container.setDisplayMode(mode.getWidth(),mode.getHeight(),true);
                container.start();
                //yo
                
                } catch (SlickException e) {
                        e.printStackTrace();
                }
                
            } else {
                System.err.println("Argument" + args[0] + " must be a boolean.");
            }
            
        } else {
            System.err.println("You must give exactly one Argument.");
        }

    }
    
    private void p(Object o){
        System.out.println(o);
    }
    
}
