package nick.pack.service;

import java.util.List;

public interface CRUD<Entity> {
    Entity searchById(int id);
    List<Entity> read();
    void create(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
}
