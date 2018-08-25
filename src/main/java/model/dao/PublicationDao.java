package model.dao;

import model.entity.Publication;
import model.entity.User;

import java.util.List;
import java.util.Map;

public interface PublicationDao extends GenericDao<Publication>{
    Map<String, Integer> getStatistics();
    List<User> getReport(int id);
    void checkDataUnique(String titleEn, String titleUa);
}
