package logic.RequestManagers;

public class ParamChecker {
    public boolean isParam(float[] params){
        //true-верные параметры, false-неверные (ошибка формата или не переданы все)
        return !(params.length==4); //RequestReader => catch float[].lenght == 4
    }
}
