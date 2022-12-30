package observer;

public class ConcreteMember implements Member {

    //member- an UndoableStringBuilder object, in order to save a stock of statuses
    private UndoableStringBuilder member;

    /**
     *Build an empty ConcreteMember() <br>
     * <u>{@code member}</u> - an empty UndoableStringBuilder <br>
     */
    public ConcreteMember(){
        this.member = null;
    }


    /**
     * <p>
     * The method in the interface Member is update (this class implements Member). <br>
     * This method is called when we add or remove observer and we want to update the {@code usb} accordingly
     * </p>
    */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.member = usb;
    }


    /**
     * <p>
     * This method representing the {@code member} . <br>
     * </p>
     */
    public String toString(){
        return this.member.toString();
    }
}


