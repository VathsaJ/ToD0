import java.io.*;
import java.util.*;


public class CustomLogger
{

    public static CustomLogger log = null;
    public static String filename = "General";

    private CustomLogger()
    {

    }

    public void log(String data){
        BufferedWriter wr = null;
        Date dt = new Date();
        String msg = dt +" "+ data;
        try {

            wr = new BufferedWriter(new FileWriter("C:\\Project\\CustomLog\\"+filename+".txt",
                    true));
            wr.write(msg);
            wr.newLine();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                wr.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

     public static CustomLogger getInstance(){
        if (log == null)
            log = new CustomLogger();

        return log;
    }

    public void changeFile(String name){
        if(VathsaUtil.validateName(name))
            filename = name;
        else
        {
            System.out.println("(Logger)Invalid file name");
        }

    }
}
