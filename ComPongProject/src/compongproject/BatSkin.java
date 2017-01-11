/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compongproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class BatSkin extends Image {
    
    int id;
    int rarity;
    String skinName;
    String projectPath;
    
    public BatSkin(int i) throws SlickException {
        super(new File("").getAbsolutePath() + "/BatSkins/batSkin" + i + ".png");
        projectPath = new File("").getAbsolutePath();
        id = i;
        loadSkin(i);
    }
    
    //legacy
    public BatSkin(int i, int r, String n) throws SlickException{
        super(new File("").getAbsolutePath() + "/BatSkins/batSkin" + i + ".png");
        projectPath = new File("").getAbsolutePath();
        id = i;
        rarity = r;
        skinName = n;
    }
    
    private void loadSkin(int i){
        try {
            FileInputStream fis = new FileInputStream(projectPath + "BatSkins.csv");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bur = new BufferedReader(isr);
            String sLine = bur.readLine();
            String[] skinProps = sLine.split(";");
            while(sLine != null){
                while (!skinProps[0].equals(id+"")) {
                    sLine = bur.readLine();
                    skinProps = sLine.split(";");
                }
                rarity = new Integer(skinProps[1]);
                skinName = skinProps[2];
                break;
            }
            
            bur.close();
            }
            catch (IOException eIO) {
        }
    }
 
    public int getID(){
        return id;
    }
    
    public int getRarity(){
         return rarity;
    }
    
    public String getSkinName(){
        return name;
    }
        
    
    private void p(Object o){
        System.out.println(o);
    }
}