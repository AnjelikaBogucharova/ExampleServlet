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
        model.put(2, new User("Mikhail", "Mikhaylov", 22222));
    }

    public void add(User user, int id){
        model.put(id,user);
    }

    public void update(User user, int id){
        model.get(id).setName(user.getName());
        model.get(id).setSurname(user.getSurname());
        model.get(id).setSalary(user.getSalary());
    }

    public void remove(int id){
        model.remove(id);
    }

    public Map<Integer, User> getFromList(){
        return model;
    }

}
