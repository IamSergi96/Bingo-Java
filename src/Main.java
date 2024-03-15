import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Jugador jugador1 = new Jugador("Juan", "123456789A");
        Jugador jugador2 = new Jugador("Sofia", "987654321B");

        //comprar algunos cartones
        jugador1.comprarCarton();
        jugador2.comprarCarton();
        jugador1.comprarCarton();
        jugador2.comprarCarton();

        Random random = new Random();
        int numeroGanador;
        boolean hayGanador = false;

        //bucle para jugar
        while(!hayGanador){
            numeroGanador = random.nextInt(51);
            //comprobacion
            for(Carton carton: jugador1.getConjuntoCartones()){
                //comprobar para cada carton del jugador si tiene el numero
                if(carton.contieneNumeros(numeroGanador)){
                    System.out.println("El jugador 1 gana con el numero: "+numeroGanador);
                    hayGanador=true;
                    break;
                }
            }
            if(!hayGanador){
                for(Carton carton: jugador2.getConjuntoCartones()){
                    if(carton.contieneNumeros(numeroGanador)){
                        System.out.println("El jugador 2 gana con el numero: "+numeroGanador);
                        hayGanador=true;
                        break;
                    }
                }
            }
        }
    }
}
class Carton{
    int[] numerosBingo = new int[10];
    Random random = new Random();
    public void generarNumeros(){
        for(int i=0;i<numerosBingo.length;i++){
            int numeroCreado = random.nextInt(51);
            numerosBingo[i]=numeroCreado;
        }
    }
    //metodo para comprobar si el numero que sale en el bingo esta en el carton
    public boolean contieneNumeros(int numero){
        for(int num:numerosBingo){
            if(num==numero){
                return true;
            }
        }
        return false;
    }
}
class Jugador{
    private ArrayList<Carton> conjuntoCartones;
    private double saldo;
    private String nombre, dni;

    //getter de conjunto cartones para acceder desde Main
    public ArrayList<Carton> getConjuntoCartones() {
        return conjuntoCartones;
    }
    //constructor:
    public Jugador(String nombre, String dni){
        this.conjuntoCartones= new ArrayList<>();
        //que todos empiecen con 30 de saldo
        this.saldo=30.0;
        this.nombre=nombre;
        this.dni=dni;
    }
    //metodos: recargar salgo y comprar cartones (10 de saldo cadauno)
    public void recargarSaldo(double cantidad){
        this.saldo+=cantidad;
        System.out.println("Saldo recargado correctamente. Saldo actual: "+this.saldo);
    }
    public void comprarCarton(){
        if(this.saldo>=10){
            Carton nuevoCarton= new Carton();
            nuevoCarton.generarNumeros();
            this.conjuntoCartones.add(nuevoCarton);
            this.saldo -=10.0;
            System.out.println("Carton comprado correctamente. Saldo actual: "+this.saldo);
        }else {
            System.out.println("No tienes saldo suficiente. Te faltan "+(10.0-this.saldo));
        }
    }
}