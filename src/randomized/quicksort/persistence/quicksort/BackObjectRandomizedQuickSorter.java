/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomized.quicksort.persistence.quicksort;

import java.util.Random;
import randomized.quicksort.model.BackObject;
import randomized.quicksort.persistence.BackObjectSorter;

/**
 *
 * @author abiam
 */
public class BackObjectRandomizedQuickSorter implements BackObjectSorter{

    @Override
    public void BackObjectSort(BackObject[] backObjects,Integer sort) {
        quickSort(backObjects,0,backObjects.length-1,sort);
    }

    private void quickSort(BackObject[] backObjects, int low, int high,Integer sort) {
        if (low < high){
            int pi = partitionSelect(backObjects,low,high,sort);
            quickSort(backObjects,low,pi-1,sort);
            quickSort(backObjects,pi+1,high,sort);
        }
    }
    
    private int partitionSelect(BackObject[] backObjects, int low, int high,Integer sort){
        switch (sort){
            case 0:
                return partitionValuemM(backObjects,low,high);
            case 1:
                return partitionValueMm(backObjects,low,high);
            case 2:
                return partitionWeigthmM(backObjects,low,high);
            case 3:
                return partitionWeigthMm(backObjects,low,high);
        }
        return low;
    }
    
    private int partitionValuemM(BackObject[] backObjects, int low, int high) {
        random(backObjects,low,high);
        BackObject pivot = backObjects[high];
        int i = (low-1);//Indice del elemento mas pequeño
        for(int j = low; j < high; j++){
            if (backObjects[j].getValue() < pivot.getValue()){
                i++;
                backObjects[i].swap(backObjects[j]);
            }
        }
   
        backObjects[i+1].swap(backObjects[high]);
               
        return i+1;
    }
    
    private int partitionValueMm(BackObject[] backObjects, int low, int high) {
        random(backObjects,low,high);
        BackObject pivot = backObjects[high];
        int i = (low-1);//Indice del elemento mas pequeño
        for(int j = low; j > high; j++){
            if (backObjects[j].getValue() < pivot.getValue()){
                i++;
                backObjects[i].swap(backObjects[j]);
            }
        }
   
        backObjects[i+1].swap(backObjects[high]);
               
        return i+1;
    }

    private int partitionWeigthmM(BackObject[] backObjects, int low, int high) {
        random(backObjects,low,high);
        BackObject pivot = backObjects[high];
        int i = (low-1);//Indice del elemento mas pequeño
        for(int j = low; j < high; j++){
            if (backObjects[j].getWeigth()< pivot.getWeigth()){
                i++;
                backObjects[i].swap(backObjects[j]);
            }
        }
   
        backObjects[i+1].swap(backObjects[high]);
               
        return i+1;
    }

    private int partitionWeigthMm(BackObject[] backObjects, int low, int high) {
        random(backObjects,low,high);
        BackObject pivot = backObjects[high];
        int i = (low-1);//Indice del elemento mas pequeño
        for(int j = low; j < high; j++){
            if (backObjects[j].getWeigth()> pivot.getWeigth()){
                i++;
                backObjects[i].swap(backObjects[j]);
            }
        }
   
        backObjects[i+1].swap(backObjects[high]);
               
        return i+1;
    }
    
    private void random(BackObject[] backObjects, int low, int high) {
        Random rand = new Random();
        int pivot = rand.nextInt(high-low)+low;

        backObjects[pivot].swap(backObjects[high]);
    }
}
