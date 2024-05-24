package com.michaelvslalapan.SaveAndLoad;
 
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;


public class Load {
    
    public void loadFile(){
        File myObj = new File("SaveFile.txt");
        try{
            Scanner reader = new Scanner(myObj);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
            }
            System.out.println("berhasil");
            reader.close();
        }
        catch (Exception E){
            System.out.println("test");
        }  
    }

    public static void main(String args[]) {
        (new Load()).loadFile();
    }
}
