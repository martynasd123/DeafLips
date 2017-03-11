package lips.deafcommunication.deaflips;

/**
 * Created by valentinas on 11/03/2017.
 */



public class MessageData {

    public static final int TYPE_VOICE = 0; //Casual
    public static final int TYPE_NORMAL = 1; //Message
    public int type;
    public String message;


    public MessageData(int type, String message){
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
