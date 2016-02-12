package classes;


public class Dado {

    private int intt = Integer.MAX_VALUE;
    private double doublee = Double.MAX_VALUE;
    private String str;

    public Dado(int intt) {
        this.intt = intt;
    }

    public Dado(double floatt) {
        this.doublee = floatt;
    }

    public Dado(String str) {
        this.str = str;
    }

    public int getInt() {
        return intt;
    }

    public double getDouble() {
        return doublee;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        if (this.intt != Integer.MAX_VALUE) {
            return ("" + this.intt);
        }
        if (this.doublee != Double.MAX_VALUE) {
            return ("" + this.doublee);
        }
        return this.str;
    }

    public void setIntt(int intt) {
        this.intt = intt;
    }

    public void setDoublee(double doublee) {
        this.doublee = doublee;
    }

    
}
