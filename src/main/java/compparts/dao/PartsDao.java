package compparts.dao;

import compparts.controller.FilterType;
import compparts.model.Part;

import java.util.List;

public interface PartsDao {
    List<Part> allParts();
    List<Part> allParts(int page, FilterType filterType);
    void add(Part part);
    void delete(Part part);
    void edit(Part part);
    Part getById(int id);
    int partsCount();
    int compsCount();
}