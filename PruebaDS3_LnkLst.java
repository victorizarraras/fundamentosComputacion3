package com.mycompany.pruebads3_lnklst;/**
 *
 * @author AngelLopez Vigil
 * @date Aug, 17, 2026
 */
class Student {
    String name;
    int score;
    Student next;
    
    public Student(String name, int score) {
        this.name = name;
        this.next = null;
        this.score = score;
    }
}
    class LnkdLst {
        Student head;
        public LnkdLst() {
    this.head = null;
    }
    //Agrega un nuevo estudiante al final de la lista
    public void add(String name,int score) {
        Student newStudent = new Student(name,score);
        if(head == null) {
            head = newStudent;
            return;
      
    }
        Student current = head;
        while(current.next !=null) {
           current = current.next;  
        }   
        current.next = newStudent;
    }
    public void print() {
        Student current = this.head;
        while(current !=null) {
            System.out.println(current.name + " - " + current.score);
            current = current.next;
        }
    }
}

public class PruebaDS3_LnkLst {
    public static void main(String[] args) {
        LnkdLst lista = new LnkdLst();
        lista.add("Linus",90);
        lista.add("ada", 95);
        lista.add("Paul", 70);
        lista.print();
    }
}
    
        