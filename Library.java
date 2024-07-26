import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void readBook(String bookTitle, int pages) {
        Book book = getBookByTitle(bookTitle);
        if (book != null) {
            book.readPages(pages);
            System.out.println("You read " + pages + " pages of \"" + book.getTitle() + "\".");
        } else {
            System.out.println("Book not found.");
        }
    }

    private Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        for (Book book : books) {
            book.displayInfo();
        }
    }

    public int getTotalPagesRead() {
        int totalPagesRead = 0;
        for (Book book : books) {
            totalPagesRead += book.getPagesRead();
        }
        return totalPagesRead;
    }

    public int getTotalPagesInLibrary() {
        int totalPages = 0;
        for (Book book : books) {
            totalPages += book.getTotalPages();
        }
        return totalPages;
    }

    public double getPagesReadPerDay(int days) {
        if (days == 0) return 0;
        return (double) getTotalPagesRead() / days;
    }
}
