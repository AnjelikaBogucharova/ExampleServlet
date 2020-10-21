package com.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance(){
        return instance;
    }

    private Model(){
        model = new HashMap<>();

        model.put(1, new User("Ivan", "Ivanov", 11111));
    }

    public void add(User user, int id){
        model.put(id,user);
    }

    public Map<Integer, User> getFromList(){
        return model;
    }

}
