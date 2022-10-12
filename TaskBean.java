import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class TaskBean implements Comparable
{
    @Override
    public String toString() {
        return taskName+"::"+desc+"::"+priority+"::"+date+"::"+tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskBean)) return false;
        TaskBean taskBean = (TaskBean) o;
        return priority == taskBean.priority && Objects.equals(desc, taskBean.desc) && date.equals(taskBean.date) && tags.equals(taskBean.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, priority, date, tags);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private String taskName;
    private String desc;
    private int priority;
    private String date;
    private String tags;

    public TaskBean() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskBean(String taskName, String desc, int priority, String date, String tags) {
        this.taskName = taskName;
        this.desc = desc;
        this.priority = priority;
        this.date = date;
        this.tags = tags;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
