package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.entity.DTO.PublicationDto;
import model.entity.User;
import model.service.resource.manager.MessageManager;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PublicationService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public PublicationService() {
        /*DaoFactory daoFactory = DaoFactory.getInstance();
        PublicationDao dpublicationDao = daoFactory.createPublicationDao();*/
    }
    
    public List<PublicationDto> getAll() {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
           return dao.getAll();
        }
    }

    public PublicationDto getById(int id) {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.getById(id);
        }
    }

    public boolean setInDb(PublicationDto publication){
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.setInDb(publication);
        }
    }

    public boolean update(PublicationDto publication){
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.update(publication);
        }
    }

    public Map<String, Integer> getStatistics() {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.getStatistics();
        }
    }

    public List<User> getReport(int publicationId) {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.getReport(publicationId);
        }
    }

    public void checkDataUnique(String titleEn, String titleUa) {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            dao.checkDataUnique(titleEn, titleUa);
        }
    }

    /*public List<PublicationDto> getAllMultiLanguagePublication() {
        return publicationDao.getAll();
    }

    public void setPublicationDto(PublicationDto dto) {
        publicationDao.setInDb(dto);
    }*/

    public boolean delete(int id) {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.delete(id);
        }
    }

    public List<PublicationDto> search(Map<String, String> searchParameters) {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.search(searchParameters);
        }
    }
    public int getNumberOfPublication(){
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.getNumberOfPublications();
        }
    }

    public List<PublicationDto> getPaginatedList(int start, int recordsPerPage) {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.getPaginatedList(start, recordsPerPage);
        }
    }

    public int getNumberOfSearchedPublication(Map<String, String> searchParameters) {
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.getNumberOfSearchedPublications(searchParameters);
        }
    }

    public List<PublicationDto> getPaginatedSearchList(Map<String, String> searchParameters, int start, int recordsPerPage){
        try(PublicationDao dao = daoFactory.createPublicationDao()){
            return dao.getPaginatedSearchList(searchParameters, start, recordsPerPage);
        }
    }
}
