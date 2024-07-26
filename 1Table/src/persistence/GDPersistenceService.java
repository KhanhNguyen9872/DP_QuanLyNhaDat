package persistence;

import java.util.List;

import domain.model.GD;

public interface GDPersistenceService {
    public void add(GD gd);
    public void update(GD gd);
    public void delete(int id);

    public GD getGDByID(int id);
    public List<GD> getAllGDs();
}
