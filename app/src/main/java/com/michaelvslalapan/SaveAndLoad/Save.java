package com.michaelvslalapan.SaveAndLoad;
 
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.nio.file.Paths;
import java.io.FileWriter;


public class Save {
    public void whenWriteStringUsingBufferedWritter_thenCorrect()  {

        try{

            String str = "Hello";

            FileWriter writer = new FileWriter("SaveFile.txt");
            
            writer.write("Hello");
            writer.write("\nHello");
            
            writer.close();
            System.out.println("sukses");
        }
        catch(Exception e){
            System.out.println("gagal");
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        Save test = new Save();
        test.whenWriteStringUsingBufferedWritter_thenCorrect();
    }
}
