package flicksArchive;

import java.util.Comparator;

public class ComparatorNotas implements Comparator<Elemento>{

	@Override
	public int compare(Elemento o1, Elemento o2) {
		int n= o2.getNotaUsuario() - o1.getNotaUsuario();
		if(n==0) {
			n=o1.compareTo(o2);
		}
		
		return n;
	}

}
