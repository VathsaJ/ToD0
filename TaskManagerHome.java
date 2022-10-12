import java.util.*;
import java.io.*;

public class TaskManagerHome {
    public static void main(String[] args) {
       CustomLogger lg = CustomLogger.getInstance();
       lg.changeFile("TaskManagerApp");

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        File f = new File("C:\\Project\\TaskManager");


        int ch1 = 0,ch2 = 0,ch3 = 0,priority;
        String task,cat,desc,date,tags;


        while(ch1!=7)
        {
            System.out.println("Enter the option");
            System.out.println("1.Create/Edit1 category");
            System.out.println("2.Load category");
            System.out.println("3.Remove");
            System.out.println("4.List");
            System.out.println("5.Search");
            System.out.println("6.Export");
            System.out.println("7.Exit");

            ch1 = sc1.nextInt();

            switch (ch1)
            {
                case 1:
                {
                    System.out.println("Enter the Category name(1word)");
                    cat = sc2.nextLine();
                    // Input validate and business validate
                    while (VathsaUtil.validateName(cat) == false && VathsaTaskModel.doesFileExist(cat))
                    {
                        System.out.println("Name is invalid / already exists ...Please enter valid unique name");
                        sc2.nextLine();
                    }
                    while (ch2 != 6)
                    {
                        System.out.println("Enter option");
                        System.out.println("1 Create Task");
                        System.out.println("2 Update Task");
                        System.out.println("3 Remove Task");
                        System.out.println("4 List Tasks");
                        System.out.println("5 Search Task");
                        System.out.println("6 Main menu");

                        ch2 = sc1.nextInt();

                        switch (ch2)
                        {
                            case 1: {
                                lg.log("Creating task....");
                                System.out.println("Enter the TaskName");
                                task = sc2.nextLine();
                                while (!VathsaUtil.validateName(task)) {
                                    System.out.println("Enter valid name");
                                    task = sc2.nextLine();
                                }
                                System.out.println("Enter end date");
                                date = sc2.nextLine();
                                System.out.println("Enter priority 1-low || 10 - high");
                                priority = sc1.nextInt();
                                while(!VathsaUtil.validatePriority(priority)){
                                    System.out.println("Enter Integer btw 1 and 10");
                                    priority=sc1.nextInt();
                                }
                                System.out.println("Enter Discription of the task");
                                desc = sc2.nextLine();
                                System.out.println("Enter comma separated tags");
                                tags = sc2.nextLine();
                                TaskBean taskObj = new TaskBean(task,desc,priority,date,tags);
                                String result = VathsaTaskModel.addTask(taskObj,cat);

                                if (result.equals("Success") )
                                {
                                    System.out.println(result);
                                }else {
                                    System.out.println(result);
                                }
                                break;
                            }
                            case 2:
                            {
                                String updTask;
                                System.out.println("Enter taskname to be updtated");
                                updTask = sc2.nextLine();
                                TaskBean temp = VathsaTaskModel.getTask(cat,updTask);
                                if (temp == null){
                                    System.out.println("NO such task found");
                                    System.out.println();
                                    break;
                                }
                                while(ch3 != 6){
                                    System.out.println(temp);
                                    System.out.println("Enter the field to be changed....Make sure to save before exiting");
                                    System.out.println("0.Taskname");
                                    System.out.println("1.Description");
                                    System.out.println("2.Priority");
                                    System.out.println("3.Date");
                                    System.out.println("4.Tags");
                                    System.out.println("5.Save");
                                    System.out.println("6.Go back....");

                                    ch3 = sc1.nextInt();

                                    switch (ch3){
                                        case 0 :
                                        {

                                            System.out.println("Enter new taskName");
                                            task = sc2.nextLine();
                                            while(!VathsaUtil.validateName(task))
                                            {
                                                System.out.println("Enter valid name");
                                                task = sc2.nextLine();
                                            }
                                                temp.setTaskName(task);
                                            System.out.println("Successful");
                                                break;
                                            }
                                        case 1:
                                        {
                                            System.out.println("Enter Description");
                                            desc = sc2.nextLine();
                                            temp.setDesc(desc);
                                            System.out.println("Successful");
                                            break;
                                        }
                                        case 2:{
                                            System.out.println("Enter the new priority");
                                            priority = sc1.nextInt();
                                            while(!VathsaUtil.validatePriority(priority)){
                                                System.out.println("Priority can be within 1(low)and10(High)");
                                                sc1.nextLine();
                                            }
                                            temp.setPriority(priority);
                                            System.out.println("Successful");
                                            break;
                                        }
                                        case 3:
                                        {
                                            System.out.println("Enter new planned end date");
                                            date = sc2.nextLine();
                                            temp.setDate(date);
                                            System.out.println("Successful");
                                            break;
                                        }
                                        case 4:{
                                            System.out.println("Enter new tags(separate tags using comma");
                                            tags = sc2.nextLine();
                                            temp.setTags(tags);
                                            System.out.println("Successful");
                                            break;
                                        }
                                        case 5:{
                                            String res = VathsaTaskModel.updateTask(cat,updTask,f,temp);
                                            System.out.println(res);
                                            break;
                                        }
                                        case 6:
                                        {
                                            System.out.println("Going back");
                                            System.out.println();
                                            break;
                                        }
                                    }

                                } break;

                            }
                            case 3 :
                            {
                                System.out.println("Enter Task name to be removed");
                                String remName = sc2.nextLine();
                                while(!VathsaUtil.validateName(remName))
                                {
                                    System.out.println("Enter valid name");
                                    remName = sc2.nextLine();
                                }
                                String res = VathsaTaskModel.removeTask(cat,remName,f);
                                System.out.println(res);
                                break;
                            }
                            case 4:
                            {
                                List<TaskBean> list = VathsaTaskModel.getTask(cat);
                                if (list == null) System.out.println("Category doesnt exist");
                                else System.out.println(list);
                                break;
                            }
                            case 5:{
                                System.out.println("Enter Taskname to search");
                                String taskName = sc2.nextLine();
                                while(!VathsaUtil.validateName(taskName)){
                                    System.out.println("Enter valid name");
                                    sc2.nextLine();
                                }
                                TaskBean resTask = VathsaTaskModel.getTask(cat,taskName);
                                if(resTask != null){
                                    System.out.println(resTask);
                                }
                                else{
                                    System.out.println("Task not found");
                                }

                                break;
                            }
                            case 6:
                            {
                                System.out.println("Going back");
                                System.out.println();
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2 :
                {
                    System.out.println("Categories are");
                    File[] lists =f.listFiles();
                    for(File a : lists){
                        System.out.println(a.getName());
                    }
                    System.out.println("Enter category name to display tasks");
                    cat = sc2.nextLine();
                    List<TaskBean> list = VathsaTaskModel.getTask(cat);
                    if (list == null) System.out.println("Category doesnt exist");
                    else System.out.println(list);
                    break;
                }
                case 3 :{

                    List<String> files = VathsaTaskModel.getCategory(f);
                    System.out.println(files);
                    System.out.println("Enter the Catagory name to remove");
                    String catName = sc2.nextLine();
                    while(!VathsaUtil.validateName(catName))
                    {
                        System.out.println("Enter valid name");
                        catName = sc2.nextLine();
                    }
                    if(files.contains(catName+".txt")){
                        boolean res = VathsaTaskModel.removeCat(catName,f);
                        if(res == true) System.out.println("Succesfull");
                        else System.out.println("Unsuccesfull");
                    }else System.out.println("File doesnt exist");


                    break;
                }
                case 4 :{
                    List<String> files = VathsaTaskModel.getCategory(f);
                    System.out.println(files);
                    break;
                }
                case 5 :
                {
                    System.out.println("Enter the category name ");
                    String searchName = sc2.nextLine();
                    while(!VathsaUtil.validateName(searchName))
                    {
                        System.out.println("Enter valid name");
                        searchName = sc2.nextLine();
                    }
                    List<String> files = VathsaTaskModel.getCategory(f);
                    if( files.contains(searchName+".txt")){
                        List<TaskBean> list = VathsaTaskModel.getTask(searchName);
                        System.out.println(list);
                    }
                    else
                    {
                        System.out.println("Catogory doesnt exist");
                    }


                }
                case 6 :{

                }
                case 7 : {

                    System.exit(0);

                }
                default : {
                    System.out.println("Option not available yet");
                    break;
                }
            }
        }
    }
}