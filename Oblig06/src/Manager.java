
import krypto.*;
import java.util.ArrayList;

public class Manager
{
    static CryptoQueue<MsgPart> encryptedMsgs;
    static int activeListeners;
    private static int activeInterpreters;
    private static int interpreterThreads = 2500;
    static int totalIntercepted;

    public static void main(String[] args) throws Exception
    {
        try
        {
            interpreterThreads = Integer.parseInt(args[0]);
        }catch(Exception e)
        {
            System.out.println("Error handling parameter arguments\nrun as Manager [number of threads]");
            System.out.println("Using default thread count: "+interpreterThreads);
            System.out.println("My CPU's hot but my cores run cold, starting in 5000ms, Good luck...");
            Thread.sleep(5000);
        }

        long start = System.currentTimeMillis();
        Operasjonssentral ops = new Operasjonssentral(3);
        Kanal[] channels = ops.hentKanalArray();
        ArrayList<Listener> listeners = new ArrayList<>();
        ArrayList<Interpreter> interpreters = new ArrayList<>();
        encryptedMsgs = new CryptoQueue<>();
        activeListeners = 0;
        activeInterpreters = 0;
        totalIntercepted = 0;

        for(Kanal channel: channels)
        {
            listeners.add(new Listener(channel));
            activeListeners++;
        }

        for(int i = 0; i<interpreterThreads; i++)
        {
            interpreters.add(new Interpreter(i));
            activeInterpreters++;
        }

        for(Listener l: listeners) l.start();
        for(Interpreter i: interpreters) i.start();


        System.out.println("Monitoring and decrypting");
        boolean busy = true;
        int runningInterpreters;
        while(busy)
        {
            try
            {
                busy = false;
                runningInterpreters = 0;
                for(Interpreter it: interpreters)
                {
                    if(it.isAlive())
                    {
                        busy = true;
                        runningInterpreters++;
                    }
                }
                System.out.printf("Running interpreters: %d%n", runningInterpreters);
                Thread.sleep(1000);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("\n\n");
        for(Listener l: listeners) l.printMessageStream();


        System.out.println("\nAll Channels silent");
        System.out.printf("Messages intercepted: %d%n", totalIntercepted);
        System.out.printf("Messages still encrypted: %d%n", encryptedMsgs.size());
        System.out.printf("Time to complete: %dms%n", (System.currentTimeMillis()-start));
    }
}
