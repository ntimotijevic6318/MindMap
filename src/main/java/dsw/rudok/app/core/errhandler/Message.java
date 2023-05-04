package dsw.rudok.app.core.errhandler;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter

public class Message {

    String content;
    Enum type;
    String timestamp;

    public Message(String content, Enum type) {
     this.content = content;
     this.type = type;
     this.timestamp = generateTimeStamp();
    }

    private String generateTimeStamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy h:mm a");
        String formattedDate = sdf.format(date);


        return formattedDate;
    }

}
