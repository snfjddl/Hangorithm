class Solution {
    public static int INF = 987654321;
    public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[]{INF, INF};
        int[] sortArr = new int[nums.length];
        for(int i=0 ; i<nums.length ; ++i)
            sortArr[i] = nums[i];
        Arrays.sort(sortArr);
        
        int L=0, R=nums.length-1;
        while(true) {
            if(sortArr[L]+sortArr[R] > target) {
                R--;
            }
            else if(sortArr[L]+sortArr[R] < target) {
                L++;
            } 
            else {  // 답을 구한 경우
                break;
            }
        }
        
        for(int i=0 ; i<nums.length ; ++i) {
            if(answer[0] == INF && (nums[i] == sortArr[L] || nums[i] == sortArr[R])) {
               answer[0] = i;
            }
            else if(nums[i] == sortArr[L] || nums[i] == sortArr[R]) {
               answer[1] = i;
                break;
            }
        }
        
        return answer;
    }
}