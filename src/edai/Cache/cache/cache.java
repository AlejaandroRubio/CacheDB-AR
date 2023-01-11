package edai.Cache.cache;
import edai.Cache.exceptions.KeyNotFoundException;
import edai.Cache.exceptions.*;

import java.io.*;

public class cache {

    //./out/production/CacheDB-AlejandroRubio/edai/Cache/cache/data/
    //./out/production/CacheDB-AlejandroRubio/edai/Cache/cache/masterKey.txt

    //./src/edai/Cache/cache/data/
    //./src/edai/Cache/cache/masterKey.txt

    private static final String HashPath = "./";
    private static final String MasterKeyPath = "./masterKey.txt";

    public cache() {

    }

    //region GetData

    /**
     *
     This method takes a key as an input and returns the corresponding data in a text file, by generating the hash code for the key,
     concatenating it with ".txt" and looking for it in a specified file path (HashPath).
     @param key A string input which represents the key to retrieve data from.
     @throws KeyNotFoundException when the key input is not found in the file specified by HashPath.
     @throws IOException when there is an input/output error while reading the file.
     @return String the corresponding data if found, or null if not found.
     */

    public String getData(String key) throws KeyNotFoundException, IOException {
        String hash=String.format("%o", key.hashCode());


        try{
                FileReader fileReader = new FileReader(HashPath+hash+".txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line="";
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(hash)) {
                        line=line.replace( " <------ "+hash, "");
                        return line;
                    }
                }
                return null;

        } catch (FileNotFoundException e) {
            throw new KeyNotFoundException();
        }
    }
    //endregion

    //region SaveData

    /**
     *
     This method takes a key and value as inputs and saves them in a text file, by generating the hash code for the key,
     concatenating it with ".txt" and saving the value in the file.
     It also saves the key in a master key file if it does not already exist.
     @param key A string input which represents the key to save data with.
     @param value A string input which represents the data to be saved.
     @throws IOException when there is an input/output error while writing to the file.
     */

    public void saveData(String key, String value) throws IOException {
        String hash=String.format("%o", key.hashCode());

        FileWriter fileWriter = new FileWriter(HashPath+hash+".txt");
        fileWriter.write(String.format(value+" <------ %o",key.hashCode()));
        fileWriter.write('\n');
        fileWriter.close();


        if (!existsKey(key)){
            FileWriter masterKeyWriter = new FileWriter(MasterKeyPath,true);
            masterKeyWriter.write(String.format(key+" <------ %o",key.hashCode()));
            masterKeyWriter.write('\n');
            masterKeyWriter.close();
        }

    }

    //endregion

    //region DeleteData

    /**
     *
     This method takes a key as an input and deletes the corresponding data in a text file, by generating the hash code for the key,
     concatenating it with ".txt" and looking for it in a specified file path (HashPath) and deletes the file if it is found.
     @param key A string input which represents the key to delete data with.
     @throws FileNotFoundException when the key input is not found in the file specified by HashPath.
     */

    public void deleteData(String key) throws FileNotFoundException {
        String hash=String.format("%o", key.hashCode());

            try{
                FileReader fileReader = new FileReader(HashPath+hash+".txt");
                fileReader.close();
                File file = new File(HashPath+hash+".txt");
                boolean p=file.delete();
                System.out.println(p);
            }
            catch (IOException e) {
                throw new FileNotFoundException();
            }

    }
    //endregion

    //region GetKey

    /**
     *
     This method takes a hash as an input and returns the corresponding key in a master key file, by looking for the hash in the file specified by MasterKeyPath
     @param hash A string input which represents the hash code to retrieve key from.
     @throws KeyNotFoundException when the hash input is not found in the master key file specified by MasterKeyPath.
     @throws IOException when there is an input/output error while reading the master key file.
     @return String the corresponding key if found, or null if not found.
     */

    public String getKey(String hash) throws KeyNotFoundException, IOException {

                FileReader fileReader = new FileReader(MasterKeyPath);
                String linea="";
                BufferedReader BufferR = new BufferedReader(fileReader);

                while ((linea=BufferR.readLine())!=null) {
                    if (linea.contains(hash)){
                        linea=linea.replace(" <------ "+hash,"");
                        //System.out.println(linea);
                        return linea;
                    }
                }
                throw new KeyNotFoundException();
    }
    //endregion

    //region Exists & ExistsInMasterKey

    /**
     *
     This method takes a key as an input and checks if it already exists in the master key file by looking for the key in the file specified by MasterKeyPath
     @param key A string input which represents the key to check existance of
     @throws IOException when there is an input/output error while reading the master key file.
     @return boolean true if key is found in the master key file, false otherwise.
     */

    public boolean existsKey(String key) throws IOException {
        try{
            FileReader fileReader = new FileReader(MasterKeyPath);
            String linea="";
            BufferedReader BufferR = new BufferedReader(fileReader);

            while ((linea=BufferR.readLine())!=null) {
                if (linea.contains(key)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        }

        return false;
    }



    /**
    *
    This method takes a hash code as an input and checks if the corresponding data file already exists in a specified path (HashPath) by opening the file.
    @param hashCode A string input which represents the hash code to check existance of
    @throws KeyNotFoundException when the file is not found in the HashPath
    @return boolean true if file is found in the HashPath, false otherwise.
    */

    public boolean exists(String hashCode) throws KeyNotFoundException{

        try {
            FileReader fileReader = new FileReader(HashPath+".txt");
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
//endregion






}
