package albumForum.config;

import java.util.List;

public class AuthenticationFilterConfiguration {

    private static volatile AuthenticationFilterConfiguration instance;
    private List<String> urlsWithRequiredAuthentication;

    private AuthenticationFilterConfiguration(){}

    public static AuthenticationFilterConfiguration getInstance(){
        if(instance == null){
            synchronized (AuthenticationFilterConfiguration.class){
                if(instance == null){
                    instance = new AuthenticationFilterConfiguration();
                }
            }
        }
        return instance;
    }

    public List<String> getUrlsWithRequiredAuthentication() {
        return urlsWithRequiredAuthentication;
    }
}
