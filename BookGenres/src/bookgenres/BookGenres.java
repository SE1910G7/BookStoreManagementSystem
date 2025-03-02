import java.util.*;

class BookGenre {
    String bookGenresId;
    String bookId;
    String genreId;
    String bookName;
    String genreName;

    public BookGenre(String bookGenresId, String bookId, String genreId, String bookName, String genreName) {
        this.bookGenresId = bookGenresId;
        this.bookId = bookId;
        this.genreId = genreId;
        this.bookName = bookName;
        this.genreName = genreName;
    }
}