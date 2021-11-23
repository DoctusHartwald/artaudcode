package message;

public class MessageWelcome implements IMessage{
	public String decorateMessage(String message){
		return "***  "+message+" **";
	}
}
