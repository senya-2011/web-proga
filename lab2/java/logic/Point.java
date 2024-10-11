package logic;

public class Point {
    float x;
    float y;
    float r;
    String status;
    AreaChecker areaChecker = new AreaChecker();
    public Point(float[] floats){
        this.x = (float) Math.round(floats[0] * 1000.0f) / 1000.0f;
        this.y = (float) Math.round(floats[1] * 1000.0f) / 1000.0f;
        this.r = (float) Math.round(floats[2] * 1000.0f) / 1000.0f;
        this.status = String.valueOf(setStatus(floats));
        this.status = areaChecker.checkData(floats) ? this.status : "out of range" ;
    }
    private boolean setStatus(float[] floats){
        return areaChecker.checkArea(floats);
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

    public String getStatus() {
        return status;
    }
    @Override
    public String toString(){
        return x+ ", "+y+", "+r+", "+status;
    }
}
