package concurrency.problems;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadManagerClient {


    private static class DownloadTask implements Runnable{

        String fileUrl;

        public DownloadTask(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        @Override
        public void run() {
            System.out.println("Starting Download of file : "+this.fileUrl);
            for (int i=0;i<=100;i+=10){
                try {
                    System.out.println("Download Progress for file : "+this.fileUrl+"  is : "+i+"%");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Failed to Download the File : "+this.fileUrl);
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Download Completed for File : "+this.fileUrl);
        }
    }

    private static class DownloadManager {

        ExecutorService executorService  = null;

        public DownloadManager(Integer numberOfThreads) {
            executorService = Executors.newFixedThreadPool(numberOfThreads);
        }

        private void downloadFiles(List<String> files){
            for (String file:files){
                executorService.submit(new DownloadTask(file));
            }
        }
    }


    static void main() throws InterruptedException {

        Integer numberOfThread =1;
        long startTime  = System.currentTimeMillis();

        DownloadManager dm = new DownloadManager(numberOfThread);
        List<String> files = List.of("File 1","File 2","File 3","File 4","File 5");
        dm.downloadFiles(files);

        /*
        for (int i=0;i<5;i++){
            System.out.println("Main Thread is doing some task.....");
            Thread.sleep(1000);
        }
*/
        System.out.println((System.currentTimeMillis()-startTime)/1000+"s require of download "+files.size()+" with number of threads : "+numberOfThread );


    }

}
