package com.example.trucker;

import com.example.trucker.exceptions.TRUCKerListError;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class UserList {

    private static final String USERS_FILE_PATH =
            "src/main/resources/users.csv";

    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public ArrayList<User> getUserList() {
        return users;
    }

    public void setUser(int newId, String newUserName, String newPassword) throws TRUCKerListError {
        getUsers();
        for (User user : users) {
            if (user.getId()==(newId)) {
                removeFromList(user);
                User newUser = new User(newId, newUserName, newPassword);
                addUserToList(newUser);
            }
        }
        reloadFile();
        getUsers();
    }

    public void sorting() throws TRUCKerListError {
        getUsers();
        System.out.println("Sorting Users by ID.");
        Collections.sort(users, (Comparator.comparing(User::getId)));
        users.forEach(user -> System.out.println(user));
    }

    public String returnStatus(String name, String psw) throws TRUCKerListError {
        String status = null;
        getUsers();
        if (name != null && name.equals("") || psw != null && psw.equals("")) {
            throw new TRUCKerListError("User doesn't excist!");
        }
        System.out.println("We check the status of user.");
        for (User user : users) {
            if (name.startsWith("admin") && user.getUserName().equals(name) && user.getPassword().equals(psw)) {
                status = "admin";
            } else if (user.getUserName().equals(name) && user.getPassword().equals(psw)) {
                status = "driver";
            }
        }
        System.out.println(status);
        return status;
    }

    public void removeFromList(User user) throws TRUCKerListError {
        if (user == null) {
            throw new TRUCKerListError("You need to show egsisting user to remove it!");
        }
        getUsers();
        users.removeIf(u -> u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword()));
        reloadFile();
        getUsers();
        System.out.println("User was removed.");
    }

    public void addUserToList(User user) throws TRUCKerListError {
        if (user == null) {
            throw new TRUCKerListError("Fill all the requiered fields to add user!");
        }
        users.add(user);
        reloadFile();
        getUsers();
        System.out.println("User was added.");
    }

    public void reloadFile() throws TRUCKerListError {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(USERS_FILE_PATH));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (User userFromList : users) {
                csvPrinter.printRecord(Arrays.asList(userFromList.getId(), userFromList.getUserName(), userFromList.getPassword()));
                csvPrinter.flush();
            }

            csvPrinter.close();
            writer.close();
        } catch (IOException e) {
            throw new TRUCKerListError("Sorry, we can't save user to file.", e);
        }
    }

    public void printUsers() throws TRUCKerListError {
        getUsers();
        users.forEach(user -> System.out.println(user));
    }

    public User getOneUser(String username,String password) throws TRUCKerListError{
        User userWeNeed = null;
        getUsers();
        if (username != null && username.equals("") || password != null && password.equals("")) {
            throw new TRUCKerListError("User doesn't excist!");
        }
        System.out.println("We check the egsisting users.");
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                userWeNeed=user;
            }
        }
        System.out.println(userWeNeed);
        return userWeNeed;
    }


    public void getUsers() throws TRUCKerListError {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(USERS_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            readFromFile(csvParser);
            csvParser.close();
            reader.close();
        } catch (IOException e) {
            throw new TRUCKerListError("Sorry, we can't read users list file!", e);
        }
    }

    public void readFromFile(CSVParser csvParser) throws TRUCKerListError {
        users = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {

            Integer id = Integer.valueOf(csvRecord.get(0));
            String userName = csvRecord.get(1);
            String password = csvRecord.get(2);

            users.add(new User(id, userName, password));
        }
    }
}
