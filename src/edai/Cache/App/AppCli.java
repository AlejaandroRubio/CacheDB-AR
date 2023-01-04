package edai.Cache.App;

import edai.Cache.CacheDB;
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

        @Override
        public void run() {
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
            } else {
                System.out.println("No operation specified. Use -a to add an item to the cache or -g to get an item from the cache.");
            }
        }

        public static void main(String[] args) {
            int exitCode = new CommandLine(new AppCli()).execute(args);
            System.exit(exitCode);
        }
    }


