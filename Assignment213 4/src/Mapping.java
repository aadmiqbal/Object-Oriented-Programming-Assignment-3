import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /** TODO
     * create a static LocationMap object
     */
    static LocationMap locationMap = new LocationMap();

    /** TODO
     * create a vocabulary HashMap to store all directions a user can go
     */
    HashMap<String,String> vocabulary = new HashMap<>();

    /** TODO
     * create a FileLogger object
     */
    FileLogger fileLogger = new FileLogger();

    /** TODO
     * create a ConsoleLogger object
     */
    ConsoleLogger consoleLogger = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /** TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("EAST","E");
        vocabulary.put("SOUTH","S");
        vocabulary.put("WEST","W");
        vocabulary.put("UP","U");
        vocabulary.put("DOWN","D");
        vocabulary.put("NORTHEAST","NE");
        vocabulary.put("NORTHWEST","NW");
        vocabulary.put("SOUTHEAST","SE");
        vocabulary.put("SOUTHWEST","SW");

    }

    public void mapping() {

        /** TODO
         * create a Scanner object
         */
        Scanner scanner = new Scanner(System.in);

        /**
         * initialise a location variable with the INITIAL_LOCATION
         */
        int location = INITIAL_LOCATION;

        while (true) {

            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            fileLogger.log(locationMap.get(location).getDescription());
            consoleLogger.log(locationMap.get(location).getDescription());
            /** TODO
             * verify if the location is exit
             */
            if(locationMap.get(location).getLocationId() == 0)
                break;

            /** TODO
             * get a map of the exits for the location
             */
            Map<String,Integer> mapofexits;
            mapofexits = locationMap.get(location).getExits();

            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
            String[] exitkeys = mapofexits.keySet().toArray(new String[0]);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Available exits are ");
            for (String exitkey : exitkeys) {
                stringBuilder.append(exitkey).append(", ");
            }
            fileLogger.log(stringBuilder.toString());
            consoleLogger.log(stringBuilder.toString());

            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
            String direction = scanner.nextLine().toUpperCase();

            /** TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
            String[] separatewords;
            HashMap<String,Integer> viabledirections = new HashMap<>();
            Integer[] viabledirectionslocations = new Integer[0];
            separatewords = direction.split(" ");
            if(separatewords.length>1) {
                try {
                    for (int i = 0; i < separatewords.length; i++) {
                        if (vocabulary.containsKey(separatewords[i]))
                            viabledirections.put(separatewords[i], i);
                    }
                    for (int i = 0; i < viabledirections.size(); i++)
                        viabledirectionslocations = viabledirections.values().toArray(new Integer[i]);
                    int n = viabledirectionslocations.length;
                    if (n > 1) {
                        int tempvar = 0;
                        for (int i = 0; i < n; i++) {
                            for (int j = 1; j < (n - i); j++) {
                                if (viabledirectionslocations[j - 1] > viabledirectionslocations[j]) {
                                    tempvar = viabledirectionslocations[j - 1];
                                    viabledirectionslocations[j - 1] = viabledirectionslocations[j];
                                    viabledirectionslocations[j] = tempvar;
                                }
                            }
                        }
                    }
                    direction = separatewords[viabledirectionslocations[viabledirectionslocations.length - 1]];
                    //  System.out.println(direction);
                } catch (IndexOutOfBoundsException e) {
                }
            }
            String directionvalue = vocabulary.get(direction);







            /** TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
            if(mapofexits.containsKey(directionvalue)){
                location=mapofexits.get(directionvalue);}
            else if(mapofexits.containsKey(direction))
                location=mapofexits.get(direction);
            else {
                consoleLogger.log("You cannot go in that direction");
                fileLogger.log("You cannot go in that direction");
            }
        }
    }

    public static void main(String[] args) {
        /**TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping mapping = new Mapping();
        mapping.mapping();
    }

}
