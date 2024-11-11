package logic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Point {
    @Id
    @GeneratedValue
    private Long id;
    private float x;
    private float y;
    private float r;
    private boolean status;

    public Point(float x, float y, float r, boolean status){
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = status;
    }

    public Point() {

    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus(){
        return status;
    }
    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "x: "+x+"; y: "+y+"; r: "+ r+ "; status: "+ status;
    }
}
