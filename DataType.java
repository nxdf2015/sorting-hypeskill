package sorting;

public enum DataType {
    WORD("word"),
    LINE("line"),
    LONG("number"),
    NATURAL( "sorted data"),
    BYCOUNT("count");

    private String value;
    DataType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
