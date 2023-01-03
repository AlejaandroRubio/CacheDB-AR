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
        TreeNode<MapEntry> entry = tree.search(new MapEntry(key, null));
        if (entry == null) {
            throw new KeyNotFoundException();
        };
        return entry.getData().getValue();
        }

    //endregion

    //region Remove
    public void remove(String key) {
        MapEntry entry = new MapEntry(key, null);
        tree.remove(entry);
    }

    //endregion
}

