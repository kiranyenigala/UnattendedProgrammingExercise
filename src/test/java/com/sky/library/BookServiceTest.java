package com.sky.library;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookServiceTest {

    BookService bookService = new BookServiceImpl();


    @Test(expected = InvalidBookReferenceException.class)
    public void retrieveBookWithNullReference() throws BookNotFoundException {
        try {
            bookService.retrieveBook(null);
        } catch (InvalidBookReferenceException ie) {
            assertEquals("Book Reference should not be empty or null", ie.getMessage());
            throw ie;
        }
    }

    @Test(expected = InvalidBookReferenceException.class)
    public void retrieveBookWithEmptyReference() throws BookNotFoundException {
        try {
            bookService.retrieveBook("   ");
        } catch (InvalidBookReferenceException ie) {
            assertEquals("Book Reference should not be empty or null", ie.getMessage());
            throw ie;
        }
    }

    @Test(expected = InvalidBookReferenceException.class)
    public void retrieveBookInvalidReference() throws BookNotFoundException {
        try {
            bookService.retrieveBook("INVALID-REF");
        } catch (InvalidBookReferenceException ie) {
            assertEquals("Book Reference must start with BOOK-", ie.getMessage());
            throw ie;
        }
    }

    @Test(expected = BookNotFoundException.class)
    public void retrieveBookThrowsBookNotFound() throws BookNotFoundException {
        try {
            bookService.retrieveBook("BOOK-999");
        } catch (BookNotFoundException ie) {
            assertEquals("Book with reference BOOK-999 Not Found", ie.getMessage());
            throw ie;
        }
    }

    @Test
    public void retrieveBook() throws BookNotFoundException {
        Book book = bookService.retrieveBook("BOOK-GRUFF472");
        assertNotNull(book);
    }

    @Test(expected = BookNotFoundException.class)
    public void getBookSummaryThrowsBookNotFound() throws BookNotFoundException {
        bookService.getBookSummary("BOOK-999");
    }

    @Test
    public void getBookSummary() throws BookNotFoundException {
        String bookSummary = bookService.getBookSummary("BOOK-GRUFF472");
        assertNotNull(bookSummary);
        assertEquals("[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods.", bookSummary);
    }

    @Test
    public void getBookSummaryWinnieThePooh() throws BookNotFoundException {
        String bookSummary = bookService.getBookSummary("BOOK-POOH222");
        assertNotNull(bookSummary);
        assertEquals("[BOOK-POOH222] Winnie The Pooh - In this first volume, " +
                "we meet all the friends from the Hundred Acre Wood.", bookSummary);
    }

    @Test
    public void getBookSummaryWill987() throws BookNotFoundException {
        String bookSummary = bookService.getBookSummary("BOOK-WILL987");
        assertNotNull(bookSummary);
        assertEquals("[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside, " +
                "the good-natured Mole loses patience with spring cleaning. He flees his underground home, emerging to take in the air and ends up at the river, which he has " +
                "never seen before. Here he meets Rat (a water vole), who at this time of year spends all his days in, on and close by the river. Rat takes Mole for a ride " +
                "in his rowing boat. They get along well and spend many more days boating, with Rat teaching Mole the ways of the river.", bookSummary);
    }
}