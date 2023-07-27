import java.util.Date;

public class Consumidor implements Runnable {
    private Buffer buffer;
    public Consumidor(Buffer b){
        this.buffer=b;
    }

    public void run(){
        Date message=null;
        while(true){
            System.out.println("Consumidor durmiendo");
            Utilidades.dormir();

            message = (Date)buffer.remover();
            System.out.println(("Consumidor consumio: "+ message));
        }

    }
}
