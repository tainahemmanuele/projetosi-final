package models;

/**
 * Created by Suelany on 05/08/2016.
 */
public class Archive implements Content{
    public String name;

    public Archive(){
    }

    public Archive(String name){
        this.name = name;
    }


    public String getName(){
        return this.name;
    }

    public boolean isDirectory(){
        return false;
    }
}
