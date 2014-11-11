import java.util.List;
import java.util.ListIterator;


public class Member {
	private String name;
	private String email;
	private List<Member> friends;
	
	private void printSocialGraph(Member m) {
		if (m.friends.isEmpty()) return;
		
		List<Member> friends = m.friends;
		
		for (ListIterator<Member> it = friends.listIterator(); it.hasNext(); ) {
			String name = it.next().name;
			System.out.print(name + ", ");
		}
		System.out.println();
		
		// recurse
		for (ListIterator<Member> it = friends.listIterator(); it.hasNext(); ) {
			printSocialGraph(it.next());
		}
	}
}
