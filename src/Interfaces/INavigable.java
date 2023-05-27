package Interfaces;

/**
 * This code is defining a Java interface called `INavigable`. The interface has
 * two methods:
 * `nextPage()` and `previousPage()`, both of which return an integer. Any class
 * that implements this
 * interface must provide an implementation for these two methods. This
 * interface is likely intended to
 * be used in classes that represent navigable objects, such as a book or a
 * website, where the user can
 * move forward or backward through pages.
 */
public interface INavigable {
    public int nextPage();

    public int previousPage();
}
