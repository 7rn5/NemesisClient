package nemesis.mixininterface;

public interface IChatHudLine {
    void nemesis$setId(int id);
    int nemesis$getId();
    
    default void nemesis$setIndicatorPath(String path) {}
    default String nemesis$getIndicatorPath() {
        return null;
    }
}