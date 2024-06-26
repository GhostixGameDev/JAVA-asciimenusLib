package ar.com.ghostix.lib.asciimenus;
import java.util.Scanner;
import java.lang.reflect.*;

//Automatic ascii menus library for my highschool assignments.
public class MainMenu
{
    private String name;
    private Object[] objects;
    private String pattern;
    private int options;
    //CLASS CONSTRUCTOR
    public MainMenu(String Name, String itemsName, Object[] Objects)
    {
        name = Name;
        options = Objects.length + 1;
        objects = Objects;
        pattern = itemsName;
        
    }
    //Get and set Methods
    public String getName(){
        return name;
    }
    public void setName(String newValue){
        name = newValue;
    }
    public String getPattern(){
        return pattern;
    }
    public void setPattern(String newValue){
        pattern = newValue;
    }
    public Object[] getObjects(){
        return objects;
    }
    public void setObjects(Object[] newValues){
        if(newValues.length <= objects.length){
            objects = newValues;
        }else{
            System.out.println("Array out of length");
        }
    }
    public int getOptions(){
        return options;
    }
    public void setOptions(int newValue){
        options = newValue;
    }
    //Main method
    public void run(Scanner scan){
        int width = 22 + getName().length(); //11 spaces for the right and left sides of the name
        int height = 7;
        int option = 0;
        Class[] cArg = new Class[1];
        cArg[0] = Scanner.class; //We save this for the run method reflection.
        while(option!=getOptions()){
            //Title printing
            String line="";
            for(int i = 0; i<width; i++){
                line = line + "=";
            }
            System.out.println(line);
            for(int y = 0; y<height; y++){
                if(y!=3){
                    //White spaces
                    line = "|";
                    for(int x = 1; x<width-1; x++){
                        line = line + " ";
                    }
                    line = line + "|";
                    System.out.println(line);
                }else{
                    //We print the name
                    line = "|";
                    for(int x = 0; x<10; x++){
                        line = line + " ";
                    }
                    line = line + getName();
                    int actualLength = line.length();
                    for(int x = actualLength; x<width - 1; x++){
                        line = line + " ";
                    }
                    line = line + "|";
                    System.out.println(line);
                }
            }
            line = "";
            for(int i = 0; i<width; i++){
                line = line + "=";
            }
            System.out.println(line);
            //Title finished, now we print the options.
            for(int i = 1; i<getOptions(); i++){
                System.out.println(i + "- "+ getPattern() + i + ".");
            }
            System.out.println(getOptions() + "- Salir.");
            System.out.println("==========================");
            System.out.println("Selecciona una opción.");
            option = scan.nextInt();
            //We prompt the user to input and then call the respective method or close the program.
            if(option!=getOptions() && option>0 && option<getOptions()){
                try{
                    Method method = getObjects()[option-1].getClass().getMethod("run", cArg);
                    method.invoke(getObjects()[option-1], scan);
                }catch(Exception error){
                    error.printStackTrace();
                    System.out.println("TODOS TUS OBJETOS EN EL MENU DEBEN TENER UN METODO RUN Y QUE SEA PUBLICO.");
                }
            }else{
                if(option!=getOptions()){
                    System.out.println("Opcion invalida.");
                }else{
                    System.out.println("Cerrando el programa.");
                }
            }
        }
    }

}
