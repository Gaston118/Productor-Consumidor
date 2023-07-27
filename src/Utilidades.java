public class Utilidades {
    private static final int NAP_TIME=2;

    public static void dormir(){
        dormir(NAP_TIME);
    }

    public static void dormir(int time){
        int sleeptime = (int)(NAP_TIME*Math.random());
        System.out.println("Durmiendo: "+sleeptime+" seg.");
        try{
            Thread.sleep(sleeptime*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error en dormir "+ e);
        }
    }
}
