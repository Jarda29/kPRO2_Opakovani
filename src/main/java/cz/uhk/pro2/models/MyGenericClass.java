package cz.uhk.pro2.models;

import cz.uhk.pro2.models.io.FileOperations;

public class MyGenericClass<T extends FileOperations> {
    private T obj;

    public MyGenericClass(T obj){
        this.obj = obj;
    }
}
