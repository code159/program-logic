package shuo.laoma.concurrent.c69;

import java.io.FileInputStream;
import java.io.IOException;

public class InterruptReadDemo {
	
    private class A extends Thread {
    	private FileInputStream in;
    	
    	public A(FileInputStream in){
    		this.in=in;
    	}
        
    	@Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println(in.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("exit");
        }

        public void cancel() {
            try {
                in.close();
            } catch (IOException e) {
            }
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
    	//need to set file name locally
        A t = new InterruptReadDemo().new A(new FileInputStream("D:/CrackCaptcha.log"));
        t.start();
        Thread.sleep(100);
        t.cancel();
    }
}


