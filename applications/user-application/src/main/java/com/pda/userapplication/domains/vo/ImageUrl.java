package com.pda.userapplication.domains.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.net.MalformedURLException;
import java.net.URL;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUrl {
    private URL url;

    public static ImageUrl of(String url) {
        if (url == null)
            throw new NullPointerException("url is null");

        try {
            return new ImageUrl(new URL(url));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }

    @Override
    public String toString() {
        return url.toString();
    }
}
