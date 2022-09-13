package BatchParallelProcessing.Project2.ItemProcessor;
import BatchParallelProcessing.Project2.Entity.DataOfUsers;
import BatchParallelProcessing.Project2.Mail.MailSendingService.MailsendingService;

import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<DataOfUsers, DataOfUsers>{

    private MailsendingService service = new MailsendingService();
    @Override
		public DataOfUsers process(DataOfUsers item) throws Exception{
            String username = item.getFirstName();
            String usermail = item.getEmail();
            
            System.out.println(" Mail sending Processor Start :: " + username.toUpperCase());

            // service.sendEmail(usermail, "You have a meeting Scheduled", username);
            
            
            // System.out.println(" Mail sending Processor End ");
            // System.out.println();
            
            return item;
        }
			
}
