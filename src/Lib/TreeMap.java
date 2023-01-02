package Lib;


import exceptions.KeyNotFoundException;

public class TreeMap {
    private final BinaryTree<MapEntry> tree = new BinaryTree<>();

    public int size(){
        return tree.size();
    }

    public boolean isEmpty(){
        return tree.isEmpty();
    }

    //region Keys & Values
    public String[] keys(){
        Object[] data = tree.listData();
        String[] key = new String[data.length];

        for (int i = 0; i < data.length; ++i) {
            key[i] = ((MapEntry) data[i]).getKey();
        }

        return key;
    }

    public String[] values(){
        Object[] data = tree.listData();
        String[] value = new String[data.length];

        for (int i = 0; i < data.length; ++i) {
            value[i] = (String) ((MapEntry) data[i]).getValue();
        }

        return value;
    }

    //endregion

    //region Put & Get

    public void put(String key, String value) {
        MapEntry newEntry = new MapEntry(key, value);
        tree.insert(newEntry);

    }

    public String get(String key) throws KeyNotFoundException {
        MapEntry entry = new MapEntry(key, null);
        if (entry == null) {
            throw new KeyNotFoundException();
        };
        return entry.getValue();
        }


    public void remove(String key) {
        MapEntry entry = new MapEntry(key, null);
        tree.remove(entry);
    }
}

    //endregion

    //region MapEntry
    class MapEntry implements Comparable<MapEntry> {

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

