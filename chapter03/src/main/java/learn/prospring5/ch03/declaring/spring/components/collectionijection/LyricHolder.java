package learn.prospring5.ch03.declaring.spring.components.collectionijection;

public class LyricHolder implements ContentHolder{
    private String value = "'You be the DJ, I'll be the driver'";
    @Override public String toString() {
        return "LyricHolder: { " + value + "}";
    }
}