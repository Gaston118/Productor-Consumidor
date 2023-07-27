public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Thread productor = new Thread(new Productor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));

        productor.start();
        consumidor.start();
    }
}