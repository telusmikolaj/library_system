import com.company.model.Book;
import com.company.service.BookService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class BookServiceTest {



    @Test
    public void isBookAvailableTest() throws SQLException {
        Book book = new Book("To kill aa mockingbird",
                "Harper Lee",
                "Novel",
                "300",
                "pub");

        book.setAvailbility(1);
        BookService.insertBook(book);

        Assert.assertTrue(BookService.isBookAvailable(bookFromDB.getId()));

    }


    @Test
    public void updateBookTest() throws SQLException {
        Book book = new Book("Clean Code",
                "Martin",
                "Computer Science",
                "800",
                "Helion");

        BookService.insertBook(book);
        Book bookBeforeUpdate = BookService.searchBookByTitle(book.getTitle());
        bookBeforeUpdate.setTitle("Clean Architecture");
        BookService.updateBook(bookBeforeUpdate);
        Book updatedBook = BookService.findBookById(bookBeforeUpdate.getId());

        Assert.assertEquals(updatedBook.getTitle(),"Clean Architecture");
    }



    @Test
    public void deleteBookTest() throws SQLException {
        Book book = new Book("Algorithms",
                "Sedgewick",
                "Computer Science",
                "800",
                "Helion");

        BookService.insertBook(book);
        int insertedBookId = BookService.searchBookByTitle(book.getTitle()).getId();
        BookService.deleteBook(insertedBookId);
        Book bookFromDb = BookService.searchBookByTitle(book.getTitle());

        Assert.assertNull("Book from database should be null", bookFromDb);

    }


    @Test
    public void searchBookByTitleTest() throws SQLException {
        Book book = new Book("Java",
                "Horstman",
                "Computer Science",
                "800",
                "Helion");

        BookService.insertBook(book);
        Book bookFromDB = BookService.searchBookByTitle(book.getTitle());

        Assert.assertEquals(book.getTitle(), bookFromDB.getTitle());

    }

    @Test
    public void insertBookTest() throws SQLException {
        Book book = new Book("Pride and Prejudice",
                "Jane Austen",
                "novel",
                "300",
                "Whitehall");

        BookService.insertBook(book);
        Book bookFromDB = BookService.searchBookByTitle(book.getTitle());

        Assert.assertEquals(book.getTitle(), bookFromDB.getTitle());
    }
}
