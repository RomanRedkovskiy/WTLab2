package albumForum.model.attributesholder.interfaces;

public interface AttributesHolder {

    Object getAttribute(String name);

    void setAttribute(String name, Object obj);

    boolean containsAttribute(String name);

    void deleteAttribute(String name);

    Object getSynchronizationObject();
}
