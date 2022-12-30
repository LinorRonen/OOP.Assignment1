import observer.GroupAdmin;
import observer.ConcreteMember;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class Tests {
    @BeforeEach
    void initialize() {
        System.out.println("Start a new Test");
    }

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }

    /**
     *Test for the method: <u>{@code register}</u>
     */
    @Test
    void register (){
        //Create a new GroupAdmin object for the test
        GroupAdmin groupAdmin = new GroupAdmin();

        //check the size of the groupAdmin after we initialized it
        System.out.println("The size of the groupAdmin after we initialized it is:");
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));

        groupAdmin.append("register");
        //Create new Member objects for the test
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();

        //check the size of the groupAdmin after we added some data
        //we want to make sure that the size of the groupAdmin is grown up
        System.out.println("The size of the groupAdmin after we added some data is:");
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));

        //register the first member
        groupAdmin.register(member1);
        //check the number of members in the group
        assertEquals(1, groupAdmin.getSizeOfMembers());

        //register the second member
        groupAdmin.register(member2);

        //register a member that already exist
        groupAdmin.register(member1);
        //check the number of members in the group
        assertEquals(2, groupAdmin.getSizeOfMembers());

        //register members that already exist
        groupAdmin.register(member1);
        groupAdmin.register(member2);
        groupAdmin.register(member2);
        groupAdmin.register(member1);
        //check the number of members in the group
        assertEquals(2, groupAdmin.getSizeOfMembers());

        //check the size of the groupAdmin after we added some members to the group
        //we want to make sure that the size of the groupAdmin is grown up
        System.out.println("The size of the groupAdmin after we added some members to the group is:");
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));
    }

    /**
     *Test for the method: <u>{@code unregister}</u>
     */
    @Test
    void unregister(){
        //Create a new GroupAdmin object for the test
        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.append("unregister");

        //check the size of the groupAdmin after we initialized it
        System.out.println("The size of the groupAdmin after we initialized it is:");
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));

        //Create new Member objects for the test
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();

        //register the members
        groupAdmin.register(member1);
        groupAdmin.register(member2);
        groupAdmin.register(member3);

        //check the size of the groupAdmin after we added some members to the group and some data
        //we want to make sure that the size of the groupAdmin is grown up
        System.out.println("The size of the groupAdmin after we added some members to the group and some data is:");
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));

        //unregister the first member
        groupAdmin.unregister(member1);
        //check the number of members in the group after the unregister method
        assertEquals(2, groupAdmin.getSizeOfMembers());

        //unregister a member that doesn't exit
        groupAdmin.unregister(member1);
        //check the number of members in the group after the unregister method
        assertEquals(2, groupAdmin.getSizeOfMembers());

        //unregister the other members
        groupAdmin.unregister(member2);
        groupAdmin.unregister(member3);
        //check the number of members in the group after the unregister method
        assertEquals(0, groupAdmin.getSizeOfMembers());

        //unregister from an empty groupAdmin
        groupAdmin.unregister(member3);
        assertEquals(0, groupAdmin.getSizeOfMembers());

        //check the size of the groupAdmin after we removed all the members from the group
        //we want to make sure that the size of the groupAdmin became smaller
        System.out.println("The size of the groupAdmin after we removed all the members from the group is:");
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));
    }

    /**
     *Test for the method: <u>{@code notifyObserver}</u>
     */
    @Test
    void notifyObserver(){
        //Create a new GroupAdmin object for the test
        GroupAdmin groupAdmin = new GroupAdmin();

        //check the size of the groupAdmin after we initialized it
        System.out.println("The size of the groupAdmin after we initialized it is:");
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));

        groupAdmin.append("notify observer");

        //Create new Member objects for the test
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();

        //register the first member
        groupAdmin.register(member1);
        //check if the first member is updated
        assertEquals("UndoStringBuilder{stringBuilder=notify observer, this.undoStack=[]}", member1.toString());

        //change the string in the groupAdmin's usb
        groupAdmin.append(" test");

        //register the second member
        groupAdmin.register(member2);
        //check if those members are updated
        assertEquals("UndoStringBuilder{stringBuilder=notify observer test, this.undoStack=[, notify observer]}", member1.toString());
        assertEquals("UndoStringBuilder{stringBuilder=notify observer test, this.undoStack=[, notify observer]}", member2.toString());

        //register the third member
        groupAdmin.register(member3);
        //check if the first member is updated
        assertEquals("UndoStringBuilder{stringBuilder=notify observer test, this.undoStack=[, notify observer]}", member3.toString());

        //make some changes in the groupAdmin's usb
        groupAdmin.append("!!");
        groupAdmin.delete(20,21);
        groupAdmin.insert(15, " in");

        //check the size of the groupAdmin after we added some members to the group and some data
        //we want to make sure that the size of the groupAdmin is grown up
        System.out.println("The size of the groupAdmin after we added some members to the group and some data is:");
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));

        //check if those members are updated
        assertEquals("UndoStringBuilder{stringBuilder=notify observer in test!, this.undoStack=[, notify observer, notify observer test, notify observer test!!, notify observer test!]}", member1.toString());
        assertEquals("UndoStringBuilder{stringBuilder=notify observer in test!, this.undoStack=[, notify observer, notify observer test, notify observer test!!, notify observer test!]}", member2.toString());
        assertEquals("UndoStringBuilder{stringBuilder=notify observer in test!, this.undoStack=[, notify observer, notify observer test, notify observer test!!, notify observer test!]}", member3.toString());

        //make sure that all the members have the same size
        System.out.println("The size of the first member in the group is:");
        logger.info(()->JvmUtilities.objectTotalSize(member1));
        System.out.println("The size of the second member in the group is:");
        logger.info(()->JvmUtilities.objectTotalSize(member2));
        System.out.println("The size of the third member in the group is:");
        logger.info(()->JvmUtilities.objectTotalSize(member3));
    }
}