public class JsonParser {
    public float[] getFloats(String requestString){
        String[] elements = requestString.split(":");
        float[] floats = new float[3];
        if(elements.length!=4){
            return new float[]{-20,-20,-20};
        }
        try{
            floats[0] = Float.parseFloat((elements[1].split(",")[0]));
            floats[1] = Float.parseFloat((elements[2].split(",")[0]));
            floats[2] = Float.parseFloat(elements[3].split("}")[0].replace("}", ""));
            return floats;
        }catch (Exception e){
            return new float[]{-20,-20,-20};
        }
    }
}
