package algorithms;

import java.util.Stack;

public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> nums = new Stack<>() ;

        String str = "(2+(5*2) ) + (2*4)" ;
        str = "(" +str+")" ;  //格式化输入

        for(int i=0;i<str.length();i++){
            String subStr = str.substring(i, i+1) ;
            System.out.println(subStr);
            if(subStr.equals(" ")) ;
            else if(subStr.equals("(")) ;
            else if(subStr.equals("+")) ops.push(subStr) ;
            else if(subStr.equals("-")) ops.push(subStr) ;
            else if(subStr.equals("*")) ops.push(subStr) ;
            else if(subStr.equals("/")) ops.push(subStr) ;
            else if(subStr.equals("sqrt")) ops.push(subStr) ;
            else if(subStr.equals(")")) {  //输出标识
                System.out.println("-------");
                String op = ops.pop();
                double var = nums.pop() ;
                if(op.equals("+")) var = nums.pop() + var ;
                else if(op.equals("-")) var = nums.pop() - var ;
                else if(op.equals("*")) var = nums.pop() * var ;
                else if(op.equals("/")) var = nums.pop() / var ;
                else if(op.equals("sqrt")) var = Math.sqrt(var) ;
                nums.push(var) ;
            }
            else {
                nums.push(Double.parseDouble(subStr)) ;
            }

        }

        System.out.println(nums.pop());
        //System.out.println(nums.pop());
    }
}
