import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Library {
    private LibraryDatabase database;

    public Library() {
        database = new LibraryDatabase();
    }

    public void addBook(Book book) {
        database.addBook(book);
    }

    public void addMember(Member member) {
        database.addMember(member);
    }

    public void readBook(String bookTitle, int pages) {
        Book book = database.getBookByTitle(bookTitle);
        if (book != null) {
            book.readPages(pages);
            database.updateBookPagesRead(bookTitle, book.getPagesRead());
            System.out.println("You read " + pages + " pages of \"" + book.getTitle() + "\".");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void displayBooks() {
        database.displayBooks();
    }

    public int getTotalPagesRead() {
        String sql = "SELECT SUM(pagesRead) AS totalPagesRead FROM books";
        int totalPagesRead = 0;
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                totalPagesRead = rs.getInt("totalPagesRead");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPagesRead;
    }

    public int getTotalPagesInLibrary() {
        String sql = "SELECT SUM(totalPages) AS totalPages FROM books";
        int totalPages = 0;
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                totalPages = rs.getInt("totalPages");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPages;
    }

    public double getPagesReadPerDay(int days) {
        if (days == 0) return 0;
        return (double) getTotalPagesRead() / days;
    }
}
