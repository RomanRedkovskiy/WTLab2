package albumForum.model.attributesholder.implementation;

import albumForum.model.attributesholder.interfaces.AttributesHolder;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HttpSessionAttributesHolder implements AttributesHolder {

    private final HttpSession httpSession;

    @Override
    public Object getAttribute(String name) {
        synchronized (httpSession) {
            return httpSession.getAttribute(name);
        }
    }

    @Override
    public void setAttribute(String name, Object obj) {
        synchronized (httpSession) {
            httpSession.setAttribute(name, obj);
        }
    }

    @Override
    public boolean containsAttribute(String name) {
        synchronized (httpSession) {
            return httpSession.getAttribute(name) != null;
        }
    }

    @Override
    public void deleteAttribute(String name) {
        synchronized (httpSession) {
            httpSession.removeAttribute(name);
        }
    }

    @Override
    public Object getSynchronizationObject() {
        return httpSession;
    }
}
