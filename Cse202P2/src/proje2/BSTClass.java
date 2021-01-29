/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author beşiktaşlı
 */
public class BSTClass {

    Consumer root;

    public BSTClass() {
        root = null;
    }

    public Consumer search(int id) throws Exception {
        if (root == null) {
            return null;
        }
        Consumer temp = root;
        while (temp.id != id) {
            if (id < temp.id) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
            if (temp == null) {
                return null;
            }
        }
        return temp;
    }

    public Consumer search(String nameAndSurname) throws Exception {
        Consumer temp = root;
        if (root == null || temp.nameAndSurname.equals(nameAndSurname)) {
            return root;
        }
        while (temp.left != null) {
            if (nameAndSurname.equals(temp.nameAndSurname)) {
                return temp;
            } else if (!nameAndSurname.equals(root.nameAndSurname)) {
                temp = temp.left;
                search(nameAndSurname);
            }
        }
        while (temp.right != null) {
            if (nameAndSurname.equals(temp.nameAndSurname)) {
                return temp;
            } else if (!nameAndSurname.equals(temp.nameAndSurname)) {
                temp = temp.left;
                search(nameAndSurname);
            }
        }
        return temp;
    }

    public Consumer searchWithRanges(int minId, int maxId) throws Exception {
        if (root == null) {
            return null;
        }
        Consumer temp = root;
        while (temp.id != minId) {
            if (minId < temp.id) {
                if (maxId > temp.id) {
                    temp = temp.left;
                }
            } else if (minId > temp.id) {
                if (maxId > temp.id) {
                    temp = temp.right;
                }
            }
            if (temp == null) {
                return null;
            }
        }
        return temp;
    }

    public Consumer searchWithRanges(String nameAndSurname) throws Exception {
        Consumer temp = root;
        if (root == null || temp.nameAndSurname.equals(nameAndSurname)) {
            return root;
        }
        while (temp.left != null) {
            if (nameAndSurname.equals(temp.nameAndSurname)) {
                return temp;
            } else if (!nameAndSurname.equals(root.nameAndSurname)) {
                temp = temp.left;
                search(nameAndSurname);
            }
        }
        while (temp.right != null) {
            if (nameAndSurname.equals(temp.nameAndSurname)) {
                return temp;
            } else if (!nameAndSurname.equals(temp.nameAndSurname)) {
                temp = temp.left;
                search(nameAndSurname);
            }
        }
        return temp;
    }

    public void insertBSTWithNameAndSurname(String nameAndSurname) throws Exception {
        Consumer con = new Consumer();
        con.nameAndSurname = nameAndSurname;
        int i = 0;
        if (root == null) {
            root = con;
        } else {
            Consumer chooser = root;
            Consumer parent = root;
            while (chooser != null) {
                parent = chooser;
                if ((int) nameAndSurname.charAt(i) < (int) chooser.nameAndSurname.charAt(i)) {
                    chooser = chooser.left;
                } else if ((int) nameAndSurname.charAt(i) > (int) chooser.nameAndSurname.charAt(i)) {
                    chooser = chooser.right;
                } else if ((int) nameAndSurname.charAt(i) == (int) chooser.nameAndSurname.charAt(i)) {
                    i++;
                    return;
                }
                              
            }
            int n = 0;
            boolean temp = true;
            while (temp = true) {
                if ((int) nameAndSurname.charAt(n) > (int) parent.nameAndSurname.charAt(n)) {
                    parent.right = con;
                    temp = false;
                } else if ((int) nameAndSurname.charAt(n) < (int) parent.nameAndSurname.charAt(n)) {
                    parent.left = con;
                    temp = false;
                } else if ((int) nameAndSurname.charAt(n) == (int) parent.nameAndSurname.charAt(n)) {
                    n++;
                    temp = true;
                }            
            }
        }
    }

    public void insertBSTWithId(int id) throws Exception {
        Consumer con = new Consumer();
        con.id = id;
        if (root == null) {
            root = con;
        } else {
            Consumer chooser = root;
            Consumer parent = root;
            while (chooser != null) {
                parent = chooser;
                if (id < chooser.id) {
                    chooser = chooser.left;
                } else if (id > chooser.id) {
                    chooser = chooser.right;
                }
            }
            if (id > parent.id) {
                parent.right = con;
            } else {
                parent.left = con;
            }
        }
    }

    public void insertBSTWithAge(int age) throws Exception {
        Consumer con = new Consumer();        
        con.age = age;
        if (root == null) {
            root = con;
        } else {
            Consumer first = root;
            Consumer second = root;
            while (first != null) {
                second = first;
                if (age < first.age) {
                    first = first.left;
                } else if (age > first.age) {
                    first = first.right;
                }
            }
            if (age > second.age) {
                second.right = con;
            } else {
                second.left = con;
            }
        }
    }

    public void delete(int id) throws Exception,NullPointerException {
        Consumer deleted = root;
        Consumer parent = root;
        boolean isleft = true;
        //find node
        while (deleted.id != id) {
            parent = deleted;
            if (id < deleted.id) {
                isleft = true;
                deleted = deleted.left;
            } else {
                isleft = false;
                deleted = deleted.right;
            }
            if (deleted == null) {
                break;
            }
        }
        //no child
        if (deleted.left == null && deleted.right == null) {
            if (deleted == root) {
                root = null;
            } else if (isleft = true) {
                parent.left = null;
            } else if (isleft = false) {
                parent.right = null;
            }
        }//one child
        else if (deleted.right == null) {
            if (deleted == root) {
                root = deleted.left;
            } else if (isleft == true) {
                parent.left = deleted.left;
            } else if (isleft == false) {
                parent.right = deleted.left;
            }
        } else if (deleted.left == null) {
            if (deleted == root) {
                root = deleted.right;
            } else if (isleft == true) {
                parent.left = deleted.right;
            } else if (isleft == false) {
                parent.right = deleted.right;
            }
        }//two child
        else {
            Consumer temp = deleted.right;
            while (temp.left != null) {//min val after itself
                temp = temp.left;
            }
            Consumer minValue = temp;
            if (deleted == root) {
                deleted = minValue;
            } else if (isleft == true) {
                parent.left = minValue;
            } else {
                parent.right = minValue;
                minValue.left = deleted.left;
            }
        }
    }

    public Consumer findMaxValue() throws Exception {
        Consumer max = root;
        while (max.right != null) {
            max = max.right;
        }
        return max;
    }

    public Consumer findMinValue() throws Exception {
        Consumer min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    public void createNewFile() throws IOException, NullPointerException, Exception {
        Consumer current = root;
        String name = root.nameAndSurname;
        File info = new File(name + ".txt");
        BufferedWriter br = new BufferedWriter(new FileWriter(info));
        br.write("Consumer_name_and_surname:" + " " + current.nameAndSurname);
        br.newLine();
        br.write("Consumer_id:" + " " + current.id);
        br.newLine();
        br.write("age:" + " " + current.age);
        br.newLine();
        br.write("Gender:" + " " + current.gender);
        for (int i = 0; i < current.listOfPurchase.size(); i++) {
            br.newLine();
            br.write("purchase_list:" + " " + current.listOfPurchase.get(i));
        }
        br.close();
    }

    public void readFromFile(File file) throws IOException, NullPointerException, Exception {
        Consumer con = new Consumer();
        String fileString = new String(Files.readAllBytes(Paths.get(file.getPath())), StandardCharsets.UTF_8);
        String[] words = fileString.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if ("Consumer_name_and_surname:".equals(words[i])) {
                con.nameAndSurname.equals(words[i + 1]);
            } else if ("Consumer_id:".equals(words[i])) {
                con.id = Integer.valueOf(words[i + 1]);
            } else if ("age:".equals(words[i])) {
                con.age = Integer.valueOf(words[i + 1]);
            } else if ("Gender:".equals(words[i])) {
                con.gender.equals(words[i + 1]);
            } else if ("purchase_list:".equals(words[i])) {
                con.listOfPurchase.add(words[i + 1]);
            }
        }
        
    }

    public static void main(String[] args) throws IOException, Exception, NullPointerException {
        Scanner scn = new Scanner(System.in);
        BSTClass bst = new BSTClass();
        System.out.println("Press 1 for search consumer with id");
        System.out.println("Press 2 for search consumer with name and surname ");
        System.out.println("Press 3 for search costumer between choosen id's ");
        System.out.println("Press 4 for search costumer with name and surname");
        System.out.println("Press 5 for insert binary search tree with name and surname");
        System.out.println("Press 6 for insert binary search tree with id");
        System.out.println("Press 7 for insert binary search tree with age");
        System.out.println("Press 8 for deleting consumer from tree");
        System.out.println("Press 9 for finding maximum value of tree");
        System.out.println("Press 10 for finding minimum value of tree");
        System.out.println("Press 11 for creating costumer file");
        System.out.println("Press 12 for uploading created file to system");
        System.out.println("Press 0 for exit");
        int choice = scn.nextInt();
        switch (choice) {
            case 0:
                System.out.println("Good Bye");
                break;
            case 1:
                System.out.println("Enter id");
                int temp = scn.nextInt();
                bst.search(temp);
                break;
            case 2:
                System.out.println("Enter name and surname");
                String temp0 = scn.next();
                bst.search(temp0);
                break;
            case 3:
                System.out.println("Enter minimum value of id");
                int temp1 = scn.nextInt();
                System.out.println("Enter maximum value of id");
                int temp2 = scn.nextInt();
                bst.searchWithRanges(temp1, temp2);
                break;
            case 4:
                System.out.println("Enter name and surname");
                String temp3 = scn.next();
                bst.searchWithRanges(temp3);
                break;
            case 5:
                System.out.println("Enter name and surname");
                String temp4 = scn.next();
                bst.insertBSTWithNameAndSurname(temp4);
                break;
            case 6:
                System.out.println("Enter id");
                int temp5 = scn.nextInt();
                bst.insertBSTWithId(temp5);
                break;
            case 7:
                System.out.println("Enter age");
                int temp6 = scn.nextInt();
                bst.insertBSTWithAge(temp6);
                break;
            case 8:
                System.out.println("Enter id");
                int temp7 = scn.nextInt();
                bst.delete(temp7);
                break;
            case 9:
                System.out.println("maximum value in tree is : " + bst.findMaxValue());
                break;
            case 10:
                System.out.println("minimum value in tree is : " + bst.findMinValue());
                break;
            case 11:
                bst.createNewFile();
                break;
            case 12:
                System.out.println("Enter way of file");
                String way = scn.next();
//                Scanner file = new Scanner(new FileReader(C:\\Users\\beşiktaşlı\\Documents\\NetBeansProjects\\BinarySearchTree2\\customer.txt));
                File file = new File(way);
                bst.readFromFile(file);
        }
    }
}
