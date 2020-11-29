package randomized.quicksort.model;

import java.util.ArrayList;
import java.util.List;
public class Backpack {
    private List<BackObject> backpack = new ArrayList<>();
    private final String backPackName;
    private Integer Weigth;
    private final Integer MaxWeigth;
    private double ExecutionTime;
    private Integer benefit;
    //Maletas Reales
    public Backpack(Integer MaxWeigth,String backPackName) {
        this.Weigth = MaxWeigth;
        this.MaxWeigth = MaxWeigth;
        this.backPackName = backPackName;
        this.ExecutionTime = 0;
        this.benefit=0;
    }
    
    private Boolean addBackObject(BackObject ob){
        if(Weigth-ob.getWeigth()>= 0){
             backpack.add(ob);
             benefit = benefit + ob.getValue();
             Weigth=Weigth-ob.getWeigth();
             return true;
        }
        return false;
    }

    public double getExecutionTime() {
        return ExecutionTime;
    }
    
    public void setExecutionTime(double ExecutionTime) {
        this.ExecutionTime = ExecutionTime;
    }

    public List<BackObject> getBackpack() {
        return backpack;
    }
    public void GreedyAdd(BackObject[] backObjects){
        for (int i = 0; i < backObjects.length; i++) {
            this.addBackObject(backObjects[i]);
        }
    }

    public String getBackPackName() {
        return backPackName;
    }

    public Integer getWeigth() {
        return Weigth;
    }

    public Integer getMaxWeigth() {
        return MaxWeigth;
    }

    public Integer getBenefit() {
        return benefit;
    }
    
}
