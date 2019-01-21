package com.example.android.customviewer;

public class Collections {

    private String handle, title;

    private String image;

    private int inventory;

    private long c_id, p_id;

    private String Body;

    public Collections(long Cid, String label, String title, String image, String body) {
        this.c_id = Cid;
        this.handle = label;
        this.title = title;
        this.image = image;
        this.Body = body;
    }

    public String getLabel() { return handle; }

    public String getTitle() { return title; }

    public String getImage() { return image; }

    public int getInventory() { return inventory; }

    public Long getcId() { return c_id; }

    public String getBody() { return Body; }

}
