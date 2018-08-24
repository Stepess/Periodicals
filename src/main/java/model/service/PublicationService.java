package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.entity.Publication;
import model.entity.User;

import java.util.List;
import java.util.Map;

public class PublicationService {
    private PublicationDao publicationDao;

    public PublicationService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        publicationDao = daoFactory.createPublicationDao();
    }
    
    public List<Publication> getAll() {
        return publicationDao.getAll();
    }

    public Publication getById(int id) {
        return publicationDao.getById(id);
    }

    public void setInDb(Publication publication){
        System.out.println(publicationDao.setInDb(publication));
    }

    public Map<String, Integer> getStatistics() {
        return publicationDao.getStatistics();
    }

    public List<User> getReport(int publicationId) {
        return publicationDao.getReport(publicationId);
    }
}
