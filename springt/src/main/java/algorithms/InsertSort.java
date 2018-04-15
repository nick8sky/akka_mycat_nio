package algorithms;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int ayy[] = {5, 6, 7, 1, 2, 3, 4, 9, 8, 10};
        int insert;
        int j;
        //从第二元素开始 // i =3
        for (int i = 1; i < ayy.length; i++) {
            j = i;
            insert = ayy[i];
            while (j > 0 && insert < ayy[j - 1]) {
                ayy[j] = ayy[j - 1];
                j--;
/*                System.out.println(Arrays.toString(ayy));
                System.out.println("j" + j);*/
            }
            ayy[j] = insert;
        }
        System.out.println(Arrays.toString(ayy));
    }



}

