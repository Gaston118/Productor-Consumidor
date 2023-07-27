import java.util.Date;

public class Productor implements Runnable{
    private Buffer buffer;
    public Productor(Buffer b){
        this.buffer=b;
    }

    public void run(){
        Date message=null;
        while(true){
            System.out.println("Productor durmiendo");
            Utilidades.dormir();
            message= new Date();
            System.out.println(("Productor produjo: "+ message));
            buffer.insert(message);
        }

    }
}
