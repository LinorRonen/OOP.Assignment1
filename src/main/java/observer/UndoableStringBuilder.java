package observer;

/*
Use the class you've implemented in previous assignment
 */

import java.util.Stack;

public class UndoableStringBuilder {

    StringBuilder stringBuilder;
    Stack<StringBuilder> undoStack;

    /**
     *Build a string builder with no characters in it, and an empty stack
     */
    public UndoableStringBuilder (){
        this.stringBuilder = new StringBuilder();
        this.undoStack = new Stack<StringBuilder>();
    }

    /**
     * <p>
     *First, push the current value in {@code stringBuilder} to {@code undoStack} <br>
     * Then, appends the specified {@code String Str} to this character sequence
     * </p>
     * <p>
     * @param str The {@code String} to append <br>
     * @return {@code stringBuilder} with the change
     *</p>
     */
    public StringBuilder append(String str){
        StringBuilder sb = new StringBuilder(this.stringBuilder);
        this.undoStack.push(sb);
        this.stringBuilder.append(str);
        return this.stringBuilder;
    }

    /**
     * <p>
     * First, push the current value in {@code stringBuilder} to {@code undoStack} <br>
     * Then, removes the characters in a substring of this sequence. <br>
     * The substring begins at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists. <br>
     * If start is equal to end, no changes are made.
     * </p>
     * <p>
     * @param start The beginning index, inclusive
     * @param end The ending index, exclusive.
     * @return {@code stringBuilder} with the change
     *</p>
     */
    public StringBuilder delete(int start, int end){
        if (start == end)
            return this.stringBuilder;
        StringBuilder sb = new StringBuilder(this.stringBuilder);
        this.undoStack.push(sb);
        this.stringBuilder.delete(start, end);
        return this.stringBuilder;
    }

    /**
     * <p>
     * First, push the current value in {@code stringBuilder} to {@code undoStack} <br>
     * Then, inserts the string into this character sequence. <br>
     * </p>
     * <p>
     * @param offset The beginning index, inclusive
     * @param str The ending index, exclusive.
     * @return {@code stringBuilder} with the change
     *</p>
     */
    public StringBuilder insert(int offset, String str){
        StringBuilder sb = new StringBuilder(this.stringBuilder);
        this.undoStack.push(sb);
        this.stringBuilder.insert(offset, str);
        return this.stringBuilder;
    }

    /**
     * <p>
     * This method replaces the characters from start index to end index by the specified string-str <br>
     * First, push the current value in {@code stringBuilder} to {@code undoStack} <br>
     * Then activate this method on our {@code stringBuilder} <br>
     * @param start - the start index
     * @param end   - the end index
     * @param str   - the specified string
     * @return  a new string in which from the start index to the end index there is the specified str string
     * </p>
     */
    public StringBuilder replace(int start,int end, String str){
        StringBuilder sb = new StringBuilder(this.stringBuilder);
        this.undoStack.push(sb);
        this.stringBuilder.replace(start, end,str);
        return this.stringBuilder;
    }

    /**
     * <p>
     * This method reverses the order of the characters <br>
     * First, push the current value in {@code stringBuilder} to {@code undoStack} <br>
     * Then activate this method on our {@code stringBuilder}
     * @return  a string in which the character order is reversed to the character order in the original string
     * </p>
     */
    public StringBuilder reverse(){
        StringBuilder sb = new StringBuilder(this.stringBuilder);
        this.undoStack.push(sb);
        this.stringBuilder.reverse();
        return this.stringBuilder;
    }

    /**
     *<p>
     * This method takes us back one situation about the {@code stringBuilder}  <br>
     * First we will check if our {@code undoStack} is empty <br>
     * If our {@code undoStack} is not empty, we will take the string that is in it
     * *</p>
     */
    public void undo(){
        if (!this.undoStack.isEmpty())
            this.stringBuilder = this.undoStack.pop();
    }

    /**
     *<p>
     * @return  a string representing the data in this sequence
     * </p>
     */
    @Override
    public String toString() {
        return "UndoStringBuilder{" +
                "stringBuilder=" + stringBuilder +
                ", this.undoStack=" + this.undoStack +
                '}';
    }
}







