package com.example.wss_2000;

import com.example.wss_2000.AppHelp.GenerateValueFiles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void generateDimens() {
        int baseW = 320;
        int baseH = 480;
        String addition = "";
//        try {
//            if (args.length >= 3) {
//                baseW = Integer.parseInt(args[0]);
//                baseH = Integer.parseInt(args[1]);
//                addition = args[2];
//            } else if (args.length >= 2) {
//                baseW = Integer.parseInt(args[0]);
//                baseH = Integer.parseInt(args[1]);
//            } else if (args.length >= 1) {
//                addition = args[0];
//            }
//        } catch (NumberFormatException e) {
//
//            System.err
//                    .println("right input params : java -jar xxx.jar width height w,h_w,h_..._w,h;");
//            e.printStackTrace();
//            System.exit(-1);
//        }

        new GenerateValueFiles(baseW, baseH, addition).generate();
    }
}