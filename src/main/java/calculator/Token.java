package calculator;

public class Token {
    public long value;
    public char type;

    public long getValue() {
        return value;
    }

    public char getType() {
        return type;
    }

    public Token(long value, char type) {
        this.value = value;
        this.type = type;
    }

    public void setValue(long value) {
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
