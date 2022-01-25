package com.petrov;

public class Main {
    public static void main(String[] args) {
        ListForStudy<String> list = new ArrayListForStudy();
        ListForStudy<String> linkedList = new LinkedListForStudy<>();
        list.add("D");
        list.add("R");
        list.add("E");
        list.remove("D");
        System.out.println(list.size());
        System.out.println(list);

        linkedList.add("Y");
        linkedList.add("O");
        System.out.println(linkedList.size());
        System.out.println(linkedList);
    }
}
