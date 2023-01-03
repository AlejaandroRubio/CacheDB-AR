package Lib;

//region MapEntry
public class MapEntry implements Comparable<MapEntry> {

    private final String key;
    private final String value;

    public MapEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(MapEntry other) {
        return this.key.compareTo(other.key);
    }

    //endregion

}
