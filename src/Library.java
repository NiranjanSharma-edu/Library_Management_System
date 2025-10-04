package src;

public class Student {
    private String name;
    private int idNo;
    private String stream;
    private String book1 = "";
    private String book2 = "";
    private int bookNo = 0;

    public Student(String name, int idNo, String stream) {
        this.name = name;
        this.idNo = idNo;
        this.stream = stream;
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getIdNo() { return idNo; }
    public String getStream() { return stream; }
    public String getBook1() { return book1; }
    public void setBook1(String book1) { this.book1 = book1; }
    public String getBook2() { return book2; }
    public void setBook2(String book2) { this.book2 = book2; }
    public int getBookNo() { return bookNo; }
    public void incrementBookNo() { bookNo++; }
    public void decrementBookNo() { bookNo--; }
}
