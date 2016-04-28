package guru.springframework.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import guru.springframework.domain.User;
import guru.springframework.repositories.UserRepository;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    private Logger log = Logger.getLogger(UserLoader.class);

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	
        User sijmen = new User();
        sijmen.setUserName("sijmen");
        //sijmen.setPassword(encoder.encode("sijmen"));
        sijmen.setPassword("sijmen");
        sijmen.setEmail("fake@email.com");
        sijmen.setEnabled(1);
        userRepository.save(sijmen);

        log.info("Saved User (Sijmen) - id: " + sijmen.getUserId());
        
        User sander = new User();
        sander.setUserName("sander");
        //sander.setPassword(encoder.encode("sander"));
        sander.setPassword("sander");
        sander.setEmail("fake@email.com");
        sander.setEnabled(1);
        userRepository.save(sander);

        log.info("Saved User (Sander) - id: " + sander.getUserId());

    }
}
