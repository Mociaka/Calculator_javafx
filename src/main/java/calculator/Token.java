package calculator;

public class Token {
    public int value;
    public char type;

    public int getValue() {
        return value;
    }

    public char getType() {
        return type;
    }

    public Token(int value, char type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString(){
        return ("[" + value + type + "]");
    }
}
