package com.company;

public interface IDBOperation {

    public boolean Insert();
    public boolean Update();
    public boolean Delete();
    public boolean Load(int recordId);


}
