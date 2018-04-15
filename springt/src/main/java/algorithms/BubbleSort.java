package algorithms;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int ayy[]= {5,6,7,1,2,3,4,9,8,10} ;

        for(int x =0 ;x< ayy.length ;x++){
            for(int y = x+1 ;y<ayy.length ;y++ ){
                //每次子循环结束，就能找到该次循环的最小值，放在最前位置
                if(ayy[x] > ayy[y]){
                    swap(ayy,x,y);
                }
            }
        }
        System.out.println(Arrays.toString(ayy));
    }



    public static void swap(int ayy[],int x ,int y){
        int valY = ayy[y];
        ayy[y] = ayy[x] ;
        ayy[x] = valY ;
    }

}
