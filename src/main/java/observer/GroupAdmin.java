package observer;

import java.util.HashSet;

public class GroupAdmin implements Sender{

    //usb- an UndoableStringBuilder object, in order to save a stock of statuses
    private UndoableStringBuilder usb;

    //members - a stock of customers who should receive updates on any changes made to the usb object.
    //members - the data structure ia a HashSet in order to optimize the running time.
    private HashSet<Member> members;

    //sizeOfMembers - present the size of the members who connected to the group right not
    private int sizeOfMembers;

    /**
     *Build an empty GroupAdmin <br>
     * <u>{@code usb}</u> - an empty UndoableStringBuilder <br>
     * <u>{@code members}</u> - an empty HashSet <br>
     * <u>{@code sizeOfMembers}</u> - reset the parameter to 0
     */
    public GroupAdmin(){
        this.usb = new UndoableStringBuilder();
        this.members = new HashSet<>();
        this.sizeOfMembers =0;
    }

    /**
     *@return the current {@code sizeOfMembers}
     */
    public int getSizeOfMembers() {
        return sizeOfMembers;
    }


    /**
     * <p>
     * The first method in the interface sender is register (this class implements Sender). <br>
     * register(observer) is a method to add  observers respectively . <br>
     * First we add the member obj to the {@code members} <br>
     * (this is the pool of customers who should receive updates on any changes made to the pool). <br>
     * we add the member using the method add that used to add a specific element into a HashSet. <br>
     * This method will add the element only if the specified element is not present in the HashSet else <br>
     * the function will return False if the element is already present in the HashSet.<br>
     * After, we use the method update().<br>
     * This method is called when we add or remove observer and we want to update the {@code usb} accordingly <br>
     * Finally,we update the {@code sizeOfMembers} <br>
     * </p>
     * <p>
     * @param obj - the customer (Member) that we add to the {@code members}
     * </p>
     */
    @Override
    public void register(Member obj) {
        this.members.add(obj);
        obj.update(this.usb);
        this.sizeOfMembers = this.members.size();
    }


/**
 * <p>
 * The second method in the interface sender is unregister (this class implements Sender). <br>
 * unregister (observer) is a method to remove observers respectively . <br>
 * First we remove the member obj from {@code members} <br>
 * (this is the pool of customers who should receive updates on any changes made to the pool). <br>
 * we remove the member using the method remove that remove a particular element from a HashSet<br>
 * This method returns true if the specified element is present in the HashSet otherwise it returns boolean false.<br>
 * After, we use the method update().<br>
 * This method is called when we add or remove observer and we want to update the {@code usb} accordingly<br>
 * Finally,we update the {@code sizeOfMembers} <br>
 * </p>
 * <p>
 * @param obj - the customer (Member) that we remove from {@code members}
 * </p>
 */
 @Override
    public void unregister(Member obj) {
        this.members.remove(obj);
        obj.update(null);
        this.sizeOfMembers = this.members.size();
    }
    /**
     * <p>
     * The third method in the interface sender is insert (this class implements Sender).<br>
     * insert is the method that inserts the string into the character sequence ( in {@code usb } ) <br>
     * At first we use the method insert() on {@code usb }. <br>
     * Then we use the method notifyObserver(). <br>
     * notifyObservers() is called when the data is changed and the observers need to be supplied with new data.<br>
     * In our case the data is updated in the {@code usb}. <br>
     * </p>
     * <p>
     * @param offset - The beginning index, inclusive.
     * @param obj - The string that is inserted.
     * </p>
     */
    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        notifyObserver();
    }
    /**
     * <p>
     * The fourth method in the interface sender is append (this class implements Sender).<br>
     * This method appends the specified {@code String obj} to the character sequence in {@code usb}.<br>
     * Then we use the method notifyObserver(). <br>
     * notifyObservers() is called when the data is changed and the observers need to be supplied with new data.<br>
     * In our case the data is updated in the {@code usb}. <br>
     * </p>
     * <p>
     * @param obj - The {@code String} to append <br>
     * </p>
     */

    @Override
    public void append(String obj) {
        this.usb.append(obj);
        notifyObserver();
    }
    /**
     * <p>
     * The fifth method in the interface sender is delete (this class implements Sender).<br>
     * This method removes the characters in a substring of this sequence. <br>
     * The substring begins at the specified start and extends to the character at index <br>
     * end - 1 or to the end of the sequence if no such character exists. <br>
     * If start is equal to end, no changes are made.<br>
     * Then we use the method notifyObserver(). <br>
     * notifyObservers() is called when the data is changed and the observers need to be supplied with new data.<br>
     * In our case the data is updated in the {@code usb}. <br>
     * </p>
     * <p>
     * @param start - The beginning index, inclusive.
     * @param end - The ending index, exclusive.
     * </p>
     */

    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        notifyObserver();
    }


    /**
     * <p>
     * The sixth method in the interface sender is undo (this class implements Sender).<br>
     * This method takes us back one situation about the {@code usb}.<br>
     * Then we use the method notifyObserver(). <br>
     * notifyObservers() is called when the data is changed and the observers need to be supplied with new data.<br>
     * In our case the data is updated in the {@code usb}. <br>
     * </p>
     */
    @Override
    public void undo() {
        this.usb.undo();
        notifyObserver();
    }
    /**
     * <p>
     * notifyObservers() is called when the data is changed and the observers need to be supplied with new data.<br>
     * The data structure of {@code members} ia a HashSet. <br>
     * In this method we go through the HashSet with for-each loop and we update the {@code usb} for each member.<br>
     * </p>
     */
    public void notifyObserver(){
        for (Member member: this.members) {
            member.update(usb);
        }
    }

    /**
     *<p>
     * @return  a string representing the {@code members}.
     * </p>
     */
    @Override
    public String toString(){
        return "The Members:" +this.members;

    }
}


