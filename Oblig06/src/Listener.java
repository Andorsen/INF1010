import krypto.Kanal;
import java.util.ArrayList;
import java.io.*;

class Listener extends Thread
{
    private Kanal channel;
    private int channelId;
    private ArrayList<MsgPart> messageStream;


    Listener(Kanal pChannel)
    {
        channel = pChannel;
        channelId = channel.hentId();
        messageStream = new ArrayList<>();
    }

    public void run()
    {
        System.out.printf("Listening on channel %d%n", channelId);
        String message = channel.lytt();
        MsgPart part;

        while(message != null)
        {
            part = new MsgPart(message);
            messageStream.add(part);
            Manager.encryptedMsgs.add(part);
            Manager.totalIntercepted++;
            message = channel.lytt();
        }

        System.out.printf("Channel gone silent! - Channel :%d%n", channelId);
        Manager.activeListeners--;
    }

    void printMessageStream()
    {
        try
        {
            System.out.printf("Writing stream for Channel: %d%n", channelId);
            System.out.printf("Messages: %d%n", messageStream.size());
            String outputFileName = "channel"+channelId+".txt";
            PrintWriter writer = new PrintWriter(new File(outputFileName));
            writer.printf("%nMessage Stream for channel: %d%n", channelId);
            for (MsgPart msg : messageStream)
            {
                writer.printf("%n%s%n%n" ,msg.getMessage());
            }
            writer.printf("End Message Stream for channel: %d%n%n", channelId);
            writer.close();
        }catch(FileNotFoundException e)
        {
            System.out.println("Something went wrong with writing\nChannel ID: "+channelId);
            e.printStackTrace();
        }
    }
}
