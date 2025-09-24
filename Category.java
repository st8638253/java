import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString(of = "name")
public class Category {
    private int id;
    private String name;
}
