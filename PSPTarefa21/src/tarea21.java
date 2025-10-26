import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class tarea21 {


    static void main() {
        parking p = new parking(5);

        coche c1 = new coche(1, p);
        coche c2 = new coche(2, p);
        coche c4 = new coche(4, p);
        coche c3 = new coche(3, p);
        coche c5 = new coche(5, p);
        coche c6 = new coche(6, p);

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();


    }
}

class coche extends Thread {
    private parking p;
    int IDCoche;

    public coche(int IDCoche, parking p) {
        this.IDCoche = IDCoche;
        this.p = p;
    }

    @Override
    public void run() {

        this.p.entrar(this.IDCoche);
        try {
            sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.p.salir(this.IDCoche);

    }

}


class parking {

    private ArrayList<Integer> plazas = new ArrayList<>();

    public parking(int numeroPlazas) {

        tamañoParking(numeroPlazas);
        System.out.println(plazas.size());
    }

    private void tamañoParking(int numeroPlazas) {

        for (int i = 0; i < numeroPlazas; i++) {
            plazas.add(0);

        }

    }

    public synchronized void entrar(int IDcoche) {

        int numeroPlaza;

        while (!plazas.contains(0)) {

            System.out.println("Parking lleno");

            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        numeroPlaza = plazas.indexOf(0);
        plazas.set(numeroPlaza, IDcoche);
        System.out.println("El coche  " + IDcoche + " ha ocupado la plaza " + numeroPlaza);


        notifyAll();


    }


    public synchronized void salir(int IDcoche) {

       int numeroPlaza=plazas.indexOf(IDcoche);

       if(numeroPlaza!=-1){

           plazas.set(numeroPlaza, 0);
           System.out.println("El coche  " + IDcoche + " ha abandonado la plaza " + numeroPlaza);


       }else{
           System.out.println("Ese coche no está aquí");
           try {
               wait();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
        notifyAll();

    }


}