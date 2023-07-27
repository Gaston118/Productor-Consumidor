import java.util.concurrent.*;

public class Buffer implements BufferI{
    private static final int BUFFER_SIZE=3; //tamaño max del buffer
    private int contador; //items actuales en el buffer
    private int in; //apunta a la siguiente posición libre en el buffer
    private int out; //apunta a la primera posición ocupada en el buffer
    private Object[] buffer;
    private Semaphore mutex;
    private Semaphore vacio;
    private Semaphore lleno;
    public Buffer(){
        contador=0;
        in=0;
        out=0;
        buffer = new Object[BUFFER_SIZE];
        mutex = new Semaphore(1);
        vacio = new Semaphore(BUFFER_SIZE);
        lleno = new Semaphore(0);
    }

    public void insert(Object item){
        try {
            vacio.acquire();
            mutex.acquire();//mutua exclusion
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ++contador;
        buffer[in]=item;
        in=(in+1)%BUFFER_SIZE;

        if(contador==BUFFER_SIZE){
            System.out.println("Buffer lleno "
            + "Productor inserto: "+item
            + " contador: "+contador
            + "in: "+in+ " out: "+out);
        }
        else{
            System.out.println(
                    "Productor inserto: "+item
                    + " contador: "+contador
                    + "in: "+in+ " out: "+out);
        }
        mutex.release();
        lleno.release();
    }

    public Object remover(){
        Object item=null;
        try {
            lleno.acquire();
            mutex.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        --contador;
        item=buffer[out];
        out=(out+1)%BUFFER_SIZE;

        if (contador==0){
            System.out.println("Buffer vacio "
            + "consumidor removio: "+item
            + " contador: "+contador
            + " in: "+in+" out: "+out);
        }
        else {
            System.out.println(
                    "consumidor removio: "+item
                    + " contador: "+contador
                    + " in: "+in+" out: "+out);
        }
        mutex.release();
        vacio.release();
        return item;
    }
}
