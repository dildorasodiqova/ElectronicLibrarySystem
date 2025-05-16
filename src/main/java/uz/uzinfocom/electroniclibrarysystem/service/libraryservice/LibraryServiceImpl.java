package uz.uzinfocom.electroniclibrarysystem.service.libraryservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.entity.Library;
import uz.uzinfocom.electroniclibrarysystem.repository.LibraryRepository;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService{
    private final LibraryRepository libraryRepository;
    @Override
    public void save(Library library) {
        libraryRepository.save(library);
    }

    @Override
    public void setBookById(Long id, int quantity) {
        Library library = libraryRepository.findByBookId(id);
        library.setTotalBooks(library.getTotalBooks()+quantity);
        libraryRepository.save(library);
    }

    public List<LibraryResponse> getAllBooks(){
        libraryRepository.getAll()
    }



}
