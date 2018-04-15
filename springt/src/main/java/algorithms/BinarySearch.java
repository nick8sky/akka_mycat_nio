package algorithms;

public class BinarySearch {
    /**
     * 递归实现二分查找
     * @param dataset 有序数组
     * @param target  需要找出target值的下标
     */
    public static int binarySearch(int[] dataset, int target, int beginIndex, int endIndex) {
        if (target < dataset[beginIndex] || target > dataset[endIndex] || beginIndex > endIndex) {
            return -1;
        }

        int midIndex = (beginIndex + endIndex) / 2;

        if (target < dataset[midIndex]) {
            return binarySearch(dataset, target, beginIndex, midIndex - 1);
        } else if (target > dataset[midIndex]) {
            return binarySearch(dataset, target, midIndex + 1, endIndex);
        } else {
            return midIndex;
        }
    }

    /**
     * 循环实现二分查找算法
     * @param dataset 有序数组
     * @param target  需要找出target值的下标
     */
    public static int binarySearch(int[] dataset, int target) {
        int low = 0;
        int high = dataset.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (target == dataset[middle]) {
                return middle;
            } else if (target < dataset[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {6, 12, 33, 87, 90, 97, 108, 561};

        System.out.println("递归查找：" + binarySearch(arr, 87, 0, arr.length - 1));
        System.out.println("循环查找：" + (binarySearch(arr, 87)));
    }


}
