package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suelany on 05/08/2016.
 */
public class Directory implements Content{
    private String name;
    private List<Archive> listArchive;
    private List<Directory> listDirectory;

    public Directory(String name){
        this.name = name;
        this.listArchive = new ArrayList<Archive>();
        this.listDirectory = new ArrayList<Directory>();
    }

    // Adiciona Archive and Director
    public void addContent (Content content , Content dir){
        if(dir.getName().equals(this.name)) {
            addContent(content);
        }else {
            ((Directory)dir).addContent(content);
        }
    }

    public void addContent(Content file){
        if (file.isDirectory()){
            this.listDirectory.add((Directory) file);
        }else if (!file.isDirectory()) {
            listArchive.add((Archive) file);
        }
    }

    // Delete Archive and Directory
    public void delContent(Content content){
        if(content.isDirectory() && this.listDirectory.contains(content)){
            listDirectory.remove(content);
        } else if(!content.isDirectory() && this.listArchive.contains(content)){
            this.listArchive.remove(content);
        }
    }

    public boolean isDirectory(){
        return true;
    }

    //Getters
    public String getListDirectory(){
        String names = "";
        String quebraLinha = System.getProperty("line.separator");
        for (int i = 0; i < this.listDirectory.size(); i++){
            names += this.listDirectory.get(i).getName() + quebraLinha;
        }
        return names;
    }

    public String getListArchive(){
        String names = "";
        String quebraLinha = System.getProperty("line.separator");
        for (int i = 0; i < this.listArchive.size(); i++){
            names += this.listArchive.get(i).getName() + quebraLinha;
        }
        return names;
    }

    public String getName() { return name; }

    //ToString
    public String toString(){
        String text = getListArchive() + getListDirectory();
        if (text.equals("")){
            return "Pasta Vazia";
        }
        return text;
    }
}
