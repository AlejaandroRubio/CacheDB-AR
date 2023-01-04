package edai.Cache;
import edai.Cache.Lib.*;
import edai.Cache.Lib.TreeMap;
import edai.Cache.exceptions.DuplicatedKeyException;
import edai.Cache.exceptions.KeyNotFoundException;
import edai.Cache.exceptions.*;


public class CacheDB implements ICache{

    public CacheDB() {

    }

    private TreeMap cache = new TreeMap();

    @Override
    public String[] getAll() {
        return cache.keys();
    }


    //region Get & GetOrDefault
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
    //endregion

    //region Exists
    @Override
    public boolean exists(String key) {

        try {
            cache.get(key);
            return true;
        } catch (KeyNotFoundException e) {
            return false;
        }
    }
    //endregion

    //region Put & AddNew
    @Override
    public void put(String key, String value) {
        cache.put(key, value);

    }

    @Override
    public void addNew(String key, String value) throws DuplicatedKeyException {

        if (exists(key)) {
            throw new DuplicatedKeyException();
        } else {
            cache.put(key, value);
        }

    }
    //endregion

    //region Remove
    @Override
    public void remove(String key) throws KeyNotFoundException {
        if (!exists(key)) {
            throw new KeyNotFoundException();
        } else {
            cache.remove(key);

        }
    }
    //endregion

    @Override
    public int size() {
        return cache.size();
    }


}
