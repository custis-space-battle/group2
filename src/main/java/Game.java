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

    private static String lastMove = "FIRE";

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

    public String compare (String value, WarField warField) {

        String str1 = "prepare!";
        Point point = null;
        if(value.indexOf("fire result: HIT") != -1){
            lastMove = "HIT";
        }
        else if(value.indexOf("fire!") != -1){
            point = warField.makeAvailbleTurn(lastMove);
        }
        else if(value.indexOf("fire result: MISS") != -1){
            lastMove = "MISS";
        }
        else if(value.indexOf("fire result: KILL") != -1){
            lastMove = "KILL";
        }
        else if(value.indexOf("HIT_AGAIN") != -1 || value.indexOf("HIT_MINE") != -1 || value.indexOf("MISS_AGAIN") != -1){
            lastMove = "OTHER";
        }
        if(new Compare().equalsValue(value, str1)){
            result = new Position().get_coordinates_to_string();
            System.out.println(result);
        }
        if(point != null){
            result = point.getX() + "," + point.getY();
        }
        return result;

    }


}