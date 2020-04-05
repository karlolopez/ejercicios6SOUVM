/*
 */


package com.sistemasoperativos.ejercicios6sistemasoperativos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

class ContactoSingleton 
    { 
        
        private static ContactoSingleton single_instance = null; 

        // variable of type String 
        public String n; 
        public String t; 

        // private constructor restricted to this class itself 
        private ContactoSingleton(String nombre, String telefono) 
        { 
            n = nombre;
            t = telefono;
        } 

        // static method to create instance of Singleton class 
        public static ContactoSingleton getInstance() 
        { 
            if (single_instance == null) 
                single_instance = new ContactoSingleton("",""); 

            return single_instance; 
        } 
    }

/*
 */

public class Contactos {

    public static void main (String[] args) throws InterruptedException {
        
        List<String> list1 = new ArrayList<>(Arrays.asList("David","Jonathan","Karlo"));
        List<String> list2 = new ArrayList<>(Arrays.asList("55123444","00011423","01759392"));

        Thread thread1 = new Thread(() -> {
            duplicar(list1, list2, 2);
        });
        Thread thread2 = new Thread(() -> {
            duplicar(list2, list1, 0);
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        
        System.out.println(list1);
        System.out.println(list2);
        
    }
    
   

    private static void duplicar (List<String> nombres, List<String> telefonos, int contactoADuplicar) {
        log("Intentar bloquear lista nombres");
        synchronized (nombres) {
            log("La lista de nombres ha sido bloqueada.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log("Intentar blosquear lista de telefonos");
            synchronized (telefonos) {
                
                //Este bloque es seguro, podemos proceder.
                
                log("Lista telefonos bloqueada");
                String nombreADuplicar = nombres.get(contactoADuplicar);
                String telefonoADuplicar = telefonos.get(contactoADuplicar);
                
                log("Duplicado completo :)");
            }
        }
    }

    private static void log (String msg) {
        System.out.println(Thread.currentThread().getName() +
                                               ": " + msg);
    }
}

