public class Member {
    private String name;
    private int memberId;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Member ID: " + memberId);
    }
}
