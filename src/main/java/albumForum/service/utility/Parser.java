package albumForum.service.utility;

import java.util.Optional;

public class Parser {

    public static Optional<Long> tryParse(String str){
        Optional<Long> result;
        try{
            result = Optional.of(Long.parseLong(str));
        } catch (NumberFormatException e){
            result = Optional.empty();
        }
        return result;
    }
}
