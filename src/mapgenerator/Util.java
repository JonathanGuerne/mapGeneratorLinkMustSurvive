/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.misc.Launcher;

/**
 *
 * @author cp-12jog
 */
public class Util {

    static HashMap<String, Image> images = new HashMap<>();
    static HashMap<String, Clip> sounds = new HashMap<>();
    static Random rnd = new Random();

    static Image loadImage(String name) {
        Image img = images.get(name);
        if (img == null) {
            String path = "/images/" + name + ".png";
            try {
                img = ImageIO.read(Util.class.getResource(path));
                images.put(name, img);
            } catch (IOException ex) {
                img = null;
            }
        }
        return img;
    }

    static Clip loadSound(String name) {
        Clip sound = sounds.get(name);
        if (sound == null) {
            String path = "/sounds/" + name + ".wav";
            try {
                AudioInputStream stream = AudioSystem.getAudioInputStream(Util.class.getResource(path));
                DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
                sound = (Clip) AudioSystem.getLine(info);
                sound.open(stream);
                sounds.put(name, sound);
            } catch (Exception ex) {
                sound = null;
            }
        }
        return sound;
    }

    static int randomInt(int n) {
        return rnd.nextInt(n);
    }

    public void getDirs(String path) throws IOException {
         File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

        if (jarFile.isFile()) {  // Run with JAR file
            final JarFile jar = new JarFile(jarFile);
            final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
            while (entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                if (name.startsWith(path + "/")) { //filter according to the path
                    System.out.println(name);
                }
            }
            jar.close();
        } else { // Run with IDE
            final URL url = Launcher.class.getResource("/" + path);
            if (url != null) {
                try {
                    final File apps = new File(url.toURI());
                    for (File app : apps.listFiles()) {
                        System.out.println(app);
                    }
                } catch (URISyntaxException ex) {
                    // never happens
                }
            }
        }
    }

    static ArrayList<String> getAllDir(File curDir) {

        File[] filesList = curDir.listFiles();
        ArrayList<String> listStr = new ArrayList<>();
        for (File f : filesList) {
            if (f.isDirectory()) {
                getAllFiles(f);
            }
            if (f.isDirectory()) {
                listStr.add(f.getName());
            }
        }

        return listStr;
    }

    static ArrayList<String> getAllFiles(File curDir) {

        File[] filesList = curDir.listFiles();
        ArrayList<String> listStr = new ArrayList<>();
        for (File f : filesList) {
            if (f.isDirectory()) {
                getAllFiles(f);
            }
            if (f.isFile()) {
                listStr.add(f.getName());
            }
        }

        return listStr;

    }

    static Clip CreateSound(String fileName) {
        Clip myClip = null;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                myClip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                myClip.open(ais);
            } else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Sound: Line Unavailable: " + e);
        }
        return myClip;
    }
}
