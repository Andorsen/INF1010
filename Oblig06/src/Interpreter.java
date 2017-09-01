import krypto.Kryptografi;

class Interpreter extends Thread
{
    private boolean interpret;
    private int interpreterId;

    Interpreter(int id)
    {
        interpreterId = id;
        interpret = true;
    }

    public void run()
    {
        String encrypted;
        String decrypted;
        MsgPart part;
        while(interpret)
        {
            part = Manager.encryptedMsgs.poll();
            if(part != null)
            {
                encrypted = part.getMessage();
                decrypted = Kryptografi.dekrypter(encrypted);
                part.setMessage(decrypted);
                //System.out.printf("Remaining: %d%n", Manager.encryptedMsgs.size());
            }else
            {
                try{Thread.sleep(50);}catch(InterruptedException e){e.printStackTrace();}
                if(Manager.activeListeners==0 && Manager.encryptedMsgs.isEmpty())
                {
                    interpret = false;
                }
            }
        }
        System.out.printf("Thread exited, Interpreter %d.%n", interpreterId);
    }
}
