//INSERT INTO user_roles (userid, role)
//VALUES (001, 'ROLE_USER');
//INSERT INTO user_roles (userid, role)
//VALUES (002, 'ROLE_ADMIN');
//INSERT INTO user_roles (userid, role)
//VALUES (002, 'ROLE_USER');

package guru.springframework.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.UserRole;
import guru.springframework.repositories.UserRoleRepository;

@Component
public class UserRoleLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRoleRepository userRoleRepository;

    private Logger log = Logger.getLogger(UserRoleLoader.class);

    @Autowired
    public void setUserRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        UserRole sijmenAdmin = new UserRole();
        sijmenAdmin.setRole("ROLE_ADMIN");
        sijmenAdmin.setUserid(1L);
        userRoleRepository.save(sijmenAdmin);

        log.info("Saved UserRoles (Sijmen ADMIN) - id: " + sijmenAdmin.getUserroleid());
        
        UserRole sijmenUser = new UserRole();
        sijmenUser.setRole("ROLE_USER");
        sijmenUser.setUserid(1L);
        userRoleRepository.save(sijmenUser);

        log.info("Saved UserRoles (Sijmen USER) - id: " + sijmenUser.getUserroleid());
        
        
        
        UserRole sanderUser = new UserRole();
        sanderUser.setRole("ROLE_USER");
        sanderUser.setUserid(2L);
        userRoleRepository.save(sanderUser);

        log.info("Saved UserRoles (Sander USER) - id: " + sanderUser.getUserroleid());
    }
}
