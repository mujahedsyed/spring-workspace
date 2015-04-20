package org.mujahed.repository;


import org.mujahed.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleBookRepository implements BookRepository {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SimpleBookRepository.class);

	public Book getBookByIsbn(String isbn) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	private void simulateSlowService() {
		try {
			long time = 5000L;
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			throw new IllegalStateException();
		}

	}
}
