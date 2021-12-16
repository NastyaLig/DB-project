package bgv.fit.bstu.eday.Models;

public class Task {
    private Integer id;
    private String name;
    private String description;
    private String date;
    private String time;
    private Integer uid;

    public Integer getId() {
        return id;
    }
    public String getName() {return name; }
    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {return time; }
    public Integer getUserId() {
        return uid;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {this.description=description;}
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setUserId(Integer uid) {
        this.uid = uid;
    }
}
