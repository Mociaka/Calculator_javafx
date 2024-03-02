package calculator;

public class Token {
    public double value;
    public char type;

    public double getValue() {
        return value;
    }

    public char getType() {
        return type;
    }

    public Token(double value, char type) {
        this.value = value;
        this.type = type;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public String toString(){
        String s = "";
        if (type =='#') s+=value;
        else s+=type;
        return s;
    }
}
