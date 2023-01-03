package cache;
import exceptions.*;

import java.io.*;

public class cache {

    private static final String HashPath = "./src/cache/data/";
    private static final String MasterKeyPath = "./src/cache/masterKey.txt";

    public cache() {

    }

    //region GetData
    public static String getData(String key) throws KeyNotFoundException, IOException {
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
    public static void saveData(String key, String value) throws IOException {
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

    //region GetKey
    public static String getKey(String hash) throws KeyNotFoundException, IOException {

                FileReader fileReader = new FileReader(MasterKeyPath);
                String linea="";
                BufferedReader BufferR = new BufferedReader(fileReader);

                while ((linea=BufferR.readLine())!=null) {
                    if (linea.contains(hash)){
                        linea=linea.replace(" <------ "+hash,"");
                        System.out.println(linea);
                        return linea;
                    }
                }
                throw new KeyNotFoundException();
    }
    //endregion


    //region Exists & ExistsInMasterKey
    public static boolean existsKey(String key) throws IOException {
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



    public static boolean exists(String hashCode) throws KeyNotFoundException{

        try {
            FileReader fileReader = new FileReader(HashPath+".txt");
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
//endregion






}
