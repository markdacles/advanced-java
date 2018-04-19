public class StringPair {
    private String key;
    private String value;

    public StringPair(String aKey, String aValue)
    {
        key   = aKey;
        value = aValue;
    }

    public void setKey(String aKey) { key = aKey; }
    public void setValue(String aValue) { value= aValue; }

    public void setRandomKey() {
    	for(int i = ((int)(Math.random() * 5) + 1); i > 0; i--) {
    		key += (char)((int)(Math.random() * 95) + 32);
    	}
    }

    public void setRandomValue() {
    	for(int i = ((int)(Math.random() * 5) + 1); i > 0; i--) {
    		value += (char)((int)(Math.random() * 95) + 32);
    	}
    }

    public String key()   { return key; }
    public String value() { return value; }

    public String merged() { return key+value; }
}