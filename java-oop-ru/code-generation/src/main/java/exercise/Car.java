package exercise;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// BEGIN

// END
@Value
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        //{"id":5,"brand":"audi","model":"q7","color":"white","owner":{"id":8,"firstName":"Nikolay","lastName":"Ivanov","age":50}}
        Map<String, Object> car = new HashMap<>();
        car.put("id", id);
        car.put("brand", brand);
        car.put("model", model);
        car.put("color", color);
        car.put("owner", owner);
        String json = "";
        try {
            json =new ObjectMapper().writeValueAsString(car);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return json;
    }

    public static Car unserialize(String json) {
        Car car = null;
        try {
            car = new ObjectMapper().readValue(json, Car.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return car;
    }
    // END
}
