import Model.pokedex;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class funcionesPokedex {

    public static void main(String[] args) {
        funcionesPokedex fp = new funcionesPokedex();
//        fp.crearPokedexObxecto("Greymon", 400.5, "Dinosaurio");
//        fp.crearPokedexObxecto("Bakemon", 70.5, "Fantasma");
//        fp.crearPokedexObxecto("Baihumon", 1500.5, "Foca");
//        fp.crearPokedexObxecto("Delumon", 100.5, "pingüino");
//        fp.crearPokedexObxecto("Boutmon", 150.2, "macaco");
//        fp.crearPokedexObxecto("Togemon", 700.3, "cactus");
//        fp.crearPokedexObxecto("Buraimon", 300.8, "samurai");
//        fp.crearPokedexObxecto("Cutemon", 30.7, "conejo");
//        fp.crearPokedexObxecto("Angemon", 500.2, "angel");
//        fp.crearPokedexObxecto("Garurumon", 500.7, "lobo");
        fp.leerPokedex(1);
        fp.leerPokedex(2);
        fp.leerPokedex(3);
        fp.leerPokedex(4);
        fp.leerPokedex(5);
        fp.leerPokedex(6);
        fp.leerPokedex(7);
        fp.leerPokedex(8);
        fp.leerPokedex(9);
        fp.leerPokedex(10);

        fp.modificarPokedex(5, "Akatorimon", 100, "gallina");
        fp.modificarPokedex(7, "Airdramon", 1000, "culebra");

        fp.leerPokedex(1);
        fp.leerPokedex(2);
        fp.leerPokedex(3);
        fp.leerPokedex(4);
        fp.leerPokedex(5);
        fp.leerPokedex(6);
        fp.leerPokedex(7);
        fp.leerPokedex(8);
        fp.leerPokedex(9);
        fp.leerPokedex(10);

        fp.eliminarPokedex(1);
        fp.eliminarPokedex(2);
        fp.eliminarPokedex(3);
        fp.eliminarPokedex(4);
        fp.eliminarPokedex(5);
        fp.eliminarPokedex(6);
        fp.eliminarPokedex(7);
        fp.eliminarPokedex(8);
        fp.eliminarPokedex(9);
        fp.eliminarPokedex(10);


    }


    public void crearPokedexObxecto(String name, double peso, String misc) {
        try (Session ses = HibernateConfig.getSessionFactory().openSession()) {

            Transaction transaction = ses.beginTransaction();

            pokedex poke = new pokedex();

            poke.setNome(name);
            poke.setPeso(peso);
            poke.setMisc(misc);
            ses.save(poke);
            transaction.commit();


        } catch (Exception e) {
            System.err.println("ERROR TIPO: " + e.getMessage());
        }

    }

    public void crearPokedexQuery() {


    }

    public pokedex leerPokedex(int id) {

        try (Session ses = HibernateConfig.getSessionFactory().openSession()) {

            return ses.get(pokedex.class, id);
        } catch (Exception e) {
            System.err.println("ERROR DE CONEXION: " + e.getMessage());
            return null;
        }


    }

    public void eliminarPokedex(int id) {
        try (Session ses = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = ses.beginTransaction();
            pokedex poke = ses.get(pokedex.class, id);
            ses.delete(poke);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("ERROR TIPO: " + e.getMessage());

        }
    }


    public void modificarPokedex(int id, String nome, double peso, String misc) {
        try (Session ses = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = ses.beginTransaction();
            pokedex poke = ses.get(pokedex.class, id);

            poke.setId(id);
            poke.setNome(nome);
            poke.setPeso(peso);
            poke.setMisc(misc);
            ses.update(poke);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("ERROR TIPO: " + e.getMessage());

        }
    }


    public List<pokedex> leerPokedexToda() {
        List<pokedex> listaPokedex = null;
        try (Session ses = HibernateConfig.getSessionFactory().openSession()) {
            ses.beginTransaction();

            String hql = "FROM pokedex";
            listaPokedex = ses.createQuery(hql, pokedex.class).getResultList();
            ses.getTransaction().commit();


        } catch (Exception e) {
            System.err.println("Error al encontrar todos los Pokémon: " + e.getMessage());
        }
        return listaPokedex;
    }
}




