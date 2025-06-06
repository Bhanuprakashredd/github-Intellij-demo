import java.util.Random;

public class ParallelSum_Application_Run {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[100000000];
        for (int j = 0; j < 100000000; j++) {
            arr[j] = random.nextInt(100);
        }
        int n = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();
        ParallelSum parallel = new ParallelSum(n);
        System.out.println(parallel.sum(arr));
        long end = System.currentTimeMillis();
        System.out.println("The time taken for Parallel Execution:"+ (end-start));
    }

}
