package albumForum.model.encoder.implementations;

import albumForum.model.encoder.interfaces.Encoder;

import java.util.Base64;

public class DefaultEncoder implements Encoder {
    @Override
    public String encode(String str) {
        return new String(Base64.getEncoder().encode(str.getBytes()));
    }

    @Override
    public String decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }
}
