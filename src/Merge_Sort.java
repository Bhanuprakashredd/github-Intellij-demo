public class Merge_Sort{
    private int[] nums;
    //it is not a in-place algorithm
    private int[] tempArray;
    public Merge_Sort(int[] nums){
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }
    public void sort(){
        mergeSort(0,nums.length-1);
    }

    public void mergeSort(int low, int high) {
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
        int[] nums ={4,6,-2,7,1,9};
        Merge_Sort m = new Merge_Sort(nums);
        m.sort();
        m.showArray();
    }
}