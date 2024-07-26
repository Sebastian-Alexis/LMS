public class Book {
    private String title;
    private String author;
    private int totalPages;
    private int pagesRead;

    public Book(String title, String author, int totalPages) {
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.pagesRead = 0;
    }

    public Book(String title, String author, int totalPages, int pagesRead) {
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.pagesRead = pagesRead;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public void readPages(int pages) {
        if (pagesRead + pages <= totalPages) {
            pagesRead += pages;
        } else {
            pagesRead = totalPages;
        }
    }

    public void displayInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Total Pages: " + totalPages + ", Pages Read: " + pagesRead);
    }
}
