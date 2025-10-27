import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class tarea21 {

    static void main(String[] args) {


        parking p = new parking(3);

        coches c = new coches(5, p);


    }

}

class coches {
    private ArrayList<coche> listaCoches = new ArrayList<>();

    public coches(int cantidad, parking p) {

        for (int i = 1; i <= cantidad; i++) {

            coche c = new coche(i, p);
            this.listaCoches.add(c);


        }

        for (coche c : this.listaCoches) {
            c.start();
        }

    }


}


class coche extends Thread {

    private parking p;
    private int IDCoche;

    public coche(int IDCoche, parking p) {

        this.p = p;
        this.IDCoche = IDCoche;
    }

    @Override
    public void run() {


        while (true) {
            try {
                sleep((long) (((Math.random() * (5)) + 1) * 1000));
                p.entrada(IDCoche);
                sleep((long) (((Math.random() * (5)) + 1) * 1000));
                p.salir(IDCoche);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}


class parking {
    private List<Integer> plazas = new ArrayList<>();


    public parking(int numeroPlazas) {
        for (int i = 0; i < numeroPlazas; i++) {

            this.plazas.add(0);
        }


    }


    public synchronized void entrada(int IDCoche) {


        while (!plazas.contains(0)) {
            try {
                System.out.println("Parking lleno, coche " + IDCoche + " debe esperar");
                wait();
            } catch (InterruptedException e) {

            }
        }
        int plazaLibre = plazas.indexOf(0);
        this.plazas.set(plazaLibre, IDCoche);
        System.out.println("El coche " + IDCoche + " ha ocupado la plaza " + plazaLibre);


    }

    public synchronized void salir(int IDCoche) {

        if (plazas.contains(IDCoche)) {
            int plazaOcupada = plazas.indexOf(IDCoche);
            System.out.println("El coche " + IDCoche + " ha abandonado la plaza " + plazaOcupada);
            plazas.set(plazaOcupada, 0);
            notifyAll();
        } else {
            System.out.println("El coche " + IDCoche + " no está aquí");

        }

    }


}



