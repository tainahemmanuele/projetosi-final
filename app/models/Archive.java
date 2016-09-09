package models;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suelany on 05/08/2016.
 */
public class Archive implements Content {
    private String name;
    private String text;
    private String type;
    private Directory parent;
    private Sharing compartilhamento;



    public Archive(){
        this.text = "";
        this.compartilhamento = new Sharing();
    }

    public Archive(String name, String type){
        this.name = name;
        this.type = type;
        this.text = "";
        this.compartilhamento = new Sharing();
    }

    public Archive(String name, String type, String text){
        this.name = name;
        this.type = type;
        this.text = text;
        this.compartilhamento = new Sharing();
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

    public Sharing getCompartilhamento() {
        return this.compartilhamento;
    }

    public void setCompartilhamento(Sharing compartilhamento) {
        this.compartilhamento = compartilhamento;
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

    public boolean isShared() {
        return this.compartilhamento.getStatus();
    }

    public void compartilhar(Usuario user, String tipo) {
        this.compartilhamento.addCompartilhamento(user, tipo);
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

}
