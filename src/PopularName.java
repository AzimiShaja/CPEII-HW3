/**
 * @author Ahmad Shaja AZIMI
 * 
 *         The PopularName class is used to represent a name with its rank and
 *         number of
 *         babies born with that name.
 */
public class PopularName {
    private String name;
    private int rank;
    private int numberOfBabies;

    /**
     * Constructs a new PopularName object with a given name, rank and number of
     * babies.
     * 
     * @param name           the name of the baby
     * @param rank           the rank of the name in the list of popular names
     * @param numberOfBabies the number of babies born with that name
     */
    public PopularName(String name, int rank, int numberOfBabies) {
        this.name = name;
        this.rank = rank;
        this.numberOfBabies = numberOfBabies;
    }

    /**
     * Returns the name of the baby.
     * 
     * @return the name of the baby
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank of the name in the list of popular names.
     * 
     * @return the rank of the name
     */
    public int getRank() {
        return rank;
    }

    /**
     * Returns the number of babies born with that name.
     * 
     * @return the number of babies born with that name
     */
    public int getNumberOfBabies() {
        return numberOfBabies;
    }
}
