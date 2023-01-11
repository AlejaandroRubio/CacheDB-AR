package edai.Cache;
import edai.Cache.Lib.*;
import edai.Cache.Lib.TreeMap;
import edai.Cache.exceptions.DuplicatedKeyException;
import edai.Cache.exceptions.KeyNotFoundException;
import edai.Cache.exceptions.*;

import java.io.FileNotFoundException;


public class CacheDB implements ICache{

    public CacheDB() {

    }

    private TreeMap cache = new TreeMap();


    /**
     * Get all keys stored in cache.
     * @return array of stored keys
     */
    @Override
    public String[] getAll() {
        return cache.keys();
    }


    //region Get & GetOrDefault

    /**
     * Get the value associated with the key passed as argument.
     * @param key Key to look for
     * @return The value associated with the key
     * @throws KeyNotFoundException if key does not exist.
     */

    @Override
    public String get(String key) {
        try {
            return cache.get(key);
        } catch (KeyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return the value of key passed as argument. Otherwise, return the
     * default value passed as second argument.
     * @param key Key to look for
     * @param defaultValue Value returned when key does not exist.
     * @return The value associated with the key or the defaultValue if key was not
    found.
     */

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

    /**
     * Check is a key exists in cache.
     * @param key Key to look for
     * @return True if key exists.
     */

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

    /**
     * Add or update the value associated to a key.
     * @param key Key to be stored.
     * @param value Value to be stored.
     */

    @Override
    public void put(String key, String value) {
        cache.put(key, value);

    }


    /**
     * Add a value to a new key. If key already exists, it throws an exception.
     * @param key Key to be stored.
     * @param value Value to be stored.
     * @throws DuplicatedKeyException the key already exists.
     */

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

    /**
     * Remove a key and its value.
     * @param key Key to be stored.
     * @throws KeyNotFoundException if key does not exist.
     */

    @Override
    public void remove(String key) throws KeyNotFoundException {
       /*if (exists(key)) {
           try {
               cache.remove(key);
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
       } else {
              throw new KeyNotFoundException();
       }*/

            try {
                cache.remove(key);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    //endregion

    /**
     * Count the keys (and values) stored in cache.
     * @return Count of keys.
     */

    @Override
    public int size() {
        return cache.size();
    }


}
