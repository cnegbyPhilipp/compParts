package compparts.service;

import compparts.controller.FilterType;
import compparts.dao.PartsDao;
import compparts.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartsServiceImpl implements PartsService {

    private PartsDao partsDao;

    @Autowired
    public void setPartsDao(PartsDao partsDao) {
        this.partsDao = partsDao;
    }

    @Override
    @Transactional
    public List<Part> allParts(){
        return partsDao.allParts();
    }

    @Override
    @Transactional
    public List<Part> allParts(int page, FilterType filterType) {
        return partsDao.allParts(page, filterType);
    }

    @Override
    @Transactional
    public void add(Part part) {
        partsDao.add(part);
    }

    @Override
    @Transactional
    public void delete(Part part) {
        partsDao.delete(part);
    }

    @Override
    @Transactional
    public void edit(Part part) {
        partsDao.edit(part);
    }

    @Override
    @Transactional
    public Part getById(int id) {
        return partsDao.getById(id);
    }

    @Override
    @Transactional
    public int partsCount() {
        return partsDao.partsCount();
    }

    @Override
    @Transactional
    public int compsCount(){
        return partsDao.compsCount();
    }

    @Override
    @Transactional
    public List<Part> findParts(String searchString) {
        List<Part> allParts = allParts();
        return allParts.stream().
                filter(part -> part.getTitle().toLowerCase().contains(searchString.toLowerCase())).
                collect(Collectors.toList());
    }
}
