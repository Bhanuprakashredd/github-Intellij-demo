public class ParallelSum {
    private ParallelWorker[] workers;
    private int No_OfThreads;
    public ParallelSum(int No_OfThreads){
        this.No_OfThreads = No_OfThreads;
        this.workers = new ParallelWorker[No_OfThreads];
    }
    public int sum(int[] nums){
        int size = (int) Math.ceil((nums.length*1.0)/No_OfThreads);
        for(int i=0;i<No_OfThreads;++i){
            workers[i] = new ParallelWorker(nums,i*size,(i+1)*size);
            workers[i].start();
        }
        try{
            for(ParallelWorker worker:this.workers)
                worker.join();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        int total = 0;
        for(ParallelWorker worker:workers){
            total += worker.getPartialSum();
        }
        return total;
    }
}
