package ro.teamnet.zth.fmk;

/**
 * Created by Andrei on 5/7/2015.
 */
public class Parameter {
    private Class type;
    private String name;

    public Parameter(Class type, String name) {
        this.type = type;
        this.name = name;
    }

    public Parameter() {
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
