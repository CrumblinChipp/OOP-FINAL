package GameSetup;
import java.util.HashMap;
import java.util.Map;

public class City {

    private Map<String, Integer> attributes;

    City(int healthcare, int education, int innovation, int environment, int fund, int morale) {
        attributes = new HashMap<>();
        attributes.put("healthcare", healthcare);
        attributes.put("education", education);
        attributes.put("innovation", innovation);
        attributes.put("environment", environment);
        attributes.put("fund", fund);
        attributes.put("morale", morale);
    }

    public City() {
        this(0, 0, 0, 0, 0, 0);
    }

    public int getAttribute(String name) {
        return attributes.getOrDefault(name, 0);
    }

    public void setAttribute(String name, int value) {
        if (value < 0) value = 0;
        else if (value > 100) value = 100;
        attributes.put(name, value);
    }

}
