import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class VathsaTaskModel {
    public static boolean doesFileExist(String catName) {

        return new File("C:\\Project\\TaskManager\\" + catName + ".txt").exists();
    }

    public static String addTask(TaskBean task, String catName) {

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(("C:\\Project\\TaskManager\\" + catName + ".txt"), true));
            Date dt = new Date();
            bw.write(task.getTaskName() + "::" + task.getDesc() + "::" + task.getPriority() + "::" + task.getDate() + "::" + task.getTags() + "::" + dt.getTime());
            bw.newLine();
            return "Success";

        } catch (IOException e) {
            return "Ooops.....Some Error occured " + e.getMessage();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<TaskBean> getTask(String catName) {
        BufferedReader br = null;
        String line;
        List<TaskBean> list = new ArrayList<TaskBean>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

        try {

            if (new File("C:\\Project\\TaskManager\\" + catName + ".txt").exists()) {
                br = new BufferedReader(new FileReader("C:\\Project\\TaskManager\\" + catName + ".txt"));
                while ((line = br.readLine()) != null) {
                    String[] a = line.split("::");
                    TaskBean task = new TaskBean(a[0], a[1], Integer.valueOf(a[2]), a[3], a[4]);
                    list.add(task);
                }
                return list;
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static TaskBean getTask(String catName, String taskName) {
        BufferedReader br = null;
        String line;
        TaskBean task = null;

        try {

            if (new File("C:\\Project\\TaskManager\\" + catName + ".txt").exists()) {
                br = new BufferedReader(new FileReader("C:\\Project\\TaskManager\\" + catName + ".txt"));
                while ((line = br.readLine()) != null) {
                    String[] a = line.split("::");

                    if (a[0].equals(taskName))
                        task = new TaskBean(a[0], a[1], Integer.valueOf(a[2]), a[3], a[4]);
                }
                return task;
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String updateTask(String catName, String taskName, File f, TaskBean t) {
        BufferedReader br = null;
        String line;

        try {
            File f1 = new File(f + "//" + catName + ".txt");
            br = new BufferedReader(new FileReader(f1));


            while ((line = br.readLine()) != null) {
                String a[] = line.split("::");
                if (a[0].equals(taskName)) {
                    addTask(t, "temp");
                } else {
                    t = new TaskBean(a[0], a[1], Integer.valueOf(a[2]), a[3], a[4]);
                    addTask(t, "temp");
                }
            }
            br.close();
            File temp = new File(f + "//temp.txt");
            f1.delete();
            temp.renameTo(f1);


            return "Success";
        } catch (Exception e) {
            return "Oops...Something went wrong" + e.getMessage();
        }

    }

    public static List<String> getCategory(File f) {

        File[] fileArray = f.listFiles();
        List<String> fileList = new ArrayList<String>();

        for (File af : fileArray){
            String[] splittedName = af.getName().split("/");
            fileList.add(splittedName[splittedName.length-1]);
        }
        return fileList;

    }

    public static boolean removeCat(String catName, File f) {
        File newFile = new File(f + "//" + catName + ".txt");
        return (newFile.delete());

    }

    public static String removeTask(String catName, String taskName, File f){
        BufferedReader br = null;
        String line;

        try {
            File f1 = new File(f + "//" + catName + ".txt");
            br = new BufferedReader(new FileReader(f1));
            while ((line = br.readLine()) != null) {
                String a[] = line.split("::");
                if (a[0].equals(taskName)) {
                } else  {
                    TaskBean t = new TaskBean(a[0], a[1], Integer.valueOf(a[2]), a[3], a[4]);
                    addTask(t, "temp");
                }
            }
            br.close();
            File temp = new File(f + "//temp.txt");
            f1.delete();
            temp.renameTo(f1);
            return "Success";
        } catch (Exception e) {
            return "Oops...Something went wrong" + e.getMessage();
        }
    }
}
