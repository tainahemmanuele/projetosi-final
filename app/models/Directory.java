package models;

import exceptions.AlreadyExistingContentException;
import exceptions.EmptyStringException;
import util.Verificador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suelany on 05/08/2016.
 */
public class Directory implements Content{
    private String name;
    private List<Archive> listArchive;
    private List<Directory> listDirectory;
    private Directory parent;

    public Directory() {
        this.listArchive = new ArrayList<Archive>();
        this.listDirectory = new ArrayList<Directory>();
    }

    public Directory(String name) throws EmptyStringException {
        if (!Verificador.verificaString(name)) {
            throw new EmptyStringException("Nome do diretorio");
        }
        this.name = name;
        this.listArchive = new ArrayList<Archive>();
        this.listDirectory = new ArrayList<Directory>();
    }

    public boolean hasDirectory(Directory directory) {
        return listDirectory.contains(directory);
    }

    public boolean hasArchive(Archive archive) {
        return listArchive.contains(archive);
    }

    public void addContent(Content file) throws AlreadyExistingContentException {
        file.setParent(this);
        if (file.isDirectory()){
            if (!hasDirectory((Directory) file))
                this.listDirectory.add((Directory) file);
            else
                throw new AlreadyExistingContentException(file.getName());
        }else {
            if (!hasArchive((Archive) file))
                this.listArchive.add((Archive) file);
            else
                throw new AlreadyExistingContentException(((Archive) file).getNameType());
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

    public boolean isEmpty() {
        if(this.listArchive.size() == 0 && this.listDirectory.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    //Getters
    public String getlistDirectory(){
        String names = "";
        String quebraLinha = System.getProperty("line.separator");
        for (int i = 0; i < this.listDirectory.size(); i++){
            names += this.listDirectory.get(i).getName() + quebraLinha;
        }
        return names;
    }

    public String getlistArchive(){
        String names = "";
        String quebraLinha = System.getProperty("line.separator");
        for (int i = 0; i < this.listArchive.size(); i++){
            names += this.listArchive.get(i).getName() + quebraLinha;
        }
        return names;
    }

    public List<Directory> getListDirectory(){
       return this.listDirectory;
    }

    public List<Archive> getListArchive(){
       return this.listArchive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
            return this.name;
        } else {
            return this.parent.getPath() + "/" + this.name;
        }
    }

    public Content getContent(String contentName) {
        if (contentName.contains(".")) {
            for (Archive archive : listArchive) {
                if (archive.getNameType().equals(contentName)) {
                    return archive;
                }
            }
        } else {
            for (Directory directory : listDirectory) {
                if (directory.getName().equals(contentName)) {
                    return directory;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Directory) {
            Directory directory = (Directory) object;
            return name.equals(directory.getName());
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    //ToString
    public String toString(){
        String text = getlistArchive() + getlistDirectory();
        if (text.equals("")){
            return "Pasta Vazia";
        }
        return text;
    }
}
