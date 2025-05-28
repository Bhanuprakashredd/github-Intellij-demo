import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstSource implements Runnable{
    private BlockingQueue<Person> queue;
    public FirstSource(BlockingQueue<Person> queue){
        this.queue = queue;
    }
    public void run() {
        try {
            queue.put(new Person("Komma", 22));

            queue.put(new Person("Nomma", 21));

            queue.put(new Person("aomma", 20));

            queue.put(new Person("somma", 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class SecondSource implements Runnable{
    private BlockingQueue<Person> queue;
    public SecondSource(BlockingQueue<Person> queue){
        this.queue = queue;
    }
    public void run()
    {
        try {
            Thread.sleep(1000);

            System.out.println(queue.take());

            System.out.println(queue.take());

            System.out.println(queue.take());

            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Person implements Comparable<Person>{
    private String Name;
    private int Age;
    public Person(String Name,int Age){
        this.Name = Name;
        this.Age = Age;
    }
    public int compareTo(Person person){
          return Name.compareTo(person.getName());
    }
    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public int getAge(){
        return Age;
    }
    public void setAge(int Age){
        this.Age = Age;
    }
    public String toString(){
       return "Person [Name:"+Name+" "+"Age:"+Age+" "+"]";
    }
}
public class priority_Queue {
    public static void main(String[] args){
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
        FirstSource first = new FirstSource(queue);
        SecondSource second = new SecondSource(queue);
        new Thread(first).start();

        new Thread(second).start();

    }
}
