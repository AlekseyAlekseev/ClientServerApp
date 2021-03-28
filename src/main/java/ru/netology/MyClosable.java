package ru.netology;

public class MyClosable implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("Работа окончена...");
    }
}
