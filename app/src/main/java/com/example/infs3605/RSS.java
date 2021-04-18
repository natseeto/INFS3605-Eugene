package com.example.infs3605;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "rss", strict = false)
public class RSS implements Serializable {

    @Element(name = "channel")
    public Feed channel;

    public Feed getChannel() {
        return channel;
    }

    public void setChannel(Feed channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "RSS{" +

                ",\n channel=" + channel +
                '}';
    }
}
