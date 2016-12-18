/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tests.InputTest;

/**
 *
 * @author Jonas
 */
public class ComPongMain {
    
    
    public static void main(String[] args) {
        try {
			AppGameContainer container = new AppGameContainer(new InputTest());
			container.setDisplayMode(1920,1080,true);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

    }
    
    private void p(Object o){
        System.out.println(o);
    }
    
}
