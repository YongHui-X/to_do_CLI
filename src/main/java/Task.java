import lombok.Data;

@Data
public class Task {
    private int id;
    private String status;
    private String description;
    private String createdAt;
    private String updatedAt;

    public Task(int id, String status, String description, String createdAt,  String updatedAt){
        this.id = id;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Task(){}



}
