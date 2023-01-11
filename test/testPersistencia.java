import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edai.Cache.cache.cache;
import edai.Cache.exceptions.KeyNotFoundException;
import edai.Cache.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class testPersistencia {


    cache cache= new cache();

   @Test

    public void GetData() throws KeyNotFoundException, IOException {
        cache.saveData("1", "value1");
        cache.saveData("2", "value2");
        cache.saveData("3", "value3");
        cache.saveData("4", "value4");
        cache.saveData("5", "value5");

        assertEquals("value1", cache.getData("1"));
        assertEquals("value2", cache.getData("2"));
        assertEquals("value3", cache.getData("3"));
        assertEquals("value4", cache.getData("4"));
        assertEquals("value5", cache.getData("5"));
    }

    @Test

    public void SaveData() throws KeyNotFoundException, IOException {
        cache.saveData("1", "value1");
        cache.saveData("2", "value2");
        cache.saveData("3", "value3");
        cache.saveData("4", "value4");
        cache.saveData("5", "value5");
        assertTrue(cache.existsKey("1"));
        assertTrue(cache.existsKey("2"));
        assertTrue(cache.existsKey("3"));
        assertTrue(cache.existsKey("4"));
        assertTrue(cache.existsKey("5"));
    }

  @Test
    public void GetKeys() throws KeyNotFoundException, IOException {
        cache.saveData( "1", "value1");
        String hash = String.format("%o", "1".hashCode());
        assertEquals("1", cache.getKey(hash));
    }

    @Test
    public void deleteData() throws IOException {
        cache.saveData("6", "value6");
        cache.deleteData("6");
        try {
            cache.deleteData("6");
            fail( "Expected FileNotFoundException" );
        } catch (FileNotFoundException e) {
            // expected
        }
    }
}
