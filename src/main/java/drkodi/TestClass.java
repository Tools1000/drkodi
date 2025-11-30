/*
 *     Dr.Kodi - A Minimalistic Batch Renamer
 *
 *     Copyright (C) 2024
 *
 *     This file is part of Dr.Kodi.
 *
 *     You can redistribute it and/or modify it under the terms of the GNU Affero
 *     General Public License as published by the Free Software Foundation, either
 *     version 3 of the License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful, but WITHOUT
 *     ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *     FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 *     for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package drkodi;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class TestClass {

    public static void main(String[] args) throws IOException {


//        FFmpegFrameGrabber g = new FFmpegFrameGrabber("/Volumes/Case-Sensitive/Data/");

//        FFmpegFrameGrabber g = new FFmpegFrameGrabber("/Volumes/Case-Sensitive/Data/");


        FFmpegFrameGrabber g = new FFmpegFrameGrabber("/Volumes/Case-Sensitive/Data/);

        g.start();

        int total = g.getLengthInFrames();
        int divider = 200;
        int frameNr = total / divider;

        System.out.println("Frame " + frameNr);

        g.setFrameNumber(frameNr);

        Java2DFrameConverter converter = new Java2DFrameConverter();

        for (int i = 0 ; i < 100 ; i++) {

            Frame frame = g.grabImage(); // It is important to use grabImage() to get a frame that can be turned into a BufferedImage

            if(i % 10 == 0){

//                System.out.println("IIII: " + i);

                BufferedImage bi = converter.convert(frame);

                ImageIO.write(bi, "png", new File("video-frame-" + System.currentTimeMillis() + ".png"));

            }


        }

        g.stop();
    }
}
