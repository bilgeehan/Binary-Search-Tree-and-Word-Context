package proje2;

import java.util.ArrayList;

public class Consumer {

   protected String nameAndSurname;
   protected int id;
   protected ArrayList<String> listOfPurchase;
   protected String gender;
   protected int age;
   protected Consumer right;
   protected Consumer left;

    public Consumer(String nameAndSurname, int ID, String gender, int age) {
        this.nameAndSurname = nameAndSurname;
        this.id = ID;
        this.gender = gender;
        this.age = age;
        String asdf="asdf";
       int a= asdf.charAt(0);
    }

    public Consumer() {
        
    }

    @Override
    public String toString() {
        return "Consumer{" + "nameAndSurname=" + nameAndSurname + ", id=" + id + ", gender=" + gender + ", age=" + age + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consumer other = (Consumer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
