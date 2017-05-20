import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Участник on 20.05.2017.
 */
public class Compare {
    private volatile boolean aBoolean;

    private static  final Logger log = LoggerFactory.getLogger(Compare.class);

    public boolean equalsValue(String str1, String str2) {
        try {
            if (str1.equalsIgnoreCase(str2)) {
                aBoolean = true;
                return aBoolean;

            } else {
                aBoolean = false;
                return aBoolean;
            }

        } catch (Exception e) {
            log.trace("проблема в " +
                    "итерации по символам");
        } return aBoolean;
    }
}
