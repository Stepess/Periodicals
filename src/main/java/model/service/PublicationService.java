package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.entity.Publication;

import java.util.List;

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
}
