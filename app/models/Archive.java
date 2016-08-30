package models;

import  exceptions.*;
/**
 * Created by Suelany on 05/08/2016.
 */
public class Archive implements Content {
    private String name;
    private String texto;
    private Directory parent;


    public Archive(){
        this.texto = "";
    }

    public Archive(String name){
        this.name = standardName(name);
        this.texto = "";
    }

    public Archive(String name, String texto){
        this.name = standardName(name);
        this.texto = texto;
    }


    public String getName(){
        return this.name;
    }

    public String getArchiveName() {
        return  this.name;
    }

    public void setName(String novoNome) {
        this.name = standardName(novoNome);
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String novoTexto) {
        this.texto = novoTexto;
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

    public boolean isDirectory(){
        return false;
    }


    // Este metodo remove os caracteres em branco do inicio do titulo.
    private static String standardName(String nomeDoArquivo){
        int indexCharacter = 0;
        while( nomeDoArquivo.charAt(indexCharacter) == ' '  ){
            indexCharacter++;
        }
        String newName = nomeDoArquivo.substring(indexCharacter, (nomeDoArquivo.length()));
       if (newName.endsWith(".txt") || newName.endsWith(".md")) {
              return  newName;
        }

        //default aq, so um place holder pq ainda não tem a caixinha la.
else {
        return newName + ".txt";
    }

    }



}
