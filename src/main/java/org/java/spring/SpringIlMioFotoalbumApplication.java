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
		
		roleServ.save(roleDemigod);

		String pws = AuthConfiguration.passwordEncoder().encode("password");
		
		User owner1 = new User("mimmo", pws, roleDemigod);
		
		User owner2 = new User("pippo", pws, roleDemigod);
		
		userServ.save(owner1);
		userServ.save(owner2);
		
		Category category1=new Category("bella", "foto belle");
		Category category2=new Category("bella2", "foto belle2");
		
		categoryServ.save(category1);
		categoryServ.save(category2);
		
		
		for(long num=1;num<=14;num++) {
			
		
			
			if(num>12) {
				photoServ.save(new Photo("foto "+num, "desc "+num, userServ.findById(2) ,"https://picsum.photos/300?random="+num));
			}

			else {
				photoServ.save(new Photo("foto "+num, "desc "+num, userServ.findById(1) ,"https://picsum.photos/300?random="+num));
			}
			if(num<10) {
				
				Photo photo=photoServ.findById(num);
				
				Category c = num%2==0?category1:category2;
				
				photo.setCategories(c);
				
				photoServ.save(photo);
				
			}
			
		}
		Message msg1= new Message("email@email.email"
								, "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
								+ " Aenean at purus orci. Duis porttitor felis vitae"
								+ " congue suscipit. Sed vitae."
								, owner1);
		
		messageServ.save(msg1);
				
	}

}
