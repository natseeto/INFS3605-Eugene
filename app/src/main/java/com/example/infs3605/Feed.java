package com.example.infs3605;

import java.io.Serializable;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "channel", strict = false)
public class Feed  implements Serializable {

    @Element(name = "title")
    private String title;

    @ElementList(inline = true, name = "item")
    private List<Entry> entrys;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Entry> getEntrys() {
        return entrys;
    }

    public void setEntrys(List<Entry> entrys) {
        this.entrys = entrys;
    }

    @Override
    public String toString() {
        return "Feed{" +

                ",\n entrys=" + entrys +
                '}';
    }
}
