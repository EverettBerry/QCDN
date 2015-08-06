package ndejaco.qcdn;

import java.util.ArrayList;

/**
 * Created by Nicholas on 8/5/2015.
 */
public class Playlist {

private String VideoURL[] = new String[5];
    private int size = 0;

    public Playlist(String type) {
        if (type.equals("qcdn")) {
            VideoURL = new String[]{"http://192.168.1.16:8000/video1.mp4",
                    "http://192.168.1.16:8000/video2.mp4",
                    "http://192.168.1.16:8000/video3.mp4",
                    "http://192.168.1.16:8000/video4.mp4",
                    "http://192.168.1.16:8000/video5.mp4"};

            size = 5;
        }

         else {
            VideoURL = new String[]{"http://128.46.75.105:8080/FinalWebsiteDemo.mp4"};
            size = 1;
        }

    }



    public String getVideo(int number) {
        if (number != size) {
            return VideoURL[number];
        } else {
            return VideoURL[0];
        }

    }

    public int getSize() {
        return size;
    }

    public void addURL(String url) {
        VideoURL[size] = url;
        size++;
    }

}
