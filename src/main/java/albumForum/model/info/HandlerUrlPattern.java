package albumForum.model.info;

import java.util.regex.Pattern;

public record HandlerUrlPattern(Pattern pattern, String basicUrl) {
}
