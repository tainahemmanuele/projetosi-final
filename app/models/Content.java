package models;

/**
 * Created by Suelany on 05/08/2016.
 */
public interface Content {

    public boolean isDirectory();

    public String getName();

    public String toString();

    public String getPath();

    public Directory getParent();

    public void setParent(Directory dir);

}
