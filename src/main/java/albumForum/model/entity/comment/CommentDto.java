package albumForum.model.entity.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    public CommentDto(Comment comment, boolean isUserThePublisher){
        this.isUserThePublisher = isUserThePublisher;
        id = comment.getId();
        message = comment.getMessage();
        publisherUsername = comment.getPublisherUsername();
        albumId = comment.getAlbumId();
    }

    private Long id;
    private String message;
    private String publisherUsername;
    private Long albumId;
    private boolean isUserThePublisher;
}
