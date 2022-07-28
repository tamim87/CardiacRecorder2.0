package com.example.cardicrecoder;

public class RecordDB {

    String Id;
    Recorditem recorditem;

    public  RecordDB(){
    }

    public RecordDB(String id, Recorditem recorditem) {
        Id = id;
        this.recorditem = recorditem;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Recorditem getRecorditem() {
        return recorditem;
    }

    public void setRecorditem(Recorditem recorditem) {
        this.recorditem = recorditem;
    }
}
