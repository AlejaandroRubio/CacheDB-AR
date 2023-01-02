import Lib.*;
import exceptions.KeyNotFoundException;

public class CacheDB implements ICache{

    private TreeMap cache;

    /**
     * Get all keys stored in cache.
     * @return array of stored keys
     */
    @Override
    public String[] getAll() {
        return cache.keys();
    }

    @Override
    public String get(String key) {
        try {
            return cache.get(key);
        } catch (KeyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getOrDefault(String key, String defaultValue) {
        try {
            return cache.get(key);
        } catch (KeyNotFoundException e) {
            return defaultValue;
        }
    }

    @Override
    public boolean exists(String key) {

        try {
            cache.get(key);
            return true;
        } catch (KeyNotFoundException e) {
            return false;
        }
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, value);

    }

    @Override
    public void addNew(String key, String value) {
        

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public int size() {
        return 0;
    }
}
