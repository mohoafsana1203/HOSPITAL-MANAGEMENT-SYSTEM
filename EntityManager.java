// EntityManager.java
import java.util.List;

public interface EntityManager<T> {
    void add(T entity);
    void update(int id, T entity);
    void delete(int id);
    T findById(int id);
    List<T> getAll();
    List<T> search(String keyword);
}
