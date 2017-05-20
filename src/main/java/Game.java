import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.IOException;

import java.io.StringReader;
import java.nio.ByteBuffer;
import java.util.Scanner;


/**
 * Created by Участник on 20.05.2017.
 */
public class Game{
    private static final Logger log = LoggerFactory.getLogger(Game.class);
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String preGame() {
        Position position = new Position();
        String str = position.get_coordinates_to_string();
        return str;
    }
    public ByteBuffer encodeString (String s) {
        return ByteBuffer.wrap(s.getBytes());
    }
    public String decodeString(ByteBuffer message) {
        byte[] bytes = new byte[message.remaining()];
        message.get(bytes);
        return new String(bytes);
    }

    public String compare (String value) {
        try {
            BufferedReader bufferedReader = new BufferedReader((new StringReader(value)));
            String line = null;
            Scanner scanner = null;
            while ((line = bufferedReader.readLine()) != null) {
                scanner = new Scanner(line);
                while (scanner.hasNext()) {
                    String val = scanner.next();
                    String str1 = "prepare!";
                    if(new Compare().equalsValue(val, str1)){
                        result = new Position().get_coordinates_to_string();
                        System.out.println(result);
                    }
                }


            }
            bufferedReader.close();
        } catch (IOException e) {
            log.trace("проблемы с объектом");
        }
        return result;

    }


}