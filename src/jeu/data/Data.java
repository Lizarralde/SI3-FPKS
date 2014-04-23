package jeu.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import jeu.model.Difficulties;
import jeu.model.Question;
import jeu.model.Themes;

public class Data {

    public static Object load(String location) {

        XStream xstream = new XStream(new DomDriver());

        Object obj = null;

        InputStream in = null;

        try {

            in = new FileInputStream(location);

            obj = xstream.fromXML(in);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } finally {

            if (in != null) {

                try {

                    in.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

        return obj;
    }

    public static void store(Object obj, String location) {

        XStream xstream = new XStream(new DomDriver());

        OutputStream out = null;

        try {

            out = new FileOutputStream(location);

            xstream.toXML(obj, out);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } finally {

            if (out != null) {

                try {

                    out.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        List<String> l = new ArrayList<String>();

        l.add("2");
        l.add("20");
        l.add("200");
        l.add("2000");

        Question q = new Question(Difficulties.MOYEN, Themes.MATHS, "1+1=?", l);
        
        Data.store(q, "./resources/questions/math/medium/question_1.xml");
    }
}
