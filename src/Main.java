import java.util.ArrayList;
import java.util.List;

interface Subject {
    void attach(Observer observer);
    void Opov(String notification);
}
interface Observer {
    void update(String notification);
}
class User implements Observer {
    private String name;
    private List<String> notifications = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String notification) {
        System.out.println(name + ", Вы получили новое уведомление: " + notification);
        notifications.add(notification);
    }
}

class Group implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String name;

    public Group(String name) {
        this.name = name;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void Opov(String notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }

    public void Newpost(String notification) {
        System.out.println("Уведомление из группы " + name + ": " + notification);
        Opov(notification);
    }
}

public class Main {
    public static void main(String[] args) {
        // Создание пользователей
        User user1 = new User("Андрей");
        User user2 = new User("Артём");

        // Создание групп и добавление пользователей в них
        Group group1 = new Group("Grand Croos:Origin");
        group1.attach(user1);
        group1.attach(user2);

        Group group2 = new Group("Grand Croos:SDS");
        group2.attach(user1);
        group2.attach(user2);

        Group group3 = new Group("Honkai Impact");
        group3.attach(user1);
        group3.attach(user2);

        Group group4 = new Group("Hoi 4");
        group4.attach(user2);

        // Оповещение всех пользователей о новых сообщениях в группах
        group1.Newpost("Новый пост в группе 'Grand Croos:Origin'");
        group2.Newpost("Новый пост в группе 'Grand Croos:SDS'");
        group3.Newpost("Новый пост в группе 'Honkai Impact'");
        group4.Newpost("Новый пост в группе 'Hoi 4'");
    }
}