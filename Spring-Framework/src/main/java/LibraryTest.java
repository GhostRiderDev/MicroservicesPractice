import com.java.hibernate.Book;
import com.java.hibernate.Library;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class LibraryTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("library.xml");
        Library library = (Library) context.getBean("lib");
        List<Book> bookList = library.getBooks();
        for (Book book: bookList) {
            System.out.println(book);
        }
    }
}
