import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private Map<String, User> userDatabase;

    public UserService(){
        userDatabase = new HashMap<>();
        userDatabase.put("1",new User("initial1","email1",1));
        userDatabase.put("2",new User("initial2","email2",2));
        userDatabase.put("3",new User("initial3","email3",3));
    }

    public void addUser(String str,User user){
        userDatabase.put(str,user);
    }


    public Optional<User> findUserByName(String name){
        return userDatabase.values().stream().filter(u->u.getName().equals(name)).findFirst();
    }

//    public String getUserEmail(String name) - uses Optional to return email or "No
//    email provided"
    public String getUserEmail(String name){
        return findUserByName(name).map(User::getEmail).orElse("No email provided.");
    }
//    public int getUserAge(String name) - uses Optional to return age or 0
    public Integer getUserAge(String name){

        return findUserByName(name).map(User::getAge).orElse(0);
    }

}
