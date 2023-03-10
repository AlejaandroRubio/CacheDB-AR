import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edai.Cache.CacheDB;
import edai.Cache.exceptions.DuplicatedKeyException;
import edai.Cache.exceptions.KeyNotFoundException;
import edai.Cache.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class testCache {

    private CacheDB cache;

    @BeforeEach
    public void setUp() {
        cache = new CacheDB();
        cache.put( "1", "value1");
        cache.put( "2", "value2");
        cache.put( "3", "value3");
        cache.put( "4", "value3");
        cache.put( "5", "value3");
    }

    @Test
    public void testGetAll() {
        String[] keys = cache.getAll();
        assertEquals(5, keys.length);
        assertEquals("1", keys[0]);
        assertEquals("2", keys[1]);
        assertEquals("3", keys[2]);
        assertEquals("4", keys[3]);
        assertEquals("5", keys[4]);
    }

    @Test
    public void testGet() {
        assertEquals("value1", cache.get("1"));
        assertEquals("value2", cache.get("2"));
        assertEquals("value3", cache.get("3"));
        assertEquals("value3", cache.get("4"));
        assertEquals("value3", cache.get("5"));
    }

    @Test
    public void testGetOrDefault() {
        assertEquals("value1", cache.getOrDefault("1", "default"));
        assertEquals("value2", cache.getOrDefault("2", "default"));
        assertEquals("value3", cache.getOrDefault("3", "default"));
        assertEquals("value3", cache.getOrDefault("4", "default"));
        assertEquals("value3", cache.getOrDefault("5", "default"));
    }

    @Test
    public void remove() throws KeyNotFoundException {
        cache.remove("1");
        assertEquals(4, cache.getAll().length);
    }

    @Test
    public void exists() {
        assertTrue(cache.exists("1"));
        assertTrue(cache.exists("2"));
        assertTrue(cache.exists("3"));
        assertTrue(cache.exists("4"));
        assertTrue(cache.exists("5"));
    }


    @Test
    public void addNew() throws DuplicatedKeyException {
        String key = "6";
        String hash = String.format("%o", key.hashCode());
        File file = new File("./"+hash+".txt");
        file.delete();
        cache.addNew("6", "value6");
        assertTrue(cache.exists("6"));
        try {
            cache.addNew("6", "value6");
        } catch (DuplicatedKeyException e) {
        }


        /*try {
                cache.addNew("6", "value7");
                fail("DuplicatedKeyException expected");
        } catch (DuplicatedKeyException e) {
            // expected
        }*/
    }

    @Test
    public void size() {
        assertEquals(5, cache.size());
    }


}
