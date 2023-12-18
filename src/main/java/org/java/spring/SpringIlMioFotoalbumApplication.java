package org.java.spring;

import org.java.spring.auth.conf.AuthConfiguration;
import org.java.spring.auth.db.service.RoleService;
import org.java.spring.auth.db.service.UserService;
import org.java.spring.db.auth.pojo.Role;
import org.java.spring.db.auth.pojo.User;
import org.java.spring.pojo.Category;
import org.java.spring.pojo.Message;
import org.java.spring.pojo.Photo;
import org.java.spring.service.CategoryService;
import org.java.spring.service.MessageService;
import org.java.spring.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private RoleService roleServ;
	
	@Autowired
	private CategoryService categoryServ;
	
	@Autowired
	private PhotoService photoServ;
	
	@Autowired
	private MessageService messageServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role roleDemigod = new Role("DEMIGOD");
		Role roleSuperAdmin = new Role("SUPERADMIN");
		Role roleAdmin = new Role("ADMIN");
		
		roleServ.save(roleDemigod);
		roleServ.save(roleSuperAdmin);
		roleServ.save(roleAdmin);

		String pws = AuthConfiguration.passwordEncoder().encode("password");
		
		User user1 = new User("mimmo", pws, roleDemigod);
		
		User user2 = new User("pippo", pws, roleSuperAdmin);
		
		User user3 = new User("franco", pws, roleAdmin);
		
		
		userServ.save(user1);
		userServ.save(user2);
		userServ.save(user3);
		Category category1=new Category("bella", "foto belle");
		Category category2=new Category("bella2", "foto belle2");
		
		categoryServ.save(category1);
		categoryServ.save(category2);
		
		
		for(long num=1;num<=24;num++) {
			
				if(num<=12) {
					
					photoServ.save(new Photo("foto "+num, "desc "+num, userServ.findById(1) ,"https://picsum.photos/300?random="+num));
				}
				
				else {
					photoServ.save(new Photo("foto "+num, "desc "+num, userServ.findById(3) ,"https://picsum.photos/300?random="+num));
				}
		
				Photo photo=photoServ.findById(num);
				
				Category c = num%2==0?category1:category2;
				
				photo.setCategories(c);
				
				photoServ.save(photo);
				
			
		}
		Message msg1= new Message("email@email.email"
								, "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
								+ " Aenean at purus orci. Duis porttitor felis vitae"
								+ " congue suscipit. Sed vitae."
								, user1);
		
		messageServ.save(msg1);
				
	}

}
