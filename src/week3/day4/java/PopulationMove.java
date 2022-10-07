package week3.day4.java;

public class PopulationMove {
    private int fromSido;
    private int toSido;

    // constructor추가 fromSido, toSido를 받아서 멤버변수에 넣는 기능


    public PopulationMove(int fromSido, int toSido) {
        this.fromSido = fromSido;
        this.toSido = toSido;
    }
    public PopulationMove(String fromSido, String toSido) {
        this.fromSido = Integer.parseInt(fromSido);
        this.toSido = Integer.parseInt(toSido);
    }

    public int getFromSido() {
        return fromSido;
    }

    public int getToSido() {
        return toSido;
    }
}
