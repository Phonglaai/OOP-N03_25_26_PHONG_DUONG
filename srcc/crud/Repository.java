import java.util.HashMap;

public class Repository<T> {
    private HashMap<Integer, T> storage = new HashMap<>();

    // CREATE
    public void create(int id, T obj) {
        storage.put(id, obj);
    }

    // READ
    public T read(int id) {
        return storage.get(id);
    }

    // UPDATE
    public void update(int id, T obj) {
        if (storage.containsKey(id)) {
            storage.put(id, obj);
        }
    }

    // DELETE
    public void delete(int id) {
        storage.remove(id);
    }

    // In tất cả
    public void printAll() {
        for (T obj : storage.values()) {
            System.out.println(obj);
        }
    }
}
