package Model;

import javax.persistence.*;

@Entity
@Table(name = "pokedex")
public class pokedex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "nome", nullable = false, length = 100)
    String nome;
    @Column(name = "peso", precision = 5, scale = 2)
    double peso;
    @Column(name = "misc", length = 100)
    String misc;

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public String getMisc() {
        return misc;
    }





    @Override
    public String toString() {
        return "pokedex{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", peso=" + peso +
                ", misc='" + misc + '\'' +
                '}';
    }
}











