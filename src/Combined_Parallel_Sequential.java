import java.util.Random;

public class Combined_Parallel_Sequential {
    public static void main(String[] args){
        int No_Of_Threads = Runtime.getRuntime().availableProcessors();
        int[] arr1 = createArray(100000000);
        int[] arr2 = createArray(arr1.length);
        for(int i=0;i<arr1.length;++i){
            arr2[i] = arr1[i];
        }
        Parrllel_MergeSort parallel = new Parrllel_MergeSort(arr1);
        long StartTime1 = System.currentTimeMillis();
        parallel.parrllel_MergeSort(0,arr1.length-1,No_Of_Threads);
        long endTime1 = System.currentTimeMillis();
        System.out.println("the time taken to complate in parallel Computing is:"+(endTime1-StartTime1));
        long StartTime2 = System.currentTimeMillis();
        Merge_Sort sequential = new Merge_Sort(arr2);
        sequential.mergeSort(0,arr2.length-1);
        long endTime2 = System.currentTimeMillis();
        System.out.println("the time taken to complete in sequentail Computing is:"+(endTime2-StartTime2));


    }
    private static int[] createArray(int n){
        Random random = new Random();
        int[] a = new int[n];
        for(int i=0;i<n;++i){
            a[i] = random.nextInt();
        }
        return a;
    }
}
