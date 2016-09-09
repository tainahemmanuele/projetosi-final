package models;


import exceptions.EmptyStringException;
import util.Verificador;

/**
 * Created by Suelany on 05/08/2016.
 */
public class Archive implements Content {
    private String name;
    private String text;
    private String type;
    private Directory parent;


    public Archive(){
        this.text = "";
    }

    public Archive(String name, String type) throws EmptyStringException {
        if (!Verificador.verificaString(name)) {
            throw new EmptyStringException("Nome do arquivo");
        }
        this.name = name;
        this.type = type;
        this.text = "";
    }

    public Archive(String name, String type, String text){
        this.name = name;
        this.type = type;
        this.text = text;
    }


    public String getName(){
        return this.name;
    }

    public String getNameType() {
        return  standardName(getName(), getType());
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String newType){
        this.type = newType;
    }
    public String getText() {
        return this.text;
    }

    public void setTexto(String newText) {
        this.text = newText;
    }

    @Override
    public Directory getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public String getPath() {
        if (this.parent == null) {
            return getNameType();
        } else {
            return this.parent.getPath() + "/" + getNameType();
        }
    }

    public boolean isDirectory(){
        return false;
    }


    // Este metodo remove os caracteres em branco do inicio do titulo.
    private static String standardName(String nameArchive, String type){
        int indexCharacter = 0;
        while( nameArchive.charAt(indexCharacter) == ' '  ){
            indexCharacter++;
        }
        String newName = nameArchive.substring(indexCharacter, (nameArchive.length()));
        if (!newName.endsWith("." + type)) {
            newName = newName + "." + type;

        }
        return newName;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Archive) {
            Archive archive = (Archive) object;
            return name.equals(archive.getName()) && type.equals(archive.getType());
        } else
            return false;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
