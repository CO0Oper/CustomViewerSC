package com.example.android.customviewer;

public class Collections {

    private String handle, title;

    private String image;

    private int inventory;

    private String c_id, p_id;

    private String Body;

    private String type, tags, vendor;

    public Collections() { }

    public Collections(String Cid, String label, String title, String image, String body) {
        this.c_id = Cid;
        this.handle = label;
        this.title = title;
        this.image = image;
        this.Body = body;
    }

    public Collections(String P_id) {
        this.p_id = P_id;
    }

    public Collections(String title, String body, String vendor, String type,  String tags, int inventory){
        this.title = title;
        this.Body = body;
        this.vendor = vendor;
        this.type = type;
        this.tags = tags;
        this.inventory = inventory;
    }

    public String getLabel() { return handle; }

    public String getTitle() { return title; }

    public String getImage() { return image; }

    public int getInventory() { return inventory; }

    public String getcId() { return c_id; }

    public String getpId() { return p_id; }

    public String getBody() { return Body; }

}
