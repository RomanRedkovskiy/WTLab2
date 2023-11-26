package albumForum.model.entity.user;

import albumForum.model.entity.interfaces.Entity;
import lombok.Data;

@Data
public class User implements Entity<Long> {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String bio;
    private Role role;
    private Status status;

    @Override
    public Long getId(){
        return id;
    }
}
