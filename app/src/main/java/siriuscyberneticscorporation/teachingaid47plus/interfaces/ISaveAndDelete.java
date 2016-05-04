package siriuscyberneticscorporation.teachingaid47plus.interfaces;

public interface ISaveAndDelete {

    /**
     * Save Method
     *
     * @return true if save was successful - false otherwise
     */
    public boolean saveToDB();

    /**
     * Deletes the Object from the Database
     */
    public void deleteFromDB();

}