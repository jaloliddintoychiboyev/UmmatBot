import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    private Integer id;
    private String firstname;
    private String username;
    private String chat_id;
}
