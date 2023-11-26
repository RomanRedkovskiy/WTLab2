package albumForum.model.entity.comment;

import albumForum.model.entity.interfaces.Entity;
import lombok.Data;

@Data
public class Comment implements Entity<Long> {

    private Long id;
    private String message;
    private String publisherUsername;
    private Long albumId;
}
