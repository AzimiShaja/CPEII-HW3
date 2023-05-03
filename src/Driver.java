import java.io.*;
import java.util.*;

/* 
@author Ahmad Shaja AZIMI
*/
public class Driver {
    // An array list to store PopularName class for males
    private static ArrayList<PopularName> maleNames = new ArrayList<PopularName>();
    // An array list to store popularName class for females
    private static ArrayList<PopularName> femaleNames = new ArrayList<PopularName>();
    private static Scanner keyboard = new Scanner(System.in);

    /*
     * The Main prompts the user to enter a file path, reads data from the file,
     * sorts the data alphabetically using bubble sort, calculates the percentage of
     * male and female names used, allows the user to search for a name and see its
     * statistics, and repeats the search process if desired. Finally, it prints a
     * message and ends the program.
     */
    public static void main(String[] args) {
        // propmt user to enter the file path
        System.out.println("Enter the file name: ");
        String filePath = keyboard.nextLine();
        // Scanner object to read the file contents
        Scanner inputStream = null;
        try {
            File f = new File(filePath); // Create a new file
            if (f.exists()) { // check if the file exists
                inputStream = new Scanner(f);
                System.out.println("file " + filePath + " has been uploaded");
            } else {
                System.out.println("File does not exist");
            }

        } catch (Exception e) { // catch possible exceptions
            System.out.println("Error reading file " + filePath);
            inputStream.close(); // close the scanner
        }

        try {
            // Read data from file
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                // split each line with ',' and store it in array
                String[] arr = line.split(",");
                int rank = Integer.parseInt(arr[0]);
                String maleName = arr[1];
                int maleNumbers = Integer.parseInt(arr[2]);
                String femaleName = arr[3];
                int femaleNumbers = Integer.parseInt(arr[4]);
                // add the variables to the array Lists
                maleNames.add(new PopularName(maleName, rank, maleNumbers));
                femaleNames.add(new PopularName(femaleName, rank, femaleNumbers));
            }

        } catch (Exception e) { // Catch all exceptions
            System.out.println("Error reading data from file");
            System.exit(0);
        }
        // Sort the names bases on the alphabetic order using bubble sort
        // algorithm.
        bubbleSort(maleNames);
        bubbleSort(femaleNames);

        // Calculate the percentage of the male names used.
        int totalMaleBabies = 0;
        for (int i = 0; i < maleNames.size(); i++) {
            PopularName name = maleNames.get(i);
            totalMaleBabies += name.getNumberOfBabies();
        }
        // the same for the female names used.
        int totalFemaleBabies = 0;
        for (int i = 0; i < femaleNames.size(); i++) {
            PopularName name = femaleNames.get(i);
            totalFemaleBabies += name.getNumberOfBabies();
        }
        // asks user if want to search for a name and see statistics.
        System.out.println("Do you want to search for a name and see its statistics (y/n)?");
        String response = keyboard.nextLine().toLowerCase();
        while (response.equals("y")) {
            // asks gender
            System.out.println("Enter a gender (male/female)");
            String gender = keyboard.nextLine();
            // checks if the eneterd gender is male of female
            if (gender.equals("male")) {
                System.out.println("Enter a male name: ");
                String mName = keyboard.nextLine();
                // serach for name using binary search
                int index = binarySearch(maleNames, mName);
                if (index >= 0) {
                    // print the expected gender's statistics for male
                    PopularName name = maleNames.get(index);
                    System.out.println("\n" + name.getName() + ":");
                    System.out.println("Index in sorted list: " + index);
                    System.out.println("Rank in popularity: " + name.getRank());
                    System.out.println("Number of babies with this name: " + name.getNumberOfBabies());
                    double MPercentage = (double) name.getNumberOfBabies() / totalMaleBabies * 100;
                    System.out.printf("Percentage of male babies with this name: %.2f%%", MPercentage);
                } else {
                    System.out.println("male Name not found");
                }
            } else if (gender.equals("female")) {
                System.out.println("Enter a female name:");
                String fName = keyboard.nextLine();
                int index = binarySearch(femaleNames, fName);
                if (index >= 0) {
                    PopularName name = femaleNames.get(index);
                    System.out.println("\n" + name.getName() + ":");
                    System.out.println("Index in sorted list: " + index);
                    System.out.println("Rank in popularity: " + name.getRank());
                    System.out.println("Number of babies with this name: " + name.getNumberOfBabies());
                    double FPercentage = (double) name.getNumberOfBabies() / totalFemaleBabies * 100;
                    System.out.printf("Percentage of female babies with this name: %.2f%%", FPercentage);
                } else {
                    System.out.println("female Name not found");
                }
            } else { // invalid gender if user tend to enter a gender rather than male or female
                System.out.println("Invalid gender");
            }
            // asks user again for repeating the same process
            System.out.println("\nDo you want to search for another name (y/n)?");
            response = keyboard.nextLine().toLowerCase();
        }
        System.out.println("have a nice time.");
    }

    /**
     * 
     * Sorts an ArrayList of PopularName objects in alphabetical order using the
     * bubble sort algorithm.
     * 
     * @param li the ArrayList of PopularName objects to be sorted
     */
    public static void bubbleSort(ArrayList<PopularName> li) {
        for (int i = 0; i < li.size() - 1; i++) {
            for (int j = 0; j < li.size() - i - 1; j++) {
                if (li.get(j).getName().compareTo(li.get(j + 1).getName()) > 0) {
                    PopularName temp = li.get(j);
                    li.set(j, li.get(j + 1));
                    li.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Searches for a specific name in a sorted ArrayList of PopularName objects
     * using binary search algorithm.
     * 
     * @param li   The sorted ArrayList of PopularName objects to search in.
     * 
     * @param name The name to search for.
     * 
     * @return The index of the PopularName object with the specified name, or -1 if
     *         not found.
     */
    public static int binarySearch(ArrayList<PopularName> li, String name) {
        int low = 0;
        int high = li.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            String midName = li.get(mid).getName();
            if (name.compareTo(midName) < 0) {
                high = mid - 1;
            } else if (name.compareTo(midName) > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1; // return -1 when not found
    }
}
