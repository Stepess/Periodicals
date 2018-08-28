package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.entity.DTO.PublicationDto;
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
    
    public List<PublicationDto> getAll() {
        return publicationDao.getAll();
    }

    public PublicationDto getById(int id) {
        return publicationDao.getById(id);
    }

    public void setInDb(PublicationDto publication){
        publicationDao.setInDb(publication);
    }

    public Map<String, Integer> getStatistics() {
        return publicationDao.getStatistics();
    }

    public List<User> getReport(int publicationId) {
        return publicationDao.getReport(publicationId);
    }

    public void checkDataUnique(String titleEn, String titleUa) {
        publicationDao.checkDataUnique(titleEn, titleUa);
    }

    /*public List<PublicationDto> getAllMultiLanguagePublication() {
        return publicationDao.getAll();
    }

    public void setPublicationDto(PublicationDto dto) {
        publicationDao.setInDb(dto);
    }*/

    public void delete(int id) {
        publicationDao.delete(id);
    }

    public List<PublicationDto> search(Map<String, String> searchParameters) {
        return publicationDao.search(searchParameters);
    }
}
