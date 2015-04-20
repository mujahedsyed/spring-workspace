package org.mujahed;

import org.mujahed.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookRepository bookRepository = (BookRepository) ctx
				.getBean("bookRepository");
		log.info(".... Fetching books");
		log.info("isbn-1234 -->" + bookRepository.getBookByIsbn("isbn-1234"));
		log.info("isbn-1234 -->" + bookRepository.getBookByIsbn("isbn-1234"));
		log.info("isbn-1234 -->" + bookRepository.getBookByIsbn("isbn-1234"));

		ctx.close();
	}
}
