import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDao;
import entities.Filiere;
import entities.Role;
import entities.Student;

public class TestStudent {
	public static IDao<Student> lookUpStudentRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDao<Student>) context.lookup("ejb:AdnaneEAR/EJB_SERVEURG4/STUDENT_EJB!dao.IDao");

	}
	
	
	public static IDao<Filiere> lookUpFiliereRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDao<Filiere>) context.lookup("ejb:AdnaneEAR/EJB_SERVEURG4/FILIERE_EJB!dao.IDao");

	}
	
	public static IDao<Role> lookUpRoleRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDao<Role>) context.lookup("ejb:AdnaneEAR/EJB_SERVEURG4/ROLE_EJB!dao.IDao");

	}

	public static void main(String[] args) {
		try {
			IDao<Student> dao = lookUpStudentRemote();
			IDao<Filiere> daofiliere = lookUpFiliereRemote();
			IDao<Role> daorole = lookUpRoleRemote();
			
			Filiere f = new Filiere("a1","f1");
			daofiliere.create(f);
			
			System.out.println("avant changement : "+f);

			System.out.println("   ");

			f.setCode("C_modified");
			f.setName("name_modified");
			daofiliere.update(f);
			
			System.out.println("apres changement : "+f);
			
			System.out.println("    ");


			List<Role> r = new ArrayList<Role>();
			Role r1 = new Role("R1");
			Role r2 = new Role("R2");
			r.add(r1);
			r.add(r2);
			daorole.create(r1);
			daorole.create(r2);

			r1.setName("R11");
			r2.setName("R22");
			
			System.out.println("apres changement : "+r1);
			System.out.println("apres changement : "+r2);

			daorole.delete(r2);
			

			for(Role role: daorole.findAll()) {
				System.out.println("roles qui existent : "+role);
			}
			
			System.out.println(" ");


			Student s1 = new Student("yassineorochi@gmail.com","OOFUUDT21","yassine","akashi", "0610939910");
			s1.setFiliere(f); 
			s1.setRoles(r);
			dao.create(s1);
			
			
	  
			
			for(Student s: dao.findAll()) {
				System.out.println(s);
			}
			
			System.out.println("Roles de l etudiant : "+s1.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
			
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
