/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomized.quicksort.model;
public class BackObject {
    private Integer position;
    private Integer value;
    private Integer weigth;

    public BackObject(Integer value, Integer weigth) {
        this.value = value;
        this.weigth = weigth;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    
    public void setPositionValueWeigth(Integer Position, Integer Value,Integer Weigth){
        this.position=Position;
        this.value=Value;
        this.weigth=Weigth;
    }
    public void swap(BackObject backobject){
        Integer ptemp = this.position;
        Integer vtemp = this.value;
        Integer wtemp = this.weigth;
        this.setPositionValueWeigth(backobject.getPosition(), backobject.getValue(), backobject.getWeigth());
        backobject.setPositionValueWeigth(ptemp, vtemp, wtemp);
    }
}
