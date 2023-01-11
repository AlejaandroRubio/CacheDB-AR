package edai.Cache.App;

import edai.Cache.CacheDB;
import edai.Cache.exceptions.KeyNotFoundException;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

@Command(name = "edai/Cache/cache", mixinStandardHelpOptions = true)

class AppCli implements Runnable{


        private CacheDB cache = new CacheDB();

        @Option(names = {"-a", "--add"}, description = "Add an item to the cache")
        private String key;

        @Option(names = {"-v", "--value"}, description = "The value to add to the cache")
        private String value;

        @Option(names = {"-g", "--get"}, description = "Get an item from the cache")
        private String getKey;

        @Option(names = {"-d", "--delete"}, description = "Delete an item from the cache")
        private String deleteKey;

        @Override
        public void run() {
            System.out.println("X-----------------------------------------------------------------------------------------------------------X");
            System.out.println("Use -a to add an item to the cache, -g to get an item from the cache, or -d to delete an item from the cache.");
            System.out.println("X-----------------------------------------------------------------------------------------------------------X");
            if (key != null && value != null) {
                cache.put(key, value);
                System.out.println("Added item to cache");
            } else if (getKey != null) {
                String item = cache.get(getKey);
                if (item != null) {
                    System.out.println("Retrieved item from cache: " + item);
                } else {
                    System.out.println("Item not found in cache");
                }
            } else if (deleteKey != null) {

                try {
                    cache.remove(deleteKey);
                    System.out.println("Deleted item from cache");
                } catch (KeyNotFoundException e) {
                    System.out.println("Item not found in cache");
                }
            }
            else {
                System.out.println("No operation specified. Use -a to add an item to the cache, -g to get an item from the cache, or -d to delete an item from the cache.\"");
            }
        }

        public static void main(String[] args) {
            int exitCode = new CommandLine(new AppCli()).execute(args);
            System.exit(exitCode);
        }
    }


