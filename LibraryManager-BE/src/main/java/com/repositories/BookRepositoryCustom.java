package com.repositories;

import com.entities.Book;
import java.util.List;

public interface BookRepositoryCustom {
 
    List<Book> searchByQuery(String query);
}