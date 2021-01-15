package twiliopack;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class Test1
{
	public static void main(String[] args)
	{
		//Connect to twilio cloud for SMSService
		String sid="AC8265893ca78750905f0aa5ba861b2e76";
		String auth="dc067a4dd12743049ce88ba2ed401625";
		Twilio.init(sid, auth);
		ResourceSet<Message> messages=Message.reader().read();
		for(Message message:messages)
		{
			System.out.println(message.getFrom()+"--->"+message.getBody());
		}
	}
}
