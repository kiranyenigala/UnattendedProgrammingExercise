package com.sky.library;

public class BookServiceImpl implements BookService {

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        checkValidBookReference(bookReference);
        final BookRepository repository = new BookRepositoryStub();
        Book book = repository.retrieveBook(bookReference);

        if (book == null) {
            throw new BookNotFoundException(String.format("Book with reference %s Not Found", bookReference));
        }
        return book;
    }

    private void checkValidBookReference(String bookReference) {
        if (bookReference == null || bookReference.trim().equals("")) {
            throw new InvalidBookReferenceException("Book Reference should not be empty or null");
        }
        if (!bookReference.startsWith(BookPrefix.BOOK_PREFIX)) {
            throw new InvalidBookReferenceException(String.format("Book Reference must start with %s", BookPrefix.BOOK_PREFIX));
        }
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException {
        Book book = retrieveBook(bookReference);
        return book.getBookSummary();
    }
}
