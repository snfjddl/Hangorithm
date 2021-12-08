class Solution {
    public int maxSubArray(int[] nums) {
        int tmp = nums[0], max = nums[0];
        
        for(int i=1 ; i<nums.length ; ++i) {
            int next = tmp+nums[i];
            if(next <= 0) {
                if(nums[i] > 0) {
                    tmp = nums[i];
                } else {
                    tmp = 0;
                }
                max = Math.max(max, nums[i]);
            } else {
                if(next < nums[i]) {
                    tmp = nums[i];
                } else {
                    tmp = next;    
                }
                max = Math.max(max, tmp);
            }  
        }
        
        return max;
    }
}