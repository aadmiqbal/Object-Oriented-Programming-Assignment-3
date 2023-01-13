import java.util.*;
import java.io.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    /**
     * TODO
     * create a static locations HashMap
     */
    static HashMap<Integer, Location> locations = new HashMap<>();

    static {
        /** TODO
         * create a FileLogger object
         */
        FileLogger fileLogger = new FileLogger();

        /** TODO
         * create a ConsoleLogger object
         */
        ConsoleLogger consoleLogger = new ConsoleLogger();

        /** TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put the location and a new Location object in the locations HashMap, using temporary empty hashmaps for exits
         */
        File locationfile = new File(LOCATIONS_FILE_NAME);
        File directionfile = new File(DIRECTIONS_FILE_NAME);

        try (Scanner locationsreader = new Scanner(locationfile)) {
            consoleLogger.log("Available locations:");
            fileLogger.log("Available locations:");
            while (locationsreader.hasNextLine()) {
                String string = locationsreader.nextLine();
                String locationstr = string.substring(0, string.indexOf(","));
                String desc = string.substring(string.indexOf(",") + 1, string.length());
                consoleLogger.log((locationstr + ": " + desc));
                fileLogger.log((locationstr + ": " + desc));
                HashMap<String, Integer> temp = new HashMap<>();
                temp.clear();
                Location location = new Location(Integer.parseInt(locationstr), desc, temp);
                locations.put(Integer.valueOf(locationstr), location);
            }
        } catch (Exception e) {
        }


        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * add the exits for each location
         */
        try (Scanner directionsreader = new Scanner(directionfile)) {
            consoleLogger.log("Available directions:");
            fileLogger.log("Available directions:");
            while (directionsreader.hasNextLine()) {
                String string2 = directionsreader.nextLine();
                String[] array2 = string2.split(",");
                consoleLogger.log((array2[0] + ": " + array2[1] + ": " + array2[2]));
                fileLogger.log((array2[0] + ": " + array2[1] + ": " + array2[2]));
                locations.get(Integer.parseInt(array2[0])).addExit(array2[1], Integer.parseInt(array2[2]));
            }
        } catch (Exception e) {
            System.err.println("somethign bad");

        }


    }

    /**
     * TODO
     * implement all methods for Map
     *
     * @return
     */
    @Override
    public int size() {
        //TODO
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //TODO
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //TODO
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        //TODO
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        //TODO
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO
        locations.putAll(m);
    }

    @Override
    public void clear() {
        //TODO
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        //TODO
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        //TODO
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO
        return locations.entrySet();
    }
}
