package uz.uzinfocom.electroniclibrarysystem.service.libraryservice;

import uz.uzinfocom.electroniclibrarysystem.entity.Library;

public interface LibraryService {
    void save(Library library);

    void setBookById(Long id, int quantity);

}
