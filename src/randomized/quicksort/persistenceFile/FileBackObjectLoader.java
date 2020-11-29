package randomized.quicksort.persistenceFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import randomized.quicksort.model.BackObject;

public class FileBackObjectLoader {
    private final File fileName;

    public FileBackObjectLoader(File fileName) {
        this.fileName = fileName;
    }
   
    public BackObject[] ListBackObjects(){
        Integer cont=0;
        List<BackObject> backObjects = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String info = reader.readLine();//numero de items y capacidad de la mochila
            
            while(true){
                String line = reader.readLine();
                if(line == null) break;
                backObjects.add(ListBackObjectOf(line,cont));
                cont = cont +1;
            }
        } catch(FileNotFoundException e) {
            System.out.println("ERROR FileCurrencyListLoader::currency(File Not Found)");
        } catch(IOException e) {
            System.out.println("ERROR FileCurrencyListLoader::currency(IO)");
        }
        return backObjects.toArray(new BackObject[0]);
    }

    private BackObject ListBackObjectOf(String line,Integer cont) {
        String[] split = line.split(" ");
        BackObject backObject = new BackObject(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
        backObject.setPosition(cont);
        return backObject;
    }
    public Integer LoadMaxWeigth(){
        Integer MaxWeigth = 1;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String info = reader.readLine();//numero de items y capacidad de la mochila
            MaxWeigth = MaxWeigthOf(info);
        } catch(FileNotFoundException e) {
            System.out.println("ERROR FileCurrencyListLoader::currency(File Not Found)");
        } catch(IOException e) {
            System.out.println("ERROR FileCurrencyListLoader::currency(IO)");
        }
        return MaxWeigth;
    }
    private Integer MaxWeigthOf(String line){
        String[] split = line.split(" ");
        return Integer.parseInt(split[1]);
    }
}
