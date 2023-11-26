package albumForum.service.factory;

import albumForum.model.encoder.implementations.DefaultEncoder;
import albumForum.model.encoder.interfaces.Encoder;

public class AuxiliaryFactory {

    private static final Encoder encoder = new DefaultEncoder();

    public static Encoder getEncoder(){
        return encoder;
    }
}
