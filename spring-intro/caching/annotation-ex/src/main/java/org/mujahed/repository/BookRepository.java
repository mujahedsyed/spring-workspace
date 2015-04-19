package org.mujahed.repository;

import org.mujahed.entity.Book;

public interface BookRepository {

	Book getBookByIsbn(String isbn);
}
