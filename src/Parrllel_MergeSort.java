public class Parrllel_MergeSort {
        private int[] nums;
        //it is not a in-place algorithm
        private int[] tempArray;
        public Parrllel_MergeSort (int[] nums){
            this.nums = nums;
            this.tempArray = new int[nums.length];
        }
        public void parrllel_MergeSort(int low,int high,int numOfThreads){
            if(numOfThreads <= 1){
                mergeSort(low,high);
                return;
            }
            int middleIndex = (low+high)/2;
            Thread leftSorter = createThread(low,middleIndex,numOfThreads);
            Thread rightSorter = createThread(middleIndex+1,high,numOfThreads);
            leftSorter.start();
            rightSorter.start();
            try {
                leftSorter.join();
                rightSorter.join();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            merge(low,middleIndex,high);
        }
        private Thread createThread(int low,int high,int numOfThreads){
            return new Thread() {
                public void run() {
                    parrllel_MergeSort(low, high, numOfThreads/2);
                }
            };
        }
        /*public void sort(){
            mergeSort(0,nums.length-1);
        }*/

        private void mergeSort(int low, int high) {
            if(low>=high){
                return;
            }
            int middle = (low+high)/2;
            mergeSort(low,middle);
            mergeSort(middle+1,high);
            merge(low,middle,high);

        }
        public void merge(int low,int middle,int high){
            for(int i=low;i<=high;++i){
                tempArray[i] = nums[i];
            }
            int i = low;int j = middle+1;
            int k = low;
            while(i<=middle && j<=high){
                if(tempArray[i]<tempArray[j]){
                    nums[k] = tempArray[i];
                    ++i;
                }
                else{
                    nums[k] = tempArray[j];
                    ++j;
                }
                ++k;
            }
            while(i<=middle){
                nums[k] = tempArray[i];
                ++i;
                ++k;
            }
            while(j<=high){
                nums[k] = tempArray[j];
                ++j;
                ++k;
            }
        }
        private void swap(int i,int j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        public void showArray(){
            for(int i=0;i<nums.length;++i){
                System.out.print(nums[i]+" ");
            }
        }
    public static void main(String[] args){
        int[] nums = {4, 6, -2, 7, 1, 9};
        Parrllel_MergeSort sorter = new Parrllel_MergeSort(nums);

        // Number of available processors to decide the level of parallelism
        int numThreads = Runtime.getRuntime().availableProcessors();

        // Run the parallel merge sort
        sorter.parrllel_MergeSort(0, nums.length - 1, numThreads);

        // Show the sorted array
        sorter.showArray();
    }
  
}

