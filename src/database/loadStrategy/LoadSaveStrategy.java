package database.loadStrategy;

import java.util.List;

public interface LoadSaveStrategy {

    void loadFromStorage();

    void saveToStorage();

    void saveObjectToStorage(Object object);

    List MapToList();

}
