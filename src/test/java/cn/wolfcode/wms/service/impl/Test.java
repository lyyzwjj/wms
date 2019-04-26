package cn.wolfcode.wms.service.impl;

public class Test {
    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        System.out.println(String.format("之前 a = %s, b = %s", a, b));
        swap1(a, b);
        System.out.println(String.format("之后 a = %s, b = %s", a, b));
        Integer a1 = 3;
        Integer b1 = 5;
        System.out.println(String.format("Integer之前 a1 = %s, b1 = %s", a1, b1));
        swap2(a1, b1);
        System.out.println(String.format("Integer之后 a1 = %s, b1 = %s", a1, b1));
        MyInteger mya = new MyInteger(3);
        MyInteger myb = new MyInteger(5);
        System.out.println(String.format("MyInteger之前 mya = %s, myb = %s", mya, myb));
        swap3(mya, myb);
        System.out.println(String.format("MyInteger之后 mya = %s, myb = %s", mya, myb));
        int[] arr = new int[5];
        System.out.println(arr[0]);
        arrOper(arr);
        System.out.println(arr[0]);
        MyInteger myc = new MyInteger(3);
        myIntegerOper(myc);
        System.out.println(myc.a);
    }
    public static void arrOper(int[] arr) {
        arr[0] = 10;
    }
    public static void myIntegerOper(MyInteger myc) {
        myc.a=88;
    }

    static class MyInteger {
        int a;

        MyInteger(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return a + "";
        }
    }

    public static void swap3(MyInteger a, MyInteger b) {
        System.out.println(String.format("MyInteger调用变之前 mya = %s, myb = %s", a, b));
        MyInteger temp = a;
        a = b;
        b = temp;
        System.out.println(String.format("MyInteger调用变之后 mya = %s, myb = %s", a, b));
    }

    public static void swap1(int a, int b) {
        System.out.println(String.format("调用变之前 a = %s, b = %s", a, b));
        int temp = a;
        a = b;
        b = temp;
        System.out.println(String.format("调用变之后 a = %s, b = %s", a, b));
    }

    public static void swap2(Integer a, Integer b) {
        System.out.println(String.format("Integer调用变之前 a1 = %s, b1 = %s", a, b));
        Integer temp = a;
        a = b;
        b = temp;
        System.out.println(String.format("Integer调用变之后 a1 = %s, b1 = %s", a, b));
    }
}

