package lesson_35.myArrayList;

/**
 * 18.10.2023
 * Menu
 *
 * @author Mihail nedioglo (AIT TR)
 */

public interface MyList<T> {
    void add(T value);

    void addAll(T... values);

    int size();

    int indexOf(T value);

    int lastIndexOf(T value);

    boolean contains(T value);

    T[] toArray();

    boolean remove(T value);

    T remove(int index);

    // Отвечает на вопрос является ли коллекция пустой
    boolean isEmpty();

    T get(int index);


}
