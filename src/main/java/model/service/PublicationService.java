package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.entity.User;

import java.util.List;
import java.util.Map;

public class PublicationService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public PublicationService() {
    }

    public List<PublicationDto> getAll() {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.getAll();
        }
    }

    public PublicationDto getById(int id) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.getById(id);
        }
    }

    public boolean addPublication(PublicationDto publication) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.setInDb(publication);
        }
    }

    public boolean editPublication(PublicationDto publication) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.update(publication);
        }
    }

    public Map<PublicationDto, Integer> getStatistics() {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.getStatistics();
        }
    }

    public List<User> getReport(int publicationId) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.getReport(publicationId);
        }
    }

    public void checkTitlesUnique(String titleEn, String titleUa) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            dao.checkDataUnique(titleEn, titleUa);
        }
    }

    public boolean deletePublication(int id) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.delete(id);
        }
    }

    public int getNumberOfPublication() {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.getNumberOfPublications();
        }
    }

    public List<PublicationDto> getPaginatedList(int start, int recordsPerPage) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.getPaginatedList(start, recordsPerPage);
        }
    }

    public int getNumberOfSearchedPublication(Map<String, String> searchParameters) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.getNumberOfSearchedPublications(searchParameters);
        }
    }

    public List<PublicationDto> getPaginatedSearchList(Map<String, String> searchParameters, int start, int recordsPerPage) {
        try (PublicationDao dao = daoFactory.createPublicationDao()) {
            return dao.getPaginatedSearchList(searchParameters, start, recordsPerPage);
        }
    }
}
