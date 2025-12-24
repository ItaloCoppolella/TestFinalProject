package interfaces;

import java.util.List;

import entities.Entity;

public interface IDao {

    void create(Entity e);
	
	List<Entity> read();
	
	void update(Entity e);
	
	void delete(int id);

}
