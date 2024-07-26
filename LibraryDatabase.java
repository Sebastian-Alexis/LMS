import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibraryDatabase {
    private static final String URL = "jdbc:sqlite:library.db";

    public LibraryDatabase() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load SQLite JDBC driver");
            e.printStackTrace();
        }
        createTables();
    }

    private void createTables() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String createBooksTable = "CREATE TABLE IF NOT EXISTS books ("
                                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                    + "title TEXT NOT NULL, "
                                    + "author TEXT NOT NULL, "
                                    + "totalPages INTEGER NOT NULL, "
                                    + "pagesRead INTEGER NOT NULL)";
            stmt.execute(createBooksTable);

            String createMembersTable = "CREATE TABLE IF NOT EXISTS members ("
                                      + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                      + "name TEXT NOT NULL)";
            stmt.execute(createMembersTable);
        } catch (SQLException e) {
            System.err.println("Failed to create tables");
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, totalPages, pagesRead) VALUES(?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getTotalPages());
            pstmt.setInt(4, book.getPagesRead());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to add book");
            e.printStackTrace();
        }
    }

    public void addMember(Member member) {
        String sql = "INSERT INTO members(name) VALUES(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to add member");
            e.printStackTrace();
        }
    }

    public void updateBookPagesRead(String title, int pagesRead) {
        String sql = "UPDATE books SET pagesRead = ? WHERE title = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pagesRead);
            pstmt.setString(2, title);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update book pages read");
            e.printStackTrace();
        }
    }

    public Book getBookByTitle(String title) {
        String sql = "SELECT * FROM books WHERE title = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Book(rs.getString("title"), rs.getString("author"), rs.getInt("totalPages"), rs.getInt("pagesRead"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to get book by title");
            e.printStackTrace();
        }
        return null;
    }

    public void displayBooks() {
        String sql = "SELECT * FROM books";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getInt("totalPages"), rs.getInt("pagesRead"));
                book.displayInfo();
            }
        } catch (SQLException e) {
            System.err.println("Failed to display books");
            e.printStackTrace();
        }
    }
}
