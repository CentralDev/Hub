package me.imaginefrags.main.utils.Image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ***********************************************************************
 * Copyright CentralDev (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of ImagineFrags. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * Thanks.
 * ************************************************************************
 */
public class AnimatedMessage {
    private ImageMessage[] images;
    private int index = 0;

    public AnimatedMessage(ImageMessage... images) {
        this.images = images;
    }

    public AnimatedMessage(File gifFile, int height, char imgChar) throws IOException {
        List<BufferedImage> frames = getFrames(gifFile);
        images = new ImageMessage[frames.size()];
        for (int i = 0; i < frames.size(); i++) {
            images[i] = new ImageMessage(frames.get(i), height, imgChar);
        }
    }

    public List<BufferedImage> getFrames(File input) {
        List<BufferedImage> images = new ArrayList<BufferedImage>();
        try {
            ImageReader reader = ImageIO.getImageReadersBySuffix("GIF").next();
            ImageInputStream in = ImageIO.createImageInputStream(input);
            reader.setInput(in);
            for (int i = 0, count = reader.getNumImages(true); i < count; i++) {
                BufferedImage image = reader.read(i); // read next frame from gif
                images.add(image);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return images;
    }

    public ImageMessage current() {
        return images[index];
    }

    public ImageMessage next() {
        ++index;
        if (index >= images.length) {
            index = 0;
            return images[index];
        } else {
            return images[index];
        }
    }

    public ImageMessage previous() {
        --index;
        if (index <= 0) {
            index = images.length - 1;
            return images[index];
        } else {
            return images[index];
        }
    }

    public ImageMessage getIndex(int index) {
        return images[index];
    }
}
