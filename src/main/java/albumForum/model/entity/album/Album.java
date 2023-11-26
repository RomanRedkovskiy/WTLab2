package albumForum.model.entity.album;

import albumForum.model.entity.interfaces.Entity;
import lombok.Data;

import java.sql.Date;


@Data
public class Album implements Entity<Long> {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Date releaseDate;
}
