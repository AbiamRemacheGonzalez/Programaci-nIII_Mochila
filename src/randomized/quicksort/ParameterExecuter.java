/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomized.quicksort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import randomized.quicksort.model.BackObject;
import randomized.quicksort.model.Backpack;
import randomized.quicksort.persistence.BackObjectSorter;
import randomized.quicksort.persistence.quicksort.BackObjectRandomizedQuickSorter;
import randomized.quicksort.persistenceFile.FileBackObjectLoader;

/**
 *
 * @author acer
 */
public class ParameterExecuter {
    private final String[] opc = {"-h","-b","-r","-t","-dt"};
    private final String[] opc1 = {"--help","--benefit","--room","--time","-display_taken"};
    private Integer[] control= {0,0,0,0,0};
    private List<Backpack> BackPacks = new ArrayList<>();
    
    public ParameterExecuter(String[] args) {
        String name ="";
        for (int i = 0; i < args.length; i++) {
            int flag = 0;
            if(args[i].equals("-f") || args[i].equals("--file")){
                BackPacks.clear();
                file(new File(args[i+1]));
                name=args[i+1];
                continue;
            }
            if(args[i].equals("-d") || args[i].equals("--directory")){
                BackPacks.clear();
                directory(args[i+1]);
                name=args[i+1];
                continue;
            }
            for (int j = 0; j < opc.length; j++) {
                if(args[i].equals(opc[j])){
                    flag = 1;
                    control[j]=1;
                    break;
                }
                if(args[i].equals(opc1[j])){
                    flag = 1;
                    control[j]=1;
                    break;
                }
            }
            if(flag == 0 && !args[i].equals(name)){
                System.out.println("Comando no reconocido: " + args[i]);
                help();
                break;
            }
        }
        Output();
    }
    
    private void Output() {
        for (Backpack BackPack : BackPacks) {
            System.out.print(BackPack.getBackPackName()+"     ");
            for (int i = 0; i < control.length; i++) {
                if (control[i] == 1){
                    execute(opc[i],BackPack);
                    System.out.print("     ");
                }
            }
            System.out.println("");
        }
    }
    
    private void execute(String arg,Backpack BackPack){
        switch(arg){
            case "-h":
                help();
                break;
            case "-b":
                benefit(BackPack);
                break;
            case "-r":
                room(BackPack);
                break;
            case "-t":
                exec_time(BackPack);
                break;
            case "-dt":
                display_taken(BackPack);
                break;  
        }
    }

    private void help() {
        System.out.println("usage: ks.py [-h] [-d [DIRECTORY]] [-f [FILE]] [-b] [-r] [-t] [-dt]");
        System.out.println("");
        System.out.println("-h, --help                                  Show this help message and exit");
        System.out.println("-d [DIRECTORY], --directory [DIRECTORY]     Directory (process many files)");
        System.out.println("-f [FILE], --file [FILE]                    file (process many files)");
        System.out.println("-b, --benefit                               Display benefit");
        System.out.println("-r, --room                                  Display room (unused knapsack weigth)");
        System.out.println("-t, --time                                  Display time");
        System.out.println("-dt, --display_taken                        Display identifier of taken items");
    }

    private void file(File arg) {
        for (int i = 0; i < 4; i++) {
          
        double inicio = System.currentTimeMillis();
        
        FileBackObjectLoader loader = new FileBackObjectLoader(arg);
        BackObject [] origen = loader.ListBackObjects();
        
        Backpack backpack = new Backpack(loader.LoadMaxWeigth(),arg.getName());
        
        BackObjectSorter sorter = new BackObjectRandomizedQuickSorter();
        sorter.BackObjectSort(origen,i);
        
        backpack.GreedyAdd(origen);
        
        double fin = System.currentTimeMillis();
        backpack.setExecutionTime(fin-inicio);
        
        BackPacks.add(backpack);
        }
    }
    public void directoryFile(File arg){
        double inicio = System.currentTimeMillis();
        
        FileBackObjectLoader loader = new FileBackObjectLoader(arg);
        BackObject [] origen = loader.ListBackObjects();
        
        Backpack backpack = new Backpack(loader.LoadMaxWeigth(),arg.getName());
        
        BackObjectSorter sorter = new BackObjectRandomizedQuickSorter();
        sorter.BackObjectSort(origen,3);
        
        backpack.GreedyAdd(origen);
        
        double fin = System.currentTimeMillis();
        backpack.setExecutionTime(fin-inicio);
        
        BackPacks.add(backpack);
    }
    private void directory(String arg) {
        File directory = new File(arg);
        for(final File ficheroEntrada : directory.listFiles()){
            if(ficheroEntrada.isDirectory()){
                System.out.println("Directorio: "+ ficheroEntrada.getName());
            }else{
                directoryFile(ficheroEntrada);
            }
        }
    }
    
    private void benefit(Backpack BackPack) {
        //Integer spacebenefit = BackPack.getMaxWeigth()-BackPack.getWeigth();
        System.out.print(BackPack.getBenefit());
    }

    private void room(Backpack BackPack) {
        System.out.print(BackPack.getMaxWeigth()+"     "+BackPack.getWeigth());
    }

    private void exec_time(Backpack BackPack) {
        System.out.print(BackPack.getExecutionTime() + " seconds");
    }

    private void display_taken(Backpack BackPack) {
        System.out.print("Taken = [");
        Integer n = BackPack.getBackpack().size();
        for (BackObject BackObjects : BackPack.getBackpack()) {
            if (n != 1){
            System.out.print(BackObjects.getPosition()+", ");
            }else{
                System.out.print(BackObjects.getPosition());
            }
            n--;
        }
        System.out.println("]");
    }
}
